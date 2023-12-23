package servlets;

import com.google.gson.JsonObject;
import database.DB_Connection;
import mainClasses.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Chat extends HttpServlet {
    private final DB_Connection dbConnection = new DB_Connection();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int lastMessageID = Integer.parseInt(request.getParameter("lastID"));

        try (PrintWriter out = response.getWriter()) {
            // Send only the most recent message to the client
            if (lastMessageID < messages.size()) {
                out.println(messages.get(messages.size() - 1));
            }
        }
    }

    // Updated to use Message class
    private final ArrayList<Message> messages = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String recipient = request.getParameter("recipientName");
            System.out.println("onoma edw " + recipient);
            // Get the current user's username from the session
            String sender = getCurrentUsername(request);

            String userMessage = request.getParameter("message");

            if (checkRecipientExistence(recipient)) {
                // Create a new Message object
                Message newMessage = new Message();
                newMessage.setSender(sender);
                newMessage.setRecipient(recipient);
                newMessage.setMessage(userMessage);
                // Set timestamp and other fields as needed

                // Add the message to the list
                messages.add(newMessage);

                // Print the message in the response
                String messageHTML = "<p class='chat-message'>" + sender + " to " + recipient + ": " + userMessage + "</p>";
                out.print(messageHTML);
            } else {
                // Print "PetKeeper Not Found" in a new line
                out.print("<p class='error-message'>PetKeeper Not Found</p>");
            }
        }
    }

    private String getCurrentUsername(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("loggedIn");

        // Check if the username is not null or empty
        if (username != null && !username.isEmpty()) {
            return username;
        } else {
            String actualUsername = "Error";

            return actualUsername;
        }
    }

private boolean checkRecipientExistence(String recipient) {
    boolean recipientExists = false;
    System.out.println("this is the recpiwnt " + recipient);
    try (Connection connection = dbConnection.getConnection()) {
        String query = "SELECT * FROM PetKeepers WHERE username=?";
        System.out.println("this is the query " + query);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, recipient);
            System.out.println("Executing query: " + preparedStatement.toString()); 
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                recipientExists = resultSet.next();
            }
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    System.out.println("Recipient exists for " + recipient + ": " + recipientExists);
    return recipientExists;
}



    @Override
    public String getServletInfo() {
        return "Short description";
    }
}