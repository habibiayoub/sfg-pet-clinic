package guru.springframework.sfgpetclinic.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

	@Autowired
	private PetService petService;
	@Autowired
	private PetTypeService petTypeService;
	@Autowired
	private OwnerService ownerService;
	
	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
	
	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes() {
		return petTypeService.findAll();
	}

	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
		return ownerService.findById(ownerId);
	}

	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/pets/new")
	public String initCreationForm(Owner owner, Model model) {
		Pet pet = new Pet();
		owner.getPets().add(pet);
		pet.setOwner(owner);
		model.addAttribute("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/pets/new")
	public String processCreationForm(Owner owner, @Validated Pet pet, BindingResult result, 
			Model model) {
		if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
			result.rejectValue("name", "duplicate", "already exists");
		}
		owner.getPets().add(pet);
		if (result.hasErrors()) {
			model.addAttribute("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}
		else {
			petService.save(pet);
			return "redirect:/owners/" + owner.getId();
		}
	}

	@GetMapping("/pets/{petId}/edit")
	public String initUpdateForm(@PathVariable("petId") Long petId, Model model) {
		Pet pet = petService.findById(petId);
		model.addAttribute("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/pets/{petId}/edit")
	public String processUpdateForm(@Validated Pet pet, BindingResult result, Owner owner, 
			Model model) {
		if (result.hasErrors()) {
			pet.setOwner(owner);
			model.addAttribute("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}
		else {
			owner.getPets().add(pet);
			petService.save(pet);
			return "redirect:/owners/" + owner.getId();
		}
	}

}
