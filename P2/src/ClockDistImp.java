package src;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.rmi.Naming;

public class ClockDistImp extends UnicastRemoteObject implements ClockDist
{
    private static int cptClient;
    public static final int MAX_CLIENT = 3;
    private static final long serialVersionUID = 1L;

    public ClockDistImp() throws RemoteException
    {
        ClockDistImp.cptClient = 0;
    }

    public int connect(int n, int x, Client client) throws RemoteException, InterruptedException 
    {
        synchronized(this){
            if ( cptClient >= MAX_CLIENT)
            {
                System.out.println("A client try to connect but no slot free!");
                return -1;
            }
            
            cptClient ++;
            
        }
        ClientInt objdist = null;
        try {
            System.out.println("Searching for object.");
            String url = "rmi://localhost/client";
            objdist = (ClientInt) Naming.lookup(url);
            System.out.println("Client find !");
            
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }


        int number;
        int id_client = (int) (Math.random() * ( 100000 - 0 ));
        System.out.println("Client is connected !\n With ID :" + id_client);

        if (objdist != null) {
            for (int i = 0; i < n ; i++) {
                Thread.sleep(x*1000);
                number = (int) (Math.random() * ( 100000 - 0 ));
                System.out.println("Generate " + number + " client  = " + objdist.toString());
                objdist.add_new(number, 0);
            }
        }
        return 0;
    }
    
    public Status close(int id_client) throws RemoteException, InterruptedException
    {
        synchronized(this){
            if (cptClient <= 0)
                throw new NoClientException(); // if the compteur is null or less. i can't remove the compteur.
            System.out.println("Client " + id_client + " is disconnect !");
			cptClient--;
            return Status.success;
        }
    }

}