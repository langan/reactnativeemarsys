package com.missionlabs;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.emarsys.Emarsys;
import com.emarsys.config.EmarsysConfig;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;

import java.util.HashMap;
import java.util.Map;

public class RNReactNativeEmarsysModule extends ReactContextBaseJavaModule {

  private static final String TAG = "RNReactNativeEmarsys";

  public RNReactNativeEmarsysModule(ReactApplicationContext reactContext) {
    super(reactContext);
    Log.i(TAG, "RNReactNativeEmarsysModule");
  }

  @ReactMethod
  public void init(String applicationCode) {
    Log.i(TAG, "init: " + applicationCode);

    EmarsysConfig config = new EmarsysConfig.Builder()
      .application(getCurrentActivity().getApplication())
      .mobileEngageApplicationCode(applicationCode)
      .contactFieldId(12)
      .build();
    Emarsys.setup(config);
  }

  @ReactMethod
  public void setPushToken(String pushToken) {
    Log.i(TAG, "setPushToken");
    Emarsys.getPush().setPushToken(pushToken);
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
    Emarsys.trackCustomEvent(eventName, eventAttributesMap);
  }

  @ReactMethod
  public void trackMessageOpen(Intent intent) {
    Log.i(TAG, "trackMessageOpen");
    Emarsys.getPush().trackMessageOpen(intent);
  }

  @Override
  public String getName() {
    return TAG;
  }

//  @Override
//  public void handleEvent(String eventName, JSONObject payload) {
//    Log.i(TAG, "handleEvent " + eventName);
//  }
//
//  @Override
//  public void handleEvent(Context context, String eventName, @Nullable JSONObject payload) {
//    Log.i(TAG, "handleEvent " + eventName);
//  }
//
//  @Override
//  public void onError(String id, Exception error) {
//    Log.i(TAG, "onError " + error.getMessage(), error);
//  }
//
//  @Override
//  public void onStatusLog(String id, String message) {
//    Log.i(TAG, "onStatusLog " + id + " " + message);
//  }
}