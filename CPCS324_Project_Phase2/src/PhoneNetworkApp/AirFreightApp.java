package PhoneNetworkApp;


import GraphFramework.DBAllSourceSPAlg;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class AirFreightApp {

    static BluePrintsGraph PhLNetwork;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        PhLNetwork = new BluePrintsGraph();
        boolean printTime = true , isReq1 = false  ;
        // long variables to store the strating time and ending time 
        //of processing Kruskal and Prim algorithms
        long startTime, endTime;

        // double variable to store differenece of time 
        //in nanoseconed between startTime and endTime variables
        double result;
        
        
        System.out.print(">> Enter your choice (1 or 2): ");
        int choice = input.nextInt();
        if (choice == 1) {
            PhLNetwork.readGraphFromFile("input.txt");printTime = false ;

            isReq1 = true ;
        } else {
            System.out.print("Enter the number of Vertices : ");
            int vertexNO = input.nextInt();
            System.out.print("Enter the number of Edges : ");
            int edgeNO = input.nextInt();
            System.out.print("Enter 0 if the graph is undirected or 1 if directed : ");
            int isdiagraph = input.nextInt();
            System.out.println("");
            PhLNetwork.makeGraph(vertexNO, edgeNO, isdiagraph);
            
        }
        
        
        
   
    DBAllSourceSPAlg multiDijkstra = new DBAllSourceSPAlg(PhLNetwork);

    startTime = System.currentTimeMillis();
        
       
    multiDijkstra.computeDijkstraBasedSPAlg();
       

    }

}
