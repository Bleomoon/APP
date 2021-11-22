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
        this.todo_lists = new HashMap<Integer, ToDoList>();
        this.nb_clients_connected = 0;
    }

    public int connect(int n, int x, Client client) throws RemoteException // return the client id or -1 if client pool is overflow
    {
        synchronized(this){
            if ( nb_clients_connected >= MAX_CLIENT)
            {
                System.out.println("A client try to connect but no slot free!");
                return -1;
            }

            nb_clients_connected ++;
            int number = 0;
            System.out.println("Client " + id_client + " is connected !");

            while (true && client != null){
                thread.sleep(x*1000);
                for (int i = 0; i < n ; i++) {
                    number = (int) (Math.random() * ( 100000 - 0 ));
                    client.add(number);
                }
            }
            return Status.success;
        }
    }
    
    public Status close(int id_client) throws NoClientException
    {
        synchronized(this){
            if (nb_clients_connected <= 0)
                throw new NoClientException(); // if the compteur is null or less. i can't remove the compteur.
            System.out.println("Client " + id_client + " is disconnect !");
			nb_clients_connected--;
            return Status.success;
        }
    }

}