/*
(4485) 녹색 옷을 입은 애가 젤다지? [골드4]
시간: 224ms / 메모리 : 19528KB
아이디어 : dijkstra + PriorityQueue로 풀었고, 중요했던점은 첫번째에서도 루피를 잃을 수도 있다는 것이였습니다.
*/
import java.util.*;
import java.io.*;
public class Main
{
    static int n, INF = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
    static boolean[][] visited;
    static int[][] minLost;
    //다익스트라 메서드
    static int dijkstra(int sR, int sC){
        
        
        //PriorityQueue 설정
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1,int[] o2){
                return Integer.compare(o1[2],o2[2]);
            }
        });
        //출발지 처리
        minLost[sR][sC] = map[sR][sC];
        pq.offer(new int[] {sR,sC,minLost[sR][sC]});
        
        //큐 돌면서 처리
        while(!pq.isEmpty()){
            int[] temp = pq.poll(); //현재 보고있는 경유지
            int r = temp[0];
            int c = temp[1];
            int totalLost = temp[2];
            
            if(visited[r][c]) continue;
            visited[r][c] = true;
            
            //도착지에 도착했다면 끝내고 totalLost 반환
            if(r == n-1 && c == n-1) return totalLost;
            
            //4방 탐색하면서
            for(int i = 0 ; i < 4; i++){
                int nr = r + direction[i][0];
                int nc = c + direction[i][1];
                if(0<=nr && nr<n && 0<=nc && nc<n && !visited[nr][nc] && minLost[nr][nc] > totalLost+map[nr][nc]){
                    minLost[nr][nc] = totalLost + map[nr][nc];
                    pq.offer(new int[] {nr,nc,minLost[nr][nc]});
                }
            }
            
        }
        return -1;
    }
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int num = 1;
		while (true){
		    n = Integer.parseInt(br.readLine());
		    if(n == 0) break;
		    //minLost 배열 초기화하기
		    minLost = new int[n][n];
		    for(int i = 0; i < n ; i++){
		        for(int j = 0 ; j < n ; j++){
		            minLost[i][j] = INF;
		        }
		    }
		    //map 입력받기
		    map = new int[n][n];
		    for(int i = 0 ; i < n ; i++){
		        StringTokenizer st = new StringTokenizer(br.readLine());
		        for(int j = 0 ; j < n ; j++){
		            map[i][j] = Integer.parseInt(st.nextToken());
		        }
		    }
		    visited = new boolean[n][n];
		    System.out.println("Problem "+num+": "+dijkstra(0,0));
		    num++;
		}
	}
}
