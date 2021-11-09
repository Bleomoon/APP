package src;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server
{
    public static void main(String[] args)
    {
        ManageDistImp objserv=null;
        try {
            System.out.println("Creation de l'objet.");
            objserv=new ManageDistImp();
            System.out.println("Enregistrement de l'objet.");
            Naming.rebind("echoservice",objserv);
            System.out.println("serveur operationnel.");
        }
        catch(Exception e) {
            System.out.println(e); // ou e.printStackTrace(System.err);
        }
    }
}