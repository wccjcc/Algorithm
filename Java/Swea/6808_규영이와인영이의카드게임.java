/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
import java.io.*;
public class Main
{
    static List<int[]> mine = new ArrayList<>();
    static List<int[]> op = new ArrayList<>();
    
    
    
    static int[] cards;
    static int[] opcards;
    static int[] numbers = new int[9];
    
    static boolean[] isSelectedMine = new boolean[19];
    static boolean[] isSelectedOp = new boolean[19]; 
    
    public static int[] cardsOfOp(int[] mine){
        List<Integer> list = new ArrayList<>();
        int[] opcards = new int[9];
        for(int i = 1; i <= 18; i++){
            boolean flag = false;
            for(int m : mine){
                if (i == m){
                    flag = true;
                    break;
                }
            }
            if(flag == false){
                list.add(i);
            }
        }
        for(int i = 0; i < 9 ; i++){
            opcards[i] = list.get(i);
        }
        return opcards;
    }
    public static void permutations(int[] cards,int cnt,List<int[]> numberList,boolean[] isSelected){
        if(cnt == 9){
            numberList.add(numbers.clone());
            return;
        }
        for(int i : cards){
            if(isSelected[i]) continue;
            numbers[cnt] = i;
            isSelected[i] = true;
            permutations(cards,cnt+1,numberList,isSelected);
            isSelected[i] = false;
        }
        
    }
    
    public static int game(int[] me, int[] op){
        int meTotal = 0;
        int opTotal = 0;
        for(int i = 0 ; i < 9 ; i++){
            if(me[i] > op[i]){
                meTotal += me[i]+op[i];
            } else if (me[i] < op[i]){
                opTotal += me[i]+op[i];
            } 
        }
        if (meTotal > opTotal){
            return 1;
        } else if (meTotal < opTotal){
            return 0;
        }
        return -1;
    }
    
    
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int T = Integer.parseInt(br.readLine());
	    for(int tc = 1; tc <= T; tc++){
	        int wincnt = 0;
            int losecnt = 0;
	        cards = new int[9];
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        for(int i = 0 ; i < 9; i++){
	            cards[i] = Integer.parseInt(st.nextToken());
	        }
	        //상대방 카드 가져오기
	        opcards = cardsOfOp(cards);
	        //순열 만들기 
	        permutations(opcards,0,op,isSelectedOp);
	        
	        
	            for(int[] o : op){
	                int result = game(cards,o);
	                if(result== 1 ){
	                    wincnt++;
	                }else if (result == 0){
	                    losecnt++;
	                }
	            }
	            System.out.println("#"+tc+" "+wincnt+" "+losecnt);
	        }
	        
	        
	        
	    }
}
