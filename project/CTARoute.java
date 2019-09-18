//Harshini Chellasamy
//Dec 7, 2018
//CTA Route Classes that holds a list of CTA stations on that route
//(color) name & list of stops

package project;

import java.util.ArrayList;

public class CTARoute extends CTAStation{

	//declare variables of the class
	private String name;
	private ArrayList<CTAStation> stops;
	
	//default constructor 
	public CTARoute() {
		name = "Red";
		stops = new ArrayList<CTAStation>();
	}
	
	public CTARoute(String name) {
		this.name = name;
		stops = new ArrayList<CTAStation>();
	}
	
	//non-default constructor 
	public CTARoute(String n, ArrayList<CTAStation> s) {
		this.name=n;
		this.stops=s;
	}
	
	//accessor methods
	public String getName() {
		return name;
	}
	public ArrayList<CTAStation> getStops(){
		return stops;
	}
	
	//mutator methods
	public void setName(String n) {
		this.name=n; 
	}
	public void setStops(ArrayList<CTAStation> s){
		this.stops=s;
	}
	
	//other methods: add a station, remove a station, insert a station, look up a station 
	public void addStation(CTAStation s) {
		stops.add(s);
	}

	public void removeStation(CTAStation s) {
		stops.remove(s);
	}
	
	public void insertStation(int index, CTAStation s) {
		stops.add(index, s);
	}
		
	public CTAStation lookupStation(String name) {
		for (int i=0;i<stops.size();i++) {
			if (name.equals(stops.get(i).getName())) {
				return stops.get(i);
			}
		}
		return null;
	}
	
	//equals method
	public boolean equals(CTARoute a) { 
		return name.equals(a.getName()) && stops.equals(a.getStops());
	}
	
	//to-string method
	public String toString() {
		return ("Name: "+name+", Stops: "+stops);
	}
	
}