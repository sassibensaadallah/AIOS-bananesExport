package com.aios.bananesExport.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aios.bananesExport.exception.DestintaireExistantException;
import com.aios.bananesExport.model.Destinataire;
import com.aios.bananesExport.repository.DestinataireRepository;

@RestController
public class DestinataireControlleur {
	DestinataireRepository destinataireRepo;

	@Autowired
	public DestinataireControlleur(DestinataireRepository destinataireRepo) {
		this.destinataireRepo = destinataireRepo;
	}

	@GetMapping("/destinataires")
	public ResponseEntity<List<Destinataire>> getAllDestinataires() {
		List<Destinataire> destinataires = new ArrayList<Destinataire>();
		destinataireRepo.findAll().forEach(destinataires::add);
		if (destinataires.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(destinataires, HttpStatus.OK);
	}

	@DeleteMapping("/destinataires/{id}")
	public ResponseEntity<HttpStatus> deleteDestinataire(@PathVariable("id") long id) {
		destinataireRepo.deleteById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/destinataires")
	public ResponseEntity<Destinataire> createDestinataire(@RequestBody Destinataire destinataire) {
		if (verifierLexistance(destinataire)) {
			throw new DestintaireExistantException();

		}
		destinataire = destinataireRepo.save(destinataire);
		return new ResponseEntity<>(destinataire, HttpStatus.OK);
	}

	private boolean verifierLexistance(Destinataire destinataire) {
		return destinataireRepo.findAll().contains(destinataire);
	}

	@GetMapping("/destinataires/{id}")
	public ResponseEntity<Destinataire> getDestinataireById(@PathVariable("id") long id) {
		Destinataire destinataire = new Destinataire();
		try {
			destinataire = destinataireRepo.findById(id).orElseThrow(() -> new NotFoundException());
		} catch (NotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(destinataire, HttpStatus.OK);
	}

	@PutMapping("/destinataires/{id}")
	public ResponseEntity<Destinataire> updateDestinataire(@RequestBody Destinataire destinataire) {
		return new ResponseEntity<>(destinataireRepo.save(destinataire), HttpStatus.OK);
	}

}
