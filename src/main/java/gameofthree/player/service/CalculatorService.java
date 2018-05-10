package gameofthree.player.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import gameofthree.player.PlayerApplication;

@Service
public class CalculatorService {

	private static final Logger log = LoggerFactory.getLogger(PlayerApplication.class);

	private boolean gameOver = false;
	private boolean gameOn = false;

	public int incept(int min, int max) {
		Random random = new Random();
		int number = random.nextInt(max - min + 1);
		number += min;
		log.info("The start number is: " + number);
		return number;
	}

	public int calculate(int number) {
		if (!gameOn) {
			gameOn = true;
		}

		log.info("The received number is: " + number);

		int addedNumber = 0;
		switch (number % 3) {
		case 1:
			addedNumber = -1;
			break;
		case 2:
			addedNumber = 1;
			break;
		}
		number += addedNumber;
		log.info("The added number is: " + addedNumber);
		number /= 3;

		log.info("The calculated number is: " + number);

		if (number == 1) {
			log.info("WIN!!!");
			gameOver = true;
		}

		return number;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public boolean isGameOn() {
		return gameOn;
	}

}