package src;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
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
