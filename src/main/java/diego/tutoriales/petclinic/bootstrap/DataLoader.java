package diego.tutoriales.petclinic.bootstrap;

import diego.tutoriales.petclinic.model.*;
import diego.tutoriales.petclinic.services.OwnerService;
import diego.tutoriales.petclinic.services.PetTypeService;
import diego.tutoriales.petclinic.services.SpecialtyService;
import diego.tutoriales.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args)  {

        int count = petTypeService.findAll().size();

        if(count == 0) loadData();

    }

    private void loadData() {
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

        Specialty radiologySpecialty = new Specialty();
        radiologySpecialty.setDescription("Radiology");
        Specialty savedRadiologySpecialty = specialtyService.save(radiologySpecialty);

        Specialty surgerySpecialty = new Specialty();
        radiologySpecialty.setDescription("Surgery");
        Specialty savedSurgerySpecialty = specialtyService.save(surgerySpecialty);

        Specialty dentistrySpecialty = new Specialty();
        dentistrySpecialty.setDescription("Dentistry");
        Specialty savedDentistrySpecialty = specialtyService.save(dentistrySpecialty);
        System.out.println("Loaded Specialties...");


        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedRadiologySpecialty);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(savedSurgerySpecialty);
        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
