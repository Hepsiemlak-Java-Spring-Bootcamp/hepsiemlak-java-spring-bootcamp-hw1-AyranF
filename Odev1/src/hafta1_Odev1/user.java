package hafta1_Odev1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class user implements Serializable{
	private static final long serialVersionUID = 1L;
	String type;
	String eMail;
	String password;
	Set<ad> favorites;
	Set<ad> usersAds;
	ArrayList<message> inboxUnreaded;
	ArrayList<message> inboxReaded;
	Set<message> outbox;
	public user(String type, String eMail, String password) {
		this.type = type;
		this.eMail = eMail;
		this.password = password;
		this.favorites = new HashSet<>();
		this.usersAds = new HashSet<>();
		inboxUnreaded = new ArrayList<>();
		inboxReaded = new ArrayList<>();
		outbox = new HashSet<>();
	}
	public void sendMessage(String receiverEmail, String title, String msg) {
		try {
			FileInputStream fi = new FileInputStream(new File("users/"+receiverEmail));
			ObjectInputStream oi = new ObjectInputStream(fi);
			user receiver = (user) oi.readObject();
			oi.close();
			fi.close();
			outbox.add(new message(this, receiver, title, msg));
			save();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void readMessage() {
		while(inboxUnreaded.size() != 0) {
			message msg = inboxUnreaded.remove(0);
			System.out.println("Gönderen: "+ msg.sender.eMail+" \n" +msg.title+" \n"+msg.message);
			msg.isReaded = true;
			inboxReaded.add(msg);
		}
	}
	public void receiveMessage(message Msg) {
		inboxUnreaded.add(Msg);
		save();
	}
	
	public void addFav(ad Ad) {
		favorites.add(Ad);
		save();
	}
	public void addAd() {
		System.out.println("Vermek istediðiniz ilan tipini seçiniz: \n 1- Konut \n 2- Ýþ Yeri");
		int i = new Scanner(System.in).nextInt();
		System.out.print("Ýlan Baþlýðý: ");
		String title = new Scanner(System.in).nextLine();
		System.out.print("Fiyat: ");
		String price = new Scanner(System.in).nextLine();
		System.out.print("Ýl: ");
		String city = new Scanner(System.in).nextLine();
		System.out.print("Ýlçe: ");
		String county = new Scanner(System.in).nextLine();
		System.out.print("Alan: ");
		int sqm = new Scanner(System.in).nextInt();
		System.out.print("Bina Yaþý: ");
		int age = new Scanner(System.in).nextInt();
		System.out.print("Kat: ");
		int floor = new Scanner(System.in).nextInt();
		switch(i) {
			case 1:
				System.out.print("Oda Sayýsý: ");
				int rooms = new Scanner(System.in).nextInt();
				System.out.print("Salon Sayýsý: ");
				int roomsB = new Scanner(System.in).nextInt();
				usersAds.add(new ad(title,price, new house(eMail,city, county, rooms, roomsB, sqm, floor, age)));
				save();
				break;
			case 2:
				System.out.println("Kýsým Sayýsý: ");
				int section = new Scanner(System.in).nextInt();
				usersAds.add(new ad(title,price, new commercial(eMail,city, county, section, sqm, floor, age)));
				save();
				break;
		}
	}
	public void save() {
		try {
			FileOutputStream f = new FileOutputStream(new File("users/"+eMail));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(this);
			o.close();
			f.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
/*
 * String city, String county, int rooms, int roomsB, int sqm, int floor, int age
 * String city, String county, String section, int sqm, int floor, int age
*/