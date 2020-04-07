import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Bhk {
  
  public static void main(String args []){
    //ww  w  .j  a  v a 2 s. c  o  m
    int [] a1= {1,3,8,54,2,65,7};
    int [] a2= {3,1,5,8,7,90};
    
    
    List set1 = new ArrayList();
    List set2 = new ArrayList();
    
    for (int i=0;i<a1.length;i++){
      for(int j=0;j<a2.length;j++){
        if(a1[i] == a2[j])
        set1.add(a1[i]);
      }  
      if(! set1.contains(a1[i]))
        set2.add(a1[i]);
    }
    System.out.println(set1);
    System.out.println(set2);
    
    for(int i=0;i<a2.length;i++){
      if(! set1.contains(a2[i]))
        set2.add(a2[i]);
      }
    System.out.println(set2);
    
  }    
}
