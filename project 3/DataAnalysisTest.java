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


public class DataAnalysisTest {
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
	* Test sortByDegree() for the max degree RatingSummary
	*/
	@Test
	void testSortDegMax() {
		RatingSummary expectedOutput = new RatingSummary("B000GFDAUG",52,3,2);
		
		List<AbstractRatingSummary> rs = new ArrayList<AbstractRatingSummary>();
		rs.add(new RatingSummary("A1EE2E3N7PW666",3,5,4));
		rs.add(new RatingSummary("AGZ8SM1BGK3CK",42,2,1));
		rs.add(new RatingSummary("AQNPK1Q7HIAP3",3,3,2));
		rs.add(new RatingSummary("B000GOYLNC",2,3,2));
		rs.add(new RatingSummary("B000GFDAUG",52,3,2));
		rs.add(new RatingSummary("B007427XS4",1,1,1));

		
		//System.out.println(DataAnalysis.sortByDegree(rs));
		DataAnalysis.sortByDegree(rs);
		//assertEquals(expectedOutput, ds.getDataId());
		assertThat(expectedOutput.toString(), equalTo(rs.get(0).toString()));
	}
	
	/**
	* Test sortByDegree() for the min degree RatingSummary
	*/
	@Test
	void testSortDegMin() {
		RatingSummary expectedOutput = new RatingSummary("B007427XS4",1,1,1);
		
		List<AbstractRatingSummary> rs = new ArrayList<AbstractRatingSummary>();
		rs.add(new RatingSummary("A1EE2E3N7PW666",3,5,4));
		rs.add(new RatingSummary("AGZ8SM1BGK3CK",42,2,1));
		rs.add(new RatingSummary("AQNPK1Q7HIAP3",3,3,2));
		rs.add(new RatingSummary("B000GOYLNC",2,3,2));
		rs.add(new RatingSummary("B000GFDAUG",52,3,2));
		rs.add(new RatingSummary("B007427XS4",1,1,1));
		rs.add(new RatingSummary("B000527XS5",7,1,1));
				

		
		//System.out.println(DataAnalysis.sortByDegree(rs));
		DataAnalysis.sortByDegree(rs);
		//assertEquals(expectedOutput, ds.getDataId());
		assertThat(expectedOutput.toString(), equalTo(rs.get(rs.size()-1).toString()));
	}
	
	/**
	* Test sortByAvgDiff() for the max difference score
	*/
	@Test
	void testSortAvgDiffMax() {
		RatingSummary expectedOutput = new RatingSummary("AGZ8SM1BGK3CK",42,22,1);
		
		List<AbstractRatingSummary> rs = new ArrayList<AbstractRatingSummary>();
		rs.add(new RatingSummary("A1EE2E3N7PW666",3,5,4));
		rs.add(new RatingSummary("AGZ8SM1BGK3CK",42,22,1));
		rs.add(new RatingSummary("AQNPK1Q7HIAP3",3,3,2));
		rs.add(new RatingSummary("B000GOYLNC",2,3,20));
		rs.add(new RatingSummary("B000GFDAUG",52,3,2));
		rs.add(new RatingSummary("B007427XS4",1,1,1));
		
		
		
		DataAnalysis.sortByAvgDiff(rs);
		//assertEquals(expectedOutput, ds.getDataId());
		assertThat(expectedOutput.toString(), equalTo(rs.get(0).toString()));
	}
	
	/**
	* Test sortByAvgDiff() for the min difference score
	*/
	@Test
	void testSortAvgDiffMin() {
		RatingSummary expectedOutput = new RatingSummary("B000GOYLNC",2,2,2);
		
		List<AbstractRatingSummary> rs = new ArrayList<AbstractRatingSummary>();
		rs.add(new RatingSummary("A1EE2E3N7PW666",3,5,44));
		rs.add(new RatingSummary("AGZ8SM1BGK3CK",42,20,1));
		rs.add(new RatingSummary("AQNPK1Q7HIAP3",3,36,2));
		rs.add(new RatingSummary("B000GOYLNC",2,2,2));
		rs.add(new RatingSummary("B000GFDAUG",52,3,2));
		rs.add(new RatingSummary("B007427XS4",1,19,1));
		rs.add(new RatingSummary("B000527XS5",7,1,71));
		System.out.println(DataAnalysis.sortByAvgDiff(rs));
		
		DataAnalysis.sortByAvgDiff(rs);
		//assertEquals(expectedOutput, ds.getDataId());
		assertThat(expectedOutput.toString(), equalTo(rs.get(rs.size()-1).toString()));
	}
	

}
