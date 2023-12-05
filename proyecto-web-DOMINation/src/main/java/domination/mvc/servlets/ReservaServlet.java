/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.servlets;

import domination.DAO.DAO;
import domination.DAO.SalaDAO;
import domination.DAO.SedeDAO;
import domination.DAO.UsuarioDAO;
import domination.mvc.model.SalaEnsayo;
import domination.mvc.model.Sede;
import domination.mvc.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Seba
 */
public class ReservaServlet extends HttpServlet {
    private DAO<Sede, Integer> sedeDAO;
    private DAO<Usuario, Integer> userDAO;
    private DAO<SalaEnsayo,Integer> salaDAO;

    @Override
    public void init() throws ServletException {
        sedeDAO = new SedeDAO();
        userDAO = new UsuarioDAO();
        salaDAO = new SalaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            
        } catch (Exception ex) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            
        } catch (Exception ex) {
        }
    }
    
}
