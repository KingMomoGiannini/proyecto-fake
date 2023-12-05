/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domination.mvc.model;

/**
 *
 * @author Seba
 */
public class Reserva {
    private int idReserva;
    private int duracion;
    private int idSala;
    private int idCliente;
    private int horaInicio;
    private int horaFin;
    private double monto;

    public Reserva() {
    }

    public Reserva(int duracion, int idSala, int idCliente, int horaInicio, int horaFin, double monto) {
        this(0,duracion,idSala,idCliente,horaInicio,horaFin,monto);
    }
    
    public Reserva(int idReserva, int duracion, int idSala, int idCliente, int horaInicio, int horaFin, double monto) {
        this.idReserva = idReserva;
        this.duracion = duracion;
        this.idSala = idSala;
        this.idCliente = idCliente;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.monto = monto;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getIdSala() {
        return idSala;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public double getMonto() {
        return monto;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
    
    
}
