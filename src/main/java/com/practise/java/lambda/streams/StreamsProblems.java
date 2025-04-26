package com.practise.java.lambda.streams;

import com.practise.commons.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
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

    static void oddEvenList(List<Integer> integerList) {
        Map<Boolean, List<Integer>> partitionList = integerList.stream().collect(Collectors.partitioningBy(e -> e % 2 == 0));

        List<Integer> oddList = partitionList.get(false);
        List<Integer> evenList = partitionList.get(true);

        Utils.printList(oddList);
        Utils.printList(evenList);
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

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        oddEvenList(numbers);
        System.out.println(findAvgUsingReduce(numbers));
        System.out.println(findAvgUsingStreams(numbers));
        combineListUsingStream(Arrays.asList(numbers, numbers));

    }
}
