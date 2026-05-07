import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TokenInterface extends Remote {
    void receiveToken() throws RemoteException;
}