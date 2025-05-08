import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

class file{
    public static void main(String[] args) {
        // Try-with-resources ensures the file is closed automatically
        try (FileReader f = new FileReader("msg.txt")) {
            int data = f.read();
            while (data != -1) {
                System.out.print((char) data);
                data = f.read();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An I/O error occurred: " + e.getMessage());
        }
    }
}

