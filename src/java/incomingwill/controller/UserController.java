package incomingwill.controller;

/*
 *  Document   : Email Utility
 *  Created on : 08.05.22
 *  @author incomingWill
 *  CPS 316 Final Project
*/

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import incomingwill.business.User;
import incomingwill.data.UserDB;

public class UserController extends HttpServlet 
{

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                      throws IOException, ServletException 
    {

        String requestURI = request.getRequestURI();
        String url = "";
        if (requestURI.endsWith("/deleteCookies")) 
        {
            url = deleteCookies(request, response);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
                       throws IOException, ServletException 
    {

        String requestURI = request.getRequestURI();
        String url = "";
        if (requestURI.endsWith("/subscribeToEmail")) 
        {
            url = subscribeToEmail(request, response);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String deleteCookies(HttpServletRequest request,
                                 HttpServletResponse response) 
    {
        Cookie[] cookies = request.getCookies();
        
        //delete cookies for the entire app
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "/delete_cookies.jsp";
    }

    private String subscribeToEmail(HttpServletRequest request,
                                    HttpServletResponse response) 
    {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        request.setAttribute("user", user);

        String url;
        String message;
        
        //check that email address doesn't already exist
        if (UserDB.emailExists(email)) 
        {
            message = "This email address already exists. <br>"
                    + "Please enter another email address.";
            request.setAttribute("message", message);
            url = "/email/index.jsp";
        } 
        else 
        {
            UserDB.insert(user);
            message = "";
            request.setAttribute("message", message);
            url = "/email/thanks.jsp";
        }
        return url;
    }
}