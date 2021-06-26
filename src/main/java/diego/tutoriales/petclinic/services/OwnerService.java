package diego.tutoriales.petclinic.services;

import java.util.Set;

import diego.tutoriales.petclinic.model.Owner;

public interface OwnerService {

		Owner findByLastName(String name);
	
		Owner findById(Long id);
		
		Owner save(Owner owner);
		
		Set<Owner> findAll();
}
