/*
Baekjoon/(12865)평범한 배낭[골드5]
시간:172ms/메모리:53824KB
아이디어:0/1 knapsack dp 문제와 똑같은 풀이입니다다
*/
import java.util.*;
import java.io.*;
public class Main
{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //물품의 수 
		int k = Integer.parseInt(st.nextToken()); //버틸수 있는 무게
		int[][] stuff = new int[n][2];
		//물건에 대한 무게와 가치 입력받기 
		for(int i = 0 ; i < n ; i++){
		    st = new StringTokenizer(br.readLine());
		    stuff[i][0] = Integer.parseInt(st.nextToken());
		    stuff[i][1] = Integer.parseInt(st.nextToken());
		}
		//정렬하기 
		Arrays.sort(stuff, (a,b) -> Integer.compare(a[0],b[0])); //무게 기준으로 오름차순 정렬 
		//dp로 최적 구하기
		int[][] dp = new int[n+1][k+1];
		for(int i = 1 ; i <= n ; i++){
		    for(int j = 1 ; j <= k ; j++){
		        int tV = stuff[i-1][0]; //현재 보고있는 물건의 무게 
		        int tC = stuff[i-1][1]; //현재 보고있는 물건의 가치 
		        if(tV > j){ //현재 고려하는 무게가 현재 보고있는 물건의 무게보다 작다면 넣을 수 없음 
		            dp[i][j] = dp[i-1][j]; //그 전 무게를 고려했을때껄로 
		        } else{ //아니라면 넣을 수 있음 
		            dp[i][j] = Math.max(dp[i-1][j-tV]+tC,dp[i-1][j]); //물건을 넣었을 때와 넣지 않았을때 이득중 큰것을 채택 
		        }
		    }
		}
		System.out.println(dp[n][k]); //모든 물건을 고려하고, k무게로 고려했을때의 답 출력 
	}
}
