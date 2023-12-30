package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.tables.EditPetKeepersTable1;
import mainClasses.PetKeeper;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;

// Import statements...
import com.google.gson.Gson;
import java.util.List;
import java.util.stream.Collectors;

public class AvailablePetKeepers extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        List<PetKeeper> keepers = new ArrayList<PetKeeper>();
        EditPetKeepersTable1 available_petKeeper = new EditPetKeepersTable1();

        try {
            keepers = available_petKeeper.getAvailableKeepers("all");
        } catch (SQLException ex) {
            Logger.getLogger(AvailablePetKeepers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AvailablePetKeepers.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Create a list of usernames
        List<String> usernames = keepers.stream()
                .map(PetKeeper::getUsername)
                .collect(Collectors.toList());

        // Use Gson to convert the list of usernames to a JSON array
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(usernames);

        // Print the JSON response
        PrintWriter out = response.getWriter();
        out.write(jsonResponse);
        out.close();
    }
}


//for loop string 
//keepers.get(1).getUsername()
//trexw guest
//kanw register neo user
//jana trexw na dw an emfanizete o neos user.



