package com.aios.bananesExport.exception;

public class DestintaireExistantException extends RuntimeException {
	public DestintaireExistantException() {
		super("Destinataire deja existant");
	}

}