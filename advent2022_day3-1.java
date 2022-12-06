import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution5 {

    static char alpha[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                           'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static void main(String args[]) throws FileNotFoundException {
        //read .txt file
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        //data
        String line = "";
        String sackLeft = "";
        String sackRight = "";
        int sum = 0;

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            sackLeft = line.substring(0, line.length() / 2);
            sackRight = line.substring(line.length() / 2, line.length());
            //i hate nested loops woohoo!
            outerloop:
            for (int i = 0; i < sackLeft.length(); i++) {
                for (int j = 0; j < sackRight.length(); j++) {
                    if (sackLeft.charAt(i) == sackRight.charAt(j)) {
                        sum += calcPriority(sackLeft.charAt(i));
                        break outerloop;
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
