package com.aios.bananesExport.exception;

public class MauvaisQuantiteException extends RuntimeException {
	public MauvaisQuantiteException() {
		super("La quantité doit etre entre 25 et 10000 et doit etre multiple de 25");
	}
}
