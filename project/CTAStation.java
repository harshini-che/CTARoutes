//Harshini Chellasamy
//Dec 7, 2018
//CTA Station Class that describes a cta station w/ name, loc, wheelchair accessibility 
//and which line(s) its on

package project;

public class CTAStation extends GeoLocation{
	
	//declare variables of the class
	private String name;
	private String location; 
	private boolean wheelchair; 
	private int red;
	private int green; 
	private int blue; 
	private int brown; 
	private int purple; 
	private int pink;
	private int orange; 
	private int yellow;

	//default constructor
	public CTAStation() {
		name = "Harlem"; 
		lat = 41.88706;
		lng= -87.80486;
		location = "elevated";
		wheelchair = true; 
		red=-1;
		green=0;
		blue=-1;
		brown=-1;
		purple=-1;
		pink=-1;
		orange=-1;
		yellow=-1;
	}
	
	//non-default constructor
	public CTAStation(String n, double la, double ln, String loc, boolean w, int r, int g, int bl, int br, int pu, int pi, int o, int y) {
		this.name=n;
		this.lat=la; 
		this.lng=ln;
		this.location=loc;
		this.wheelchair=w;
		this.red=r;
		this.green=g;
		this.blue=bl;
		this.brown=br;
		this.purple=pu;
		this.pink=pi;
		this.orange=o;
		this.yellow=y;
	}
	
	//accessor methods 
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	public boolean getWheelchair() {
		return wheelchair; 
	}
	public int getRed() {
		return red;
	}
	public int getGreen() {
		return green;
	}
	public int getBlue() {
		return blue;
	}
	public int getBrown() {
		return brown;
	}
	public int getPurple() {
		return purple;
	}
	public int getPink() {
		return pink;
	}
	public int getOrange() {
		return orange;
	}
	public int getYellow() {
		return yellow;
	}

	//mutator methods
	public void setName(String n) {
		this.name=n; 
	}
	public void setLocation(String loc) {
		this.location=loc; 
	}
	public void setWheelchair(boolean w) {
		this.wheelchair=w; 
	}
	public void setRed(int r) {
		this.red=r; 
	}
	public void setGreen(int g) {
		this.green=g;
	}
	public void setBlue(int bl) {
		this.blue=bl;
	}
	public void setBrown(int br) {
		this.brown=br;
	}
	public void setPurple(int pu) {
		this.purple=pu;
	}
	public void setPink(int pi) {
		this.pink=pi;
	}
	public void setOrange(int o) {
		this.orange=o;
	}
	public void setYellow(int y) {
		this.yellow=y;
	}
	
	//check cta line/route of a CTAStation
	public int getRoute() {
		if (this.red>=0)
			return 0;
		if (this.green>=0)
			return 1;
		if (this.blue>=0)
			return 2;
		if (this.brown>=0)
			return 3;
		if (this.purple>=0)
			return 4;
		if (this.pink>=0)
			return 5;
		if (this.orange>=0)
			return 6;
		if (this.yellow>=0)
			return 7;
		else {
			return -1;
		}
	}

	//to-string method
	private String constructString() {
		return "\nStation Name: "+name+", Geolocation: ("+ lat+","+lng+"), Location: "+location+", Wheelchair Accessible: "+wheelchair+
				", Red: "+red+", Green: "+green+", Blue: "+blue+", Brown: "+brown+", Purple: "+purple+
				", Pink: "+pink+", Orange: "+orange+", Yellow: "+yellow;
	}
	public String toString() {
		return constructString();
	}
	
	//equals method 
	public boolean equals(CTAStation a) { 
		return name.equals(a.getName()) && location.equals(a.getLocation()) && (lat == a.getLat()) && (lng == a.getLng()) 
				&& (red == a.getRed()) && (wheelchair == a.getWheelchair()) && (green==a.getGreen()) && (blue==a.getBlue())
				&& (brown == a.getBrown()) && (purple == a.getPurple()) && (pink==a.getPink()) && (orange==a.getOrange())
				&& (yellow ==a.getYellow());
				}

}
