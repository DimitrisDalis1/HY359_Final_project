var parentElement = document.getElementById('container')



function create_title(str, s_elem) {
	document.getElementById(s_elem).innerHTML = str;
}

checkme = true;

function check_this() {
	if(checkme === true) {
		show_extra_form();
		checkme = false;
	}
	else {
		let x = document.getElementById("posts");
		x.style.display = "block";
	}

}

function show_extra_form() {

	var a = 1, b = 2;
	var div = document.createElement('div');
	div.setAttribute('class', 'post block bc2');
	div.innerHTML = `
		<div >
			<h3>Dog Property</h3>
			<div>
				<label for = "indoor" >Εσωτερικός Χώρος</label>
				<input type = "radio" name = "property" id="indoor" value="indoor" onclick="if_outside();">
				<br>
				<label for = "outdoor" >Εξωτερικός Χώρος</label>
				<input type = "radio" name = "property" id="outdoor" value="outdoor" onclick="if_outside();">
				<br> 
				<label for = "both" >Και τα 2</label>
				<input type = "radio" name = "property" id="both" value="both" onclick="if_outside();"> 
			</div> 

			<h3>Φιλοξενώ</h3>
			<div>
				<input type="checkbox" id="catkeeper" name="catkeeper" value="Cat" onclick="if_checked_cat();">
				<label for="catkeeper">Φιλοξενώ Γάτα</label><br>
				<input type="checkbox" id="dog" name="dogkeeper" value="Dog" onclick="if_checked_dog();" >
				<label for="dog">Φιλοξενώ Σκύλο</label><br>
			</div>

            <h3>Τιμή φιλοξενίας για γάτα ένα βράδυ</h3>
            <div>
                <label for="cat_price">Τιμή (απο 0 εως 15)</label>
                <br>
                <input type="number" id="cat_price" name="catprice" value="8" onchange="check_price();" min = "0" max = "15">
            <div>

            <h3>Τιμή φιλοξενίας για σκύλο ένα βράδυ</h3>
            <div>
                <label for="dog_price">Τιμή (απο 0 εως 15)</label>
                <br>
                <input type="number" id="dog_price" name="dogprice" value="10" onchange="check_price();" min = "0" max = "20">
            <div>
            <div>
                <h3><p><label for="propertyDescription">Περιγραφή Κατοικίας</label></p></h3>
                <textarea id="propertyDescription" name="propertydescription" rows="4" cols="50">Αναφέρετε μερικά πράγματα για τον τόπο κατοικοίας σας.</textarea>
            </div>
		</div>
	`;
	document.getElementById('posts').appendChild(div);
}

function if_outside() {
    let radio1 = document.getElementById('outdoor');
    if(radio1.checked){
        console.log("Enable")
        document.getElementById("dog").disabled = false;
    }else {
        console.log("Disable")
        document.getElementById("dog").disabled = true;
        document.getElementById("dog").checked = false;
        document.getElementById("catkeeper").required = true;
    }
}

function check_price() {
    let x = document.getElementById("cat_price").value;
    if (x < 0 || x > 15){
        return false;
    }else {
        return true;
    }
}

function if_checked_cat() {
    let x = document.getElementById("catkeeper").checked;
    document.getElementById("dog").required = false;
    if (x) {
        document.getElementById("cat_price").required = true;
    }else {
        document.getElementById("cat_price").required = false;
    }
}

function if_checked_dog() {
    let x = document.getElementById("dog").checked;
    document.getElementById("catkeeper").required = false;
    if (x) {
        document.getElementById("dog_price").required = true;
    }else {
        document.getElementById("dog_price").required = false;
    }
}

function hide_extra_form() {
	let x = document.getElementById("posts");
	if (x.style.display === "none") {
		x.style.display = "block";
	} else {
		x.style.display = "none";
    uncheck_all_fields();
	}
}

function uncheck_all_fields() {
  $('input[name=property]').prop('checked',false);
  $('input[name=catkeeper]').prop('checked',false);
  $('input[name=dogkeeper]').prop('checked',false);
  $('#cat_price').val("8");
  $('#dog_price').val("10");


}

//Na kanoun uncheck otan epilegw to allo






































/*
function show_extra_form(pElement){  //pElement refers to the parent element
	create_title("Diamonh", 'Prwto pedio');
    var radioButton1 = document.createElement('input');
    var radioButton2 = document.createElement('input');
    var radioButton3 = document.createElement('input');


    radioButton1.setAttribute('type' , 'radio');
    radioButton2.setAttribute('type' , 'radio');
    radioButton3.setAttribute('type' , 'radio');

    radioButton1.setAttribute('id' , 'newRadio1');
    radioButton1.setAttribute('id' , 'newRadio2');
    radioButton1.setAttribute('id' , 'newRadio3');

	radioButton1.setAttribute('name' , 'prop');
    radioButton2.setAttribute('name' , 'prop');
    radioButton3.setAttribute('name' , 'prop');


    var label1 = document.createElement('label');
    var label2 = document.createElement('label');
    var label3 = document.createElement('label');

    label1.textContent = 'Outdoors';
    label2.textContent = 'Indoors';
    label3.textContent = 'Both';

    label1.setAttribute('for' , 'newRadio1');
    label2.setAttribute('for' , 'newRadio2');
    label3.setAttribute('for' , 'newRadio3');



    pElement.appendChild(label1);
	pElement.appendChild(radioButton1);

    pElement.appendChild(label2);
    pElement.appendChild(radioButton2);

    pElement.appendChild(label3);
    pElement.appendChild(radioButton3);

}
*/