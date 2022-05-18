function auth() {
  // Create a request variable and assign a new XMLHttpRequest object to it.
  var request = new XMLHttpRequest()

  // Open a new connection, using the GET request on the URL endpoint
  request.open('POST', 'http://localhost:8080/test-app/auth', true)

  const obj = {"email":"lh@gmail.com", "password": "pass"};
  const myJSON = JSON.stringify(obj);
  // Send request
  request.send(myJSON)
}