package com.aios.bananesExport.controlleur;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.aios.bananesExport.controller.CommandeControlleur;
import com.aios.bananesExport.repository.CommandeRepository;

public class CommandeControlleurTest {
	
	//c'est pas utiliser dans la methode a tester donc pas d'exception
	CommandeRepository commandeRepository;
	CommandeControlleur commandControlleur=new CommandeControlleur(commandeRepository);
	
	@Test
	public void calculate_difference_between_two_dates_test() {
		assertEquals(commandControlleur.calculatedateDifferences("2023-11-20"), 5L);
	}
}
