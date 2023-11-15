package com.aios.bananesExport.exception;

public class MauvaisDateException extends RuntimeException {
	public MauvaisDateException() {
		super("La date doit etre superieure a la date d aujourdhui de 7 jours au  minimum ");
	}
}
