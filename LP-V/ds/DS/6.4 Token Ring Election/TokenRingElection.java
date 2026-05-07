import java.util.*;

class Process {
    int id;
    boolean active;

    Process(int id) {
        this.id = id;
        this.active = true;
    }
}

public class TokenRingElection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        
        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            processes[i] = new Process(i);
        }

        System.out.print("Enter initiator process ID: ");
        int initiator = sc.nextInt();

        // 1. Election Phase
        List<Integer> electionList = new ArrayList<>();
        int current = initiator;

        System.out.println("\n--- Election Phase Started ---");
        do {
            if (processes[current].active) {
                electionList.add(processes[current].id);
                int next = (current + 1) % n;
                System.out.println("[Election Message]: Process " + processes[current].id + 
                                   " sends list " + electionList + " to Process " + next);
            }
            current = (current + 1) % n;
        } while (current != initiator);

        // 2. Determine Winner
        int leader = Collections.max(electionList);
        System.out.println("\n[Result]: Process " + initiator + " received list back. Max ID is " + leader);

        // 3. Coordination Phase
        System.out.println("\n--- Coordination Phase Started ---");
        current = initiator;
        do {
            int next = (current + 1) % n;
            System.out.println("[Coordinator Message]: Process " + current + 
                               " informs Process " + next + " that Leader is " + leader);
            current = (current + 1) % n;
        } while (current != initiator);

        System.out.println("\nFinal Leader: Process " + leader);
        sc.close();
    }
}






// import java.util.Scanner;

// public class SimpleRingElection {

//     public static void main(String[] args) {

//         Scanner sc = new Scanner(System.in);

//         System.out.print("Enter number of processes: ");
//         int n = sc.nextInt();

//         boolean[] active = new boolean[n];

//         // all processes active
//         for (int i = 0; i < n; i++) {
//             active[i] = true;
//         }

//         System.out.print("Enter initiator process ID: ");
//         int initiator = sc.nextInt();

//         int current = initiator;
//         int leader = initiator;

//         System.out.println("\n--- Election Started ---");

//         do {

//             System.out.println("Process " + current + " passes token");

//             // highest active process becomes leader
//             if (active[current] && current > leader) {
//                 leader = current;
//             }

//             current = (current + 1) % n;

//         } while (current != initiator);

//         System.out.println("\nLeader elected: Process " + leader);

//         sc.close();
//     }
// }