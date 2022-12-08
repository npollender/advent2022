import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution16 {

    static int dim = 99;

    public static void main(String args[]) throws FileNotFoundException {
        //read .txt file
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        //data
        String line = "";
        int trees[][] = new int[dim][dim];
        int distance[] = new int[5]; //index 4 is the tmp score
        int scenicScore = 0;

        while (sc.hasNextLine()) {
            for (int i = 0; i < dim; i++) {
                line = sc.nextLine();
                for (int j = 0; j < dim; j++)
                    trees[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }
        sc.close();

        //check distances
        for (int i = 1; i < dim - 1; i++) {
            for (int j = 1; j < dim - 1; j++) {
                for (int k = 0; k < distance.length; k++) distance[k] = 0;
                //left
                for (int k = j - 1; k >= 0; k--) {
                    distance[0]++;
                    if (trees[i][k] >= trees[i][j]) break;
                }

                //right
                for (int k = j + 1; k < dim; k++) {
                    distance[1]++;
                    if (trees[i][k] >= trees[i][j]) break;
                }

                //top
                for (int k = i - 1; k >= 0; k--) {
                    distance[2]++;
                    if (trees[k][j] >= trees[i][j]) break;
                }

                //bottom
                for (int k = i + 1; k < dim; k++) {
                    distance[3]++;
                    if (trees[k][j] >= trees[i][j]) break;
                }

                distance[4] = distance[0] * distance[1] * distance[2] * distance[3];
                if (distance[4] > scenicScore)
                    scenicScore = distance[4];
            }
        }

        System.out.print("Scenic score of best tree = " + scenicScore);
    }
}