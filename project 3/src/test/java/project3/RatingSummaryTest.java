package project3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author toufik
 */
public class RatingSummaryTest {

	public RatingSummary rs;
	
	@BeforeEach
	public void setup() {
		rs = new RatingSummary("A1EE2E3N7PW666", 2);
	}

	/**
	* Test sortStats() when range of float stats
	*/
	@Test
	void testSortStats() {
		float expectedOutput = (float)8.8;
		
		List<Float> stats = rs.createList();
		stats.add(new Float(4.2));
		stats.add(new Float(4.8));
		stats.add(new Float(5.1));
		stats.add(new Float(1.2));
		stats.add(new Float(8.8));
		
		rs.setList(stats);
		assertEquals(expectedOutput, rs.sortStats());
	}
	
	/**
	* Test sortStats() when range of float stats
	*/
	@Test
	void testSortNegStats() {
		float expectedOutput = (float)15.1;
		
		List<Float> stats = rs.createList();
		stats.add(new Float(-4.2));
		stats.add(new Float(-4.8));
		stats.add(new Float(15.1));
		stats.add(new Float(-1.2));
		stats.add(new Float(-8.8));
		
		rs.setList(stats);
		assertEquals(expectedOutput, rs.sortStats());

	}
	
	
	/**
	* Test sortStats() when both same
	*/
	@Test
	void testSortSameStats() {
		float expectedOutput = (float)4;
		
		List<Float> stats = rs.createList();
		stats.add(new Float(4));
		stats.add(new Float(4));
		stats.add(new Float(4));
		stats.add(new Float(4));
		stats.add(new Float(4));
		stats.add(new Float(4));
		stats.add(new Float(4));
		stats.add(new Float(4));

		rs.setList(stats);
		assertEquals(expectedOutput, rs.sortStats());
	}
	

	/**
	* Test setDegree() and getDegree using a list of Ratings
	*/
	@Test
	void testSetGetDegreeList() {
		long expectedOutput = (long)2;
		
		List<Rating> rawRatings = new ArrayList<Rating>();
		rawRatings.add(new Rating("A1EE2E3N7PW666","B000GFDAUG",5));
		rawRatings.add(new Rating("AGZ8SM1BGK3CK","B000GFDAUG",4));
		rawRatings.add(new Rating("AQNPK1Q7HIAP3","B000GOYLNC",3));
		rawRatings.add(new Rating("AQNPK1Q7HIAP3","B007427XS4",2));
		rawRatings.add(new Rating("A1EE2E3N7PW666","B007427XS4",5));

		
		rs.setDegree(rawRatings);
		assertEquals(expectedOutput, rs.getDegree());
	}

	
	/**
	* Test setDegree() and getDegree using a specific degree
	*/
	@Test
	void testSetGetDegreeLong() {
		long expectedOutput = (long)53;
		
		
		rs.setDegree((long) 53);
		assertEquals(expectedOutput, rs.getDegree());
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
