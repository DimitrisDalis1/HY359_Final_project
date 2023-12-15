//Function to check if the passwords match
function check_if_same() {
  let x = document.getElementById("password").value
  let y = document.getElementById("re-password").value

  if (x != y) {
    console.log("failed match")
    document.getElementById("error_message").innerHTML = "Passwords do not match"
  }
  else if (x===y){
    my_value = document.getElementById("error_message")
    if (my_value.innerHTML === "Passwords do not match"  || my_value.innerHTML === "") {
      if (x != "" && y!= ""){
        my_value.innerHTML = "Passwords match"
      } else {
        my_value.innerHTML = ""
      }
    }
  }
  validate_submit();
}

//Show/Hide password
function showPass(){
  if(document.getElementById("password").type==="text"){
    document.getElementById("password").type="password";
    document.getElementById("re-password").type="password";
    document.getElementById("show_password").value="Show Password";
  }
  else{
    document.getElementById("password").type="text";
    document.getElementById("re-password").type="text";
    document.getElementById("show_password").value="Hide Password";
  }
}

//Check substrings
function check_for_substrings() {
  let x = document.getElementById("password").value
  let sub1 = "cat", sub2 = "dog", sub3 = "gata", sub4 = "skulos";

  if (x.includes(sub1) || x.includes(sub2) || x.includes(sub3) || x.includes(sub4)) {
    /*
    document.getElementById("error_for_substrings")
      .innerHTML = "Password must not include the substrings: cat, dog, gata, skulos"
      */
    console.log(`Substring ${sub1}, ${sub2}, ${sub3}, ${sub4}, are not allowed`)
    return false
  }
  else {
    /*
    document.getElementById("error_for_substrings")
    .innerHTML = ""
    */
    return true
  }

}

function password_strength() {
  let x = document.getElementById("password").value
  let length = x.length
  let result = amount_of_numbers(x)
  if(x === "") {
    document.getElementById("password_strength_check").innerHTML = ""
  } else if(result >= (length / 2) || length < 8) {
    document.getElementById("password_strength_check").innerHTML = "Weak Password"

  } else if (containsLowercase(x) && containsUppercase(x) && containsSpecialChars(x) && amount_of_numbers(x)>0 && length >= 8 && length <= 15)
  {
    document.getElementById("password_strength_check").innerHTML = "Strong Password"
  }
  else if(length >= 8 && length <= 15){
    document.getElementById("password_strength_check").innerHTML = "Medium Password"
  }
  validate_submit();


}

function containsSpecialChars(str) {
  const specialChars = /[`!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
  return specialChars.test(str);
}

function containsUppercase(str) {
  return /[A-Z]/.test(str);
}

function containsLowercase(str) {
  return /[a-z]/.test(str);
}

function amount_of_numbers(str) {
  str = str.match(/\d/g);
  if (str === null) {
    return 0;
  }
  else {
    return Object.keys(str).length;
  }

}

function validation() {
  console.log(check_for_substrings());
}

function validate_submit() {
  let x = document.getElementById("error_message").innerHTML;
  let y = document.getElementById("password_strength_check").innerHTML;
  if (x ===  "Passwords match" && y != "Weak Password") {
    //document.getElementById("submit_button").disabled = false;
    console.log("They match")
    return true;
  }
  else {
    console.log("They do not match");
    //document.getElementById("submit_button").disabled = true;
    return false;

  }
}

function validate_submit1() {
  if(validate_submit() === false) {
    document.getElementById("submit_button").disabled = true; 
    alert("Passwords do not match or passwords too weak")
  }
  else {
    document.getElementById("submit_button").disabled = false;
  }
  document.getElementById("submit_button").disabled = false; 
}