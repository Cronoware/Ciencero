<%-- 
    Document   : iniciarSesion
    Created on : 20/02/2017, 11:08:36 PM
    Author     : Emilio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar Sesión</title>
        <form method="GET" action="/Ciencero/iniciarSesion">
            <input id="correo" name="correo" type="text" placeholder="correo@ciencias.unam.mx">
            <input id="password" name="psswd" type="password" placeholder="contraseña">
            <button>Aceptar</button>
        </form>
        
    </head>
    <body>
        <h2><font color="#FF0000">${info}</font></h2>
    </body>

</html>
    