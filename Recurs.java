
class Sum{
    int Rec(int k){
        if(k>0){
         return k + Rec(k-1); }
         else{return 0;}
    }
}
public class Recurs {
    public static void main(String args[]){
        Sum s=new Sum();
       System.out.println(s.Rec(10)); 
    }
}
