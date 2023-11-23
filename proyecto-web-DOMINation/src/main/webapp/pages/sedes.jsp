<h1>Detalles de Sede</h1>

<c:choose>
    <c:when test="${not empty sede}">
        <h2>${sede.nombre}</h2>
        <p><strong>ID de Sede:</strong> ${sede.getIdSede()}</p>
        <p><strong>Cantidad de Salas:</strong> ${sede.cantSalas}</p>
        <p><strong>Hora de Inicio:</strong> ${sede.horaInicio}</p>
        <p><strong>Hora de Fin:</strong> ${sede.horaFin}</p>
        <p><strong>Teléfono:</strong> ${sede.telefono}</p>

        <h3>Dirección:</h3>
        <p><strong>Provincia:</strong> ${dom.provincia}</p>
        <p><strong>Localidad:</strong> ${dom.localidad}</p>
        <p><strong>Partido:</strong> ${dom.partido}</p>
        <p><strong>Calle:</strong> ${dom.calle}</p>
        <p><strong>Altura:</strong> ${dom.altura}</p>

        <h3>Usuario que creó la Sede:</h3>
        <p><strong>ID de Usuario:</strong> ${userLogueado.getIdUsuario()}</p>
        <p><strong>Nombre de Usuario:</strong> ${userLogueado.nombre}</p>

    </c:when>
    <c:otherwise>
        <p>No hay sedes disponibles registradas </p>
    </c:otherwise>
</c:choose>
