public int[] twoSum7(int[] nums, int target) {
    Entry[] entries = new Entry[nums.length];
    for (int i = 0; i < nums.length; i++) {
    	entries[i] = new Entry(nums[i], i);
    }

    int[] ret = new int[2];

    Arrays.sort(entries, (e1, e2) -> e1.val - e2.val);

    int l = 0;
    for (int r = 1; r < nums.length; r++) {
    	while(l < r && nums[r] - nums[l] > Math.abs(target)) {
    		l++;
    	}
    	if (nums[r] - nums[l] == Math.abs(target)) {
    		ret[0] = l;
    		ret[1] = r;
    		break;
    	}
    }

    return ret;
}

private class Entry {
	int val;
	int index;
	Entry(int val, int index) {
		this.val = val;
		this.index = index;
	}
}