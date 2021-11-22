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

    public int getNumber(int id_client) throws InterruptedException{
            ArrayList<Integer> list = this.nbs.get(id_client);
            while(list.isEmpty()){
                    System.out.println("Waiting generation of the number...");
                    Thread.sleep(100);
            }
            int nb = list.get(0);
            list.remove(0);
            return nb;
    }

    public static void main(String[] args) throws RemoteException {
        Client myClient = new Client();
        ClockDist objdist = null;
        int id = 0;
		
        try {
            System.out.println("Searching for object.");
            String url = "rmi://" + args[0] + "/echoservice";
            objdist = (ClockDist) Naming.lookup(url);
            int n = Integer.parseInt(args[1]);
            id = objdist.connect(n, Integer.parseInt(args[2]), myClient);

            for (int i= 0; i < n; i++){
                System.out.println("The generated number " + (i + 1) + " is " + myClient.getNumber(id)); 
            }
        } catch(Exception e) {
            objdist.close();
        }
    }
}
