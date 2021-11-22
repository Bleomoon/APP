package Test;

import java.rmi.Naming;
import java.rmi.RemoteException;
import src.*;
import src.Client;


/*
** Création de un client qui fait plusieurs requete
*/
public class TestAttente {
    public static void main(String[] args) throws RemoteException {
        ClockDist objdist = null;
		ClientInt objserv = null;

        try {
            System.out.println("Creation de l'objet.");
            objserv=new Client();
            System.out.println("Enregistrement de l'objet.");
            Naming.rebind("client",objserv);
            System.out.println("serveur operationnel.");

            System.out.println("Searching for object.");
            String url = "rmi://" + args[0] + "/echoservice";
            objdist = (ClockDist) Naming.lookup(url);

            int id1 = ((Client)objserv).connectNew(4, 500, "localhost", objdist);

            for (int i = 0; i < 4; i++) {
                int current = ((Client)objserv).getNumber(id1);
                if (current == -1 ) {
                    break;
                }
                System.out.println("The server generate : " + current);
            }
        }
        catch ( Exception e) {
            System.out.println(e);
        }
    }
}
