package guru.springframework.sfgpetclinic.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
	
	Owner findByLastName(String lastName);
	
	Set<Owner> findAllByLastNameLike(String lastName);

}
