
package incomingwill.data;

/*
 *  Document   : Product Database
 *  Created on : 08.05.22
 *  @author incomingWill
 *  CPS 316 Final Project
*/

import java.sql.*;
import java.util.*;

import incomingwill.business.*;

public class ProductDB 
{

    //This method returns null if a product isn't found.
    public static Product selectProduct(String productCode) 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Product "
                + "WHERE ProductCode = ?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, productCode);
            rs = ps.executeQuery();
            if (rs.next()) 
            {
                Product p = new Product();
                p.setProductID(rs.getLong("ProductID"));
                p.setCode(rs.getString("ProductCode"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setPrice(rs.getDouble("ProductPrice"));
                return p;
            } 
            else 
            {
                return null;
            }
        } 
        catch (SQLException e) 
        {
            System.err.println(e);
            return null;
        } 
        finally 
        {
            DBUtility.closeResultSet(rs);
            DBUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    //This method returns null if a product isn't found.
    public static Product selectProduct(long productID) 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Product "
                + "WHERE ProductID = ?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setLong(1, productID);
            rs = ps.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getLong("ProductID"));
                p.setCode(rs.getString("ProductCode"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setPrice(rs.getDouble("ProductPrice"));
                return p;
            } 
            else 
            {
                return null;
            }
        } 
        catch (SQLException e) 
        {
            System.err.println(e);
            return null;
        } 
        finally 
        {
            DBUtility.closeResultSet(rs);
            DBUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    //This method returns null if a product isn't found.
    public static List<Product> selectProducts() 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Product";
        try 
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Product> products = new ArrayList<>();
            while (rs.next()) 
            {
                Product p = new Product();
                p.setCode(rs.getString("ProductCode"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setPrice(rs.getDouble("ProductPrice"));
                p.setProductID(rs.getLong("ProductID"));
                products.add(p);
            }
            return products;
        } 
        catch (SQLException e) 
        {
            System.err.println(e);
            return null;
        } 
        finally 
        {
            DBUtility.closeResultSet(rs);
            DBUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    //This method inserts a product into the database
    public static void insertProduct(Product product)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query
                = "INSERT INTO Product "
                + "(ProductCode, "
                + "ProductDescription, "
                + "ProductPrice) "
                + "VALUES (?, ?, ?)";
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            
            ps.executeUpdate();
            
            //Get the product ID from the last INSERT statement
            String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
            Statement identityStatement = connection.createStatement();
            ResultSet identityResultSet = identityStatement.executeQuery(
                    identityQuery);
            identityResultSet.next();
            long productID = identityResultSet.getLong("IDENTITY");
            identityResultSet.close();
            identityStatement.close();
            
            //set the product ID in the Product object
            product.setProductID(productID);
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally
        {
            DBUtility.closeResultSet(rs);
            DBUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    //This method edits a product into the database
    public static void updateProduct(Product product)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE Product SET "
                + "ProductCode = ?, "
                + "ProductDescription = ?, "
                + "ProductPrice = ? "
                + "WHERE ProductID = ?";
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setLong(4, product.getProductID());
            
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally
        {
            DBUtility.closeResultSet(rs);
            DBUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    //This method deletes a product from the database via product code
    public static void deleteProduct(Product product)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "DELETE FROM Product "
                + "WHERE ProductID = ?";
        try
        {
            ps = connection.prepareStatement(query);
            ps.setLong(1, product.getProductID());
    
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally
        {
            DBUtility.closeResultSet(rs);
            DBUtility.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}