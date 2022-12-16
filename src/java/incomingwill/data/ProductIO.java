
package incomingwill.data;

/*
 *  Document   : ProductIO
 *  Created on : 08.05.22
 *  @author incomingWill
 *  CPS 316 Final Project
*/

import java.io.*;
import java.util.*;
import incomingwill.business.Product;

public class ProductIO 
{
    private static List<Product> products = null;
    private static String filePath =  null;
    
    //Called once from the controller based on servlet context
    public static void init(String filePath)
    {
        ProductIO.filePath = filePath;
    }
    
    //method to set products in a list
    public static List<Product> selectProducts()
    {
        products = new ArrayList<Product>();
        File file = new File(filePath);
        try
        {
            BufferedReader in = new BufferedReader(
                                new FileReader(file));
            
            String line = in.readLine();
            while (line != null)
            {
                StringTokenizer t = new StringTokenizer(line, "|");
                if (t.countTokens() >= 3)
                {
                    String code = t.nextToken();
                    String description = t.nextToken();
                    String priceAsString = t.nextToken();
                    double price = Double.parseDouble(priceAsString);
                    
                    Product p = new Product();
                    p.setCode(code);
                    p.setDescription(description);
                    p.setPrice(price);
                    
                    products.add(p);
                }
                line = in.readLine();
            }
            in.close();
            return products;
        } 
        catch (IOException e)
        {
            System.out.println(e);
            return null;
        }
    }
    
    //method to select a product from the list
    public static Product selectProduct(String productCode)
    {
        products = selectProducts();
        for (Product p : products)
        {
            if (productCode != null && productCode.equalsIgnoreCase(p.getCode()))
            {
                return p;
            }
        }
        return null;
    }
    
    //method to check to see if a product exists in the list
    public static boolean exists(String productCode)
    {
        Product p = selectProduct(productCode);
        if (p != null) return true;
        else return false;
    }
    
    //method to save the product list to file
    private static void saveProducts(List<Product> products)
    {
        try
        {
            File file = new File(filePath);
            PrintWriter out = new PrintWriter(new FileWriter(file));
            for (Product p : products)
            {
                out.println(p.getCode() + "|"
                    + p.getDescription() + "|"
                    + p.getPrice());
            }
            
            out.close();
        } 
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    
    //method to put a product into the list of products
    public static void insertProduct(Product product)
    {
        products = selectProducts();
        products.add(product);
        saveProducts(products);
    }
    
    //method to update a product
    public static void updateProduct(Product product)
    {
        products = selectProducts();
        for (int i = 0; i < products.size(); i++)
        {
            Product p = products.get(i);
            if (product.getCode() != null 
                    && product.getCode().equalsIgnoreCase(
                    p.getCode()))
            {
                products.set(i, product);
            }
        }
        saveProducts(products);
    }
    
    //method to delete a product from the list
    public static void deleteProduct(Product product)
    {
        products = selectProducts();
        for (int i = 0; i < products.size(); i++)
        {
            Product p = products.get(i);
            if (product!= null 
                    && product.getCode().equalsIgnoreCase(
                    p.getCode()))
            {
                products.remove(i);
            }
        }
        saveProducts(products);
    }  
    
}
