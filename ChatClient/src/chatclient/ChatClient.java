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
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class ChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
//                SocketFactory socketFactory=SSLSocketFactory.getDefault();
//                Socket socket=socketFactory.createSocket("localhost",6666);
                boolean login=true; 
                Socket socket=new Socket("localhost",6666);
		ObjectOutputStream ous=new ObjectOutputStream(socket.getOutputStream());
		ThreadClient tc=new ThreadClient(new ObjectInputStream(socket.getInputStream()));
		tc.start();
//                ThreadClient2 tc=new ThreadClient2(socket);
//                tc.start();
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter Your Username: ");
		String username=reader.readLine();
                
                Message msg=new Message();
		msg.setSender(username);
		msg.setText("login");
		ous.writeObject(msg);
		ous.flush();
		
		while(true) {
                        if(login==false)break;
			System.out.println("Menu");
                        System.out.println("1.Chat Private");
                        System.out.println("2.Chat Public");
                        System.out.println("3.Check Online User");
                        System.out.println("4.Log Out");
			String text=reader.readLine();
                        switch(text) {
                        case "1":
                          System.out.println("User Destination");
                          String userdest=reader.readLine();
                          System.out.println("Private Message");
                          text=reader.readLine();
                          msg=new Message();
                          msg.setSender(username);
			  msg.setText("private "+userdest+" | "+text);
//                          System.out.println("private "+userdest+" | "+text);
                          System.out.println("To "+userdest+":"+text);
		 	  ous.writeObject(msg);
			  ous.flush();
                          break;
                        case "2":
                          System.out.println("Public Message");
                          text=reader.readLine();
                          msg=new Message();
                          msg.setSender(username);
			  msg.setText("<public> "+text);
                          System.out.println("<public> "+text);
                   
		 	  ous.writeObject(msg);
			  ous.flush();
                          break;
                        case "3":
                          msg=new Message();
                          msg.setSender(username);
			  msg.setText("Total User");
		 	  ous.writeObject(msg);
			  ous.flush();
                          break;
                         case "4":
                          msg=new Message();
                          msg.setSender(username);
			  msg.setText("logout");
                          System.out.println(username+" has logged out");
		 	  ous.writeObject(msg);
			  ous.flush();
                          ous.close();
                          socket.close();
                          login=false;
                          break;
                        default:
                          // code block
                      }
                        
                        
			
                    
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



