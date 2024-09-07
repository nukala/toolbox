package org.ravi.udemy.jdk8.streams;

import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.ravi.udemy.jdk8.streams.StreamsMapExample.strSoutFunc;


// lab37 and lab38
public class StreamsFlatMapExample {
    private final List<Student> students;

    public StreamsFlatMapExample() {
        this.students = StudentDataBase.getAllStudents();
    }

    public static void main(String[] args) {
        StreamsFlatMapExample smfe = new StreamsFlatMapExample();
        System.out.println(strSoutFunc.apply(smfe.activitiesSet()));

        System.out.println(System.lineSeparator() + "===");
        System.out.println(strSoutFunc.apply(smfe.activitiesList()));

        System.out.println(System.lineSeparator() + "===");
        System.out.println("distinctList : " + strSoutFunc.apply(smfe.distinctList()));
        
        System.out.println(System.lineSeparator() + "===");
        System.out.println("totalDistinctActivityCount : " + smfe.totalDistinctActivityCount());
        System.out.println("rawActivityCount : " + smfe.rawActivityCount());
        System.out.println("sortedActivities : " + smfe.sortedActivities());
    }

    public List<String> activitiesList() {
        return students
                .stream()
                .map(Student::getActivities)
                .flatMap(List::stream) // Stream<String>
                .collect(Collectors.toList())
                ;
    }

    public Set<String> activitiesSet() {
        @WorthLooking("use flatmap to TRANSFORM stream collection of objects into stream of objects.")
        Set<String> activitySet = students
                .stream()
                .map(Student::getActivities)
                // lst is an arraylist with strings!
                .flatMap((lst) -> {
                    // convert from Stream<List<?>> to Stream<?>
                    System.out.println(">>> " + strSoutFunc.apply(lst));
                    return lst.stream();
                }) // Stream<String>
                .collect(Collectors.toSet())
                ;
        return activitySet;
    }

    // RNTODO toMap with counts?

    public List<String> distinctList() {
        return activitiesList()
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @SuppressWarnings("all")
    // warning is to skip stream creation.
    public long totalDistinctActivityCount() {
        return distinctList()
                .stream()
                .count();
    }

    @SuppressWarnings("all")
    public long rawActivityCount() {
        return activitiesList()
                .stream()
                .count();
    }
    
    //@SuppressWarnings("all")
    public List<String> sortedActivities() {
        return distinctList()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
