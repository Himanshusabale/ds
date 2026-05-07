import java.rmi.*;

public class AddClient {
    public static void main(String args[]) {
        try {
            // Get reference to the remote object
            String addServerURL = "rmi://" + args[0] + "/AddServer";
            AddServerIntf addServerIntf = (AddServerIntf) Naming.lookup(addServerURL);
            System.out.println("The first number is: " + args[1]);
            double d1 = Double.parseDouble(args[1]);
            System.out.println("The second number is: " + args[2]);
            double d2 = Double.parseDouble(args[2]);
            // Invoke remote method to add numbers
            System.out.println("The sum is: " + addServerIntf.add(d1, d2));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}

// 2
// Ye mpi wali assignment ki commands dekh
// cd ~/mpj-v0_44
// export MPJ_HOME=$PWD
// export PATH=$PATH:$MPJ_HOME/bin

// cd ~/mpi
// javac -cp .:$MPJ_HOME/lib/mpj.jar ArrSum.java
// mpjrun.sh -np 4 ArrSum

// 3
// idlj -fall Reverse.idl
// javac *.java
// orbd -ORBInitialPort 1050 &
// java ReverseServer -ORBInitialPort 1050 -ORBInitialHost localhost
// java ReverseClient -ORBInitialPort 1050 -ORBInitialHost localhost

// javac *.java for compile
// rmiregistry
// java addserver
// Open new terminal
// java addClient localhost And add any like compare strings, number Two any

// Assignment 4
// python Server py
// Then new terminal
// Python Client.py
// On vs

// Ass 5
// Javac *.java
// Java filename

// Install JDK-8

// sudo apt-get remove openjdk*
// sudo apt update
// sudo apt install openjdk-8-jdk openjdk-8-jre
// Download MPJ Express and extract in the Downloads dir

// Install Apache Netbeans

// sudo apt update && sudo apt upgrade
// sudo snap install netbeans --classic
// Glassfish server version must be 4.1.1

// Assignment 1:
// Terminal 1:

// javac *.java
// rmic AddServerImpl
// Terminal 2:

// rmiregistry
// Terminal 3:

// java AddServer
// Terminal 4:

// java addClient localhost any number
// Assignment 2:
// Terminal 1:

// idlj -fall ReverseModule.idl
// javac *.java ReverseModule/*.java
// orbd -ORBInitialPort 1056&
// java ReverseServer -ORBInitialPort 1056&
// Terminal 2:

// java ReverseClient -ORBInitialPort 1056 -ORBInitialHost localhost
// Assignment 3:
// Terminal:

// export MPJ_HOME=/home/ubuntu/Downloads/mpj-v0_44
// export PATH=$MPJ_HOME/bin:$PATH
// javac -cp $MPJ_HOME/lib/mpj.jar ArrSum.java(here class name and file name
// should be same)
// $MPJ_HOME/bin/mpjrun.sh -np 4 ArrSum

// Assignment 4:
// Terminal 1:

// python client.py
// Terminal 2:

// python server.py
// Assignment 5:
// Terminal

// javac Tring.java
// java Tring
// Assignmnet 6:
// Terminal

// javac Bully.java
// java Bully
// javac Ring.java
// java Ring