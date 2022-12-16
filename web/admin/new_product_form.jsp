<!--
    Document   : admin edit product
    Created on : 08.05.22
    @author incomingWill
    CPS 316 Final Project
-->

<jsp:include page="/includes/head.jsp" />
<jsp:include page="/includes/column_left_admin.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Taglib directive that specifies the custom tag library, also in header -->
<%@ taglib prefix="iw" uri="/WEB-INF/incomingwill.tld" %>


<!-- begin middle column -->

<section id="admin">
    
<!-- Add New -->

<form action="newProduct" method="post">
                
<h1>
    Add New Product
</h1>

<p><iw:ifEmptyMark field=""/> marks required fields</p>

    <label>Code:</label>
    <input type="text" name="code" value="<c:out value='${product.code}' />">
    <iw:ifEmptyMark field="${product.code}"/><br>

    <label>Description:</label>
    <input type="text" name="description" value="<c:out value='${product.description}' />">
    <iw:ifEmptyMark field="${product.description}"/><br>

    <label>Price:</label>
    <input type="text" name="price" value="<c:out value='${product.price}' />">    
    <iw:ifEmptyMark field="${product.price}"/><br>
    
    <input type="submit" value="Save Product" />
<!-- these Form tags don't force a secure connection -->
        
<!-- message to display if product already exists in database -->
<p><i>${message}</i></p>

</form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/foot.jsp" />