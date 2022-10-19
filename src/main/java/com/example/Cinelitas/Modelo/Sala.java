package com.example.Cinelitas.Modelo;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sala")
public class Sala {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "capacidadmaxima", nullable = false, scale = 2)
    private int CapacidadMaxima;

    @Column(name = "numerosala", nullable = false, scale = 2)
    private int NumeroSala;

    @Column(name = "peliculamostrar", nullable = false, length = 100)
    private String PeliculaMostrar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroSala() {
        return NumeroSala;
    }

    public void setNumeroSala(Integer NumeroSala) {
        this.NumeroSala = NumeroSala;
    }

    public void setPeliculaMostrar(String PeliculaMostrar) {
        this.PeliculaMostrar = PeliculaMostrar;
    }

    public String getPeliculaMostrar() {
        return PeliculaMostrar;
    }
}
