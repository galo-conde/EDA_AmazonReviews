package project1;

import java.util.ArrayList;

/* Team needs to import relevant packages here */

import java.util.List;

/**
 * Utility class, all static methods Inventory of the datasets in
 * DATA_FILE_FOLDER, kept in DATA_FILE_NAME
 * @author tesic
 * @author toufik
 */
public class DataAnalysis {

	/** Private Constructor */
	private DataAnalysis() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Implement method
	 * @author conde
	 * @author padilla
	 * @param inlist, list of calculated rating summaries
	 * @return sorted list by number of degrees
	 */
	public static List<AbstractRatingSummary> sortByDegree(List<AbstractRatingSummary> inList) {

		// your code here
		inList.sort((AbstractRatingSummary r1, AbstractRatingSummary r2)->Long.compare(r2.getDegree(), r1.getDegree()));

		return inList;
	}

	/**
	 * Implement method
	 * @author conde
	 * @author padilla
	 * @param inlist, list of calculated rating summaries
	 * @return sorted list by average difference 
	 */
	public static List<AbstractRatingSummary> sortByAvgDiff(List<AbstractRatingSummary> inList) {

		// your code here 
		inList.sort((AbstractRatingSummary r1, AbstractRatingSummary r2)->Float.compare(r2.avgScore(), r1.avgScore()));

		return inList;
	}

	
	/**
	 * Implement method
	 * @author conde
	 * @author padilla
	 * @param k, desired number of top results
	 * @param inlist, list of calculated rating summaries
	 * @return string, formatted report
	 */
	public static String createReport(List<AbstractRatingSummary> inList, int k) {

		//your code here
		StringBuilder outReport = new StringBuilder();

		List<AbstractRatingSummary> revList = new ArrayList<AbstractRatingSummary>();
		List<AbstractRatingSummary> prdList =  new ArrayList<AbstractRatingSummary>();
		

		for(AbstractRatingSummary rec : inList) {
			if (rec.getNodeID().charAt(0) == 'A') {
				revList.add(rec);
			}
			else {
				prdList.add(rec);
			}

		}
		
		//reviewers
		outReport.append("Id,degree,product avg,reviewer avg\n"
				+ "--------------------------------------------------\n"
				+ " Top " + k + " REVIEWER ANALYSIS\n"
				+ "--------------------------------------------------\n"
				+ "Reviewers with highest number of reviews\n");

		revList = sortByDegree(revList);
		for (int i = 0; i < revList.size(); i++){
			outReport.append(revList.get(i).toString());
			if (i == k) 
				break;
		}
		
		outReport.append("Products with discrepnacies per reviwer\n");
		
		revList = sortByAvgDiff(revList);
		for (int i = 0; i < revList.size(); i++){
			outReport.append(revList.get(i).toString());
			if (i == k) 
				break;
		}

		//products

		outReport.append("--------------------------------------------------\n" +
				 " Top " + k + " REVIEWER ANALYSIS\n" +
				"--------------------------------------------------\n" +
				"Products with highest number of reviews\n");
		
		prdList = sortByAvgDiff(prdList);
		for (int i = 0; i < prdList.size(); i++){
			outReport.append(prdList.get(i).toString());
			if (i == k) 
				break;
		}
		
		outReport.append("Products with discrepnacies per reviwer\n");
		
		prdList = sortByAvgDiff(inList);
		for (int i = 0; i < prdList.size(); i++){
			outReport.append(prdList.get(i).toString());
			if (i == k) 
				break;
		}

		return outReport.toString();

	}

	/**
	 * 
	 * The file name of where the database is going to be saved.
	 */
	public static final String LINE_SEP = System.lineSeparator();
	public static final String DELIMITER = ",";
	public static final String DB_FOLDER = "data";
	public static final String DB_FILENAME = "data.csv";
	public static final String DATA_ID_TEMPLATE = "<dataID>";
	public static final String STAT_FILE_TEMPLATE = "ratingSummary_<dataID>.csv";
	public static final String REPORT_FILE_TEMPLATE = "report_<dataID>.csv";
	public static final String RESULTS_FILE_TEMPLATE = "results_<dataID>.csv";
	public static final String SUMMARY_HEADER ="Id,degree,product avg,reviewer avg";
}