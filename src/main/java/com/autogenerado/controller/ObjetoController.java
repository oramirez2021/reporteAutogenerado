package com.autogenerado.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.autogenerado.ws.ObjetoService;

@CrossOrigin
@RestController
@RequestMapping("/reportes")
public class ObjetoController{
	private static final Logger log = LoggerFactory.getLogger(ObjetoService.class);
	@Scheduled(cron = "0 0/5 * * * *")
	@RequestMapping(method = RequestMethod.GET, path = "/GeneraReporteGeneral")
	public @ResponseBody String getObjeto() {
		ObjetoService objService = null;
		try {
			objService = new ObjetoService();
			objService.generaReporteGeneral();
		}catch(Exception ex) {
			log.info(ex.toString());
		}
		return objService.getObjeto();
	}
	
}

