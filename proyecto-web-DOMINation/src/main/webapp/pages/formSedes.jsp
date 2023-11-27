<%-- 
    Document   : formSedes
    Created on : 10 nov 2023, 14:55:22
    Author     : giann
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar/Editar/Eliminar Sede</title>
        <<link rel="stylesheet" href="../css/formSede.css"/>
    </head>
    <body>
        <c:import url="../navbar.jsp"/>
        <div class="elcontainer">
            <div class="container-inicial">
                <h1>Registro/Edición/Eliminación de Sede</h1>
            </div>
            <form action="sedes" method="post">
                <input type="hidden" name="idPrestador" value="${userLogueado.getIdPrestador()}">
                <input type="hidden" name="idSede" value="${laSede.getIdSede()}">
                <input type="hidden" name="idDom" value="${elDom.getId()}">
                <input type="hidden" name="action" value="${action}">
                <h3 class="fuentePrincFondo">Datos de la sede</h3>
                    <div class="inter-texto">
                        <label class="fuenteMin" for="nomSede">Nombre del local:</label> <input class="text-box-ajuste" type="text" name="nomSede" id="nombre" required>
                        <label class="fuenteMin" for="salas">Cantidad de salas:</label>
                            <select name="salas" id="salas">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                            </select>
                        <label class="fuenteMin" for="celular">Telefono/Celular:</label> <input class="text-box-ajuste" type="text" id="cel" name="celular" required>
                        <label class="fuenteMin" for="horaInicio">Apertura del local:</label>
                            <select name="horaInicio" id="horaInicio">
                                <option value="1">1hs</option>
                                <option value="2">2hs</option>
                                <option value="3">3hs</option>
                                <option value="4">4hs</option>
                                <option value="5">5hs</option>
                                <option value="6">6hs</option>
                                <option value="7">7hs</option>
                                <option value="8">8hs</option>
                                <option value="9">9hs</option>
                                <option value="10">10hs</option>
                                <option value="11">11hs</option>
                                <option value="12">12hs</option>
                                <option value="13">13hs</option>
                                <option value="14">14hs</option>
                                <option value="15">15hs</option>
                                <option value="16">16hs</option>
                                <option value="17">17hs</option>
                                <option value="18">18hs</option>
                                <option value="19">19hs</option>
                                <option value="20">20hs</option>
                                <option value="21">21hs</option>
                                <option value="22">22hs</option>
                                <option value="23">23hs</option>
                                <option value="24">24hs</option>
                            </select>
                        <label class="fuenteMin" for="horaCierre">Cierre del local:</label>
                            <select name="horaCierre" id="horaFin">
                                <option value="1">1hs</option>
                                <option value="2">2hs</option>
                                <option value="3">3hs</option>
                                <option value="4">4hs</option>
                                <option value="5">5hs</option>
                                <option value="6">6hs</option>
                                <option value="7">7hs</option>
                                <option value="8">8hs</option>
                                <option value="9">9hs</option>
                                <option value="10">10hs</option>
                                <option value="11">11hs</option>
                                <option value="12">12hs</option>
                                <option value="13">13hs</option>
                                <option value="14">14hs</option>
                                <option value="15">15hs</option>
                                <option value="16">16hs</option>
                                <option value="17">17hs</option>
                                <option value="18">18hs</option>
                                <option value="19">19hs</option>
                                <option value="20">20hs</option>
                                <option value="21">21hs</option>
                                <option value="22">22hs</option>
                                <option value="23">23hs</option>
                                <option value="24">24hs</option>
                            </select>
                    </div>
                    <br>

                    <h3 class="fuentePrincFondo">Domicilio de la misma</h3> 
                    <div class="inter-texto">
                        <label class="fuenteMin" for="calle">Calle:</label> <input class="text-box-ajuste" type="text" id="calle" name="calle">
                        <label class="fuenteMin" for="altura">altura:</label> <input class="text-box-ajuste" type="text" id="altura" name="altura">
                        <label class="fuenteMin" for="localidad">Localidad:</label> <input class="text-box-ajuste" type="text" id="localidad" name="localidad">                       
                        <label class="fuenteMin" for="partido">Partido:</label> <input class="text-box-ajuste" type="text" id="partido" name="partido">        
                        <label class="fuenteMin" for="provincia">Provincia:</label> <input class="text-box-ajuste" type="text" id="provincia" name="provincia">
                    </div>

                    <br><br>

                    <div class="centrarEnPag">
                        <button class ="botoncin" type="submit">Enviar</button>
                        <button class ="botoncin" type="reset" >Limpiar Formulario</button>
                    </div>  <br>
            </form>
        </div>
        <c:import url="../footer.jsp"/>
    </body>
</html>
