package guru.springframework.sfgpetclinic.services.map;


import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import guru.springframework.sfgpetclinic.model.Owner;

class OwnerServiceMapTest {
	
	OwnerServiceMap ownerServiceMap;
	
	final Long ownerId = 1L;
	final String lastName = "Smith";

	@BeforeEach
	void setUp() {
		ownerServiceMap = new OwnerServiceMap();
		ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
	}
	
	@Test
	void findAll() {
		Set<Owner> owners = ownerServiceMap.findAll();
		
		assertEquals(1, owners.size());
	}
	
	@Test
	void findById() {
		Owner owner = ownerServiceMap.findById(ownerId);
		
		assertEquals(ownerId, owner.getId());
	}
	
	@Test
	void saveWithProvidedId() {
		Long id = 2L;
		
		Owner owner = Owner.builder().id(id).build();
		Owner savedOwner = ownerServiceMap.save(owner);
		
		assertEquals(id, savedOwner.getId());
	}
	
	@Test
	void saveWithNoProvidedId() {
		Owner savedOwner = ownerServiceMap.save(Owner.builder().build());
		
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());		
	}
	
	@Test
	void delete() {
		ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
		
		assertEquals(0, ownerServiceMap.findAll().size());
	}
	
	@Test
	void deleteById() {
		ownerServiceMap.deleteById(ownerId);
		
		assertEquals(0, ownerServiceMap.findAll().size());
	}
	
	@Test
	void findByEwistingLastName() {
		Owner owner = ownerServiceMap.findByLastName(lastName);
		
		assertEquals(lastName, owner.getLastName());
	}
	
	@Test
	void findByNotEwistingLastName() {
		Owner owner = ownerServiceMap.findByLastName("Jordan");
		
		assertNull(owner);
	}

}
