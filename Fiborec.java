class Fiborec {
    public static void main(String[] args) {
        int n = 2; // Number of terms to display
        System.out.println("Fibonacci series up to " + n + " terms:");
        
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

    // Recursive method to calculate the nth Fibonacci number
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0; // Base case: F(0) = 0
        }
        if (n == 1) {
            return 1; // Base case: F(1) = 1
        }
        return fibonacci(n - 1) + fibonacci(n - 2); // Recursive step
    }
}
