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
            <br><br><br>
            <form action="sedes" method="post">
                <c:choose>
                    <c:when test="${userLogueado.rol eq 'prestador'}">
                        <input type="hidden" name="idPrestador" value="${userLogueado.getIdPrestador()}">
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="idPrestador" value="${elPrestador.getIdPrestador()}">
                    </c:otherwise>
                </c:choose>
                <input type="hidden" name="idSede" value="${laSede.getIdSede()}">
                <input type="hidden" name="idDom" value="${elDom.getId()}">
                <input type="hidden" name="action" value="${action}">
                <input type="hidden" name="Exito" value=false>
                <c:choose>
                    <c:when test="${action eq 'delete'}">
                
                        <div class="inter-texto">
                            <label class="fuenteMin" for="sede">Sede a eliminar:</label>
                            <input class="text-box-ajuste" type="text" value="${laSede.nombre}" readonly>
                            <input class="text-box-ajuste" type="text" value="${laSede.getIdSede()}" readonly>
                            <label class="fuenteMin" for="sede">Usuario al que le pertenece:</label>
                            <input class="text-box-ajuste" type="text" value="id de Prestador: ${elPrestador.getIdPrestador()}" readonly>
                            <input class="text-box-ajuste" type="text" value="id de Usuario: ${elPrestador.getIdUsuario()}" readonly>
                            <input class="text-box-ajuste" type="text" value="Nombre y apellido: ${elPrestador.getNombre()} ${elPrestador.getApellido()}" readonly>
                            <br>
                            <h3 class="fuentePrincFondo">¿Está seguro que desea eliminar esta sede?</p>
                            
                        </div>
                            <br><br>
                        <div class="centrarEnPag">
                            <button class="botoncin" type="submit" name="confirmDelete" value="true">Eliminar</button>
                            <button class="botoncin" type="submit" name="cancelDelete" value="true">Cancelar</button>
                        </div>
                    </c:when>
                    <c:otherwise>
                        
                            <h3 class="fuentePrincFondo">Datos de la sede</h3>
                                <div class="inter-texto">
                                    <label class="fuenteMin" for="nomSede">Nombre del local:</label> <input class="text-box-ajuste" type="text" name="nomSede" id="nombre" value="${laSede.nombre}">
                                    <label class="fuenteMin" for="salas">Cantidad de salas:</label>
                                        <select name="salas" id="salas" value =value="${laSede.cantSalas}">
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
                                    <label class="fuenteMin" for="celular">Telefono/Celular:</label> <input class="text-box-ajuste" type="text" id="cel" name="celular" value="${laSede.telefono}">
                                    <label class="fuenteMin" for="horaInicio">Apertura del local:</label>
                                        <select name="horaInicio" id="horaInicio" value="${laSede.horaInicio}">
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
                                        <select name="horaCierre" id="horaFin" value="${laSede.horaFin}">
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
                                    <label class="fuenteMin" for="calle">Calle:</label> <input class="text-box-ajuste" type="text" id="calle" name="calle" value="${elDom.calle}">
                                    <label class="fuenteMin" for="altura">altura:</label> <input class="text-box-ajuste" type="text" id="altura" name="altura" value="${elDom.altura}">
                                    <label class="fuenteMin" for="localidad">Localidad:</label> <input class="text-box-ajuste" type="text" id="localidad" name="localidad" value="${elDom.localidad}">                       
                                    <label class="fuenteMin" for="partido">Partido:</label> <input class="text-box-ajuste" type="text" id="partido" name="partido" value="${elDom.partido}">        
                                    <label class="fuenteMin" for="provincia">Provincia:</label> <input class="text-box-ajuste" type="text" id="provincia" name="provincia" value="${elDom.provincia}">
                                </div>

                                <br><br>

                                <div class="centrarEnPag">
                                    <button class ="botoncin" type="submit">Enviar</button>
                                    <button class ="botoncin" type="reset" >Limpiar Formulario</button>
                                    <button class ="botoncin" type="submit" name="cancelDelete" value="true">Cancelar</button>
                                </div>  <br>
                            
                    </c:otherwise>    
                </c:choose>
            </form>
        </div>
        <c:import url="../footer.jsp"/>
    </body>
</html>
