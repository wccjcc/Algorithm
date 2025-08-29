/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
import java.io.*;
public class Solution_5656_정우주
{
    static int[][] map;
    static int N,W,H;
    static int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
    static int answer;
    //벽돌 폭발하는 메서드드
    static void explode(int[][] map,int i, int j){
        if ((i<0||i>=H||j<0||j>=W)||map[i][j]==0) return;
        int temp = map[i][j];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i,j,map[i][j]});
        map[i][j] = 0;// 방문 표시
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], power = cur[2];
            if(power <= 1) continue;
            for(int d = 0 ; d < 4; d++){
                int dr = direction[d][0], dc = direction[d][1];
                //1..power-1칸까지
                for(int step = 1; step < power; step++){
                    int nr = r + dr * step;
                    int nc = c + dc * step;
                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) break;
                    if (map[nr][nc] == 0) continue;
                    q.add(new int[]{nr,nc,map[nr][nc]});
                    map[nr][nc] = 0;
                }
            }
        }
    }
    //벽돌 내리는 메서드드
    // static void down(){
    //     for(int c = 0; c < W; c++){
    //         Deque<Integer> bricks = new ArrayDeque<>();
    //         //남아있는 블록 알아두기
    //         for(int r = H-1 ; r >= 0; r--){
    //             if (map[r][c] != 0){
    //                 bricks.add(map[r][c]);
    //                 map[r][c] = 0;
    //             } 
    //         }
    //         for(int r = H-1; r >= 0; r--){
    //             if(bricks.size() == 0) break;
    //             map[r][c] = bricks.pollFirst();
    //         }
    //     }
    // }
    static void down(int[][] board){
        for (int c = 0; c < W; c++){
            int write = H - 1;
            for (int r = H - 1; r >= 0; r--){
                if (board[r][c] != 0){
                    int val = board[r][c];
                    board[r][c] = 0;
                    board[write--][c] = val;
                }
            }
        }
    }
    //남은 벽돌 수 세는 메서드
    static int cntRemain(int [][] map){
        int cnt = 0;
        for(int[] r : map){
		      for(int i : r){
		          if (i != 0) cnt++;
		      }
		 }
		 return cnt; 
    }
    //한 열의 최상단 벽돌 행 인덱스 (없으면 -1반환)
    static int topBrick(int[][] map,int col){
        for(int r = 0 ; r < H ; r++){
            if(map[r][col] != 0) return r;
        }
        return -1;
    }
    //한번 쏘는 턴의 메메서드
    static void shoot(int[][] map, int sr, int sc){
        explode(map,sr,sc);
        down(map);
    }
    //순열만들기
    static void per(int depth, int[][] map){
        int left = cntRemain(map);
        if(left == 0){ //이미 다 깨진 경우
            answer = 0;
            return;
        }
        if(depth == N){
            answer = Math.min(answer,left);
            return;
        }
        if (answer == 0) return; //이미 최선보다 못이기는경우(이미 0개남은경우) 중단
        
        for(int col = 0 ; col < W; col++){
            int topR = topBrick(map,col);
            if(topR == -1){
                per(depth+1,map);
                continue;
            }
            int[][] next = copyOf(map);
            shoot(next,topR,col);
            per(depth+1, next);
            if(answer == 0 )return;
        }
    }
    
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T ; tc++){
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    N = Integer.parseInt(st.nextToken());
		    W = Integer.parseInt(st.nextToken());
		    H = Integer.parseInt(st.nextToken());
		    map = new int[H][W];
		    for(int i = 0 ; i < H; i++){
		        st = new StringTokenizer(br.readLine());
		        for(int j = 0 ; j < W; j++){
		            map[i][j] = Integer.parseInt(st.nextToken());
		        }
		    }
		    answer = Integer.MAX_VALUE;
		    per(0,map);
		    System.out.println("#"+tc+" "+answer);
		}
	}
	static int[][] copyOf(int[][] arr){
	    int[][] copied = new int[H][W];
	    for(int i = 0 ; i < H; i++){
	        for(int j = 0 ; j < W; j++){
	            copied[i][j] = arr[i][j];
	        }
	    }
	    return copied;
	}
}