
package PhoneNetworkApp;
import GraphFramework.Vertex;


public class Location extends Vertex{
    private String city;
    
    public Location (int lable ){
       super( lable );
       city = "Loc "+(char)(lable+'A');
    }
    
    //********
   
    
   
   public String setLabelO (int num ){
       return "O"+num ;
   }
    
    public String displayInfo(){
       return city+" :city "+super.getLabel();
    }

    
}
