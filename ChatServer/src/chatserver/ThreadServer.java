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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class ThreadServer extends Thread {
	private Hashtable<String,ThreadClient> ClientList;
	private ServerSocket server;
        
        ArrayList<String> users ;
	
	public ThreadServer() throws IOException {
//		try {
			this.ClientList=new Hashtable<String,ThreadClient>();
                        
//                        ServerSocketFactory serverSocketFactory=SSLServerSocketFactory.getDefault();
//                        this.server=(SSLServerSocket) serverSocketFactory.createServerSocket(6666);
			
                        this.server=new ServerSocket(6666);
                        users = new ArrayList<String>();
			
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
            String totaluserstring="User Online\n";
            if(msg.getText().indexOf("Total User")>=0){
//                System.out.println("muncul total user");
                for (String i : users) {
                                totaluserstring=totaluserstring+"\n"+i;
                }
                msg.setText(totaluserstring);
            }
            else if(msg.getText().indexOf("login")>=0){
                            System.out.println("Account "+msg.getSender()+" Has logined");
                            if(!users.contains(msg.getSender()))
                                users.add(msg.getSender());
            }
            else if(msg.getText().indexOf("logout")>=0){
                                users.remove(msg.getSender());
            }
            else{
                System.out.println(msg.getSender()+": "+msg.getText());
            }
                
            
            
             
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
