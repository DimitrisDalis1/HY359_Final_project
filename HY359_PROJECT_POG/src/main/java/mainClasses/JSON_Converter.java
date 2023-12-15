/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainClasses;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;

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
    public String PetOwnerToJSON(PetOwner per) {
        Gson gson = new Gson();

        String json = gson.toJson(per, PetOwner.class);
        return json;
    }
    
    public String PetKeeperToJSON(PetKeeper per) {
        Gson gson = new Gson();

        String json = gson.toJson(per, PetKeeper.class);
        return json;
    }
    
    public PetKeeper jsonToPetKeeper(BufferedReader json) {
        Gson gson = new Gson();
        PetKeeper msg = gson.fromJson(json, PetKeeper.class);
        return msg;
    }
    
    public PetOwner jsonToPetOwner(BufferedReader json) {
        Gson gson = new Gson();
        PetOwner msg = gson.fromJson(json, PetOwner.class);
        return msg;
    }


    
    
}
