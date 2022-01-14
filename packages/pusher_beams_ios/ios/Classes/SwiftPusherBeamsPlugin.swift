import Flutter
import UIKit
import PushNotifications

public class SwiftPusherBeamsPlugin: NSObject, FlutterPlugin, PusherBeamsApi, InterestsChangedDelegate {
    
    static var callbackHandler : CallbackHandlerApi? = nil
    
    var interestsDidChangeCallback : String? = nil
    var beamsClient : PushNotifications?
    var started : Bool = false
    var deviceToken : Data? = nil

    public static func register(with registrar: FlutterPluginRegistrar) {
        let messenger : FlutterBinaryMessenger = registrar.messenger()
        let instance : SwiftPusherBeamsPlugin = SwiftPusherBeamsPlugin()
      
        callbackHandler = CallbackHandlerApi(binaryMessenger: messenger)
        
        registrar.addApplicationDelegate(instance)
        
        PusherBeamsApiSetup(messenger, instance)
    }
    
    public func application(_ application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data) {
        if(started) {
            beamsClient?.registerDeviceToken(deviceToken)
        } else {
            self.deviceToken = deviceToken
        }
    }

    private func application(_ application: UIApplication, didReceiveRemoteNotification userInfo: [AnyHashable: Any], fetchCompletionHandler completionHandler: @escaping (UIBackgroundFetchResult) -> Void) {
        PushNotifications.shared.handleNotification(userInfo: userInfo)
    }

    private func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        PushNotifications.shared.registerForRemoteNotifications()
        return true
    }
    
    public func startInstanceId(_ instanceId: String, error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        beamsClient = PushNotifications(instanceId: instanceId)
        beamsClient?.delegate = self

        beamsClient?.start()

        if(deviceToken != nil) {
            beamsClient?.registerDeviceToken(deviceToken!)
            deviceToken = nil
        }
        started = true
    }

    public func addDeviceInterestInterest(_ interest: String, error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        try? beamsClient!.addDeviceInterest(interest: interest)
    }

    public func removeDeviceInterestInterest(_ interest: String, error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        try? beamsClient!.removeDeviceInterest(interest: interest)
    }

    public func getDeviceInterestsWithError(_ error: AutoreleasingUnsafeMutablePointer<FlutterError?>) -> [String]? {
        return beamsClient!.getDeviceInterests()
    }

    public func setDeviceInterestsInterests(_ interests: [String], error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        try? beamsClient!.setDeviceInterests(interests: interests)
    }

    public func clearDeviceInterestsWithError(_ error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        try? beamsClient!.clearDeviceInterests()
    }
    
    public func interestsSetOnDeviceDidChange(interests: [String]) {
        if (interestsDidChangeCallback != nil && (SwiftPusherBeamsPlugin.callbackHandler != nil)) {
            SwiftPusherBeamsPlugin.callbackHandler?.handleCallbackCallbackId(interestsDidChangeCallback!, callbackName: "onInterestChanges", args: [interests], completion: {_ in
                print("SwiftPusherBeamsPlugin: interests changed: \(interests)")
            })
        }
    }

    public func onInterestChangesCallbackId(_ callbackId: String, error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        interestsDidChangeCallback = callbackId
    }

    public func setUserIdUserId(_ userId: String, provider: BeamsAuthProvider, callbackId: String, error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        let tokenProvider = BeamsTokenProvider(authURL: provider.authUrl!) { () -> AuthData in
            let headers = provider.headers ?? [:]
            let queryParams: [String: String] = provider.queryParams ?? [:]
            return AuthData(headers: headers, queryParams: queryParams)
        }
        
        beamsClient!.setUserId(userId, tokenProvider: tokenProvider, completion: { error in
            guard error == nil else {
                SwiftPusherBeamsPlugin.callbackHandler?.handleCallbackCallbackId(callbackId, callbackName: "setUserId", args: [error.debugDescription], completion: {_ in
                  print("SwiftPusherBeamsPlugin: callback \(callbackId) handled with error")
                })
                return
            }

            SwiftPusherBeamsPlugin.callbackHandler?.handleCallbackCallbackId(callbackId, callbackName: "setUserId", args: [], completion: {_ in
                print("SwiftPusherBeamsPlugin: callback \(callbackId) handled")
            })
        })
    }

    public func clearAllStateWithError(_ error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        beamsClient!.clearAllState {
            print("SwiftPusherBeamsPlugin: state cleared")
        }
    }

    public func stopWithError(_ error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        beamsClient!.stop {
            print("SwiftPusherBeamsPlugin: stopped")
        }
        started = false
    }
}
