import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution17 {

    //bridge dims, increase if oob excep
    static int x = 1000;
    static int y = 1000;

    public static void main(String args[]) throws FileNotFoundException {
        //read .txt file
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        //data
        String line = "";
        int bridge[][] = new int[x][y];
        int head[] = {x / 2, y / 2}; //starting pos
        int tail[] = {x / 2, y / 2};
        char dir = 'X';
        int step = 0;
        int unique = 1;

        bridge[tail[0]][tail[1]]++; 

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            dir = line.charAt(0);
            step = Integer.parseInt(line.substring(2));
            //move head, then determine where tail should go.
            switch (dir) {
                case 'L':
                    head[1] -= step;
                    break;
                case 'R':
                    head[1] += step;
                    break;
                case 'U':
                    head[0] -= step;
                    break;
                case 'D':
                    head[0] += step;
                    break;
            }
            //head and tail overlap, or if adjacent, do nothing
            if (isAdjacent(head, tail)) continue;
            //follow head along x axis
            else if (head[0] == tail[0] && head[1] != tail[1]) {
                while (!isAdjacent(head, tail)) {
                    if (dir == 'L') tail[1]--;
                    else if (dir == 'R') tail[1]++;
                    bridge[tail[0]][tail[1]]++;
                    if (bridge[tail[0]][tail[1]] == 1) unique++;
                }
            }
            //follow head along y axis
            else if (head[0] != tail[0] && head[1] == tail[1]) {
                while (!isAdjacent(head, tail)) {
                    if (dir == 'U') tail[0]--;
                    else if (dir == 'D') tail[0]++;
                    bridge[tail[0]][tail[1]]++;
                    if (bridge[tail[0]][tail[1]] == 1) unique++;
                }
            }
            //x & y differ
            else {
                //step up/down, follow head along x axis
                if (head[0] == tail[0] + 1 || head[0] == tail[0] - 1) {
                    tail[0] = head[0];
                    while (!isAdjacent(head, tail)) {
                        if (dir == 'L') tail[1]--;
                        else if (dir == 'R') tail[1]++;
                        bridge[tail[0]][tail[1]]++;
                        if (bridge[tail[0]][tail[1]] == 1) unique++;
                    }
                }
                //step left/right, follow head along y axis
                else if (head[1] == tail[1] + 1 || head[1] == tail[1] - 1) {
                    tail[1] = head[1];
                    while (!isAdjacent(head, tail)) {
                        if (dir == 'U') tail[0]--;
                        else if (dir == 'D') tail[0]++;
                        bridge[tail[0]][tail[1]]++;
                        if (bridge[tail[0]][tail[1]] == 1) unique++;
                    }
                }
            }
        }
        sc.close();
        System.out.print("Unique tail positions = " + unique);
    }

    public static boolean isAdjacent(int head[], int tail[]) {
        return ((head[0] == tail[0] && head[1] == tail[1])             || //overlap
                (head[0] == tail[0] && head[1] == (tail[1] + 1))       || //sides
                (head[0] == tail[0] && head[1] == (tail[1] - 1))       ||
                (head[0] == (tail[0] + 1) && head[1] == tail[1])       ||
                (head[0] == (tail[0] - 1) && head[1] == tail[1])       ||
                (head[0] == (tail[0] + 1) && head[1] == (tail[1] + 1)) || //corners
                (head[0] == (tail[0] - 1) && head[1] == (tail[1] - 1)) ||
                (head[0] == (tail[0] + 1) && head[1] == (tail[1] - 1)) ||
                (head[0] == (tail[0] - 1) && head[1] == (tail[1] + 1))
                );
    }
}