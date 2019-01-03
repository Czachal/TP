import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main {

    private static HashSet<String> names = new HashSet<String>();

    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

	private static  ArrayList<ServerSocket> clients = new ArrayList<ServerSocket>();
	
	public static void main(String[] args) throws Exception {
	
		int size=4;
		ServerSocket listener = new ServerSocket(7171);
		JOptionPane.showMessageDialog(null,"Server zostal wlaczony!");
		 try {
            while(true) {
				
                new Handler(listener.accept()).start();
				
				Socket socket = listener.accept();
				Manager map = new Manager(size);
		
				map.beginturn();
				System.out.println("Klt polaczony");
            }
        } finally {
            listener.close();
        }
		
		
		
		

	}
	private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
		
		
        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {


                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!names.contains(name)) {
                            names.add(name);
                            break;
                        }
                    }
                }

            
                out.println("NAMEACCEPTED");
                writers.add(out);
				
                while (true) {
                    String input = in.readLine();
					String st;
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " + name + ": " + input);
						try{
					DataInputStream dis = new DataInputStream(socket.getInputStream());
						
						st = dis.readUTF();
						System.out.println(""+st);
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						dos.writeUTF(""+st);
						dos.flush();
						}catch(IOException e){System.out.println("Blad z polaczeniem");}
                    }
						
				
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
            
                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
