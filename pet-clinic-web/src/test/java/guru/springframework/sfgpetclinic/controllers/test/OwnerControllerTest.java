package guru.springframework.sfgpetclinic.controllers.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.beans.HasPropertyWithValue;
import org.hibernate.hql.internal.ast.tree.IsNotNullLogicOperatorNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.sfgpetclinic.controllers.OwnerController;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {
	
	@InjectMocks
	OwnerController ownerController;
	
	@Mock
	OwnerService ownerService;
	
	MockMvc mockMvc;
	Set<Owner> owners;
	
	@BeforeEach
	void setUp() {
		owners = new HashSet<Owner>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());
		
		mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
	}
	
	@Test
	void listOwners() throws Exception {
		when(ownerService.findAll()).thenReturn(owners);
		
		mockMvc.perform(get("/owners"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/index"))
			.andExpect(model().attribute("owners", org.hamcrest.Matchers.hasSize(2)));
		
	}
	
	@Test
	void listOwnersByIndex() throws Exception {
		when(ownerService.findAll()).thenReturn(owners);
		
		mockMvc.perform(get("/owners/index"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/index"))
			.andExpect(model().attribute("owners", org.hamcrest.Matchers.hasSize(2)));
		
	}
	
	@Test
	void findOwners() throws Exception {
		mockMvc.perform(get("/owners/find"))
			.andExpect(status().isOk())
			.andExpect(view().name("notImplementd"));
		
		verifyZeroInteractions(ownerService);
	}
	
	@Test
	void displayOwner() throws Exception {
		when(ownerService.findById(Mockito.anyLong())).thenReturn(Owner.builder().id(1L).build());
		
		mockMvc.perform(get("/owners/123"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/ownerDetails"))
			.andExpect(model().attribute("owner", org.hamcrest.Matchers.hasProperty("id", is(1L))));
		
	}
}
