
package incomingwill.business;

/*
 *  Document   : index
 *  Created on : 08.05.22
 *  @author incomingWill
 *  CPS 316 Final Project
*/

import java.text.NumberFormat;
import java.io.Serializable;

public class Product implements Serializable
{

    private String code;
    private String description;
    private double price;
    private Long productID;

    //default contstuctor, zero-argument for JavaBean
    public Product() 
    { }
    
    //overloaded constructor for unknown product
    public Product(String description)
    {
    	this.description = description;
    }
    
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setPrice(double price) 
    {
        this.price = price;
    }

    public double getPrice() 
    {
        return price;
    }
    
    public void setProductID(long productID)
    {
        this.productID = productID;
    }
    
    public long getProductID()
    {
        return productID;
    }

    //price format method
    public String getPriceCurrencyFormat() 
    {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
    
    //get artist name
    public String getArtistName()
    {
        String artistName = description.substring(
                            0, description.indexOf(" - "));
        return artistName;
    }
    
    //get album name
    public String getAlbumName()
    {
        String albumName = description.substring(
                            description.indexOf(" - ") + 3);
        return albumName;
    }
            
    //get image URL
    public String getImageURL()
    {
        String imageURL = "/CPS316Final/images/" + code + "_cover.jpg";
        return imageURL;
    }
    
    //get product type
    public String getProductType()
    {
        return "Compact Disc";
    }

}