/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

public class Mantenimiento {
    private int id;
    private String fecha;
    private int id_vehiculo;
    private double costo;
    private String marca_vehiculo;
    private String modelo_vehiculo;

    public Mantenimiento() {
    }
    
    public Mantenimiento(String fecha, int id_vehiculo, double costo) {
        this.fecha = fecha;
        this.id_vehiculo = id_vehiculo;
        this.costo = costo;
    }
    
    public Mantenimiento(int id, String fecha, int id_vehiculo, double costo) {
        this.id = id;
        this.fecha = fecha;
        this.id_vehiculo = id_vehiculo;
        this.costo = costo;
    }
    
    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public double getCosto() {
        return costo;
    }

    public String getMarca_vehiculo() {
        return marca_vehiculo;
    }

    public String getModelo_vehiculo() {
        return modelo_vehiculo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    } 

    public void setMarca_vehiculo(String marca_vehiculo) {
        this.marca_vehiculo = marca_vehiculo;
    }

    public void setModelo_vehiculo(String modelo_vehiculo) {
        this.modelo_vehiculo = modelo_vehiculo;
    }
    
}
