package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {
	
	@Autowired
	private OwnerService ownerService;
	@Autowired
	private VetService vetService;
	@Autowired
	private PetTypeService petTypeService;
	@Autowired
	private SpecialityService specialityService;
	
//	public DataLoader(OwnerService ownerService, VetService vetService) {
//		this.ownerService = ownerService;
//		this.vetService = vetService;
//	}


	@Override
	public void run(String... args) throws Exception {
		int count = petTypeService.findAll().size();
		
		if (count == 0) {
			loadData();
		}
	}
	
	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Speciality radiology = new Speciality();
		radiology.setDescription("Radiology");
		Speciality savedRadiology = specialityService.save(radiology);
		Speciality surgery = new Speciality();
		radiology.setDescription("Surgery");
		Speciality savedSurgery = specialityService.save(surgery);
		Speciality dentisty = new Speciality();
		radiology.setDescription("Dentisty");
		Speciality savedDentisty = specialityService.save(dentisty);
		
		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		owner1.setAddress("123 Brickerel");
		owner1.setCity("Miami");
		owner1.setTelephone("22154879");
		Pet michaelsPet = new Pet();
		michaelsPet.setPetType(savedDogPetType);
		michaelsPet.setOwner(owner1);
		michaelsPet.setBirthDate(LocalDate.now());
		michaelsPet.setName("Rosco");
		owner1.getPets().add(michaelsPet);
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glennane");
		owner2.setAddress("26 beverly hills");
		owner2.setCity("New York");
		owner2.setTelephone("22114536");
		Pet fionasPet = new Pet();
		fionasPet.setPetType(savedCatPetType);
		fionasPet.setOwner(owner2);
		fionasPet.setBirthDate(LocalDate.now());
		fionasPet.setName("Caramel");
		owner2.getPets().add(fionasPet);
		
		ownerService.save(owner2);
		
		System.out.println("Loaded Owners ...");
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		vet1.getSpecialities().add(savedDentisty);
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("John");
		vet2.setLastName("Lenon");
		vet2.getSpecialities().add(savedSurgery);
		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets ...");
	}

}
