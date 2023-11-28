/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author giann
 */
public class BienvenidaServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
//            String exitoParam = req.getParameter("Exito");
//            
//            if (exitoParam != null && exitoParam.equals("true")) {
//                req.setAttribute("Exito", true);
//                req.setAttribute("mensajeExito", req.getParameter("mensajeExito"));
//            }
            req.getRequestDispatcher("pages/inicio.jsp").forward(req, resp);

        }catch(Exception ex){
            resp.sendError(500,"En BienvenidaServlet hay un error");
        }
    }

}
