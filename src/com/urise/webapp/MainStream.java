package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0, 2, 3, 9, 5, 2, 1, 7, 6));
//        Stream<Integer> stream1 = list.stream();
//        Stream<Integer> stream2 = list.stream();
//        stream.forEach(x-> System.out.println(x));
//        stream1.distinct().forEach(System.out::println);
/*        int value = stream2.reduce(0, (a, b) -> {
            int sum = Integer.sum(a, b);
            return sum;
        });
        System.out.println(value);*/
//        Stream.of(1, 5, 6, 2, 9, 8, 0, 8, 1, 2, 8, 9).sorted().distinct().filter(x -> x > 0).forEach(x -> System.out.println(x));
        /*List<Integer> ex = stream2.filter(x -> x < 6 & x > 1).map(x -> x * 10).toList();
        System.out.println(ex);*/
    /*    IntStream intStream = Stream.of(5,6,9,7,1,0,7).mapToInt(x -> x);
        System.out.println(intStream.summaryStatistics().getAverage());*/
        //        int odd = Stream.of(1, 5, 6, 2, 9, 8, 0, 8, 1, 2, 8, 9).filter(p -> p % 2 != 0).reduce((a, b)-> a+b);
//        System.out.println(odd);



        /*        реализовать метод через стрим int minValue(int[] values).
        Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число,
        составленное из этих уникальных цифр. Не использовать преобразование в строку и обратно.
        Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89*/

        int[] array = {1, 2, 3, 8, 9, 7, 0, 2, 2, 9, 9};
        System.out.println(minValue(array));

        /*        реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная -
        удалить все нечетные, если четная - удалить все четные. Сложность алгоритма должна быть O(N).
        Optional - решение в один стрим.*/

        List<Integer> list = new ArrayList<>(Arrays.asList(0, 2, 3, 9, 5, 2, 1, 7, 6));
        System.out.println(oddOrEvenVariant1(list));
        System.out.println(oddOrEvenVariant2(list));
    }

    static int minValue(int[] values) {
        return Arrays.stream(values)
                .sorted()
                .distinct()
                .filter(x -> x > 0)
                .reduce(0, (total, i) -> (total * 10) + i);
    }

    static List<Integer> oddOrEvenVariant1(List<Integer> integers) {
        List<Integer> listOdd = new ArrayList<>(integers.size());
        List<Integer> listEven = new ArrayList<>(integers.size());
        Integer sum = integers.stream()
                .peek(i -> {
                    if (i % 2 == 0) {
                        listEven.add(i);
                    } else {
                        listOdd.add(i);
                    }
                })
                .reduce(0, Integer::sum);

        return sum % 2 == 0 ?  listOdd : listEven;
    }


    static List<Integer> oddOrEvenVariant2(List<Integer> integers) {
        int sum = integers.stream().reduce(0, Integer::sum);
        boolean isSumEven = sum % 2 == 0;
        return integers.stream()
                .filter(i-> isSumEven == (i % 2 != 0))
                .collect(Collectors.toList());
    }
}
