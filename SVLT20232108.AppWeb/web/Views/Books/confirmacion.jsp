<%-- 
    Document   : confirmacion
    Created on : 21 ago 2023
    Author     : Steven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmacion</title>
    </head>
    <body>
        <h1><%=request.getAttribute("confirmacion")%></h1>
         <a href="Book" class="waves-effect waves-light btn blue">Aceptar</a>    
    </body>
</html>
