/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.DAO;

import domination.mvc.model.Sede;
import domination.DAO.DomicilioDAO;
import domination.mvc.model.Domicilio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author giann
 */
public class SedeDAO implements DAO<Sede,Integer>{

    @Override
    public List<Sede> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(Sede elObjeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Sede elObjeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer elId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Sede getByID(Integer elId) throws SQLException, Exception {
        String query = "SELECT * FROM sucursal WHERE idsucursal = ?";
        Sede sede = null;

        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, elId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Domicilio dom = obtenerDomicilioPorId(resultSet.getInt("idsucursal"));  // Obtener domicilio por id
                    sede = rsRowToSede(resultSet, dom);
                }
            }
        } catch (SQLException ex) {
            throw new Exception("Error al obtener una sede por ID", ex);
        }

        return sede;
    }
    /*---------------------------------------------------*/

    private Domicilio obtenerDomicilioPorId(int id) throws Exception {
        String query = "SELECT * FROM domicilio WHERE idsucursal = ?";
        Domicilio domicilio = null;

        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    domicilio = rsRowToDomicilio(resultSet);
                }
            }
        } catch (SQLException ex) {
            throw new Exception("Error al obtener un domicilio por ID", ex);
        }

        return domicilio;
    }

    private Domicilio rsRowToDomicilio(ResultSet rs) throws SQLException {
        int idDomicilio = rs.getInt("iddomicilio");
        String provincia = rs.getString("provincia");
        String localidad = rs.getString("localidad");
        String partido = rs.getString("partido");
        String calle = rs.getString("calle");
        String altura = rs.getString("altura");

        return new Domicilio(idDomicilio, provincia, localidad, partido, calle, altura);
    }

    private Sede rsRowToSede(ResultSet rs, Domicilio dom) throws SQLException {
        int idSede = rs.getInt("idsucursal");
        String nombre = rs.getString("nombre");
        int cantSalas = rs.getInt("cant_salas");
        int idPrestador = rs.getInt("prestador_idprestador");
        int horaInicio = rs.getInt("hora_inicio");
        int horaFin = rs.getInt("hora_fin");
        String telefono = rs.getString("telefono");

        return new Sede(idSede, nombre, cantSalas, idPrestador, horaInicio, horaFin, telefono, dom);
    }
    
    
}
