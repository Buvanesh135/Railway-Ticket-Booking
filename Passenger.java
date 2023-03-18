package List;

public class Passenger {
	static int id=1;
	String name;
	int Passengerid;
	String perferberth;
	String allotedberth;
	public int seatno;
	public Passenger(String name , String perferberth) {
		this.name = name;
		Passengerid =id;
		id=id+1;
		this.perferberth = perferberth;
		this.allotedberth ="";
		this.seatno=1;
	}
	

	

}
