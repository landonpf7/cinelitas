package com.example.Cinelitas.Repositorio;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Cinelitas.Modelo.Pelicula;
public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer>{
    
}
