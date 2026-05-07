import mpi.*;

public class MPIAverage {
    public static void main(String[] args) throws Exception {
        // Initialize MPI environment
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();   // Current process ID
        int size = MPI.COMM_WORLD.Size();   // Total number of processes

        int nPerProcess = 4; // Each process will handle 4 numbers
        int totalNumbers = nPerProcess * size;
        
        int[] rootArray = new int[totalNumbers];
        int[] subArray = new int[nPerProcess];
        float[] averages = new float[size];

        // --- PHASE 1: Root process generates random numbers ---
        if (rank == 0) {
            System.out.println("Root: Generating " + totalNumbers + " random numbers.");
            for (int i = 0; i < totalNumbers; i++) {
                rootArray[i] = (int) (Math.random() * 100);
            }
        }

        // --- PHASE 2: Scatter the data ---
        // Root process splits rootArray and sends a piece (subArray) to every process
        MPI.COMM_WORLD.Scatter(rootArray, 0, nPerProcess, MPI.INT, 
                              subArray, 0, nPerProcess, MPI.INT, 0);

        // --- PHASE 3: Local Computation ---
        float localSum = 0;
        for (int num : subArray) {
            localSum += num;
        }
        float localAverage = localSum / nPerProcess;
        System.out.println("Process " + rank + ": Computed Local Average = " + localAverage);

        // --- PHASE 4: Gather the results ---
        // Collect localAverage from every process into the 'averages' array on Root
        float[] sendBuffer = new float[]{localAverage};
        MPI.COMM_WORLD.Gather(sendBuffer, 0, 1, MPI.FLOAT, 
                             averages, 0, 1, MPI.FLOAT, 0);

        // --- PHASE 5: Final Aggregation on Root ---
        if (rank == 0) {
            float totalAvgSum = 0;
            for (float avg : averages) {
                totalAvgSum += avg;
            }
            float finalAverage = totalAvgSum / size;
            System.out.println("\n[Root] Final Global Average: " + finalAverage);
        }

        // Finalize MPI environment
        MPI.Finalize();
    }
}