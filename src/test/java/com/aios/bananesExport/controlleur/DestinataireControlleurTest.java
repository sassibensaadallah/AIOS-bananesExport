package com.aios.bananesExport.controlleur;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.aios.bananesExport.controller.DestinataireControlleur;
import com.aios.bananesExport.model.Destinataire;
import com.aios.bananesExport.repository.DestinataireRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DestinataireControlleur.class)
class DestinataireControlleurTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	DestinataireRepository destinataireRepository;

	@Test
	public void creerCommande() throws Exception {
		Destinataire destinataire = new Destinataire();
		destinataire.setAdresse("adresse");
		destinataire.setNom("Alain");
		destinataire.setPays("France");
		when(destinataireRepository.save(destinataire)).thenReturn(destinataire);
		mvc.perform(MockMvcRequestBuilders.post("/destinataires").content(asJsonString(destinataire))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(
						"{\"nom\":\"Alain\",\"adresse\":\"adresse\",\"codePostal\":null,\"ville\":null,\"pays\":\"France\",\"destinataireId\":0}"));

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} 
}
