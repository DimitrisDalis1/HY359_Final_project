/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import database.tables.EditPetKeepersTable;
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
public class ToUpdateData extends HttpServlet {

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
            out.println("<title>Servlet ToUpdateData</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ToUpdateData at " + request.getContextPath() + "</h1>");
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
            //pkt.updatePetKeeper(p.getUsername(), "New standard value");
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
        HttpSession session = request.getSession();
        //System.out.println("Name is : " + session.getAttribute("loggedIn").toString());
        String name = session.getAttribute("loggedIn").toString();
        EditPetKeepersTable pkt = new EditPetKeepersTable();
        BufferedReader inputJSONfromClient = request.getReader();

        //Convert to string
        JSON_Converter jsc = new JSON_Converter();
        String finalInput = jsc.getJSONFromAjax(inputJSONfromClient);

        //dummy
        PetKeeper p = pkt.jsonToPetKeeper(finalInput);
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            pkt.updatePetKeeper(name, p.getPersonalpage(), p.getGender(), p.getBirthdate(), p.getFirstname(), p.getLastname(), p.getJob(), p.getTelephone(), p.getProperty(), p.getCatkeeper(), p.getDogkeeper(), p.getCatprice(), p.getDogprice(), p.getPropertydescription());


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
