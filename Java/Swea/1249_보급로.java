/*
(1249) 보급로
아이디어 : 다익스트라 + PriorityQueue를 이용하기 
왜냐하면, 가중치가 여러가지기 때문에 bfs를 쓰기 힘들다
*/
import java.util.*;
import java.io.*;
public class Main
{
    static int n = 0, INF = Integer.MAX_VALUE; //n : 지도의 크기, INF : 거리를 무한으로 해놓기 위해
    static int map[][]; //맵
    //방향벡터터
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int t = Integer.parseInt(br.readLine());
	    for(int tc = 1; tc <= t ; tc++){
	        n = Integer.parseInt(br.readLine());
	        map = new int[n][n];
	        for(int i = 0 ; i < n ; i++){
	            String line = br.readLine();
	            for(int j = 0 ; j < n ; j++){
	                map[i][j] = line.charAt(j) - '0';
	            }
	        }
	        System.out.println("#"+tc+" "+dijkstra(0,0));
	    }
	}
	//다익스트라 (최소거리(=시간) 구하는 메서드드)
	static int dijkstra(int startR,int startC){
	    
	    int[][] minTime = new int[n][n]; //각 좌표에 대해 갈수있는 가장 작은 시간을 담을 배열
	    boolean[][] visited = new boolean[n][n]; //각 좌표에 대해 방문 정보를 담을 배열
	    
	    //step 0: 모든 정점에 대해 inf 초기화
	    for(int r = 0 ; r < n ; r++){
	        for(int c = 0; c < n ; c++){
	            minTime[r][c] = INF;
	        }
	    }
	    // int[] : r,c,mintime 등이 들어감
	    PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
	        @Override
	        public int compare(int[] o1, int[] o2){
	            return Integer.compare(o1[2],o2[2]); //가중치 기준으로 정렬하기기
	        }
	    });
	    
	    minTime[startR][startC] = 0;  //출발지는 minTime을 0으로 
	    pq.offer(new int[] {startR,startC,minTime[startR][startC]}); //pq에 넣기기
	    
	    while(!pq.isEmpty()){
	        
	        int[] stopOver = pq.poll(); //현재 보고있는 경유지 
	        int r = stopOver[0];
	        int c = stopOver[1];
	        int totalTime = stopOver[2]; //totalTime : 현재 가지고있는 거리정보보
	        
	        if(visited[r][c]) continue; // 이미 방문한곳은 건너뛰어
	        visited[r][c] = true; //방문하지 않았다면 방문 처리
	        
	        if(r == n-1 && c == n-1) return totalTime; //도착지에 도착 그럼 끝내고 totalTime 내기기
	        //4방향 탐색
	        for(int d = 0; d < 4; d++){
	            int nr = r + dr[d];
	            int nc = c + dc[d];
	            //조건 (new 좌표)
	            //1. map 밖을 벗어나지 않을 것
	            //2. 방문하지 않은곳일 것
	            //3. 갱신할만한 곳일 것 (새로운곳의 minTime이 가기전 totalTime+가중치보다 커야 갱신가능능)
	            if(nr>=0 && nr<n && nc>=0 && nc<n && !visited[nr][nc] && minTime[nr][nc] > totalTime+map[nr][nc]){
	                minTime[nr][nc] = totalTime+map[nr][nc]; //minTime 갱신신
	                pq.offer(new int[] {nr,nc,minTime[nr][nc]});
	            }
	        }
	        
	    }
	    
	    return -1; //혹시나 도착을 못했을경우우
	}
}
