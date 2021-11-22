package src;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;

public class ClockDistImp extends UnicastRemoteObject implements ClockDist
{
    private static int cptClient;
    public static final int MAX_CLIENT = 3;

    public ClockDistImp() throws RemoteException
    {
        ClockDistImp.cptClient = 0;
    }

    public int connect(int n, int x, Client client) throws RemoteException, InterruptedException // return the client id or -1 if client pool is overflow
    {
        synchronized(this){
            if ( cptClient >= MAX_CLIENT)
            {
                System.out.println("A client try to connect but no slot free!");
                return -1;
            }

            cptClient ++;
            int number;
            int id_client = (int) (Math.random() * ( 100000 - 0 ));
            System.out.println("Client is connected !\n With ID :" + id_client);

            while (client != null){
                Thread.sleep(x*1000);
                for (int i = 0; i < n ; i++) {
                    number = (int) (Math.random() * ( 100000 - 0 ));
                    client.add_new(number, id_client);
                }
            }
            return 0;
        }
    }
    
    public Status close(int id_client) throws NoClientException
    {
        synchronized(this){
            if (cptClient <= 0)
                throw new NoClientException(); // if the compteur is null or less. i can't remove the compteur.
            System.out.println("Client " + id_client + " is disconnect !");
			cptClient--;
            return Status.success;
        }
    }

    @Override
    public int connect() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}