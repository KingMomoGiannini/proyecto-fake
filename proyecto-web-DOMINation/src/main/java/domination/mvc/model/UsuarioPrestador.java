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
    
    private Sede laSede;
    private int idPrestador;
   
    public UsuarioPrestador(int idPrestador, String nomUsuario, String nombre, String apellido, String email, String password, String celular) {
        super(nomUsuario, nombre, apellido, email, password, celular);
        this.idPrestador = idPrestador;
    }
    
    public UsuarioPrestador(String nomUsuario, String nombre, String apellido, String email, String password, String celular) {
        super(0,nomUsuario, nombre, apellido, email, password, celular);
       
    }
    

    public Sede getLaSede() {
        return laSede;
    }

    public int getIdPrestador() {
        return idPrestador;
    }
    
}
