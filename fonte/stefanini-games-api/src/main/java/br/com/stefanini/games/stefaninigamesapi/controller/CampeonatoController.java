package br.com.stefanini.games.stefaninigamesapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {
	
	@GetMapping
	public String getCampeonatos(){
		return "oi";
	}

}
