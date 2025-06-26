# Meta Spatial SDK Templates

This repository is a collection of code samples that demonstrate the capabilities of Meta Spatial SDK. [Meta Spatial SDK](https://developers.meta.com/horizon/documentation/spatial-sdk/spatial-sdk-overview) is a new way to build immersive apps for Meta Horizon OS. Meta Spatial SDK lets you combine the rich ecosystem of Android development and the unique capabilities of Meta Quest via accessible APIs.

The samples in this repository are used for New Project Templates by the [Meta Horizon](https://plugins.jetbrains.com/plugin/26861-meta-horizon) Plugin for Android Studio. Each sample project includes source code, build scripts, and documentation to help developers understand how to use the SDK to build their own spatially-aware applications.

## Requirements

To try out these sample apps, you will need:

- A Meta Quest device (Quest 2/3/3S/Pro)
  - [Meta Quest build v69.0 or newer](https://www.meta.com/help/quest/articles/whats-new/release-notes/)
- Mac or Windows
  - Android Studio Hedgehog or newer
  - [Meta Spatial Editor](https://developers.meta.com/horizon/downloads/spatial-sdk/)

## Getting Started

First, ensure that all of the [requirements](#requirements) are met.

Then, to build and run a sample:

1. Add the [Meta Horizon Plugin for Android Studio](https://plugins.jetbrains.com/plugin/26861-meta-horizon) to your Android Studio IDE.
2. In the main toolbar, press "File" -> "New Spatial SDK Project" -> Fill out your project's information -> "Create"
3. Plug in your Quest device to your computer
4. Click the "Run" button in the Android Studio toolbar, the app will now be running on your headset

**Notes**:

- The templates require you to install [Meta Spatial Editor](https://developers.meta.com/horizon/documentation/spatial-sdk/spatial-editor-overview).

## Templates

We currently have 1 template app:

- [StarterTemplate](/StarterTemplate) is a starter project that shows a basic 3D immersive scene with panels that communicate with each other.

## Documentation

The documentation for Meta Spatial SDK can be found [here](https://developers.meta.com/horizon/develop/spatial-sdk).

## Release Notes

Find our official release notes [here](https://developers.meta.com/horizon/documentation/spatial-sdk/release-notes).

## Spatial SDK Gradle Plugin

The samples all include the Spatial SDK Gradle Plugin in their build files. This plugin is used for the [Spatial Editor integration](https://developers.meta.com/horizon/documentation/spatial-sdk/spatial-sdk-editor#use-the-spatial-sdk-gradle-plugin) and for build-related features like [custom shaders](https://developers.meta.com/horizon/documentation/spatial-sdk/spatial-sdk-custom-shaders).

Meta collects telemetry data from the Spatial SDK Gradle Plugin to help improve MPT Products. You can read the [Supplemental Meta Platforms Technologies Privacy Policy](https://www.meta.com/legal/privacy-policy/) to learn more.

## License

The Meta Spatial SDK Templates package is multi-licensed.

The majority of the project is licensed under the [Zero-Clause BSD License](https://opensource.org/license/0bsd), as found in the LICENSE file.

The [Meta Platform Technologies SDK license](https://developer.oculus.com/licenses/oculussdk/) applies to the Meta Spatial SDK and supporting material, and to the assets used in the Meta Spatial SDK Templates package. The [MPT SDK license](https://github.com/meta-quest/Meta-Spatial-SDK-Templates/tree/main/StarterTemplate/app/src/main/assets/LICENSE.md) can be found in the asset folder of each sample.

Specifically, all the supporting materials in each template's `app/src/main/assets` folders including 3D models, videos, sounds, and others, are licensed under the [MPT SDK license](https://developer.oculus.com/licenses/oculussdk/).
