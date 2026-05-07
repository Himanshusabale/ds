import java.rmi.Naming;
import java.util.ArrayList;

public class BerkeleyMaster {
    public static void main(String[] args) {
        try {
            java.util.Scanner sc = new java.util.Scanner(System.in);
            System.out.print("Enter Master's local clock time: ");
            long masterTime = sc.nextLong();

            System.out.print("Enter Client IP (Machine 2): ");   // clientIp & myIp both are same only.
            String clientIp = sc.next();

            // 1. Collect times
            System.out.println("\n[Phase 1] Collecting times...");
            BerkeleyInterface client = (BerkeleyInterface) Naming.lookup("rmi://" + clientIp + "/BerkeleyService");
            long clientTime = client.getTime();
            
            System.out.println("Master Time: " + masterTime);
            System.out.println("Client Time: " + clientTime);

            // 2. Calculate Average
            long average = (masterTime + clientTime) / 2;
            System.out.println("\n[Phase 2] Calculated Average Time: " + average);

            // 3. Calculate and send offsets
            long masterOffset = average - masterTime;
            long clientOffset = average - clientTime;

            System.out.println("\n[Phase 3] Sending offsets...");
            client.adjustTime(clientOffset);
            
            // Apply own offset
            masterTime += masterOffset;
            System.out.println("Master's New Synchronized Time: " + masterTime);

        } catch (Exception e) { e.printStackTrace(); }
    }
}