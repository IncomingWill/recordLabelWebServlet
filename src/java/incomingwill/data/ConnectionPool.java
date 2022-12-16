package incomingwill.data;

/*
 *  Document   : Connection Pool Utility
 *  Created on : 08.05.22
 *  @author incomingWill
 *  CPS 316 Final Project
*/

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;

public class ConnectionPool 
{

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    //private constructor to create an instance of the connection pool
        //InitialContext object returns a DataSource object
            //must match attribute specified in context.xml file
    private ConnectionPool() 
    {
        try 
        {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/music");
        } 
        catch (NamingException e) 
        {
            System.err.println(e);
        }
    }

    //getInstance and constructor allow only a single instance of
        //class to abide by singleton pattern
    public synchronized static ConnectionPool getInstance() 
    {
        if (pool == null) 
        {
            pool = new ConnectionPool();
        }
        return pool;
    }
    
    public Connection getConnection() 
    {
        try 
        {
            return dataSource.getConnection();
        } 
        catch (SQLException sqle) 
        {
            System.err.println(sqle);
            return null;
        }
    }

    public void freeConnection(Connection c) 
    {
        try 
        {
            c.close();
        } 
        catch (SQLException sqle) 
        {
            System.err.println(sqle);
        }
    }
}