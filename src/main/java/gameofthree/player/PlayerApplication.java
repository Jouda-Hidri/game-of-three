package gameofthree.player;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import gameofthree.player.service.CalculatorService;

@SpringBootApplication
public class PlayerApplication {

	private static final Logger log = LoggerFactory.getLogger(PlayerApplication.class);
	private static final int PORT1 = 8080;
	private static final int PORT2 = 8081;
	private static final int TIME_SPAN = 1000;
	private int number = 0;

	@Value("${server.port}")
	int serverPort;

	@Autowired
	CalculatorService service;

	public static void main(String[] args) {
		SpringApplication.run(PlayerApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return args -> {
			Thread.sleep(TIME_SPAN);
			if (!service.isGameOn()) {
				number = service.incept(5, 75);
				send();
			}
		};
	}

	public void send() {
		RestTemplate restTemplate = new RestTemplate();
		int port = serverPort == PORT1 ? PORT2 : PORT1;
		String url = "http://localhost:" + port + "/number/" + number;

		try {
			number = restTemplate.getForObject(url, Integer.class);
			log.info("Player 1 ...");
			number = service.calculate(number);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (!service.isGameOver()) {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					send();
				}
			}, TIME_SPAN);
		}
	}
}
