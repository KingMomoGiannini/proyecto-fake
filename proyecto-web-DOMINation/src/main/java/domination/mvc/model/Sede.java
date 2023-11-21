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
    private Domicilio dom;

    public Sede(String nombre, int cantSalas, int idPrestador, int horaInicio, int horaFin, String telefono, Domicilio dom) {
        this(0,nombre,cantSalas, idPrestador, horaInicio, horaFin, telefono, dom);
    }

    public Sede(int idSede, String nombre, int cantSalas, int idPrestador, int horaInicio, int horaFin, String telefono, Domicilio dom) {
        this.idSede = idSede;
        this.nombre = nombre;
        this.cantSalas = cantSalas;
        this.idPrestador = idPrestador;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.telefono = telefono;
        this.dom = dom;
    }
    
    public Sede(Domicilio dom) {
        this.dom = dom;
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

    public Domicilio getDom() {
        return dom;
    }
   
}
