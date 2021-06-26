package diego.tutoriales.petclinic.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pet{
	
	private PetType petType;
	private Owner owner;
	private LocalDate birthDate;
}
