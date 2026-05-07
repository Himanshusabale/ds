import java.util.Scanner;

class Process extends Thread {
    public int id;
    public boolean isAlive;

    public Process(int id) {
        this.id = id;
        this.isAlive = true;
    }
}

public class BullyAlgorithm {
    static Process[] processes;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        n = sc.nextInt();

        processes = new Process[n];
        for (int i = 0; i < n; i++) {
            processes[i] = new Process(i);
        }

        System.out.print("Enter the ID of the process that initiates the election: ");
        int initiator = sc.nextInt();

        // Start the election
        electLeader(initiator);
        sc.close();
    }

    static void electLeader(int initiatorId) {
        System.out.println("\n--- Election started by Process " + initiatorId + " ---");
        boolean higherProcessFound = false;

        // Send election messages to higher ID processes
        for (int i = initiatorId + 1; i < n; i++) {
            System.out.println("[Election Message]: Process " + initiatorId + " -> Process " + i);
            
            if (processes[i].isAlive) {
                System.out.println("[OK Message]: Process " + i + " -> Process " + initiatorId);
                higherProcessFound = true;

               // electLeader(i);  --> in case of recursive bully algorithm.
               //  return;
            }
        }

        if (higherProcessFound) {
            // Find the highest alive process to become the leader
            int newLeader = -1;
            for (int i = n - 1; i >= 0; i--) {
                if (processes[i].isAlive) {
                    newLeader = i;
                    // Usually, the process that sent 'OK' starts its own election
                    // To keep this simple, the highest alive one wins.
                    break;
                }
            }
            
            // Final coordination message
            System.out.println("\n[Coordinator Message]: Process " + newLeader + " is the new Leader!");
            for (int i = 0; i < n; i++) {
                if (i != newLeader) {
                    System.out.println("Process " + i + " acknowledged Process " + newLeader + " as Leader.");
                }
            }
        } else {
            // Initiator is the highest alive process
            System.out.println("\n[Coordinator Message]: Process " + initiatorId + " is the new Leader!");
        }
    }
}





// | Version                        | Complexity       |
// | ------------------------------ | ---------------- |
// | Your simplified code           | O(n)             |
// | Real recursive bully algorithm | O(n²) worst case |
