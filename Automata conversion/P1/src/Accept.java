import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Accept {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner string = new Scanner(System.in);
        String str = new String(string.nextLine());
        File myObj = new File("src\\DFA_Input_1.txt");
        Scanner reader = new Scanner(myObj);
        String alphabet = reader.nextLine();
        String conditions = reader.nextLine();
        String start = reader.nextLine();
        String end = reader.nextLine();
        ArrayList<String> pro = new ArrayList<String>();
        while (reader.hasNextLine()) {
            String production = reader.nextLine();
            pro.add(production);
        }

        ArrayList<Character> numCon = new ArrayList<Character>();
        numCon.add(start.charAt(1));
        int f = 0;
        int d = 0;

        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);      // str is the string that user import
            f = 0;
            for (int j = 0; j < pro.size(); j++) {
                String p = pro.get(j);
                if (p.charAt(1) == numCon.get(numCon.size() - 1) && p.charAt(3) == s) {
                    numCon.add(p.charAt(6)); // find one direction to next state (there is not more than one because we have DFA
                    f = 1;
                    break;
                }

            }
            if (f != 1) {    //there is no direction to next state
                d = 1;
                break;
            }

        }
        int s = 0;
        if (d == 1)
            System.out.println("not accepted");
        else {
            for (int i = 1; i < end.length(); i = i + 3) {
                if (numCon.get(numCon.size() - 1) == end.charAt(i)) {   // the last state is one of the final stats
                    System.out.println("accepted");
                    s = 1;
                }
            }
            if (s == 0)
                System.out.println("not accepted");
        }
    }
}
