package project3;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.function.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DatasetTest {
	public Dataset ds;
	
	
	
	@BeforeEach
	public void setup() {
		try {
			Path tempFile = Files.createTempFile(null, null);

			ds = new Dataset("", tempFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* Test new Dataset declaration throws IOException
	*/
	@Test
	void exceptionTesting() {
		Executable runnable = () -> ds = new Dataset();
		assertThrows(IOException.class, runnable, "Exception correctly thrown.");
	}
	
	
	/**
	* Test setDataId() and getDataID()
	*/
	@Test
	void testDataIDSetGet() {
		String expectedOutput = "52";
		ds.setDataId("52");
		
		//assertEquals(expectedOutput, ds.getDataId());
		assertThat(expectedOutput, equalTo(ds.getDataId()));
	}
	
	
	/**
	* Test hasStats() with no stats populated
	*/
	@Test
	void testHasNoStats() {
		Boolean expectedOutput = false;
	 
		assertEquals(expectedOutput, ds.hasStats());
		//assertThat(expectedOutput, equalTo(ds.getDataId()));
	}
	
	/**
	* Test hasStats() with stats populated
	*/
	@Test
	void testHasStats() {
		Boolean expectedOutput = true;
		
		List<AbstractRatingSummary> rs = new ArrayList<AbstractRatingSummary>();
		rs.add(new RatingSummary("A1EE2E3N7PW666",3,5,4));
		rs.add(new RatingSummary("AGZ8SM1BGK3CK",4,2,1));
		rs.add(new RatingSummary("AQNPK1Q7HIAP3",3,3,2));
		rs.add(new RatingSummary("B000GOYLNC",2,3,2));
		rs.add(new RatingSummary("B000GFDAUG",5,3,2));
		rs.add(new RatingSummary("B007427XS4",5,1,1));
		
		
		ds.setRatingSummary(rs);
		assertEquals(expectedOutput, ds.hasStats());
		//assertThat(expectedOutput, equalTo(ds.getDataId()));
	}
		
	/**
	* Test saveStats() with stats populated
	*/
	@Test
	void testSaveStats() {
		String expectedOutput = "A1EE2E3N7PW666,3,5.000,4.000\n"
				+ "AGZ8SM1BGK3CK,4,2.000,1.000\n"
				+ "AQNPK1Q7HIAP3,3,3.000,2.000\n"
				+ "B000GOYLNC,2,3.000,2.000\n"
				+ "B000GFDAUG,5,3.000,2.000\n"
				+ "B007427XS4,5,1.000,1.000\n";
		
		List<AbstractRatingSummary> rs = new ArrayList<AbstractRatingSummary>();
		rs.add(new RatingSummary("A1EE2E3N7PW666",3,5,4));
		rs.add(new RatingSummary("AGZ8SM1BGK3CK",4,2,1));
		rs.add(new RatingSummary("AQNPK1Q7HIAP3",3,3,2));
		rs.add(new RatingSummary("B000GOYLNC",2,3,2));
		rs.add(new RatingSummary("B000GFDAUG",5,3,2));
		rs.add(new RatingSummary("B007427XS4",5,1,1));
		ds.setRatingSummary(rs);
		
		String actualOutput = ds.saveStats();
		//System.out.println(actualOutput);
		//System.out.println(actualOutput.toString());
		//assertEquals(expectedOutput, actualOutput);
		assertThat(expectedOutput, equalTo(actualOutput));
	}

}
