import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Solution14 {

    public static void main(String args[]) throws FileNotFoundException {
        //read .txt file
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        //data
        String line = "";
        ArrayList<Integer> dirSize = new ArrayList<>();
        ArrayList<Integer> allDirs = new ArrayList<>();
        long toDelete = 0;
        
        dirSize.add(0);

        while(sc.hasNextLine()) {
            line = sc.nextLine();
            if (line.equals("$ cd /") || line.equals("$ ls") || line.startsWith("dir"))
                continue;
            else if (line.equals("$ cd ..")) {
                allDirs.add(dirSize.get(dirSize.size() - 1));
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
        allDirs.addAll(dirSize);
        Collections.sort(allDirs);
        for (int i = 0; i < allDirs.size() - 1; i++) {
            if (allDirs.get(allDirs.size() - 1) - allDirs.get(i) < 40000000) {
                toDelete = allDirs.get(i);
                break;
            }
        }    

        System.out.print("Size of dir to delete = " + toDelete);
    }
}