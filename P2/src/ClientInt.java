package src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInt extends Remote {
    public void add_new(int generated, int id_client) throws RemoteException;
}