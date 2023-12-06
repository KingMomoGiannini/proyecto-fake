<%-- 
    Document   : formReservas
    Created on : 5 dic 2023, 15:53:20
    Author     : Seba
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> 
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../css/formSede.css"/>
        <title>Reservas</title>
    </head>
    <body style="background-color: black">
        <c:import url="../navbar.jsp"/>
        <div class="elcontainer">
            
            <div class="container-inicial">
                <h1>Formulario de Reserva</h1>
            </div>
            <c:if test="${userLogueado.rol eq 'cliente'}">
                <br><br><br>
                <form action="reservas" method="post">
                    <input type="hidden" name="action" value="${action}">
                    <c:choose>
                        <c:when test="${action eq 'create'}">
                            
                            <input type="hidden" name="idCliente" value="${userLogueado.getIdCliente()}">
                            <input type="hidden" name="idSala" value="${laSala.getIdSala()}">
                            <div class="centrarEnPag">
                                <div class="inter-texto">
                                    <label class="fuenteMin" for="fecha">Fecha:
                                    <input type="date" class="form-control fuente-mas-grande" id="fecha" name="fecha" data-provide="datepicker"></label>

                                    <label class="fuenteMin" for="horaInicio">Hora de Inicio:
                                    <input type="time" class="form-control fuente-mas-grande" id="horaInicio" name="horaInicio"></label>

                                    <label class="fuenteMin" for="horaFin">Hora de Fin:
                                    <input type="time" class="form-control fuente-mas-grande" id="horaFin" name="horaFin"></label>
                                </div>
                            </div>
                            <div class="centrarEnPag"><br><br><br>
                                <button class="botoncin" type="submit" name="confirmDelete" value="true">Reservar</button>
                                <button class="botoncin" type="submit" name="cancelDelete" value="true">Cancelar</button>
                            </div>
                        </c:when>
                    </c:choose> 
                </form>
            </c:if>
           
        </div>
        <c:import url="../footer.jsp"/>
    </body>
</html>
