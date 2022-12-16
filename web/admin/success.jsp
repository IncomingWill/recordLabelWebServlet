<!--
    Document   : success add / edit product
    Created on : 08.05.22
    @author incomingWill
    CPS 316 Final Project
-->

<jsp:include page="/includes/head.jsp" />
<jsp:include page="/includes/column_left_admin.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section id="admin">
    
<!-- confirmation message to display-->
<h1><i>${message}</i></h1>
    
<label>Code:</label>
<span>${product.code}</span><br>
<label>Description:</label>
<span>${product.description}</span><br>
<label>Price:</label>
<span>${product.priceCurrencyFormat}</span><br>

<!-- return to display products -->
<form action="<c:url value='/maintenanceController/displayProducts'/>" method="post">
    <input type="submit" value="Back to Menu">
</form>

    
</section>
<!-- end middle column -->

<jsp:include page="/includes/foot.jsp" />