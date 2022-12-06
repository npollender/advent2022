import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Solution10 {

    static int col = 9;

    public static void main(String args[]) throws FileNotFoundException {
        //read .txt file
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        //data
        String line = "";
        String raw[];
        ArrayList<Character>[] stack = new ArrayList[col];
        ArrayList<Character> buffer = new ArrayList<Character>();
        int move = 0;
        int from = 0;
        int to = 0;
        stack = init(stack);
        
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            //identify crates
            if (line.startsWith("[")) {
                int idx = 0;
                for (int i = 1; i < line.length(); i += 4) {
                    if (line.charAt(i) != ' ') 
                        stack[idx].add(line.charAt(i));
                    idx++;
                }
            }
            //move crates
            else if (line.startsWith("move")) {
                raw = line.split(" ");
                move = Integer.parseInt(raw[1]);
                from = Integer.parseInt(raw[3]) - 1;
                to = Integer.parseInt(raw[5]) - 1;
                for (int i = 0; i < move; i++) {
                    buffer.add(stack[from].get(stack[from].size() - 1));
                    stack[from].remove(stack[from].size() - 1);
                }
                Collections.reverse(buffer);
                stack[to].addAll(buffer);
                buffer.clear();
            }
            //reverse list
            else if (line.startsWith(" 1")) {
                for (int i = 0; i < col; i++)
                    Collections.reverse(stack[i]);
            }
        }
        sc.close();
        for(int i = 0; i < stack.length; i++)
            System.out.print(stack[i].get(stack[i].size() - 1));
    }

    public static ArrayList[] init(ArrayList arr[]) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = new ArrayList<Character>();
        return arr;
    }
}