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

    public UsuarioPrestador(Sede laSede, int id, String nomUsuario, String nombre, String apellido, String email, String password, String celular) {
        super(id, nomUsuario, nombre, apellido, email, password, celular);
        this.laSede = laSede;
    }
    
    
}
