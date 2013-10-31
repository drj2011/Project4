import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *  <P>
 * A class used to create a PaperDatabase object.  It can construct a PaperDatabase
 * object, sort the Paper objects of the database, search the Paper objects of the
 * database and output the PaperDatabase as a .txt file to a specified location.
 * </P>
 *
 */
/**
 * A class that stores an arraylist of Paper objects, and can complete various actions with them.
 */
public class PaperDatabase {

	private ArrayList<Paper> paperList = new ArrayList<Paper>();
	private HashMap<String, Author> authorHash = new HashMap<String, Author>();

	/**
	 * <P>
	 * A constructor for an object type PaperDatabase.
	 * <P> 
	 */
	public PaperDatabase() {
		
	}
	
	/**
	 * <P>
	 * Used to create the paper database that is used throughout the program.
	 * </P>
	 * 
	 * @throws IOException
	 */
	public void fillDirectory() throws IOException{
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Where is the .txt file containing the located?");
		String fileLocation = inputReader.readLine();
		
		FileReader fr = new FileReader(fileLocation);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> paperData = new ArrayList<String>();
		String[] pData;
		String paperLine = "";
		ArrayList<String> tempInfo = new ArrayList<String>();
		
		int counter = 0;
		Paper p;
		//loads entire file into arraylist
		while(counter < 2){

			if((paperLine = br.readLine()) != null)
			{
				paperData.add(paperLine);
				counter = 0;
			} else {
				paperData.add(paperLine);
				counter++;
			}
		}
		paperData.trimToSize();
		pData = new String[(paperData.size() - 2)];
		for (int i = 0; i < paperData.size() - 2; i++) {
			pData[i] = paperData.get(i);
		}
		
		for(int i = 0; i < (pData.length); i++){
			if(!(pData[i].equals(""))){
				tempInfo.add(pData[i]);
			} else {
				p = new Paper(tempInfo);
				tempInfo.clear();
				paperList.add(p);
			}
			
		}
		p = new Paper(tempInfo);
		tempInfo.clear();
		paperList.add(p);
		for(Paper aper : paperList){
			authorLoad(aper);
		}
		br.close();
		}
	/**
	 * <P>
	 * Sorts the PaperDatabase how the user chooses.  Uses Collections interface.
	 * <P> 
	 * 
	 * @param userInput - Method to sort the database.
	 */
	public void sortRouting(int userInput) {
		
		switch (userInput){
		case 1: Collections.sort(paperList);
		case 2: Collections.sort(paperList, Paper.AuthorComparator);
		case 3: Collections.sort(paperList, Paper.PaperTitleComparator);
		case 4: Collections.sort(paperList, Paper.SerialTitleComparator);
		case 5: Collections.sort(paperList, Paper.ChronoComparator);
		case 6: Collections.shuffle(paperList);
		}

		
	}


	/**
	 * <P>
	 * Searches the PaperDatabase for the paper with the title given.  This is a linear search
	 * <P> 
	 * 
	 * @param userInput - Name of the paper to search for.
	 */
	public void search(String userInput) {
		ArrayList<Paper> searchResults = new ArrayList<Paper>();
		for (int i = 0; i < paperList.size(); i++) {
			for(int j = 0; j < 6; j++){
				if(paperList.get(i).getInfo(j).toLowerCase().contains(userInput.toLowerCase())){
					searchResults.add(paperList.get(i));
					break;

				}
			}
		}
		for (Paper p : searchResults) {
			System.out.println(p.getInfo(0) + "\n" + p.getInfo(1) + "\n" + p.getInfo(2) +
					"\n" + p.getInfo(3) + "\n" + p.getInfo(4) + "\n" + p.getInfo(5));
			if(!p.getInfo(6).equals("")){
				System.out.println(p.getInfo(6));
			}
			System.out.println("");
		}
	}
	/**
	 * This method uses Collections to search with a binary search based on search type and a key
	 * 
	 * @param searchType	How to search, either by Paper Title (PT) or Serial Title (ST)
	 * @param key			The string to search for
	 */
	public void binarySearch(String searchType, String key){
		ArrayList<String> biSearch = new ArrayList<String>();
		if(searchType.equals("PT")){
			for(Paper p : paperList){
				biSearch.add(p.getInfo(2));
			}
		}else{
			for(Paper p : paperList){
				biSearch.add(p.getInfo(3));
			}		}
		int index = Collections.binarySearch(biSearch,key);
		if(index >= 0){
			System.out.println(paperList.get(index).getAll());
		}
	}

	/** 
	 * <P>
	 * Outputs PaperDatabase to text file.
	 * <P> 
	 */
	public void output(){
		for(Paper p: paperList){
			System.out.println(p.getInfo(0));
			System.out.println(p.getInfo(1));
			System.out.println(p.getInfo(2));
			System.out.println(p.getInfo(3));
			System.out.println(p.getInfo(4));
			System.out.println(p.getInfo(5));
			if (p.getInfo(6).matches(".*[a-zA-Z]+.*")){
				System.out.println(p.getInfo(6));
			}
			System.out.println("");

		}
		
	}
	
	/**
	 * Writes the file in the specified format.
	 * 
	 * @param fileName		The file name to write to
	 * @throws IOException
	 */
	public void fileWriter(String fileName) throws IOException{
		FileWriter outfile = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(outfile);
		for (Paper p : paperList) {
			bw.write(p.getAll());
			bw.newLine();
		}
		
		
		bw.close();
	}
	/**
	 * Writes the ArrayList paperList to a user-specified file name
	 * 
	 * @param fileName	The file the user wants to write to.
	 * @throws IOException
	 */
	public void binaryFileWriter(String fileName) throws IOException{
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(paperList);
		out.close();
		fileOut.close();
	}
	/**
	 * Reads in the serialized arrayList paperList and updates the database
	 * 
	 * @param fileName	The file to read from
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public void binaryFileReader(String fileName) throws IOException, ClassNotFoundException{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
		paperList = (ArrayList<Paper>) in.readObject();
		in.close();
	}
	/**
	 * Creates a hashmap of Strings pointing to Authors.  Also, Associates papers with specific authors
	 * @param p	The paper to get authors from
	 */
	public void authorLoad(Paper p){
		ArrayList<String> authorString = new ArrayList<String>();
		authorString = p.getAuthors();
		for(String s : authorString){
			if(authorHash.containsKey(s)){
				authorHash.get(s).addPublication(p);
			}else{
				Author a = new Author();	//Creates a temporary author, adds the publication to it, and adds it to the hashmap
				String[] names = s.split(", ");  //Turns (Last Name, First Name) into [Last Name First Name]
				a.addPublication(p);
				a.setName(names);
				authorHash.put(s, a);
			}
		}
	}
	/**
	 * Uses the hashmap to get an author from the name
	 * @param name	The string key
	 * @return	Returns the Author object corresponding to the key
	 */
	public Author getAuthor(String name){
		return authorHash.get(name);
	}
	/**
	 * Nonfunctioning GUI stuff.  Frankly, I have no clue what to do about the graphs.
	 * @param aName
	 * @param type
	 */
	public void showGraph(String aName, String type){


	}





}