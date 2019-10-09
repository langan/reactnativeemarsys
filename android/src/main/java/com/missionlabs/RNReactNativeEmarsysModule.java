package com.missionlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.emarsys.mobileengage.EventHandler;
import com.emarsys.mobileengage.MobileEngageStatusListener;
import com.emarsys.mobileengage.config.MobileEngageConfig;
import com.emarsys.mobileengage.experimental.MobileEngageFeature;
import com.emarsys.mobileengage.notification.NotificationEventHandler;
import com.emarsys.mobileengage.MobileEngage;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RNReactNativeEmarsysModule extends ReactContextBaseJavaModule implements EventHandler, NotificationEventHandler, MobileEngageStatusListener {

  private static final String TAG = "RNReactNativeEmarsys";

  private final ReactApplicationContext reactContext;

  public RNReactNativeEmarsysModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    Log.i(TAG, "RNReactNativeEmarsysModule");
  }

  @ReactMethod
  public void init(String applicationCode, String applicationPassword) {
    Log.i(TAG, "init: " + applicationCode + "|" + applicationPassword);
    Log.i(TAG, "Core version: " + com.emarsys.core.BuildConfig.VERSION_NAME);
    Log.i(TAG, "MobileEngage version: " + com.emarsys.mobileengage.BuildConfig.VERSION_NAME);
    MobileEngageConfig config = new MobileEngageConfig.Builder()
      .application(getCurrentActivity().getApplication())
      .credentials(applicationCode, applicationPassword)
      .enableDefaultChannel("default", "here is a description")
      .enableExperimentalFeatures(
        MobileEngageFeature.IN_APP_MESSAGING,
        MobileEngageFeature.USER_CENTRIC_INBOX
      )
      .statusListener(this)
      .setDefaultInAppEventHandler(this)
      .setNotificationEventHandler(this)
      .build();
    MobileEngage.setup(config);
  }

  @ReactMethod
  public void setPushToken(String pushToken) {
    Log.i(TAG, "setPushToken");
    MobileEngage.setPushToken(pushToken);
  }

  @ReactMethod
  public void appLoginAnon() {
    Log.i(TAG, "appLoginAnon");
    String result = MobileEngage.appLogin();
    Log.i(TAG, "appLoginAnon result = " + result);
  }

  @ReactMethod
  public void appLoginUser(int contactFieldId, String contactFieldValue) {
    Log.i(TAG, "appLoginUser");
    String result = MobileEngage.appLogin(contactFieldId, contactFieldValue);
    Log.i(TAG, "appLoginUser result = " + result);
  }

  @ReactMethod
  public void trackCustomEvent(String eventName, @Nullable ReadableMap eventAttributes) {
    Log.i(TAG, "trackCustomEvent");
    Map<String, String> eventAttributesMap = new HashMap<String, String>();
    ReadableMapKeySetIterator iterator = eventAttributes.keySetIterator();
    while (iterator.hasNextKey()) {
      String key = iterator.nextKey();
      ReadableType type = eventAttributes.getType(key);
      switch (type) {
        case String:
          eventAttributesMap.put(key, eventAttributes.getString(key));
          break;
        default:
          throw new IllegalArgumentException("Could not convert object with key: " + key + ".");
      }
    }
    String result = MobileEngage.trackCustomEvent(eventName, eventAttributesMap);
    Log.i(TAG, "trackCustomEvent result = " + result);
  }

  @ReactMethod
  public void trackMessageOpen(Intent intent) {
    Log.i(TAG, "trackMessageOpen");
    String result = MobileEngage.trackMessageOpen(intent);
    Log.i(TAG, "trackMessageOpen result = " + result);
  }

  @ReactMethod
  public void trackDeepLink(Activity activity, Intent intent) {
    Log.i(TAG, "trackDeepLink");
    MobileEngage.trackDeepLink(activity, intent);
  }

  @ReactMethod
  public void appLogout() {
    Log.i(TAG, "appLogout");
    String result = MobileEngage.appLogout();
    Log.i(TAG, "appLogout result = " + result);
  }

  @Override
  public String getName() {
    return TAG;
  }

  @Override
  public void handleEvent(String eventName, JSONObject payload) {
    Log.i(TAG, "handleEvent " + eventName);
  }

  @Override
  public void handleEvent(Context context, String eventName, @Nullable JSONObject payload) {
    Log.i(TAG, "handleEvent " + eventName);
  }

  @Override
  public void onError(String id, Exception error) {
    Log.i(TAG, "onError " + error.getMessage(), error);
  }

  @Override
  public void onStatusLog(String id, String message) {
    Log.i(TAG, "onStatusLog " + id + " " + message);
  }
}