package src;

import java.rmi.RemoteException;

public class NoClientException extends RemoteException {
    
    private static final long serialVersionUID = 1L;

    public NoClientException(){
        super();
    }

    public NoClientException(String s){
        super(s);
    }
}