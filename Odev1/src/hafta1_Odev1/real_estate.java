package hafta1_Odev1;

import java.io.Serializable;

public class real_estate implements Serializable{
	private static final long serialVersionUID = 1L;
	String owner;
	String city, county;
	int sqm, floor, age;
	@Override
	public String toString() {
		return " �l: " + city + "\n �l�e: " + county + "\n Metre Kare: " + sqm + "\n Kat: " + floor + "\n Bina Ya��: " + age;
	}
}
