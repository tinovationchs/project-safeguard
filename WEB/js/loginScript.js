// DEBUG BOOLEAN
var debug = false;

function safeguard_createUser() {
    // Get a reference to the database service
    var database = firebase.database();

    // Get data from document fields
    var username = document.getElementById('user').value;
    var password = document.getElementById('password').value;

    if (debug) alert(username + " | " + password);

    //timeFormatter();

    // TODO check if user exists
    var topUserPostsRef = firebase.database().ref(username);
    alert(topUserPostsRef);

    // attempts to read data at username, to check if data exists.
    return topUserPostsRef.once('value').then(function(snapshot) {
        if (snapshot.val() == null) {
          // data doesn't exist! Create user!
          if (debug) console.log("User " + username + " does not exist. Creating now...");
          writeUserData(username, password);
          if (debug) console.log("Successfully created!");
        }
        else {
            // Data exists. Do not create user.
            var stream = snapshot.val().stream;
            console.log("User " + username + " already exists. User not created.");
            alert("Sorry, Username already in use. Sign in or choose another name.");
        }
    });




}

function safeguard_signIn() {

  // Get data from document fields
  var username = document.getElementById('user').value;
  var password = document.getElementById('password').value;

  var userRef = firebase.database().ref(username);
  alert(userRef);

  // attempts to read data at username, to check if data exists.
  return userRef.once('value').then(function(snapshot) {
      if (snapshot.val() == null) {
        // data doesn't exist! sign in failed.
        if (debug) console.log("User " + username + " does not exist.");
        alert("username doesn't exist! create an account first!");
      }
      else {
          // Data exists. Sign in and populate
          var onlinePassword = snapshot.val().password;
          if (password == onlinePassword) {
            if (debug) alert("Sign in success.");
            if (debug) console.log("User " + username + " successfully authenticated with password " + password + " matching the online archived password of " + onlinePassword);
            populateData(username);
          } else {
            alert("password incorrect! Try again!");
          }


      }
  });
}

// TODO actually populate data using data from stream
function populateData(userId) {
  if (debug) alert('POPULATING DATA');
}

function writeUserData(userId, pass) {
    // setting default data structure with username and password
    firebase.database().ref(userId).set({
        password: pass,
    });

    // populating stream with initial timestamp.
    firebase.database().ref(userId + "/stream/" + timeFormatter()).set({
        event: "USER " + userId + " CREATED",
    });
}

function timeFormatter() {
    var output;
    var d = new Date(),
        h = (d.getHours() < 10 ? '0' : '') + d.getHours(),
        m = (d.getMinutes() < 10 ? '0' : '') + d.getMinutes();
    output = h + ':' + m;

    var pre = d.getHours() <= 12 ? 'am' : 'pm';

    return (pre + h + m);
}

/*
function safeguard_signInUserGoogle() {
    alert('loodl');

    var provider = new firebase.auth.GoogleAuthProvider();

    firebase.auth().signInWithPopup(provider).then(function(result) {
        // This gives you a Google Access Token. You can use it to access the Google API.
        var token = result.credential.accessToken;
        // The signed-in user info.
        var user = result.user;
        // ...
        alert(user);
    }).catch(function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;

        console.log(errorCode + " | " + errorMessage);
        // The email of the user's account used.
        var email = error.email;
        // The firebase.auth.AuthCredential type that was used.
        var credential = error.credential;
        // ...
    });

    alert('executed');
}
*/

/*
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

// invoked when the sign in button is clicked
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
        console.log(error.message);
    });

    if (debug) console.log("Sign In successful");

    var user = firebase.auth().currentUser;
    var name, email, photoUrl, uid, emailVerified;

    if (user != null) {
        name = user.displayName;
        email = user.email;
        photoUrl = user.photoURL;
        emailVerified = user.emailVerified;
        uid = user.uid; // The user's ID, unique to the Firebase project. Do NOT use
        // this value to authenticate with your backend server, if
        // you have one. Use User.getToken() instead.
    }

}
*/
