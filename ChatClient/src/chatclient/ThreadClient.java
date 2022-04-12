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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ThreadClient extends Thread{
	private ObjectInputStream ois;
        private boolean checklogin=false;
        private String namaUser;
        
         ArrayList<String> users ;
    
       
	
	public ThreadClient(ObjectInputStream ois) {
                users = new ArrayList<String>();
		this.ois=ois;
	}
	

        @Override
	public void run() {
            while(true) {
		try {
			Message msg=(Message) this.ois.readObject();
			
                        if(checklogin==false){
                            namaUser=msg.getSender();
                            checklogin=true;
                        }
                        if(msg.getText().indexOf("private")>=0&&msg.getText().indexOf(namaUser)>=0){
                            int leng=msg.getText().indexOf("|");
//                            System.out.println("leng:"+leng);
                            String tmp=msg.getText().substring(leng+2);
                            System.out.println("from "+msg.getSender()+": "+tmp);
                        }
                        else if(msg.getText().indexOf("public")>=0){
//                            System.out.println("this is public message "+namaUser);
                            System.out.println(msg.getSender()+": "+msg.getText());
                        }
//                        else if(msg.getText().indexOf("login")>=0){
//                            System.out.println("Account "+msg.getSender()+" Has logined");
//                            if(!users.contains(msg.getSender()))
//                                users.add(msg.getSender());
//                        }
//                        else if(msg.getText().indexOf("totaluser")>=0){
//                            for (String i : users) {
//                                System.out.println(i);
//                             }
//                        }
//                         else if(msg.getText().indexOf("userpertama")>=0){
//                            
//                                System.out.println(namaUser);
//                            
//                        }
                         else if(msg.getText().indexOf("User Online")>=0&&msg.getSender().indexOf(namaUser)>=0){
                             System.out.println(msg.getText());
                         }
                        
			
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
                         continue;
		}
            }
	}
}
