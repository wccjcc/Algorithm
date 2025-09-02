/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
import java.io.*;
public class Main
{
    static int n,m;
    static char[][] map;
    static boolean[][] visited;
    static int[] demon, suyeon, god;
    static int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
    
    static int bfs(){
        
        //queue 설정
        Deque<int[]> dq = new ArrayDeque<>();
        Deque<int[]> dqD = new ArrayDeque<>();
        
        //초기 설정
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(map[i][j] == '*') dqD.add(new int[]{i,j});
            }
        }
        visited[suyeon[0]][suyeon[1]] = true;
        dq.add(new int[]{suyeon[0],suyeon[1],0});
        
        //4방탐색하면서 큐에 넣기
        while(!dq.isEmpty()){
            //악마
            int dS = dqD.size();
            for(int k = 0 ; k < dS; k++){
                int[] tempD = dqD.poll();
                int dr = tempD[0];
                int dc = tempD[1];
                
                for(int i = 0 ; i < 4; i++){
                    int ndr = dr + direction[i][0];
                    int ndc = dc + direction[i][1];
                    if(0<= ndr && ndr < n && 0<= ndc && ndc < m && map[ndr][ndc] == '.'){
                        map[ndr][ndc] = '*';
                        dqD.add(new int[] {ndr,ndc});
                    }
                }
            }
            
            
            //수연이
            int sS = dq.size();
            for(int k = 0 ; k < sS; k++){
                int[] temp = dq.poll();
                int r = temp[0];
                int c = temp[1];
                
                if (r == god[0] && c == god[1]) return temp[2];
                
                for(int i = 0 ; i < 4; i++){
                    int nr = r + direction[i][0];
                    int nc = c + direction[i][1];
                    if(0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc] && (map[nr][nc] == '.'||map[nr][nc] == 'D')){
                        visited[nr][nc] = true;
                        dq.add(new int[] {nr,nc,temp[2]+1});
                    }
                }
            }
            
            
            
            
        }
        
        
        return -1;
    }
    
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t ; tc++){
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    n = Integer.parseInt(st.nextToken());
		    m = Integer.parseInt(st.nextToken());
		    map = new char[n][m];
		    visited = new boolean[n][m];
		    for(int i = 0 ; i < n ; i++){
		        String line = br.readLine();
		        for(int j = 0 ; j < m ; j++){
		            map[i][j] = line.charAt(j);
		            if(line.charAt(j) == 'S') {
		                suyeon = new int[] {i,j};
		                map[i][j] = '.';
		            }
		            else if (line.charAt(j) == 'D') god = new int[] {i,j};
		            
		        }
		    }
		    int result = bfs();
		    if (result == -1) System.out.println("#"+tc+" GAME OVER");
		    else System.out.println("#"+tc+" "+result);
		}
	}
}
