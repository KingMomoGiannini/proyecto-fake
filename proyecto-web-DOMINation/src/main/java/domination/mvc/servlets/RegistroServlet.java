/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.servlets;
import domination.DAO.ClienteDAO;
import domination.DAO.DAO;
import domination.DAO.DomicilioDAO;
import domination.DAO.PrestadorDAO;
import domination.mvc.model.Domicilio;
import domination.mvc.model.Usuario;
import domination.DAO.UsuarioDAO;
import domination.mvc.model.UsuarioCliente;
import domination.mvc.model.UsuarioPrestador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *
 * @author giann
 */
/*@WebServlet(name = "RegistroServlet", value = "/registrarse")*/
public class RegistroServlet extends HttpServlet  {
    
    private DAO<Usuario,Integer> userDAO;
    private DAO<UsuarioPrestador,Integer> prestDAO;
    private DAO<UsuarioCliente,Integer> cliDAO;
    
    @Override
    public void init() throws ServletException{
        userDAO = new UsuarioDAO();
        prestDAO = new PrestadorDAO();
        cliDAO = new ClienteDAO();
    }
    
    public Domicilio domUser(String calle, String alt, String partido, String provincia, String localidad){
        Domicilio dom = null;
        if (calle != null && alt != null && partido != null && provincia != null && localidad != null) {
            dom = new Domicilio(provincia,partido,localidad,calle,alt);
        }
        return dom;
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try{
       
            req.getRequestDispatcher("pages/registro.jsp").forward(req, resp);//redirige el servlet primero a el JSP del form de registro.

        }catch (Exception ex){
            resp.sendError(500);
        }
    
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            String nombre = req.getParameter("nomCliente");
            String apellido = req.getParameter("apeCliente");
            String celular = req.getParameter("celular");
            String email = req.getParameter("email");
            String username = req.getParameter("user");
            String password = req.getParameter("pass");
            String tipoUsuario = req.getParameter("tipoUsuario");
            
            if ("prestador".equals(tipoUsuario)) {
                // Crea un usuario de tipo prestador
                UsuarioPrestador elPrestador = new UsuarioPrestador(username, nombre, apellido, email, password, celular);
                prestDAO.create(elPrestador);
                req.setAttribute("elUsuario", elPrestador);
            } else {
                // Por defecto, crea un usuario de tipo cliente
                UsuarioCliente elUsuario = new UsuarioCliente(username, nombre, apellido, email, password, celular);
                cliDAO.create(elUsuario);
                req.setAttribute("elUsuario", elUsuario);
            }

            req.getRequestDispatcher("pages/felicitacion.jsp").forward(req, resp);
            
        } catch (Exception ex){
            resp.sendError(500,"no anda bien esto en RegistroServlet\n"+ ex.getMessage());
        }
        
    }
    
}
