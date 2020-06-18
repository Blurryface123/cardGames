function userLogin(){
try {
    const userAction = async () => {
        const response = await fetch('localhost:8080/login', {
            method: 'POST',
            body: {
                "username": $("#username").val(),
                "password": $("#password").val(),
                "room": $('#room option:selected').text()
            }, // string or object
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const myJson = await response.json(); //extract JSON from the http response
        console.log(myJson);
        // do something with myJson
    }} catch (error) {
        // Error handling here!
    }

}
