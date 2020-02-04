package com.model2.mvc.common.NOTUSED;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;


public class ChatServerApp {

	public ChatServerApp() {
	}

	public static void main(String[] args) {

		System.out.println("===========================");
		System.out.println("[ChatServerApp Main] : STARTUP .... \n");
		
		List<ChatServerSocketThread> list = new Vector<ChatServerSocketThread>(10, 10);
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		ChatServerSocketThread serverSocketThread = null;
		
		boolean loopFlag = false;
		
		try {
			serverSocket = new ServerSocket(7000);
			loopFlag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while (loopFlag) {
			try {
				System.out.println("\n\t\t\t\t/////////////////////////////////////////////////////");
				System.out.println("\t\t\t\t[ChatServerApp Main Thread] : Client Connection Wait");
				socket = serverSocket.accept();
				System.out.println("\n\t\t\t\t[Host Main Thread] : client"+socket.getRemoteSocketAddress()+" 연결");
				serverSocketThread = new ChatServerSocketThread(socket, list);
				list.add(serverSocketThread);
				
				System.out.println("\t\t\t\t[ChatServerApp Main Thread] : 총 접속자수 "+list.size());
				serverSocketThread.start();
				
			} catch(Exception e) {
				e.printStackTrace();
				loopFlag = false;
			}
		}
		
		System.out.println("\t\t\t\t[ChatServerApp Main Thread] : Client Connection Wait END");
		System.out.println("\n\t\t\t\t/////////////////////////////////////////////////\n");
		
		
		synchronized(list) {
			for(ChatServerSocketThread thread: list) {
				thread.setLoopFlag(false);
			}
		}
				
		while(true) {
			if(list.size()!=0) {
				try {
					Thread.sleep(1000);
				} catch(InterruptedException e) {}
			}else {
				break;
			}
		}
		
		try {
			if(serverSocket != null) {
				serverSocket.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n[ChatServerApp Main Thread] : SHUTDOWN");
		System.out.println("================================================");
		
	}

}
