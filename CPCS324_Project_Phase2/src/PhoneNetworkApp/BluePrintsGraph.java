/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhoneNetworkApp;

import GraphFramework.Graph;
import GraphFramework.Vertex;

/**
 *
 * @author cvs
 */
public class BluePrintsGraph extends Graph {
     
     
     @Override
     public Route createEdge(Vertex source, Vertex target , int weight){
        return new Route (source , target , weight);
        
    }
     @Override
      public Location createVertex(int lable ){
        return new Location(lable);
    }
     
}
