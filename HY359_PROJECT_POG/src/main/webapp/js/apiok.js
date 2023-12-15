var lon_main;
var lat_main;

function loadDoc(){
  const xhr = new XMLHttpRequest();
  xhr.withCredentials = true;
  
  xhr.addEventListener("readystatechange", function () {
    if (this.readyState === this.DONE) {
      const obj = JSON.parse(xhr.responseText);
          var addressDetails=obj[0];
          var i = 0;
          for(var name in addressDetails){
            if(name === "lon") {lon_main = addressDetails[name];}
            if(name === "lat") {lat_main = addressDetails[name];}
            
            i += 1
            if(name === "display_name"){           
              if(check_if_heraklion(addressDetails[name]) === true) {
                check_address(addressDetails[name]); 
              }
              else {
                check_address("1");
              }
            }
          }
          if (i === 0) {
            console.log("Error")
            check_address("0");
          }
    }
  });


  var addressName =  document.getElementById("address").value;


  var country = document.getElementById("country").value;


  var city = document.getElementById("city").value;

  var address = country+ " " + city + " " + addressName;
  console.log(address)
  
  
  xhr.open("GET", "https://forward-reverse-geocoding.p.rapidapi.com/v1/search?q="+address+"&accept-language=en&polygon_threshold=0.0");
  
  xhr.setRequestHeader("x-rapidapi-host", "forward-reverse-geocoding.p.rapidapi.com");
  var key="4225e86e72msh9d5ea0869c744a2p15cdb6jsn42e73244df5e"; //YOUR KEY
  xhr.setRequestHeader("x-rapidapi-key", key);
  
  xhr.send();
}

function check_address(x) {
  if (x === "0") {
    console.log("here")
    document.getElementById("address_dummy").innerHTML = "Address not found"
  }
  else if (x === "1"){
    document.getElementById("address_dummy").innerHTML = "The service is only available at heraklion!"
  }
  else {
    document.getElementById("address_dummy").innerHTML = "<br>" + x

  }
}

function check_if_heraklion(name) {

  if(name.includes("Heraklion")) {
    console.log("In crete");
    return true;
  }else
  {
    console.log("Not in crete!")
    return false;
  }
}

