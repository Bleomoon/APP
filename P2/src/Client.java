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

	public CLient()
	{
		nbs = new HashMap<Integer, ArrayList<Integer>>();
	}


	@Async
    public void add_new(int generated, int id_client){
		ArrayList<Integer> current = nbs.get(id_client);

		if (current != null)
		{
			current.add(generated);	
		}
	}	

	public int getNumber(int id_client){
		ArrayList list = this.nbs.get(id_client);
		while(list.size() == 0){
			System.out.printl("Waiting generation of the number...");
			Thread.sleep(100);
		}
		return list.get(0);
	}

    public static void main(String[] args) throws RemoteException {
        ClockDist objdist = null;
        int id = 0;
		
        try {
            }
        } catch(Exception e) {
            objdist.close();
        }
    }
}
