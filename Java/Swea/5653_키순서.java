/*
(5643) 키순서 [D4]
시간 : 0.07587s 
아이디어 : tallList라는 이름의 2차원 int배열을 만들어 크기비교 정보를 boolean 형태로 기록해두고,
각 사람마다 그 배열을 돌면서 tall+small 사람수가 자기자신을제외한 모든 사람수와 같을 때 
자신의 순서를 알 수 있으므로 answer에 추가했습니다.
*/
import java.util.*;
import java.io.*;
public class 5653_키순서
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t ; tc++){
		    int n = Integer.parseInt(br.readLine()); //학생들의 수
		    int m = Integer.parseInt(br.readLine()); //관계의 수수
		    
		    boolean [][] tallList = new boolean[n][n]; //누가 누구보다 큰지에 대한 여부를 담을 배열열
		    for(int i = 0 ; i < m ; i++){
		        StringTokenizer st = new StringTokenizer(br.readLine());
		        int a = Integer.parseInt(st.nextToken())-1; //둘중 작은 수
		        int b = Integer.parseInt(st.nextToken())-1; //둘중 큰 수수
		        tallList[a][b] = true; //a보다 b가 큰것에 대해 tallList에 기록록
		    }
		    int answer = 0;
		    for(int i = 0 ; i < n; i++){
		        for(int j = 0 ; j < n ; j++){
		            for(int k  = 0 ; k < n ; k++){
		                //i < j 그리고 j < k 면 i < k를 이용
		                if(tallList[i][j] && tallList[j][k]){
		                    tallList[i][k] = true;
		                }
		            }
		        }
		    }
		    for(int i = 0 ; i < n ; i++){
		        int tall = 0; //자기보다 큰 사람의 수 
		        int small = 0; //자기보다 작은 사람의 수
		        //기준 사람(i) 보다 큰 사람, 작은사람의 수를 업데이트하기기
		        for(int j = 0 ; j < n ; j++){
		            if (tallList[i][j]) tall++;
		            if (tallList[j][i]) small++;
		        }
		        //tall+small 사람의 수가 자기자신을 제외한 수일때 answer++
		        if(tall+small == n-1) answer++;
		    }
		    System.out.println("#"+tc+" "+answer);
		}
	}
}
