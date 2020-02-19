// Saddleback Search
// Time: O(M + N)
// Space: O(1)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int m = matrix.length, n = matrix[0].length;
        
        int i = 0, j = n - 1;
        
        while(i < m && j >= 0) {
            if (target < matrix[i][j]) {
                j--;
            } else if (target > matrix[i][j]) {
                i++;
            } else {
                return true;
            }
        }
        
        return false;
    }
}

// Middle point as divide and conquer
// Time: T(N*N) = 3 * T(1/4*N*N) + O(1) -> T(N*N) = O((N*N)^log4,3) -> T(N) = O(N^1.59)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        return helper(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, target);
    }
    
    private boolean helper(int[][] matrix, int x1, int y1, int x2, int y2, int target) {
        if (x1 > x2 || y1 > y2) {
            return false;
        }
        
        if (x1 == x2 && y1 == y2) {
            return matrix[x1][y1] == target;
        }
        
        int midX = (x1 + x2) / 2;
        int midY = (y1 + y2) / 2;
        
        if (target < matrix[midX][midY]) {
            return helper(matrix, x1, y1, midX - 1, midY - 1, target)
                || helper(matrix, x1, midY, midX - 1, y2, target)
                || helper(matrix, midX, y1, x2, midY - 1, target);
        } else if (target > matrix[midX][midY]) {
            return helper(matrix, midX + 1, midY + 1, x2, y2, target)
                || helper(matrix, x1, midY + 1, midX, y2, target)
                || helper(matrix, midX + 1, y1, x2, midY, target);
        } else {
            return true;
        }
    }
}