
#import "RNReactNativeEmarsys.h"
#import <React/RCTLog.h>
#import "EMSConfig.h"
#import "Emarsys.h"
#import <React/RCTConvert.h>

@implementation RNReactNativeEmarsys

+ (void)init:(NSString *)applicationCode contactFieldId:(NSNumber *)contactFieldId {
  EMSConfig *config = [EMSConfig makeWithBuilder:^(EMSConfigBuilder *builder) {
    [builder setMobileEngageApplicationCode:applicationCode];
    [builder setContactFieldId:contactFieldId];
  }];
  [Emarsys setupWithConfig:config];
}

+ (void)setPushToken:(NSData *)deviceToken
{
  const char *data = [deviceToken bytes];
  NSMutableString *stringToken = [NSMutableString string];
  for (NSUInteger i = 0; i < [deviceToken length]; i++) {
    [stringToken appendFormat:@"%02.2hhX", data[i]];
  }
  NSLog(@"EMARSYS - Setting push token to : %@", stringToken);
  [Emarsys.push setPushToken:deviceToken completionBlock:^(NSError *error) {
    if (NULL != error) {
      RCTLogInfo(@"EMARSYS - error setting push token: %@", [error localizedDescription]);
    }
  }];
}

+ (void)trackMessageOpenWithUserInfo:(NSDictionary *)userInfo
{
  RCTLogInfo(@"EMARSYS - trackMessageOpenWithUserInfo");
  [Emarsys.push trackMessageOpenWithUserInfo:userInfo
    completionBlock:^(NSError *error) {
      if (NULL != error) {
        RCTLogInfo(@"EMARSYS - error tracking open message: %@", [error localizedDescription]);
      }
    }
  ];
}

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(requestPushAuth)
{
  RCTLogInfo(@"EMARSYS - Requesting Push Authorisation");
  UNUserNotificationCenter *center = [UNUserNotificationCenter currentNotificationCenter];
  [center requestAuthorizationWithOptions:(UNAuthorizationOptionSound | UNAuthorizationOptionAlert | UNAuthorizationOptionBadge) completionHandler:^(BOOL granted, NSError * _Nullable error){
      if(!error){
          [[UIApplication sharedApplication] registerForRemoteNotifications];
      }
  }];
}

RCT_EXPORT_METHOD(clearPushToken)
{
  [Emarsys.push clearPushTokenWithCompletionBlock:^(NSError *error) {
    if (NULL != error) {
      RCTLogInfo(@"EMARSYS - error clearing push token: %@", [error localizedDescription]);
    }
  }];
}

RCT_EXPORT_METHOD(trackCustomEvent:(NSString *)eventName eventAttributes:(NSDictionary *)eventAttributes)
{
  RCTLogInfo(@"EMARSYS - trackCustomEvent - %@", eventName);
  [Emarsys trackCustomEventWithName:eventName eventAttributes:eventAttributes completionBlock:^(NSError *error) {
    if (NULL != error) {
      RCTLogInfo(@"EMARSYS - error sending event: %@", [error localizedDescription]);
    }
  }];
}

@end

