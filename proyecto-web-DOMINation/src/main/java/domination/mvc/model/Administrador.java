/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.model;


/**
 *
 * @author giann
 */
public abstract class Administrador {

    private static final int ID = 1;
    private String nomUsuario;
    private String password;
    
    public Administrador(int id, String nomUsuario, String password) {
        this.nomUsuario = nomUsuario;
        this.password = password;
    }

    public static int getId() {
        return ID;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    @Override
    public String toString() {
        return "Administrador{" + "id=" + ID + ", nomUsuario=" + nomUsuario + '}';
    }
    
    
       
}
