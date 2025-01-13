import java.util.HashMap;
public class HashMAp {
    public static void main(String args[]){
        HashMap<String,Integer> Student = new HashMap<String,Integer>();
        
             Student.put("ashik", 23);
             Student.put("anu", 13);
             Student.put("dilu", 16);

             for (String i : Student.keySet()) {
                System.out.println("Name: " + i + " Age: " + Student.get(i));
              }

      

    }
}
