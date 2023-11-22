/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.model;

/**
 *
 * @author giann
 */
public class Sede {
    private int idSede;
    private String nombre;
    private int cantSalas;
    private int idPrestador;
    private int horaInicio;
    private int horaFin;
    private String telefono;


    public Sede() {
    }
    
    public Sede(String nombre, int cantSalas, int idPrestador, int horaInicio, int horaFin, String telefono) {
        this(0,nombre,cantSalas, idPrestador, horaInicio, horaFin, telefono);
    }

    public Sede(int idSede, String nombre, int cantSalas, int idPrestador, int horaInicio, int horaFin, String telefono) {
        this.idSede = idSede;
        this.nombre = nombre;
        this.cantSalas = cantSalas;
        this.idPrestador = idPrestador;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.telefono = telefono;

    }


    public int getIdSede() {
        return idSede;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantSalas() {
        return cantSalas;
    }

    public int getIdPrestador() {
        return idPrestador;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }
    
    public String getTelefono() {
        return telefono;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }
    
}
