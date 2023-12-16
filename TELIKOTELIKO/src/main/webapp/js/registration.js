// registration.js

function registerUser() {
    /*var formData = {
        username: document.getElementById('username').value,
        email: document.getElementById('email').value,
        password: document.getElementById('passwordInput').value,
        confirmPassword: document.getElementById('confirmPassword').value,
        name: document.getElementById('name').value,
        surname: document.getElementById('surname').value,
        dob: document.getElementById('dob').value,
        gender: document.querySelector('input[name="gender"]:checked').value,
        Type: document.getElementById('Type').value,
        indoorAccommodation: document.getElementById('indoorAccommodation').checked,
        outdoorAccommodation: document.getElementById('outdoorAccommodation').checked,
        hostCat: document.getElementById('hostCat').checked,
        hostDog: document.getElementById('hostDog').checked,
        catPriceInput: document.getElementById('catPrice').value,
        dogPriceInput: document.getElementById('dogPrice').value,
        Description: document.getElementById('propertyDescription').value,
        country: document.getElementById('country').value,
        city: document.getElementById('city').value,
        address: document.getElementById('address').value,
        personalPage: document.getElementById('personalPage').value,
        job: document.getElementById('job').value,
        mobilePhone: document.getElementById('mobilePhone').value,
        agreement: document.getElementById('agreement').checked,
    };*/
    
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => {
		if(key === "indoorAccommodation"){
			return data["property"] = "Indoor"
		}else if(key === "outdoorAccommodation"){
			return data["property"] = "Outdoor"
		}
		
		
		
		return data[key] = value
	});

	console.log(formData);
	
    if ($("#country").val() === "Monaco") {
        alert("Banned due to Many Requests");
        return;
    }

    var jsonData = JSON.stringify(data);
    console.log(formData, myForm, jsonData);

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const responseData = JSON.parse(xhr.responseText);
			console.log(responseData);
           // $('#ajaxContent').html(createTableFromJSON(responseData));
           // setChoicesForRegisteredUser();
			document.getElementById('ajaxContent').style.display = "flex";
			document.getElementById('ajaxContent').style.color = "green";
			  	document.getElementById('ajaxContent')
                    .innerHTML = 'Request succeed. User: '+responseData.username+' is registered!';

        } else if (xhr.status !== 200) {
           
            const responseData = JSON.parse(xhr.responseText).error;
 			console.log(responseData);
         	document.getElementById('ajaxContent')
                    .innerHTML = 'Request failed. '+responseData;
			document.getElementById('ajaxContent').style.display = "flex";
        }
    };
    //alert(jsonData);
    xhr.open('POST', 'registerUser');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsonData);
}

function showLogin() {
   	document.getElementById('login-container').style.display = "flex";
	document.getElementById('registerForm').style.display = "none";
}


function showRegistrationForm() {
    document.getElementById('login-container').style.display = "none";
	document.getElementById('registerForm').style.display = "flex";
}

function loginPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            //setChoicesForLoggedUser();
           $("#outputContent").html("Welcome again "+xhr.responseText);
		 	document.getElementById('login-container').style.display = "none";
			document.getElementById('registerButton').style.display = "none";
			document.getElementById('loginButton').style.display = "none";
			document.getElementById('logoutButton').style.display = "inline";
        } else if (xhr.status !== 200) {
              $("#outputContent").html("Wrong Credentials");
            //('Request failed. Returned status of ' + xhr.status);
        }
    };
    var data = $('#loginForm').serialize();
    xhr.open('POST', 'Login');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send(data);
}

function isLoggedIn() {
	var xhr = new XMLHttpRequest();
	xhr.onload = function () {
		if (xhr.readyState === 4 && xhr.status === 200) {
			//setChoicesForLoggedUser();
			$("#outputContent").html("Welcome again "+xhr.responseText);
			document.getElementById('registerForm').style.display = "none";
			document.getElementById('registerButton').style.display = "none";
			document.getElementById('loginButton').style.display = "none";
			document.getElementById('logoutButton').style.display = "inline";
			
		} else if (xhr.status !== 200) {
			$("#outputContent").html("Login or register to continue");
			document.getElementById('registerButton').style.display = "inline";
			document.getElementById('loginButton').style.display = "inline";
			document.getElementById('logoutButton').style.display = "none";
			document.getElementById('registerForm').style.display = "inline";
		}
	};
	xhr.open('GET', 'Login');
	xhr.send();
}

$(document).ready(function ()
{
	isLoggedIn();
});

function logout(){
	var xhr = new XMLHttpRequest();
	xhr.onload = function () {
		if (xhr.readyState === 4 && xhr.status === 200) {
			$("#outputContent").html("Successful Logout");
			document.getElementById('registerButton').style.display = "inline";
			document.getElementById('loginButton').style.display = "inline";
			document.getElementById('logoutButton').style.display = "none";
			document.getElementById('registerForm').style.display = "inline";
		} else if (xhr.status !== 200) {
			alert('Request failed. Returned status of ' + xhr.status);
		}
	};
	xhr.open('POST', 'Logout');
	xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	xhr.send();
}


