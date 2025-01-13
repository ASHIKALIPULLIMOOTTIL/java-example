import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ArraylistColl {

    public static void main(String[] args) {
        ArrayList<Integer> num=new ArrayList<Integer>();
        num.add(4);
        num.add(1);
        num.add(7);
        num.add(3);
        num.add(40);
        Collections.sort(num);
        System.out.println(num);
        int index=Collections.binarySearch(num, 1);
        System.out.println(index);
        int max =Collections.max(num);
        System.out.println(max);
        num.add(1,0);
        System.out.println(num);

        Iterator<Integer> it = num.iterator();

        while(it.hasNext()){
            int i = it.next();
            if(i>10){
                it.remove();
            }
        }
        System.out.println(num);
    }
}