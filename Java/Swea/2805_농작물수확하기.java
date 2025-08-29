/*
난이도 : D3
아이디어 : 모래시계처럼 중심인덱스에서 얼마나 많이 떨어져있냐에 따라서 각 행마다 탐색할 넓이를 구하고, 
시작할 인덱스를 구한 뒤 행마다 반복문을 돌리면서 수확하였습니다.
*/
import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); //테스트케이스
		//테스트케이스마다 반복복
		for (int tc = 1; tc <= T; tc++){
		    int N = Integer.parseInt(br.readLine());
		    int [][] farm = new int[N][N]; //농장 배열 선언
		    //농장 입력받기 (공백없이이)
		    for(int i = 0 ; i < N; i++){
		        String line = br.readLine();
		        for(int j = 0 ; j < N ; j++){
		            farm[i][j] = line.charAt(j) - '0';
		        }
		    }
		    int sum = 0; //수확한 양(정답)
		    int mid = N/2; //중심 인덱스
		    //배열을 돌며며
		    for(int k = 0 ; k < N; k++){ 
		        int width = 2 * ( mid - Math.abs(mid-k) ) + 1; //각 행에 해당하는 탐색할 넓이
		        int start = (N-width)/2; //탐색을 시작할 인덱스 (각 행에서서)
		        for(int h = start; h < start+width; h++ ){ //넓이만큼 이동하면서서
		            sum += farm[k][h]; //수확하기기
		        }
		    }
		    System.out.println("#"+tc+" "+sum); //정답출력력
		}
	}
}

/*
## 중요했던점

- 모래시계에서와 같이 최대까지 늘어났다 줄어드는 것은 중심에서 얼마나 떨어져있냐를 abs로 판단하고, 이를 이용해 수열을 만드는 것이다.
- 공백없이 받는건 charAt() - ‘0’를 통해 받자

## 개선점

- 굳이 넓이를 구할 필요 없이, 중심에서 얼마나 떨어져있냐를 가지고 시작인덱스와 끝인덱스를 구하기만 하면 됐었다!
    - 넓이를 굳이 구할 필요 x
*/
