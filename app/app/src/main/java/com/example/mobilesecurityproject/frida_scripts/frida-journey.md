# Frida steps I took in case I forget

- Download frida-server from github (refer to lab 6)
- `pip install frida-tools` (cmd)
- Show all processes on your emulator `frida-ps -Ua`
- `frida -U PID` to directly connect to an app by starting a session
- start the server `adb shell "/data/local/tmp/frida-server" &`
- inside the session: ```Java.enumerateMethods('*LoginScreen*!*');``` to find all methods of classed
- usage of enumerateMethods: 'ClassName!Method' (the * is a wildcard), (if the class is a kotlin file just say LoginScreen*kt*)
- find the name of the method and make a simple script to alert you when its executed
```js 
Java.perform(function() {
      // replace in class_name with actual name of class
      var class_name = Java.use("com.example.mobilesecurityproject.screens.LoginScreenKt");
      class_name.testFrida.implementation = function (arg1) {
          console.log("test frida was called");
          return this.testFrida(arg1)
      }
  });
```
- In the script replace *testFrida* with the actual name of the method
- run a session with a script `frida -U -p PID -l script.js`
- EXTRA: I suppose you need an admin cmd