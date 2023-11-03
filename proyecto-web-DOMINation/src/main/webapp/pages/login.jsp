<%-- 
    Document   : inicio
    Created on : 21 sept 2023, 9:29:58
    Author     : giann
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inicia Sesión</title>
        <link rel="stylesheet" href="css/estiloLog.css">
    </head>

<body>
    <h1 class="domiTit"><del>DO  MI </del> Nation </h1>
    <h2 class="fuentePrinc centrarEnPag">Ingresar con tus datos</h2>
    <form action="ingresar" method="post" >
        <div class= "centrarEnPag" >

            <label class="fuenteMin" for="user">Usuario:</label> <input type="text" id="user" name="user">
            <br/><br/>
            <label class="fuenteMin" for="pass">Clave:</label> <input type="password" id="pass" name="pass">
            <br/><br/>
            <button type="submit" >Enviar</button>

        </div>
    </form>

</body>
</html>