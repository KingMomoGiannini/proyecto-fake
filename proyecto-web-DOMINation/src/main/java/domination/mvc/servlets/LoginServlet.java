/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.servlets;


import domination.DAO.AdministradorDAO;
import domination.DAO.ClienteDAO;
import domination.DAO.DAO;
import domination.DAO.DomicilioDAO;
import domination.DAO.PrestadorDAO;
import domination.DAO.SedeDAO;
import domination.DAO.UsuarioDAO;
import domination.mvc.model.Usuario;
import domination.mvc.model.Administrador;
import domination.mvc.model.Domicilio;
import domination.mvc.model.Sede;
import domination.mvc.model.UsuarioCliente;
import domination.mvc.model.UsuarioPrestador;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giann
 */
public class LoginServlet extends HttpServlet {
    
    private DAO<UsuarioPrestador,Integer> prestDAO;
    private DAO<UsuarioCliente,Integer> cliDAO;
    private DAO<Sede,Integer> sedeDAO;
    private DAO<Domicilio,Integer> domDAO;
    private DAO<Usuario,Integer> userDAO;
    
   
    @Override
    public void init() throws ServletException{
        prestDAO = new PrestadorDAO();
        cliDAO = new ClienteDAO();
        sedeDAO = new SedeDAO();
        domDAO = new DomicilioDAO();
        userDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
           req.getRequestDispatcher("pages/login.jsp").forward(req, resp);//redirige el servlet a el JSP del form.        
        }
        catch (Exception ex){
            resp.sendError(500,"no anda bien esto en LoginServlet\n"+ ex.getMessage());
        }

    }    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Usuario elUser =null;
        Administrador elAdmin = null;
        String user = req.getParameter("user");
        String password = req.getParameter("pass");
        
        if(user.equals("admin")){
            elAdmin = new AdministradorDAO().autenticar(user, password);
        }
        else {
            List<Usuario> listaUsers = new LinkedList();
            try {
                for (UsuarioPrestador prestador : prestDAO.getAll()) {
                    System.out.println(prestador.getIdUsuario());
                    listaUsers.add(prestador);
                }
                for (UsuarioCliente cliente : cliDAO.getAll()) {
                    System.out.println(cliente.getIdUsuario());
                    listaUsers.add(cliente);
                }
            } catch (Exception ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Usuario elUsuario : listaUsers) {
                if (((elUsuario.getNomUsuario().equals(user))&&(elUsuario.getPassword().equals(password))) && (elUsuario instanceof UsuarioPrestador)) {
                    elUser = (UsuarioPrestador)elUsuario;
                   
                }
                else if(((elUsuario.getNomUsuario().equals(user))&&(elUsuario.getPassword().equals(password)))&& (elUsuario instanceof UsuarioCliente)){
                    elUser = (UsuarioCliente) elUsuario;
                }
            }
            
        }
        
        if ((elUser != null)&&(elUser instanceof UsuarioPrestador)) {//Si el user existe
            
            UsuarioPrestador elUserLog = (UsuarioPrestador) elUser;
            List<Sede> lasSedesUsuario = new LinkedList();
            List<Domicilio> domiciliosSedes = new LinkedList();
            Domicilio dom = null;
            Sede laSede = null;
            try {
                for (Sede sede : sedeDAO.getAll()) {
                    if (sede.getIdPrestador() == elUserLog.getIdPrestador()) {
                        lasSedesUsuario.add(sede);
                        for (Domicilio domicilioSede : domDAO.getAll()) {
                            if (domicilioSede.getIdSucursal() == sede.getIdSede()) {
                                domiciliosSedes.add(domicilioSede);
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            HttpSession laSesion = req.getSession(); //Creamos una sesion
            laSesion.setMaxInactiveInterval(3600); //Le damos un maximo de tiempo en segundos(1hr)
            laSesion.setAttribute("sedesDelUsuario", lasSedesUsuario);
            laSesion.setAttribute("domiciliosDeSedes", domiciliosSedes);
            laSesion.setAttribute("userLogueado",elUserLog);//
            laSesion.setAttribute("elDom", dom);
            laSesion.setAttribute("laSede", laSede);
            resp.sendRedirect(req.getContextPath()+"/inicio");//redirigimos al usuario a su pagina de inicio
        }
        
        else if ((elUser != null)&&(elUser instanceof UsuarioCliente)) {//Si el user existe lo ligamos a la sesion
            List<Sede> lasSedesUsuario = new LinkedList();
            List<Domicilio> domiciliosSedes = new LinkedList();
            Domicilio dom = null;
            Sede laSede = null;
            try {
                for (Sede sede : sedeDAO.getAll()) {
                    lasSedesUsuario.add(sede);
                }
                for (Domicilio domicilioSede : domDAO.getAll()) {
                    domiciliosSedes.add(domicilioSede);
                }
            } catch (Exception ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            UsuarioCliente elUserLog = (UsuarioCliente) elUser;
            HttpSession laSesion = req.getSession(); //Creamos una sesion
            laSesion.setMaxInactiveInterval(3600); //Le damos un maximo de tiempo en segundos(1hr)
            laSesion.setAttribute("sedesDelUsuario", lasSedesUsuario);
            laSesion.setAttribute("domiciliosDeSedes", domiciliosSedes);
            laSesion.setAttribute("elDom", dom);
            laSesion.setAttribute("laSede", laSede);
            laSesion.setAttribute("userLogueado",elUserLog);//
            resp.sendRedirect(req.getContextPath()+"/inicio");//redirigimos al usuario a su pagina de inicio
        }
        
        else if (elAdmin != null) {//Si el admin existe
            List<Sede> lasSedesUsuario = new LinkedList();
            List<Domicilio> domiciliosSedes = new LinkedList();
            
            List<Usuario> usuarios = new LinkedList();
            List<UsuarioPrestador> prestadores = new LinkedList();
            List<UsuarioCliente> clientes = new LinkedList();
            Domicilio dom = null;
            Sede laSede = null;
            try {
                for (Sede sede : sedeDAO.getAll()) {
                    lasSedesUsuario.add(sede);
                }
                for (Domicilio domicilioSede : domDAO.getAll()) {
                    domiciliosSedes.add(domicilioSede);
                }
                for (Usuario usuario : userDAO.getAll()) {
                    for (UsuarioCliente cliente : cliDAO.getAll()) {
                        if (usuario.getIdUsuario()==cliente.getIdUsuario()) {
                            usuarios.add(cliente);
                        }
                    }
                    for (UsuarioPrestador prestador : prestDAO.getAll()) {
                        if (usuario.getIdUsuario()==prestador.getIdUsuario()) {
                            usuarios.add(prestador);
                        }
                    }
                }
                for (Usuario usuario : usuarios) {
                    System.out.println(usuario.getRol());
                }
            } catch (Exception ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            HttpSession laSesion = req.getSession(); //Creamos una sesion
            laSesion.setMaxInactiveInterval(3600); //Le damos un maximo de tiempo en segundos(1hr)
            laSesion.setAttribute("sedesDelUsuario", lasSedesUsuario);
            laSesion.setAttribute("domiciliosDeSedes", domiciliosSedes);
            laSesion.setAttribute("usuarios", usuarios);
            laSesion.setAttribute("userLogueado",elAdmin);//
            laSesion.setAttribute("elDom", dom);
            laSesion.setAttribute("laSede", laSede);
            resp.sendRedirect(req.getContextPath()+"/inicio");//redirigimos al usuario a su pagina de inicio
        } 
        else { //Sino mostramos el mensaje de error
            req.setAttribute("hayError", true);//Si hay un error
            req.setAttribute("mensajeError", "Usuario o contraseña incorrectos.");//Este sera el mensaje de error 
            doGet(req,resp);
        }
        
    }

}
