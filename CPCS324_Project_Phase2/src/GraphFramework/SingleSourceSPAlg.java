
package GraphFramework;

import java.io.FileNotFoundException;
import java.util.*;

public class SingleSourceSPAlg extends ShortestPathAlgorithm {
   public SingleSourceSPAlg (Graph graph){
        this.graph = graph ;
    }


public static void dijkstra(Integer source) {
    // Initialize the distances from the source vertex to all other vertices to infinity.
    Map<Integer, Double> distances = new HashMap<>();
    
    for (Vertex vertex : graph.getVertices()) {
        distances.put(vertex.getLabel(), Double.POSITIVE_INFINITY);
    }
    distances.put(source, 0.0);

    // Initialize the set of visited vertices to the empty set.
    Set<Vertex> visited = new HashSet<>();

    // Initialize a map to store the paths from the source vertex to each vertex.
    Map<Vertex, List<Vertex>> paths = new HashMap<>();
    for (Vertex vertex : graph.getVertices()) {
        paths.put(vertex, new ArrayList<>());
    }
     int counter =0 ;
    // While there are still vertices that have not been visited:
    while (visited.size() < graph.getVertices().size()  ) {
       Vertex currentVertex = null;
double shortestDistance = Double.POSITIVE_INFINITY;

// Find the first unvisited vertex and initialize currentVertex to it.
for (Vertex vertex : graph.getVertices()) {
    if (!visited.contains(vertex)) {
        currentVertex = vertex;
        shortestDistance = distances.get(vertex.getLabel() );
        break;
    }
}
       // Search for the vertex with the shortest distance that has not been visited yet.
for (Vertex vertex : graph.getVertices()) {
    if (!visited.contains(vertex) && distances.get(vertex.getLabel()) < shortestDistance){
        currentVertex = vertex;
        shortestDistance = distances.get(vertex.getLabel());
    }
}  

          
          ArrayList<Vertex> neighborsList = new ArrayList<>();
          for (int i = 0; i < currentVertex.getAdjLists().size(); i++) {
             
     neighborsList.add(currentVertex.getAdjLists().get(i).getParent().equals(currentVertex) ? 
             currentVertex.getAdjLists().get(i).getTarget() :
             currentVertex.getAdjLists().get(i).getParent());

          }
          
            // For each neighbor ofthe current vertex:
            for (Vertex neighbor : neighborsList) {
                // If the distance to the neighbor through the current vertex is shorter than the current distance to the neighbor:
                double distanceToNeighbor = distances.get(currentVertex.getLabel()) + graph.getEdgeWeight(currentVertex, neighbor);
                if (distanceToNeighbor < distances.get(neighbor.getLabel())) {
                    // Update the distance to the neighbor.
                    distances.put(neighbor.getLabel(), distanceToNeighbor);
                    // Update the path to the neighbor.
                    List<Vertex> path = new ArrayList<>(paths.get(currentVertex));
                    path.add(currentVertex);
                    paths.put(neighbor, path);
                }
            }

           
       
            visited.add(currentVertex);
    }

// Print the shortest paths from the source vertex to all other vertices.
    for (Vertex vertex : graph.getVertices()) {
        List<Vertex> shortestPath = paths.get(vertex);
       
        if (shortestPath.isEmpty()) {
             System.out.print( graph.getVertices().get(source).displayInfo()+ " to " + vertex.displayInfo() + ": ");
            System.out.println("No path exists.");
        } else {
            shortestPath.add(vertex);
            for (int i = 0; i < shortestPath.size() - 1; i++) {
               
                System.out.print(shortestPath.get(i).displayInfo() + " -> ");
            }
            System.out.println( vertex.displayInfo() + " --- route length: " + distances.get(vertex.getLabel()) );
    
}
    }
}
}
