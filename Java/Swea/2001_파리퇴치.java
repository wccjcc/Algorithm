/*
난이도 : D2
아이디어 : 2차원배열의 누적합을 이용해서, 파리 배열을 입력받을때 바로 누적합 배열을 만들고,
M만큼씩 누적합에서 구간합을 구해 max를 갱신합니다.
*/
import java.util.*;
import java.io.*;

public class Solution_2001_정우주
{
    //누적합에서 구간합 구하는 메서드드
    public static int guganhap(int[][] prefix,int i, int j,int M){
        return prefix[i+1][j+1] - prefix[i+1-M][j+1] - prefix[i+1][j+1-M] + prefix[i+1-M][j+1-M];
    }
	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int T = Integer.parseInt(br.readLine());
	    for(int tc=1; tc <= T; tc++){
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        int N = Integer.parseInt(st.nextToken()); //N = 전체 판
	        int M = Integer.parseInt(st.nextToken()); //M = 파리채 한 변
	        //파리 배열
	        int[][] arr = new int[N][N];
	        //파리 누적합 배열
	        int[][] prefix = new int[N+1][N+1];
	        //파리 입력받으면서 누적합 만들기
	        for(int i = 0 ; i < N; i++){
	            st = new StringTokenizer(br.readLine());
	            for(int j = 0 ; j < N ; j++){
	                arr[i][j] = Integer.parseInt(st.nextToken());
	                prefix[i+1][j+1] = prefix[i][j+1] + prefix[i+1][j] - prefix[i][j] + arr[i][j]; 
	            }
	        }
	        //최대한 많은 죽은파리의 수
	        int maxArea = 0;
	        //i = m-1~n-1, j = m-1~n-1 구간합 구해서 max 갱신하기기
	        for (int i = M-1 ; i <= N-1; i++){
	            for(int j = M-1; j <= N-1; j++){
	                maxArea = Math.max(maxArea,guganhap(prefix,i,j,M));
	            }
	        }
	        //출력
	        System.out.println("#"+tc+" "+maxArea);
	    }
	}
}
