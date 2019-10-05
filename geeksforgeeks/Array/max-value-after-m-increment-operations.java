/*
Brute force: just follow what the question asks, increment each number in operation
and find max at last.

Time complexity: O(n + m * MAX_RANGE)
Space complexity: O(n)

Improvement: no need to increment count for each numbers in the interval, just update boundary.
Track the "diff" array of all numbers increment count for left boundary and decrement count for right boundary.

Finally, find the max pre-fix sum which is equivalent to find the max in original count array.

Time complexity: O(n + m)
Space complexity: O(n)

Ref: https://www.geeksforgeeks.org/maximum-value-array-m-range-increment-operations/
*/

int findMax(int n,
            int a[], int b[],
            int k[])
{
    int result = 0;
    int[] diffArray = new int[n + 1];

    Arrays.fill(diffArray, 0);

    for (int i = 0; i < a.length, i++)
    {
        diffArray[a[i]] += k[i];
        diffArray[b[i] + 1] -= k[i];
    }

    int prefixSum = 0;
    for (int i = 0; i < n; i++)
    {
        prefixSum += diffArray[i];
        result = Math.max(result, prefixSum);
    }

    return result;
}