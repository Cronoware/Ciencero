<%-- 
    Document   : registro
    Created on : 20/02/2017, 11:08:36 PM
    Author     : Emilio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        

    <body>
        <h2><font color="#FF0000">${info}</font></h2>
        <form method="POST" action="/Ciencero/registrar">
            <input id="correo" name="correo" type="text" placeholder="correo@ciencias.unam.mx">
            <input id="password" name="psswd" type="password" placeholder="contraseña">
            <input id="nombre" name="nombre" type="text" placeholder="Nombre">
            
            <button>Aceptar</button>
        </form>
    </body>

</html>