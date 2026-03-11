//시간복잡도 : O(p_n*s_n)
import java.util.*;
import java.io.*;

public class Main
{
    static int[] switches;
    static int s_n;
    
    public static void doMansCommand(int num){
        for(int i = num-1; i < s_n ; i++){
            if ((i+1)%num == 0) switches[i] = (switches[i]+1)%2;
        }
    }
    
    public static void doWomansCommand(int num){
        int i = 1;
        switches[num-1] = (switches[num-1]+1)%2;
        while(true){
            int left = num-i-1;
            int right = num+i-1;
            if(left<0 || right >= s_n) break;
            if (switches[left] != switches[right]) break;
            switches[left] = (switches[left]+1)%2;
            switches[right] = (switches[right]+1)%2;
            i++;
        }
    }
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    s_n = Integer.parseInt(br.readLine());
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    switches = new int[s_n];
	    for(int i = 0 ; i < s_n ; i++){
	        switches[i] = Integer.parseInt(st.nextToken());
	    }
	    int p_n = Integer.parseInt(br.readLine());
	    
	    //성별과 받은 스위치 번호로 명령 수행하기
	    for(int i = 0 ; i < p_n ; i++){
	        st = new StringTokenizer(br.readLine());
	        int sex = Integer.parseInt(st.nextToken());
	        int num = Integer.parseInt(st.nextToken());
	        if(sex == 1) doMansCommand(num);
	        else doWomansCommand(num);
	    }
	    
	    //스위치 상태 출력
	    for(int i = 0 ; i < s_n ; i++){
	        System.out.print(switches[i]+" ");
	        if((i+1)%20 == 0) System.out.println();
	    }
	    
	}
}
