# Pusher Beams for Flutter
![Pub Version](https://img.shields.io/pub/v/pusher_beams)
![GitHub](https://img.shields.io/github/license/pusher/flutter_pusher_beams)
[![likes](https://badges.bar/pusher_beams/likes)](https://pub.dev/packages/pusher_beams/score)
[![popularity](https://badges.bar/pusher_beams/popularity)](https://pub.dev/packages/pusher_beams/score)
[![pub points](https://badges.bar/pusher_beams/pub%20points)](https://pub.dev/packages/pusher_beams/score)

Official Flutter Plugin for [Pusher Beams](https://pusher.com/beams) using [Pigeon](https://pub.dev/packages/pigeon) for platform plugin interface and [Federated Plugin Architecture](https://docs.google.com/document/d/1LD7QjmzJZLCopUrFAAE98wOUQpjmguyGTN2wd_89Srs/edit?usp=sharing).

- [Dart](https://dart.dev/) >= 2.12.x
- [Flutter](https://flutter.dev/) >= 2.x.x

## Table of Contents
- [Architecture](#architecture)
- [Flutter Support](#flutter-support)
- [Platform Support](#platform-support)
    - [Web Support](#web-support)
    - [Mobile Support](#mobile-support)
- [Example](#example)
- [Prerequisites](#prerequisites)
    - [Android Additional](#android-additional)
    - [iOS Additional](#ios-additional)
- [Installation](#installation)
- [Initialization](#initialization)
- [API Reference](#api-reference)
- [Contributing](#contributing)
    - [Developing Environment](#developing-environment)
    - [Running the tests](#running-the-tests)
- [License](#license)


## Architecture
This plugin was developed based on the [Federated Plugin Architecture](https://docs.google.com/document/d/1LD7QjmzJZLCopUrFAAE98wOUQpjmguyGTN2wd_89Srs/edit?usp=sharing), following packages are included in the plugin:

- [pusher_beams](https://github.com/pusher/flutter_pusher_beams/tree/master/packages/pusher_beams): This is intended to be the main or _app-facing package_. **You must install this package in order to use it**.
- [pusher_beams_platform_interface](https://github.com/pusher/flutter_pusher_beams/tree/master/packages/pusher_beams_platform_interface): This is the _platform package interface_ that glues [pusher_beams](https://github.com/pusher/flutter_pusher_beams/tree/master/packages/pusher_beams) and platform packages.
- [pusher_beams_android](https://github.com/pusher/flutter_pusher_beams/tree/master/packages/pusher_beams_android): This is a _platform package_ which implements Android code.
- [pusher_beams_ios](https://github.com/pusher/flutter_pusher_beams/tree/master/packages/pusher_beams_ios): This is a _platform package_ which implements the iOS code
- [pusher_beams_web](https://github.com/pusher/flutter_pusher_beams/tree/master/packages/pusher_beams_web): This is a _platform package_ which implements the Web code.

## Flutter Support
This is the comparison table of functions implemented within this plugin according to the native libraries. ([Android](https://pusher.com/docs/beams/reference/android/), [iOS](https://pusher.com/docs/beams/reference/ios/), [Web](https://pusher.com/docs/beams/reference/web/))

|                      | **iOS** | **Android** | **Web** |
|----------------------|-----|---------|-----|
| [addDeviceInterest](https://pub.dev/documentation/pusher_beams/latest/pusher_beams/PusherBeams/addDeviceInterest.html)    | ✅   | ✅       | ✅   |
| [clearAllState](https://pub.dev/documentation/pusher_beams/latest/pusher_beams/PusherBeams/clearAllState.html)        | ✅   | ✅       | ✅   |
| [clearDeviceInterests](https://pub.dev/documentation/pusher_beams/latest/pusher_beams/PusherBeams/clearDeviceInterests.html) | ✅   | ✅       | ✅   |
| [getDeviceInterests](https://pub.dev/documentation/pusher_beams/latest/pusher_beams/PusherBeams/getDeviceInterests.html)   | ✅   | ✅       | ✅   |
| [onInterestChanges](https://pub.dev/documentation/pusher_beams/latest/pusher_beams/PusherBeams/onInterestChanges.html)    | ✅   | ✅       | ⬜️   |
| [removeDeviceInterest](https://pub.dev/documentation/pusher_beams/latest/pusher_beams/PusherBeams/removeDeviceInterest.html) | ✅   | ✅       | ✅   |
| [setDeviceInterests](https://pub.dev/documentation/pusher_beams/latest/pusher_beams/PusherBeams/setDeviceInterests.html)   | ✅   | ✅       | ✅   |
| [setUserId](https://pub.dev/documentation/pusher_beams/latest/pusher_beams/PusherBeams/setUserId.html)            | ✅   | ✅       | ✅   |
| [start](https://pub.dev/documentation/pusher_beams/latest/pusher_beams/PusherBeams/start.html)                | ✅   | ✅       | ✅   |
| [stop](https://pub.dev/documentation/pusher_beams/latest/pusher_beams/PusherBeams/stop.html)                 | ✅   | ✅       | ✅   |

## Platform Support
This plugin supports Web, Android and iOS platforms.

### Web Support
- Chrome (mobile & desktop)
- Safari (mobile & desktop)
- Edge (mobile & desktop)
- Firefox (mobile & desktop)

[See Web FAQ](https://docs.flutter.dev/development/platform-integration/web#which-web-browsers-are-supported-by-flutter)

### Mobile Support

- _iOS 10_ and above
- _Android 4.4_ and above (>= SDK Version 19)

## Example
A fully example using this plugin can be found in this repository, implementing a basic use of most of the functionality included in this plugin.

[See Example](https://github.com/pusher/flutter_pusher_beams/tree/master/packages/pusher_beams/example)

## Prerequisites
In order to work with this _Flutter_ plugin you must have a [Pusher Beams Account](https://pusher.com/beams) and already got an `instanceId` within your dashboard, you can [Sign Up here](https://dashboard.pusher.com/accounts/sign_up?product=beams).

### Android Additional
- [Firebase Account](https://firebase.google.com/) and a `google-services.json` ([follow this guide](https://pusher.com/docs/beams/getting-started/android/sdk-integration/)). **Do not initialize anything**, this plugin just requires a `google-services.json` within your `android/app` folder.
- [Enable Multidex](https://firebase.flutter.dev/docs/manual-installation/android#enabling-multidex) (If your `minSdkVersion` is lower than 21)
- For Android 12 support,`compileSdkVersion` must be set to `31` (and if you use kotlin, use the `1.5.30` version)

### iOS Additional
- [Enable capabilities within your iOS app.](https://pusher.com/docs/beams/getting-started/ios/sdk-integration/#enable-capabilities)
- [Configure APNs](https://pusher.com/docs/beams/getting-started/ios/configure-apns/) in order to work with iOS platform.

## Installation
To install this plugin within you Flutter application, you need to add the package into your `pubspec.yaml`.
```yaml
    dependencies:
      pusher_beams: ^1.0.1
```
or with `flutter pub`
```
flutter pub add pusher_beams
```

There are some additional steps for some platforms.

### Android

- Add this line inside your project-level `build.gradle` which is located at your `android` folder
  ```
  buildscript {
      // Your config...
      
      repositories {
          // Your repositories...
      }
  
      dependencies {
          // Your dependencies...
  
          // Add this line
          classpath 'com.google.gms:google-services:4.2.0'
      }
  }
  ```

- Now, on your app-level `build.gradle` you must include `com.google.gms.google-services` ( `google-services.json` must be already included within your app as it's a [prerequisite](#prerequisites)). Just add this line at the end of your app-level `build.gradle`.
  ```
  apply plugin: 'com.google.gms.google-services'
  ```

### Web

- Create a file called `service-worker.js` inside your `web` folder.
- That file must include the following line:
  ```js
  importScripts("https://js.pusher.com/beams/service-worker.js");
  ```
- Now you must add this `<script>` tag inside and **at the beginning** of your `<body>` tag within the `web/index.html` file:
  ```html
  <script src="https://js.pusher.com/beams/1.0/push-notifications-cdn.js"></script>
  ```

## Initialization

If you already have done the [prerequisites](#prerequisites), now you can initialize _Pusher Beams_ using the `start` method as soon as possible (Preferable inside the `main` function).
```dart
void main() async {
  // Some initial code
  
  await PusherBeams.instance.start('YOUR INSTANCE ID');
  
  runApp(MyApp());
}
```

Ensure the bindings are initialized, you can do this by adding the following line inside your `main` function **before you start this plugin**.
```dart
void main() async {
  // Some initial code

  WidgetsFlutterBinding.ensureInitialized(); // Add this line
  await PusherBeams.instance.start('YOUR INSTANCE ID');

  runApp(MyApp());
}
```

Overall, that's all! ✨ Now you can use the methods described in the [API Reference.](#api-reference)

## API Reference

If you want to see the API reference in-depth, you may want to see the [Official API Reference](https://pub.dev/documentation/pusher_beams/latest/) from [pub.dev](https://pub.dev/).

## Contributing
In order to contribute you must first read [how to develop flutter plugins](https://docs.flutter.dev/development/packages-and-plugins/developing-packages#plugin), this is the basic knowledge to start.

This repository is following [git flow branching model](https://nvie.com/posts/a-successful-git-branching-model/), so in order to contribute, once you fork this project, you must create a _fix/_ or _feature/_ branch, which will be pull requested from you once it's ready.


Commits follows the [conventional commits](https://www.conventionalcommits.org/en/v1.0.0/) standard, which scopes are the follow:

- **pusher_beams**: For [pusher_beams](https://github.com/pusher/flutter_pusher_beams/tree/master/packages/pusher_beams) code commits.
- **web**: For [pusher_beams_web](https://github.com/pusher/flutter_pusher_beams/tree/master/packages/pusher_beams_web) code commits.
- **ios**: For [pusher_beams_ios](https://github.com/pusher/flutter_pusher_beams/tree/master/packages/pusher_ios) code commits.
- **android**: For [pusher_beams_android](https://github.com/pusher/flutter_pusher_beams/tree/master/packages/pusher_beams_android) code commits.
- **interface**: For [pusher_beams_platform_interface](https://github.com/pusher/flutter_pusher_beams/tree/master/packages/pusher_beams_platform_interface) code commits.

So, in order to commit something you must use a commit message like below:

```
feat(android): i did something to android code :0
```

### Developing Environment
- [Flutter](https://flutter.dev/) >= 2.x.x (running `flutter doctor` will check if everything is good to start)
- To contribute on [packages/pusher_beams_ios](packages/pusher_beams_ios) you must be in MacOS and Xcode must be installed

### Running The Tests
There's two major tests, our **app-facing package** test which is run as [Integration Test](https://docs.flutter.dev/testing/integration-tests) and our **platform-interface package** test run as [Unit Test](https://api.flutter.dev/flutter/flutter_test/flutter_test-library.html)

#### Integration Tests

For integration tests, we use the example app provided in `packages/pusher_beams/example`. In order to run the integration tests you must complete the [prerequisites](#prerequisites) in the example app which is...
- You already registered into [Pusher Beams](https://pusher.com/beams) platform.
- You already have a [Firebase Account](https://firebase.google.com/) linked with [Pusher Beams](https://pusher.com/beams)
- The [packages/pusher_beams/example](packages/pusher_beams/example) app is already set up and firebase is initialized.

If you already double-check the list above, then you must replace the constant `instanceId` located on `packages/pusher_beams/example/integration_test/pusher_beams_test.dart` with a real one from [Pusher Beams](https://pusher.com/beams).

```dart
// Code...

const instanceId = 'your-instance-id'; // Replace this with a real instanceId

// More Code...
```

So that's all! you can now run the integration test with the following command on the **example app path** (`packages/pusher_beams/example`):

```
flutter test integration_test
```

And for web (Do not forget to [read this](https://docs.flutter.dev/cookbook/testing/integration/introduction#5b-web)):

```
flutter drive \
  --driver=test_driver/integration_test.dart \
  --target=integration_test/pusher_beams_test.dart \
  -d web-server
```

#### Unit Tests
In order to run unit tests for [packages/pusher_beams_platform_interface](packages/pusher_beams_platform_interface) you must be in the directory and run the following:
```
flutter test
```

### Pigeons
As this plugin platform interface is generated by [Pigeon](https://pub.dev/packages/pigeon) and if you modified the file [messages.dart](packages/pusher_beams_platform_interface/pigeons/messages.dart) from [packages/pusher_beams_platform_interface](packages/pusher_beams_platform_interface) package, in order to generate a new `MethodChannel` interface you must run the following command on path `packages/pusher_beams_platform_interface`:
```
make
```
After this command you must go to [packages/pusher_beams_platform_interface/lib/method_channel_pusher_beams.dart](packages/pusher_beams_platform_interface/lib/method_channel_pusher_beams.dart) and extends the class `PusherBeamsApi` with `PusherBeamsPlatform` like below:
```dart
class PusherBeamsApi extends PusherBeamsPlatform {
  // Pigeon Generated Class Code
}
```
This will require you to change the methods `onInterestChanges` and `setUserId` parameter `arg_callbackId` to type `dynamic` in order to accomplish `PusherBeamsPlatform` definition like below:
```dart
  // Class code...
  Future<void> onInterestChanges(dynamic arg_callbackId) async {
    // onInterestChanges generated function code...
  }

  Future<void> setUserId(String arg_userId, BeamsAuthProvider arg_provider, dynamic arg_callbackId) async {
    // setUserId generated function code...
  }
  // More class code...
```

## License

Copyright (c) 2015 Pusher Ltd. See [LICENSE](LICENSE) for details.
