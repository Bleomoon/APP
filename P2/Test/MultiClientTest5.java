package Test;

import java.rmi.Naming;
import java.rmi.RemoteException;
import src.*;

import src.Client;

public class MultiClientTest5 {
    public static void main(String[] args) throws InterruptedException, RemoteException {
        Client myClient = new Client();
        ClockDist objdist = null;
		ClientInt objserv = null;
        int id = 0;

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
        
			myClient.connectNew(n, x,  "localhost", objdist);

            System.out.println("n = " + n + " : x = " + x); 

            int i, cpt = 0;
            for (i = 0; i < 2000000; i ++){
                System.out.println("");
            }

            for (i= 0; i < n; i++){
                System.out.println("The generated number " + (i + 1) + " is " + myClient.getNumber(current_id)); 
            }

            return ;
        } catch(Exception e) {
			System.out.println(e);
            objdist.close();
        }
    }
}
