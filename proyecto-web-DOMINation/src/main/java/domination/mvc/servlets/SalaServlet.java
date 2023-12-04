/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.servlets;

import domination.DAO.DAO;
import domination.DAO.SalaDAO;
import domination.DAO.SedeDAO;
import domination.mvc.model.SalaEnsayo;
import domination.mvc.model.Sede;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Seba
 */
public class SalaServlet extends HttpServlet{
    
    private DAO<SalaEnsayo,Integer> salaDAO;
    private DAO<Sede,Integer> sedeDAO;

    @Override
    public void init() throws ServletException {
        salaDAO = new SalaDAO();
        sedeDAO = new SedeDAO();
    }
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String destino="/";
            String pathInfo = req.getPathInfo(); // Obtiene la parte de la URL después de "/sedes"
            pathInfo = pathInfo == null ? "" : pathInfo;
            switch(pathInfo){
                case "/create":
                    int idSedeSala = Integer.parseInt(req.getParameter("idSede"));
                    Sede laSede = sedeDAO.getByID(idSedeSala);
                    req.setAttribute("laSede", laSede);
                    req.setAttribute("laSala", new SalaEnsayo());
                    req.setAttribute("action", "create");
                    destino+= "pages/formSalas.jsp";
                    break;
                case "/salasDisponibles":
                    idSedeSala = Integer.parseInt(req.getParameter("idSede"));
                    laSede = sedeDAO.getByID(idSedeSala);
                    List<SalaEnsayo> salas = new LinkedList();
                    for (SalaEnsayo sala : salaDAO.getAll()) {
                        if (sala.getIdSede()==laSede.getIdSede()) {
                            salas.add(sala);
                        }
                    }
                    req.setAttribute("laSede", laSede);
                    req.setAttribute("lasSalas", salas);
                    req.setAttribute("action", "read");
                    destino+= "pages/listaSalas.jsp";
                    break;
                case "/edit":
                    int idSalaEdit = Integer.parseInt(req.getParameter("idSala")); 
                    int idSedeSalaEdit = Integer.parseInt(req.getParameter("idSede")); 
                    SalaEnsayo laSalaEdit = salaDAO.getByID(idSalaEdit);
                    Sede laSedeSalaEdit = sedeDAO.getByID(idSedeSalaEdit);
                    req.setAttribute("laSala", laSalaEdit);
                    req.setAttribute("laSede", laSedeSalaEdit);
                    req.setAttribute("action", "update");
                    destino+="pages/formSalas.jsp";
                    break;
                case "/delete":
                    int idSalaDel = Integer.parseInt(req.getParameter("idSala"));
                    int idSedeSalaDel = Integer.parseInt(req.getParameter("idSede"));
                    SalaEnsayo laSalaDel = salaDAO.getByID(idSalaDel);
                    Sede laSedeSalaDel = sedeDAO.getByID(idSedeSalaDel);
                    req.setAttribute("laSala", laSalaDel);
                    req.setAttribute("laSede", laSedeSalaDel);
                    req.setAttribute("action", "delete");
                    destino+="pages/formSalas.jsp";
                    break;
                    
            }
            req.getRequestDispatcher(destino).forward(req, resp);
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.sendError(500, "error en doGet SalaServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String cancelar = null;
            String action = req.getParameter("action");
            switch (action) {
                case "create":
                    cancelar = req.getParameter("cancelDelete");
                    if (cancelar != null && cancelar.equals("true")) {
                        req.getSession().setAttribute("Exito", false);
                        break;
                    }
                    else{
                        createSala(req);
                    }
                    break;
                case "update":
                    cancelar = req.getParameter("cancelDelete");
                    if (cancelar != null && cancelar.equals("true")) {
                        req.getSession().setAttribute("Exito", false);
                        break;
                    }
                    else{
                        updateSala(req);
                    }
                    break;
                case "delete":
                    cancelar = req.getParameter("cancelDelete");
                    if (cancelar != null && cancelar.equals("true")) {
                        req.getSession().setAttribute("Exito", false);
                        break;
                    }
                    else{
                        deleteSala(req);                    
                    }
                    break;
            }

            resp.sendRedirect(req.getServletContext().getContextPath()+"/inicio");
        } catch (Exception ex) {
            resp.sendError(500, "error en doPost SalaServlet");
        }
    }
    
    private void createSala(HttpServletRequest req) throws Exception {
        SalaEnsayo laSala = obtenerSalaDesdeRequest(req);
        salaDAO.create(laSala);
        setAttributesForSuccess(req, "La Sala ha sido creada exitosamente", laSala);
    }
    
    private void updateSala(HttpServletRequest req) throws Exception {
        int idSala = Integer.parseInt(req.getParameter("idSala"));
//        int idSede = Integer.parseInt(req.getParameter("idSede"));
        SalaEnsayo laSala = obtenerSalaDesdeRequest(req);
        laSala.setIdSala(idSala);
        salaDAO.update(laSala);
        setAttributesForSuccess(req, "La sala ha sido actualizada exitosamente", laSala);
    }
    
    private void deleteSala(HttpServletRequest req) throws Exception {
        int idSala = Integer.parseInt(req.getParameter("idSala"));//Obtengo el id de la sede en el formulario
        salaDAO.delete(idSala);
        setAttributesForSuccess(req, "La sala ha sido eliminada exitosamente", null);
    }
    
    private SalaEnsayo obtenerSalaDesdeRequest(HttpServletRequest req) {
        int numSala = Integer.parseInt(req.getParameter("numSala"));
        int idSede = Integer.parseInt(req.getParameter("idSede"));
        return new SalaEnsayo(numSala,idSede);
    }
    
    private void setAttributesForSuccess(HttpServletRequest req, String mensaje, SalaEnsayo laSala) throws Exception {
        List<SalaEnsayo> lasSalasDeLaSede = new LinkedList();
        
        req.getSession().setAttribute("Exito", true);
        req.getSession().setAttribute("mensajeExito", mensaje);
        for (SalaEnsayo salaEnsayo : salaDAO.getAll()) {
            lasSalasDeLaSede.add(salaEnsayo);
        }
        req.getSession().setAttribute("lasSalas",lasSalasDeLaSede);
        
    }
}
