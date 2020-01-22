package com.model2.mvc.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.List;

public class ChatServerSocketThread extends Thread {
	
	private BufferedReader fromClient;
	private PrintWriter toClient;
	private Socket socket;
	private List<ChatServerSocketThread> list;
	private boolean loopFlag;
	private SocketAddress socketAddress;
	private String clientName;
	
	public ChatServerSocketThread() {
	}
	

	public ChatServerSocketThread(Socket socket, List<ChatServerSocketThread> list) {
		this.socket = socket;
		this.list = list;
		
		try {
			fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			toClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			loopFlag = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println("\n[ChatServerSocketThread "+socketAddress+" ] : data�� ���� �۽� Loop START");
		String fromClientData = null;
		
		while(loopFlag) {
			try {
				if((fromClientData = fromClient.readLine()) != null) {
					System.out.println("\n[ServerSocketThread ("+socketAddress+")] : Client ���۹��� Data ==>"+fromClientData);
					this.toAllClient("["+clientName+"] : "+fromClientData);
					//execute(fromClientData.substring(0,3), fromClientData.substring(4));
					
				}else {
					
					System.out.println("\n[ChatServerSocketThread "+socketAddress+"] : Client ���� ����� Thread ������");
					break;
				}
			} catch(SocketException se) {
				se.printStackTrace();
				loopFlag = false;
			} catch (Exception e) {
				e.printStackTrace();
				loopFlag = false;
			}
		}
		
		System.out.println("\n[ChatServerSocketThread("+socketAddress+")] : data�� ����, �۽� Loop End");
		this.close();
	}
	
	public void execute(String protocol, String message) {

		if(protocol.equals("100")) {
			this.clientName = message;
			if(this.hasName(message)) {
				System.out.println("system : ["+clientName+"] ��ȭ�� �ߺ�");
				toClient.println("system : ["+message+"] ��ȭ�� �ߺ�");
				loopFlag = false;
			} else {
				this.toAllClient("system : ["+message+"] ���� �����ϼ̽��ϴ�.");
			}
			
		} else if(protocol.equals("200")) {
			this.toAllClient("["+clientName+"] : "+message);
		} else if(protocol.equals("400")) {
			this.toAllClient("system : ["+clientName+"] ���� �����ϼ̽��ϴ�.");
		}
	}
	
	public synchronized boolean hasName(String clientName) {
		for(ChatServerSocketThread chatServerSocketThread13_teacher : list) {
			if(chatServerSocketThread13_teacher != this 
					&& clientName.equals(chatServerSocketThread13_teacher.getClientName())) {
						return true;
					}
		}
		return false;
	}
	
	public void close() {
		System.out.println(":: close() start...");
		
		try {
			if(toClient != null) {
				toClient.close();
				toClient = null;
			}
			if(fromClient != null) {
				fromClient.close();
				fromClient = null;
			}
			if(socket != null ) {
				socket.close();
				socket = null;
			}
			
			list.remove(this);
			System.out.println("�����ڼ� : "+list.size());
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(":: "+socketAddress+"close() end...\n");
	}


	public void setLoopFlag(boolean loopFlag) {
		this.loopFlag = loopFlag;
	}

	public synchronized void toAllClient(String message) {
		for(ChatServerSocketThread chatServerSocketThread : list ) {
			chatServerSocketThread.getWriter().println(message);
		}
	}
	
	public PrintWriter getWriter() {
		return toClient;
	}
	public String getClientName() {
		return clientName;
	}


}
