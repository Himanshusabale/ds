import mpi.*;

public class MPIMultiplication {

    public static void main(String[] args) throws Exception {

        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();   // Process ID
        int size = MPI.COMM_WORLD.Size();   // Total processes

        int[] data = new int[size];

        // Root process initializes array
        if (rank == 0) {

            System.out.println("Root Process initializing array:");

            for (int i = 0; i < size; i++) {
                data[i] = i + 1;
                System.out.print(data[i] + " ");
            }

            System.out.println("\n");
        }

        // Buffer for each process
        int[] recv = new int[1];

        // Scatter one element to each process
        MPI.COMM_WORLD.Scatter(
                data, 0, 1, MPI.INT,
                recv, 0, 1, MPI.INT,
                0
        );

        // Each process performs multiplication
        int result = recv[0] * 10;

        System.out.println(
                "Process " + rank +
                " received " + recv[0] +
                " and multiplied result = " + result
        );

        MPI.Finalize();
    }
}