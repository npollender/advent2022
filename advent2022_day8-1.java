import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Solution15 {

    static int dim = 99;

    public static void main(String args[]) throws FileNotFoundException {
        //read .txt file
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        //data
        String line = "";
        int trees[][] = new int[dim][dim];
        ArrayList<Integer[]> viewable = new ArrayList<>();
        int tallest = 0;

        while (sc.hasNextLine()) {
            for (int i = 0; i < dim; i++) {
                line = sc.nextLine();
                for (int j = 0; j < dim; j++)
                    trees[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }
        sc.close();

        //consider viewing trees from all 4 directions...
        //left
        for (int i = 0; i < dim; i++) {
            tallest = 0;
            for (int j = 0; j < dim; j++) {
                Integer coord[] = {i, j};
                if (i == 0 || j == 0 || i == dim - 1 || j == dim - 1 || trees[i][j] > tallest) {
                    if (!alreadySeen(coord, viewable)) viewable.add(coord);
                    tallest = trees[i][j];
                    if (tallest == 9) break;
                }
            }
        }

        //right
        for (int i = 0; i < dim; i++) {
            tallest = 0;
            for (int j = dim - 1; j >= 0; j--) {
                Integer coord[] = {i, j};
                if (trees[i][j] > tallest) {
                    if (!alreadySeen(coord, viewable)) viewable.add(coord);
                    tallest = trees[i][j];
                    if (tallest == 9) break;
                }
            }
        }

        //top
        for (int j = 0; j < dim; j++) {
            tallest = 0;
            for (int i = 0; i < dim; i++) {
                Integer coord[] = {i, j};
                if (trees[i][j] > tallest) {
                    if (!alreadySeen(coord, viewable)) viewable.add(coord);
                    tallest = trees[i][j];
                    if (tallest == 9) break;
                }
            }
        }

        //bottom
        for (int j = 0; j < dim; j++) {
            tallest = 0;
            for (int i = dim - 1; i >= 0; i--) {
                Integer coord[] = {i, j};
                if (trees[i][j] > tallest) {
                    if (!alreadySeen(coord, viewable)) viewable.add(coord);
                    tallest = trees[i][j];
                    if (tallest == 9) break;
                }
            }
        }

        System.out.print("Number of viewable trees from all directions = " + viewable.size());
    }

    public static boolean alreadySeen(Integer coord[], ArrayList<Integer[]> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i)[0] == coord[0] && arr.get(i)[1] == coord[1]) return true;
        }
        return false;
    }
}