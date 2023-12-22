# Get Game Data Mod (Minecraft Client Mod)
## Requirement
- Jdk >= 17
## Runing
- Windows
```
./gradlew.bat runClient
```
- Mac / Linux
```
./gradlew runClient
```
## Proxy
If you are not using **Clash**, you should modify the `gradle.properties`  
delete line 20 to line 23
```
# Done to increase the memory available to gradle.
org.gradle.jvmargs=-Xmx1G
org.gradle.parallel=true

# Fabric Properties
# check these on https://fabricmc.net/develop
minecraft_version=1.20.1
yarn_mappings=1.20.1+build.10
loader_version=0.15.3

# Mod Properties
mod_version=1.0.0
maven_group=com.novel
archives_base_name=outputdata

# Dependencies
fabric_version=0.91.0+1.20.1

# Proxy <- Delete Here
systemProp.http.proxyHost=127.0.0.1
systemProp.http.proxyPort=7890
systemProp.https.proxyHost=127.0.0.1
systemProp.https.proxyPort=7890
```
## Setting
You can change how long to get one game data  
change line 16 in `src/client/java/com/novel/OutputDataClient.java`
```java
package com.novel;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class OutputDataClient implements ClientModInitializer {
  private GetDataThread getData = new GetDataThread();
  private Thread getDataThread = new Thread();

  @Override
  public void onInitializeClient() {
    // This entrypoint is suitable for setting up client-specific logic, such as rendering.
    ClientTickEvents.START_WORLD_TICK.register((world) -> {
      if (!getDataThread.isAlive()) {
        getData.setWorld(world);
        getData.setPause(5); // pause second <- Change Here
        getDataThread = new Thread(getData, "Get Data Thread");
        getDataThread.start();
      }
    });
  }
}
```
