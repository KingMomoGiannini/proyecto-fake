/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.DAO;

import domination.mvc.model.Domicilio;
import domination.mvc.model.Usuario;
import domination.mvc.model.UtilExceptions;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author giann
 */
public class UsuarioDAO implements DAO<Usuario,Integer>{

    public UsuarioDAO() {
        
    }     
        
    @Override
    public List<Usuario> getAll() throws SQLException {
        List<Usuario> usuarios  = new LinkedList();
        String query = "SELECT * FROM USUARIO"; //Creamos el SELECT * para la base de datos
        try(Connection conexion =ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conexion.prepareStatement(query);//Transformamos la cadena guardada en query, en una sentencia SQL
            ResultSet rs = ps.executeQuery();)
        {    
            while(rs.next()){
                usuarios.add(rsRowToUsuario(rs));//El metodo utilizado debería convertir una rs en un objeto de tipo Usuario
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        
        return usuarios;
    }

    @Override
    public void create(Usuario elObjeto) throws Exception {
        
    }
    
    @Override
    public void add(Usuario elUser) throws Exception {
        
    }

    @Override
    public void update(Usuario elUser) throws Exception {
        
    }

    @Override
    public void delete(Integer elId) throws Exception {
        
    }

//    @Override
//    public Usuario getByID(Integer elId) throws Exception {
//        
//        
//    }
    
    public Usuario userFake(String username, String pass){
        Usuario usuarioFake = null;
        if (pass.equals("1234")) {
            switch(username){
                case "admin":
                    usuarioFake = new Usuario(1,username,"Sebastian","Giannini","emailfake@elproyecto.com","1234","1169696969",new Domicilio("provincia falsa","localidad falsa","partido falso","calle falsa","123"));
                    break;
            }
        }
        
        return usuarioFake;
    }

    private Usuario rsRowToUsuario(ResultSet rs) throws SQLException {

        int id = rs.getInt("idusuario");
        String nomUsuario = rs.getString("nombre_usuario");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String celular = rs.getString("celular");
        Domicilio dom = null;
        
        return new Usuario(id,nomUsuario,nombre,apellido,email,password,celular,dom);
    }
    
}
