package incomingwill.tags;

/*
 *  Document   : if empty mark tag class
 *  Created on : 08.05.22
 *  @author incomingWill
 *  CPS 316 Final Project
*/

import jakarta.servlet.jsp.*;
import jakarta.servlet.jsp.tagext.*;
import java.io.*;

public class IfEmptyMarkTag extends TagSupport 
{

    private String field;
    private String color = "green";

    public void setField(String field) 
    {
        this.field = field;
    }

    public void setColor(String color) 
    {
        this.color = color;
    }

    @Override
    public int doStartTag() throws JspException 
    {
        try 
        {
            JspWriter out = pageContext.getOut();
            if (field == null || field.length() == 0) 
            {
                out.print("<font color=" + color + "> *</font>");
            }
        } 
        catch (IOException ioe) 
        {
            System.out.println(ioe);
        }
        return SKIP_BODY;
    }
}
