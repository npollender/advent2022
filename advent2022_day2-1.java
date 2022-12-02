import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution3 {

    public static void main(String args[]) throws FileNotFoundException {
        //read .txt file
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        //data
        String line = "";
        char hand;
        int score = 0;

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            hand = line.charAt(2);
            switch (line.charAt(0)) {
                //ABC = opponent choice (rock paper scissors)
                //XYZ = your choice (rock paper scissors)
                //1 point for rock, 2 points for paper, 3 points for scissors
                //6 points for winning, 3 points for draw, 0 points for losing
                case 'A':
                    if (hand == 'X')
                        score += (1 + 3);
                    else if (hand == 'Y')
                        score += (2 + 6);
                    else
                        score += (3 + 0);
                    break;
                case 'B':
                    if (hand == 'X')
                        score += (1 + 0);
                    else if (hand == 'Y')
                        score += (2 + 3);
                    else
                        score += (3 + 6);
                    break;
                case 'C':
                    if (hand == 'X')
                        score += (1 + 6);
                    else if (hand == 'Y')
                        score += (2 + 0);
                    else
                        score += (3 + 3);
                    break;
            }
        }
        sc.close();
        System.out.println("Total score = " + score);
    }
}