// DEBUG BOOLEAN
var debug = true;

function safeguard_createUser() {

      var email = document.getElementById('email').value;
      var password = document.getElementById('password').value;

			// validation of fields
      if (email.length < 1) {
        alert('Please enter an email address.');
        return;
      }
      if (password.length < 1) {
        alert('Please enter a password.');
        return;
      }

			if (debug) {
				console.log("user: " + email);
				console.log("password: " + password);
			}




      // Sign in with email and pass.
      // [START createwithemail]
      firebase.auth().createUserWithEmailAndPassword(email, password).catch(function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;

				// Debug error detection
				if (debug) console.log("CRITICAL ERROR: " + error.code);

        // [START_EXCLUDE]
        if (errorCode == 'auth/weak-password') {
          alert('The password is too weak.');
        } else if (errorCode == 'auth/email-already-in-use') {
					alert('That email is already in use');
				} else {
          console.log(errorMessage);
        }
        console.log(error);
        // [END_EXCLUDE]
      });
      // [END createwithemail]


}

function safeguard_signInUser() {

	var email = document.getElementById('email').value;
	var password = document.getElementById('password').value;

	if (debug) {
		console.log("user: " + email);
		console.log("password: " + password);
	}

	// Sign in with email and pass
	firebase.auth().signInWithEmailAndPassword(email, password).catch(function(error) {
  	// Handle Errors here.
  	var errorCode = error.code;
  	var errorMessage = error.message;
  // ...
	});

	if (debug) console.log("Sign In successful")

}
