//Harshini Chellasamy
//Final Project
//Dec 7, 2018
//CS201 

//Please give me a 96% or more. ;) 

/*TEST plan: 
 1: Be able to create a new station; Enter values of the CTAStation with no issue. Test by printing all stations and noting the appearance of a new station. Repeat a couple of times. 
Tested new station names and gave other data & they appear when i display station names with menu option 1
2: Choose a station to modify and be able to enter new values of the station without an issue. Test by printing all stations and noting a change in the selected station. Repeat a couple of times.
Tested new station names and changed other data & they appear when i display station names with menu option 1
3: Choose a station to remove. Test by printing all stations and noting the missing station. Repeat a couple of times.
REmoved Oakton-Skokie, Harlem, and Chicago, Tested by displaying all station names
4: Be able to enter different station names and display their data. 
It works! But it repeats some of them if they are on more than one line
5: Enter some coordinates and make sure that it returns the nearest station. Repeat a couple of times. 
I entered exact coordinates of 3 different stations and it returned the station i entered
6: Enter two stations. Use Google Maps to confirm the route makes sense. Repeat a couple of times. 
Some work, some dont, the program has trouble when you have to transfer multiple times 
7: Make sure the program closes when you enter 7! from the main menu -- done! 
 */

package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CTAStopApp {
	
	//generate path method
	public static String generatePath(ArrayList<CTARoute> routes, Scanner sc) {
		
		//initialize variables and arraylists
		CTAStation firststation=null; 
		CTAStation secondstation=null;
		CTAStation transfer=null;
		ArrayList<CTAStation> firstsamenamestations = new ArrayList<CTAStation>();
		ArrayList<CTAStation> secondsamenamestations = new ArrayList<CTAStation>();
		int indexvalue1;
		int indexvalue2; 
		
		//prompt for first station's name, add to arraylist & check if there are multiple stations w that name
		System.out.println("Enter the first station: ");
		String station1 = sc.nextLine();
		for (CTARoute r: routes) {	
			if(r.lookupStation(station1)!=null) {
				firstsamenamestations.add(r.lookupStation(station1));	
			}
		}
		
		//if one station only, set first station to that station, else ask which one the user wants
		if(firstsamenamestations.size()==0) {
			System.out.println("Error! Station not found.");
		}
		else if(firstsamenamestations.size()==1) {
			firststation = firstsamenamestations.get(0); 
		} else { 
			System.out.println("Which one?");
			for(int i=0;i<firstsamenamestations.size();i++) {
				System.out.println(i+": "+firstsamenamestations.get(i));
			}
			System.out.println("Enter an array index value: ");
			indexvalue1 = Integer.parseInt(sc.nextLine());
			firststation = firstsamenamestations.get(indexvalue1);
		}

		//do the same for the second station
		System.out.println("Enter the second station: ");
		String station2 = sc.nextLine();
		for (CTARoute r: routes) {
			if(r.lookupStation(station2)!=null) {
				secondsamenamestations.add(r.lookupStation(station2));
			}
		}
		if(secondsamenamestations.size()==1) {
			secondstation = secondsamenamestations.get(0);
		} else { 
			System.out.println("Which one?");
			for(int i=0;i<secondsamenamestations.size();i++) {
				System.out.println(i+"; "+secondsamenamestations.get(i));
			}
			System.out.println("Enter an array index value: ");
			indexvalue2 = Integer.parseInt(sc.nextLine());				
			secondstation = secondsamenamestations.get(indexvalue2);
		}
		
		//if the first station & second station have the same route, get on at the first station and get off at second station
		for (CTARoute r: routes) {	
			if(r.lookupStation(station1)!=null) {
				if(firststation.equals(r.lookupStation(station1))){
				}
			}
		}
		if(firststation.getRoute()==secondstation.getRoute()) {
			System.out.println("Get on the train at "+firststation.getName()+". Get off at "+secondstation.getName());
		}
		
		//if they're not on the same route, find transfer station. 
		else {
			//if there is a station found
			transfer = findTransferStation(routes, firststation, secondstation);
			if(transfer!=null) {
				System.out.println("Get on the train at "+firststation.getName()+". Transfer at "+transfer.getName()+". Get off at "+secondstation.getName());
			}
			//if no transfer station, ..
			else {
				transfer = findTransferStation(routes, firststation, routes.get(0).getStops().get(23));
				CTAStation transfer2 = findTransferStation(routes, transfer, secondstation);
				if(transfer!=null&transfer2!=null) {
				System.out.println("Get on at "+firststation.getName()+". Transfer to "+transfer.getName()+". Transfer to "+transfer2.getName()+". Get off at "+secondstation);
				}else {
					System.out.println("Get on the train at "+firststation.getName()+". Transfer at "+transfer.getName()+". Get off at "+secondstation.getName());
				}
			}
		}
		return null;
	}
	
	//find the transfer station
	public static CTAStation findTransferStation(ArrayList<CTARoute> routes, CTAStation a, CTAStation b) {
		int aline = a.getRoute();
		int bline = b.getRoute();
		CTAStation transfer=null; 
		//for the stations in aline's route, if bline route = aline route, thats the transfer station
		for(int i=0; i<routes.get(aline).getStops().size();i++) {
			if(bline==routes.get(aline).getStops().get(i).getRoute()) { 
				transfer = routes.get(aline).getStops().get(i);
				}
		}
		if (transfer!=null) {
			return transfer;
		}
		else{
			return null;
		}
	}
	
	//display all stations
	public static void displayStationNames(ArrayList<CTARoute> routes, Scanner sc) throws FileNotFoundException {
		//go through each route and print all the stations
		System.out.println("Do you want to save output into file? Enter y or n");
		String userinput = sc.nextLine();
		if(userinput.equalsIgnoreCase("N")) {
			for(int i=0;i<routes.get(0).getStops().size();i++) {
				System.out.println(routes.get(0).getStops().get(i).toString());
			}
			for(int i=0;i<routes.get(1).getStops().size();i++) {
				System.out.println(routes.get(1).getStops().get(i).toString());
			}
			for(int i=0;i<routes.get(2).getStops().size();i++) {
				System.out.println(routes.get(2).getStops().get(i).toString());
			}
			for(int i=0;i<routes.get(3).getStops().size();i++) {
				System.out.println(routes.get(3).getStops().get(i).toString());
			}
			for(int i=0;i<routes.get(4).getStops().size();i++) {
				System.out.println(routes.get(4).getStops().get(i).toString());
			}
			for(int i=0;i<routes.get(5).getStops().size();i++) {
				System.out.println(routes.get(5).getStops().get(i).toString());
			}
			for(int i=0;i<routes.get(6).getStops().size();i++) {
				System.out.println(routes.get(6).getStops().get(i).toString());
			}
			for(int i=0;i<routes.get(7).getStops().size();i++) {
				System.out.println(routes.get(7).getStops().get(i).toString());
			}
		}
		else if(userinput.equalsIgnoreCase("Y")){
	        PrintStream out = new PrintStream(new File("output.txt")); 
	        PrintStream console = System.out; 
	       
	        System.setOut(out); 
	        for(int i=0;i<routes.get(0).getStops().size();i++) {
				System.out.println(routes.get(0).getStops().get(i).toString());
			}
			for(int i=0;i<routes.get(1).getStops().size();i++) {
				System.out.println(routes.get(1).getStops().get(i).toString());
			}
			for(int i=0;i<routes.get(2).getStops().size();i++) {
				System.out.println(routes.get(2).getStops().get(i).toString());
			}
			for(int i=0;i<routes.get(3).getStops().size();i++) {
				System.out.println(routes.get(3).getStops().get(i).toString());
			}
			for(int i=0;i<routes.get(4).getStops().size();i++) {
				System.out.println(routes.get(4).getStops().get(i).toString());
			}
			for(int i=0;i<routes.get(5).getStops().size();i++) {
				System.out.println(routes.get(5).getStops().get(i).toString());
			}
			for(int i=0;i<routes.get(6).getStops().size();i++) {
				System.out.println(routes.get(6).getStops().get(i).toString());
			}
			for(int i=0;i<routes.get(7).getStops().size();i++) {
				System.out.println(routes.get(7).getStops().get(i).toString());
			}
			System.setOut(console); 
			System.out.println("File Saved");
			
		}
	}
	
	//add station to array list of stations/routes
	public static void addStation(ArrayList<CTARoute> routes, Scanner a) {
		CTAStation c = new CTAStation(); 
		
		System.out.println("Enter a station name: ");
		String n = a.nextLine();
		c.setName(n);
		
		System.out.println("Enter the station latitute: ");
		Double la = Double.parseDouble(a.nextLine());
		c.setLat(la);
		
		System.out.println("Enter the station longitude: ");
		Double ln = Double.parseDouble(a.nextLine());
		c.setLng(ln);
		
		System.out.println("Enter location: ");
		String loc = a.nextLine();
		c.setLocation(loc);
		
		System.out.println("Wheelchair accessible? (Enter true/false): ");
		Boolean w = Boolean.parseBoolean(a.nextLine());
		c.setWheelchair(w);
		
		System.out.println("Enter the red value: ");
		int r = Integer.parseInt(a.nextLine());
		c.setRed(r);
		
		System.out.println("Enter the green value: ");
		int g = Integer.parseInt(a.nextLine());
		c.setGreen(g);
		
		System.out.println("Enter the blue value: ");
		int bl = Integer.parseInt(a.nextLine());
		c.setBlue(bl);
		
		System.out.println("Enter the brown value: ");
		int br = Integer.parseInt(a.nextLine());
		c.setBrown(br);
		
		System.out.println("Enter the purple value: ");
		int pu = Integer.parseInt(a.nextLine());
		c.setPurple(pu);
		
		System.out.println("Enter the pink value: ");
		int pi = Integer.parseInt(a.nextLine());
		c.setPink(pi);
		
		System.out.println("Enter the orange value: ");
		int o = Integer.parseInt(a.nextLine());
		c.setOrange(o);
		
		System.out.println("Enter the yellow value: ");
		int y = Integer.parseInt(a.nextLine());
		c.setYellow(y);
		//add station to the right routes 
		if (r >= 0) {
			routes.get(0).addStation(c);
		}
		if (g >= 0) {
			routes.get(1).addStation(c);
		}
		if (bl >= 0) {
			routes.get(2).addStation(c);
		}
		if (br >= 0) {
			routes.get(3).addStation(c);
		}
		if (pu >= 0) {
			routes.get(4).addStation(c);
		}
		if (pi >= 0) {
			routes.get(5).addStation(c);
		}
		if (o >= 0) {
			routes.get(6).addStation(c);
		}
		if (y >= 0) {
			routes.get(7).addStation(c);
		}
	}
	
	//modify a station in the arraylist 
	public static void modifyStation(ArrayList<CTARoute> routes, Scanner a) {
		ArrayList<CTAStation> stationswithsamename = new ArrayList<CTAStation>();
		
		System.out.println("Enter the name of the station you wish to modify: ");
		
		String userinput = a.nextLine();
		CTAStation c = null;
		for (CTARoute r : routes) {
			if(r.lookupStation(userinput)!=null) {
				stationswithsamename.add(r.lookupStation(userinput));
			}
		}
		if(stationswithsamename.size()==1) {
			c = stationswithsamename.get(0);
		}else {
			System.out.println("Which one? Enter an array index value: ");
			for(int i=0;i<stationswithsamename.size();i++) {
				System.out.println(i+"; "+stationswithsamename.get(i));
			}
			int indexvalue = Integer.parseInt(a.nextLine());
			c = stationswithsamename.get(indexvalue);
		}
		
		System.out.println("Enter a new station name: ");
		String n = a.nextLine();
		c.setName(n);
		
		System.out.println("Enter the new station latitute: ");
		Double la = Double.parseDouble(a.nextLine());
		c.setLat(la);
		
		System.out.println("Enter the new station longitude: ");
		Double ln = Double.parseDouble(a.nextLine());
		c.setLng(ln);
		
		System.out.println("Enter the new location: ");
		String loc = a.nextLine();
		c.setLocation(loc);
		
		System.out.println("Wheelchair accessible? (Enter true/false): ");
		Boolean w = Boolean.parseBoolean(a.nextLine());
		c.setWheelchair(w);
		
		System.out.println("Enter the new red value: ");
		int r = Integer.parseInt(a.nextLine());
		c.setRed(r);
		
		System.out.println("Enter the new green value: ");
		int g = Integer.parseInt(a.nextLine());
		c.setGreen(g);
		
		System.out.println("Enter the new blue value: ");
		int bl = Integer.parseInt(a.nextLine());
		c.setBlue(bl);
		
		System.out.println("Enter the new brown value: ");
		int br = Integer.parseInt(a.nextLine());
		c.setBrown(br);
		
		System.out.println("Enter the new purple value: ");
		int pu = Integer.parseInt(a.nextLine());
		c.setPurple(pu);
		
		System.out.println("Enter the new pink value: ");
		int pi = Integer.parseInt(a.nextLine());
		c.setPink(pi);
		
		System.out.println("Enter the new orange value: ");
		int o = Integer.parseInt(a.nextLine());
		c.setOrange(o);
		
		System.out.println("Enter the new yellow value: ");
		int y = Integer.parseInt(a.nextLine());
		c.setYellow(y);
		
		if(r<0) {
			routes.get(0).removeStation(c);
		}
		if(g<0) {
			routes.get(1).removeStation(c);
		}
		if(bl<0) {
			routes.get(2).removeStation(c);
		}
		if(br<0) {
			routes.get(3).removeStation(c);
		}
		if(pu<0) {
			routes.get(4).removeStation(c);
		}
		if(pi<0) {
			routes.get(5).removeStation(c);
		}
		if(o<0) {
			routes.get(6).removeStation(c);
		}
		if(y<0) {
			routes.get(7).removeStation(c);
		}
		if (r >= 0) {
			routes.get(0).addStation(c);
		}
		if (g >= 0) {
			routes.get(1).addStation(c);
		}
		if (bl >= 0) {
			routes.get(2).addStation(c);
		}
		if (br >= 0) {
			routes.get(3).addStation(c);
		}
		if (pu >= 0) {
			routes.get(4).addStation(c);
		}
		if (pi >= 0) {
			routes.get(5).addStation(c);
		}
		if (o >= 0) {
			routes.get(6).addStation(c);
		}
		if (y >= 0) {
			routes.get(7).addStation(c);
		}
	}
	
	//remove a station from the arraylist
	public static void removeStation(ArrayList<CTARoute> routes, Scanner a) {
		ArrayList<CTAStation> stationswithsamename = new ArrayList<CTAStation>();
		System.out.println("Enter a station name to remove: ");
		
		String userinput = a.nextLine();
		CTAStation c = null;
		for (CTARoute r : routes) {
			if(r.lookupStation(userinput)!=null) {
				stationswithsamename.add(r.lookupStation(userinput));
			}
		}
		if(stationswithsamename.size()==1) {
			c = stationswithsamename.get(0);
		}else {
			System.out.println("Which one? Enter an array index value: ");
			for(int i=0;i<stationswithsamename.size();i++) {
				System.out.println(i+"; "+stationswithsamename.get(i));
			}
			int indexvalue = Integer.parseInt(a.nextLine());
			c = stationswithsamename.get(indexvalue);
		}
		//go through routes and remove station that has that name
		for (CTARoute r : routes) {
			c = r.lookupStation(userinput);
			if (c != null) {
				r.removeStation(c);
			}
		}
	}
	
	//search for a station name and print all the stations with that name 
	public static void searchStation(ArrayList<CTARoute> routes, Scanner sc) {
		System.out.println("Enter a station name to find (Case-Sensitive!): ");
		CTAStation target=null;
		String userinput = sc.nextLine();
		//look through all the routes and print all routes that have that name
		for (CTARoute r : routes) {
			target = r.lookupStation((userinput));
			if (target != null) {
				System.out.println(target.toString());
			}
		}
		if(target==null) {
			System.out.println("Station not found! Try again.");
		}
	}
	
	//print the station thats nearest to the lat/lng user entered
	public static void displayNearest(ArrayList<CTARoute> routes, Scanner input) {
		System.out.println("Please enter a latitude.");
		double lat = Double.parseDouble(input.nextLine());
		System.out.println("Please enter a longitude.");
	    double lng = Double.parseDouble(input.nextLine());
	    GeoLocation user = new GeoLocation(lat,lng);
	    double mindist = 100000;
	    CTAStation c=null;
	    //look through routes and get distance for each one. print out the minimum distance station
	    for(int i=0;i<routes.size();i++){
	    	 for(int j=0;j<routes.get(i).getStops().size();j++) {
	    		 double dist = user.calcDistance(routes.get(i).getStops().get(j).getLat(), routes.get(i).getStops().get(j).getLng());
	    		 if (mindist>dist) {
	    		 mindist=dist;
	    		 c = routes.get(i).getStops().get(j);
	    		 }
	    	 }
	 	}
	    System.out.println(c.toString());
	}	

	//main!
	public static void main(String[] args) throws IOException{
		//make a file and add array list to file
		File f = new File("src/project/CTAStops.csv");
		ArrayList<CTARoute> route = new ArrayList<CTARoute>();
		route.add(new CTARoute("Red"));
		route.add(new CTARoute("Green"));
		route.add(new CTARoute("Blue"));
		route.add(new CTARoute("Brown"));
		route.add(new CTARoute("Purple"));
		route.add(new CTARoute("Pink"));
		route.add(new CTARoute("Orange"));
		route.add(new CTARoute("Yellow"));
		Scanner in = new Scanner(f);
		CTAStation station;
		@SuppressWarnings("unused")
		String HeaderLine = in.nextLine();
		@SuppressWarnings("unused")
		String HeaderLine2 = in.nextLine();
		while(in.hasNextLine()) {
			String[] line = in.nextLine().split(","); 
			station = new CTAStation();
			station.setName(line[0]);
			station.setLat(Double.parseDouble(line[1]));
			station.setLng(Double.parseDouble(line[2]));
			station.setLocation(line[3]);
			station.setWheelchair(Boolean.parseBoolean(line[4]));
			station.setRed(Integer.parseInt(line[5]));
			station.setGreen(Integer.parseInt(line[6]));
			station.setBlue(Integer.parseInt(line[7]));
			station.setBrown(Integer.parseInt(line[8]));
			station.setPurple(Integer.parseInt(line[9]));
			station.setPink(Integer.parseInt(line[10]));
			station.setOrange(Integer.parseInt(line[11]));
			station.setYellow(Integer.parseInt(line[12]));
			
			if (station.getRed() >= 0) {
				route.get(0).addStation(station);
			}
			if (station.getGreen() >= 0) {
				route.get(1).addStation(station);
			}
			if (station.getBlue() >= 0) {
				route.get(2).addStation(station);
			}
			if (station.getBrown() >= 0) {
				route.get(3).addStation(station);
			}
			if (station.getPurple() >= 0) {
				route.get(4).addStation(station);
			}
			if (station.getPink() >= 0) {
				route.get(5).addStation(station);
			}
			if (station.getOrange() >= 0) {
				route.get(6).addStation(station);
			}
			if (station.getYellow() >= 0) {
				route.get(7).addStation(station);
			}
		}
		//start menu loop
		while(true) {
			System.out.println("\n-* CTA Stop App Main Menu *- Please enter a menu option:");
			System.out.println("Enter 0 to display information for all stations or save information into a file.");
			System.out.println("Enter 1 to create a new station.");
			System.out.println("Enter 2 to modify an existing station.");
			System.out.println("Enter 3 to remove an existing station.");
			System.out.println("Enter 4 to search for a station and display its information.");
			System.out.println("Enter 5 to find the nearest station.");
			System.out.println("Enter 6 to generate a path between two stations.");
			System.out.println("Enter 7 to exit.");
			Scanner input = new Scanner(System.in);
			int selection=-1;
			try {
				selection=Integer.parseInt(input.nextLine());
				}
				catch(NumberFormatException e) {
					System.out.println("Invalid Entry! Try again.");
				}
			if (selection==1 || selection==0 || selection==2 ||selection==3 ||selection==4 ||selection==5 ||selection==6 ||selection==7) {
			switch(selection) {
			case 0: 
				displayStationNames(route, input);
				break;
			
			case 1:
				try {
					addStation(route,input);
				}
				catch(NumberFormatException e) {
					System.out.println("Invalid Entry! Try again.");
				}
				break; 
		
			case 2: 
				try {
					modifyStation(route, input);
				}
				catch(NumberFormatException|NullPointerException e) {
					System.out.println("Invalid Entry! Try again.");
				}
				break;
			
			case 3: 
				try {
					removeStation(route, input);
				}
				catch(NumberFormatException e) {
					System.out.println("Invalid Entry! Try again.");
				}
				break;
			
			case 4: 
				try {
					searchStation(route, input);
				}
				catch(NumberFormatException e) {
					System.out.println("Invalid Entry! Try again.");
				}
				break; 
			
			case 5:
				try {
					displayNearest(route, input);
				}
				catch(NumberFormatException e) {
					System.out.println("Invalid Entry! Try again.");
				}
				break;
		
			case 6:
				try {
					generatePath(route,input);
				}
				catch(NumberFormatException e) {
					System.out.println("Invalid Entry! Try again.");
				}
				break;
			
			case 7: 
				System.out.println("Exiting Program...");
				in.close();
				input.close();
				System.exit(0);
				break; 	
			}
			}
		} 
	}
}

