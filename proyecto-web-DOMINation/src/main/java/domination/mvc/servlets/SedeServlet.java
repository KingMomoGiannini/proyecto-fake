/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.servlets;

import domination.DAO.DAO;
import domination.DAO.DomicilioDAO;
import domination.DAO.SedeDAO;
import domination.mvc.model.Domicilio;
import domination.mvc.model.Sede;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Seba
 */
public class SedeServlet extends HttpServlet {
    
    
    private DAO<Sede, Integer> laSedeDAO;
    private DAO<Domicilio, Integer> elDomDAO;

    @Override
    public void init() throws ServletException {
         laSedeDAO = new SedeDAO();
         elDomDAO = new DomicilioDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
        String destino;
        String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/sedes"
        pathInfo = pathInfo == null ? "" : pathInfo;
        req.setAttribute("action", "create");

        switch (pathInfo) {
            case "/edit":
                int idSedeEdit = Integer.parseInt(req.getParameter("id")); 
                System.out.println("Valor de idParam: " + idSedeEdit);
                Sede laSedeEdit = laSedeDAO.getByID(idSedeEdit);
                System.out.println(laSedeEdit.getNombre());
                req.setAttribute("laSede", laSedeEdit);
                req.setAttribute("action", "update");
                destino = "/pages/formSedes.jsp";
                break;
            case "/delete":
                int idSedeDelete = Integer.parseInt(req.getParameter("id")); // Cambiado el nombre de la variable
                Sede laSedeDelete = laSedeDAO.getByID(idSedeDelete);
                req.setAttribute("laSede", laSedeDelete);
                req.setAttribute("action", "delete");
                destino = "pages/inicio.jsp"; // Utiliza el mismo formulario que el de creación
                break;
            default:
                req.setAttribute("laSede", new Sede()); // Crea una nueva instancia de Sede para el formulario de creación
                destino = "pages/formSedes.jsp";
                break;
        }

        req.getRequestDispatcher(destino).forward(req, resp);
    } catch (Exception ex) {
        ex.printStackTrace();
        resp.sendError(500, ex.getMessage());
    }
}
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            switch (action) {
                case "create":
                    createSede(req);
                    break;
                case "update":
                    updateSede(req);
                    break;
                case "delete":
                    deleteSede(req);
                    break;
            }
        resp.sendRedirect(req.getContextPath()+"/inicio");
//        req.getRequestDispatcher("pages/inicio.jsp").forward(req, resp);
        } catch (Exception ex) {
            resp.sendError(500, "error en parseando en doPost SedeServlet");

        }
    }
    
    private void createSede(HttpServletRequest req) throws Exception {
        Sede laSede = obtenerSedeDesdeRequest(req);
        laSedeDAO.create(laSede);
        Domicilio elDom = obtenerDomicilioDesdeRequest(req, laSede.getIdSede());
        elDomDAO.create(elDom);
        setAttributesForSuccess(req, "La sede ha sido creada exitosamente", laSede,elDom);
    }

    private void updateSede(HttpServletRequest req) throws Exception {
        int idSede = Integer.parseInt(req.getParameter("idSede"));
        Sede laSede = obtenerSedeDesdeRequest(req);
        laSede.setIdSede(idSede);
        laSedeDAO.update(laSede);
        Domicilio elDom = obtenerDomicilioDesdeRequest(req, idSede);
        elDomDAO.update(elDom);
        setAttributesForSuccess(req, "La sede ha sido actualizada exitosamente", laSede,elDom);
    }

    private void deleteSede(HttpServletRequest req) throws Exception {
        int idSede = Integer.parseInt(req.getParameter("idSede"));
        laSedeDAO.delete(idSede);
        setAttributesForSuccess(req, "La sede ha sido eliminada exitosamente", null,null);
    }

    private Sede obtenerSedeDesdeRequest(HttpServletRequest req) {
        String nomSede = req.getParameter("nomSede");
        int cantSalas = Integer.parseInt(req.getParameter("salas"));
        String telefono = req.getParameter("celular");
        int horaInicio = Integer.parseInt(req.getParameter("horaInicio"));
        int horaCierre = Integer.parseInt(req.getParameter("horaCierre"));
        int idPrestador = Integer.parseInt(req.getParameter("idPrestador"));//Recupero el ID del prestador

        return new Sede(nomSede, cantSalas, idPrestador, horaInicio, horaCierre, telefono);
    }
        
    private Domicilio obtenerDomicilioDesdeRequest(HttpServletRequest req, int idSede) {
        String provincia = req.getParameter("provincia");
        String localidad = req.getParameter("localidad");
        String partido = req.getParameter("partido");
        String calle = req.getParameter("calle");
        String altura = req.getParameter("altura");

        return new Domicilio(provincia, localidad, partido, calle, altura, idSede);
    }
    
    private void setAttributesForSuccess(HttpServletRequest req, String mensaje, Sede laSede, Domicilio elDom) {
        req.setAttribute("Exito", true);
        req.setAttribute("mensajeExito", mensaje);
        req.setAttribute("HaySede", true);
        req.setAttribute("sede", laSede);
        req.setAttribute("domicilio", elDom);
    }
    
    
}
