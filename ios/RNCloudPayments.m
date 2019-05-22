#import "RNCloudPayments.h"
#import <React/RCTBridgeModule.h>
#import <React/RCTEventDispatcher.h>
#import <React/RCTBridge.h>
#import <CloudPaymentsAPI/CPService.h>

@implementation RNCloudPayments
@synthesize bridge = _bridge;

RCT_EXPORT_MODULE();

- (dispatch_queue_t)methodQueue
{
  return dispatch_get_main_queue();
}

RCT_EXPORT_METHOD(checkCard:(NSDictionary *)config
                  findEventsWithResolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
  NSString *cardNumber = config[@"cardNumber"];
  if ([CPService isCardNumberValid:cardNumber]) {
    resolve(NULL);
  } else {
    reject(@"", @"", NULL);
  }
}

RCT_EXPORT_METHOD(createToken:(NSDictionary *)config
                  findEventsWithResolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
  CPService *apiService = [[CPService alloc] init];
  NSString *cardNumber = config[@"cardNumber"];
  NSString *expDate = config[@"expDate"];
  NSString *cvv = config[@"cvv"];
  NSString *apiPublicID = config[@"CP_PUBLIC_ID"];
  
  @try {
    NSString *str =  [apiService makeCardCryptogramPacket:cardNumber
                                               andExpDate:expDate
                                                   andCVV:cvv
                                         andStorePublicID: apiPublicID];
    resolve(str);
  } @catch (NSException *exception) {
    reject(@"", @"", NULL);
  }
}

@end
