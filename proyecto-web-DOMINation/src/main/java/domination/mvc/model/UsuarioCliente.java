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

    public UsuarioCliente(int id, String nomUsuario, String nombre, String apellido, String email, String password, String celular) {
        super(id, nomUsuario, nombre, apellido, email, password, celular);
    }

    public UsuarioCliente(String nomUsuario, String nombre, String apellido, String email, String password, String celular) {
        super(nomUsuario, nombre, apellido, email, password, celular);
    }

    public UsuarioCliente(int idCliente, int id, String nomUsuario, String nombre, String apellido, String email, String password, String celular) {
        super(id, nomUsuario, nombre, apellido, email, password, celular);
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }
    
}