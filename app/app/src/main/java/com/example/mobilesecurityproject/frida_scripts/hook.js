Java.perform(function() {
      var class_name = Java.use("com.example.mobilesecurityproject.screens.LoginScreenKt");
      class_name.validUserCredentials.implementation = function (username, password, userRepository) {
            console.log("validUserCredentials was called");
            console.log("Username: ", username);
            console.log("Password: ", password);
            
            return Java.use('java.lang.Boolean').valueOf(true);
      }
  });