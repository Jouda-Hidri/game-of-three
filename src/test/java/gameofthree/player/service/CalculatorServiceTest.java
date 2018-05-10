package gameofthree.player.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class CalculatorServiceTest {

	@InjectMocks
	CalculatorService service;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCalculate() {
		int dummyNumber1 = 20;
		int expectation1 = 7;
		int dummyNumber2 = 16;
		int expectation2 = 5;
		int dummyNumber3 = 30;
		int expectation3 = 10;

		int result1 = service.calculate(dummyNumber1);
		int result2 = service.calculate(dummyNumber2);
		int result3 = service.calculate(dummyNumber3);

		assertThat(result1, is(expectation1));
		assertThat(result2, is(expectation2));
		assertThat(result3, is(expectation3));
	}

	@Test
	public void testIsGameOver() {
		int dummyNumber = 3;
		boolean expectation = true;

		service.calculate(dummyNumber);
		boolean result = service.isGameOver();

		assertThat(result, is(expectation));
	}

}
