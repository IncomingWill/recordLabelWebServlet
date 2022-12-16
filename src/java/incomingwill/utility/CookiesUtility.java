package incomingwill.utility;

/*
 *  Document   : Cookies Utility
 *  Created on : 08.05.22
 *  @author incomingWill
 *  CPS 316 Final Project
*/

import jakarta.servlet.http.*;

public class CookiesUtility 
{

    public static String getCookieValue(
            Cookie[] cookies, 
            String cookieName) 
    {

        String cookieValue = "";
        if (cookies != null) 
        {
            for (Cookie cookie: cookies) 
            {
                if (cookieName.equals(cookie.getName())) 
                {
                    cookieValue = cookie.getValue();
                }
            }
        }
        return cookieValue;
    }
}
