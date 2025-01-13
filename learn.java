

public class learn {
   int x = 10;
   double PI = 3.14;

  public static void main(String[] args) {
    learn myObj = new learn();
    myObj.x = 50; // will generate an error
    myObj.PI = 25; // will generate an error
    System.out.println(myObj.x); 
  }
}
