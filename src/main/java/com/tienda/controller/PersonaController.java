
package com.tienda.controller;

import com.tienda.entity.Pais;
import com.tienda.entity.Persona;
import com.tienda.service.IPaisService;
import com.tienda.service.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonaController {
   
    @Autowired
    private IPersonaService personaService;
    
    @Autowired
    private IPaisService paisService;
    
    @GetMapping("/persona")
    public String index(Model model){
    List<Persona> listaPersona = personaService.getAllPersona();
    model.addAttribute("titulo", "Tabla Personas");
    model.addAttribute("persona", listaPersona);
    return "personas";    
    }
    
    @GetMapping("/personaN")
    public String crearPersona (Model model){
    List<Pais> listaPaises = paisService.listCountry();
     model.addAttribute("titulo", "Tabla Personas");
    model.addAttribute("persona", new Persona());
    model.addAttribute("paises", listaPaises);
    return "crear";        
    }
    
    @GetMapping("delete/{id}")
    public String eliminarPesona(@PathVariable("id") Long idpersona)
    {
         personaService.delete(idpersona);
                 return "redirect:/persona";
    }
    
    @PostMapping("/save")
    public String guardarPersona (@ModelAttribute Persona persona){
     personaService.savePersona(persona);
     return "redirect:/persona";
    }
    
    @GetMapping("/editPersona/{id}")
    public String editarPersona (@PathVariable("id") Long id, Model model){
      Persona persona = personaService.getPersonaById(id);  
      List<Pais> listapaises = paisService.listCountry();
      model.addAttribute("persona", persona);
      model.addAttribute("paises", listapaises);
     return "editarPersona";
    }
    
       @PostMapping("/editPersona/{id}")
    public String actualizarPersona (@PathVariable Long id,@ModelAttribute("persona")  Persona persona){
      Persona personaEditar = personaService.getPersonaById(id);
      personaEditar.setId(id);
      personaEditar.setNombre(persona.getNombre());
      personaEditar.setApellido1(persona.getApellido1());
      personaEditar.setApellido2(persona.getApellido2());
      personaEditar.setEmail(persona.getEmail());
      personaEditar.setTelefono(persona.getTelefono());
      personaEditar.setPais(persona.getPais());
     personaService.savePersona(personaEditar);
      return "redirect:/persona";
    }
    
    
    
    
}
