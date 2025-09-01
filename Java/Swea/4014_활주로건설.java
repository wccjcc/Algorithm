/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
import java.io.*;
public class 4014_활주로건설
{
    static int n,x;
    static int[][] map;
    static boolean checkSlope(int[] arr){
        int now = arr[0];
        int cnt = 1;//활주로를 놓을 수 있는 도로 수
        List<Integer> list = new ArrayList<>();//설치한 인덱스 담을 리스트
        //그뒤부터 계속 확인
        for(int i = 1; i < n; i++){
            //1. 평지 인 경우
            if(now == arr[i]) cnt++;
            //2. 오르막길인 경우
            else if (arr[i]-now == 1){
                //실패 : cnt가 부족한경우
                if(cnt < x) return false;
                //성공
                int idx = i-1; //
                int runCnt = x;
                while(true){
                    if(runCnt <= 0) break; //x만큼 써야해
                    if(list.contains(idx)) return false; //이미 지형이 쌓인경우
                    list.add(idx--);
                    runCnt--;
                }
                now = arr[i];
                cnt = 1;
            } else if (now-arr[i] == 1) { //3. 내리막길인 경우
                int cnt2 = 1;
                list.add(i);
                //그 뒤로 활주로를 놓을 수 있는지 확인
                for(int j = i+1; j < n ; j++){
                    //일단 활주로를 놓을수있는 높이가 일정한지 확인
                    if(now-1 == arr[j]){
                        list.add(j);
                        cnt2++;
                    } else break;
                    //x만큼의 길이가 확보된 순간
                    if(cnt2 >= x){
                        now = arr[j];
                        i = j;
                        break;
                    }
                }
                if (cnt2 < x) return false; //길이가 안되는경우에는 false
                cnt =1; //다시 초기화
            } else { //4. 그 외의 경우 (2이상 차이나는 경우)
                return false;
            }
        }
        return true; //다 통과한 경우
    }
    static int solve(){
        int answer = 0;
        //가로
        for(int i = 0 ; i < n ; i++){
            if(checkSlope(map[i])){
                answer++;
            }
        }
        //세로
        for(int j = 0 ; j < n ; j++){
            int[] arr = new int[n];
            for(int i = 0; i < n ; i++){
                arr[i] = map[i][j];
            }
            if(checkSlope(arr)){
                answer++;
            }
        }
        
        
        return answer;
    }
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t ; tc++){
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    n = Integer.parseInt(st.nextToken());
		    x = Integer.parseInt(st.nextToken());
		    map = new int[n][n];
		    for(int i = 0 ; i < n ; i++){
		        st = new StringTokenizer(br.readLine());
		        for(int j = 0 ; j < n ; j++){
		            map[i][j] = Integer.parseInt(st.nextToken());
		        }
		    }
		    
		    System.out.println("#"+tc+" "+solve());
		}
	}
}
