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
import com.example.Cinelitas.Modelo.Sala;
import com.example.Cinelitas.Repositorio.SalaRepositorio;

@Controller

public class SalaControlador {

    @Autowired
    private SalaRepositorio salaRepositorio;

    @GetMapping({"/", "sala"})
    public String verPaginaDeInicioSala(Model modelo) {
        List<Sala> salas = salaRepositorio.findAll();
        modelo.addAttribute("salas", salas);
        return "index";
    }

    @GetMapping("/nuevasala")
    public String mostrarFormularioDeRegistrarSala(Model modelo) {
        modelo.addAttribute("sala", new Sala());
        return "nuevasala";
    }

    @PostMapping("/nuevasala")
    public String guardarSala(@Validated Sala sala, BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
        if (bindingResult.hasErrors()) {
            modelo.addAttribute("sala", sala);
            return "nuevasala";
        }

        salaRepositorio.save(sala);
        redirect.addFlashAttribute("msgExito", "La sala se ha guardado con exito");
        return "redirect:/";
    }

    @GetMapping("/{id}/editarsala")
    public String mostrarFormularioDeEditarSala(@PathVariable Integer id, Model modelo) {
        Sala sala = salaRepositorio.getById(id);
        modelo.addAttribute("sala", sala);
        return "nuevasala";
    }

    @PostMapping("/{id}/editarsala")
    public String actualizarSala(@PathVariable Integer id, @Validated Sala sala, BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
        Sala salaDB = salaRepositorio.getById(id);
        if (bindingResult.hasErrors()) {
            modelo.addAttribute("sala", sala);
            return "nuevasala";
        }

        salaDB.setCapacidadMaxima(sala.getCapacidadMaxima());
        salaDB.setNumeroSala(sala.getNumeroSala());
        salaDB.setPeliculaMostrar(sala.getPeliculaMostrar());

        salaRepositorio.save(salaDB);
        redirect.addFlashAttribute("msgExito", "La sala se ha actualizado correctamente");
        return "redirect:/";
    }

    @PostMapping("/{id}/eliminarsala")
    public String eliminarSala(@PathVariable Integer id, RedirectAttributes redirect) {
        Sala sala = salaRepositorio.getById(id);
        salaRepositorio.delete(sala);
        redirect.addFlashAttribute("msgExito", "La sala se ha eliminado");
        return "redirect:/";
    }
}
