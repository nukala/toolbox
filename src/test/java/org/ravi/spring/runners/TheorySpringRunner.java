package org.ravi.spring.runners;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

@RunWith(Theories.class)
@ContextConfiguration(classes = { TheorySpringRunner.TheoryCfg.class })
public class TheorySpringRunner {
	private static final Logger logger = LoggerFactory.getLogger(TheorySpringRunner.class);

	 static class TheoryCfg {
		@Bean
		public Adder makeAdder() {
			return new Adder() {
				@Override
				public int add(int i, int k) {
					return i + k;
				}
			};
		}
	}

	@Before
	public void loadSpringElseTheoryDoesnotWork() throws Exception {
		TestContextManager tcm = new TestContextManager(getClass());
		tcm.prepareTestInstance(this);
		// also load mockito manager if needed!
	}

	@Autowired
	private Adder adder;

	@DataPoints
	public static int[] integers() {
		return new int[] { 1, 3, 5, 9 };
	}

	@Theory
	public void withTwoNumbers(Integer i, Integer k) {
		logger.info("i={}, k={},", i, k);
		// assertTrue(k > 0);
		assertTrue(i + k == adder.add(i, k));
	}

	@Theory
	public void echoTest(Integer i) {
		logger.info("using only 1 parameter i={}", i);
	}

	@Test
	public void toSeeIffTestAndTheoryMayBeCombined() {
		logger.info("Seems we can combine theories and simple tests tooo !");
	}
}
