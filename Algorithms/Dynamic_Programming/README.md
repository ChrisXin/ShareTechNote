
### 2 key ingredients

1. Optimal substrcture 

2. Overlapping subproblems


### Memoization
To avoid calculating values multiple times, store intermediate calculations in a Hash table/Array.

We save (memoize) computed answers for possible later reuse, rather than re-computing the answer multiple times

### Top-down Approach

From the very top, we break the problem into sub-problems

(In solving optimization problems, the top-down approach may require repeatedly obtaining optimal solutions for the same sub-problem.)

### Bottom-up Approach

Solve smaller problems and use these to build a solution for the problem

### Compare with Divide-and-Conquer

Dynamic programming is distinct from divide-and-conquer, as the divide-and-conquer approach works well if the sub-problems are essentially unique (Storing intermediate results would only waste memory)

If sub-problems re-occur, the problem is said to have overlapping sub-problems
