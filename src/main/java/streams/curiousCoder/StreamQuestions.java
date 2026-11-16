package streams.curiousCoder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import streams.curiousCoder.model.Employee;
import streams.curiousCoder.util.EmployeeUtil;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class StreamQuestions {

    public static void main(String[] args) {

        List<Integer> nums = List.of(5, 3, 1 , 3 ,2 ,5 ,4);
        System.out.println(distinctDescending(nums));

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4 ,5));
        System.out.println(squaresOfOdd(list));

        System.out.println(skipThenLimit(list, 1, 2));

        secondHighest(list).ifPresent(second ->
                System.out.println("2nd highest element in a list : " +second));

        System.out.println(partitionEvenOdd(list));

        List<String> stringList = new ArrayList<>(Arrays.asList("Java", "SpringBoot", "API"));
        longestString(stringList).ifPresent(max ->
                System.out.println("Max length :" +max));

        List<Employee> employees = EmployeeUtil.getEmployeesList();

        firstWithSalaryAbove(employees, 50000).ifPresent(emp ->
                System.out.println("First employee having salary greater than 50000 : " + emp)
        );

        System.out.println(topBySalary(employees, 2));

        System.out.println(sortedBySalaryThenName(employees));

        List<Integer> list1 = Arrays.asList(3,2,3,4,4,1,2,1,1,1,5,6,5);
        System.out.println("Frequency of each element: \n" +frequencies(list1));

        System.out.println("Employees in each department : \n" +countByDepartment(employees));

        System.out.println("Average Salaries of employees in each department : \n" +averageSalaryByDepartment(employees));

        System.out.println("Highest paid employee in each department :\n" +highestPaidByDepartment(employees));

        List<Integer> listOne = Arrays.asList(1,2,3,4);
        List<Integer> listTwo = Arrays.asList(3,4,5,6);
        System.out.println("Common Elements: \n" +commonElements(listOne, listTwo));

        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1,2,3,4),
                Arrays.asList(3,4,5,6),
                Arrays.asList(7,8,1,2),
                Arrays.asList(9,10,5,6),
                Arrays.asList(11,12,7,8)
        );
        System.out.println(flattenDistinct(listOfLists));

        namesWithSalaryAbove(employees, 50000).forEach(System.out::println);

        System.out.println("Sort employees by salary in descending order: \n" +sortedBySalaryDescending(employees));

        System.out.println("Sort employees by department ascending and salary descending : \n" +sortedByDepartmentThenSalaryDescending(employees));

        System.out.println("Highest Paid employee : \n" +highestPaid(employees));

        secondHighestSalary(employees).ifPresent(System.out::println);

        System.out.println("Group employees by department: \n" +groupByDepartment(employees));

        System.out.println("Count employees in each department : \n" +countByDepartment(employees));

        System.out.println(" Find average salary department-wise \n" + averageSalaryByDepartment(employees));

        System.out.println("Find employee names department-wise \n" +namesByDepartment(employees));

        System.out.println("highest salary employee in each department : " +highestPaidByDepartment(employees));

        String str = "programming";
        System.out.println("Frequency of each character \n" +characterFrequencies(str));

        firstNonRepeatedCharacter(str).ifPresent(ch ->
                System.out.println("First Non-Repeating character : " +ch));

        firstRepeatedCharacter(str).ifPresent(ch ->
                System.out.println("First Repeating character : " +ch));

        System.out.println("Duplicates :"  +duplicateCharacters(str));

        System.out.println("Unique Characters :"  +uniqueCharacters(str));

    }

    // ---- numbers and strings

    //unique numbers, largest first
    static List<Integer> distinctDescending(List<Integer> nums) {
        return nums.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    static List<Integer> squaresOfOdd(List<Integer> nums) {
        return nums.stream()
                .filter(x -> x%2 != 0)
                .map(x-> x*x)
                .collect(Collectors.toList());
    }

    static List<Integer> skipThenLimit(List<Integer> nums, long skip, long limit) {
        return nums.stream()
                .skip(skip)
                .limit(limit)
                .collect(Collectors.toList());
    }

    //find the 2nd highest element in the list; empty when there are fewer than 2 distinct values
    static Optional<Integer> secondHighest(List<Integer> nums) {
        return nums.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
    }

    //divide the numbers into even (true) and odd (false) numbers
    static Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> nums) {
        return nums.stream()
                .collect(Collectors.partitioningBy(x -> x%2 == 0 ));
    }

    //when several strings share the greatest length, the first of them wins
    static Optional<String> longestString(List<String> strings) {
        return strings.stream()
                .reduce((longest, next) -> next.length() > longest.length() ? next : longest);
    }

    //find the frequency of each element in a list
    static Map<Integer, Long> frequencies(List<Integer> nums) {
        return nums.stream()
                .collect(Collectors.groupingBy(x->x, Collectors.counting()));
    }

    //elements of second that also appear in first, in the order (and with the repeats) of second
    static List<Integer> commonElements(List<Integer> first, List<Integer> second) {
        return second.stream()
                .filter(x -> first.contains(x))
                .collect(Collectors.toList());
    }

    static List<Integer> flattenDistinct(List<List<Integer>> listOfLists) {
        return listOfLists.stream()
                .flatMap(innerList -> innerList.stream())
                .distinct().collect(Collectors.toList());
    }

    // ---- employees

    //find the first employee whose salary is greater than the threshold
    static Optional<Employee> firstWithSalaryAbove(List<Employee> employees, int threshold) {
        return employees.stream()
                .filter(emp -> emp.getSalary() > threshold)
                .findFirst();
    }

    //top n highest salary employees
    static List<Employee> topBySalary(List<Employee> employees, int n) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    //sort the employees by salary, then by name
    static List<Employee> sortedBySalaryThenName(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName))
                .collect(Collectors.toList());
    }

    //count how many employees are in each department
    static Map<String, Long> countByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }

    //find the average salaries of employees in each department
    static Map<String, Double> averageSalaryByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
    }

    //highest paid employee in each department
    static Map<String, Optional<Employee>> highestPaidByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
    }

    //find employees whose salary is greater than the threshold and collect only their names.
    static List<String> namesWithSalaryAbove(List<Employee> employees, int threshold) {
        return employees.stream()
                .filter(emp -> emp.getSalary() > threshold)
                .map(Employee::getName)
                .collect(Collectors.toList());
    }

    // Sort employees by salary in descending order; equal salaries keep their input order
    static List<Employee> sortedBySalaryDescending(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .collect(Collectors.toList());
    }

    //Sort employees by department ascending and salary descending
    static List<Employee> sortedByDepartmentThenSalaryDescending(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getDepartment).
                        thenComparing(Comparator.comparing(Employee::getSalary).reversed()))
                .collect(Collectors.toList());
    }

    //Find the highest salary employee.
    static Optional<Employee> highestPaid(List<Employee> employees) {
        return employees.stream()
                .max(Comparator.comparing(Employee::getSalary));
    }

    //Find the second highest distinct salary.
    static Optional<Integer> secondHighestSalary(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
    }

    //Group employees by department
    static Map<String, List<Employee>> groupByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    //Find employee names department-wise
    static Map<String, List<String>> namesByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
    }

    // ---- characters

    //Find frequency of each character, in order of first appearance
    static Map<Character, Long> characterFrequencies(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        x->x,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
    }

    //Find first non-repeated character
    static Optional<Character> firstNonRepeatedCharacter(String str) {
        return characterFrequencies(str).entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst();
    }

    //Find first repeated character
    static Optional<Character> firstRepeatedCharacter(String str) {
        return characterFrequencies(str).entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst();
    }

    //Find all duplicate characters
    static List<Character> duplicateCharacters(String str) {
        return characterFrequencies(str)
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    //Find all unique characters
    static List<Character> uniqueCharacters(String str) {
        return characterFrequencies(str)
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
