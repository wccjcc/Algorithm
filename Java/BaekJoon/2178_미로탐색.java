/*
(2178) 미로 탐색 [실버1]
메모리 : 14496KB, 시간 : 112ms
아이디어 : bfs로 큐에 좌표와함께 현재 거리도 넣어 목적지에 도착하면 바로 끝내는 형식으로 최소 거리를 탐색했습니다.
*/
import java.util.*;
import java.io.*;

public class Main_2178_정우주주 {
	static int[][] map; //미로 배열
	static int n,m; //n : 세로 길이, m : 가로 길이
	static int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}}; //방향 벡터
	static boolean[][] visited; //방문 배열
	//bfs 메서드드
	static int bfs(int i, int j) {
		
		Deque<int[]> dq = new ArrayDeque<>(); 
		dq.add(new int[] {i,j,1}); //첫 좌표 deque에 넣기
		visited[i][j] = true; //방문 처리리
	    int result = 0;
		//큐가 빌동안 계속하기
		while(!dq.isEmpty()) {
			int[] temp = dq.poll(); //큐에서 하나 꺼내기
			if (temp[0] == n-1&& temp[1]== m-1){ //꺼낸 좌표가 목적지면 거리 반환하고 반복문 끝내기
			    result = temp[2];
			    break;
			}
			//4방향 탐색
			for(int d = 0 ; d < 4; d++) {
				int ni = temp[0] + direction[d][0];
				int nj = temp[1] + direction[d][1];
				//좌표가 범위를 벗어나지 않고, 갈수있는장소(1)이며, 아직 방문하지 않았을 때
				if(0<=ni && ni<n && 0<=nj && nj<m && map[ni][nj]==1&&visited[ni][nj]==false) { 
					dq.add(new int[] {ni,nj,temp[2]+1}); //deque에 새 좌표 넣기(거리+1)해서
					visited[ni][nj] = true; //방문처리리
				}
			}
		}
		
		return result;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		//공백없이 받기기
		for(int i = 0 ; i < n; i++) {
			String line = br.readLine();
			for(int j = 0 ; j < m; j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		}
		System.out.println(bfs(0,0));
		
	}
}
