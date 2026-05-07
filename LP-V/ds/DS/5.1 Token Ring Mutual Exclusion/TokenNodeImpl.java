import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TokenNodeImpl extends UnicastRemoteObject implements TokenInterface {
    public boolean hasToken;

    protected TokenNodeImpl(boolean startWithToken) throws RemoteException {
        super();
        this.hasToken = startWithToken;
    }

    @Override
    public synchronized void receiveToken() throws RemoteException {
        this.hasToken = true;
        System.out.println("\n[System] Token received from neighbor!");
    }
}