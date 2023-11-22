/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.model;

/**
 *
 * @author giann
 */
public class UsuarioPrestador extends Usuario {
    
    private int idPrestador;
   
    public UsuarioPrestador(int idPrestador, String nomUsuario, String nombre, String apellido, String email, String password, String celular, String rol) {
        super(nomUsuario, nombre, apellido, email, password, celular,"prestador");
        this.idPrestador = idPrestador;
    }
    
    public UsuarioPrestador(String nomUsuario, String nombre, String apellido, String email, String password, String celular) {
        super(0,nomUsuario, nombre, apellido, email, password, celular,"prestador");
       
    }

    public int getIdPrestador() {
        return idPrestador;
    }
    
}
