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

    // handle the maximum number of client connected and create the random idClient
    public int connect() throws RemoteException, InterruptedException 
    {
        synchronized(this){
            if ( cptClient >= MAX_CLIENT)
            {
                System.out.println("A client try to connect but no slot free!");
                return -1;
            }
            
            cptClient ++;
                
            int id_client;
            do{
                id_client = (int) (Math.random() * ( 100000 - 0 ));
            }
            while(idList.contains(id_client));
            System.out.println("Client is connected !\n With ID :" + id_client);
            idList.add(id_client);
            
            return id_client;
        }
    }
    
    // disconnect a client, drcrease number of client connected and remove the id in his list
    public Status close(int id) throws RemoteException, InterruptedException
    {
        synchronized(this){
            if (cptClient <= 0)
                throw new NoClientException(); // if the compteur is null or less. i can't remove the compteur.
            if (!idList.contains(id))
                throw new NoClientException();
            System.out.println("Client " +id+ " is disconnect !");
            cptClient--;

            // remove the id in the list
            for (int i = 0; i < idList.size(); i++)
            {
                if (idList.get(i) == id){
                    idList.remove(i);
                    break;
                }
            }

            return Status.success;
        }
    }

    // function that add n number in the client with x seconds between each
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