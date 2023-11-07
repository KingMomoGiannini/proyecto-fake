<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/navbar.css"/>
<nav class="header">
    <div class="logo">
        <img src="img/logo.png" alt="LogoDomi">
    </div>
    <div>
        <ul class="loenlace">
            <li><a href="${pageContext.request.contextPath}">Inicio</a></li>
            <li><a href="#">Nosotros</a></li>
        </ul>
    </div>
    <div>
        <del class="eltitulo">DO MI</del><b class="eltitulo color"> Nation</b>
    </div>
    <div>
            <c:choose>
                <c:when test="${userLogueado == null}">
                    <a class="botoncin" href="ingresar"><button type="submit">Ingresar</button></a>
                    <a class="botoncin" href="registrarse"><button type="submit">Registrarse</button></a>
                </c:when>
                <c:otherwise>
                    <a class="botoncin" href="logout"><button type="submit">Cerrar Sesion</button></a>
                </c:otherwise>
            </c:choose>
    </div>
</nav>

