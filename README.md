# Get Game Data Mod (Minecraft Client Mod)
## Result
### Terminal Output
![image](https://github.com/novel2430/MyImage/blob/main/MCMUSIC-001.png?raw=true)
### File Output
```json
{
	"Index":3,
	"Biome":{
		"Plains":100.0
	},
	"Time":{
		"Midnight":100.0
	},
	"Climate":{
		"Clear":100.0
	},
	"Temperature":{
		"Not Cold":100.0
	},
	"Health":{
		"Full":100.0
	},
	"Hunger":{
		"Full":100.0
	},
	"Status":{
		"Not Touching Water":100.0,
		"Not On Fire":100.0
	},
	"Motion":{
		"Not Sprinting":100.0,
		"Not Sneaking":100.0
	},
	"Placing":{
		"On Ground":100.0,
		"Not In Lava":100.0,
		"Not On Rail":100.0
	},
	"Monster":{
		"skelton":2.0793548341035764,
		"zombie":3.556985132528389,
		"sum":7.780587773591776,
		"spider":0.3294200931966782,
		"creeper":1.8148277137631317
	},
	"Attacked":{
		"Not Attacked":100.0
	}
}
```
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
### Key Meaning
| Key | Meaning |
|---|---|
| PauseSecond | how long between each output |
| SavePath | where to save ouput files (DO NOT put "/" at the path end! Path "." means directory `run`) |
| Debug | need or not to print thread log |
| Calculate | need or not to do calculation |
| URL | Data Transfer Server URL |
| PlayerName | Player Name |
| DetectSize | Size of detecting monster |
### File Content
```json
{
  "PauseSecond": 10.0,
  "SavePath": ".", 
  "Debug": true,
  "Calculate": true,
  "URL": "http://127.0.0.1:44349",
  "PlayerName": "novel2430",
  "DetectSize": 20
}
```
