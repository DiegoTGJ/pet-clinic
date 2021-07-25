package diego.tutoriales.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
public class Pet extends BaseEntity{
	private String name;
	private PetType petType;
	private Owner owner;
	private LocalDate birthDate;
}
