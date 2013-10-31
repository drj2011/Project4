import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.io.*;

/**
 * <P>
 * A class used to create a Paper object.  It can construct a Paper
 * object, set individual variables for a Paper, as well as return any
 * of the variables of the object type Paper.
 * </P>
 */
public class Paper implements Comparable<Paper>, Serializable{

	/**
	 * Stores the type of the paper.
	 */
	private String type;

	
	/**
	 * Stores the authors of the paper.
	 */
	private ArrayList<String> authorList = new ArrayList<String>();

	/**
	 * Stores the title of the paper.
	 */
	private String title;

	/**
	 * Stores the serial title of the paper.
	 */
	private String serialTitle;

	/**
	 * Stores the start and end page numbers, along with the volume and issue if
	 * it is a journal.
	 */
	private String pageNo;

	/**
	 * Stores the date the paper was published.
	 */
	private String pubDate;

	/**
	 * Stores the URL where a digital copy of the paper can be found.
	 */
	private String digitalID;



	/**
	 *<P>
	 * A constructor for an object type Paper.
	 * <P> 
	 * 
	 * @param type - the type of the paper to be assigned to the Paper object.
	 * @param authorList - the list of authors of the paper to be assigned to the Paper object.
	 * @param title - the title of the paper to be assigned to the Paper object.
	 * @param serialTitle the serial title of the paper to be assigned to the Paper object.
	 * @param pageNo - the starting and ending page numbers of the paper to be assigned to the Paper object.
	 * @param pubDate - the publication date of the paper to be assigned to the Paper object.
	 * @param digitalID - the URL of the paper to be assigned to the Paper object.
	 */
	public Paper(ArrayList<String> paperInfo) {
		type = paperInfo.get(0);
		
		String[] paperAuthors = paperInfo.get(1).split("; ");
		for(String s: paperAuthors){
			authorList.add(s);
		}
		
		title = paperInfo.get(2);
		
		serialTitle = paperInfo.get(3);
		
		pageNo = paperInfo.get(4);
		
		pubDate = paperInfo.get(5);
		
		
		if(paperInfo.size() == 7) {
			digitalID = paperInfo.get(6);
		} else {
			digitalID = "";
		}
		
		
		
	}
	
	/**
	 * @param m the string representing the month to be compared to each of the 12 months
	 * @return An int representation of the month inputed.
	 */
	public static int monthToInt(String m){
		
		switch (m) {
		
		case "January": return 0;
		case "February": return 1;
		case "March": return 2;
		case "April": return 3;
		case "May": return 4;
		case "June": return 5;
		case "July": return 6;
		case "August": return 7;
		case "September": return 8;
		case "October": return 9;
		case "November": return 10;
		case "December": return 11;
		
		
		}
		return -1;	
		
	}

	/**
	 * <P>
	 * Used to return a String representing one of the core variables of 
	 * a paper object
	 * <P> 
	 * 
	 * @param option - determined in the PaperDatabase class, option is an
	 * 				   int representation of how to sort the database.
	 * @return Returns a String that represents one of the core variables of 
	 * 		   a paper object, depending on what the option variable is.
	 */
	public String getInfo(int option) {
		String authors = "";

		switch (option) {
		case 0: return type;
		case 1: for (int i = 0; i < authorList.size(); i++) {
			authors = authors + authorList.get(i) + "; ";
		}
		return authors;

		case 2: return title;
		case 3: return serialTitle;
		case 4: return pageNo;
		case 5: return pubDate;
		case 6: return digitalID;
		}
		return null;
	}

	/**
	 * @return A String that has all of the information originally provided by the input file for one paper object.
	 */
	public String getAll() {
		String all = "";
		for(int i = 0; i < 7; i++){

			String temp = getInfo(i);
			if(!temp.equals("")){
				all =  all + temp + "\n";
			}
		}

		return all;
	}
	/**
	 * Getter for the list of authors
	 * @return
	 */
	public ArrayList<String> getAuthors(){
		return authorList;
	}
	/**
	 * @param p - The paper to be compared.
	 * @return The method will return -1 if the object should go before
	 * the object it is being compared to, 0 if it is equal to the object 
	 * being compared to, and 1 if the object should go after the object
	 * it is being compared to.
	 */
	@Override
	public int compareTo(Paper p) {
		return type.compareTo(p.getInfo(0));
	}


	/**
	 * A comparator used to compare paper titles
	 */
	public static Comparator<Paper> PaperTitleComparator = new Comparator<Paper>() {
		public int compare(Paper p1, Paper p2) {

			String p1Txt = p1.getInfo(2);
			String p2Txt = p2.getInfo(2);


			return p1Txt.compareTo(p2Txt);
		}
	};

	/**
	 * A comparator used to compare a paper's authors
	 */
	public static Comparator<Paper> AuthorComparator = new Comparator<Paper>() {
		public int compare(Paper p1, Paper p2) {

			String[] p1Authors = p1.getInfo(1).split("; ");
			String[] p2Authors = p2.getInfo(1).split("; ");


			if(p1Authors.length < p2Authors.length)
			{
				for (int i = 0; i < p1Authors.length; i++) {
					if(p1Authors[i].compareTo(p2Authors[i]) < 0){
						return -1;
					} else if (p1Authors[i].compareTo(p2Authors[i]) > 0){
						return 1;
					}
				}
			} else {
				for (int i = 0; i < p2Authors.length; i++) {
					if(p1Authors[i].compareTo(p2Authors[i]) < 0){
						return -1;
					}	else if (p1Authors[i].compareTo(p2Authors[i]) > 0){
						return 1;
					}

				}
			}

			return 0;
		}
	};



	/**
	 * A comparator used to compare serial titles
	 */
	public static Comparator<Paper> SerialTitleComparator = new Comparator<Paper>() {
		public int compare(Paper p1, Paper p2) {

			String p1Txt = p1.getInfo(3);
			String p2Txt = p2.getInfo(3);


			return p1Txt.compareTo(p2Txt);
		}
	};

	/**
	 * A comparator used to compare paper's published dates
	 */
	public static Comparator<Paper> ChronoComparator = new Comparator<Paper>() {
		public int compare(Paper p1, Paper p2) {

			String[] temp1 = p1.getInfo(5).split(" ");
			String[] temp2 = p2.getInfo(5).split(" ");
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.set(Integer.parseInt(temp1[1]), monthToInt(temp1[0]), 1);
			c2.set(Integer.parseInt(temp2[1]), monthToInt(temp2[0]), 1);
			return c1.compareTo(c2);


		}
	};
}