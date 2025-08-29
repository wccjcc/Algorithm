/*
(1949) 등산로 조성
아이디어 : 처음에 dfs로 풀고, 공사를 하려고 할 때 둘의 차가 k보다 작아야 더 적도록 공사가능 하도록 하였는데,
단순히 dfs 하고 끝이 아니라 사방으로 탐색을 해봐야 어떤 경로가 가장 긴 줄 알 수 있었습니다.
따라서 백트래킹을 통해 원상복구해주고 할수있는 경로를 다 탐색해봐야 maxHeight을 알 수 있었습니다.
*/
import java.util.*;
import java.io.*;
public class Solution_1949_정우주주
{
    static int k; //최대 공사 깊이
    static int n; //맵의 크기
    static boolean[][] visited; //방문 정보
    static int[][] map; //맵
    static int[][] copiedMap; //복사된 맵
    static int[][] direction = {{0,1},{1,0},{-1,0},{0,-1}}; // 방향
    static int maxHeight; //가장 긴 등산로의 길이
    //dfs메서드드
    public static void dfs(int i, int j, int depth, boolean isPo){
        maxHeight = Math.max(maxHeight,depth); //maxHeight이 있다면 갱신
        visited[i][j] = true; //방문처리
        //사방 탐색색
        for(int h = 0; h < 4; h++){
            //한칸 이동하기기
            int ni = i + direction[h][0];
            int nj = j + direction[h][1];
            //맵 안에 있고, 아직 방문하지 않았다면,
            if(0 <= ni && ni < n && 0 <= nj && nj < n && visited[ni][nj] == false){
                //방문할 좌표가 더 낮은 높이라면 그대로 dfs 탐색색
                if(copiedMap[ni][nj] < copiedMap[i][j]){
                    dfs(ni,nj,depth+1,isPo);
                    //더 낮은 높이가 아니고, 공사가능하다면,
                } else if (isPo == true){
                    int diff = copiedMap[ni][nj]-copiedMap[i][j];
                    if(diff < k){ //높이차가 k 미만이라면, 
                        int original = copiedMap[ni][nj]; //원래 높이 저장해두고, 
                        copiedMap[ni][nj] -= (diff+1); //공사진행행
                        dfs(ni,nj,depth+1,false); //dfs 진행행
                        //원상복구
                        copiedMap[ni][nj] = original;
                    }
                }
            }
        }
        visited[i][j] = false; //방문해제해주고 원상복구구
    }
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++){
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    n = Integer.parseInt(st.nextToken());
		    k = Integer.parseInt(st.nextToken());
		    map = new int[n][n];
		    int max = 0; //가장 높은 높이 저장할 변수
		    List<int[]> maxList = new ArrayList<>(); //가장 높은 높이의 좌표들을 저장할 리스트트
		    //지도에 입력받으면서 최대 높이 기록해놓기
		    for(int i = 0 ; i < n ; i++){
		        st = new StringTokenizer(br.readLine());
		        for(int j = 0 ; j < n ; j++){
		            int temp = Integer.parseInt(st.nextToken());
		            map[i][j] = temp;
		            if (temp > max){
		                max = temp;
		                maxList = new ArrayList<>();
		                maxList.add(new int[] {i,j});
		            } else if (temp == max){
		                maxList.add(new int[] {i,j});
		            } 
		        }
		    }
		    maxHeight = 0;
		    //maxList에서 가장높은곳들 가져와서 dfs해보기
		    for(int[] highest : maxList){
		        copiedMap = new int[n][n];
		        for(int i = 0 ; i < n ; i++){
		            for(int j = 0 ; j < n ; j++){
		                copiedMap[i][j] = map[i][j];
		            }
		        }
		        visited = new boolean[n][n];
		        dfs(highest[0],highest[1],1,true);
		    }
		    System.out.println("#"+tc+" "+maxHeight);
		}
	}
}
