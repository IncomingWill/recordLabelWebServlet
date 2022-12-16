package incomingwill.data;

/*
 *  Document   : Connection Pool Utility
 *  Created on : 08.05.22
 *  @author incomingWill
 *  CPS 316 Final Project
*/

import java.sql.*;

import incomingwill.business.*;

public class DownloadDB 
{

    public static long insert(Download download) 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO Download "
                + "(UserID, DownloadDate, ProductCode) "
                + "VALUES "
                + "(?, NOW(), ?)";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setLong(1, download.getUser().getId());
            ps.setString(2, download.getProductCode());
            return ps.executeUpdate();
        } 
        catch (SQLException e) 
        {
            System.err.println(e);
            return 0;
        } 
        finally 
        {
            DBUtility.closeResultSet(rs);
            DBUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}