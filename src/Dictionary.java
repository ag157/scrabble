

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.max;


public class Dictionary {
    public static String FILE_PATH="src/Files/sowpods.txt";

    public static int MaxLengthInStringArrayList(ArrayList<String> arrayList){
        int max=0;
        for(int i=0;i< arrayList.size();i++){
            max= max(max,arrayList.get(i).length());
        }
        return max;
    }
    public static ArrayList<String> getDictonayArray() throws FileNotFoundException {
        ArrayList<String> dictionaryArray = new ArrayList<>();
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            dictionaryArray.add(scanner.nextLine());
        }
        return dictionaryArray;
    }




    public static void main(String[] args){
        try {
            /*ArrayList<String> dictionaryArray=getDictonayArray();
            for(String str: dictionaryArray){
                System.out.println(str);
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}