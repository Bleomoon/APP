package Test;
import java.rmi.RemoteException;

import src.Client;
public class MultiClientTest {
    public static void main(String[] args) throws RemoteException {
        Client c1 = new Client();
        Client c2 = new Client();
        Client c3 = new Client();

        c2.connectNew(4, 1, "localhost");
        c1.connectNew(5, 3, "localhost");
        c3.connectNew(3, 1, "localhost");
    }
}
