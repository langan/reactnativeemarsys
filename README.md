
# react-native-react-native-emarsys

## Getting started

`$ npm install react-native-react-native-emarsys --save`

### Mostly automatic installation

`$ react-native link react-native-react-native-emarsys`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-react-native-emarsys` and add `RNReactNativeEmarsys.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNReactNativeEmarsys.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNReactNativeEmarsysPackage;` to the imports at the top of the file
  - Add `new RNReactNativeEmarsysPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-react-native-emarsys'
  	project(':react-native-react-native-emarsys').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-react-native-emarsys/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-react-native-emarsys')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNReactNativeEmarsys.sln` in `node_modules/react-native-react-native-emarsys/windows/RNReactNativeEmarsys.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using React.Native.Emarsys.RNReactNativeEmarsys;` to the usings at the top of the file
  - Add `new RNReactNativeEmarsysPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNReactNativeEmarsys from 'react-native-react-native-emarsys';

// TODO: What to do with the module?
RNReactNativeEmarsys;
```
  