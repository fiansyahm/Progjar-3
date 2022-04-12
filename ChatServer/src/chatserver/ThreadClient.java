/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

/**
 *
 * @author User
 */

import com.chatobject.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadClient extends Thread{
	private Socket socket;
	private ObjectOutputStream ous;
	private ObjectInputStream ois;
	private ThreadServer threadServer;
	
	public ThreadClient(Socket socket,ThreadServer threadServer) throws IOException {
		this.socket=socket;
		this.ous=new ObjectOutputStream(this.socket.getOutputStream());
		this.ois=new ObjectInputStream(this.socket.getInputStream());
		this.threadServer=threadServer;
		
	}
        @Override
	public void run() {
		while(true) {
			try {
				Message msg=(Message) this.ois.readObject();
				this.threadServer.sendToAll(msg);
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void send(Message msg) {
		try {
			this.ous.writeObject(msg);
			this.ous.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
