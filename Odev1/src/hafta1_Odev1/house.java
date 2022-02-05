package hafta1_Odev1;

public class house extends real_estate{
	private static final long serialVersionUID = 1L;
	int rooms, roomsB;

	public house(String owner, String city, String county, int rooms, int roomsB, int sqm, int floor, int age) {
		super.sqm = sqm;
		super.floor = floor;
		super.county = county.substring(0,1).toUpperCase()+county.substring(1).toLowerCase();
		super.city = city.substring(0,1).toUpperCase()+city.substring(1).toLowerCase();
		super.age = age;
		this.rooms = rooms;
		this.roomsB = roomsB;
		super.owner = owner;
	}
	@Override
	public String toString() {
		return "\nKonut: \n" +super.toString()+ "\n Oda sayýsý: " + rooms + "\n Salon Sayýsý: "+roomsB;
	}
}
