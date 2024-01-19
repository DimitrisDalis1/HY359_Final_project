/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import database.tables.EditPetKeepersTable1;
import database.tables.EditPetOwnersTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alexp
 */
@WebServlet(name = "DeleteUser", urlPatterns = {"/DeleteUser"})
public class DeleteUser extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usernameToDelete = request.getParameter("username");

        try {
            // Create instances of the classes to call non-static methods
            EditPetKeepersTable1 editPetKeepersTable1 = new EditPetKeepersTable1();
            EditPetOwnersTable editPetOwnersTable = new EditPetOwnersTable();

            // Check if the instances are not null before calling methods
            if (editPetKeepersTable1 != null) {
                editPetKeepersTable1.deletePetKeeper(usernameToDelete);
            }
            if (editPetOwnersTable != null) {
                editPetOwnersTable.deletePetOwner(usernameToDelete);
            }
            
            response.setContentType("text/plain");
            response.getWriter().write(usernameToDelete + " deleted successfully");
        } catch (SQLException | ClassNotFoundException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting user");
            e.printStackTrace(); // Log the exception for debugging
        }
    }
}