import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static int[] points= new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4,4, 8, 4, 10};

    public static int ComputeScore(String str){
        int score=0;
        for(int i=0;i<str.length();i++){
            score +=points[(int) (str.charAt(i) - 'A')];
        }
        return score;
    }

    public static int CheckandScoreCurrWord(int[] countArray, String word , int diff){
        int negCount=0;
        int[] temparr = new int[26];
        for(int i=0;i<26;i++){
            temparr[i]= countArray[i];
        }
        for(int i=0;i<word.length();i++){
            int temp= --temparr[(int) (word.charAt(i) - 'A')];
            if(temp <0) {
                negCount++;
            }
        }
        if(negCount <= diff){
            return ComputeScore(word);
        }
        return 0;
    }

    public static void MaxScrabbleScore(ArrayList<String> dictionaryArray,int mCase,String alphabets, int position, char fixChar){
        int[] countArray = new int[26];
        for(int i=0;i<alphabets.length();i++){
            countArray[(int) (alphabets.charAt(i) - 'A')]++;
        }
        int maxscore=0;
        String maxWord="";
        if(mCase==0 || mCase==1) {
            for (int i = 0; i < dictionaryArray.size(); i++) {
                if (maxscore < CheckandScoreCurrWord(countArray, dictionaryArray.get(i), mCase)) {
                    maxscore = CheckandScoreCurrWord(countArray, dictionaryArray.get(i), mCase);
                    maxWord = dictionaryArray.get(i);
                }
            }
        }else if( mCase==2){
            int[] temparr = new int[26];
            for(int i=0;i<26;i++){
                temparr[i]= countArray[i];
            }
            temparr[fixChar - 'A']++;
            for(int i=0;i<dictionaryArray.size();i++){
                if(dictionaryArray.get(i).length() > position && dictionaryArray.get(i).charAt(position)==fixChar) {
                    if (maxscore < CheckandScoreCurrWord(temparr, dictionaryArray.get(i), 0)) {
                        maxscore = CheckandScoreCurrWord(temparr, dictionaryArray.get(i), 0);
                        maxWord = dictionaryArray.get(i);
                    }
                }
            }
        }
        System.out.println("Case " +(mCase+1) + ": " +maxWord + ":" + maxscore);
    }


    public static void main(String[] args) {
        Dictionary dictionary= new Dictionary();
        try {
            ArrayList<String> dictionaryArray = dictionary.getDictonayArray();


            /**************************************************************************
                Case 1: No tile on board and no wild card.
             **************************************************************************/
            String alphabets="QUARTZY";
            MaxScrabbleScore(dictionaryArray, 0,alphabets,0,'A');



            /**************************************************************************
             Case 2: No tile on board and with wild card.
             **************************************************************************/
            alphabets="SQUISH";
            MaxScrabbleScore(dictionaryArray,1,alphabets,0,'A');



            /**************************************************************************
             Case 3: Fixed character at a fixed position
             **************************************************************************/
            alphabets="SQUISHY";
            MaxScrabbleScore(dictionaryArray,2,alphabets,2,'Q');

            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
