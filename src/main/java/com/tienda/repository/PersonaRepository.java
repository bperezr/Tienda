
package com.tienda.repository;
import com.tienda.entity.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

 @Repository
public interface PersonaRepository extends JpaRepository<Persona,Long>{
      List<Persona> findByApellido1(String apellido1);
}



