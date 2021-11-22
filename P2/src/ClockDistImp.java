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

    public int connect() throws RemoteException, InterruptedException 
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

        
        return id_client;
    }
    
    public Status close() throws RemoteException, InterruptedException
    {
        synchronized(this){
            if (cptClient <= 0)
                throw new NoClientException(); // if the compteur is null or less. i can't remove the compteur.
            System.out.println("Client is disconnect !");
			cptClient--;
            return Status.success;
        }
    }
    public void generateNumber(int x, int n, String hostname)throws RemoteException, InterruptedException {
        ClientInt objdist = null;
        try {
            System.out.println("Searching for object.");
            String url = "rmi://"+hostname+"/client";
            objdist = (ClientInt) Naming.lookup(url);
            System.out.println("Client find !");
            
        } catch (Exception e) {
            System.out.println(e);
        }


        int number;
        if (objdist != null) {
            for (int i = 0; i < n ; i++) {
                Thread.sleep(x*1000);
                number = (int) (Math.random() * ( 100000 - 0 ));
                System.out.println("Generate " + number + " client  = " + objdist.toString());
                objdist.add_new(number, 0);
            }
        }
    }

}