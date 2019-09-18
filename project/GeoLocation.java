//Harshini Chellasamy
//Dec 7, 2018
//GEolocation class with latitude and longitude ! including a calc distance between
// two geolocations method

package project;

public class GeoLocation {
	
	//Instantiate variables 
	protected double lat;
	protected double lng;
	
	//default constructor 
	public GeoLocation(){
		lat = 0.0;
		lng = 0.0;
	}
	
	//non-default constructor
	public GeoLocation(double la, double ln) {
		this.lat = la;
		this.lng = ln;
	}
	
	//accessor methods
	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}
	
	//mutator methods
	public void setLat(double la){
		this.lat = la; 
	}
	
	public void setLng(double ln) {
		this.lng = ln; 
	}
	
	//tostring method
	private String constructString() {
		return "The geolocation is ("+lat+","+lng+")";
	}
	
	public String toString() {
		return constructString();
	}
	
	//calculate distance between one geolocation & a lat & a lng
	public double calcDistance(double lat2, double lng2) {
		double lat1 = Math.toRadians(this.getLat());
		double lng1 = Math.toRadians(this.getLng());
		lat2 = Math.toRadians(lat2);
		lng2 = Math.toRadians(lng2);
		double cos = Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lng1-lng2);
		double arcLength = Math.acos(cos);
		return arcLength*3963.1676; //3963.1676 is earth's radius in miles
	}
	
	//calculate distance between two geolocations
	public double calcDistance(GeoLocation g) {
		double lat2=Math.toRadians(g.getLat());
		double lng2=Math.toRadians(g.getLng());
		double cos=Math.sin(Math.toRadians(this.lat))*Math.sin(lat2)+Math.cos(Math.toRadians(this.lat))*Math.cos(lat2)*Math.cos(Math.toRadians(this.lng)-lng2);
		double arcLength = Math.acos(cos);
		return arcLength*3963.1676;
	}
	
	//equals method
	public boolean equals(GeoLocation g){
		if(this.lat==g.lat&&this.lng==g.lng) {
			return true;
		}
		return false;
	}
}
