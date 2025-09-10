/*
swea/(1263)사람네트워크2[D6]
아이디어 : 플로이드워샬 기본문제로, 인접행렬을 입력받아 출발지와목적지가 같은경우에는 0으로 초기화,
인접하면 1로 초기화, 아닌데 값이 0이면 충분히 큰 값으로 넣어주어 현재는 갈 수 없음을 표현했습니다.
여기서 중요했던점은 INF 를 Integer.MAX_VALUE로 하면 overflow가 일어나 min 을 제대로 계산할 수 없었기 때문에
N은 최대 1000이고 최악의 거리는 999이므로 1000000정도로 크게 잡아주었습니다.

*/
import java.util.*;
import java.io.*;
public class Main
{
    static int[][] D; //인접행렬 받을 배열 
    static int N, INF = 1000000; //N : 사람의 수, INF : 충분히 큰 값(갈수없음을 나타냄)
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T ; tc++){
		    StringTokenizer st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		D = new int[N][N];
    		//인접행렬 입력받기 
    		for (int i = 0 ; i < N ; i++){
    		    for(int j = 0 ; j < N ; j++){
    		        int temp = Integer.parseInt(st.nextToken());
    		        if (i == j) D[i][j] = 0; //출발지와 목적지가 같으면 거리 0으로 표시 
    		        else{
    		            if (temp == 1) D[i][j] = 1; //인접하다고 나온 경우에는 1로 표시 
    		            else if (temp == 0) D[i][j] = INF; //인접하지 않다고 나온 경우에는 INF로 표시 
    		        }
    		    }
    		}
    		//플로이드워샬 알고리즘 실행
    		for (int k = 0 ; k < N ; k++){ //경유지 
    		    for(int i = 0 ; i < N ; i++){ //출발지 
    		        if(i==k) continue; //출발지와 경유지가 같으면 넘어가기 
    		        for(int j = 0 ; j < N ; j++){ //도착지 
    		            if(j==k || i==j) continue; //도착지와 경유지가 같거나, 출발지와 경유지가 같으면 넘어가기 
    		            D[i][j] = Math.min(D[i][k]+D[k][j],D[i][j]); // k를 경유해서 가는것과, 그냥 가는것 비교하기 
    		        }
    		    }
    		}
    		//CC값 구하고 min 갱신하기
    		int min = Integer.MAX_VALUE;
    		for(int i = 0; i < N; i++){
    		    int CC = 0; //CC(i) 초기화 
    		    for(int j = 0; j < N; j++){
    		        CC += D[i][j]; //Dij의 합 구하기 
    		    }
    		    if (min > CC) min = CC; //최솟값 갱신 
    		}
    		
    		//정답 출력
    		System.out.println("#"+tc+" "+min);    
		}
		
		
	}
}
