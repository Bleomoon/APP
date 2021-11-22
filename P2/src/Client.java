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
    
    public int connectNew(int n, int x, String hostname, ClockDist objdist) throws InterruptedException, RemoteException
    {
        int id = objdist.connect();
        Thread t = new Thread(() -> {
            try {
                objdist.generateNumber(n, x, hostname, id);
            }catch (RemoteException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                objdist.close(id);
            }catch (NoClientException e) {
                System.out.println("The server tried to disconnect a client but nobody was connected !");
            }catch (Exception e) {
                System.out.println(e);
            }
            
        });

        if (id != -1) 
		    t.start();
        else
            System.out.println("There is no thread free for the server !");

        return id;
		
    }

    public int getNumber(int id_client) throws InterruptedException{
		ArrayList<Integer> list =  this.nbs.get(id_client);;

       	while(list == null || list.isEmpty()){
			if (list == null )
				list = this.nbs.get(id_client);
			System.out.println("Waiting for "+ id_client + " the generation of the number...");
			Thread.sleep(100);
        }
        int nb = list.get(0);
        list.remove(0);
        return nb;
    }
}
