package hafta1_Odev1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class ad implements Serializable{
	private static final long serialVersionUID = 1L;
	int id;
	real_estate realEstate;
	boolean isPromoted = false, isActive = true;
	String title;
	BigDecimal price;
	Date createdDate;
	public ad(String title, String price, real_estate realestate) {
		this.title = title;
		this.price = new BigDecimal(price);
		this.createdDate = new Date();
		this.realEstate = realestate;
		try {
			File directory = new File("ads");
		    if (!directory.exists()) {
		        directory.mkdir();
		    }
			directory = new File("ads/id");
		    if (!directory.exists()) {
		        directory.mkdir();
		        new File("ads/id/1").createNewFile();
		    }
		    File fileid = new File("ads/id").listFiles()[0];
			id = Integer.parseInt(fileid.getName())+1;
			fileid.renameTo(new File("ads/id/"+id));
		}catch(Exception e) {
			e.printStackTrace();
		}
		save();
	}
	@Override
	public String toString() {
		return title + "\n Fiyat: " + new DecimalFormat("#,### TL").format(price) + "\n Ýlan Tarihi: "+ (new SimpleDateFormat("dd/MM/yyyy").format(createdDate)) + realEstate.toString();
	}
	public void save() {
			try {
				File directory = new File("ads/"+ realEstate.city);
			    if (!directory.exists())
			        directory.mkdir();
			    directory = new File("ads/"+ realEstate.city+"/"+ realEstate.county);
				if (!directory.exists())
					directory.mkdir();
				FileOutputStream f = new FileOutputStream(new File("ads/"+ realEstate.city +"/"+ realEstate.county+"/"+id));
				ObjectOutputStream o = new ObjectOutputStream(f);
				o.writeObject(this);
				o.close();
				f.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	public static HashSet<ad> readHouse(String city, int price, int rooms, int roomsB){
		HashSet<ad> ads = new HashSet<>();
		if(city.length() != 0 || city.length() != 1) {
			File[] fileCity = new File("ads/"+city.substring(0,1).toUpperCase()+city.substring(1).toLowerCase()).listFiles();
			for(int i = 0; i < fileCity.length; i++) {
				File[] fileCounty = fileCity[i].listFiles(); 
				for(int j = 0; j < fileCounty.length; j++) {
					try {
						FileInputStream fi = new FileInputStream(fileCounty[j]);
						ObjectInputStream oi = new ObjectInputStream(fi);
						ad Ad = (ad) oi.readObject();
						if(new BigDecimal(price+"").compareTo(Ad.price)>=0 && rooms >= ((house)Ad.realEstate).rooms && roomsB >= ((house)Ad.realEstate).roomsB) {
							ads.add(Ad);
						}
						oi.close();
						fi.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		
		
		return ads;
	}
}
