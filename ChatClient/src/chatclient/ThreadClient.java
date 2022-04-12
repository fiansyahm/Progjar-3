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

public class ThreadClient extends Thread{
	private ObjectInputStream ois;
	
	public ThreadClient(ObjectInputStream ois) {
		this.ois=ois;
	}
	
        @Override
	public void run() {
		try {
			Message msg=(Message) this.ois.readObject();
			System.out.println(msg.getSender()+": "+msg.getText());
			
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
