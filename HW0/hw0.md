[This is hw0's website](https://sp18.datastructur.es/materials/hw/hw0/hw0)
# Exercise 1a: Drawing a Triangle
Your goal is to create a program that prints the following figure. Your code should use loops (i.e. shouldn’t just be five print statements, that’s no fun).
```
*
**
***
****
*****
```
You can either write the program from scratch, or you can copy and paste lines of code from this link. You may find `System.out.print` to be a useful alternative to `System.out.println`. The difference is that `System.out.print` does not include an automatic newline.

If you go the copy and paste route, note that lines may be used once, multiple times, or not at all.

Run your code and verify that it works correctly by comparing it by eye to the program above. In next week’s lab and hw, we’ll discuss more sophisticated ways of verifying program correctness.
## Creative Exercise 1b: DrawTriangle

# Exercise 2
get the max value in a array

# Exercise 3
Rewrite code with for loop

# Exercise 4
This is a particularly challenging exercise, but strongly recommended.

Write a function `windowPosSum(int[] a, int n)` that replaces each element a[i] with the sum of a[i] through a[i + n], but only if a[i] is positive valued. If there are not enough values because we reach the end of the array, we sum only as many values as we have.

For example, suppose we call `windowPosSum` with the array `a = {1, 2, -3, 4, 5, 4}`, and `n = 3`. In this case, we’d:

- Replace a[0] with a[0] + a[1] + a[2] + a[3].
- Replace a[1] with a[1] + a[2] + a[3] + a[4].
- Not do anything to a[2] because it’s - negative.
- Replace a[3] with a[3] + a[4] + a[5].
- Replace a[4] with a[4] + a[5].
Not do anything with a[5] because there are no values after a[5].
Thus, the result after calling `windowPosSum` would be `{4, 8, -3, 13, 9, 4}`.

As another example, if we called `windowPosSum` with the array `a = {1, -1, -1, 10, 5, -1}`, and `n = 2`, we’d get `{-1, -1, -1, 14, 4, -1}`.