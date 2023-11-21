/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.DAO;

import domination.mvc.model.Domicilio;
import domination.mvc.model.Sede;
import domination.mvc.model.UsuarioPrestador;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author giann
 */
public class PrestadorDAO implements DAO<UsuarioPrestador,Integer>{ 
        
    @Override
    public List<UsuarioPrestador> getAll() throws SQLException, Exception {
        List<UsuarioPrestador> prestadores = new LinkedList<>();
        String query = "SELECT * FROM prestador p JOIN usuario u ON p.usuario_idusuario = u.idusuario";
        try (Connection conexion = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conexion.prepareStatement(query);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                prestadores.add(rsRowToPrestador(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return prestadores;
    }

    @Override
    public void create(UsuarioPrestador elPrestador) throws Exception {
        String query = "INSERT INTO usuario (nombre_usuario, nombre, apellido, email, password, celular, administrador_idadministrador) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, elPrestador.getNomUsuario());
            preparedStatement.setString(2, elPrestador.getNombre());
            preparedStatement.setString(3, elPrestador.getApellido());
            preparedStatement.setString(4, elPrestador.getEmail());
            preparedStatement.setString(5, elPrestador.getPassword());
            preparedStatement.setString(6, elPrestador.getCelular());
            preparedStatement.setInt(7, elPrestador.getIdAdmin());

            preparedStatement.executeUpdate();

            // Obtener el ID generado para identificar como prestador
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idUsuarioGenerado = generatedKeys.getInt(1);
                    // Ahora insertar en la tabla prestador
                    insertarEnPrestador(idUsuarioGenerado);
                } else {
                    throw new SQLException("Error al obtener el ID del prestador generado.");
                }
            }
//    public void create(UsuarioPrestador elPrestador) throws Exception {
//        String query = "INSERT INTO prestador (usuario_idusuario) VALUES ( ?)";
//        try (Connection con = ConnectionPool.getInstance().getConnection();
//             PreparedStatement preparedStatement = con.prepareStatement(query)) {
//            preparedStatement.setInt(1, elPrestador.getIdUsuario());
////            preparedStatement.setInt(2, elPrestador.getIdUsuario());
//
//            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new Exception("Error al crear un nuevo prestador", ex);
        }
    }

    @Override
    public void update(UsuarioPrestador elUser) throws Exception {
        String query = "UPDATE prestador SET nombre_usuario = ?, nombre = ?, apellido = ?, email = ?, password = ?, celular = ? WHERE idusuario = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, elUser.getNomUsuario());
            preparedStatement.setString(2, elUser.getNombre());
            preparedStatement.setString(3, elUser.getApellido());
            preparedStatement.setString(4, elUser.getEmail());
            preparedStatement.setString(5, elUser.getPassword());
            preparedStatement.setString(6, elUser.getCelular());
            preparedStatement.setInt(7, elUser.getIdUsuario());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Error al actualizar el usuario", ex);
        }
    }

    @Override
    public void delete(Integer elId) throws Exception {
        String query = "DELETE FROM prestador WHERE idusuario = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, elId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Error al eliminar un usuario", ex);
        }
    }

    @Override
    public UsuarioPrestador getByID(Integer elId) throws Exception {
        String query = "SELECT * FROM prestador WHERE idusuario = ?";
        UsuarioPrestador usuario = null;
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, elId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = rsRowToPrestador(resultSet);
                }
            }
        } catch (SQLException ex) {
            throw new Exception("Error al obtener un usuario por ID", ex);
        }
        return usuario;
    }
    
   private UsuarioPrestador rsRowToPrestador(ResultSet rs) throws SQLException, Exception {
        int id = rs.getInt("idusuario");
        String nomUsuario = rs.getString("nombre_usuario");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String celular = rs.getString("celular");
        int idPrestador = rs.getInt("idprestador");  // Ajustar el nombre de la columna según tu esquema
        //int idSede = rs.getInt("prestador_idprestador");  // Ajustar el nombre de la columna según tu esquema

        // Aquí necesitas lógica para obtener la Sede (Sucursal)
        //Sede laSede = obtenerSedePorId(idSede);  // Debes implementar este método

        return new UsuarioPrestador(idPrestador, nomUsuario, nombre, apellido, email, password, celular);
    }
    
    public UsuarioPrestador autenticar(String nomUsuario, String pass) throws Exception{
        String query = "SELECT * FROM prestador WHERE nombre_usuario = ? AND password = ?";
        UsuarioPrestador elUser= null;
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, nomUsuario);
            ps.setString(2,pass);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    elUser = rsRowToPrestador(resultSet);
                }
            } 
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return elUser;
    }
    // Método para insertar en la tabla prestador después de insertar en usuario
    private void insertarEnPrestador(int idUsuarioGenerado) throws SQLException {
        String query = "INSERT INTO prestador (usuario_idusuario) VALUES (?)";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, idUsuarioGenerado);
            preparedStatement.executeUpdate();
        }
    }
    
    private Sede obtenerSedePorId(int idSede) throws SQLException, Exception {
        String query = "SELECT * FROM sucursal WHERE idsucursal = ?";
        Sede laSede = null;

        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, idSede);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Ajusta los nombres de las columnas según tu esquema
                    int idSedeDB = resultSet.getInt("idsucursal");
                    String nombreSede = resultSet.getString("nombre");
                    int cantSalas = resultSet.getInt("cant_salas");
                    int idPrestador = resultSet.getInt("prestador_idprestador");
                    int horaInicio = resultSet.getInt("hora_inicio");
                    int horaFin = resultSet.getInt("hora_fin");
                    String telefono = resultSet.getString("telefono");

                    // Obtén el domicilio asociado a través del DAO de Domicilio (debes tener un método getById en tu DAO)
                    DomicilioDAO elDAO = new DomicilioDAO();
                    Domicilio dom = elDAO.getByID(idSede);

                    // Crea una instancia de Sede con la información obtenida
                    laSede = new Sede(idSedeDB, nombreSede, cantSalas, idPrestador, horaInicio, horaFin, telefono, dom);
                    // Ajusta según los atributos reales de la clase Sede
                }
            }
        }

        return laSede;
    }

}
