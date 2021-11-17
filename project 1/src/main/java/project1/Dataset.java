package project1;

/* Team needs to import relevant packages here */

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * Dataset
 * @author tesic
 * @author toufik
 */
public class Dataset {

	// Constructors

	/**
	 * Default constructor
	 */
	public Dataset() throws IOException{
		this("", Paths.get(""));
	}

	/**
	 * Dataset constructor w 2 parameters
	 * @param dataId unique dataset identifier
	 * @param inRawFile  Amazon rating input file
	 */
	public Dataset(String dataId, Path inRawFile) throws IOException {
		this.dataId = dataId;
		this.rawFile = inRawFile;
		this.numberOfRatings = Files.lines(inRawFile).count();
		this.ratingList = new ArrayList<>();
		this.ratingStat = new ArrayList<>();
	}

	/**
	 * Implement readRatings method
	 * @author conde
	 * @author padilla
	 * @returns int number of ratings
	 */
	public int readRatings() {

		//your code here 

		File inFile = new File(this.getRawFile().toString()); 

		Scanner scnr = null;
		try {
			scnr = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (scnr.hasNextLine())
		{
			String [] ratingVar = scnr.nextLine().split(",");
			Rating newRating = new Rating(ratingVar[0], ratingVar[1], Float.parseFloat(ratingVar[2]));
			this.ratingList.add(newRating);
		}

		scnr.close();

		return this.ratingList.size();

	}

	/**
	 * Implement readStats method
	 * @author conde
	 * @author padilla
	 * @param Path location of the statistics file
	 * @returns int, number of stats
	 */
	public int readStats(Path inStatPath) {
		
		//your code here
		File inFile = new File(inStatPath.toString()) ; 
		Scanner scnr = null;
		try {
			scnr = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int retVal = 0;

		if (scnr.hasNextLine()){

		while (scnr.hasNextLine())
		{
			String [] ratingStatVar = scnr.nextLine().split(",");
			AbstractRatingSummary newRatingStat = new RatingSummary(ratingStatVar[0], Long.parseLong(ratingStatVar[1])); //TODO Add ratingStatVar[2] listOfStats
			this.ratingStat.add(newRatingStat);
		}
		retVal = this.ratingStat.size();
		}
		return retVal;
	}

	/**
	 * Compute Statistics for object ratings
	 * @return if computing statistics from raw file was a success
	 */
	public boolean computeStats() {
		// do not append, start from scratch
		this.ratingStat = new ArrayList<>();

		// create unique list of users and products here
		Set<String> nodeIds = this.ratingList.stream().map(r -> r.getReviewerID()).collect(Collectors.toSet());
		boolean newStats = nodeIds.addAll(this.ratingList.stream().map(r -> r.getProductID()).collect(Collectors.toSet()));

		// Create List from set
		List<String> uniqueList = nodeIds.stream().collect(Collectors.toCollection(ArrayList::new));

		// loop over field
		for (String user : uniqueList) {
			AbstractRatingSummary newSummary = new RatingSummary(user, this.ratingList);
			newStats = this.ratingStat.add(newSummary);
		}
		return newStats;
	}


	/**
	 * Compute Statistics for object ratings
	 * @return if computing statistics from raw file was a success
	 */
	public String saveStats() {

		StringBuilder statString = new StringBuilder();
		// writing a rating summary in each line
		for (AbstractRatingSummary rs : this.getRatingStat()) {
			statString.append(rs.toString());
		}
		return statString.toString();
	}

	/**
	 * Data ID getter
	 * @return  unique dataset identifier
	 */
	public String getDataId() {
		return dataId;
	}

	/**
	 * Data ID setter
	 * @param  dataId set unique dataset identifier
	 */
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	/**
	 * Get ratings filename
	 * @return Path to ratings file
	 */
	public Path getRawFile() {
		return this.rawFile;
	}

	/**
	 * rating list getter
	 * @return list of ratings
	 */
	public List<Rating> getRatingList() {
		return this.ratingList;
	}

	/**
	 * stat list getter
	 * @return list of rating stats
	 */
	public List<AbstractRatingSummary> getRatingStat() {
		return this.ratingStat;
	}

	/**
	 * @return if the object has stats
	 */
	public boolean hasStats(){
		return !(this.ratingStat.isEmpty());
	}

	/**
	 * Rating stat list setter
	 * @param ratingSummary list of rating stats
	 */
	public void f(List<AbstractRatingSummary> ratingSummary) {
		this.ratingStat = ratingSummary;
	}

	/** Print out format is dataID,RAW_FILE,RATINGS_NO,STAT_FILE 
	 * @return formatted output 
	*/
	@Override
	public String toString() {
		return (this.dataId + "," + this.numberOfRatings + "," + this.rawFile.getFileName());
	}

	
	private String dataId;
	private Path rawFile;
	private long numberOfRatings;
	private List<Rating> ratingList;
	private List<AbstractRatingSummary> ratingStat;
}
