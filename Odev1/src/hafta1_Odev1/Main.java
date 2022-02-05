package hafta1_Odev1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		user User = null;
		boolean loginRegister = false;
		do {
			System.out.println("L�tfen Se�iniz: \n 1- Kay�t Ol \n 2- Giri� yap");
			switch((new Scanner(System.in).nextInt())) {
				case 1:
					User = Register();
					if(User == null) {
						loginRegister = true;
						break;
					}
					loginRegister = false;
					break;
				case 2:
					User = Login();
					if(User == null) {
						loginRegister = true;
						break;
					}
					loginRegister = false;
					break;
				default:
					System.out.println("Hatal� giri�!\n");
					loginRegister = true;
			}
		}while(loginRegister);
		
		boolean mainMenu = false;
		do {
			System.out.println("\nL�tfen Se�iniz: \n 1- �lan Ver \n 2- �lan Bak \n 3- Kendi �lanlar�ma Bak \n 4- Favori �lanlara Bak \n 5- Mesaj G�nder \n 6- Mesajlar�m� Oku");
			switch((new Scanner(System.in).nextInt())) {
				case 1:
					User.addAd();
					break;
				case 2:
					HashSet<ad> houses = searchHouse();
					for(ad a: houses) {
						System.out.println();
						System.out.println(a.toString());
						System.out.println("\n1- Favoriye ekle: \n2- Devam: ");
						if(new Scanner(System.in).nextInt() == 1) {
							User.addFav(a);
						}
					}
					break;
				case 3:
					User.usersAds.forEach((n)-> System.out.println(n.toString()+"\n"));
					break;
				case 4:
					User.favorites.forEach((n)-> System.out.println(n.toString()));
					break;
				case 5:
					System.out.print("Mesaj Ba�l���: ");
					String msgTitle = (new Scanner(System.in).nextLine());
					System.out.print("Mesaj ��eri�ini Yaz�n�z: ");
					String msg = (new Scanner(System.in).nextLine());
					System.out.print("Al�c� Mail Adresi: ");
					String receiverEmail = (new Scanner(System.in).nextLine());
					User.sendMessage(receiverEmail, msgTitle, msg);
					break;
				case 6:
					User.readMessage();
					break;
				case 7:
					if(User.type.equals("Admin")) {
						message.readAllMessage();
						break;
					}
				default:
					System.out.println("Hatal� giri�!");
					mainMenu = true;
			}
		}while(mainMenu);
		
	}

	public static HashSet searchHouse() {
		System.out.println("Bakmak istedi�iniz ilan�n �zelliklerini giriniz: ");
		System.out.print("�l: ");
		String city = new Scanner(System.in).nextLine().toLowerCase();
		System.out.print("Fiyat: (Yaln�zca Miktar Harf Girmeyiniz) ");
		int price = new Scanner(System.in).nextInt();
		System.out.print("Oda Say�s�: ");
		int rooms = new Scanner(System.in).nextInt();
		System.out.print("Salon Say�s�: ");
		int roomsB = new Scanner(System.in).nextInt();
		return ad.readHouse(city, price, rooms, roomsB);
	}
		
	public static user Register() {
		File directory = new File("users");
	    if (!directory.exists())
	        directory.mkdir();
		System.out.print("ePosta Adresiniz: ");
		String eMail = (new Scanner(System.in).nextLine());
		System.out.print("�ifreniz: ");
		String password = new Scanner(System.in).nextLine();
		for(File file : new File("users/").listFiles()) {
			if(file.getName().equals(eMail)) {
				System.out.println("Bu eposta adresi ile kay�t olunmu�tur\n");
				return null;
			}
		}
		user User = new user("Normal", eMail, password);
		if(eMail.equals("Admin"))
			User.type = "Admin";
		try {
			FileOutputStream f = new FileOutputStream(new File("users/"+eMail));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(User);
			o.close();
			f.close();
			return User;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static user Login() {
		try {
			System.out.print("ePosta Adresiniz: ");
			String eMail = new Scanner(System.in).nextLine();
			String password;
			if(!eMail.equals("ayse.sari@gmail.com")) {
				System.out.print("�ifreniz: ");
				password = new Scanner(System.in).nextLine();
			}else {
				password = "asd";
			}
			FileInputStream fi = new FileInputStream(new File("users/"+eMail));
			ObjectInputStream oi = new ObjectInputStream(fi);
			user User = (user) oi.readObject();
			oi.close();
			fi.close();
			if(User.password.equals(password))
				return User;
			else
				throw new loginException();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
//System.out.println((new ad(new commercial("Ankara", "�ankaya", "5", 150, 1, 0), "sat�l�k ev", "10000000")).toString());