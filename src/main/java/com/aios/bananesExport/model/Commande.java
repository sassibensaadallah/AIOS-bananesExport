package com.aios.bananesExport.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long commandeId;
	private int quantite;
	private float prix;
	private String date;
	@ManyToOne(optional = false)
	@JoinColumn(name = "destinataire_id", nullable = false)
	private Destinataire destinataire;
	public Long getCommandeId() {
		return commandeId;
	}

	public void setCommandeId(Long commandeId) {
		this.commandeId = commandeId;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Destinataire getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(Destinataire destinataire) {
		this.destinataire = destinataire;
	}

}
