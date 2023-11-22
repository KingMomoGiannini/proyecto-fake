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
            req.getRequestDispatcher("pages/formSedes.jsp").forward(req, resp);//redirige el servlet primero a el JSP del form de registro.
//            String destino;
//            String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/sedes"
//            pathInfo = pathInfo == null ? "" : pathInfo;
////              
//            switch (pathInfo) {
//                
//                case "/update": // Form de edición sede
//                    destino = "/WEB-INF/jsp/recetaEditForm.jsp";
//                    break;
//
//                default: // Ver detalle de la Sede
//                    req.setAttribute("laSede", laSedeDAO.getAll());
//                    destino = "/pages/inicio.jsp";
//            }
//            
//            req.getRequestDispatcher(destino).forward(req, resp);
        } catch (Exception ex) {
            resp.sendError(500, ex.getMessage());
        }

    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Sede laSede;
            Domicilio elDom;
            //int id;
            //String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/recetas"
            //pathInfo = pathInfo == null ? "" : pathInfo;
            //switch (pathInfo) {
               // case "/create":

                    String nomSede = req.getParameter("nomSede");
                    int cantSalas = Integer.parseInt(req.getParameter("salas"));
                    String telefono = req.getParameter("celular");
                    int horaInicio = Integer.parseInt(req.getParameter("horaInicio"));
                    int horaCierre = Integer.parseInt(req.getParameter("horaCierre"));
                    String calle = req.getParameter("calle");
                    String altura = req.getParameter("altura");
                    String localidad = req.getParameter("localidad");
                    String partido = req.getParameter("partido");
                    String provincia = req.getParameter("provincia");
                    
                    int idPrestador = Integer.parseInt(req.getParameter("idPrestador"));//Recupero el ID del prestador.

                    laSede = new Sede(nomSede,cantSalas,idPrestador,horaInicio,horaCierre,telefono);
                    laSedeDAO.create(laSede);
                    elDom = new Domicilio(provincia,localidad,partido,calle,altura,laSede.getIdSede());
                    elDomDAO.create(elDom);
                    
                    if ((laSede!=null) && (elDom!=null)) {
                        req.setAttribute("Exito", true);
                        req.setAttribute("mensajeExito","La sede ha sido creada exitosamente");
                    }
                    //break;
            //}
            resp.sendRedirect(getServletContext().getContextPath() + "/inicio");
//            resp.sendRedirect(getServletContext().getContextPath() + "/inicio");
        } catch (Exception ex) {
            resp.sendError(500, ex.getMessage());

        }
    }

}
