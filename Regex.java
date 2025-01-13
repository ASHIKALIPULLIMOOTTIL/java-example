import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Regex {
    public static void main(String args[]){
        Pattern p = Pattern.compile("ashik",Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher("ashik ali pulimoottil");
        boolean found=m.find();
        if(found){
            System.out.println("found");
        }
        else{
            System.out.println(" not found");
        }

    }
}
