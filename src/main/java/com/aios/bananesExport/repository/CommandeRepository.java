package com.aios.bananesExport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aios.bananesExport.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
	List<Commande> findByDestinataireId(Long id);
}