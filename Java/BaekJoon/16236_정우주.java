import java.util.*;
import java.io.*;

public class Main_16236_정우주주 {
    static int N;
    static int[][] map;
    static int[] di = {-1,0,0,1}; // 위, 왼, 오른, 아래 (우선순위 반영 위해)
    static int[] dj = {0,-1,1,0};
    
    static class Shark {
        int i, j, size, eatCnt;
        Shark(int i, int j, int size){
            this.i = i; this.j = j; this.size = size;
            this.eatCnt = 0;
        }
    }
    
    static Shark shark;

    // BFS로 가장 가까운 먹을 물고기 찾기
    public static int[] bfs(){
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{shark.i, shark.j, 0});
        visited[shark.i][shark.j] = true;
        
        List<int[]> candidates = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int i = cur[0], j = cur[1], dist = cur[2];
            
            // 현재 위치에 먹을 수 있는 물고기가 있으면 후보에 추가
            if(map[i][j] > 0 && map[i][j] < shark.size){
                if(dist <= minDist){
                    minDist = dist;
                    candidates.add(new int[]{i,j,dist});
                }
                continue; // 더 먼 곳은 볼 필요 없음
            }
            
            for(int d=0; d<4; d++){
                int ni = i + di[d];
                int nj = j + dj[d];
                if(0<=ni && ni<N && 0<=nj && nj<N && !visited[ni][nj]){
                    if(map[ni][nj] <= shark.size){ // 지나갈 수 있음
                        visited[ni][nj] = true;
                        q.add(new int[]{ni,nj,dist+1});
                    }
                }
            }
        }
        
        if(candidates.isEmpty()) return null;
        
        // 후보 중 "위쪽 → 왼쪽" 우선 정렬
        candidates.sort((a,b)->{
            if(a[2] != b[2]) return a[2]-b[2]; // 거리 짧은 순
            if(a[0] != b[0]) return a[0]-b[0]; // 위쪽
            return a[1]-b[1]; // 왼쪽
        });
        
        return candidates.get(0); // 가장 우선순위 높은 물고기 반환
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int val = Integer.parseInt(st.nextToken());
                map[i][j] = val;
                if(val == 9){
                    shark = new Shark(i,j,2);
                    map[i][j] = 0; // 시작 위치는 빈칸 처리
                }
            }
        }
        
        int time = 0;
        
        while(true){
            int[] target = bfs();
            if(target == null) break; // 더 이상 먹을 물고기 없음
            
            int ni = target[0], nj = target[1], dist = target[2];
            
            // 상어 이동 & 먹기
            shark.i = ni; shark.j = nj;
            time += dist;
            shark.eatCnt++;
            map[ni][nj] = 0; // 먹은 자리 비우기
            
            // 크기 증가 조건
            if(shark.eatCnt == shark.size){
                shark.size++;
                shark.eatCnt = 0;
            }
        }
        
        System.out.println(time);
    }
}
