package src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClockDist extends Remote {
    public int connect() throws RemoteException; // return the client id or -1 if client pool is overflow
    public Status close(int id_client) throws RemoteException;
}