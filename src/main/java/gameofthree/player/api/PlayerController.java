package gameofthree.player.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gameofthree.player.PlayerApplication;
import gameofthree.player.service.CalculatorService;

@RestController
public class PlayerController {

	private static final Logger log = LoggerFactory.getLogger(PlayerApplication.class);

	@Autowired
	CalculatorService service;

	@RequestMapping(value = "/number/{number}", method = RequestMethod.GET)
	public int receive(@PathVariable(value = "number") int number) {
		log.info("Player 2 ...");
		number = service.calculate(number);
		return number;
	}

}
