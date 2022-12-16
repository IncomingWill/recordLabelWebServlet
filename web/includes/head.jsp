<!--
    Document   : head.jsp
    Created on : 08.05.22
    @author incomingWill
    CPS 316 Final Project
-->

<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Taglib directive that specifies the custom tag library -->
<%@ taglib prefix="mma" uri="/WEB-INF/incomingwill.tld" %>
<!doctype html>

<html>

<head>
    <meta charset="UTF-8">
    <title>IncomingWill Records</title>
    <link rel="shortcut icon" href="<c:url value='/images/favicon.ico'/>">   
    <link rel="stylesheet" href="<c:url value='/Styles/main.css'/> ">   
    <!-- changed url, could download package from their github, but I didn't -->
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
</head>

<body>
    <header>
        <img src="<c:url value='/images/logo.jpg'/>"
            alt="IncomingWill Records Logo" width="125">
        <h1>IncomingWill Records</h1>
        <h2>Quality Sounds Incoming!</h2>
    </header>
            <nav id="nav_bar">
                <ul>
                    <li><a href="<c:url value='/admin'/>">
                            Admin</a></li>
                    <li><a href="<c:url value='/user/deleteCookies'/>">
                            Delete Cookies</a></li>
                    <li><a href="<c:url value='/order/showCart'/>">
                            Show Cart</a></li>
                </ul>
            </nav>