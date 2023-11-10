/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.servlets;


import domination.mvc.model.Usuario;
import domination.DAO.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author giann
 */
public class LoginServlet extends HttpServlet {

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

        String user = req.getParameter("user");
        String password = req.getParameter("pass");
        
        Usuario elUser = new UsuarioDAO().autenticar(user, password);
        
        if (elUser != null) {//Si el user existe
            //Ligamos el user a la sesion
            HttpSession laSesion = req.getSession(); //Creamos una sesion
            laSesion.setMaxInactiveInterval(3600); //Le damos un maximo de tiempo en segundos(1hr)
            laSesion.setAttribute("userLogueado",elUser);//
            resp.sendRedirect(req.getContextPath()+"/inicio");

        } else { //Sino mostramos el mensaje de error
            req.setAttribute("hayError", true);//Si hay un error
            req.setAttribute("mensajeError", "Credenciales incorrectas pelotudo");//Este sera el mensaje de error 
            doGet(req,resp);
            
        }
        
    }
    
    
    
}
