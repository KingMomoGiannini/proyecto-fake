/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.DAO;

import domination.mvc.model.Administrador;
import domination.mvc.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author giann
 */
public class AdministradorDAO implements DAO<Administrador,Integer>{

    @Override
    public List<Administrador> getAll() throws Exception {
        return null; 
    }

    @Override
    public void create(Administrador elObjeto) throws Exception {
        String query = "INSERT INTO usuario (nombre_usuario, nombre, apellido, email, password, celular) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, elObjeto.getNomUsuario());
            preparedStatement.setInt(2, elObjeto.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Error al crear un nuevo usuario", ex);
        }
    }

    @Override
    public void update(Administrador elObjeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer elId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Administrador getByID(Integer elId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
