package com.model2.mvc.common.push;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

public class ClientSocketThread extends Thread {

	private BufferedReader fromServer;
	private PrintWriter toServer;
	private Socket socket;
	private int timeOut = 3000;
	private boolean loopFlag;
	
	public ClientSocketThread() {
	}
	
	public ClientSocketThread(String connectIp, int connectPort) {
		try {
			this.socket = new Socket();
			//socket.setSoTimeout(timeOut);
			socket.setSoLinger(true, timeOut);
			
			SocketAddress socketAddress = new InetSocketAddress(connectIp, connectPort);
			socket.connect(socketAddress, timeOut);
			
			toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),true);
			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			
			loopFlag = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void run() {
		
		System.out.println("[ClientThread] : ClientSocketThread.run() START.....");
		
		while(loopFlag) {
			try {
				String fromHostData = fromServer.readLine();
				
				if(fromHostData == null) {
					System.out.println("host로부터 받은 data가 null 이어서 break");
					break;
				}
				
				System.out.println(":: Server에서 수신한 Data : "+fromHostData);
				System.out.print("[전송문자입력 ...Quit ] : ");
			} catch(SocketTimeoutException stoe) {
				stoe.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
				loopFlag=false;
			}
		}
		
		this.close();
		System.out.println("[Client Thread] : ClientSocketThread.run() END.....");
		
	}
	
	public void close() {
		System.out.println(":: close() Start...");
		
		try {
			if(toServer != null) {
				toServer.close();
				toServer = null;
			}
			if(fromServer != null) {
				fromServer.close();
				fromServer = null;
			}
			if(socket != null) {
				socket.close();
				socket = null;
			}
			
			Thread.sleep(timeOut);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(":: close() End...");
	}
	
	public void toServerMessage(String message) {
		toServer.println(message);
	}
	

}
