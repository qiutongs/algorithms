class Solution extends Relation {
	public int findCelebrity(int n) {
		if (n <= 0) {
			return -1;
		}

		int l = 0, r = n - 1;
		while(l < r) {
			if (knows(l, r)) {
				l++;
			} else {
				r--;
			}
		}
        int ret = l;
		for (int i = 0; i < n; i++) {
			if (i != l) {
				if (knows(i, ret) == false || knows(ret, i)) {
					return -1;
				}
			}
		}
		return ret;
	}
}