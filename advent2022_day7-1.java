import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Solution13 {

    public static void main(String args[]) throws FileNotFoundException {
        //read .txt file
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        //data
        String line = "";
        ArrayList<Integer> dirSize = new ArrayList<>();
        int sum = 0;

        while(sc.hasNextLine()) {
            line = sc.nextLine();
            if (line.equals("$ cd /") || line.equals("$ ls") || line.startsWith("dir"))
                continue;
            else if (line.equals("$ cd ..")) {
                if (dirSize.get(dirSize.size() - 1) <= 100000)
                    sum += dirSize.get(dirSize.size() - 1);
                dirSize.remove(dirSize.size() - 1);
            }
            else if (line.startsWith("$ cd ")) {
                dirSize.add(0);
            }
            else {
                String split[] = line.split(" ");
                for (int i = 0; i < dirSize.size(); i++)
                    dirSize.set(i, dirSize.get(i) + Integer.parseInt(split[0]));
            }
        }
        sc.close();
        for (int i = 0; i < dirSize.size(); i++)
            if (dirSize.get(i) <= 100000) sum += dirSize.get(i);
        System.out.print("Sum of directories = " + sum);
    }
}