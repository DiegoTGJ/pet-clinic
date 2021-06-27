package diego.tutoriales.petclinic.services;

import diego.tutoriales.petclinic.model.Owner;

public interface OwnerService  extends  CrudService<Owner, Long>{

		Owner findByLastName(String name);

}
