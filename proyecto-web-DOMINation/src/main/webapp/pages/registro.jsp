<%-- 
    Document   : reg-cliente
    Created on : 21 sept 2023, 9:32:14
    Author     : giann
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/estiloReg.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registrarse</title>
    </head>

    <body>   
        
        <div class="elcontainer">
            <div>
                <img class="logo" src="img/logo.png" alt="logo">
                <del class="eltitulo">DO MI</del><b class="eltitulo rojo"> Nation</b>
            </div><br>
    
            <div class="form-container"> 
            <h2 class="fuentePrinc">Registrate completando los datos solicitados</h2>
            <form action="registrarse" method="POST" >
                <h3 class="fuentePrincFondo">Datos Personales</h3>
                <div class="inter-texto">
                    <label class="fuenteMin" for="name">Nombre:</label> <input class="text-box-ajuste" type="text" name="nomCliente" id="nombre">
                    <label class="fuenteMin" for="last-name">Apellido:</label> <input class="text-box-ajuste" type="text" id="apellido" name="apeCliente">
                    <label class="fuenteMin" for="celular">Celular:</label> <input class="text-box-ajuste" type="text" id="cel" name="celular">
                </div>
                <h3 class="fuentePrincFondo">Otros datos</h3>
                <div class="inter-texto">
                    <label class="fuenteMin" for="email">Correo Electrónico</label> <input class="text-box-ajuste" type="text" id="email" name="email">        
                    <label class="fuenteMin" for="user">Nombre de usuario:</label> <input class="text-box-ajuste" type="text" id="user" name="user">
                    <br>
                    
                    <label class="fuenteMin" for="pass">Contraseña:</label> <input class="text-box-ajuste" type="password" id="pass" name="pass">
                    <label class="fuenteMin" for="pass">Confirme contraseña:</label> <input class="text-box-ajuste" type="password" id="pass" name="pass">
                </div>
                <br><br>
            
                <div class="centrarEnPag">
                    <button class ="botoncin" type="submit" >Enviar</button>
                    <button class ="botoncin" type="reset" >Limpiar Formulario</button>
                </div>  <br>
                </form> 
            </div>
        </div>    
    </body>
</html>