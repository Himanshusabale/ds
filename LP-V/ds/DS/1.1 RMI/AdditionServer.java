import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class AdditionServer {
    public static void main(String[] args) {
        try {
            AdditionImpl obj = new AdditionImpl();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("AdditionService", obj);

            System.out.println("Addition Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
    }
}