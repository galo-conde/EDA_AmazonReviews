package project3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tesic
 * @author toufik
 */
public class RatingSummaryHamcrestTest {

public RatingSummary rs;
	
	@BeforeEach
	public void setup() {
		rs = new RatingSummary("A1EE2E3N7PW666", 2);
	}

	/**
	* Test collectStats() with customer
	*/
	@Test
	void testCalculateStatsWithCustomer() {
		List<Rating> rawRatings = new ArrayList<Rating>();
		rawRatings.add(new Rating("A1EE2E3N7PW666","B000GFDAUG",5));
		rawRatings.add(new Rating("AGZ8SM1BGK3CK","B000GFDAUG",4));
		rawRatings.add(new Rating("AQNPK1Q7HIAP3","B000GOYLNC",3));
		rawRatings.add(new Rating("AQNPK1Q7HIAP3","B007427XS4",2));
		rawRatings.add(new Rating("A1EE2E3N7PW666","B007427XS4",5));

		rs.calculateStats(rawRatings);
		String actualOutput = "A1EE2E3N7PW666,2,4.000,5.000\n";
		
		assertThat(actualOutput, equalTo(rs.toString()));
	}
	
} 