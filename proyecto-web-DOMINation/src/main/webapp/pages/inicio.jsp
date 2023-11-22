<%-- 
    Document   : inicio
    Created on : 16 oct 2023, 21:15:13
    Author     : giann
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/inicio.css">
    <title>inicio</title>
</head>
<c:import url ="../navbar.jsp" />
<body>

    <div class="elcontainer">
        <div class="container-inicial">
            <h1>Bienvenido/a ${userLogueado.nomUsuario}</h1>
        </div>
        <div><%--contenedor que mantendrá objetos de tipo salasDeEnsayo --%>
            <c:choose>
                <c:when test="${userLogueado.getRol() == 'administrador'}">
                    <br><br><br>
                    <div class="centrarEnPag">
                        
                            <a class="botoncin" href="#"><button type="submit">Mostrar Usuarios</button></a>
                            <a class="botoncin" href="#"><button type="submit">Mostrar Sedes</button></a>.
                            
                    </div>
                    <br><br><br>
                </c:when>
                <c:when test="${userLogueado.getRol() == 'prestador'}">
                    <br><br><br>
                    <div class="centrarEnPag">
                        <c:if test = "${Exito==true}">
                            <br><br><br>
                            <h1>${mensajeExito}</h1>
                            <br><br><br>
                        </c:if>
                            <a class="botoncin" href="sedes"><button>Crear Sede</button></a>
                            <a class="botoncin" href="#"><button>Editar Sede</button></a>.
                            <a class="botoncin" href="#"><button>Eliminar Sede</button></a>.
                            
                    </div>
                    <br><br><br>
                </c:when> <%-- --%>   
                <c:when test="${userLogueado.getRol() == 'cliente'}">
                    <h1 style="color: white">Acá deberían mostrarse las sedes...</h1>
                </c:when> <%-- --%>   
            </c:choose>
        </div>
    </div>

</body>
<c:import url ="../footer.jsp" />
</html>
