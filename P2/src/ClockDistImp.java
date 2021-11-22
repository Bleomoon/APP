package src;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;

public class ClockDistImp extends UnicastRemoteObject implements ClockDist
{
    private HashMap<Integer, ToDoList> todo_lists;
    private int nb_clients_connected;
    public final static int MAX_CLIENT = 3;
    private static final long serialVersionUID = 1L;

    public ClockDistImp() throws RemoteException
    {
        this.todo_lists = new HashMap<Integer, ToDoList>();
        this.nb_clients_connected = 0;
    }

    public int connect() throws RemoteException // return the client id or -1 if client pool is overflow
    {
        synchronized(this){
            if ( nb_clients_connected >= MAX_CLIENT)
            {
                System.out.println("A client try to connect but no slot free!");
                return -1;
            }

            nb_clients_connected ++;
            int id_client = 0;

            // find and unique is_client
            do{
                id_client = (int) (Math.random() * ( 100000 - 0 ));
            }while(todo_lists.get(id_client) != null);

            ToDoList client_list = new ToDoList();
            todo_lists.put(id_client, client_list); // TODO recup ret and throw if -1 

            System.out.println("Client " + id_client + " is connected !");
            return id_client;
        }
    }
    
    public Status close(int id_client) throws NoClientException
    {
        synchronized(this){
            if (nb_clients_connected <= 0)
                throw new NoClientException(); // if the compteur is null or less. i can't remove the compteur.

            // remove all tasks of the client on the map
            todo_lists.remove(id_client);
            System.out.println("Client " + id_client + " is disconnect !");
			nb_clients_connected--;
            return Status.success;
        }
    }

}