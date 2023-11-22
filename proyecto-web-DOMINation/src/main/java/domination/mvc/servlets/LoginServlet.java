/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.servlets;


import domination.DAO.AdministradorDAO;
import domination.DAO.ClienteDAO;
import domination.DAO.DAO;
import domination.DAO.PrestadorDAO;
import domination.mvc.model.Usuario;
import domination.mvc.model.Administrador;
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
   
    @Override
    public void init() throws ServletException{
        prestDAO = new PrestadorDAO();
        cliDAO = new ClienteDAO();
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
                    listaUsers.add(prestador);
                }
                for (UsuarioCliente cliente : cliDAO.getAll()) {
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
            //Ligamos el user a la sesion
            UsuarioPrestador elUserLog = (UsuarioPrestador) elUser;
            HttpSession laSesion = req.getSession(); //Creamos una sesion
            laSesion.setMaxInactiveInterval(3600); //Le damos un maximo de tiempo en segundos(1hr)
            laSesion.setAttribute("userLogueado",elUserLog);//
            resp.sendRedirect(req.getContextPath()+"/inicio");//redirigimos al usuario a su pagina de inicio
        }
        else if ((elUser != null)&&(elUser instanceof UsuarioCliente)) {//Si el user existe
            //Ligamos el user a la sesion
            UsuarioCliente elUserLog = (UsuarioCliente) elUser;
            HttpSession laSesion = req.getSession(); //Creamos una sesion
            laSesion.setMaxInactiveInterval(3600); //Le damos un maximo de tiempo en segundos(1hr)
            laSesion.setAttribute("userLogueado",elUserLog);//
            resp.sendRedirect(req.getContextPath()+"/inicio");//redirigimos al usuario a su pagina de inicio
        }
        else if (elAdmin != null) {//Si el admin existe
            //Ligamos el user a la sesion
            HttpSession laSesion = req.getSession(); //Creamos una sesion
            laSesion.setMaxInactiveInterval(3600); //Le damos un maximo de tiempo en segundos(1hr)
            laSesion.setAttribute("userLogueado",elAdmin);//
            resp.sendRedirect(req.getContextPath()+"/inicio");//redirigimos al usuario a su pagina de inicio

        } 
        else { //Sino mostramos el mensaje de error
            req.setAttribute("hayError", true);//Si hay un error
            req.setAttribute("mensajeError", "Usuario o contraseña incorrectos.");//Este sera el mensaje de error 
            doGet(req,resp);
            
        }
        
    }
    
    
    
}
