package com.example.Cinelitas.Modelo;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pelicula")
public class Pelicula {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombrepelicula", nullable = false, length = 100)
    private String NombrePelicula;

    @Column(name = "costo", nullable = false, scale = 2)
    private double Costo;

    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "fechaestreno", nullable = false, length = 150)
    private LocalDate FechaEstreno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombrePelicula(String NombrePelicula) {
        this.NombrePelicula = NombrePelicula;
    }

    public String getNombrePelicula() {
        return NombrePelicula;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double Costo) {
        this.Costo = Costo;
    }

    public LocalDate getFechaEstreno() {
        return FechaEstreno;
    }

    public void setFechaEstreno(LocalDate FechaEstreno) {
        this.FechaEstreno = FechaEstreno;
    }

}
