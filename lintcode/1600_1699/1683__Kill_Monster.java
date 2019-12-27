/*
There are n monsters and an Altman. Both Altman and Monster have five attribute values. When Altman's five attributes are greater than or equal to the five attributes of a monster, Altman can kill the monster. When Altman kills a monster, the five properties of the monster are added to Altman. How many monsters can Altman kill at most?
Example 1:

Input: n = 2, v = [[1,1,1,1,1],[1,1,1,1,1],[2,2,2,2,2]]
Output: 2
Explanation: Altman kills monster v[1] at first, and his attribute changes to [2,2,2,2,2]. Then Altman can kill monster v[2]
Example 2:

Input: n = 5, v = [[3,9,2,1,5],[0,9,6,5,9],[6,1,8,6,3],[3,7,0,4,4],[9,9,0,6,5],[5,6,5,6,7]]
Output: 0
Explanation: Altman can not kill any monster.
*/

public class Solution {
    /**
     * @param n: 
     * @param v: 
     * @return: nothing
     */
    public int killMonster(int n, int[][] v) {
        int[] altman = v[0];
        //Arrays.sort(v, (v1, v2) -> v1[0]+v1[1]+v1[2]+v1[3]+v1[4]-(v2[0]+v2[1]+v2[2]+v2[3]+v2[4]));
        int ret = 0;
        for (int i = 1; i < v.length; i++) {
            int j = i;
            for (; j < v.length; j++) {
                if (isKillable(altman, v[j])) {
                    ret++;
                    kill(altman, v[j]);
                    swap(v, i, j);
                    break;
                }
            }
            if (j == v.length) {
                break;
            }
        }
        return ret;
    }
    
    private boolean isKillable(int[] altman, int[] monster) {
        for (int i = 0; i < 5; i++) {
            if (altman[i] < monster[i]) {
                return false;
            }
        }
        return true;
    }
    
    private void kill(int[] altman, int[] monster) {
        for (int i = 0; i < 5; i++) {
            altman[i] += monster[i];
        }
    }
    
    private void swap(int[][] v, int i, int j) {
        int[] tmp = v[i];
        v[i] = v[j];
        v[j] = tmp;
    }
}
