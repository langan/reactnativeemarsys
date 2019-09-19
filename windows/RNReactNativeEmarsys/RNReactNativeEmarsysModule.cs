using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace React.Native.Emarsys.RNReactNativeEmarsys
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNReactNativeEmarsysModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNReactNativeEmarsysModule"/>.
        /// </summary>
        internal RNReactNativeEmarsysModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNReactNativeEmarsys";
            }
        }
    }
}
