/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphFramework;


public class DBAllSourceSPAlg extends ShortestPathAlgorithm {
   public DBAllSourceSPAlg(Graph g){
        graph = g;
    }
    
    public void computeDijkstraBasedSPAlg(){
       SingleSourceSPAlg dj = new SingleSourceSPAlg(graph);

        
        for (int i = 0; i < graph.getVertices().size(); i++) {
            
            char location = (char)(graph.getVertices().get(i).getLabel()+'A');
            
            Vertex source = graph.getVertices().get(i);
            System.out.println("The starting point location is "+ location +
                              " The routes from location "+location+" to the rest of the locations are:");
            
            dj.dijkstra(source.getLabel());

            System.out.println("");
            
          
            
        }
    }
}
