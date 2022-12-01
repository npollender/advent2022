import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution2 {

    public static void main(String args[]) throws FileNotFoundException {
        //read .txt file
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        //data
        String line = "";
        int calories = 0;
        int maxCalories[] = {0, 0, 0}; //part 2: instead record top 3 values

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            //check if blank line, if yes check if there is a new max
            if (line.equals("")) {
                if (calories > maxCalories[0]) {
                    maxCalories[2] = maxCalories[1];
                    maxCalories[1] = maxCalories[0];
                    maxCalories[0] = calories;
                } else if (calories > maxCalories[1]) {
                    maxCalories[2] = maxCalories[1];
                    maxCalories[1] = calories;
                } else if (calories > maxCalories[2]) {
                    maxCalories[2] = calories;
                }
                calories = 0;
            } else {
                try {
                    calories += Integer.parseInt(line);
                } catch (Exception e) {}
            }
        }
        sc.close();
        System.out.println("Max calories = " + (maxCalories[0] + maxCalories[1] + maxCalories[2]));
    }
}