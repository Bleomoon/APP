package src;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

import javax.sound.sampled.SourceDataLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private HashMap<Integer, ArrayList<Integer>> nbs;

    public Client()
    {
            nbs = new HashMap<Integer, ArrayList<Integer>>();
    }

    public void add_new(int generated, int id_client){
		ArrayList<Integer> current = nbs.get(id_client);

		if (current != null)
		{
			current.add(generated);	
		}
	}	
    
    public void connectNew(int n, int x, ClockDist objdist) throws InterruptedException, RemoteException
    {
        Client client = this;
        Thread t = new Thread(() -> {
			
            System.out.println("Thread go !" + objdist.toString()); 

			if (objdist == null)
            	System.out.println("je suis null !"); 

            try {
                objdist.connect(n, x, client);
            } catch (Exception e){
                try {
            System.out.println("je close !"); 
					
                    objdist.close(0);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                } 
            }
        });
		t.start();
		
    }

    public int getNumber(int id_client) throws InterruptedException{
        ArrayList<Integer> list = this.nbs.get(id_client);
        if (list == null){
            nbs.put(id_client, new ArrayList<Integer>());
            list = nbs.get(id_client);
        }

        while(list.isEmpty()){
                System.out.println("Waiting generation of the number...");
                Thread.sleep(100);
        }
        int nb = list.get(0);
        list.remove(0);
        return nb;
    }

    public static void main(String[] args) throws InterruptedException, RemoteException {
        Client myClient = new Client();
        ClockDist objdist = null;
        int id = 0;
		
        try {
            System.out.println("Searching for object.");
            String url = "rmi://" + args[0] + "/echoservice";
            objdist = (ClockDist) Naming.lookup(url);
            int n = Integer.parseInt(args[1]);
			int x = Integer.parseInt(args[2]);
            
            System.out.println("Launch the client"); 

			myClient.connectNew(n, x, objdist);


            for (int i= 0; i < n; i++){
                System.out.println("The generated number " + (i + 1) + " is " + myClient.getNumber(id)); 
            }
        } catch(Exception e) {
            objdist.close(id);
        }
    }
}
