
package com.tienda.service;

import com.tienda.entity.Pais;
import java.util.List;

@Service
public class PaisService  implements IPaisService{

    @Autowired
    private PaisRepository paisRepository;
    
    
    @Override
    public List<Pais> listCountry() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
    
}
