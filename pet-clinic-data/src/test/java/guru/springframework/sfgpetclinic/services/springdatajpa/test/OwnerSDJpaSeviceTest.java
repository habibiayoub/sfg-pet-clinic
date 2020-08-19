package guru.springframework.sfgpetclinic.services.springdatajpa.test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.OwnerSDJpaService;

@ExtendWith(MockitoExtension.class)
public class OwnerSDJpaSeviceTest {
	
	@Mock
	OwnerRepository ownerRepository;
	
	@Mock
	PetRepository petRepository;
	
	@Mock
	PetTypeRepository petTypeRepository;
	
	@InjectMocks
	OwnerSDJpaService ownerSDJpaService;
	
	final Long ownerId = 1L;
	final String lastName = "Smith";
	
	Owner returnOwner;
	
	@BeforeEach
	void setUp() {
		returnOwner = Owner.builder().id(ownerId).lastName(lastName).build();
	}
	
	@Test
	void findByLastName(){
		when(ownerRepository.findByLastName(Mockito.anyString())).thenReturn(returnOwner);
		Owner owner = ownerSDJpaService.findByLastName(lastName);
		
		assertEquals(lastName, owner.getLastName());
		verify(ownerRepository).findByLastName(Mockito.anyString());
	}
	
	@Test
	void findAll() {
		Set<Owner> returnOwnerSet = new HashSet<Owner>();
		returnOwnerSet.add(Owner.builder().id(1L).build());
		returnOwnerSet.add(Owner.builder().id(2L).build());
		
		when(ownerRepository.findAll()).thenReturn(returnOwnerSet);
		
		Set<Owner> owners = ownerSDJpaService.findAll();
		
		assertNotNull(owners);
		assertEquals(2, owners.size());
	}
	
	@Test
	void findById() {
		when(ownerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(returnOwner));
	
		Owner owner = ownerSDJpaService.findById(ownerId);
		
		assertNotNull(owner);
	}
	
	@Test
	void findByIdNotFound() {
		when(ownerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
	
		Owner owner = ownerSDJpaService.findById(ownerId);
		
		assertNull(owner);
	}
	
	@Test
	void save() {
		Owner ownerToSave = Owner.builder().id(1L).build();
		
		when(ownerRepository.save(Mockito.any(Owner.class))).thenReturn(returnOwner);
		
		Owner savedOwner = ownerSDJpaService.save(ownerToSave);
		
		assertNotNull(savedOwner);
		verify(ownerRepository).save(Mockito.any());
	}
	
	@Test
	void delete() {
		ownerSDJpaService.delete(returnOwner);
		
		verify(ownerRepository).delete(Mockito.any());
	}
	
	@Test
	void deleteById() {
		ownerSDJpaService.deleteById(ownerId);
		
		verify(ownerRepository).deleteById(Mockito.anyLong());
	}

}
