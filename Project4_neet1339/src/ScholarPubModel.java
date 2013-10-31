import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;



public class ScholarPubModel  {
	
	/**
	 * List to keep track of who is registered to listen for events from the academicPapersModel.
	 */
	private ArrayList<ActionListener> actionListenerList;
	private ArrayList<Paper> paperList = new ArrayList<Paper>();
	PaperDatabase database = new PaperDatabase();



	public ScholarPubModel() {

	}
	
	public void fillDirectory() throws IOException{
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		//System.out.println("Where is the .txt file containing the located?");
		//String fileLocation = inputReader.readLine();
		String fileLocation = "p.txt";
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
		for (Paper x : paperList) {
			System.out.println(x.getAll());
		}
	
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
	 * Go through the list of ActionListeners and inform each of the ActionEvent e.
	 * Note that we first make a copy of the list and then go through the copy of the list.
	 * We make the copy in a synchronized block to ensure that the list isn't modified during
	 * the copy operation.
	 */
	private void processEvent(ActionEvent e) {
		ArrayList<ActionListener> list;
		
		synchronized (this) {
			if (actionListenerList == null) return;
			list = (ArrayList<ActionListener>)actionListenerList.clone();
		}
		
		for (int i = 0; i < list.size(); i++) {
			ActionListener listener = list.get(i);
			listener.actionPerformed(e);
		}
	}
	
}
