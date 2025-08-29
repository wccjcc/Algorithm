/*
제한 : 3초/256MB(힙,정적메모리)/1MB(스택메모리)
아이디어 : 반복문(while)로 구현하였고, 각 블록마다 dir의 각 인덱스 방향으로 부딪혔을때 방향이 어느것으로 바뀌는지 지정해두고,
웜홀은 배열2개를 이용해 인덱스에 맞는 좌표를 담아두었습니다.
*/
import java.util.*;
import java.io.*;
public class Main
{
    
    // 방향 R,D,L,U로
    static final int[][] dir = { {0,1},{1,0},{0,-1},{-1,0} }; // R,D,L,U
    
    // 블록, 벽에 부딪힌 후 반사 방향향
    static final int[][] blocks = {
        {2,3,0,1}, // 벽
        {2,0,3,1}, // 1
        {2,3,1,0}, // 2
        {1,3,0,2}, // 3
        {3,2,0,1}, // 4
        {2,3,0,1}  // 5
    };
    static int[][] board; //핀볼 보드
    
    static int[][] wormHole1; //웜홀1
    static int[][] wormHole2; //웜홀 2
   //게임하는 메서드드
    static int game(int s_i, int s_j,int dirIdx,int n){
        int score = 0; //초기 스코어
        //첫 좌표에서 주어진 초기 방향으로 움직이기기
        int ni = s_i + dir[dirIdx][0]; 
        int nj = s_j + dir[dirIdx][1];
        //게임 루프 시작
        while (true){
            //전판에서 움직여서 온 위치가 벽일때때
            if(ni < 0 || ni >= n || nj < 0 || nj >= n){
                score++; //스코어+1
                dirIdx = blocks[0][dirIdx]; //방향전환
                //반사되어 도착한 새 좌표 (벽이면 안되니까까)
                ni += dir[dirIdx][0];
                nj += dir[dirIdx][1];
            }
            //도착한 좌표 혹은 새 좌표가 시작좌표라면 게임 끝내기기
            if (ni == s_i && nj == s_j) return score;
            //ni,nj 좌표의 보드 값
            int curr = board[ni][nj];
            //블랙홀을 만났다면 게임 끝내기기
            if (curr == -1) return score;
            //블록을 만났다면(1~5)
            if (curr >= 1 && curr <= 5){
                score++; //스코어+1
                dirIdx = blocks[curr][dirIdx]; //방향 전환
                //전환 후 새 좌표로로
                ni += dir[dirIdx][0];
                nj += dir[dirIdx][1];
            } else if (curr >= 6 && curr <= 10){ //웜홀을 만났다면(6~10)
                int idx = curr-6; //웜홀인덱싱을위해 6씩 빼주기기
                if (wormHole2[idx][0] == ni && wormHole2[idx][1] == nj){ //웜홀2에있던 좌표라면
                //연결된 웜홀 1의 좌표 가져오기
                    ni = wormHole1[idx][0] + dir[dirIdx][0];
                    nj = wormHole1[idx][1] + dir[dirIdx][1];
                } else{ //아니라면
                //웜홀 2의 좌표 가져오기기
                    ni = wormHole2[idx][0] + dir[dirIdx][0];
                    nj = wormHole2[idx][1] + dir[dirIdx][1];
                }
                    
            } else{ //빈 공간이라면
                //다음 좌표로 가기
                ni += dir[dirIdx][0];
                nj += dir[dirIdx][1];
            }
        }
    }
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); //정답 출력할 StringBuilder선언언
		int t = Integer.parseInt(br.readLine().trim());
		//테스트케이스 시작
		for(int tc = 1; tc <= t ; tc++){
		    int n = Integer.parseInt(br.readLine().trim());
		    board = new int[n][n];
		    //웜홀 배열 생성성
		    wormHole1 = new int[5][2];
		    wormHole2 = new int[5][2];
		    boolean[] exist = new boolean[5]; //웜홀이 존재하는지 확인할 배열열
		    //보드 정보 입력받기기
		    for(int i = 0 ; i < n ; i++){
		        StringTokenizer st = new StringTokenizer(br.readLine().trim());
		        for(int j = 0 ; j < n ; j++){
		            board[i][j] = Integer.parseInt(st.nextToken());
		            int v = board[i][j];
		            if(6<=v && v<=10){ //보드내용이 웜홀이라면
		                if (!exist[v - 6]) { //웜홀이 존재하지않으면 1에다가 넣고 존재한다고 표시
                            wormHole1[v - 6][0] = i;
                            wormHole1[v - 6][1] = j;
                            exist[v - 6] = true;
                        } else { //웜홀 이미 존재하면 웜홀2에다가 넣기기
                            wormHole2[v - 6][0] = i;
                            wormHole2[v - 6][1] = j;
                        }
            		 }
		        }
		    }
		    int max = 0; //정답(max점수수)
		    //빈공간에서 게임 시작(모든 방향으로로)
		    for(int i = 0 ; i < n ; i++){
		        for(int j = 0 ; j < n ; j++){
		            if (board[i][j] == 0){
		                for(int k = 0 ; k < 4; k++){
		                    int result = game(i,j,k,n);
		                    max = Math.max(max,result);
		                }
		            }
		        }
		    }
		    //정답 StringBuilder에 더하기기
		    sb.append("#").append(tc).append(" ").append(max).append("\n");
		    
		}
		System.out.println(sb.toString()); //정답 출력력
	}
}

/*
헤맸던 부분
- StringBuilder를 선언하면 조금 더 빠르다
- 그냥 배열을 이용하면 시간복잡도가 O(1)이 되기 때문에 웜홀 때 배열을 이용하자
- 빈 공간일 때의 부분을 처리해주지 않아서 계속 runtime error가 일어났었다
*/
