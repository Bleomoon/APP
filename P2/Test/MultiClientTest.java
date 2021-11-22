package Test;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

import javax.sound.sampled.SourceDataLine;
import src.*;

import java.rmi.server.UnicastRemoteObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Serializable;
import src.Client;

public class MultiClientTest {
    public static void main(String[] args) throws RemoteException {
        ClockDist objdist = null;
        int id = 0;
		ClientInt objserv = null;

        int current_id;
        try {
            System.out.println("Creation de l'objet.");
            objserv=new Client();
            System.out.println("Enregistrement de l'objet.");
            Naming.rebind("client",objserv);
            System.out.println("serveur operationnel.");

            System.out.println("Searching for object.");
            String url = "rmi://" + args[0] + "/echoservice";
            objdist = (ClockDist) Naming.lookup(url);

            int id1 = objdist.connect();
            int id2 = objdist.connect();
            int id3 = objdist.connect();
            ((Client)objserv).connectNew(4, 1, "localhost", objdist, id1);
            ((Client)objserv).connectNew(5, 3, "localhost", objdist, id2);
            ((Client)objserv).connectNew(3, 1, "localhost", objdist, id3);
        }
        catch ( Exception e) {
            System.out.println(e);
        }
    }
}
