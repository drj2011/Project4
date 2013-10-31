import java.util.ArrayList;

public class Author {

	public Author(){
		
	}
	/**
	 * Stores an Author's first name
	 */
	private String firstName;

	/**
	 * Stores an Author's last name
	 */
	private String lastName;

	/**
	 * Stores the list of an author's publications 
	 */
	private ArrayList<Paper> publications = new ArrayList<Paper>();

	/**
	 * @return The list of publications associated with the author
	 */
	public ArrayList<Paper> getPublications() {
		return publications;
	}
	/**
	 * Adds a paper to the ArrayList of papers associated with the author
	 * @param p	The paper to add
	 */
	public void addPublication(Paper p){
		publications.add(p);
	}
	/**
	 * Sets the first and last names of the author
	 * @param names	An array of the last name and first name.  It was easier to do it this way.
	 */
	public void setName(String[] names){
		firstName = names[1];
		lastName = names[0];
	}
}