package GraphFramework;

public abstract class Edge {
    private int weight ;
    private Vertex source ;
    private Vertex target ;
    private Vertex parent ;
    
public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }
    
    public Edge( Vertex source, Vertex target , int weight) {
        this.weight = weight;
        this.source = source;
        this.target = target;
        parent = source;
        
    }
    public Edge( Vertex source,  int weight) {
        this.weight = weight;
        this.source = source;
      
        
    }
    public Edge(){
        
    }
     public Vertex getSource() {
        return source;
    }
    
    public Vertex getTarget() {
        return target;
    }
    
   public  int getWeight() {
       return weight ;
   }

   

  public abstract String displayInfo();
  
  

}
