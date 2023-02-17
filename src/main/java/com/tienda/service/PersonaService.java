
package com.tienda.service;
import com.tienda.entity.Persona;
import java.util.List;

@Service
public class PersonaService implements IPersonaService{

    @Autowired
    private PersonaRepository personaRepository;
    
    @Override
    public List<Persona> getAllPersona() {
       return (List<Persona>)personaRepository.findAll();
    }

    @Override
    public Persona getPersonaById(long id) {
       return personaRepository.findById(id).orElse(null);
    }

    @Override
    public void savePersona(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    public void delete(long id) {
       personaRepository.deleteById(id);
    }
    
}
