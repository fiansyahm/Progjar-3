/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

/**
 *
 * @author User
 */

import com.chatobject.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
                Socket socket=new Socket("localhost",6666);
		ObjectOutputStream ous=new ObjectOutputStream(socket.getOutputStream());
		ThreadClient tc=new ThreadClient(new ObjectInputStream(socket.getInputStream()));
		tc.start();
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter Your Username: ");
		String username=reader.readLine();
		
		while(true) {
			System.out.println("Type Your Message: ");
                        ous=new ObjectOutputStream(socket.getOutputStream());
			String text=reader.readLine();
			Message msg=new Message();
			msg.setSender(username);
			msg.setText(text);
			ous.writeObject(msg);
			ous.flush();
                       
//			ous.close();
//			socket.close();
			
		}
		
//		Person person=new Person();
//		person.setFullname("Eren Jaeger");
//		person.setAge(25);
		
//		ous.writeObject(person);
//		ous.flush();
//		ous.close();
//		socket.close();
	}
}



