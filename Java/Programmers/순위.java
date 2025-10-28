
import java.util.*;
class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        boolean[][] winList = new boolean[n+1][n+1];

        for(int[] r : results){
            winList[r[0]][r[1]] = true;
        }

        for(int k = 1; k <= n ; k++){
            for(int i = 1 ; i <= n ; i++){
                for(int j = 1 ; j <= n ; j++){
                    if(winList[i][k] && winList[k][j]){
                        winList[i][j] = true;
                    }
                }
            }
        }

        for(int i = 1; i <= n ; i++){
            int win = 0;
            int lose = 0;
            for(int j = 1; j <= n ; j++){
                if(winList[i][j]) win++;
                if(winList[j][i]) lose++;
            }
            if(win+lose == n-1) answer++;
        }


        return answer;
    }
}
