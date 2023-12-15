/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.JsonObject;
import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
import java.io.BufferedReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.JSON_Converter;
import mainClasses.PetOwner;


/**
 *
 * @author mountant
 */
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EditPetOwnersTable pot = new EditPetOwnersTable();
        EditPetKeepersTable pkt = new EditPetKeepersTable();
        BufferedReader inputJSONfromClient = request.getReader();

        //Convert to string
        JSON_Converter jsc = new JSON_Converter();
        String finalInput = jsc.getJSONFromAjax(inputJSONfromClient);
        System.out.println(finalInput);
        //dummy
        PetOwner p = pot.jsonToPetOwner(finalInput);
        //

        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            if ((pot.checkIfPetOwnerRegistered(p.getUsername(), p.getEmail()) != null) || (pkt.checkIfPetKeeperRegistered(p.getUsername(), p.getEmail()) != null)) {
                response.setStatus(409);
                JsonObject jo = new JsonObject();
                jo.addProperty("error", " Username Already Taken");
                out.write(jo.toString());
            } else {
                pot.addPetOwnerFromJSON(finalInput);
                response.setStatus(200);
                out.write(finalInput);
            }
        } catch (ClassNotFoundException ex) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("User already registered");
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }




    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
