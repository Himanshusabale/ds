import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BerkeleyClient extends UnicastRemoteObject implements BerkeleyInterface {
    private long localTime;

    protected BerkeleyClient(long initialTime) throws RemoteException {
        super();
        this.localTime = initialTime;
    }

    @Override
    public long getTime() throws RemoteException {
        System.out.println("Master requested time. Sending: " + localTime);
        return localTime;
    }

    @Override
    public void adjustTime(long offset) throws RemoteException {
        System.out.println("Received adjustment offset: " + offset);
        this.localTime += offset;
        System.out.println("New Synchronized Time: " + localTime);
    }

    public static void main(String[] args) {
        try {
            java.util.Scanner sc = new java.util.Scanner(System.in);

            // 1. Enter ur machine IP.
            System.out.print("Enter your Machine IP: ");
            String myIp = sc.next();

            // 2.Enter the local time for ur machine - Client
            System.out.print("Enter local clock time (e.g., 1000): ");
            long time = sc.nextLong();

            BerkeleyClient client = new BerkeleyClient(time);
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://" + myIp + "/BerkeleyService", client);

            System.out.println("Client is ready and waiting for Master...");
        } catch (Exception e) { e.printStackTrace(); }
    }
}