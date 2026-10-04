package streams.curiousCoder;

import streams.curiousCoder.model.Employee;
import streams.curiousCoder.util.EmployeeUtil;

import java.util.*;
import java.util.stream.Collectors;

public class StreamQuestions {

    public static void main(String[] args) {

        List<Integer> nums = List.of(5, 3, 1 , 3 ,2 ,5 ,4);
        List<Integer> result1 = nums.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(result1);

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4 ,5));
        List<Integer> result2 = list.stream()
                .filter(x -> x%2 != 0)
                .map(x-> x*x)
                .collect(Collectors.toList());
        System.out.println(result2);

        List<Integer> result3 = list.stream()
                .skip(1)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(result3);

        //find the 2nd highest element in the list
        Optional<Integer> result4 = list.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
        System.out.println("2nd highest element in a list : " +result4.get());

        //divide the numbers into even and odd numbers
        Map<Boolean, List<Integer>> oddEvenPartition = list.stream()
                .collect(Collectors.partitioningBy(x -> x%2 == 0 ));
        System.out.println(oddEvenPartition);

        List<String> stringList = new ArrayList<>(Arrays.asList("Java", "SpringBoot", "API"));
        Optional<String> max = stringList.stream().max(Comparator.comparing(x -> x.length()));
        if(max.isPresent()){
            System.out.println("Max length :" +max.get());
        }

        //find the first employee whose salary is greater than 50000
        Optional<Employee> firstEmployee = EmployeeUtil.getEmployeesList()
                .stream()
                .filter(emp -> emp.getSalary() > 50000)
                .findFirst();

        firstEmployee.ifPresent(emp ->
                System.out.println("First employee having salary greater than 50000 : " + emp)
        );

        //top 2 highest salary employees
        List<Employee> result5 = EmployeeUtil.getEmployeesList()
                .stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(result5);

        //sort the employees by salary, then by name
        List<Employee> result6 = EmployeeUtil.getEmployeesList()
                .stream()
                .sorted(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName))
                .collect(Collectors.toList());
        System.out.println(result6);

        //find the frequency of each element in a list
        List<Integer> list1 = Arrays.asList(3,2,3,4,4,1,2,1,1,1,5,6,5);
        Map<Integer, Long> result7 = list1.stream()
                .collect(Collectors.groupingBy(x->x, Collectors.counting()));
        System.out.println("Frequency of each element: \n" +result7);

        //count how many employees are in each department
        Map<String, Long> collect = EmployeeUtil.getEmployeesList()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println("Employees in each department : \n" +collect);

        //find the average salaries of employees in each department
        Map<String, Double> result8 = EmployeeUtil.getEmployeesList()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Average Salaries of employees in each department : \n" +result8);

        //highest paid employee in each department
        Map<String, Optional<Employee>> result9 = EmployeeUtil.getEmployeesList()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
        System.out.println("Highest paid employee in each department :\n" +result9);

        List<Integer> listOne = Arrays.asList(1,2,3,4);
        List<Integer> listTwo = Arrays.asList(3,4,5,6);
        //find common elements
        List<Integer> result10 = listTwo.stream()
                .filter(x -> listOne.contains(x))
                .collect(Collectors.toList());
        System.out.println("Common Elements: \n" +result10);

        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1,2,3,4),
                Arrays.asList(3,4,5,6),
                Arrays.asList(7,8,1,2),
                Arrays.asList(9,10,5,6),
                Arrays.asList(11,12,7,8)
        );

        List<Integer> result11 = listOfLists.stream()
                .flatMap(innerList -> innerList.stream())
                .distinct().collect(Collectors.toList());
        System.out.println(result11);

        //find employees whose salary is greater than 50000 and collect only their names.
        EmployeeUtil.getEmployeesList()
                .stream()
                .filter(emp -> emp.getSalary() > 50000)
                .map(Employee::getName)
                        .forEach(System.out::println);

        // Sort employees by salary in descending order
        List<Employee> sorted = EmployeeUtil.getEmployeesList()
                .stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .collect(Collectors.toList());
        System.out.println("Sort employees by salary in descending order: \n" +sorted);

        //Sort employees by department ascending and salary descending
        List<Employee> result12 = EmployeeUtil.getEmployeesList()
                .stream()
                .sorted(Comparator.comparing(Employee::getDepartment).
                        thenComparing(Comparator.comparing(Employee::getSalary).reversed()))
                .collect(Collectors.toList());
        System.out.println("Sort employees by department ascending and salary descending : \n" +result12);

        //Find the highest salary employee.
        Optional<Employee> highestSalary = EmployeeUtil.getEmployeesList()
                .stream()
                .max(Comparator.comparing(Employee::getSalary));
        System.out.println("Highest Paid employee : \n" +highestSalary);

        //Find the second highest distinct salary.
        Optional<Integer> secondHighest = EmployeeUtil.getEmployeesList()
                .stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
        if(secondHighest.isPresent()){
            System.out.println(secondHighest.get());
        }

        //Group employees by department
        Map<String, List<Employee>> empByDpt  = EmployeeUtil.getEmployeesList()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("Group employees by department: \n" +empByDpt);

        //Count employees in each department
        Map<String, Long> countEmpByDept = EmployeeUtil.getEmployeesList()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println("Count employees in each department : \n" +countEmpByDept);

        //Find average salary department-wise
        Map<String, Double> avgSalaryByDept = EmployeeUtil.getEmployeesList()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(" Find average salary department-wise \n" + avgSalaryByDept);

        //Find employee names department-wise
        Map<String, List<String>> deptWiseEmpNames = EmployeeUtil.getEmployeesList()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println("Find employee names department-wise \n" +deptWiseEmpNames);

        //Find highest salary employee in each department
        Map<String, Optional<Employee>> highSalEmpByDept = EmployeeUtil.getEmployeesList()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
        System.out.println("highest salary employee in each department : " +highSalEmpByDept);

        //Find frequency of each character
        String str = "programming";
        Map<Character, Long> freqMap = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        x->x,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
        System.out.println("Frequency of each character \n" +freqMap);

        //Find first non-repeated character
        Map<Character, Long> map = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        x ->x,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
        Optional<Character> firstNonRepeated = map.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst();
        if(firstNonRepeated.isPresent()){
            System.out.println("First Non-Repeating character : " +firstNonRepeated.get());
        }

        //Find first repeated character
        Map<Character, Long> map1 = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        x->x,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
        Optional<Character> firstRepeatingCharacter = map1.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst();
        if(firstRepeatingCharacter.isPresent()){
            System.out.println("First Repeating character : " +firstRepeatingCharacter.get());
        }

        //Find all duplicate characters
        Map<Character, Long> map2 = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        x->x,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
        List<Character> duplicates = map2
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Duplicates :"  +duplicates);


        //Find all unique characters
        Map<Character, Long> map3 = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        x->x,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
        List<Character> uniqueChars = map3
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Unique Characters :"  +uniqueChars);

    }
}
