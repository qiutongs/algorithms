// DP
// Time: O(N*N*K)
// Space: O(N*N*K)
class Solution {
    private static int[] deltaX = {2, 2, 1, 1, -1, -1, -2, -2};
    private static int[] deltaY = {1, -1, 2, -2, 2, -2, 1, -1};
    
    public double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = new double[N][N][K + 1];
        dp[r][c][0] = 1.0;
        
        for (int k = 1; k <= K; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int l = 0; l < 8; l++) {
                        int x = i + deltaX[l], y = j + deltaY[l];
                        if (inbound(N, x, y)) {
                            dp[i][j][k] += dp[x][y][k - 1] / 8.0;
                        }
                    }
                }
            }
        }
    
        double ret = 0.0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ret += dp[i][j][K];
            }
        }
        return ret;
    }
    
    private boolean inbound(int N, int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}

// DFS + memo
class Solution {
    private static int[] deltaX = {2, 2, 1, 1, -1, -1, -2, -2};
    private static int[] deltaY = {1, -1, 2, -2, 2, -2, 1, -1};
    private Double[][][] memo;
    
    public double knightProbability(int N, int K, int r, int c) {
        memo = new Double[N][N][K];
        return dfs(N, r, c, 0, K);
    }
    
    private double dfs(int N, int x, int y, int move, int K) {
        if (move == K) {
            return 1.0;
        }
        if (memo[x][y][move] != null) {
            return memo[x][y][move];
        }
        double ret = 0.0;
        for (int j = 0; j < 8; j++) {
            int x1 = x + deltaX[j];
            int y1 = y + deltaY[j];
            if (inbound(N, x1, y1)) {
                ret += dfs(N, x1, y1, move + 1, K) / 8.0;
            }
        }
        memo[x][y][move] = ret;
        return ret;
    }
    
    private boolean inbound(int N, int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}

// Raw DFS without visited check
// Time Limit Exceeded: 8, 30, 6, 4
class Solution {
    private static int[] deltaX = {2, 2, 1, 1, -1, -1, -2, -2};
    private static int[] deltaY = {1, -1, 2, -2, 2, -2, 1, -1};
    
    public double knightProbability(int N, int K, int r, int c) {
        return dfs(N, r, c, 0, K);
    }
    
    private double dfs(int N, int x, int y, int move, int K) {
        if (move == K) {
            return 1.0;
        }
        double ret = 0.0;
        for (int j = 0; j < 8; j++) {
            int x1 = x + deltaX[j];
            int y1 = y + deltaY[j];
            if (inbound(N, x1, y1)) {
                ret += dfs(N, x1, y1, move + 1, K) / 8.0;
            }
        }
        return ret;
    }
    
    private boolean inbound(int N, int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}

// Raw BFS without visited check
// Time Limit Exceeded: 8, 30, 6, 4
// Not able to add memo
class Solution {
    private static int[] deltaX = {2, 2, 1, 1, -1, -1, -2, -2};
    private static int[] deltaY = {1, -1, 2, -2, 2, -2, 1, -1};
    
    public double knightProbability(int N, int K, int r, int c) {
        double ret = 1.0;
        int inboundSum = 0, outboundSum = 0;
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        
        int move = 0;
        while(move < K && q.isEmpty() == false) {
            move++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] node = q.poll();
                for (int j = 0; j < 8; j++) {
                    int[] next = new int[]{node[0] + deltaX[j], node[1] + deltaY[j]};
                    if (inbound(N, next)) {
                        q.offer(next);
                        inboundSum++;
                    } else {
                        outboundSum++;
                    }
                }
            }
            ret *= (double)inboundSum / (double)(inboundSum + outboundSum);
            inboundSum = 0;
            outboundSum = 0;
        }
        return ret;
    }
    
    private boolean inbound(int N, int[] node) {
        return node[0] >= 0 && node[0] < N && node[1] >= 0 && node[1] < N;
    }
}