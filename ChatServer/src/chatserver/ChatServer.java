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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {
	public static void main(String[] args)  {
		ThreadServer ts;
		try {
			ts = new ThreadServer();
			ts.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
