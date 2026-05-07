import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BerkeleyInterface extends Remote {
    // Master calls this to get the Client's time
    long getTime() throws RemoteException;

    // Master calls this to tell the Client how much to adjust its clock
    void adjustTime(long offset) throws RemoteException;
}