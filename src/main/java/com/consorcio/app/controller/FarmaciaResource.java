package com.consorcio.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.consorcio.app.business.FarmaciaProcess;

import lombok.extern.slf4j.Slf4j;

@Component
@RestController
@Slf4j
public class FarmaciaResource {

	@Autowired
	protected FarmaciaProcess farmaciaProcess;

	
	@RequestMapping("/comuna")
	public @ResponseBody ResponseEntity<String> getComuna(@RequestParam("id") String id) {
		try {
			return new ResponseEntity<>(farmaciaProcess.getComuna(id), HttpStatus.OK);
		} catch (Exception e) {
			log.info("getComuna - Exception: ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("/locales")
	public @ResponseBody ResponseEntity<String> getLocales(@RequestParam(value = "id", required = true) String id){
		try {
			return new ResponseEntity<>(farmaciaProcess.getLocales(id), HttpStatus.OK);
		} catch (Exception e) {
			log.info("getComuna - Exception: ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
