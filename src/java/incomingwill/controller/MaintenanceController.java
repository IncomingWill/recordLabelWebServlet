package incomingwill.controller;

/*
 *  Document   : Admin Maintenance Controller
 *  Created on : 08.05.22
 *  @author incomingWill
 *  CPS 316 Final Project
*/

import incomingwill.data.*;
import incomingwill.business.*;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MaintenanceController extends HttpServlet 
{

    private static final String PRODUCT_MAINTENANCE_URL = 
            "/admin/productMaintenance.jsp";
    private static final String SUCCESS_URL = 
            "/admin/success.jsp";
    private static final String NEW_PRODUCT_FORM_URL =
            "/admin/new_product_form.jsp";
    private static final String EDIT_PRODUCT_FORM_URL = 
            "/admin/edit_product_form.jsp";
    private static final String DELETE_FORM_URL = 
            "/admin/delete_form.jsp";
    
    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
                       throws IOException, ServletException 
    {
        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                      throws IOException, ServletException 
    {        
        String requestURI = request.getRequestURI();
        String url = "";
        if (requestURI.endsWith("/displayProducts"))
        {
            //third button added for Product Maintenance 
                //from admin page
            url = displayProducts(request, response);
        }
        else if (requestURI.endsWith("/new")) 
        {
            //show new product form
            url = newForm(request, response);
        }
        else if (requestURI.endsWith("/delete")) 
        {
            //show delete product form
            url = deleteForm(request, response);
        }
        else if (requestURI.endsWith("/edit"))
        {
            //show editing form
            url = editForm(request, response);
        }
        else if (requestURI.endsWith("/newProduct"))
        {
            //insert new product into databse
            url = insertProduct(request, response);
        }
        else if (requestURI.endsWith("/update"))
        {
            //update product in database from edit form
            url = updateProduct(request, response);
        }
        else if (requestURI.endsWith("/deleteProduct"))
        {
            //delete product from database        
            url = deleteProduct(request, response);
        }
        else        
        {
            //default displayProducts
            url = displayProducts(request, response);
        }
        
        //forward to the view
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    //displayProducts for Product Maintenance
        //make a collection and return in session object to display
    private String displayProducts(HttpServletRequest request,
                                   HttpServletResponse response)
                                   throws IOException
    {        
        //make a collection of all products in database
        List<Product> allProducts = ProductDB.selectProducts();
        
        //create string for productMaintenance url
        //String url;
        
        //if allProducts is not null, but is less than or equal to 0, set to null
        if(allProducts != null)
        {
            if (allProducts.size() <= 0)
            {
                allProducts = null;
            }
        }       
        
        //request into session object, set session attribute allProducts, return url
        HttpSession session = request.getSession();
        session.setAttribute("allProducts", allProducts);
        
        Product p = new Product(); 
        session.setAttribute("product", p);
        
        //url = "/admin/productMaintenance.jsp";
        return PRODUCT_MAINTENANCE_URL;
    }
    
    //display form for new product
    private String newForm(HttpServletRequest request, 
                           HttpServletResponse response) 
                           throws IOException, ServletException
    {
        //String url = "/admin/product_form.jsp";
        return NEW_PRODUCT_FORM_URL;
    }
    
    //display confirm for delete product
    private String deleteForm(HttpServletRequest request, 
                           HttpServletResponse response) 
                           throws IOException, ServletException
    {
        //session object to store productID of object
        HttpSession session = request.getSession();
        
        String productCode = request.getParameter("code");
        Product p = ProductDB.selectProduct(productCode);
        request.setAttribute("product", p);
        
        //need productID for deleteProduct
        session.setAttribute("product", p);
        
        //String url = "/admin/delete_form.jsp";
        return DELETE_FORM_URL;
    }
    
    //get the product and display in edit form
    private String editForm(HttpServletRequest request, 
                            HttpServletResponse response) 
                            throws IOException, ServletException
    {
        //String url = newForm(request, response);
        
        //session object to store productID of object
        HttpSession session = request.getSession();
        
        String productCode = request.getParameter("code");
        Product p = ProductDB.selectProduct(productCode);
        request.setAttribute("product", p);
        
        //need productID for updateProduct        
        session.setAttribute("product", p);
        
        return EDIT_PRODUCT_FORM_URL;
    }
    
    //method to insert product into database from new product form
    private String insertProduct(HttpServletRequest request, 
                                 HttpServletResponse response) 
                                 throws IOException, ServletException
    {
        HttpSession session = request.getSession();
 
        String productCode = request.getParameter("code");
        String productDescription = request.getParameter("description");
        String productPriceString = request.getParameter("price");
        Double productPrice = Double.parseDouble(productPriceString);
        
        Product p = new Product();
        p.setCode(productCode);
        p.setDescription(productDescription);
        p.setPrice(productPrice);

        //Product p = (Product) session.getAttribute("product"); 
        //session.setAttribute("product", p);

        String url = null;
        String message;
        
        //check that product doesn't already exist first
        if (ProductDB.selectProduct(p.getCode()) == null)
        {
            ProductDB.insertProduct(p);
            request.setAttribute("product", p);
            message = "The following product was Added to Database: <br>";
            request.setAttribute("message", message);
            url = SUCCESS_URL;
        }   
        else if (ProductDB.selectProduct(p.getCode()) != null)
        {
            message = "This Product Code already exists. <br>"
                    + "Please enter another product code.";
            request.setAttribute("message", message);
            request.setAttribute("product", p);
            url = newForm(request, response);
        } 
        
        return url;        
    }
    
    //editProduct for Product Maintenance
        //show editing form
    private String updateProduct(HttpServletRequest request,
                               HttpServletResponse response)
                               throws IOException, ServletException
    {        
        
        String productCode = request.getParameter("code");
        String productDescription = request.getParameter("description");
        String productPriceString = request.getParameter("price");
        Double productPrice = Double.parseDouble(productPriceString);
        
        //get product.ID from session object
        HttpSession session = request.getSession();
        
        Product oldProduct = (Product) session.getAttribute("product");
        //String productIDString = request.getParameter("ID");
        //Long productID = Long.parseLong(productIDString);
        
        Product p = new Product();
        p.setCode(productCode);
        p.setDescription(productDescription);
        p.setPrice(productPrice);        
        p.setProductID(oldProduct.getProductID());
        
        String url = null;
        String message;
        
        //update product to database
        
        ProductDB.updateProduct(p);
        message = "The following product was Updated in Database: <br>";
        request.setAttribute("message", message);
        request.setAttribute("product", p);
        url = SUCCESS_URL;  
        
        return url;
        
    }

    //deleteProduct for Product Maintenance
    private String deleteProduct(HttpServletRequest request,
                               HttpServletResponse response)
                               throws IOException, ServletException
    {
        //get product object for productID
        HttpSession session = request.getSession();
        
        Product product = (Product) session.getAttribute("product");
        
        //Product p = ProductDB.selectProduct(productCode);
        request.setAttribute("product", product);
               
        String message = "The following product Deleted from Database: <br>";
        request.setAttribute("message", message);
        
        ProductDB.deleteProduct(product);
        
        return SUCCESS_URL;
    }    
}