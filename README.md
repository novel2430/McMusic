# Get Game Data Mod (Minecraft Client Mod)
## Result
### Terminal Output
![image](https://github.com/novel2430/MyImage/blob/main/MCMUSIC-001.png?raw=true)
### File Output
![image](https://github.com/novel2430/MyImage/blob/main/MCMUSIC-002.png?raw=true)
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
you can change how long (Second) between each output, and some other settings
### Step
1. if directory `run` not exist, create directory `run`
2. copy file `outputDataConfig.json` in `run`, and it will be `run/outputDataConfig.json`
3. modify `run/outputDataConfig.json` file
```json
// run/outputDataConfig.json 
// *Default
{
  "PauseSecond": 5.0, // how long between each output
  "SavePath": ".", // where to save ouput files
                   // e.g. /home/novel2430
                   // --> DO NOT put "/" at the path end! <--
                   // --> path "." means directory `run` <--
  "Debug": true // need or not to print thread log
}
```
