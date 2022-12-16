<!--
    Document   : email thanks
    Created on : 08.05.22
    @author incomingWill
    CPS 316 Final Project
-->

<jsp:include page="/includes/head.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<section>
    
<h1>Thanks for joining our email list</h1>
<p>Here is the information that you entered:</p>

<label  class="no_pad_top">Email</label>
<span>${user.email}</span><br>
<label class="no_pad_top">First Name</label>
<span>${user.firstName}</span><br>
<label class="no_pad_top">Last Name</label>
<span>${user.lastName}</span><br>

</section>

<jsp:include page="/includes/column_right_news.jsp" />
<jsp:include page="/includes/foot.jsp" />