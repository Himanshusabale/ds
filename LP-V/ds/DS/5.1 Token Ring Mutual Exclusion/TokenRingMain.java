import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class TokenRingMain {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            
            System.out.print("Enter your Machine IP: ");
            String myIp = sc.next();
            System.out.print("Enter Neighbor Machine IP: ");
            String neighborIp = sc.next();
            System.out.print("Do you start with the token? (true/false): ");
            boolean start = sc.nextBoolean();

            // 1. Setup the Remote Object
            TokenNodeImpl node = new TokenNodeImpl(start);
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://" + myIp + "/TokenService", node);
            
            System.out.println("Node started at " + myIp);
            String nextNodeUrl = "rmi://" + neighborIp + "/TokenService";

            // 2. Main Loop
            while (true) {
                if (!node.hasToken) {
                    System.out.println("Waiting for token...");
                    while (!node.hasToken) { Thread.sleep(1000); }
                }

                System.out.println("\n--- TOKEN HELD ---");
                System.out.print("Enter Critical Section? (yes/no): ");
                String choice = sc.next();

                if (choice.equalsIgnoreCase("yes")) {
                    System.out.println("[CS] Working in Critical Section...");
                    Thread.sleep(3000); 
                    System.out.println("[CS] Done.");
                }

                // 3. Pass the token to neighbor
                try {
                    TokenInterface neighbor = (TokenInterface) Naming.lookup(nextNodeUrl);
                    node.hasToken = false; // Set to false before sending
                    neighbor.receiveToken();
                    System.out.println("[System] Token passed to " + neighborIp);
                } catch (Exception e) {
                    System.out.println("[Error] Neighbor not ready. Retrying in 2s...");
                    node.hasToken = true; 
                    Thread.sleep(2000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}