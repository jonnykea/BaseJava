package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainStream {
    /*    реализовать метод через стрим int minValue(int[] values).
        Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число,
        составленное из этих уникальных цифр. Не использовать преобразование в строку и обратно.
        Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89

        реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная -
        удалить все нечетные, если четная - удалить все четные. Сложность алгоритма должна быть O(N).
        Optional - решение в один стрим.*/
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0, 2, 3, 9, 5, 2, 1, 7, 6));
        Stream<Integer> stream1 = list.stream();
        Stream<Integer> stream2 = list.stream();
//        stream.forEach(x-> System.out.println(x));
//        stream1.distinct().forEach(System.out::println);

/*        int value = stream2.reduce(0, (a, b) -> {
            int sum = Integer.sum(a, b);
            return sum;
        });
        System.out.println(value);*/

//        Stream.of(1, 5, 6, 2, 9, 8, 0, 8, 1, 2, 8, 9).sorted().distinct().filter(x -> x > 0).forEach(x -> System.out.println(x));
        int value = Stream.of(0, 3, 6, 2).sorted().distinct().filter(x -> x > 0).reduce(0, (totalDigit, digit) -> totalDigit * 10 + digit);
        System.out.println(value);

        /*List<Integer> ex = stream2.filter(x -> x < 6 & x > 1).map(x -> x * 10).toList();
        System.out.println(ex);*/

    /*    IntStream intStream = Stream.of(5,6,9,7,1,0,7).mapToInt(x -> x);
        System.out.println(intStream.summaryStatistics().getAverage());*/
    }
}
