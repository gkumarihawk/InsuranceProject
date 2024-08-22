<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Result</title>
    </head>
    <body>
        <% if (request.getAttribute("error") != null && !request.getAttribute("error").toString().isEmpty()) { %>
            <h3 style="color: red;"><%= request.getAttribute("error") %></h3>
        <% } else { %>
            <h3 style="color: green;">Success!</h3>
            <div>Id.: <span><%= request.getAttribute("id") %></span></div>
            <div>Status: <span><%= request.getAttribute("status") %></span></div>
            <div>Charge id.: <span><%= request.getAttribute("chargeId") %></span></div>
            <div>Balance transaction id.: <span><%= request.getAttribute("balance_transaction") %></span></div>
        <% } %>
        <a href="/checkout">Checkout again</a>
    </body>
</html>
