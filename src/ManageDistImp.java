package src;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;

public class ManageDistImp extends UnicastRemoteObject implements ManageDist
{
    private HashMap<Integer, ToDoList> todo_lists;
    private int nb_clients_connected;
    public final static int MAX_CLIENT = 3;
    private static final long serialVersionUID = 1L;

    public ManageDistImp() throws RemoteException
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

    public Task getTask(int id_task, int id_client) throws RemoteException
    {
        System.out.println("Client " + id_client + " try to get the task " + id_task + "!");
        ToDoList client_list = todo_lists.get(id_client);

        if(client_list == null)
            return null;
        else
        {
            return client_list.getTask(id_task); // if the task dosen't exit return null
        }
    }

    public int add(String task_name, Date date, int id_client)
    {
        if (date == null)
            System.out.println("Client " + id_client + " try to add task " + task_name + " without deadline !");
        else
            System.out.println("Client " + id_client + " try to add task " + task_name + "to do before " + date.toString() + " !");
    
        ToDoList client_list = todo_lists.get(id_client);

        if(client_list == null || client_list.isContent(task_name))
        {
            return -1;
        }
        else
        {
            Task current_task = new Task(task_name, date);
            
            int ret = client_list.addTask(current_task);
            System.out.println("Client " + id_client + " add task number " + ret +"!");
            return ret;
        }
    }

    public Status changePriority(int id_task, int id_client) throws RemoteException
    {
        ToDoList client_list = todo_lists.get(id_client);
        
        if (client_list == null)
        {
            return Status.invalid_client_id;
        }
        else 
        {
            client_list.swap(id_task); // TODO exception
            System.out.println("Client " + id_client + " swap task " + id_task + "!");

            return Status.success;
        }
    }

    public int getNbTask(int id_client) throws RemoteException
    {
        ToDoList client_list = todo_lists.get(id_client);
        
        if (client_list == null)
        {
            return -1;
        }
        else 
        { 
            int ret = client_list.getSize();
            System.out.println("Client " + id_client + " want size : " + ret + "!");
            return ret;
        }
    }

    public Status deleteTask(int id_task, int id_client) throws RemoteException
    {
        ToDoList client_list = todo_lists.get(id_client);
        
        if (client_list == null)
        {
            return Status.invalid_client_id;
        }
        else
        {
            if(!client_list.deleteTask(id_task))
            {
                return Status.invalid_task_id;
            }
            else
            {
                System.out.println("Client " + id_client + " delete taks " + id_task + "!");
                return Status.success;
            }
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