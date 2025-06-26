## Starter Template

This template is a starting point for creating a new Meta Spatial SDK project. It includes a skybox, an environment entity, and two panels.
The skybox is created at runtime with a call to Entity.create().
The environment entity and both panels are created by the glXFManager.inflateGLXF() function. This loads the composition created by Meta Spatial Editor.

## Meta Spatial Editor

This template requires you to install [Meta Spatial Editor](https://developers.meta.com/horizon/documentation/spatial-sdk/spatial-editor-overview).
Edit the scene by opening app/scenes/Main.metaspatial with Meta Spatial Editor.

## Spatial SDK Gradle Plugin

This template includes the Spatial SDK Gradle Plugin in its build files. This plugin is used for the [Spatial Editor integration](https://developers.meta.com/horizon/documentation/spatial-sdk/spatial-sdk-editor#use-the-spatial-sdk-gradle-plugin) and for build-related features like [custom shaders](https://developers.meta.com/horizon/documentation/spatial-sdk/spatial-sdk-custom-shaders).

Meta collects telemetry data from the Spatial SDK Gradle Plugin to help improve MPT Products. You can read the [Supplemental Meta Platforms Technologies Privacy Policy](https://www.meta.com/legal/privacy-policy/) to learn more.

## License

The Meta Spatial SDK Templates package is multi-licensed.

The majority of the project is licensed under the [Zero-Clause BSD License](https://github.com/meta-quest/Meta-Spatial-SDK-Templates/tree/main/LICENSE), as found in the Github where these files are hosted.

The [Meta Platform Technologies SDK license](https://developer.oculus.com/licenses/oculussdk/) applies to the Meta Spatial SDK and supporting material, and to the assets used in the Meta Spatial SDK Templates package. The [MPT SDK license](https://github.com/meta-quest/Meta-Spatial-SDK-Templates/tree/main/StarterTemplate/app/src/main/assets/LICENSE.md) can be found in the asset folder of each sample.

Specifically, all the supporting materials in each template's `app/src/main/assets` folders including 3D models, videos, sounds, and others, are licensed under the [MPT SDK license](https://developer.oculus.com/licenses/oculussdk/).
