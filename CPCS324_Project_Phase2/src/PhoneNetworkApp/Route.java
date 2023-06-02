
package PhoneNetworkApp;

import GraphFramework.Edge;

import GraphFramework.Vertex;
public class Route extends Edge{
  
    
    
    public Route( Vertex source, Vertex target , int weight) {
       super ( source , target , weight);
      
    }
    
    
    
    public String displayInfo(){
        return "route length: "+super.getWeight() ;
    }


    

}
