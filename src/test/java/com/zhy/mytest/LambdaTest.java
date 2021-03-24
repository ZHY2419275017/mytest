package com.zhy.mytest;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

/**
 * @author: zhy
 * @Date:2021/1/26
 * @Description:
 **/
public class LambdaTest {
  public static void main(String[] args) {
//      List<String> stringList = new ArrayList<>();
//      stringList.add("abc");
//      stringList.add("def");
//      stringList.add("ghi");
//      List<String> upStringList = stringList.stream().map(item -> item.toUpperCase()+"JAVA").collect(Collectors.toList());
//      upStringList.forEach(item->{System.out.println(item);});

//      List<Integer> listint = new ArrayList<Integer>();
//      listint.add(1);
//      listint.add(2);
//      listint.add(3);
//      listint.add(4);
//      listint.add(5);
//      IntSummaryStatistics stat = listint.stream().mapToInt((item)->item).summaryStatistics();
//      System.out.println("max:"+stat.getMax());
//      System.out.println("min:"+stat.getMin());
//      System.out.println("sum:"+stat.getSum());
//      System.out.println("count:"+stat.getCount());
//      System.out.println("average:"+stat.getAverage());


//      Predicate<Integer> itemFilter1 = (item) -> (item > 2);
//      Predicate<Integer> itemFilter2 = (item) -> (item < 5);
//      List<Integer> listint = new ArrayList<Integer>();
//      listint.add(1);
//      listint.add(2);
//      listint.add(3);
//      listint.add(4);
//      listint.add(5);
//      //List<Integer> collect = listint.stream().filter((item) ->(item > 1)).limit(2).collect(Collectors.toList());
//      List<Integer> collect = listint.stream().filter(itemFilter1).filter(itemFilter2).collect(Collectors.toList());
//      collect.forEach(item->{System.out.println(item);});


//      List<Integer> listint = new ArrayList<Integer>();
//      listint.add(1);
//      listint.add(2);
//      listint.add(3);
//      listint.add(4);
//      listint.add(5);
//
//      Integer target = listint.stream().max((item1, item2) -> (item1-item2)).get();
//      System.out.println(target);


//      List<Integer> listint = new ArrayList<Integer>();
//      listint.add(1);
//      listint.add(2);
//      listint.add(3);
//      listint.add(4);
//      listint.add(5);
//
//      Set<Integer> collect = listint.stream().collect(Collectors.toSet());
//      TreeSet<Integer> collect1 = listint.stream().collect(toCollection(TreeSet::new));
//      System.out.println(collect);
//      System.out.println(collect1);

      List<Integer> listint = new ArrayList<Integer>();
      listint.add(1);
      listint.add(2);
      listint.add(3);
      listint.add(4);
      listint.add(5);
      Predicate<Integer> itemFilter1 = (item) -> (item > 2);
      Integer result = listint.stream()
              .filter(itemFilter1)
              .map((item) -> (item + 1))
              .max((item1, item2) -> (item1 - item2))
              .get();
      System.out.println(result);
  }
}
