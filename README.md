# Treap
Implementation of [Treap](https://www.geeksforgeeks.org/treap-a-randomized-binary-search-tree/) (randomized binary search tree) with max-heap order in Java.
 
Treap() constructor creates an empty treap and initializes a priorityGenerator using [new Random()](http://docs.oracle.com/javase/8/docs/api/java/util/Random.html). Treap(long seed) constructor creates an empty treap and initializes priorityGenerator using new Random(seed). 

Implemented treap methods:
- add(key, priority) - add a key to the treap
- delete(key) - delete a key from the treap
- find(key) - find a key if it is in the treap
- String toString() - return a string representation of the treap

Within the treap class is an inner Node<E> class, which defines the nodes to be implemented within the treap.
