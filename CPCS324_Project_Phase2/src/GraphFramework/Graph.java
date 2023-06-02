package GraphFramework;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;
import PhoneNetworkApp.BluePrintsGraph;
import java.util.Collections;

public abstract class Graph{

    public static int getVertexNo() {
        return vertexNo;
    }

    public static void setVertexNo(int vertexNo) {
        Graph.vertexNo = vertexNo;
    }

    public static int getEdgeNo() {
        return edgeNo;
    }

    public static void setEdgeNo(int edgeNo) {
        Graph.edgeNo = edgeNo;
    }

    public static boolean isIsDiagraph() {
        return isDiagraph;
    }

    public static void setIsDiagraph(boolean isDiagraph) {
        Graph.isDiagraph = isDiagraph;
    }

    public static ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public static void setVertices(ArrayList<Vertex> vertices) {
        Graph.vertices = vertices;
    }

    public static LinkedList<Edge> getAdjacencylist( int index) {
        return adjacencylist[index];
    }
      public static LinkedList<Edge>[] getAdjacencylist() {
        return adjacencylist;
    }

    public static void setAdjacencylist(LinkedList<Edge>[] adjacencylist) {
        Graph.adjacencylist = adjacencylist;
    }

    private static int vertexNo;
    private static int edgeNo;
    private static boolean isDiagraph;
    private static ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private static LinkedList<Edge>[] adjacencylist;
    
    public Graph(int edgeNo,int vno, boolean isDiagraph) {
        vertexNo=vno;
        this.edgeNo = edgeNo;
        this.isDiagraph = isDiagraph;
        
        adjacencylist = new LinkedList[vno];
            //initialize adjacency lists for all the vertices
              for (int i = 0; i <vno ; i++) {
               vertices.add(createVertex(i));
            }
            for (int i = 0; i <vno ; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
    }
public Graph(){
    
}
   public abstract Vertex createVertex(int label ) ;
  
   

   public abstract Edge createEdge(Vertex source, Vertex target , int weight);
   
    
     
    // method n takes as parameters the number of vertices and the number of edges
    // It is responsible for creating a graph object with the specified parameters 
    // and randomly initializes the vertices’ labels, creating edges that connects the created vertices randomly
    // and assigning them random weights alson Makeing sure that the resulting graph is connected. 

     public void makeGraph(int vno , int eno , int isdia ) {
       
//         vertexNo=vno;
//        this.edgeNo = edgeNo;
        this.isDiagraph = isdia == 0 ? false : true ;
        adjacencylist = new LinkedList[vno];
            //initialize adjacency lists for all the vertices
            for (int i = 0; i <vno ; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
            
            
        for (int i = 0; i < vno; i++) {
         
            vertices.add(createVertex(i));
            // incrementing the vertices number as requested ! 
          vertexNo++;
        }

       // object of Random class
        Random randm = new Random();
        // ensure that all verts are connected
        for (int i = 0; i < vno - 1; i++) {
            int weight = randm.nextInt(20) + 1;//generate random edge weights between 0-20
            addEdge(vertices.get(i), vertices.get(i+1), weight);    //connect verts
            if (!isDiagraph) {
               addEdge(vertices.get(i+1), vertices.get(i), weight);  
            }
        }

        // generate edges bewteen verts with the remaining edges
        int remEdges = eno - (vno - 1);

        for (int i = 0; i < remEdges; i++) {
            int source = randm.nextInt(vertexNo);
            int target = randm.nextInt(vertexNo);
            if (target == source || areConnected(vertices.get(source), vertices.get(target))) { // to avoid self loops and duplicate edges
                i--;
                continue;
            }
            // generate random weights in range 0 to 20
            int weight = randm.nextInt(20) + 1;
            // add edge to the graph
            addEdge(vertices.get(source), vertices.get(target), weight);

        }

    }

    // method to check if 2 vetices are connect with an edge 
    public static boolean areConnected(Vertex v1, Vertex v2) {

            // getting the size of the adjList that store the edges to ba able to through it 
            int size = v1.getAdjLists().size();
           
            // loop to got through the edges of a specefic vertex 
            for (int j = 0; j < size; j++) {
                
                // if one if the vertices is a source and the other is a target then they are connected (:
                if ((v1.getAdjLists().get(j).getSource() == v1 && v1.getAdjLists().get(j).getTarget() == v2)
                        || (v1.getAdjLists().get(j).getSource() == v2 && v1.getAdjLists().get(j).getTarget() == v1)) {
                    return true;
                }

            }
        
        return false;
    }

    // method that reads the edges and vertices from the text file whose name is
    // specified by the parameter filename and place the data in the Graph
    public  void readGraphFromFile(String fileName) throws FileNotFoundException {

        int eno = 0, vno = 0;
        
        
        // checking if the file exist and print 
        File f = new File(fileName);
        if (!f.exists()) {
            System.out.println("File Does not exist !");
        }
        
        // creating 2 scanner object
        // scanner opject to read the data of the each edge
        Scanner input = new Scanner(f);
        
        // scanner object to read the labels of each vertex 
        Scanner input2 = new Scanner(f);

        input2.nextLine();
        input2.nextLine();
        
        input.next();

        isDiagraph = input.nextInt() ==0  ? false : true;
        
        eno = input.nextInt();
        vno = input.nextInt();
      
        
       
        adjacencylist = new LinkedList[vno];
            //initialize adjacency lists for all the vertices
            for (int i = 0; i <vno ; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
            
        // list to store the labels 
        int[] listLabels = new int[vno];
        
        
        // counter act like index for the label list
        int counter = 0;
        listLabels[0] = 0 ;
        // loop to store the distinct labels of each edge 
        for (int i = 0; i < eno; i++) {
            
            // reading the label 
            char ch1 = input2.next().charAt(0);
            char ch2 = input2.next().charAt(0);
         
            // a flag to know wether to add the label to the list or not 
            boolean canAdd1 = true;  boolean canAdd2 = true;
            
            // loop to go through the list labels
            // to check if the label is already exist
            for (int j = 0; j < listLabels.length; j++) {
               
                if (listLabels[j] == ( ch1-'A') ) {
                  
                    canAdd1 = false;
                    
                }
                
                if (listLabels[j] == ( ch2-'A')){
                    canAdd2 =false ;
                }
            }
            
            input2.nextLine();
            
            // if the flag = true , then we can add the label 
            // and increment the index to the next place for another label 
            if (canAdd1 ) {
              
                listLabels[counter] = ch1-'A';
                counter++;
            }
            
            if(canAdd2){
               
                listLabels[counter] = ch2-'A';
                counter++;
            }
        }
        
        // Adding the vertices to its list in the graph
        for (int i = 0; i < vno; i++) {
          
            vertices.add(createVertex(listLabels[i]));
            
            // incrementing the vertices number as requested ! 
            vertexNo++;
        }
     
        
        // Adding the edges 
        // creating and initializing the needed variables 
        char label1, label2;
        
        Vertex v1 = null, v2 = null;
        
        // loop to go through each edge in the file 
        for (int i = 0; i < eno; i++) {
           
            // reading the labels for the source and target vetices 
            label1 = input.next().charAt(0);
            label2 = input.next().charAt(0);

            // loop to go through the vetices list 
            // to get the vetices objects with same readen labels
            for (int j = 0; j < vertices.size(); j++) {
                
                // if the current vertixs’s labels is the same labels 
                if (vertices.get(j).getLabel() == label1 - 'A') 
                    
                    v1 = vertices.get(j);
                    
               else if (vertices.get(j).getLabel() == label2-'A') 
                   
                    v2 = vertices.get(j);
                
            }
           
            // checking if both variables are not null , to not cause null pointer exeption !
            if (v1 != null && v2 != null) {
                
                // adding the new edge   
             
                addEdge(v1, v2, input.nextInt());
               

            }

        }
        Collections.sort(vertices, Comparator.comparingInt(vertex -> vertex.getLabel()));
      

    }


     public  void addEdge(Vertex source, Vertex target, int weight) {
            Edge edge = createEdge(source, target, weight);
            
            

            Edge edge2 = createEdge(target, source, weight);
           
            
           if (isDiagraph) {
            source.getAdjLists().add(edge); edgeNo++;
            adjacencylist[source.getLabel()].addFirst(edge);
        } // if the graph is dirceted graph then both assign from source to taget
        // and from taget to source --> <--
        else {
            adjacencylist[source.getLabel()].addFirst(edge);  
            adjacencylist[target.getLabel()].addFirst(edge2);
            source.getAdjLists().add(edge);  edgeNo += 2;
            target.getAdjLists().add(edge2);
           
        }
        }
     
     
      
      public int findEdge(Vertex source , Vertex target){
            for (int i = 0; i < adjacencylist.length; i++) {
                
                for (int j =0 ; j < adjacencylist[i].size() ; j++)
                     if (adjacencylist[i].get(j).getSource() == source && adjacencylist[i].get(j).getTarget() == target){
                            return adjacencylist[i].get(j).getWeight();
                     }
                    
            }
            return 0;
        }
      
      
      public ArrayList<Vertex> getNeighbors(Vertex source ){
         
          ArrayList<Vertex> neighborsList = new ArrayList<>();
          for (int i = 0; i < source.getAdjLists().size(); i++) {
             
              neighborsList.add(source.getAdjLists().get(i).getTarget());
          }
          
          return neighborsList;
      }
      
      public int getEdgeWeight(Vertex source , Vertex target){
          for (int i = 0; i < source.getAdjLists().size(); i++) {
             if(source.getAdjLists().get(i).getTarget() == target)
                 return source.getAdjLists().get(i).getWeight();
          }
          return 0;
      }
}
