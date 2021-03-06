package de.vsis.clh;

public class Person {

	private String _id = "";
	private String FIRST_NAME= "";
	private String LAST_NAME= "";
	private String AGE= "";
	private String PERSON_CURSE_OF_STUDIES= "";
	private String PICTURES= "";

	//getter and setter methods
	public String getId() {
		return _id;
	}
	
	public String getFirstName() {
		return FIRST_NAME;
	}
	
	public String getLastName() {
		return LAST_NAME;
	}
	
	public String getAge() {
		return AGE;
	}
	
	public String getPersonCurseOfStudies() {
		return PERSON_CURSE_OF_STUDIES;
	}
	
	public String getPictureLocation(){
		return PICTURES;
	}
	
	public void setId(String _id) {
		this._id = _id;
	}
	
	public void setFirstName(String firstName) {
		this.FIRST_NAME = firstName;
	}
	
	public void setLastName(String lastName) {
		this.LAST_NAME = lastName;
	}
	
	public void setAge(String age) {
		this.AGE = age;
	}
	
	public void setPersonCurseOfStudies(String personCurseOfStudies) {
		this.PERSON_CURSE_OF_STUDIES = personCurseOfStudies;
	}
	
	public void setPictureLocation(String location){
		this.PICTURES = location;
	}
	
	public String toString(){
		String s = _id + " " + FIRST_NAME + " " + LAST_NAME + " " + AGE + " " + PERSON_CURSE_OF_STUDIES;
		return s;
	}
	
	public boolean empty(){
		boolean r = true;
		if(!FIRST_NAME.equals(""))
			r=false;
		if(!LAST_NAME.equals(""))
			r=false;
		if(!AGE.equals(""))
			r=false;
		if(!PERSON_CURSE_OF_STUDIES.equals(""))
			r=false;
		return r;
	}
	
}
