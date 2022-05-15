function login() {
    var user = document.getElementById("uname").value;
    var pass = document.getElementById("psw").value;
  
    req.open("POST", "http://localhost:8080/test-app/auth", true);
    var combine = "email: " +user + ", password: " + psw;
    req.send(JSON.stringify({combine}));
  }