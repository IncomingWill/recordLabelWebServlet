<!--
    Document   : admin delete product
    Created on : 08.05.22
    @author incomingWill
    CPS 316 Final Project
-->

<jsp:include page="/includes/head.jsp" />
<jsp:include page="/includes/column_left_admin.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section id="admin">
    
<h1>Are you sure you want to delete this product?</h1>

<label>Code:</label>
<span><c:out value='${product.code}' /></span><br>
<label>Description:</label>
<span><c:out value='${product.description}' /></span><br>
<label>Price:</label>
<span><c:out value='${product.priceCurrencyFormat}' /></span><br>

<!-- add new product to database -->
<form action="<c:url value='deleteProduct'/>" method="post">
    <input type="submit" value="Yes">
</form>
    
<!-- Do not delete, return to /admin menu -->
<form action="<c:url value='/maintenanceController/displayProducts'/>" method="post">
    <input type="submit" value="No">
</form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/foot.jsp" />