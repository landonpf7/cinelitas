package com.example.Cinelitas.Controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.Cinelitas.Modelo.Pelicula;
import com.example.Cinelitas.Repositorio.PeliculaRepositorio;

@Controller
public class PeliculaControlador {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;


    @GetMapping({"/", ""})
    public String verPaginaDeInicio(Model modelo) {
        List<Pelicula> peliculas = peliculaRepositorio.findAll();
        modelo.addAttribute("peliculas", peliculas);
        return "index";
    }
   
    @GetMapping("/nuevo")
    public String mostrarFormularioDeRegistrarPelicula(Model modelo) {
        modelo.addAttribute("pelicula", new Pelicula());
        return "nuevo";
    }

    @PostMapping("/nuevo")
    public String guardarPelicula(@Validated Pelicula pelicula, BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
        if (bindingResult.hasErrors()) {
            modelo.addAttribute("pelicula", pelicula);
            return "nuevo";
        }

        peliculaRepositorio.save(pelicula);
        redirect.addFlashAttribute("msgExito", "La pelicula se ha guardado con exito");
        return "redirect:/";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioDeEditarPelicula(@PathVariable Integer id, Model modelo) {
        Pelicula pelicula = peliculaRepositorio.getById(id);
        modelo.addAttribute("pelicula", pelicula);
        return "nuevo";
    }

    @PostMapping("/{id}/editar")
    public String actualizarPelicula(@PathVariable Integer id, @Validated Pelicula pelicula, BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
        Pelicula peliculaDB = peliculaRepositorio.getById(id);
        if (bindingResult.hasErrors()) {
            modelo.addAttribute("pelicula", pelicula);
            return "nuevo";
        }

        peliculaDB.setNombrePelicula(pelicula.getNombrePelicula());
        peliculaDB.setCosto(pelicula.getCosto());
        peliculaDB.setFechaEstreno(pelicula.getFechaEstreno());

        peliculaRepositorio.save(peliculaDB);
        redirect.addFlashAttribute("msgExito", "La pelicula se ha actualizado correctamente");
        return "redirect:/";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarPelicula(@PathVariable Integer id, RedirectAttributes redirect) {
        Pelicula pelicula = peliculaRepositorio.getById(id);
        peliculaRepositorio.delete(pelicula);
        redirect.addFlashAttribute("msgExito", "La pelicula se ha eliminado");
        return "redirect:/";
    }
}
