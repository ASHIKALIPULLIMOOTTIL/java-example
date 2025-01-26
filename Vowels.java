class Vowels {
    public static void main(String args[]) {
        String str = "ashik";
        if (containsVowels(str)) {
            System.out.println("It contains vowels");
        } else {
            System.out.println("It does not contain vowels");
        }
    }

    public static boolean containsVowels(String str) {
        // Check for the presence of any vowel
        return str.matches(".*[aeiouAEIOU].*");
    }
}