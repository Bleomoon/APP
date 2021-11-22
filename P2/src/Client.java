package src;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

import javax.sound.sampled.SourceDataLine;

import java.rmi.server.UnicastRemoteObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Serializable;

public class Client extends UnicastRemoteObject implements ClientInt {
    private HashMap<Integer, ArrayList<Integer>> nbs;
    private static final long serialVersionUID = 1L;

    public Client() throws RemoteException
    {
            nbs = new HashMap<Integer, ArrayList<Integer>>();
    }

    public void add_new(int generated, int id_client) throws RemoteException
	{
		ArrayList<Integer> current = nbs.get(id_client);

		if (current == null) {
            nbs.put(id_client, new ArrayList<Integer>());
            current = nbs.get(id_client);
        }

		synchronized(this) {
			System.out.println(generated + " added for client " + id_client + " !");
			current.add(generated);	
		}
		
	}	
    
    public void connectNew(int n, int x, String hostname, ClockDist objdist, int current_id) throws InterruptedException, RemoteException
    {
        Client client = this;
        Thread t = new Thread(() -> {
            try {

                objdist.generateNumber(n, x, hostname, current_id);
                objdist.close();
            }catch (Exception e) {
                System.out.println(e);
            }
            
        });
		t.start();
		
    }

    public int getNumber(int id_client) throws InterruptedException{
		ArrayList<Integer> list = null;

       	do{
			if (list == null )
				list = this.nbs.get(id_client);
			System.out.println("Waiting for "+ id_client + " the generation of the number...");
			Thread.sleep(100);
        } while(list == null || list.isEmpty());
        int nb = list.get(0);
        list.remove(0);
        return nb;
    }

    public static void main(String[] args) throws InterruptedException, RemoteException {
        Client myClient = new Client();
        ClockDist objdist = null;
        int id = 0;
		ClientInt objserv = null;

        int current_id;
        try {
            System.out.println("Creation de l'objet.");
            objserv=new Client();
			myClient =(Client) objserv;
            System.out.println("Enregistrement de l'objet.");
            Naming.rebind("client",objserv);
            System.out.println("serveur operationnel.");

            System.out.println("Searching for object.");
            String url = "rmi://" + args[0] + "/echoservice";
            objdist = (ClockDist) Naming.lookup(url);
            int n = Integer.parseInt(args[1]);
			int x = Integer.parseInt(args[2]);
        
            current_id = objdist.connect();
			myClient.connectNew(n, x,  "localhost", objdist, current_id);

            System.out.println("n = " + n + " : x = " + x); 
            for (int i= 0; i < n; i++){
                System.out.println("The generated number " + (i + 1) + " is " + myClient.getNumber(current_id)); 
            }
        } catch(Exception e) {
			System.out.println(e);
            objdist.close();
        }
    }
}
