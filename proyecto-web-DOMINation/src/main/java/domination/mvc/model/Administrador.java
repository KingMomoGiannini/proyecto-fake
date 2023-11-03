/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.model;

/**
 *
 * @author giann
 */
public class Administrador extends Usuario {

    public Administrador(int id, String nomUsuario, String nombre, String apellido, String email, String password, String celular, Domicilio dom) {
        super(id, nomUsuario, nombre, apellido, email, password, celular, dom);
        
    }
    
}
