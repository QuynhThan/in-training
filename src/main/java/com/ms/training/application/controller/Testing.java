package com.ms.training.application.controller;

import java.util.*;

class Testing {
    public static void main(String[] args) {
        List<String> subjects = Arrays.asList("Math", "Science", "English", "History", "Computer Science", "ABC");
        List<String> teachers = Arrays.asList("Mr. Smith", "Ms. Johnson", "Mr. Davis", "Ms. Taylor", "Mr. Wilson", "ABD");

        int days = 5; // Number of days in a week
        int periodsPerDay = 4; // Number of periods in a day
        int populationSize = 10;

        List<Chromosome> population = initializePopulation(subjects, teachers, days, periodsPerDay, populationSize);

        for (int generation = 1; generation <= 100; generation++) {
            population = evolvePopulation(population);
            Chromosome bestSolution = getBestSolution(population);

            System.out.println("Generation " + generation + ": Fitness - " + bestSolution.getFitness());
            printTimetable(bestSolution, days, periodsPerDay);
        }
    }

    private static List<Chromosome> initializePopulation(List<String> subjects, List<String> teachers,
                                                         int days, int periodsPerDay, int populationSize) {
        List<Chromosome> population = new ArrayList<>();

        for (int i = 0; i < populationSize; i++) {
            Chromosome chromosome = new Chromosome(subjects, teachers, days, periodsPerDay);
            population.add(chromosome);
        }

        return population;
    }

    private static List<Chromosome> evolvePopulation(List<Chromosome> population) {
        List<Chromosome> newPopulation = new ArrayList<>();

        // Select parents, perform crossover, and apply mutation
        for (int i = 0; i < population.size(); i++) {
            Chromosome parent1 = selectParent(population);
            Chromosome parent2 = selectParent(population);

            Chromosome child = crossover(parent1, parent2);
            mutate(child);

            newPopulation.add(child);
        }

        return newPopulation;
    }

    private static Chromosome selectParent(List<Chromosome> population) {
        // Tournament selection: Randomly select two individuals and choose the one with the better fitness
        Random random = new Random();
        int index1 = random.nextInt(population.size());
        int index2 = random.nextInt(population.size());

        return (population.get(index1).getFitness() > population.get(index2).getFitness()) ? population.get(index1) : population.get(index2);
    }

    private static Chromosome crossover(Chromosome parent1, Chromosome parent2) {
        // Single-point crossover
        Random random = new Random();
        int crossoverPoint = random.nextInt(parent1.getGenes().size());

        List<String> childGenes = new ArrayList<>(parent1.getGenes().subList(0, crossoverPoint));
        childGenes.addAll(parent2.getGenes().subList(crossoverPoint, parent2.getGenes().size()));

        return new Chromosome(childGenes);
    }

    private static void mutate(Chromosome chromosome) {
        // Swap two randomly selected genes
        Random random = new Random();
        int index1 = random.nextInt(chromosome.getGenes().size());
        int index2 = random.nextInt(chromosome.getGenes().size());

        Collections.swap(chromosome.getGenes(), index1, index2);
    }

    private static Chromosome getBestSolution(List<Chromosome> population) {
        return Collections.max(population, Comparator.comparing(Chromosome::getFitness));
    }

    private static void printTimetable(Chromosome chromosome, int days, int periodsPerDay) {
        System.out.println("Timetable:");
        for (int day = 1; day <= days; day++) {
            for (int period = 1; period <= periodsPerDay; period++) {
                String subject = chromosome.getGene(day, period);
                String teacher = chromosome.getGene(subject);
                if (Objects.nonNull(subject) && Objects.nonNull(teacher)) {
                    System.out.println("Day " + day + ", Period " + period + ": " + subject + " (Teacher: " + teacher + ")");
                }
            }
        }
    }
}

class Chromosome {
    private List<String> genes;
    private int fitness;

    public Chromosome(List<String> subjects, List<String> teachers, int days, int periodsPerDay) {
        genes = new ArrayList<>(days * periodsPerDay);

        // Shuffle subjects and teachers independently to create a random initial timetable
        List<String> shuffledSubjects = new ArrayList<>(subjects);
        List<String> shuffledTeachers = new ArrayList<>(teachers);
        Collections.shuffle(shuffledSubjects);
        Collections.shuffle(shuffledTeachers);

        for (int day = 1; day <= days; day++) {
            for (int period = 1; period <= periodsPerDay; period++) {
                int index = (day - 1) * periodsPerDay + (period - 1);
                if (index < shuffledSubjects.size()) {
                    genes.add(shuffledSubjects.get(index));
                }
            }
        }

        calculateFitness(teachers);
    }

    public Chromosome(List<String> genes) {
        this.genes = new ArrayList<>(genes);
        // Fitness is not calculated during initialization
    }

    public List<String> getGenes() {
        return genes;
    }

    public int getFitness() {
        return fitness;
    }

    public String getGene(int day, int period) {
        int index = (day - 1) * 4 + (period - 1);
        if (index < genes.size()) {
            return genes.get(index);
        }
        return null;
    }

    public String getGene(String subject) {
        int index = genes.indexOf(subject);
        return genes.get((index / 4) * 4); // Assuming there are 4 periods per day
    }

    private void calculateFitness(List<String> teachers) {
        // Simple fitness function: Count the number of unique teachers
        Set<String> uniqueTeachers = new HashSet<>();
        for (String gene : genes) {
            uniqueTeachers.add(teachers.get(genes.indexOf(gene)));
        }
        fitness = uniqueTeachers.size();
    }
}