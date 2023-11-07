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
            <h1>Bienvenido/a ${userLogueado.nombre}</h1>
        </div>
        <%-- <c:if test="${userLogueado}"> si el user logueado no es un admin --%>
        <div><%--contenedor que mantendrá objetos de tipo salasDeEnsayo --%>
            
        </div>
    </div>

</body>
<c:import url ="../footer.jsp" />
</html>
