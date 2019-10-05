/*
Brute force: tracking counts of all numbers from 0 to max. Iterate on every interval from Li
to Ri, increment count for each numbers in the interval.

Time complexity: O(max + n * MAX_RANGE)
Space complexity: O(max)

Improvement: no need to increment count for each numbers in the interval, just update boundary.
Track the "diff" array of all numbers so that when iterating on every interval from Li to Ri,
increment count for left boundary and decrement count for right boundary.

Finally, find the max pre-fix sum which is equivalent to find the max in original count array.

Time complexity: O(max + n)
Space complexity: O(max)

Ref: https://www.geeksforgeeks.org/maximum-occurred-integer-n-ranges/
*/

/*
   0 <= Li, Ri < max
*/
int maximumOccuredElement(int[] L, int[] R, int max)
{
    int result = 0;
    int[] countsDiff = new int[max];
    Arrays.fill(countsDiff, 0);

    for (int i = 0; i < L.length; i++)
    {
        countsDiff[L[i]] += 1;
        countsDiff[R[i] + 1] -= 1;
    }

    int prefixSum = 0;
    int maxSum = 0;
    for (int i = 0; i < max; i++)
    {
        prefixSum += countsDiff[i];

        if (prefixSum > maxSum)
        {
            maxSum = prefixSum;
            result = i;
        }
    }

    return result;
}