
package incomingwill.data;

/*
 *  Document   : Database Utility
 *  Created on : 08.05.22
 *  @author incomingWill
 *  CPS 316 Final Project
*/

import java.sql.*;

public class DBUtility {

    public static void closeStatement(Statement s) 
    {
        try 
        {
            if (s != null) 
            {
                s.close();
            }
        } 
        catch (SQLException e) 
        {
            System.err.println(e);
        }
    }

    public static void closePreparedStatement(Statement ps) {
        try 
        {
            if (ps != null) 
            {
                ps.close();
            }
        } 
        catch (SQLException e) 
        {
            System.err.println(e);
        }
    }

    public static void closeResultSet(ResultSet rs) 
    {
        try 
        {
            if (rs != null) 
            {
                rs.close();
            }
        } 
        catch (SQLException e) 
        {
            System.err.println(e);
        }
    }
}