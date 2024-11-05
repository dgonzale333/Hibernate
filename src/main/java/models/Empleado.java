package models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "EMPLEADO")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private LocalDate fechaContratacion;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    Departamento departamento;


    public Empleado() {}


    public Empleado(String nombre, LocalDate fechaContratacion, Departamento departamento) {
        this.nombre = nombre;
        this.fechaContratacion = fechaContratacion;
        this.departamento = departamento;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}

