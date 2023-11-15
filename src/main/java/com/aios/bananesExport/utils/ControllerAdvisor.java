package com.aios.bananesExport.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.aios.bananesExport.exception.DestintaireExistantException;
import com.aios.bananesExport.exception.MauvaisDateException;
import com.aios.bananesExport.exception.MauvaisQuantiteException;

@ControllerAdvice
public class ControllerAdvisor {
	@ExceptionHandler(DestintaireExistantException.class)
	public ResponseEntity<Object> handleDestintaireExistantException(DestintaireExistantException ex,
			WebRequest request) {
		Map<String, Object> body = new LinkedHashMap();
		body.put("message", "Destinataire deja existant ");
		return new ResponseEntity<>(body, HttpStatus.FOUND);
	}

	@ExceptionHandler(MauvaisDateException.class)
	public ResponseEntity<Object> handleMauvaisDateException(MauvaisDateException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap();
		body.put("message", "La date doit etre superieure a la date d aujourdhui de 7 jours au minimum");
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MauvaisQuantiteException.class)
	public ResponseEntity<Object> handleMauvaisQuantiteException(MauvaisQuantiteException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap();
		body.put("message", "La quantité doit etre entre 25 et 10000 ");
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
