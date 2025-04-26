## Memory Structure of Hashmap

- A Hashmap is typically implemented as an array of linked lists or as an array of balanced binary search trees (from
  Java 8 onwards, when the threshold is crossed).
- The array provides the buckets to store key-value pairs.
- Each bucket can hold multiple entries (either in a linked list or a tree) to handle hash collisions.
- The index of the array is calculated using the hash code of the key.

## Hash Collision during hashmap.put()

- Hash collision occurs when two different keys have the same hash code.
- In a Hashmap, when a collision occurs during the put() operation, the new key-value pair is added to the existing
  bucket.
- If the bucket uses a linked list, the new entry is appended to the list.
- If the bucket uses a tree (after reaching the treefy threshold), the tree structure is used to handle collisions.

## Significance of equals() and hashCode() in Hashmap

- The equals() and hashCode() methods are crucial for proper functioning of a Hashmap.
- The hashCode() method is used to calculate the hash code of a key, which determines the index of the bucket where the
  key-value pair is stored.
- The equals() method is used to compare keys for equality when there is a hash collision.
- It helps identify if two keys are the same or different, enabling proper retrieval of values from the Hashmap.

## Searching time-complexity of HashMap is O(1)

- The searching time-complexity of a Hashmap is O(1) on average, which means it has constant-time performance for most
  operations.
- This is possible because Hashmap uses a hashing technique to calculate the index where a key-value pair is stored.
- With a properly distributed hash function, the time complexity to retrieve a value is independent of the number of
  elements stored in the Hashmap.

## Hashmap treefy threshold and its introduction in Java 8

- The “treefy threshold” is the number of entries a bucket can hold before it is converted from a linked list to a
  balanced binary search tree.
- In Java 8, an optimization was introduced to convert a bucket to a tree structure when the number of entries in that
  bucket exceeds a certain threshold (usually 8).
- This was done to improve the worst-case performance of the Hashmap, as a long linked list can lead to degraded search
  performance.
- By using a tree, the time complexity for search operations in a bucket is reduced from O(n) to O(log n), resulting in
  more predictable and improved performance.

## Java8 changes to HashMap

- Before Java8, singly linked lists was the data structure used for storing nodes in hash map buckets.
- However, a significant change was introduced in Java 8, where the implementation shifted to self-balancing binary
  search trees (BST) once a specific threshold was crossed (static final int TREEIFY_THRESHOLD = 8;).
- The rationale behind this change was to address performance issues related to lookup time in hash map buckets.
- Using singly-linked lists for storage could lead to a worst-case time complexity of O(n) for lookups.
- The typical binary search trees could result in uneven distribution of nodes, where one subtree becomes significantly
  larger than the other subtree leading to unbalanced structure resulting in lookup to O(n) complexity.
- Red-black trees and AVL trees, known as self-balancing trees, were developed to mitigate these issues by maintaining
  balance in the tree structure.
- With the adoption of self-balancing binary search trees in Java 8, even scenarios where all items hash to the same
  bucket would ensure a worst-case lookup time of O(log n).

## Collision Handling Techniques

- Collisions occur when two keys hash to the same value.
- Several methods exist to handle collisions:
    - Chaining: Each slot in the hash table points to a linked list of entries that hash to the same index. When a
      collision occurs, the new entry is added to the list at that index.
    - Open Addressing: When a collision occurs, the algorithm searches for the next available slot. There are several
      strategies for open addressing:
        - Linear Probing: If a collision occurs at index i, check i+1, i+2, ... until an empty slot is found.
        - Quadratic Probing: Similar to linear probing, but the interval between probes is increased quadratically. If a
          collision occurs at index i, check i+1^2, i+2^2, ....
        - Double Hashing: Uses a second hash function to determine the interval between probes. If a collision occurs at
          index i, the next slot checked is determined by a second hash function.

## Reference

- https://ankitwasankar.medium.com/5-tricky-interview-questions-on-hashmap-java-8-a2c7185fd795
- https://medium.com/javarevisited/frequently-asked-java-concept-programs-part3-hashmap-internal-working-563bb13bf8d0
- https://www.youtube.com/watch?v=-oafFAPgLao
- https://www.youtube.com/watch?v=sHpjxXX81Gw&t=2411s