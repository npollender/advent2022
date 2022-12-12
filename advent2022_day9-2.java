import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution18 {

    //bridge dims, increase if oob excep, smaller size = better performance
    static int x = 1000;
    static int y = 1000;
    //# of tails
    static int tails = 9;

    public static void main(String args[]) throws FileNotFoundException {
        //read .txt file
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        //data
        String line = "";
        int bridge[][] = new int[x][y];
        int rope[][] = new int[tails + 1][2]; //index 0 is head, index 9 is tail
        char dir = 'X';
        int step = 0;
        int unique = 1;

        //init list
        for (int i = 0; i < rope.length; i++) {
            rope[i][0] = x / 2;
            rope[i][1] = y / 2;
        }

        bridge[rope[tails][0]][rope[tails][1]]++; 

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            dir = line.charAt(0);
            step = Integer.parseInt(line.substring(2));
            //move head one step at a time, rest of the rope follows per iteration
            for (int head = 0; head < step; head++) {
                switch (dir) {
                    case 'L':
                        rope[0][1]--;
                        break;
                    case 'R':
                        rope[0][1]++;
                        break;
                    case 'U':
                        rope[0][0]--;
                        break;
                    case 'D':
                        rope[0][0]++;
                        break;
                }
                //move each segment of rope
                //this could absolutely be polished, but it works for now...
                for (int i = 1; i < rope.length; i++) {
                    //head and tail overlap, or if adjacent, do nothing
                    if (isAdjacent(rope[i - 1], rope[i])) break;
                    //follow head along x axis
                    else if (rope[i - 1][0] == rope[i][0] && rope[i - 1][1] != rope[i][1]) {
                        while (!isAdjacent(rope[i - 1], rope[i])) {
                            if (rope[i - 1][1] < rope[i][1]) rope[i][1]--;
                            else if (rope[i - 1][1] > rope[i][1]) rope[i][1]++;
                        }
                    }
                    //follow head along y axis
                    else if (rope[i - 1][0] != rope[i][0] && rope[i - 1][1] == rope[i][1]) {
                        while (!isAdjacent(rope[i - 1], rope[i])) {
                            if (rope[i - 1][0] < rope[i][0]) rope[i][0]--;
                            else if (rope[i - 1][0] > rope[i][0]) rope[i][0]++;
                        }
                    }
                    //x & y differ, aka diagonal
                    else {
                        if (Math.abs(rope[i - 1][0] - rope[i][0]) > 0 &&
                            Math.abs(rope[i - 1][1] - rope[i][1]) > 0) {
                            while (Math.abs(rope[i - 1][0] - rope[i][0]) > 1 &&
                                   Math.abs(rope[i - 1][1] - rope[i][1]) > 1) {
                                if (rope[i - 1][0] > rope[i][0] && rope[i - 1][1] > rope[i][1]) {
                                    rope[i][0]++;
                                    rope[i][1]++;
                                } else if (rope[i - 1][0] < rope[i][0] && rope[i - 1][1] < rope[i][1]) {
                                    rope[i][0]--;
                                    rope[i][1]--;
                                } else if (rope[i - 1][0] > rope[i][0] && rope[i - 1][1] < rope[i][1]) {
                                    rope[i][0]++;
                                    rope[i][1]--;
                                } else {
                                    rope[i][0]--;
                                    rope[i][1]++;
                                }
                            }
                            if (isAdjacent(rope[i - 1], rope[i])) continue;
                        }
                        if (Math.abs(rope[i - 1][0] - rope[i][0]) > Math.abs(rope[i - 1][1] - rope[i][1])) {
                            rope[i][1] = rope[i - 1][1];
                            while (!isAdjacent(rope[i - 1], rope[i])) {
                                if (rope[i - 1][0] < rope[i][0]) rope[i][0]--;
                                else if (rope[i - 1][0] > rope[i][0]) rope[i][0]++;
                            }
                        }
                        else {
                            rope[i][0] = rope[i - 1][0];
                            while (!isAdjacent(rope[i - 1], rope[i])) {
                                if (rope[i - 1][1] < rope[i][1]) rope[i][1]--;
                                else if (rope[i - 1][1] > rope[i][1]) rope[i][1]++;
                            }
                        }
                    }
                }
                bridge[rope[tails][0]][rope[tails][1]]++;
                if (bridge[rope[tails][0]][rope[tails][1]] == 1) unique++;
            }
            //printBridge(rope, bridge);
        }
        sc.close();
        System.out.print("Unique tail-end positions = " + unique);
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

    //purely for visualization
    public static void printBridge(int rope[][], int bridge[][]) {
        for (int i = 0; i < x; i++) {
            hori:
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < rope.length; k++) {
                    if (i == rope[k][0] && j == rope[k][1]) {
                        if (k == 0) System.out.print("H");
                        else System.out.print(k);
                        continue hori;
                    }
                }
                if (i == x / 2 && j == y / 2) {
                    System.out.print("s");
                } else if (bridge[i][j] > 0) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}