package project3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author toufik
 */
class RatingSummaryTest {

	public RatingSummary rs;
	
	@BeforeEach
	public void setup() {
		rs = new RatingSummary("A1EE2E3N7PW666", 2);
	}

	/**
	* Test avgScore() when both same
	*/
	@Test
	void testAvgScoreAllSame() {
		float expectedOutput = 0;
		rs.setList(2, 2);
		
		assertEquals(expectedOutput, rs.avgScore());
	}

	/**
	* Test avgScore() when product avg is bigger
	*/
	@Test
	void testAvgScoreProductAvgIsBigger() {
		float expected = 1;
		rs.setList(3, 2);
		
		assertEquals( expected,rs.avgScore());
	}

	/**
	* Test avgScore() when reviewer avg is bigger
	*/
	@Test
	void testAvgScoreCustIsBigger() {
		float expected = 1;
		rs.setList(2, 3);
		
		assertEquals( expected,rs.avgScore());
	}

}
