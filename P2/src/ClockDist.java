package src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClockDist extends Remote {
    public int connect() throws RemoteException, InterruptedException; // return the client id or -1 if client pool is overflow
    public Status close(int id) throws RemoteException, InterruptedException;
    public void generateNumber(int n, int x, String hostname, int id_client) throws RemoteException, InterruptedException ;
}