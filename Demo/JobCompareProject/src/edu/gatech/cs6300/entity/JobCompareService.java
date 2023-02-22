package edu.gatech.cs6300.entity;

import java.util.*;

public class JobCompareService {
    private Map<Integer, Job> jobs = new HashMap<>();
    private Job currentJob;

    public void commandLoop() {
        Scanner commandInput = new Scanner(System.in);
        printMainMenu(commandInput);
    }

    private void printMainMenu(Scanner commandInput) {
        System.out.println(">> Main Menu");
        System.out.println("(1) enter or edit current job details");
        System.out.println("(2) enter job offers");
        System.out.println("(3) adjust the comparison settings");
        System.out.println("(4) compare job offers");

        int selection = inputNumber(commandInput, "Choice: ", 1, 4);
        if (selection == 1) {
            Job savedJob = inputJobDetails(commandInput, false);
        } else if (selection == 2) {
            Job savedJob = inputJobDetails(commandInput, true);
            printSubMenu(commandInput, savedJob);
        } else if (selection == 3) {
            inputWeight(commandInput);
        } else if (selection == 4) {
            rankJobs();
            printSubMenuCompare(commandInput);
        }
        printMainMenu(commandInput);
    }

    private void printSubMenu(Scanner commandInput, Job job) {
        System.out.println(">> Sub Menu");
        System.out.println("(1) enter another offer");
        System.out.println("(2) return to the main menu");

        boolean withOption3 = job != null && (currentJob != null);
        if (withOption3) {
            System.out.println("(3) compare the offer with current job details");
        }

        int selection = inputNumber(commandInput, "Choice: ", 1, withOption3 ? 3 : 2);
        if (selection == 1) {
            Job savedJob = inputJobDetails(commandInput, true);
            printSubMenu(commandInput, job);
        } else if (selection == 2) {
            printMainMenu(commandInput);
        } else if (selection == 3) {
            inputCompanyID(commandInput, job.getId());
            printMainMenu(commandInput);
        }
    }

    private void printSubMenuCompare(Scanner commandInput) {
        System.out.println(">> Sub Menu");
        System.out.println("(1) compare two jobs");
        System.out.println("(2) return to the main menu");

        int selection = inputNumber(commandInput, "Choice: ", 1, 2);
        if (selection == 1) {
            inputCompanyID(commandInput, 0);
            printSubMenuCompare(commandInput);
        } else if (selection == 2) {
            printMainMenu(commandInput);
        }
    }

    private Job inputJobDetails(Scanner commandInput, boolean isNewOffer) {
        if (isNewOffer) {
            System.out.println(">> Please enter details of the new offer.");
        } else if (currentJob == null){
            System.out.println(">> Please enter details of your current Job.");
        } else {
            System.out.println(">> You already have a job, please edit the details of current Job.");
        }

        String title = inputString(commandInput, "Title: ");
        String company = inputString(commandInput, "Company: ");
        String location = inputString(commandInput, "Location: ");
        int index = inputNumber(commandInput, "Cost of Living Index: ", 0, Integer.MAX_VALUE);
        int salary = inputNumber(commandInput, "Yearly Salary: ", 0, Integer.MAX_VALUE);
        int bonus = inputNumber(commandInput, "Yearly Bonus: ", 0, Integer.MAX_VALUE);
        int rsu = inputNumber(commandInput, "Restricted Stock Unit Award: ", 0, Integer.MAX_VALUE);
        int relocationStipend = inputNumber(commandInput, "Relocation stipend: ", 0, 25000);
        int holiday = inputNumber(commandInput, "Personal Choice Holidays: ", 0, 20);

        if (!saveInput(commandInput)) {
            System.out.println(">> Canceled! The Info Will NOT Be Saved!");
            return null;
        }

        Job savedJob = null;
        if (isNewOffer) {
            savedJob = new Job(jobs.size() + 1, title, company, location, index, salary, bonus, rsu, relocationStipend, holiday);
            jobs.put(jobs.size() + 1, savedJob);
            System.out.println(">> Success! You Entered a new Job Offer");
        } else if (currentJob == null) {
            savedJob = new Job(jobs.size() + 1, title, company, location, index, salary, bonus, rsu, relocationStipend, holiday);
            jobs.put(jobs.size() + 1, savedJob);

            currentJob = savedJob;
            currentJob.setAsCurrentJob();
            System.out.println(">> Success! You Entered a new Current Job");
        } else {
            currentJob.editJob(title, company, location, index, salary, bonus, rsu, relocationStipend, holiday);
            System.out.println(">> Success! You Edited your current job");
        }
        return savedJob;
    }

    private void inputWeight(Scanner commandInput) {
        System.out.println(">> Please enter the new weight for comparison");
        int salaryWeight = inputNumber(commandInput, "Weight of Yearly Salary: ", 1, 10);
        int bonusWeight = inputNumber(commandInput, "Weight of Yearly Bonus: ", 1, 10);
        int rsuWeight = inputNumber(commandInput, "Weight of Restricted Stock Unit Award: ", 1, 10);
        int relocationStipendWeight = inputNumber(commandInput, "Weight of Relocation stipend: ", 1, 10);
        int holidayWeight = inputNumber(commandInput, "Weight of Personal Choice Holidays: ", 1, 10);

        Weight.setSalaryWeight(salaryWeight);
        Weight.setBonusWeight(bonusWeight);
        Weight.setRsuWeight(rsuWeight);
        Weight.setRelocationStipendWeight(relocationStipendWeight);
        Weight.setHolidaysWeight(holidayWeight);
    }

    private void inputCompanyID(Scanner commandInput, int id) {
        int id1 = id;
        int id2 = currentJob.getId();

        if (id == 0) {
            System.out.println(">> Enter 2 company id to compare");
            id1 = inputNumber(commandInput, "ID1: ", 1, jobs.size());
            id2 = inputNumber(commandInput, "ID2: ", 1, jobs.size());
        }

        System.out.println(String.format("%-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s", "id", "title", "company", "location", "salary", "bonus", "rsu", "stipend", "holidays"));
        System.out.println(jobs.get(id1).toString());
        System.out.println(jobs.get(id2).toString());
    }

    private boolean saveInput(Scanner commandInput) {
        System.out.println(">> Save (1) OR Cancel (2)?");
        return inputNumber(commandInput, "Choice: ", 1, 2) == 1;
    }

    private String inputString(Scanner commandInput, String remind) {
        System.out.print(remind);

        String str = commandInput.nextLine();
        while (str.length() == 0) {
            System.out.println(">> Please enter a valid input!");
            return inputString(commandInput, remind);
        }
        return str;
    }

    private int inputNumber(Scanner commandInput, String remind, int lower, int upper) {
        System.out.print(remind);

        String str = commandInput.nextLine();
        while (str.length() == 0 || !str.matches("[0-9]*")) {
            System.out.println(">> Please enter a valid input!");
            return inputNumber(commandInput, remind, lower, upper);
        }

        int num = Integer.parseInt(str);
        if (num < lower || num > upper) {
            System.out.println(String.format(">> Please enter number between %s and %s", lower, upper));
            return inputNumber(commandInput, remind, lower, upper);
        }
        return num;
    }

    private void rankJobs() {
        System.out.println(">> Rank Jobs based on Score:");
        List<Job> list = new ArrayList<>(jobs.values());
        list.sort((a, b) -> b.getScore() - a.getScore() > 0 ? 1 : -1);

        System.out.println(String.format("%-8s %-8s %-8s", "id", "title", "company"));
        for (Job job : list) {
            System.out.println(job.toShort());
        }
    }
}
