package com.consorcio.app.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FarmaciaProcess  {

	@Autowired
	protected RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Cacheable(value="getComuna")
	public String getComuna(String id)  {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("reg_id",id);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		try {
			ResponseEntity<String> response = restTemplate.postForEntity("https://midastest.minsal.cl/farmacias/maps/index.php/utilidades/maps_obtener_comunas_por_regiones", request, String.class);
			return  response.getBody();
			
		} catch (RestClientException e) {
			log.error("getComuna - RestClientException: " + e);
			throw new RestClientException(e.getMessage());
		}
	}

	@Cacheable(value="getFarmacias")
	public String getLocales(String id)  {
		try {
			ResponseEntity<String> response = restTemplate.getForEntity("https://farmanet.minsal.cl/maps/index.php/ws/getLocalesRegion?id_region="+id, String.class);
			return  response.getBody();
			
		} catch (RestClientException e) {
			log.error("getLocales - RestClientException: " + e);
			throw new RestClientException(e.getMessage());
		}
	}


}