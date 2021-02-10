import java.io.*;
import java.util.*;

public class Conversion {
    public static void main(String[] args) throws IOException {
        File myObj = new File("src\\NFA_Input_2.txt");
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
        ArrayList<Character> co = new ArrayList<Character>();
        ArrayList<Character> co1 = new ArrayList<Character>();
        ArrayList<Character> landaha = new ArrayList<Character>();
        ArrayList<Character> nextha = new ArrayList<Character>();
        String str = "";


        String g = "";
        g += start.charAt(1);
        ArrayList<Character> mmm = new ArrayList<Character>();
        mmm = landa(start.charAt(1), pro);
        for (Character s : mmm) {
            str += s;
        }

        ArrayList<String> finalList = new ArrayList<String>();
        ArrayList<String> mainmain = new ArrayList<String>();

        mainmain.add(str);   //add first new stat to mainmain
        String lan = "";
        String m;
        int size1 = 0;
        int size2 = 0;
        int sr = 0;
        while (size2 >= size1) {   // while(true)
            m = mainmain.get(sr);
            size1 = mainmain.size();

            for (int k = 1; k < alphabet.length(); k = k + 2) {
                for (int l = 0; l < m.length(); l++) {
                    co1 = next(m.charAt(l), alphabet.charAt(k), pro);
                    for (Character ch : co1) {                 // find nextha
                        if (!nextha.contains(ch))
                            nextha.add(ch);
                    }

                }

                for (Character ch2 : nextha) {          //find λ_closure(nextha)
                    co = landa(ch2, pro);
                    for (Character ch : co) {
                        if (!landaha.contains(ch))
                            landaha.add(ch);
                    }
                }

                for (Character s : landaha) {
                    lan += s;
                }
                char[] chars = lan.toCharArray();
                Arrays.sort(chars);
                String sorted = new String(chars);
                if (mainmain.contains(sorted)) {         //to check that this state is new or not

                    if (!sorted.isEmpty())
                        finalList.add(m + '-' + alphabet.charAt(k) + '-' + sorted);
                } else {

                    mainmain.add(sorted);

                    if (!sorted.isEmpty())
                        finalList.add(m + '-' + alphabet.charAt(k) + '-' + sorted);
                }
                nextha.clear();
                landaha.clear();
                lan = "";


            }
            sr++;
            size2 = mainmain.size();
            if (size2 == size1 && sr == mainmain.size())    //if all of the members of mainmain are check
                                                                //and there in no new state so break
                break;

        }

        PrintWriter writer = new PrintWriter("src\\DFA_Output _2.txt", "UTF-8");
                                       // if there is not this file at src so create that
        writer.println(alphabet);
        for (String ma : mainmain) {        //write conditions
            if (!ma.isEmpty()) {
                for (int r = 0; r < ma.length(); r++)
                    writer.print("q" + ma.charAt(r));
                writer.print('\t');
            }
        }
        writer.println();
        String nn = mainmain.get(0);       //write start state
        for (int gg = 0; gg < nn.length(); gg++) {
            writer.print("q" + nn.charAt(gg));
        }

        writer.println();
        for (String ma : mainmain) {       //write final states
            if (!ma.isEmpty()) {
                for (int bb = 0; bb < end.length(); bb++) {
                    if (ma.indexOf(end.charAt(bb)) != -1) { //if ma contain one of the final states so get index of that
                                                                  //otherwise return -1
                        for (int r = 0; r < ma.length(); r++)
                            writer.print("q" + ma.charAt(r));
                        writer.print('\t');
                        break;
                    }
                }
            }
        }
        writer.println();

        for (String f : finalList) {

            String[] parts = f.split("-");
            for (int s = 0; s < parts[0].length(); s++) {
                writer.print("q" + f.charAt(s));
            }
            writer.print(' ' + parts[1] + ' ');

            for (int s = parts[0].length() + 3; s < f.length(); s++) {
                writer.print("q" + f.charAt(s));
            }

            writer.println();


        }
        writer.close();


    }

    public static ArrayList<Character> next(Character chr1, Character chr2, ArrayList<String> pro) {
        ArrayList<Character> chr3 = new ArrayList<Character>();
        for (int i = 0; i < pro.size(); i++) {
            String g = pro.get(i);
            if (chr1 == g.charAt(1) && chr2 == g.charAt(3))
                chr3.add(g.charAt(6));
        }

        return chr3;
    }

    public static ArrayList<Character> landa(char k, ArrayList<String> pro) {
        ArrayList<Character> landaha1 = new ArrayList<Character>();
        ArrayList<Character> landaha2 = new ArrayList<Character>();
        ArrayList<Character> landaha3 = new ArrayList<Character>();
        landaha3.add(k);
        int f = 0;
        ArrayList<Character> nex = new ArrayList<Character>();
        nex = next(k, 'λ', pro);
        for (Character bm : nex) {
            if (!landaha1.contains(bm)) {
                landaha1.add(bm);

            }
        }

        Character n;
        for (Character land : landaha1) {
            n=land;
            landaha2.add(n);
            for (int i = 0; i < pro.size(); i++) {
                String h = pro.get(i);
                if (landaha2.get(landaha2.size() - 1) == h.charAt(1) && h.charAt(3) == 'λ') {
                    if (!landaha2.contains(h.charAt(6)))
                        landaha2.add(h.charAt(6));

                }


            }
            for (Character b:landaha2) {
                if(!landaha3.contains(b))
                    landaha3.add(b);

            }
            landaha2.clear();
        }

        return landaha3;

    }


}
