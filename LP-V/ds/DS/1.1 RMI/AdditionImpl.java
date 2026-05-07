import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class AdditionImpl extends UnicastRemoteObject implements AdditionInterface {

    public AdditionImpl() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        System.out.println("Server is calculating: " + a + " + " + b);
        return (a + b);
    }
}