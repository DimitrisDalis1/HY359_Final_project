/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.google.gson.JsonObject;
import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mainClasses.JSON_Converter;
import mainClasses.PetKeeper;

/**
 *
 * @author porok
 */
public class RegisterKeeper extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterKeeper</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterKeeper at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        JSON_Converter jsc = new JSON_Converter();

        HttpSession session = request.getSession();
        EditPetKeepersTable pkt = new EditPetKeepersTable();
        try {
            PetKeeper p = pkt.databaseToPetKeepersOnlyName(session.getAttribute("loggedIn").toString());
            System.out.println("Address is :" + p.getAddress());
            String json = jsc.JavaObjectToJSONRemoveElements(p, "password");
            response.setStatus(200);
            response.getWriter().write(json);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterKeeper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterKeeper.class.getName()).log(Level.SEVERE, null, ex);
        }

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

        //dummy
        PetKeeper p = pkt.jsonToPetKeeper(finalInput);
        //

        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            if ((pot.checkIfPetOwnerRegistered(p.getUsername(), p.getEmail()) != null) || (pkt.checkIfPetKeeperRegistered(p.getUsername(), p.getEmail()) != null)) {
                response.setStatus(409);
                JsonObject jo = new JsonObject();
                jo.addProperty("error", " Username Already Taken_1");
                out.write(jo.toString());
            } else {
                pkt.addPetKeeperFromJSON(finalInput);
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
