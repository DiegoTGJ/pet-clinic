package diego.tutoriales.petclinic.bootstrap;

import diego.tutoriales.petclinic.model.Pet;
import diego.tutoriales.petclinic.model.PetType;
import diego.tutoriales.petclinic.model.Vet;
import diego.tutoriales.petclinic.services.OwnerService;
import diego.tutoriales.petclinic.services.PetTypeService;
import diego.tutoriales.petclinic.services.VetService;
import diego.tutoriales.petclinic.model.Owner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args)  {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("Loaded PetTypes...");

        Owner owner1 = Owner.builder().address("La Florida")
                .city("Santiago")
                .telephone("+56928874351")
                .build() ;
        owner1.setFirstName("Michael");
        owner1.setLastName("Perez");

        Pet michaelsPet = Pet.builder().birthDate(LocalDate.now())
                .petType(savedDogPetType)
                .name("Ron")
                .owner(owner1)
                .build();

        owner1.getPets().add(michaelsPet);
        ownerService.save(owner1);

        Owner owner2 = Owner.builder().address("Ñuñoa")
                .city("Rancagua")
                .telephone("+5698844411")
                .build();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");

        Pet fionasCat = Pet.builder().birthDate(LocalDate.now())
                .petType(savedCatPetType)
                .name("Neko")
                .owner(owner2)
                .build();
        
        fionasCat.setOwner(owner2);
        fionasCat.setPetType(savedCatPetType);
        owner2.getPets().add(fionasCat);

        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded vets...");

    }
}
