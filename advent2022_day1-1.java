import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution {

    public static void main(String args[]) throws FileNotFoundException {
        //read .txt file
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        //data
        String line = "";
        int calories = 0;
        int maxCalories = 0;

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            //check if blank line, if yes check if there is a new max
            if (line.equals("")) {
                if (calories > maxCalories)
                    maxCalories = calories;
                calories = 0;
            } else {
                try {
                    calories += Integer.parseInt(line);
                } catch (Exception e) {}
            }
        }
        sc.close();
        System.out.println("Max calories = " + maxCalories);
    }
}