/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.servlets;

import domination.DAO.ClienteDAO;
import domination.DAO.DAO;
import domination.DAO.ReservaDAO;
import domination.DAO.SalaDAO;
import domination.DAO.SedeDAO;
import domination.DAO.UsuarioDAO;
import domination.mvc.model.Reserva;
import domination.mvc.model.SalaEnsayo;
import domination.mvc.model.Sede;
import domination.mvc.model.Usuario;
import domination.mvc.model.UsuarioCliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Seba
 */
public class ReservaServlet extends HttpServlet {
    private DAO<Reserva, Integer> reservaDAO;
    private DAO<Sede, Integer> sedeDAO;
    private DAO<Usuario, Integer> userDAO;
    private DAO<SalaEnsayo,Integer> salaDAO;
    private DAO<UsuarioCliente,Integer> cliDAO;

    @Override
    public void init() throws ServletException {
        reservaDAO = new ReservaDAO();
        sedeDAO = new SedeDAO();
        userDAO = new UsuarioDAO();
        cliDAO = new ClienteDAO();
        salaDAO = new SalaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String destino="/";
            String pathInfo = req.getPathInfo();
            pathInfo = pathInfo == null ? "" : pathInfo;
            switch(pathInfo){
                case "/create":
                    int idSala = Integer.parseInt(req.getParameter("idSala"));
                    SalaEnsayo laSala = salaDAO.getByID(idSala);
                    Sede laSede = sedeDAO.getByID(laSala.getIdSede());
                    req.setAttribute("laSede", laSede);
                    req.setAttribute("laSala", laSala);
                    req.setAttribute("laReserva", new Reserva());
                    req.setAttribute("action", "create");
                    destino+= "pages/formReservas.jsp";
                    break;
                case "/misReservas":
                    int idCliente = Integer.parseInt(req.getParameter("idCliente"));
                    List<Reserva> reservas = new LinkedList();
                    for (Reserva reserva : reservaDAO.getAll()) {
                        if (reserva.getIdCliente() == idCliente) {
                            reservas.add(reserva);
                        }
                    }
                    req.setAttribute("lasReservas", reservas);
                    req.setAttribute("action", "read");
                    destino+= "pages/listaReservas.jsp";
                    break;
                case "/edit":
//                    int idSalaEdit = Integer.parseInt(req.getParameter("idSala")); 
//                    int idSedeSalaEdit = Integer.parseInt(req.getParameter("idSede")); 
//                    SalaEnsayo laSalaEdit = salaDAO.getByID(idSalaEdit);
//                    Sede laSedeSalaEdit = sedeDAO.getByID(idSedeSalaEdit);
//                    req.setAttribute("laSala", laSalaEdit);
//                    req.setAttribute("laSede", laSedeSalaEdit);
//                    req.setAttribute("action", "update");
                    destino+="pages/formReservas.jsp";
                    break;
                case "/delete":
//                    int idSalaDel = Integer.parseInt(req.getParameter("idSala"));
//                    int idSedeSalaDel = Integer.parseInt(req.getParameter("idSede"));
//                    SalaEnsayo laSalaDel = salaDAO.getByID(idSalaDel);
//                    Sede laSedeSalaDel = sedeDAO.getByID(idSedeSalaDel);
//                    req.setAttribute("laSala", laSalaDel);
//                    req.setAttribute("laSede", laSedeSalaDel);
//                    req.setAttribute("action", "delete");
                    destino+="pages/formReservas.jsp";
                    break;
                case "/listaReservas":
                    destino+= "pages/listaReservas.jsp";
                    break;
                    
            }
            req.getRequestDispatcher(destino).forward(req, resp);

        } catch (Exception ex) {
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
                        createReserva(req);
                    }
                    break;
                case "update":
                    cancelar = req.getParameter("cancelDelete");
                    if (cancelar != null && cancelar.equals("true")) {
                        req.getSession().setAttribute("Exito", false);
                        break;
                    }
                    else{
                        //updateSala(req);
                    }
                    break;
                case "delete":
                    cancelar = req.getParameter("cancelDelete");
                    if (cancelar != null && cancelar.equals("true")) {
                        req.getSession().setAttribute("Exito", false);
                        break;
                    }
                    else{
                        //deleteSala(req);                    
                    }
                    break;
            }

            resp.sendRedirect(req.getServletContext().getContextPath()+"/inicio");
        } catch (Exception ex) {
            
        }
    }
    
    private void createReserva(HttpServletRequest req) throws Exception {
        Reserva laReserva = obtenerReservaDesdeRequest(req);
        //SalaEnsayo laSalaDeReserva = salaDAO.getByID(laReserva.getIdSala());
        //Sede sede = sedeDAO.getByID(laSalaDeReserva.getIdSede());//para comparar las horas de inicio y fin con las de la reserva.
        if ((laReserva.getDuracion() < 0)) {
            setAttributesForSuccess(req, "Seleccione un horario valido", null);
            
        }
        else{
            reservaDAO.create(laReserva);
            setAttributesForSuccess(req, "Su reserva fue realizada exitosamente", laReserva);
        }
    }
    
    private Reserva obtenerReservaDesdeRequest(HttpServletRequest req) throws Exception {
        //Primero obtengo fecha y las horas del formulario en formato string
        String fechaStr = req.getParameter("fecha");
        String horaInicioStr = req.getParameter("horaInicio");
        String horaFinStr = req.getParameter("horaFin");
        //despues lo transformo a LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime horaInicio = LocalDateTime.parse(fechaStr + " " + horaInicioStr, formatter);
        LocalDateTime horaFin = LocalDateTime.parse(fechaStr + " " + horaFinStr, formatter);
        //calculamos la duración entre la hora de inicio y de fín 
        Duration duracionReserva = Duration.between(horaInicio, horaFin);
        //pasamos la duración a minutos
        long minutos = duracionReserva.toMinutes();
        double duracion = (double) minutos / 60;
//        double duracion = duracionReserva.toHours();
        //Seguimos obteniendo datos del formulario
        int idSala = Integer.parseInt(req.getParameter("idSala"));
        int idCliente = Integer.parseInt(req.getParameter("idCliente"));
        //calculamos el monto total de la reserva
        SalaEnsayo laSala = salaDAO.getByID(idSala);
        double monto = (laSala.getValorHora() * duracion);//multiplicamos el valor hora de la sala por el tiempo en horas.
        
        return new Reserva(duracion,idSala,idCliente,horaInicio,horaFin,monto);
    }
    
    private void setAttributesForSuccess(HttpServletRequest req, String mensaje, Reserva laReserva) throws Exception {
        List<Reserva> lasReservasNuevas = new LinkedList();
        
        req.getSession().setAttribute("Exito", true);
        req.getSession().setAttribute("mensajeExito", mensaje);
        for (Reserva reserva : reservaDAO.getAll()) {
            lasReservasNuevas.add(reserva);
        }
        req.getSession().setAttribute("lasReservas",lasReservasNuevas);
        
    }
}
