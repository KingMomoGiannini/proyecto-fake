/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.model;

/**
 *
 * @author giann
 */
public class UsuarioCliente extends Usuario {
    
    private int idCliente;

    public UsuarioCliente(int idCliente, String nomUsuario, String nombre, String apellido, String email, String password, String celular,String rol) {
        super(nomUsuario, nombre, apellido, email, password, celular,"cliente");
        this.idCliente = idCliente;
    }
//
//    public UsuarioCliente(int id, String nomUsuario, String nombre, String apellido, String email, String password, String celular,String rol) {
//        super(id, nomUsuario, nombre, apellido, email, password, celular,rol);
//    }

    public UsuarioCliente(String nomUsuario, String nombre, String apellido, String email, String password, String celular) {
        super(0,nomUsuario, nombre, apellido, email, password, celular,"cliente");
    }

    public int getIdCliente() {
        return idCliente;
    }
    
}