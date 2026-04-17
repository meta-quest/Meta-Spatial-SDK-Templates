# Meta Spatial SDK

This is a **Meta Quest VR/MR headset** app, not a standard Android phone/tablet app. It renders 3D content in the user's physical space with head tracking, hand tracking, and controller input.

## Architecture

The SDK uses an **Entity-Component-System (ECS)** architecture:

- **Entities** hold **Components** (data). **Systems** process them each frame via queries.
- **Custom components** are declared in XML schemas (`app/src/main/components/*.xml`) and auto-generate Kotlin classes at build time.
- **Scenes** can be authored visually in Meta Spatial Editor (`.glxf` files) or built entirely in code at runtime.
- **Panels** render 2D Android UI (Compose or XML layouts) onto surfaces positioned in 3D space.
- **Units are meters.** e.g. `Transform(Pose(Vector3(0f, 1.5f, -2f)))` = 1.5m up, 2m forward.

## Key Patterns

```kotlin
// Create entity with components
Entity.create(listOf(Transform(pose), Mesh(Uri.parse("mesh://box"))))

// Procedural meshes (no 3D model file needed)
// mesh://sphere, mesh://box, mesh://plane, mesh://quad, mesh://skybox

// Query entities in a System
val q = Query.where { has(Transform.id, MyComponent.id) }
for (entity in q.eval()) { /* per-frame logic */ }

// Register custom components and systems
componentManager.registerComponent<MyComp>(MyComp.Companion)
systemManager.registerSystem(MySystem())
```

## Docs

https://developers.meta.com/horizon/llmstxt/documentation/spatial-sdk/llms.txt/

## Meta Spatial Editor
Meta Spatial Editor is a spatial composition tool for Spatial SDK. Import, organize, and transform your assets into visual compositions and export them into Spatial SDK projects.

### mse-agent
mse-agent is the Meta Spatial Editor command-line tool for creating and modifying 3D scenes programmatically. Run `mse-agent readme` for the full command reference.

**mse-agent Location:**
 - Mac: `/Applications/Meta Spatial Editor.app/Contents/MacOS/mse-agent`
 - Windows: `C:\Program Files\Meta Spatial Editor\V*\Resources\mse-agent (use the latest version folder)`
 - Linux: `<package-root>/mse-agent` (mse-agent is located at the root of the downloaded package)

**Before You Start**
 - Check Meta Spatial Editor is installed — verify the mse-agent path exists
 - If Meta Spatial Editor is not installed, download it from:
   - Mac: https://developers.meta.com/horizon/downloads/package/meta-spatial-editor-for-mac/
   - Windows: https://developers.meta.com/horizon/downloads/package/meta-spatial-editor-for-windows/
   - Linux (headless CLI only): https://developers.meta.com/horizon/downloads/package/meta-spatial-editor-cli-for-linux/
 - Launch Meta Spatial Editor with the project scene (Run below commands from the project root directory):
   - **Important:** The editor is a long-running GUI process. You must launch it in the background so it does not block your current process. On Mac, open already returns immediately. On Windows, use start to spawn a separate process. On Linux, the Meta Spatial Editor runs in headless mode. Always wait a few seconds after launching before running mse-agent ping to confirm the editor is ready.
   - Mac: `open -a "/Applications/Meta Spatial Editor.app" "app/scenes/Main.metaspatial"`
   - Windows: `cmd /c start /B "" "C:\Program Files\Meta Spatial Editor\V*\MetaSpatialEditor.exe" "app/scenes/Main.metaspatial"`
   - Linux: `<package-root>/MetaSpatialEditorCLI serve -p app/scenes/Main.metaspatial &>/dev/null &`

 - Verify connection: `mse-agent ping`
 - Run `mse-agent readme` for the full command reference.

## Rules

- **Use Meta Spatial Editor (mse-agent) when entities are static and primarily define scene composition or layout.** Scenes are visually inspectable in Spatial Editor, which makes review and iteration faster than runtime-only entity creation.

- **Use Kotlin runtime entity creation when entities must be created dynamically.** Spatial Editor scenes are static — if entity creation depends on runtime data, variable counts, or entities that appear and disappear based on state, runtime code is the better fit since you cannot know what to author ahead of time.

- **Use a hybrid approach when a scene has both static and dynamic aspects.** Authoring the static entities in Spatial Editor keeps it visually inspectable and lets designers iterate on composition independently, while keeping runtime-driven content in Kotlin lets engineers focus on behavior and logic in code.
