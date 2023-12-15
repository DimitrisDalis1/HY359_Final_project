/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainClasses;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author micha
 */
public class JSON_Converter {
    
    public String getJSONFromAjax(BufferedReader reader) throws IOException{
	StringBuilder buffer = new StringBuilder();
	String line;
	while ((line = reader.readLine()) != null) {
		buffer.append(line);
	}
	String data = buffer.toString();
        return data;

    }

    public String JavaObjectToJSONRemoveElements(PetKeeper p, String removeProp) {
        // Creating a Gson Object
        Gson gson = new Gson();
        String json = gson.toJson(p, PetKeeper.class);
        JsonObject object = (JsonObject) gson.toJsonTree(p);
        object.remove(removeProp);
        return object.toString();
    }

    public PetOwner jsonToPetOwner(BufferedReader json) {
        Gson gson = new Gson();
        PetOwner msg = gson.fromJson(json, PetOwner.class);
        return msg;
    }

    public String petOwnerToJSON(PetOwner per) {
        Gson gson = new Gson();

        String json = gson.toJson(per, PetOwner.class);
        return json;
    }


    
    
}
