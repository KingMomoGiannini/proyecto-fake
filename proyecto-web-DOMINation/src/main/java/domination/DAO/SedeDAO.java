/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.DAO;

import domination.mvc.model.Sede;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author giann
 */
public class SedeDAO implements DAO<Sede,Integer>{

    @Override
    public List<Sede> getAll() throws Exception {
        List<Sede> sedes  = new LinkedList();
        String query = "SELECT * FROM sucursal"; //Creamos el SELECT * para la base de datos
        try(Connection conexion =ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conexion.prepareStatement(query);//Transformamos la cadena guardada en query, en una sentencia SQL
            ResultSet rs = ps.executeQuery();)
        {    
            while(rs.next()){
                sedes.add(rsRowToSede(rs));
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        
        return sedes;
    }

    @Override
    public void create(Sede laSede) throws Exception {
        String query = "INSERT INTO sucursal (nombre, cant_salas, prestador_idprestador, hora_inicio, hora_fin, telefono) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1,laSede.getNombre());
            preparedStatement.setInt(2,laSede.getCantSalas());
            preparedStatement.setInt(3,laSede.getIdPrestador());
            preparedStatement.setInt(4,laSede.getHoraInicio());
            preparedStatement.setInt(5,laSede.getHoraFin());
            preparedStatement.setString(6,laSede.getTelefono());
            
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                laSede.setIdSede(generatedKeys.getInt(1));// me sirve para obtener el ID generado y lo asigna a laSede
            } else {
                throw new SQLException("Fallo al obtener el ID de la sede, no se generó automáticamente.");
            }
        }
        } catch (SQLException ex) {
            throw new Exception("Error al crear unaa nueva Sede por SQL", ex);
        }
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
                    sede = rsRowToSede(resultSet);
                }
            }
        } catch (SQLException ex) {
            throw new Exception("Error al obtener una sede por ID", ex);
        }

        return sede;
    }

    private Sede rsRowToSede(ResultSet rs) throws SQLException {
        int idSede = rs.getInt("idsucursal");
        String nombre = rs.getString("nombre");
        int cantSalas = rs.getInt("cant_salas");
        int idPrestador = rs.getInt("prestador_idprestador");
        int horaInicio = rs.getInt("hora_inicio");
        int horaFin = rs.getInt("hora_fin");
        String telefono = rs.getString("telefono");

        return new Sede(idSede, nombre, cantSalas, idPrestador, horaInicio, horaFin, telefono);
    }
    
//    public int getIdSede()throws SQLException{
//        int idSede;
//        return idSede;
//    }
    
    
}
