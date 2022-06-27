// Autogenerated from Pigeon (v1.0.14), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.pusher.pusher_beams;

import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/** Generated class from Pigeon. */
@SuppressWarnings({"unused", "unchecked", "CodeBlock2Expr", "RedundantSuppression"})
public class Messages {

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class BeamsAuthProvider {
    private String authUrl;
    public String getAuthUrl() { return authUrl; }
    public void setAuthUrl(String setterArg) { this.authUrl = setterArg; }

    private Map<String, String> headers;
    public Map<String, String> getHeaders() { return headers; }
    public void setHeaders(Map<String, String> setterArg) { this.headers = setterArg; }

    private Map<String, String> queryParams;
    public Map<String, String> getQueryParams() { return queryParams; }
    public void setQueryParams(Map<String, String> setterArg) { this.queryParams = setterArg; }

    private String credentials;
    public String getCredentials() { return credentials; }
    public void setCredentials(String setterArg) { this.credentials = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("authUrl", authUrl);
      toMapResult.put("headers", headers);
      toMapResult.put("queryParams", queryParams);
      toMapResult.put("credentials", credentials);
      return toMapResult;
    }
    static BeamsAuthProvider fromMap(Map<String, Object> map) {
      BeamsAuthProvider fromMapResult = new BeamsAuthProvider();
      Object authUrl = map.get("authUrl");
      fromMapResult.authUrl = (String)authUrl;
      Object headers = map.get("headers");
      fromMapResult.headers = (Map<String, String>)headers;
      Object queryParams = map.get("queryParams");
      fromMapResult.queryParams = (Map<String, String>)queryParams;
      Object credentials = map.get("credentials");
      fromMapResult.credentials = (String)credentials;
      return fromMapResult;
    }
  }

  public interface Result<T> {
    void success(T result);
    void error(Throwable error);
  }
  private static class PusherBeamsApiCodec extends StandardMessageCodec {
    public static final PusherBeamsApiCodec INSTANCE = new PusherBeamsApiCodec();
    private PusherBeamsApiCodec() {}
    @Override
    protected Object readValueOfType(byte type, ByteBuffer buffer) {
      switch (type) {
        case (byte)128:         
          return BeamsAuthProvider.fromMap((Map<String, Object>) readValue(buffer));
        
        default:        
          return super.readValueOfType(type, buffer);
        
      }
    }
    @Override
    protected void writeValue(ByteArrayOutputStream stream, Object value)     {
      if (value instanceof BeamsAuthProvider) {
        stream.write(128);
        writeValue(stream, ((BeamsAuthProvider) value).toMap());
      } else {
        super.writeValue(stream, value);
      }
    }
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter.*/
  public interface PusherBeamsApi {
    void start(String instanceId);
    void getInitialMessage(Result<Map<String, Object>> result);
    void addDeviceInterest(String interest);
    void removeDeviceInterest(String interest);
    List<String> getDeviceInterests();
    void setDeviceInterests(List<String> interests);
    void clearDeviceInterests();
    void onInterestChanges(String callbackId);
    void setUserId(String userId, BeamsAuthProvider provider, String callbackId);
    void clearAllState();
    void onMessageReceivedInTheForeground(String callbackId);
    void stop();

    /** The codec used by PusherBeamsApi. */
    static MessageCodec<Object> getCodec() {
      return PusherBeamsApiCodec.INSTANCE;
    }

    /** Sets up an instance of `PusherBeamsApi` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, PusherBeamsApi api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.PusherBeamsApi.start", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              String instanceIdArg = (String)args.get(0);
              if (instanceIdArg == null) {
                throw new NullPointerException("instanceIdArg unexpectedly null.");
              }
              api.start(instanceIdArg);
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.PusherBeamsApi.getInitialMessage", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              Result<Map<String, Object>> resultCallback = new Result<Map<String, Object>>() {
                public void success(Map<String, Object> result) {
                  wrapped.put("result", result);
                  reply.reply(wrapped);
                }
                public void error(Throwable error) {
                  wrapped.put("error", wrapError(error));
                  reply.reply(wrapped);
                }
              };

              api.getInitialMessage(resultCallback);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
              reply.reply(wrapped);
            }
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.PusherBeamsApi.addDeviceInterest", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              String interestArg = (String)args.get(0);
              if (interestArg == null) {
                throw new NullPointerException("interestArg unexpectedly null.");
              }
              api.addDeviceInterest(interestArg);
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.PusherBeamsApi.removeDeviceInterest", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              String interestArg = (String)args.get(0);
              if (interestArg == null) {
                throw new NullPointerException("interestArg unexpectedly null.");
              }
              api.removeDeviceInterest(interestArg);
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.PusherBeamsApi.getDeviceInterests", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              List<String> output = api.getDeviceInterests();
              wrapped.put("result", output);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.PusherBeamsApi.setDeviceInterests", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              List<String> interestsArg = (List<String>)args.get(0);
              if (interestsArg == null) {
                throw new NullPointerException("interestsArg unexpectedly null.");
              }
              api.setDeviceInterests(interestsArg);
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.PusherBeamsApi.clearDeviceInterests", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              api.clearDeviceInterests();
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.PusherBeamsApi.onInterestChanges", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              String callbackIdArg = (String)args.get(0);
              if (callbackIdArg == null) {
                throw new NullPointerException("callbackIdArg unexpectedly null.");
              }
              api.onInterestChanges(callbackIdArg);
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.PusherBeamsApi.setUserId", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              String userIdArg = (String)args.get(0);
              if (userIdArg == null) {
                throw new NullPointerException("userIdArg unexpectedly null.");
              }
              BeamsAuthProvider providerArg = (BeamsAuthProvider)args.get(1);
              if (providerArg == null) {
                throw new NullPointerException("providerArg unexpectedly null.");
              }
              String callbackIdArg = (String)args.get(2);
              if (callbackIdArg == null) {
                throw new NullPointerException("callbackIdArg unexpectedly null.");
              }
              api.setUserId(userIdArg, providerArg, callbackIdArg);
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.PusherBeamsApi.clearAllState", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              api.clearAllState();
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.PusherBeamsApi.onMessageReceivedInTheForeground", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              String callbackIdArg = (String)args.get(0);
              if (callbackIdArg == null) {
                throw new NullPointerException("callbackIdArg unexpectedly null.");
              }
              api.onMessageReceivedInTheForeground(callbackIdArg);
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.PusherBeamsApi.stop", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              api.stop();
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
  private static class CallbackHandlerApiCodec extends StandardMessageCodec {
    public static final CallbackHandlerApiCodec INSTANCE = new CallbackHandlerApiCodec();
    private CallbackHandlerApiCodec() {}
    @Override
    protected Object readValueOfType(byte type, ByteBuffer buffer) {
      switch (type) {
        case (byte)128:         
          return BeamsAuthProvider.fromMap((Map<String, Object>) readValue(buffer));
        
        default:        
          return super.readValueOfType(type, buffer);
        
      }
    }
    @Override
    protected void writeValue(ByteArrayOutputStream stream, Object value)     {
      if (value instanceof BeamsAuthProvider) {
        stream.write(128);
        writeValue(stream, ((BeamsAuthProvider) value).toMap());
      } else 
{
        super.writeValue(stream, value);
      }
    }
  }

  /** Generated class from Pigeon that represents Flutter messages that can be called from Java.*/
  public static class CallbackHandlerApi {
    private final BinaryMessenger binaryMessenger;
    public CallbackHandlerApi(BinaryMessenger argBinaryMessenger){
      this.binaryMessenger = argBinaryMessenger;
    }
    public interface Reply<T> {
      void reply(T reply);
    }
    static MessageCodec<Object> getCodec() {
      return CallbackHandlerApiCodec.INSTANCE;
    }

    public void handleCallback(String callbackIdArg, String callbackNameArg, List<Object> argsArg, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.CallbackHandlerApi.handleCallback", getCodec());
      channel.send(new ArrayList<Object>(Arrays.asList(callbackIdArg, callbackNameArg, argsArg)), channelReply -> {
        callback.reply(null);
      });
    }
  }
  private static Map<String, Object> wrapError(Throwable exception) {
    Map<String, Object> errorMap = new HashMap<>();
    errorMap.put("message", exception.toString());
    errorMap.put("code", exception.getClass().getSimpleName());
    errorMap.put("details", null);
    return errorMap;
  }
}
