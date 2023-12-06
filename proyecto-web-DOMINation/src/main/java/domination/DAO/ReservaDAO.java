/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.DAO;

import domination.mvc.model.Reserva;
import domination.mvc.model.SalaEnsayo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Seba
 */
public class ReservaDAO implements DAO<Reserva,Integer>{

    @Override
    public List<Reserva> getAll() throws Exception {
        List<Reserva> reservas = new LinkedList();
        String query = "SELECT * FROM reserva"; //Creamos el SELECT * para la base de datos
        try(Connection conexion =ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conexion.prepareStatement(query);//Transformamos la cadena guardada en query, en una sentencia SQL
            ResultSet rs = ps.executeQuery();)
        {    
            while(rs.next()){
                reservas.add(rsRowToReserva(rs));
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        
        return reservas;
    }

    @Override
    public void create(Reserva laReserva) throws Exception {
        String query = "INSERT INTO reserva (duracion, hora_inicio, hora_fin, monto, sala_idsala, cliente_idcliente) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1,laReserva.getDuracion());
            preparedStatement.setTimestamp(2,Timestamp.valueOf(laReserva.getHoraInicio()));
            preparedStatement.setTimestamp(3,Timestamp.valueOf(laReserva.getHoraFin()));
            preparedStatement.setDouble(4,laReserva.getMonto());
            preparedStatement.setInt(5,laReserva.getIdSala());
            preparedStatement.setInt(6,laReserva.getIdCliente());
            
            
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    laReserva.setIdReserva(generatedKeys.getInt(1));// me sirve para obtener el ID generado y lo asigna a laReserva
                } else {
                    throw new SQLException("Fallo al obtener el ID de la sala, no se generó automáticamente.");
                }
            }
        } catch (SQLException ex) {
            throw new Exception("Error al crear una nueva reserva por SQL", ex);
        }
    }

    @Override
    public void update(Reserva laReserva) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer elId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Reserva getByID(Integer elId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Reserva rsRowToReserva(ResultSet rs) throws SQLException {
        int idReserva = rs.getInt("idreserva");
        int duracion = rs.getInt("duracion");
        LocalDateTime horaInicio = rs.getTimestamp("hora_inicio").toLocalDateTime();
        LocalDateTime horaFin = rs.getTimestamp("hora_fin").toLocalDateTime();
        double monto = rs.getDouble("monto");
        int idSala = rs.getInt("sala_idsala");
        int idCliente = rs.getInt("cliente_idcliente");
        
        return new Reserva(idReserva,duracion,idSala,idCliente,horaInicio,horaFin,monto);
    }
    
}
