package com.practise.java.collection;

import java.util.*;

public class SetCollection {

    private static void hashSetExample() {
        /*
         * HashSet is a collection that does not allow duplicate elements.
         * Internally Set creates the HashMap instance to store the elements as a Hashmap key and default the hash table value as a dummy object.
         * It is backed by a hash map (actually a HashMap instance)
         * It makes no guarantees for the insertion order of the elements.
         */

        Set<Integer> hashSet1 = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        hashSet1.add(6);
        hashSet1.add(3); // Duplicate element will not be added
        hashSet1.remove(2);

        System.out.println("HashSet elements:");
        hashSet1.forEach(System.out::println);

        Set<Integer> hashSet2 = new HashSet<>(Set.of(1, 2, 3, 6, 7));
        // Changes the original set which is called the method
        hashSet1.addAll(hashSet2); // Union of two sets. Output: 1, 2, 3, 4, 5, 6, 7
        hashSet1.retainAll(hashSet2); // Intersection of two sets. Output: 1, 2, 3, 6, 7
        hashSet1.add(100);
        hashSet1.removeAll(hashSet2); // Difference of two sets. Output: 100
    }

    private static void linkedHashSetExample() {
        /*
         * LinkedHashSet is a HashSet that maintains a doubly linked list across all elements.
         * Preserves the insertion order.
         * How does it Preserve the insertion order?
         * - By maintaining a doubly linked list across all elements.
         *
         */

        Set<Integer> linkedHashSet = new LinkedHashSet<>(Set.of(1, 2, 3, 4, 5));
        linkedHashSet.add(6);
        linkedHashSet.add(3); // Duplicate element will not be added
        linkedHashSet.remove(2);

        System.out.println("LinkedHashSet elements:");
        linkedHashSet.forEach(System.out::println);
    }

    private static void treeSetExample() {
        /*
         * TreeSet is a NavigableSet implementation based on a TreeMap.
         * The elements are sorted using their natural ordering, or by a Comparator provided at set creation time, depending on which constructor is used.
         */

        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(8);
        treeSet.add(6);
        treeSet.add(3); // Duplicate element will not be added
        treeSet.add(2); // sorted order: 2, 3, 6, 8
        treeSet.remove(6); // if the element is not present, it will return false

        System.out.println("TreeSet elements (sorted):");
        treeSet.forEach(System.out::println);
    }


    private static void sortedSetExample() {
        /*
         * SortedSet is a Set that maintains its elements in ascending order.
         * It is an interface that extends Set and provides additional methods for range view, endpoints, and comparator access.
         */

        SortedSet<Integer> sortedSet = new TreeSet<>(Set.of(5, 1, 3, 2, 4));
        sortedSet.add(6);
        sortedSet.add(3); // Duplicate element will not be added
        sortedSet.remove(2);

        SortedSet<Integer> headSet = sortedSet.headSet(3); // Returns a view of the portion of this set whose elements are strictly less than 3
        headSet.forEach(System.out::println); // 1, 2

        sortedSet.tailSet(3).clear(); // Removes all elements greater than or equal to 3 in the sorted set

        System.out.println("SortedSet elements (sorted):");
        sortedSet.forEach(System.out::println);

        System.out.println("First element: " + sortedSet.first());
        System.out.println("Last element: " + sortedSet.last());
        System.out.println("Subset (1 to 4): " + sortedSet.subSet(1, 4));
    }

    private static void navigableSetExample() {
        /*
         * NavigableSet is a SortedSet with navigation methods reporting the closest matches for given search targets.
         * It is an interface that extends SortedSet and provides additional methods for navigation.
         */

        NavigableSet<Integer> navigableSet = new TreeSet<>(Set.of(5, 1, 3, 2, 4));
        navigableSet.add(6);
        navigableSet.add(3); // Duplicate element will not be added
        navigableSet.remove(2);

        System.out.println("NavigableSet elements (sorted):");
        navigableSet.forEach(System.out::println);

        System.out.println("Lower than 4: " + navigableSet.lower(4)); // Returns the greatest element less than 4 which is 3
        System.out.println("Higher than 4: " + navigableSet.higher(4)); // Returns the least element greater than 4 which is 5
        System.out.println("Floor of 4: " + navigableSet.floor(4)); // Returns the greatest element less than or equal to 4 which is 4
        System.out.println("Ceiling of 4: " + navigableSet.ceiling(4)); // Returns the least element greater than or equal to 4 which is 4

    }


    public static void main(String[] args) {
        hashSetExample();
        linkedHashSetExample();
        treeSetExample();
        sortedSetExample();
        navigableSetExample();
    }
}
