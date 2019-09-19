
#import "RNReactNativeEmarsys.h"
#import <React/RCTLog.h>
#import "MobileEngage.h"
#import "MEConfig.h"
#import "MEConfigBuilder.h"

@implementation RNReactNativeEmarsys

- (void)setPushToken:(NSData *)deviceToken
{
  RCTLogInfo(@"setPushToken");
  [MobileEngage setPushToken:deviceToken];
  RCTLogInfo(@"setPushToken done");
}

- (void)trackMessageOpenWithUserInfo:(NSDictionary *)userInfo
{
  RCTLogInfo(@"trackMessageOpenWithUserInfo");
  NSString *eventId = [MobileEngage trackMessageOpenWithUserInfo:userInfo];
  RCTLogInfo(@"trackMessageOpenWithUserInfo result %@", userInfo);
}

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(init:(NSString *)applicationCode applicationPassword:(NSString *)applicationPassword)
{
  RCTLogInfo(@"init %@ at %@", applicationCode, applicationPassword);
  MEConfig *config = [MEConfig makeWithBuilder:^(MEConfigBuilder *builder) {
    [builder setCredentialsWithApplicationCode:applicationCode applicationPassword:applicationPassword];
  }];
  [MobileEngage setupWithConfig:config launchOptions:nil];
  [MobileEngage setStatusDelegate:self];
  RCTLogInfo(@"init finished");
}

RCT_EXPORT_METHOD(appLoginAnon)
{
  RCTLogInfo(@"appLoginAnon");
  NSString *eventId = [MobileEngage appLogin];
  RCTLogInfo(@"appLoginAnon result %@", eventId);
}

RCT_EXPORT_METHOD(appLoginUser:(NSNumber *)id applicationPassword:(NSString *)value)
{
  RCTLogInfo(@"appLoginUser %@ at %@", id, value);
  NSString *eventId = [MobileEngage appLoginWithContactFieldId:id
                                             contactFieldValue:value];
  RCTLogInfo(@"appLoginUser result %@", eventId);
}

RCT_EXPORT_METHOD(trackCustomEvent:(NSString *)eventName eventAttributes:(NSDictionary<NSString *, NSString *> *)eventAttributes)
{
  RCTLogInfo(@"trackCustomEvent %@ at %@", eventName, eventAttributes);
  NSString *eventId = [MobileEngage trackCustomEvent:eventName
                                     eventAttributes:eventAttributes];
  RCTLogInfo(@"trackCustomEvent result %@", eventId);
}

RCT_EXPORT_METHOD(appLogout)
{
  RCTLogInfo(@"appLogout");
  NSString *eventId = [MobileEngage appLogout];
  RCTLogInfo(@"appLogout result %@", eventId);
}

#pragma mark - MobileEngageStatusDelegate
- (void)mobileEngageErrorHappenedWithEventId:(NSString *)eventId
                                       error:(NSError *)error {
  RCTLogInfo(@"eventId %@ at %@", eventId, error.localizedDescription);
  NSLog(@"eventId: %@, error: %@", eventId, error.localizedDescription);
}

- (void)mobileEngageLogReceivedWithEventId:(NSString *)eventId
                                       log:(NSString *)log {
  RCTLogInfo(@"eventId %@ at %@", eventId, log);
  NSLog(@"eventId: %@, log: %@", eventId, log);
}

@end

