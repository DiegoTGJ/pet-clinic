package diego.tutoriales.petclinic.services;

import java.util.Set;

import diego.tutoriales.petclinic.model.Pet;

public interface PetService {
	
	Pet findById(Long id);
	
	Pet save(Pet pet);
	
	Set<Pet> findAll();
}