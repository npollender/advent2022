import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

class Solution12 {

    public static void main(String args[]) throws FileNotFoundException {
        //read .txt file
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);

        //data
        String line = sc.nextLine();
        char marker[] = new char[14];
        int sop = 0;

        for (int i = 0; i < line.length(); i++) {
            for (int j = marker.length - 1; j > 0; j--)
                marker[j] = marker[j - 1];           
            marker[0] = line.charAt(i);
            if (i < marker.length - 1) continue;
            else if (!isDuped(marker)) {
                sop = i + 1;
                break;
            }
        }
        sc.close();
        System.out.println("Start-of-packet = " + sop);
    }

    public static boolean isDuped(char[] arr) {
        HashSet<Character> set = new HashSet<>();
        for (char c : arr)
            if (!set.add(c)) return true;
        return false;
    }
}