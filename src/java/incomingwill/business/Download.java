
package incomingwill.business;

/*
 *  Document   : Download
 *  Created on : 08.05.22
 *  @author incomingWill
 *  CPS 316 Final Project
*/

import java.util.Date;
import java.io.Serializable;

public class Download implements Serializable 
{

    private Long downloadID;
    private User user;
    private Date downloadDate;
    private String productCode;

    public Download() 
    {
        user = null;
        downloadDate = new Date();
        productCode = "";
    }

    public Long getDownloadId() 
    {
        return downloadID;
    }

    public void setDownloadId(Long downloadID) 
    {
        this.downloadID = downloadID;
    }

    public void setUser(User user) 
    {
        this.user = user;
    }

    public User getUser() 
    {
        return user;
    }

    public void setDownloadDate(Date date) 
    {
        downloadDate = date;
    }

    public Date getDownloadDate() 
    {
        return downloadDate;
    }

    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }

    public String getProductCode() 
    {
        return productCode;
    }
}