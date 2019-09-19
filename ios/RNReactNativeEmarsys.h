
#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#else
#import <React/RCTBridgeModule.h>
#endif

@interface RNReactNativeEmarsys : NSObject <RCTBridgeModule>
+(void)setPushToken:(NSData *)deviceToken;
+(void)trackMessageOpenWithUserInfo:(NSDictionary *)userInfo;
@end

