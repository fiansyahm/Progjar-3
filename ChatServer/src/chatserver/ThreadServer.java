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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

public class ThreadServer extends Thread {
	private Hashtable<String,ThreadClient> ClientList;
	private ServerSocket server;
	
	public ThreadServer() throws IOException {
//		try {
			this.ClientList=new Hashtable<String,ThreadClient>();
			this.server=new ServerSocket(6666);
			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
	}
	
        @Override
	public void run() {
		while(true) {
			try {
//				Socket socket = new ServerSocket(6666).accept();
				Socket socket = this.server.accept();
				ThreadClient tc=new ThreadClient(socket,this);
				tc.start();
				String clientId=socket.getInetAddress().getHostAddress()+":"+socket.getPort();
				this.ClientList.put(clientId, tc);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void sendToAll(Message msg) {
		//iterate through all clients
		Enumeration<String> e=this.ClientList.keys();
		//send the message
		while(e.hasMoreElements()) {
			String clientId=e.nextElement();
			ThreadClient tc=this.ClientList.get(clientId);
			tc.send(msg);
		}
	}
}
