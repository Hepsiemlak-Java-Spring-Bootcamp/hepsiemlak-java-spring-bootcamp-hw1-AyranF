package hafta1_Odev1;

public class commercial extends real_estate{
	private static final long serialVersionUID = 1L;
	int section;
	
	public commercial(String owner, String city, String county, int section, int sqm, int floor, int age) {
		super.sqm = sqm;
		super.floor = floor;
		super.county = county;
		super.city = city;
		super.age = age;
		this.section = section;
		super.owner = owner;
	}

	@Override
	public String toString() {
		return "\n Ýþ yeri bilgileri: \n" +super.toString()+ "\n Kýsým sayýsý: " + section;
	}

}
