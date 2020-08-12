package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialityRepositoty;
import guru.springframework.sfgpetclinic.services.SpecialityService;

@Service
@Profile("springdatajpa")
public class SpecialitySDJpaService implements SpecialityService {
	
	@Autowired
	private SpecialityRepositoty specialityRepositoty;

	@Override
	public Set<Speciality> findAll() {
		Set<Speciality> specialities = new HashSet<>();
		specialityRepositoty.findAll().forEach(specialities::add);
		return specialities;
	}

	@Override
	public Speciality findById(Long id) {
		Optional<Speciality> optionalSpeciality = specialityRepositoty.findById(id);
		if (optionalSpeciality.isPresent())
			return optionalSpeciality.get();
		else 
			return null;
	}

	@Override
	public Speciality save(Speciality object) {
		return specialityRepositoty.save(object);
	}

	@Override
	public void delete(Speciality object) {
		specialityRepositoty.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		specialityRepositoty.deleteById(id);
	}

}
