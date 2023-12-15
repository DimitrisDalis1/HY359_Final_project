/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function showPetKeeperForm(){
    $("#ajaxContent").load("PetKeeper.html");
}

function showPetOwnerForm(){
    $("#ajaxContent").load("PetOwner.html");
}

function showPetOwnerRegistrationForm(){
    $("#ajaxContent").load("PetOwnerRegistrationForm.html");
}

function showPetKeeperRegistrationForm(){
    $("#ajaxContent").load("PetKeeperRegistrationForm.html");
}

function showLoginForm(){
    $("#ajaxContent").load("Login.html");
}



function showHomePage(){
    $("#ajaxContent").load("index.html");
}

function showClientPage(){
    
    //eimai pet woner : ....
    //h eimai pet keepr : ..
    $("#ajaxContent").load("client.html");
    
}

function RegisterPOST() {
    let myForm = document.getElementById('register');
    let formData = new FormData(myForm);
    
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsonData = JSON.stringify(data);
    console.log(jsonData)
    
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const responseData = JSON.parse(xhr.responseText);
            //$('#register').hide();
            $('#ajaxContent').html("Successful Registration. Now please log in!<br> Your Data");
            //$('#ajaxContent').append("Successful Registration. Now please log in!<br> Your Data");
            $('#ajaxContent').append(createTableFromJSON(responseData));
            message1 = "Successful Registration. Now please log in!<br> Your Data";
            message2 = createTableFromJSON(responseData);
        } else if (xhr.status !== 200) {
            //$('#register').hide();
            $('#ajaxContent').append('Request failed. Returned status of ' + xhr.status + "<br>");
            message1 = 'Request failed. Returned status of ' + xhr.status + "<br>";
           const responseData = JSON.parse(xhr.responseText);
            for (const x in responseData) {
                $('#ajaxContent').append("<p style='color:red'>" + x + "=" + responseData[x] + "</p>");
            }
        }
    };
    
        xhr.open('POST', 'Register');
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.send(jsonData);


}


