import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution6 {

    static char alpha[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                           'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static void main(String args[]) throws FileNotFoundException {
        //read .txt file
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        //data
        String line = "";
        String line2 = "";
        String line3 = "";
        int sum = 0;

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            line2 = sc.nextLine();
            line3 = sc.nextLine();
            //oh god triple nested loop here we go... too lazy right now
            outerloop:
            for (int i = 0; i < line.length(); i++) {
                for (int j = 0; j < line2.length(); j++) {
                    for (int k = 0; k < line3.length(); k++) {
                        if (line.charAt(i) == line2.charAt(j) && line.charAt(i) == line3.charAt(k)) {
                            sum += calcPriority(line.charAt(i));
                            break outerloop;
                        }
                    }
                }
            }
        }

        sc.close();
        System.out.println("Sum = " + sum);
    }

    public static int calcPriority(char c) {
        for (int i = 0; i < alpha.length; i++) {
            if (c == alpha[i])
                return (i + 1);
        }
        return 0;
    }
}
