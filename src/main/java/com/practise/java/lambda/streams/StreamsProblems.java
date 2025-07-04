package com.practise.java.lambda.streams;

import com.practise.commons.Employee;
import com.practise.commons.Utils;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
 * https://notebook.zohopublic.in/public/notes/74tdod05928d081b5484fbfd5937d4129fa64
 * */


class StreamsProblems {

    static double findAvgUsingStreams(List<Integer> integerList) {
        OptionalDouble avg = integerList.stream().mapToInt(Integer::intValue).average();
        return (avg.isPresent()) ? avg.getAsDouble() : Double.NaN;
    }

    static double findAvgUsingReduce(List<Integer> integerList) {
        return integerList.stream()
                .mapToDouble(Integer::doubleValue)
                .reduce(0, Double::sum) / integerList.size();
    }

    static void sortDescendingOrder(List<Integer> integerList) {
        integerList.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    static void oddEvenList(List<Integer> integerList) {
        Map<Boolean, List<Integer>> partitionList = integerList.stream().collect(Collectors.partitioningBy(e -> e % 2 == 0));

        List<Integer> oddList = partitionList.get(false);
        List<Integer> evenList = partitionList.get(true);

        Utils.printList(oddList);
        Utils.printList(evenList);
    }


    static void squareOfNumbers(List<Integer> integerList) {
        List<Integer> squares = integerList.stream().map(e -> e * e).toList();
        Utils.printList(squares);
    }

    static void findDuplicates(List<Integer> integerList) {
        List<Integer> duplicates = integerList.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting())).entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
        Utils.printList(duplicates);

    }

    static void findDuplicatesUsingSetFilter(List<Integer> integerList) {
        Set<Integer> set = new HashSet<>();
        List<Integer> duplicates = integerList.stream().filter(e -> !set.add(e)).toList();
        Utils.printList(duplicates);
    }

    static void findUniqueElements(List<Integer> integerList) {
        List<Integer> distinct = integerList.stream().distinct().toList();
        Utils.printList(distinct);
    }

    static void findSmallestElement(List<Integer> integerList) {
        int smallestElement = integerList.stream().sorted().toList().getFirst();
        //int smallestElement = integerList.stream().sorted().skip(integerList.size() - 1).findFirst().orElse(-1);
        //integerList.stream().min(Comparator.comparingInt(Integer::intValue)).ifPresent(System.out::println);
        System.out.println(smallestElement);
    }


    static void findWordFrequency() {
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry",
                "banana", "apple");
        Map<String, Long> wordFreq = words.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        Utils.printMap(wordFreq);
    }

    static void findLongestString() {
        List<String> words = Arrays.asList("apple", "bananaaaaa", "apple", "cherry",
                "banana", "apple");

        String longestString = words.stream().max(Comparator.comparingInt(String::length)).orElse(null);
        System.out.println(longestString);
    }

    static void combineListUsingStream(List<List<Integer>> integerLists) {

        List<String> sentences = Arrays.asList(
                "Hello world",
                "Java streams are awesome",
                "flatMap is powerful"
        );

        // Get all words from all sentences
        List<String> words = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .collect(Collectors.toList());
        Utils.printList(words);


        integerLists.stream().flatMap(List::stream)
                .collect(Collectors.toList())
                .forEach(System.out::print);

        Integer sum = integerLists.stream().flatMapToInt(integerList -> integerList.stream().mapToInt(Integer::intValue)).sum();
        System.out.println("Sum of all integers: " + sum);

    }

    static void convertListToMap() {

        Employee employee1 = new Employee("John", 30, "Engineering", 60000);
        Employee employee2 = new Employee("Jane", 25, "Marketing", 50000);
        Employee employee3 = new Employee("Jane", 25, "Marketing", 50000);
        List<Employee> employees = Arrays.asList(employee1, employee2, employee3);

        Map<String, Double> empMap = employees.stream().collect(Collectors.toMap(
                Employee::getName,
                Employee::getSalary,
                (existing, replacement) -> existing // In case of duplicate keys, keep the existing value
        ));

        empMap.forEach((name, salary) -> System.out.println(name + ": " + salary));

        employees.sort(Comparator.comparing(Employee::getName));
        employees.stream().forEach(e -> System.out.println(e.getName()));

    }

    static void flattenStringList() {
        List<List<String>> stringLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("cherry", "date"),
                Arrays.asList("elderberry", "fig")
        );

        List<String> flattenedList = stringLists.stream()
                .flatMap(List::stream)
                .toList();

        Utils.printList(flattenedList);
    }

    /**
     * Unlike filter, takeWhile is a new method introduced in Java 9 that allows you to take elements from a stream while a given condition is true.
     * It stops processing as soon as the condition becomes false.
     */

    static void takeWhileExample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = numbers.stream()
                .takeWhile(n -> n % 2 == 0)
                .toList();
        Utils.printList(evenNumbers);
    }


    /**
     * Custom collector example that collects strings with a prefix.
     * This is a simple example of how to create a custom collector using the Collector.of() method.
     */
    static void customCollectorExample() {
        Collector<String, ?, String> joinWithPrefix = Collector.of(
                StringBuilder::new,
                (sb, s) -> sb.append("prefix-").append(s),
                StringBuilder::append,
                StringBuilder::toString
        );
        List<String> items = List.of("a", "b");
        String result = items.stream().collect(joinWithPrefix);
        System.out.println(result); // Output: prefix-a prefix-b
    }


    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 6, 7, 8);
//        oddEvenList(numbers);
//        System.out.println(findAvgUsingReduce(numbers));
//        System.out.println(findAvgUsingStreams(numbers));
//        combineListUsingStream(Arrays.asList(numbers, numbers));
//        squareOfNumbers(numbers);
//        findDuplicates(numbers);
//        findDuplicatesUsingSetFilter(numbers);
//        findUniqueElements(numbers);
//        findSmallestElement(numbers);
//        findWordFrequency();
//        findLongestString();

        //findSmallestElement(numbers);

        //convertListToMap();
        flattenStringList();
    }
}
