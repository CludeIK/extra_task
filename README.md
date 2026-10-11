# BST and Hash Table Comparison

In this work I am doing comparison of Binary Search Tree (BST) and Hash Table performance.

## Time Complexity Analysis
For Hash Table, insert, search and delete is O(1) in best case and average case. It is very fast because it go directly to index. But worst case is O(n) if many collisions happen and all items go to one place.
For BST, average time and best time is O(log n). But worst case is O(n), this happens when tree is like one long line (unbalanced).

## Test Results
N = 100: BST time = 0,000463 sec | Hash Table time = 0,000885 sec
N = 1000: BST time = 0,000260 sec | Hash Table time = 0,000923 sec
N = 10000: BST time = 0,002004 sec | Hash Table time = 0,003702 sec

## Conclusion
I do test my code with 100, 1000 and 10000 elements. Hash Table is always more faster than BST. When I put 10000 elements, Hash Table take almost 0 time. BST take more time because it does many steps to find element.
Why they have different speed in practice? Hash table use math (hash function) to find place. It is very fast for memory. But BST need to compare elements left and right, and it jumps from memory to memory using pointers. This is harder for computer. So Hash Table is better for speed.