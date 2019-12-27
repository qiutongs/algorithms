/*
You have n heroes, each with a power of atk1 and you need to use these heroes to fight with nmonsters. , the monsters have a power of atk2. In a battle, you can arrange each hero to fight with a monster separately. If the hero have more power than the monster, the monster will be killed. How many monsters can you kill at most?
*/

public class Solution {
    /**
     * @param atk1: the power of heros
     * @param atk2: the power of monsters
     * @return: how many monsters can you kill at most?
     */
    public int getAns(int[] atk1, int[] atk2) {
        Arrays.sort(atk1);
        Arrays.sort(atk2);
        
        int ret = 0;
        int p1 = 0, p2 = 0;
        while(p1 < atk1.length && p2 < atk2.length) {
            if (atk1[p1] <= atk2[p2]) {
                p1++;
            } else {
                p1++;
                p2++;
                ret++;
            }
        }
        return ret;
    }
}