/*
난이도 : D3
아이디어 : 중복없는 조합을 이용하고, L칼로리를 초과해버리면 return, 나머지는 계속 
점수를 갱신시킵니다.
*/
import java.util.*;
import java.io.*;

public class Solution_5215_정우주
{
    static int N,L,max; //N(재료의수),L(제한칼로리),max(정답-최대점수)
    static List<int[]> ingredient; //재료에대한 정보를 담은 리스트
    
    //조합 메서드
    public static void combination(int start,int score, int kcal){
        
        if (kcal > L) return; //제한칼로리를 초과하면 return
        max = Math.max(max,score); //max 갱신
        //start부터 (전인덱스의 다음부터 N-1까지 반복)
        for (int j = start ; j < N ; j++){
            //재료를 선택했다고 가정하고 점수와 칼로리에 더하고, j+1을 start로 넘겨줘서 재귀호출
            combination(j+1,score+ingredient.get(j)[0],kcal+ingredient.get(j)[1]);
        }
        
        
    }
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int T = Integer.parseInt(br.readLine());
	    for(int tc = 1; tc <= T; tc++){
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        N = Integer.parseInt(st.nextToken());
	        L = Integer.parseInt(st.nextToken());
	        ingredient = new ArrayList<>();
	        //재료 정보 입력받기
	        for (int i = 0; i < N ; i++){
	            st = new StringTokenizer(br.readLine());
	            int s = Integer.parseInt(st.nextToken());
	            int k = Integer.parseInt(st.nextToken());
	            ingredient.add(new int[]{s,k});
	        }
	        //테스트케이스마다 max가 다르므로 0으로 초기화
	        max = 0;
	        //combination 실행
	        combination(0,0,0);
	        
	        System.out.println("#" + tc + " " + max);

	        
	        
	    }
	}
}
