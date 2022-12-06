import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution7 {

    public static void main(String args[]) throws FileNotFoundException {
        //read .txt file
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        //data
        String line = "";
        String sections[] = {"", ""};
        int pairs = 0;

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            sections = line.split(",");
            if (findPairs(sections[0], sections[1]))
                pairs++;
        }
        sc.close();
        System.out.println("Number of pairs = " + pairs);
    }

    //this is disgusting i am ashamed of this segment
    public static boolean findPairs(String a, String b) {

        int pairs = 0;
        String stringA[] = a.split("-");
        String stringB[] = b.split("-");
        int minA = Integer.parseInt(stringA[0]);
        int maxA = Integer.parseInt(stringA[1]);
        int minB = Integer.parseInt(stringB[0]);
        int maxB = Integer.parseInt(stringB[1]);
        int rangeA[] = new int[maxA - minA + 1];
        int rangeB[] = new int[maxB - minB + 1];

        int index = 0;
        for (int i = minA; i < maxA + 1; i++) {
            rangeA[index] = i;
            index++;
        }
        index = 0;
        for (int i = minB; i < maxB + 1; i++) {
            rangeB[index] = i;
            index++;
        }
        
        if (rangeA.length > rangeB.length) {
            for (int i = 0; i < rangeA.length; i++) {
                for (int j = 0; j < rangeB.length; j++) {
                    if (rangeA[i] == rangeB[j]) {
                        pairs++;
                        if (pairs == rangeB.length)
                            return true;
                    }
                }
            }
        } else {
            for (int i = 0; i < rangeB.length; i++) {
                for (int j = 0; j < rangeA.length; j++) {
                    if (rangeB[i] == rangeA[j]) {
                        pairs++;
                        if (pairs == rangeA.length)
                            return true;
                    }
                }
            }
        }
        return false;
    }
}