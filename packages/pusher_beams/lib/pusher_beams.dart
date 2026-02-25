import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:pusher_beams_platform_interface/method_channel_pusher_beams.dart';
import 'package:pusher_beams_platform_interface/pusher_beams_platform_interface.dart';
import 'package:uuid/uuid.dart';

export 'package:pusher_beams_platform_interface/method_channel_pusher_beams.dart'
    show BeamsAuthProvider;

const _uuid = Uuid();

/// App-facing Implementation for [PusherBeamsPlatform] plugin.
/// It's designed to be a singleton and must be consumed with [PusherBeams.instance].
class PusherBeams extends PusherBeamsPlatform with CallbackHandlerApi {
  /// Stores the ids and the [Function]s to call back.
  static final Map<String, Function> _callbacks = {};

  static final dynamic _pusherBeamsApi = PusherBeamsPlatform.instance;

  PusherBeams._privateConstructor() {
    if (!kIsWeb) {
      CallbackHandlerApi.setup(this);
    }
  }

  static final PusherBeams _instance = PusherBeams._privateConstructor();

  /// The instance of [PusherBeams].
  /// This is intended to be a singleton
  static PusherBeams get instance => _instance;

  /// Reads the [info] key included in a Push Notification when opening the app
  /// by tapping on it.
  ///
  /// ## Example Usage
  ///
  /// ```dart
  /// function someAsyncFunction() async {
  ///   final message = await PusherBeams.instance.getInitialMessage();
  /// }
  /// ```
  ///
  /// In order to receive data the published payload must contain an [info] key
  /// in the [data] key:
  ///
  /// ```json
  /// {
  ///   "interests":["hello"],
  ///   "apns": {
  ///     "aps": {
  ///       "alert": {"title":"Hello", "body":"Hello, world!"},
  ///     },
  ///    "data": {
  ///       "info": { "name": "george" }
  ///     }
  ///   },
  ///   "fcm": {
  ///     "notification": {"title":"Hello", "body":"Hello, world!"},
  ///     "data": {
  ///       "info": { "name": "george" }
  ///     }
  ///   }
  /// }
  /// ```
  /// Throws an [Exception] in case of failure.
  @override
  Future<Map<String, dynamic>?> getInitialMessage() async {
    return await _pusherBeamsApi.getInitialMessage();
  }

  /// Adds an [interest] in this device.
  ///
  /// ## Example Usage
  ///
  /// ```dart
  /// function someAsyncFunction() async {
  ///   await PusherBeams.instance.addDeviceInterest('apple');
  /// }
  /// ```
  ///
  /// Throws an [Exception] in case of failure.
  @override
  Future<void> addDeviceInterest(String interest) async {
    await _pusherBeamsApi.addDeviceInterest(interest);
  }

  /// Clear all the state from [PusherBeams] library, leaving an empty state.
  ///
  /// ## Example Usage
  ///
  /// ```dart
  /// function someAsyncFunction() async {
  ///   await PusherBeams.instance.clearAllState();
  /// }
  /// ```
  ///
  /// Throws an [Exception] in case of failure.
  @override
  Future<void> clearAllState() async {
    await _pusherBeamsApi.clearAllState();

    if (!kIsWeb) {
      _callbacks.clear();
    }
  }

  /// Unsubscribes all interests from this device.
  ///
  /// ## Example Usage
  ///
  /// ```dart
  /// function someAsyncFunction() async {
  ///   await PusherBeams.instance.clearDeviceInterests();
  /// }
  /// ```
  ///
  /// Throws an [Exception] in case of failure.
  @override
  Future<void> clearDeviceInterests() async {
    await _pusherBeamsApi.clearDeviceInterests();
  }

  /// Get the interests registered in this device. Returns a [List] containing the interests as [String].
  ///
  /// ## Example Usage
  ///
  /// ```dart
  /// function someAsyncFunction() async {
  ///   print(await PusherBeams.instance.getDeviceInterests()); // Prints: ['banana', 'apple']
  /// }
  /// ```
  ///
  /// Throws an [Exception] in case of failure.
  @override
  Future<List<String?>> getDeviceInterests() {
    return _pusherBeamsApi.getDeviceInterests();
  }

  /// Registers a listener which calls back the [OnInterestsChange] function on interests modifications within this device.
  /// **This is not implemented on web.**
  ///
  /// ## Example Usage
  ///
  /// ```dart
  /// function someAsyncFunction() async {
  ///   await PusherBeams.instance.onInterestChanges((interests) => {
  ///     print('Interests: $interests') // This prints Interests: ['banana', 'apple', ...]
  ///   });
  /// }
  /// ```
  ///
  /// Throws an [Exception] in case of failure.
  @override
  Future<void> onInterestChanges(OnInterestsChange callback) async {
    final callbackId = _uuid.v4();

    if (!kIsWeb) {
      _callbacks[callbackId] = callback;
    }

    await _pusherBeamsApi.onInterestChanges(kIsWeb ? callback : callbackId);
  }

  /// Removes an [interest] in this device.
  ///
  /// ## Example Usage
  ///
  /// ```dart
  /// function someAsyncFunction() async {
  ///   await PusherBeams.instance.removeDeviceInterest('banana');
  /// }
  /// ```
  ///
  /// Throws an [Exception] in case of failure.
  @override
  Future<void> removeDeviceInterest(String interest) async {
    await _pusherBeamsApi.removeDeviceInterest(interest);
  }

  /// Sets the [interests] provided with a [List].
  /// This overrides and unsubscribe any interests not listed in [interests].
  ///
  /// ## Example Usage
  ///
  /// ```dart
  /// function someAsyncFunction() async {
  ///   await PusherBeams.instance.setDeviceInterests(['banana', 'apple', 'garlic']);
  /// }
  /// ```
  ///
  /// Throws an [Exception] in case of failure.
  @override
  Future<void> setDeviceInterests(List<String> interests) async {
    await _pusherBeamsApi.setDeviceInterests(interests);
  }

  /// Sets authentication for this device, so you can send notifications specifically for this device.
  /// You must create a [BeamsAuthProvider] in order to pass the [provider] argument.
  ///
  /// ## Example Usage
  ///
  /// ```dart
  /// function someAsyncFunction() async {
  ///   final BeamsAuthProvider provider = BeamsAuthProvider()
  ///     ..authUrl = 'https://some-auth-url.com/secure'
  ///     ..headers = {
  ///       'Content-Type': 'application/json'
  ///     }
  ///     ..queryParams = {
  ///       'page': '1'
  ///     }
  ///     ..credentials = 'omit';
  ///
  ///   await PusherBeams.instance.setUserId('THIS IS AN USER ID', provider, (error) => {
  ///     if (error != null) {
  ///       print(error)
  ///     }
  ///
  ///     // Success! Do something...
  ///   });
  /// }
  /// ```
  ///
  /// ## [BeamsAuthProvider]
  /// This is the list parameters table which describes the class to creates a provider.
  ///
  /// | Parameter   | Description                                               | Required |
  /// |-------------|-----------------------------------------------------------|----------|
  /// | authUrl     | An HTTP url where Pusher Beams will try to authenticate.  | Yes      |
  /// | headers     | A Map which represents Headers sent to `authUrl`          | No       |
  /// | queryParams | A Map which represents URL Query Params sent to `authUrl` | No       |
  /// | credentials | [More information](https://pusher.com/docs/beams/reference/web/#credentials-1585702178) | No       |
  ///
  /// Throws an [Exception] in case of failure.
  @override
  Future<void> setUserId(String userId, BeamsAuthProvider provider,
      OnUserCallback callback) async {
    final callbackId = _uuid.v4();

    if (!kIsWeb) {
      _callbacks[callbackId] = callback;
    }

    await _pusherBeamsApi.setUserId(
        userId, provider, kIsWeb ? callback : callbackId);
  }

  /// This function register this device to *Pusher Beams* service with the given [instanceId].
  ///
  /// You must call this method as soon as possible in your application (Preferable inside the `main` function).
  ///
  /// ## Example Usage
  ///
  /// ```dart
  /// void main() async {
  ///     // Some code...
  ///     await PusherBeams.instance.start('YOUR INSTANCE ID');
  /// ]
  /// ```
  ///
  /// Throws an [Exception] in case of failure.
  @override
  Future<void> start(String instanceId) async {
    await _pusherBeamsApi.start(UuidValue(instanceId).toString());
  }

  /// Stops by deleting all the state, remotely and locally.
  /// You must call [PusherBeams.instance.start()] again if you want to receive notifications.
  ///
  /// ## Example Usage
  ///
  /// ```dart
  /// function someAsyncFunction() async {
  ///   await PusherBeams.instance.stop();
  /// }
  /// ```
  ///
  /// Throws an [Exception] in case of failure.
  @override
  Future<void> stop() async {
    await _pusherBeamsApi.stop();

    if (!kIsWeb) {
      _callbacks.clear();
    }
  }

  /// Registers a listener which calls back the [OnMessageReceivedInTheForeground] function when the app receives a push notification in the foreground.
  /// **This is not implemented on web.**
  ///
  /// Notification is a map containing the following keys:
  ///   * title
  ///   * body
  ///   * data
  ///
  /// ## Example Usage
  ///
  /// ```dart
  /// function someAsyncFunction() async {
  ///   await PusherBeams.instance.onMessageReceivedInTheForeground((notification) => {
  ///     print('Message received in the foreground: $notification')
  ///   });
  /// }
  /// ```
  ///
  /// Throws an [Exception] in case of failure.

  @override
  Future<void> onMessageReceivedInTheForeground(
      OnMessageReceivedInTheForeground callback) async {
    final callbackId = _uuid.v4();

    if (!kIsWeb) {
      _callbacks[callbackId] = callback;
    }

    await _pusherBeamsApi
        .onMessageReceivedInTheForeground(kIsWeb ? callback : callbackId);
  }

  /// Handler which receives callbacks from the native platforms.
  /// This currently supports [onInterestChanges] and [setUserId] callbacks
  /// but by default it just call the [Function] set.
  ///
  /// **You're not supposed to use this**
  @override
  void handleCallback(String callbackId, String callbackName, List args) {
    final callback = _callbacks[callbackId]!;

    switch (callbackName) {
      case "onInterestChanges":
        callback((args[0] as List<Object?>).cast<String>());
        return;
      case "setUserId":
        callback(args.isEmpty ? null : args[0] as String?);
        return;
      case "onMessageReceivedInTheForeground":
        callback((args[0] as Map<Object?, Object?>));
        return;
      default:
        callback();
        return;
    }
  }
}
