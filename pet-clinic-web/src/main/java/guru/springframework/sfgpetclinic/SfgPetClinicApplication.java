package guru.springframework.sfgpetclinic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import guru.springframework.sfgpetclinic.model.Owner;

@SpringBootApplication
public class SfgPetClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfgPetClinicApplication.class, args);
		
//		List<String> lst = new ArrayList<>(); 
//		lst.add("mom3em");
//		lst.add("kanta");
//		lst.add("aboud");
//		lst.add("9pacha");
//		
//		System.out.println("Initial List : " + lst.toString());
//		
//		Iterator<String> iterator = lst.iterator();
//		while (iterator.hasNext()) {
//			String name = iterator.next();
//			if(name.equals("kanta"))
//				iterator.remove();
//				
//		}
//		System.out.println("Modified List : " + lst.toString());
	}

}
