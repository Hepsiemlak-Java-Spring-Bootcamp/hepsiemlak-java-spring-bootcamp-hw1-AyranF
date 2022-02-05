package hafta1_Odev1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class message implements Serializable{
	private static final long serialVersionUID = 1L;
	user receiver, sender;
	String message, title;
	boolean isReaded = false;
	boolean deleted = false;
	int id = 0;
	public message(user receiver, user sender, String message, String title) {
		this.receiver = receiver;
		this.sender = sender;
		this.message = message;
		this.title = title;
		send();
	}
	public static void readAllMessage() {
		try {
			for(int i = 1; i < Integer.parseInt(new File("msg/id").listFiles()[0].getName())+1; i++) {
				System.out.println(i);
				FileInputStream fi = new FileInputStream(new File("msg/"+i));
				ObjectInputStream oi = new ObjectInputStream(fi);
				message msg = (message) oi.readObject();
				System.out.println(msg.toString());
				oi.close();
				fi.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String toString() {
		return "Gönderici: "+sender.eMail+" \n" + title + " \n" + message;
	}
	public void send() {
		receiver.receiveMessage(this);
		save();
	}
	public void save() {
		try {
			File directory = new File("msg");
		    if (!directory.exists()) {
		        directory.mkdir();
		    }
			directory = new File("msg/id");
		    if (!directory.exists()) {
		        directory.mkdir();
		        new File("msg/id/0").createNewFile();
		        id = 0;
		    }
		    File fileid = new File("msg/id").listFiles()[0];
			id = Integer.parseInt(fileid.getName())+1;
			fileid.renameTo(new File("msg/id/"+id));
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			FileOutputStream f = new FileOutputStream(new File("msg/"+id));
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
