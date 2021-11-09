package src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ManageDist extends Remote {
    public int connect() throws RemoteException; // return the client id or -1 if client pool is overflow
    public Task getTask(int id_task, int id_client) throws RemoteException;
    public int add(String task_name, Date date, int id_client) throws RemoteException;
    public Status changePriority(int id_task, int id_client) throws RemoteException;
    public int getNbTask(int id_client) throws RemoteException;
    public Status deleteTask(int id_task, int id_client) throws RemoteException;
    public Status close(int id_client) throws RemoteException;
}