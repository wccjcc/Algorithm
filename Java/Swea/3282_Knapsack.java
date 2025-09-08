/*
swea/(3282) 0/1 Knapsack[D3]
제한시간 : Java-2초 / 제한메모리 : 256MB
아이디어 : 물건들을 하나씩 고려해봤을때 최고이득을 배낭용량에 따라 갱신합니다.
*/
import java.util.*;
import java.io.*;
public class Main
{
    static int[][] stuff; //물건에 대한 정보를 담을 배열열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T ; tc++){
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int n = Integer.parseInt(st.nextToken()); //물건의 개수(1~N)
		    int k = Integer.parseInt(st.nextToken()); //배낭에 넣을수 있는 최대 부피피
		    stuff = new int[n][2];
		    for(int i = 0 ; i < n ; i++){
		        st = new StringTokenizer(br.readLine());
		        int v = Integer.parseInt(st.nextToken()); //물건의 부피 
		        int c = Integer.parseInt(st.nextToken()); //물건의 가치 
		        stuff[i][0] = v;
		        stuff[i][1] = c;
		    }
		    int[][] S = new int[n+1][k+1]; //최고이득을 담을 배열 (패딩을위해 원래 사이즈 +1)
		    for(int i = 1 ; i <= n ; i++){ //물건 1부터 N까지 고려하면서 
		        for(int j = 1; j <= k ; j++){ //배낭용량을 1부터 k까지 
		            int tV = stuff[i-1][0]; //현재 고려하고있는 물건의 부피 
		            int tC = stuff[i-1][1]; //현재 고려하고있는 물건의 가치 
		            if (tV > j){ //현재 보고있는 최대부피보다 물건의 부피가 크다면 
		                S[i][j] = S[i-1][j]; //이 물건을 고려안했을때 그 전 물건 고려시 최대이득 
		            } else{ //아니라면 
		                S[i][j] = Math.max(S[i-1][j-tV]+tC,S[i-1][j]); //이 물건 넣는것과, 안넣었을때중에서 가장 이득 큰 것 선택택
		            }
		        } 
		    }
		    System.out.println("#"+tc+" "+S[n][k]); //S[n][k] 구하기기
		}
	}
}
