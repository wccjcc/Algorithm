/*
swea-(1952)수영장 (dp version)
아이디어 : dp의 기준을 월 단위로 잡아서, 한달 안에서 일권과 월권을 사용하는 것에서 이득을 취하고,
dp 메인 로직에서 이득취한것과 3달권을 사용한것에서 이득을 취했습니다.
마지막으로 그렇게 12월까지 구한 dp값과 1년권을 비교합니다.
*/
import java.util.*;
import java.io.*;
public class Main
{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t ; tc++){
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int costOne = Integer.parseInt(st.nextToken()); //1일권
		    int costM = Integer.parseInt(st.nextToken()); //1달권
		    int costTM = Integer.parseInt(st.nextToken()); //3달권 
		    int costY = Integer.parseInt(st.nextToken()); //1년권 
		    int[] plan = new int[13]; //달마다의 계획을 담을 배열 
		    int[] dp = new int[13]; //dp 메인 배열 
		    st = new StringTokenizer(br.readLine());
		    //계획 입력받기기
		    for(int i = 1 ; i < 13; i++){
		        plan[i] = Integer.parseInt(st.nextToken());
		    }
		    //dp 로직
		    for(int i = 1; i < 13; i++){
		        //첫번째 인자 : 전달까지의 dp값(dp[i-1]) + 이번달에 1일권과 한달권중에 뭐가 이득인지 값 
		        //두번째 인자 : 최근3달동안 3달권을 쓰는 경우 (i-3이 0보다 작으면 안되므로 max처리해줌 )
		        dp[i] = Math.min(dp[i-1]+Math.min(plan[i]*costOne,costM),dp[Math.max(0,i-3)]+costTM);
		    }
		    System.out.println("#"+tc+" "+Math.min(dp[12],costY)); //1일권,한달권,3달권을 고려했을때 최솟값과 1년권을 비교하여 이득인것을 선택택
		    
		    
		}
	}
}
