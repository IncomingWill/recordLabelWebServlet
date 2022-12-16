<!--
    Document   : admin product maintenance
    Created on : 08.05.22
    @author incomingWill
    CPS 316 Final Project
-->

<jsp:include page="/includes/head.jsp" />
<jsp:include page="/includes/column_left_admin.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section id="admin">

<form action="<c:url value='/maintenanceController/displayProducts'/>" method="post">
</form>
    
<h1>Products:</h1>

<c:if test="${allProducts == null}">
    <p>There are no products to maintain.</p>
</c:if>


<c:if test="${allProducts != null}">
    
<table>

<tr>
  <td><b>Code</b></td>
  <td><b>Description</b></td>
  <td><b>Price</b></td>
  <td><b></b></td> <!-- empty column header for edit column -->
  <td><b></b></td> <!-- empty column header for delete column -->
</tr>

<!-- for each product in database, print row with Code, Description, Price
        as well as Edit and Delete links -->
<c:forEach var="product" items="${allProducts}">
<tr>
  <td>
      ${product.code}
  </td>
  <td>
      ${product.description}
  </td>
  <td>
      ${product.priceCurrencyFormat}
  </td>
  <td>
      <a href="edit?code=<c:out value='${product.code}' />">Edit</a>        
  </td>      
  
  <!-- non-breaking space to separate buttons -->
  &nbsp;&nbsp;&nbsp;
  
  <td>
      <a href="delete?code=<c:out value='${product.code}' />">Delete</a>
  </td>
</tr>
</c:forEach>

</table>
</c:if>

<!-- To new from via maintenance controller -->
<form action="<c:url value='/maintenanceController/new'/>" method="post">
    <input type="submit" value="New Product">
</form>    

<!-- go back to admin menu button -->
<form action="<c:url value='/admin'/>" method="post">
    <input type="submit" value="Back to Admin Menu">
</form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/foot.jsp" />