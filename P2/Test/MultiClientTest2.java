package Test;

import java.rmi.Naming;
import java.rmi.RemoteException;
import src.*;

import src.Client;

/*
**Creation de 2 clients faisant une requete chacun
*/

public class MultiClientTest2 {
    public static void main(String[] args) throws RemoteException {
        ClockDist objdist = null;

		ClientInt objserv1 = null;
		ClientInt objserv2 = null;

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

            System.out.println("Searching for object.");
            String url = "rmi://" + args[0] + "/echoservice";
            objdist = (ClockDist) Naming.lookup(url);

            int id1 = ((Client)objserv1).connectNew(50, 1, "localhost", objdist);
            int id2 = ((Client)objserv2).connectNew(50, 3, "localhost", objdist);
            Naming.unbind("client");
        }
        catch ( Exception e) {
            System.out.println(e);
        }
    }
}
