/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.model;

/**
 *
 * @author giann
 */
public class Usuario {
    private int id;
    private String nomUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String celular;
    private int idAdmin;

    public Usuario(String nomUsuario, String nombre, String apellido, String email, String password, String celular) {
        this(0, nomUsuario, nombre, apellido, email, password, celular);
    }

    public Usuario(int id, String nomUsuario, String nombre, String apellido, String email, String password, String celular) {
        this.id = id;
        this.nomUsuario = nomUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.celular = celular;
    }

    public int getId() {
        return id;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCelular() {
        return celular;
    }
    
    public int getIdAdmin(){
        return Administrador.getId();
    }
}
