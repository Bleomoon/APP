package src;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.rmi.Naming;
import java.util.ArrayList;

public class ClockDistImp extends UnicastRemoteObject implements ClockDist
{
    private static int cptClient;
    public static final int MAX_CLIENT = 3;
    private static final long serialVersionUID = 1L;
    private ArrayList<Integer> idList;

    public ClockDistImp() throws RemoteException
    {
        ClockDistImp.cptClient = 0;
        idList = new ArrayList<Integer>();
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
                
            int id_client = (int) (Math.random() * ( 100000 - 0 ));
            System.out.println("Client is connected !\n With ID :" + id_client);
            idList.add(id_client);
            
            return id_client;
        }
    }
    
    public Status close(int id) throws RemoteException, InterruptedException
    {
        synchronized(this){
            if (cptClient <= 0)
                throw new NoClientException(); // if the compteur is null or less. i can't remove the compteur.
            if (!idList.contains(id))
                throw new NoClientException();
            System.out.println("Client " +id+ "is disconnect !");
            cptClient--;
            idList.remove(id);
            return Status.success;
        }
    }
    public void generateNumber(int n, int x, String hostname, int id_client)throws RemoteException, InterruptedException {
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
                objdist.add_new(number, id_client);
            }
        }
    }

}