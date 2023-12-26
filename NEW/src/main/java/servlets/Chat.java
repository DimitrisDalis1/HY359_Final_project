/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditMessagesTable;
import java.io.BufferedReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.JSONCONVERSION;
import mainClasses.Message;

public class Chat extends HttpServlet {

    ArrayList<String> message = new ArrayList<String>();
    ArrayList<Message> newmes = new ArrayList<Message>();
    int pol = 0;



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
        EditMessagesTable emt = new EditMessagesTable();
        try {
            newmes = emt.databaseToMessage(2);
            //System.out.println(newmes.get(0).getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html;charset=UTF-8");
        String messageToSend = "";
        int lastMessageID = Integer.parseInt(request.getParameter("lastID"));
        //System.out.println(lastMessageID);
        try (PrintWriter out = response.getWriter()) {
            System.out.println("Last message id " + lastMessageID);
            System.out.println("newsize " + message.size());

            for (int i = 0; i < newmes.size(); i++) {
                messageToSend += "<p>" + newmes.get(i).getSender() + ":" + newmes.get(i).getMessage() + "</p>";
            }
            System.out.println(messageToSend);
            out.print(messageToSend);
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
        response.setContentType("text/html;charset=UTF-8");


        Message msg = new Message();
        EditMessagesTable emt = new EditMessagesTable();
        JSONCONVERSION jc = new JSONCONVERSION();
        BufferedReader inputJSONfromClient = request.getReader();
        String finalInput = jc.getJSONFromAjax(inputJSONfromClient);
        System.out.println("Final input: " + finalInput);
        msg = emt.jsonToMessage(finalInput);


        try {
            emt.createNewMessage(msg);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String newMessage = "<p>" + msg.getSender() + ":" + msg.getMessage() + "</p>";
            //message.add(newMessage);
            //out.print(newMessage);
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
