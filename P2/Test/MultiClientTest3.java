package Test;

import java.rmi.Naming;
import java.rmi.RemoteException;
import src.*;

import src.Client;

/*
**Creation de plusieurs client faisant chacun une requeet
*/

public class MultiClientTest3 {
    public static void main(String[] args) throws RemoteException {
        ClockDist objdist = null;
        
		ClientInt objserv1 = null;
		ClientInt objserv2 = null;
        ClientInt objserv3 = null;
		ClientInt objserv4 = null;

        try {
            System.out.println("Creation de l'objet.");
            objserv1 = new Client();
            System.out.println("Enregistrement de l'objet.");
            Naming.rebind("client",objserv1);
            System.out.println("serveur operationnel.");
            
            System.out.println("Creation de l'objet.");
            objserv2 = new Client();
            System.out.println("Enregistrement de l'objet.");
            Naming.rebind("client",objserv2);
            System.out.println("serveur operationnel.");

            System.out.println("Creation de l'objet.");
            objserv1 = new Client();
            System.out.println("Enregistrement de l'objet.");
            Naming.rebind("client",objserv3);
            System.out.println("serveur operationnel.");
            
            System.out.println("Creation de l'objet.");
            objserv2 = new Client();
            System.out.println("Enregistrement de l'objet.");
            Naming.rebind("client",objserv4);
            System.out.println("serveur operationnel.");

            System.out.println("Searching for object.");
            String url = "rmi://" + args[0] + "/echoservice";
            objdist = (ClockDist) Naming.lookup(url);

            int id1 = objdist.connect();
            int id2 = objdist.connect();
            int id3 = objdist.connect();
            int id4 = objdist.connect();

            ((Client)objserv1).connectNew(50, 1, "localhost", objdist, id1);
            ((Client)objserv2).connectNew(50, 1, "localhost", objdist, id2);
            ((Client)objserv1).connectNew(50, 1, "localhost", objdist, id3);
            ((Client)objserv2).connectNew(50, 1, "localhost", objdist, id4);
        }
        catch ( Exception e) {
            System.out.println(e);
        }
    }
}
