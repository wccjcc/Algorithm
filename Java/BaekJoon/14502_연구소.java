/*
Baekjoon/(14502)연구소[골드4]
시간:508ms / 메모리:99696KB
아이디어 : 벽을 순열로 3개를 각각 세우고, 그 벽을 세웟을때 바이러스 시뮬레이션을 돌려 안안전구역
을 세서 max를 구했습니다.
*/
import java.util.*;
import java.io.*;

public class Main {
    static int n, m; //n:세로크기, m: 가로크기기
    static int[][] map; //map 배열
    static List<int[]> empties = new ArrayList<>(); //벽을세울 후보 칸을 담을 리스트 
    static List<int[]> viruses = new ArrayList<>(); //바이러스들의 위치를 담을 리스트 
    //방향벡터터
    static int[] dr = {1,-1,0,0}; 
    static int[] dc = {0,0,1,-1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        
        for(int i = 0; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < m ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) empties.add(new int[] {i,j}); //0이면 empties에 넣기 
                else if (map[i][j] == 2) viruses.add(new int[] {i,j}); //2이면 viruses에 넣기 
            }
        }
        
        int ans = 0; //정답 변수 (0으로 초기화화)
        
        //빈칸 3곳 조합
        int E = empties.size();
        for(int a = 0; a < E; a++){
            for(int b = a+1; b < E; b++){
                for(int c = b+1; c < E;c++){ //칸들이 중복되지않도록 하기 
                    int[][] tmp = copyMap(map); //매 시도마다 맵을 복사하기 
                    
                    //벽 3개 세우기
                    int[] A = empties.get(a);
                    int[] B = empties.get(b);
                    int[] C = empties.get(c);
                    tmp[A[0]][A[1]] = 1;
                    tmp[B[0]][B[1]] = 1;
                    tmp[C[0]][C[1]] = 1;
                    
                    //바이러스 퍼뜨리기(매번 새 큐)
                    ArrayDeque<int[]> q = new ArrayDeque<>();
                    for(int[] v : viruses) q.add(new int[]{v[0],v[1]});
                    
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        int r2 = cur[0], c2 = cur[1];
                        //네방향으로 퍼지기 
                        for(int d = 0; d < 4; d++){
                            int nr = r2 + dr[d];
                            int nc = c2 + dc[d];
                            //맵안에있고, 0이라서 바이러스가 퍼질수 있는 곳일때 퍼뜨리기 
                            if(0<=nr && nr<n && 0<=nc && nc<m && tmp[nr][nc] == 0){
                                tmp[nr][nc] = 2;
                                q.add(new int[] {nr,nc});
                            }
                        }
                    }
                    
                    //안전 영역 계산 
                    int safe = 0;
                    for (int i = 0 ; i < n ; i++){
                        for(int j = 0 ; j < m; j++){
                            if(tmp[i][j] == 0) safe++;
                        }
                    }
                    if (safe > ans) ans = safe; //max 안전구역 갱신 
                }
            }
        }
        System.out.println(ans);
    }
    //맵 복사 메서드
    static int[][] copyMap(int[][] src){
        int[][] dst = new int[n][m];
        for(int i = 0 ; i < n ; i++){
            System.arraycopy(src[i],0,dst[i],0,m);
        }
        return dst;
    }
}
