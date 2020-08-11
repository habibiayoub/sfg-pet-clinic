package guru.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgpetclinic.model.Speciality;

public interface SpecialityRepositoty extends CrudRepository<Speciality, Long> {

}
