package geneticalgorithm;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class ScheduleAlgo {

    public static Schedule initializeSchedule(List<Classroom> classrooms,
                                              List<Timeslot> timeslots,
                                              List<Professor> professors,
                                              List<Course> courses,
                                              List<Studentgroup> studentGroups) {
        Schedule schedule = new Schedule();
        classrooms.forEach(x -> {
            schedule.addRoom(x.getRoomId(),x.getRoomNumber(),x.getRoomCapacity());
        });
        timeslots.forEach(x -> {
            schedule.addTimeslot(x.getTimeslotId(), x.getTimeslot());
        });
        professors.forEach(x -> {
            schedule.addProfessor(x.getProfessorId(), x.getProfessorName());
        });
        courses.forEach(x -> {
            schedule.addCourse(x.getCourseId(), x.getCourseCode(), x.getCourseName(), x.getProfessorIds());
        });
        studentGroups.forEach(x -> {
            schedule.addGroup(x.getGroupId(), x.getGroupSize(), x.getCourseIds());
        });
        if (CollectionUtils.isEmpty(timeslots)) {
            schedule.addTimeslot(1, "Monday 9:00 - 11:00");
            schedule.addTimeslot(2, "Monday 11:00 - 13:00");
            schedule.addTimeslot(3, "Monday 13:00 - 15:00");
            schedule.addTimeslot(4, "Tuesday 9:00 - 11:00");
            schedule.addTimeslot(5, "Tuesday 11:00 - 13:00");
            schedule.addTimeslot(6, "Tuesday 13:00 - 15:00");
            schedule.addTimeslot(7, "Wednesday 9:00 - 11:00");
            schedule.addTimeslot(8, "Wednesday 11:00 - 13:00");
            schedule.addTimeslot(9, "Wednesday 13:00 - 15:00");
            schedule.addTimeslot(10, "Thursday 9:00 - 11:00");
            schedule.addTimeslot(11, "Thursday 11:00 - 13:00");
            schedule.addTimeslot(12, "Thursday 13:00 - 15:00");
            schedule.addTimeslot(13, "Friday 9:00 - 11:00");
            schedule.addTimeslot(14, "Friday 11:00 - 13:00");
            schedule.addTimeslot(15, "Friday 13:00 - 15:00");
            schedule.addTimeslot(16, "Friday 15:00 - 17:00");
            schedule.addTimeslot(17, "Monday 15:00 - 17:00");
            schedule.addTimeslot(18, "Tuesday 15:00 - 17:00");
            schedule.addTimeslot(19, "Wednesday 15:00 - 17:00");
            schedule.addTimeslot(20, "Thursday 15:00 - 17:00");
        }
//        if (CollectionUtils.isEmpty(timeslots)) {
//            schedule.addTimeslot(1, "Monday Mor");
//            schedule.addTimeslot(2, "Monday Noon");
//            schedule.addTimeslot(3, "Tuesday Mor");
//            schedule.addTimeslot(4, "Tuesday Noon");
//            schedule.addTimeslot(5, "Wednesday Mor");
//            schedule.addTimeslot(6, "Wednesday Noon");
//            schedule.addTimeslot(7, "Thursday Mor");
//            schedule.addTimeslot(8, "Thursday Noon");
//            schedule.addTimeslot(9, "Friday Mor");
//            schedule.addTimeslot(10, "Friday Noon");
//            schedule.addTimeslot(11, "Saturday Mor");
//            schedule.addTimeslot(12, "Saturday Noon");
//        }
        return schedule;
    }


    public static Schedule initializeSchedule() {

        Schedule schedule = new Schedule();


        schedule.addRoom(1, "A", 80);
        schedule.addRoom(6, "B", 30);
        schedule.addRoom(4, "C", 80);
        schedule.addRoom(5, "D", 120);
        schedule.addRoom(2, "E", 40);


        schedule.addTimeslot(1, "Monday 9:00 - 11:00");
        schedule.addTimeslot(2, "Monday 11:00 - 13:00");
        schedule.addTimeslot(3, "Monday 13:00 - 15:00");
        schedule.addTimeslot(4, "Tuesday 9:00 - 11:00");
        schedule.addTimeslot(5, "Tuesday 11:00 - 13:00");
        schedule.addTimeslot(6, "Tuesday 13:00 - 15:00");
        schedule.addTimeslot(7, "Wednesday 9:00 - 11:00");
        schedule.addTimeslot(8, "Wednesday 11:00 - 13:00");
        schedule.addTimeslot(9, "Wednesday 13:00 - 15:00");
        schedule.addTimeslot(10, "Thursday 9:00 - 11:00");
        schedule.addTimeslot(11, "Thursday 11:00 - 13:00");
        schedule.addTimeslot(12, "Thursday 13:00 - 15:00");
        schedule.addTimeslot(13, "Friday 9:00 - 11:00");
        schedule.addTimeslot(14, "Friday 11:00 - 13:00");
        schedule.addTimeslot(15, "Friday 13:00 - 15:00");
        schedule.addTimeslot(16, "Friday 15:00 - 17:00");
        schedule.addTimeslot(17, "Monday 15:00 - 17:00");
        schedule.addTimeslot(18, "Tuesday 15:00 - 17:00");
        schedule.addTimeslot(19, "Wednesday 15:00 - 17:00");
        schedule.addTimeslot(20, "Thursday 15:00 - 17:00");


        schedule.addProfessor(1, "Kal Bugrara");
        schedule.addProfessor(5, "Robin Hilyard");
        schedule.addProfessor(8, "Yusuf Ozbek");
        schedule.addProfessor(10, "Vishal Chawla");
        schedule.addProfessor(9, "Amuthan");
        schedule.addProfessor(11, "Tejas Parikh");


        schedule.addCourse(1, "AA", "Algorithm", new int[] { 1, 5 ,8});
        schedule.addCourse(8, "DD", "Database", new int[] { 1, 11 ,5});
        schedule.addCourse(3, "CC", "Cloud Computing", new int[] { 1, 10 ,5});
        schedule.addCourse(4, "WD", "Web Development", new int[] { 9, 8 ,11});
        schedule.addCourse(5, "AE", "Application Engineering", new int[] { 1 });
        schedule.addCourse(6, "DS", "Data Science", new int[] { 1, 8,10 });
        schedule.addCourse(7, "BI", "Business Intelligence", new int[]{5,8,11});


        schedule.addGroup(1, 200, new int[] { 1, 8, 3, 4, 5, 6, 7 });
//        schedule.addGroup(2, 30, new int[] { 8, 3, 5, 6 });
//        schedule.addGroup(3, 18, new int[] { 3, 4, 5 });
//        schedule.addGroup(4, 25, new int[] { 1, 4 ,7});
//        schedule.addGroup(5, 20, new int[] { 8, 3, 5 });
//        schedule.addGroup(6, 22, new int[] { 1, 4, 5 });
//        schedule.addGroup(7, 16, new int[] { 1, 3 });
//        schedule.addGroup(8, 18, new int[] { 8, 6 ,7});
//        schedule.addGroup(9, 24, new int[] { 1, 6 });
//        schedule.addGroup(10, 25, new int[] { 3, 4 ,7});

        return schedule;
    }


    public static void PrintClassAll(Schedule schedule){
        Class classes[] = schedule.getClasses();
        int classIndex = 1;
        for (Class bestClass : classes) {
            System.out.println("CLASS " + classIndex + ":");
            System.out.println("COURSE: " + schedule.getCourse(bestClass.getCourseId()).getCourseName());
            System.out.println("STUDENT GROUP: " + schedule.getGroup(bestClass.getGroupId()).getGroupId());
            System.out.println("ROOM: " + schedule.getRoom(bestClass.getRoomId()).getRoomNumber());
            System.out.println("PROFESSOR: " + schedule.getProfessor(bestClass.getProfessorId()).getProfessorName());
            System.out.println("TIMESLOT: " + schedule.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
            System.out.println("*****************************************************************");
            classIndex++;
        }
    }



    public static List<Class> getClasses(Schedule schedule, String type, int id) {
        switch (type) {
            case "ROOM":
                return schedule.getRoomMap().get(id);
            case "PROF":
                return schedule.getProfMap().get(id);
            case "MODULE":
                return schedule.getCourseMap().get(id);
            case "GROUP":
                return schedule.getGroupMap().get(id);
            default:
                return null;
        }
    }



    public static void PrintClasses(Schedule schedule, String type, int id){
        List<Class> classes = getClasses(schedule, type, id);
        for (Class bestClass: classes){
            printClass(schedule, bestClass);
        }
    }


    public static void printClass(Schedule schedule, Class bestClass) {
        System.out.println("COURSE: " + schedule.getCourse(bestClass.getCourseId()).getCourseName());
        System.out.println("STUDENT GROUP: " + schedule.getGroup(bestClass.getGroupId()).getGroupId());
        System.out.println("CLASSROOM: " + schedule.getRoom(bestClass.getRoomId()).getRoomNumber());
        System.out.println("PROFESSOR: " + schedule.getProfessor(bestClass.getProfessorId()).getProfessorName());
        System.out.println("TIMESLOT: " + schedule.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
        System.out.println("*********************************************************************");
    }


    public static void main(String[] args) {

        Schedule schedule = initializeSchedule();


        GeneticAlgorithm ga = new GeneticAlgorithm(1000, 0.01, 0.9, 2, 5);


        Population population = ga.initializingPopulation(schedule);


        ga.calcPopulation(population, schedule);


        int generation = 1;



        while (ga.isTerminating(generation, 100) == false && ga.isTerminating(population) == false) {

            System.out.println("Generation No." + generation + " Best fitness: " + population.getFittest(0).getFitness());


            population = ga.crossoverPopulation(population);


            population = ga.mutatingPopulation(population, schedule);


            ga.calcPopulation(population, schedule);

            generation++;
        }

        // Print fitness
        schedule.createClasses(population.getFittest(0));
        System.out.println();
        System.out.println("Solution found in " + generation + " generations");
        System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
        System.out.println("Clashes: " + schedule.calcClashes(100));

        PrintClassAll(schedule);

    }

    public static Schedule buildSchedule(Schedule schedule) {


        GeneticAlgorithm ga = new GeneticAlgorithm(1000, 0.01, 0.9, 2, 5);


        Population population = ga.initializingPopulation(schedule);


        ga.calcPopulation(population, schedule);


        int generation = 1;



        while (ga.isTerminating(generation, 10) == false && ga.isTerminating(population) == false) {

            System.out.println("Generation No." + generation + " Best fitness: " + population.getFittest(0).getFitness());


            population = ga.crossoverPopulation(population);


            population = ga.mutatingPopulation(population, schedule);


            ga.calcPopulation(population, schedule);

            generation++;
        }

        // Print fitness
        schedule.createClasses(population.getFittest(0));
        System.out.println();
        System.out.println("Solution found in " + generation + " generations");
        System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
        System.out.println("Clashes: " + schedule.calcClashes(100));

        PrintClassAll(schedule);
        return schedule;
    }

}