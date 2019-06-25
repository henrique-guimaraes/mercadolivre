package mercadolivre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mercadolivre.services.Stats;

@RestController
public class StatsController {

	@Autowired
	private Stats statsService;
	
	@GetMapping(value="/stats", produces="application/json")
	@ResponseBody
	public Object isSimian() {
		
		return statsService.retrieveStats();
	}
}
