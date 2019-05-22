
import { NativeModules } from 'react-native';

export default class RNCloudPayments {
  static checkCard(cardNumber){
    return NativeModules.RNCloudPayments.checkCard({cardNumber})
  }

  static createToken(cardNumber, expDate, cvv, CP_PUBLIC_ID){
    return NativeModules.RNCloudPayments.createToken(
      {
        cardNumber,
        expDate,
        cvv,
        CP_PUBLIC_ID
      }
    )
  }
}
