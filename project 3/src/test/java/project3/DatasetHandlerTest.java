package project3;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;

import org.junit.jupiter.api.function.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DatasetHandlerTest {
	public DatasetHandler dsh;
	
	
	
	@BeforeEach
	public void setup() throws InvalidPathException, IOException {
		dsh = new DatasetHandler();
	}

	/**
	* Test addDataset throws IOException
	*/
	@Test
	void exceptionTestingAddDataset() {
		
		Executable runnable = () -> dsh.addDataset(new Dataset());
		assertThrows(IOException.class, runnable, "Exception correctly thrown.");
	}
	
	/**
	* Test addRatings throws IOException
	*/
	@Test
	void exceptionTestingAddRatings() {
		
		Executable runnable = () -> dsh.addRatings(new Dataset());
		assertThrows(IOException.class, runnable, "Exception correctly thrown.");
	}
	
	/**
	* Test addStats throws IOException
	*/
	@Test
	void exceptionTestingAddStats() {
		
		Executable runnable = () -> dsh.addStats(new Dataset());
		assertThrows(IOException.class, runnable, "Exception correctly thrown.");
	}
	
	
	/**
	* Test setDataId() and getDataID()
	*/
	@Test
	void testDataIDSetGet() {
		int expectedOutput = 7;
		
		//System.out.println(dsh.getDataSets());
		assertEquals(expectedOutput, dsh.getDataSets());
		//assertThat(expectedOutput, equalTo(ds.getDataId()));
	}
	
	
	/**
	* Test checkID()
	*/
	@Test
	void testCheckID() {
		boolean expectedOutput = false;
		
		//System.out.println(dsh.getDataSets());
		//assertEquals(expectedOutput, dsh.checkID("kasjdjfh38882));
		assertThat(expectedOutput, equalTo(dsh.checkID("kasjdjfh38882")));
	}
	

}
