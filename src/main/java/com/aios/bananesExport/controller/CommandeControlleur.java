package com.aios.bananesExport.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

import com.aios.bananesExport.exception.MauvaisDateException;
import com.aios.bananesExport.exception.MauvaisQuantiteException;
import com.aios.bananesExport.model.Commande;
import com.aios.bananesExport.repository.CommandeRepository;

@RestController
public class CommandeControlleur {
	CommandeRepository commandRepo;

	@Autowired
	public CommandeControlleur(CommandeRepository commandRepo) {
		this.commandRepo = commandRepo;
	}

	@GetMapping("/commandes")
	public ResponseEntity<List<Commande>> getAllCommandes() {
		List<Commande> commandes = new ArrayList();
		commandRepo.findAll().forEach(commandes::add);
		if (commandes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(commandes, HttpStatus.OK);
	}

	@GetMapping("/destinataire/{destinataireId}/commandes")
	public ResponseEntity<List<Commande>> getAllCommadsByDestinataireId(
			@PathVariable(value = "destinataireId") Long destinataireId) {

		List<Commande> commandes = commandRepo.findByDestinataireId(destinataireId);
		return new ResponseEntity<>(commandes, HttpStatus.OK);
	}

	@DeleteMapping("/commandes/{id}")
	public ResponseEntity<HttpStatus> deleteDestinataire(@PathVariable("id") long id) {
		commandRepo.deleteById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/commandes")
	public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
		if (commande.getQuantite() < 0 || commande.getQuantite() > 10000 || commande.getQuantite() % 25 != 0) {
			throw new MauvaisQuantiteException();
		}
		if (calculatedateDifferences(commande.getDate()) < 7) {
			throw new MauvaisDateException();
		}
		commande.setPrix((float) (commande.getQuantite() * 2.5));
		commande = commandRepo.save(commande);
		return new ResponseEntity<>(commande, HttpStatus.OK);
	}

	@GetMapping("/commandes/{id}")
	public ResponseEntity<Commande> getCommandeById(@PathVariable("id") long id) {
		Commande commande = new Commande();
		try {
			commande = commandRepo.findById(id).orElseThrow(() -> new NotFoundException());
		} catch (NotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(commande, HttpStatus.OK);
	}

	@PutMapping("/commandes/{id}")
	public ResponseEntity<Commande> updateCommande(@RequestBody Commande commande) {
		if (commande.getQuantite() < 0 || commande.getQuantite() > 10000 || commande.getQuantite() % 25 != 0) {
			throw new MauvaisQuantiteException();
		}
		if (calculatedateDifferences(commande.getDate()) < 7) {
			throw new MauvaisDateException();
		}
		commande.setPrix((float) (commande.getQuantite() * 2.5));
		return new ResponseEntity<>(commandRepo.save(commande), HttpStatus.OK);
	}

	public long calculatedateDifferences(String date) {
		System.out.println("Heere");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		long daysBetween = 0;
		try {
			System.out.println("Heere1");
			LocalDate date1 = LocalDate.parse(date, dtf);
			LocalDate date2 = LocalDate.parse(LocalDate.now().toString(), dtf);
			System.out.println("Heere2");
			daysBetween = ChronoUnit.DAYS.between(date2, date1);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Heere3");
		}
		System.out.println("Heere4");
		return daysBetween;
	}
}
