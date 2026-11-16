package streams.curiousCoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import streams.curiousCoder.model.Employee;
import streams.curiousCoder.util.EmployeeUtil;
import testsupport.Console;

class StreamQuestionsTest {

    /** Results are compared by name, which keeps the expected values short. */
    private static List<String> names(List<Employee> employees) {
        return employees.stream().map(Employee::getName).collect(Collectors.toList());
    }

    private static Optional<String> name(Optional<Employee> employee) {
        return employee.map(Employee::getName);
    }

    private static List<Employee> staff() {
        return EmployeeUtil.getEmployeesList();
    }

    @Nested
    class NumbersAndStrings {

        @Test
        void distinctDescending() {
            assertEquals(List.of(5, 4, 3, 2, 1), StreamQuestions.distinctDescending(List.of(5, 3, 1, 3, 2, 5, 4)));
            assertEquals(List.of(), StreamQuestions.distinctDescending(List.of()));
            assertEquals(List.of(7), StreamQuestions.distinctDescending(List.of(7, 7)));
            assertEquals(List.of(2, 0, -1), StreamQuestions.distinctDescending(List.of(-1, 2, 0, -1)));
        }

        @Test
        void squaresOfOdd() {
            assertEquals(List.of(1, 9, 25), StreamQuestions.squaresOfOdd(List.of(1, 2, 3, 4, 5)));
            assertEquals(List.of(), StreamQuestions.squaresOfOdd(List.of()));
            assertEquals(List.of(), StreamQuestions.squaresOfOdd(List.of(2, 4, 0)));
        }

        @Test
        void squaresOfOddTreatsNegativeOddNumbersAsOdd() {
            assertEquals(List.of(1, 9), StreamQuestions.squaresOfOdd(List.of(-1, -2, -3)));
        }

        @Test
        void skipThenLimit() {
            List<Integer> nums = List.of(1, 2, 3, 4, 5);

            assertEquals(List.of(2, 3), StreamQuestions.skipThenLimit(nums, 1, 2));
            assertEquals(List.of(1, 2, 3, 4, 5), StreamQuestions.skipThenLimit(nums, 0, 99));
            assertEquals(List.of(), StreamQuestions.skipThenLimit(nums, 5, 2));
            assertEquals(List.of(), StreamQuestions.skipThenLimit(nums, 99, 2));
            assertEquals(List.of(), StreamQuestions.skipThenLimit(nums, 1, 0));
            assertEquals(List.of(5), StreamQuestions.skipThenLimit(nums, 4, 3));
            assertEquals(List.of(), StreamQuestions.skipThenLimit(List.of(), 1, 2));
        }

        @Test
        void skipThenLimitRejectsNegativeArguments() {
            assertThrows(IllegalArgumentException.class, () -> StreamQuestions.skipThenLimit(List.of(1), -1, 1));
            assertThrows(IllegalArgumentException.class, () -> StreamQuestions.skipThenLimit(List.of(1), 1, -1));
        }

        @Test
        void secondHighest() {
            assertEquals(Optional.of(4), StreamQuestions.secondHighest(List.of(1, 2, 3, 4, 5)));
            assertEquals(Optional.of(4), StreamQuestions.secondHighest(List.of(5, 5, 4, 1)));
            assertEquals(Optional.of(-2), StreamQuestions.secondHighest(List.of(-2, -1, -3)));
        }

        @Test
        void secondHighestIsEmptyWithFewerThanTwoDistinctValues() {
            assertEquals(Optional.empty(), StreamQuestions.secondHighest(List.of()));
            assertEquals(Optional.empty(), StreamQuestions.secondHighest(List.of(9)));
            assertEquals(Optional.empty(), StreamQuestions.secondHighest(List.of(9, 9, 9)));
        }

        @Test
        void partitionEvenOdd() {
            assertEquals(Map.of(false, List.of(1, 3, 5), true, List.of(2, 4)),
                    StreamQuestions.partitionEvenOdd(List.of(1, 2, 3, 4, 5)));
            assertEquals(Map.of(false, List.of(-1), true, List.of(0, -2)),
                    StreamQuestions.partitionEvenOdd(List.of(0, -1, -2)));
        }

        @Test
        void partitionEvenOddAlwaysHasBothKeys() {
            assertEquals(Map.of(false, List.of(), true, List.of()), StreamQuestions.partitionEvenOdd(List.of()));
            assertEquals(Map.of(false, List.of(), true, List.of(2)), StreamQuestions.partitionEvenOdd(List.of(2)));
        }

        @Test
        void longestString() {
            assertEquals(Optional.of("SpringBoot"), StreamQuestions.longestString(List.of("Java", "SpringBoot", "API")));
            assertEquals(Optional.of("only"), StreamQuestions.longestString(List.of("only")));
            assertEquals(Optional.of(""), StreamQuestions.longestString(List.of("")));
            assertEquals(Optional.empty(), StreamQuestions.longestString(List.of()));
        }

        @Test
        void longestStringPicksTheFirstOfEquallyLongStrings() {
            assertEquals(Optional.of("abc"), StreamQuestions.longestString(List.of("a", "abc", "xyz", "ab")));
        }

        @Test
        void frequencies() {
            assertEquals(Map.of(1, 4L, 2, 2L, 3, 2L, 4, 2L, 5, 2L, 6, 1L),
                    StreamQuestions.frequencies(List.of(3, 2, 3, 4, 4, 1, 2, 1, 1, 1, 5, 6, 5)));
            assertEquals(Map.of(), StreamQuestions.frequencies(List.of()));
            assertEquals(Map.of(-1, 2L), StreamQuestions.frequencies(List.of(-1, -1)));
        }

        @Test
        void commonElements() {
            assertEquals(List.of(3, 4), StreamQuestions.commonElements(List.of(1, 2, 3, 4), List.of(3, 4, 5, 6)));
            assertEquals(List.of(), StreamQuestions.commonElements(List.of(1, 2), List.of(3, 4)));
            assertEquals(List.of(), StreamQuestions.commonElements(List.of(), List.of(1)));
            assertEquals(List.of(), StreamQuestions.commonElements(List.of(1), List.of()));
        }

        @Test
        void commonElementsFollowTheOrderAndRepeatsOfTheSecondList() {
            assertEquals(List.of(4, 3, 3), StreamQuestions.commonElements(List.of(3, 4), List.of(4, 9, 3, 3)));
        }

        @Test
        void flattenDistinct() {
            assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
                    StreamQuestions.flattenDistinct(List.of(
                            List.of(1, 2, 3, 4), List.of(3, 4, 5, 6), List.of(7, 8, 1, 2),
                            List.of(9, 10, 5, 6), List.of(11, 12, 7, 8))));
            assertEquals(List.of(), StreamQuestions.flattenDistinct(List.of()));
            assertEquals(List.of(), StreamQuestions.flattenDistinct(List.of(List.of(), List.of())));
            assertEquals(List.of(2, 1), StreamQuestions.flattenDistinct(List.of(List.of(2, 2), List.of(), List.of(1, 2))));
        }
    }

    @Nested
    class Employees {

        @Test
        void firstWithSalaryAbove() {
            assertEquals(Optional.of("Ankit"), name(StreamQuestions.firstWithSalaryAbove(staff(), 50000)));
            assertEquals(Optional.of("Abhishek"), name(StreamQuestions.firstWithSalaryAbove(staff(), 0)));
        }

        @Test
        void firstWithSalaryAboveExcludesTheThresholdItself() {
            assertEquals(Optional.of("Sachit"), name(StreamQuestions.firstWithSalaryAbove(staff(), 70000)));
            assertEquals(Optional.empty(), StreamQuestions.firstWithSalaryAbove(staff(), 80000));
            assertEquals(Optional.empty(), StreamQuestions.firstWithSalaryAbove(List.of(), 0));
        }

        @Test
        void topBySalary() {
            assertEquals(List.of("Sachit", "Ankit"), names(StreamQuestions.topBySalary(staff(), 2)));
            assertEquals(List.of("Sachit"), names(StreamQuestions.topBySalary(staff(), 1)));
            assertEquals(List.of(), names(StreamQuestions.topBySalary(staff(), 0)));
            assertEquals(9, StreamQuestions.topBySalary(staff(), 100).size());
            assertEquals(List.of(), StreamQuestions.topBySalary(List.of(), 2));
        }

        @Test
        void topBySalaryRejectsNegativeN() {
            assertThrows(IllegalArgumentException.class, () -> StreamQuestions.topBySalary(staff(), -1));
        }

        @Test
        void sortedBySalaryThenName() {
            assertEquals(List.of("Rahul", "Tina", "Abhishek", "Push", "Sumit", "Naman", "Esha", "Ankit", "Sachit"),
                    names(StreamQuestions.sortedBySalaryThenName(staff())));
        }

        @Test
        void sortedBySalaryThenNameUsesNameOnlyToBreakSalaryTies() {
            List<Employee> employees = List.of(
                    new Employee("Zed", "X", 10), new Employee("Amy", "X", 20), new Employee("Bob", "X", 10));

            assertEquals(List.of("Bob", "Zed", "Amy"), names(StreamQuestions.sortedBySalaryThenName(employees)));
        }

        @Test
        void countByDepartment() {
            assertEquals(Map.of("IT", 3L, "HR", 2L, "Finance", 2L, "Marketing", 2L),
                    StreamQuestions.countByDepartment(staff()));
            assertEquals(Map.of(), StreamQuestions.countByDepartment(List.of()));
        }

        @Test
        void averageSalaryByDepartment() {
            Map<String, Double> averages = StreamQuestions.averageSalaryByDepartment(staff());

            assertEquals(java.util.Set.of("IT", "HR", "Finance", "Marketing"), averages.keySet());
            assertEquals(200000 / 3.0, averages.get("IT"), 1e-9);
            assertEquals(42500.0, averages.get("HR"), 1e-9);
            assertEquals(57500.0, averages.get("Finance"), 1e-9);
            assertEquals(51000.0, averages.get("Marketing"), 1e-9);
            assertEquals(Map.of(), StreamQuestions.averageSalaryByDepartment(List.of()));
        }

        @Test
        void highestPaidByDepartment() {
            Map<String, Optional<Employee>> highest = StreamQuestions.highestPaidByDepartment(staff());

            assertEquals(Map.of("IT", "Sachit", "HR", "Tina", "Finance", "Esha", "Marketing", "Sumit"),
                    highest.entrySet().stream()
                            .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().orElseThrow().getName())));
            assertEquals(Map.of(), StreamQuestions.highestPaidByDepartment(List.of()));
        }

        @Test
        void namesWithSalaryAbove() {
            assertEquals(List.of("Ankit", "Esha", "Naman", "Sachit", "Sumit"),
                    StreamQuestions.namesWithSalaryAbove(staff(), 50000));
        }

        @Test
        void namesWithSalaryAboveExcludesTheThresholdItself() {
            assertEquals(List.of("Abhishek", "Ankit", "Esha", "Naman", "Sachit", "Push", "Sumit"),
                    StreamQuestions.namesWithSalaryAbove(staff(), 49999));
            assertEquals(List.of(), StreamQuestions.namesWithSalaryAbove(staff(), 80000));
            assertEquals(List.of(), StreamQuestions.namesWithSalaryAbove(List.of(), 0));
        }

        @Test
        void sortedBySalaryDescendingKeepsInputOrderForEqualSalaries() {
            assertEquals(List.of("Sachit", "Ankit", "Esha", "Naman", "Sumit", "Abhishek", "Push", "Tina", "Rahul"),
                    names(StreamQuestions.sortedBySalaryDescending(staff())));
            assertEquals(List.of(), StreamQuestions.sortedBySalaryDescending(List.of()));
        }

        @Test
        void sortedByDepartmentThenSalaryDescending() {
            assertEquals(List.of("Esha", "Naman", "Tina", "Rahul", "Sachit", "Ankit", "Abhishek", "Sumit", "Push"),
                    names(StreamQuestions.sortedByDepartmentThenSalaryDescending(staff())));
        }

        @Test
        void highestPaid() {
            assertEquals(Optional.of("Sachit"), name(StreamQuestions.highestPaid(staff())));
            assertEquals(Optional.empty(), StreamQuestions.highestPaid(List.of()));
        }

        @Test
        void secondHighestSalary() {
            assertEquals(Optional.of(70000), StreamQuestions.secondHighestSalary(staff()));
        }

        @Test
        void secondHighestSalaryIgnoresRepeatedTopSalary() {
            List<Employee> employees = List.of(
                    new Employee("A", "X", 90), new Employee("B", "X", 90), new Employee("C", "X", 80));

            assertEquals(Optional.of(80), StreamQuestions.secondHighestSalary(employees));
        }

        @Test
        void secondHighestSalaryIsEmptyWithFewerThanTwoDistinctSalaries() {
            assertEquals(Optional.empty(), StreamQuestions.secondHighestSalary(List.of()));
            assertEquals(Optional.empty(), StreamQuestions.secondHighestSalary(List.of(new Employee("A", "X", 90))));
            assertEquals(Optional.empty(), StreamQuestions.secondHighestSalary(
                    List.of(new Employee("A", "X", 90), new Employee("B", "Y", 90))));
        }

        @Test
        void groupByDepartment() {
            Map<String, List<Employee>> groups = StreamQuestions.groupByDepartment(staff());

            assertEquals(Map.of("IT", List.of("Abhishek", "Ankit", "Sachit"), "HR", List.of("Rahul", "Tina"),
                            "Finance", List.of("Esha", "Naman"), "Marketing", List.of("Push", "Sumit")),
                    groups.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> names(e.getValue()))));
            assertEquals(Map.of(), StreamQuestions.groupByDepartment(List.of()));
        }

        @Test
        void namesByDepartment() {
            assertEquals(Map.of("IT", List.of("Abhishek", "Ankit", "Sachit"), "HR", List.of("Rahul", "Tina"),
                            "Finance", List.of("Esha", "Naman"), "Marketing", List.of("Push", "Sumit")),
                    StreamQuestions.namesByDepartment(staff()));
            assertEquals(Map.of(), StreamQuestions.namesByDepartment(List.of()));
        }

        @Test
        void noneOfTheQueriesReorderTheInputList() {
            List<Employee> employees = new ArrayList<>(staff());
            List<String> before = names(employees);

            StreamQuestions.sortedBySalaryDescending(employees);
            StreamQuestions.sortedBySalaryThenName(employees);
            StreamQuestions.sortedByDepartmentThenSalaryDescending(employees);
            StreamQuestions.topBySalary(employees, 2);

            assertEquals(before, names(employees));
        }
    }

    @Nested
    class Characters {

        @Test
        void characterFrequenciesAreInOrderOfFirstAppearance() {
            Map<Character, Long> frequencies = StreamQuestions.characterFrequencies("programming");

            assertEquals(Map.of('p', 1L, 'r', 2L, 'o', 1L, 'g', 2L, 'a', 1L, 'm', 2L, 'i', 1L, 'n', 1L), frequencies);
            assertEquals(List.of('p', 'r', 'o', 'g', 'a', 'm', 'i', 'n'), new ArrayList<>(frequencies.keySet()));
            assertEquals(Map.of(), StreamQuestions.characterFrequencies(""));
        }

        @Test
        void firstNonRepeatedCharacter() {
            assertEquals(Optional.of('p'), StreamQuestions.firstNonRepeatedCharacter("programming"));
            assertEquals(Optional.of('c'), StreamQuestions.firstNonRepeatedCharacter("aabbc"));
            assertEquals(Optional.of('A'), StreamQuestions.firstNonRepeatedCharacter("aAa"));
            assertEquals(Optional.empty(), StreamQuestions.firstNonRepeatedCharacter("aabb"));
            assertEquals(Optional.empty(), StreamQuestions.firstNonRepeatedCharacter(""));
        }

        @Test
        void firstRepeatedCharacter() {
            assertEquals(Optional.of('r'), StreamQuestions.firstRepeatedCharacter("programming"));
            assertEquals(Optional.of('a'), StreamQuestions.firstRepeatedCharacter("abba"));
            assertEquals(Optional.empty(), StreamQuestions.firstRepeatedCharacter("abc"));
            assertEquals(Optional.empty(), StreamQuestions.firstRepeatedCharacter(""));
        }

        @Test
        void duplicateCharacters() {
            assertEquals(List.of('r', 'g', 'm'), StreamQuestions.duplicateCharacters("programming"));
            assertEquals(List.of('a'), StreamQuestions.duplicateCharacters("aaa"));
            assertEquals(List.of(), StreamQuestions.duplicateCharacters("abc"));
            assertEquals(List.of(), StreamQuestions.duplicateCharacters(""));
        }

        @Test
        void uniqueCharacters() {
            assertEquals(List.of('p', 'o', 'a', 'i', 'n'), StreamQuestions.uniqueCharacters("programming"));
            assertEquals(List.of('a', 'b', 'c'), StreamQuestions.uniqueCharacters("abc"));
            assertEquals(List.of(), StreamQuestions.uniqueCharacters("aabb"));
            assertEquals(List.of(), StreamQuestions.uniqueCharacters(""));
        }
    }

    @Test
    void mainPrintsTheSameOutputAsBeforeTheLogicWasExtracted() throws Exception {
        assertEquals(Console.expectedOutput("streams.curiousCoder.StreamQuestions"),
                Console.capture(() -> StreamQuestions.main(new String[0])));
    }
}
