
#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#else
#import <React/RCTBridgeModule.h>
#endif

@interface RNReactNativeEmarsys : NSObject <RCTBridgeModule>
+(void)init:(NSString *)applicationCode contactFieldId:(NSNumber *)contactFieldId;
+(void)setPushToken:(NSData *)deviceToken;
+(void)trackMessageOpenWithUserInfo:(NSDictionary *)userInfo;
@end

