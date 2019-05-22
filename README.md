
# react-native-cloud-payments

## Getting started

`$ npm install react-native-cloud-payments --save`

### Mostly automatic installation

`$ react-native link react-native-cloud-payments`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-cloud-payments` and add `RNCloudPayments.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNCloudPayments.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Add CloudPaymentsApi.framework
5. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNCloudPaymentsPackage;` to the imports at the top of the file
  - Add `new RNCloudPaymentsPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-cloud-payments'
  	project(':react-native-cloud-payments').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-cloud-payments/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      implementation project(':react-native-cloud-payments')
  	```


## Usage
```javascript
import RNCloudPayments from 'react-native-cloud-payments';

- Validation card number
RNCloudPayments.checkCard(cardNumber: string);

- Get payment token
RNCloudPayments.createToken(cardNumber, expDate, cvv, CP_PUBLIC_ID)
```
  