<%-- 
    Document   : inicio
    Created on : 20/02/2017, 11:08:36 PM
    Author     : Emilio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ciencero</title>
        <form method="GET" action="/Ciencero/getIniciarSesion">
            <button>Iniciar sesión</button>
        </form>
        <form method="GET" action="/Ciencero/getRegistro">
            <button>Registrarse</button>
        </form>
    </head>
    <body>
        <h2><font color="#FF0000">${info}</font></h2>
    </body>
</html>
