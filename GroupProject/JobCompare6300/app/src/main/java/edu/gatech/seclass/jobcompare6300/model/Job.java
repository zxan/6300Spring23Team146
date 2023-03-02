package edu.gatech.seclass.jobcompare6300.model;

public class Job {

    private int id;
    private String title;
    private String company;
    private String location;
    private int costIndex;
    private int salary;
    private int bonus;
    private int rsu;
    private int relocateStipend;
    private int holidays;
    private boolean isCurrentJob;

    public Job(int id, String title, String company, String location, int costIndex, int salary, int bonus, int rsu, int relocateStipend, int holidays) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.costIndex = costIndex;
        this.salary = salary;
        this.bonus = bonus;
        this.rsu = rsu;
        this.relocateStipend = relocateStipend;
        this.holidays = holidays;
    }

    public void editJob(String title, String company, String location, int costIndex, int salary, int bonus, int rsu, int relocateStipend, int holidays) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.costIndex = costIndex;
        this.salary = salary;
        this.bonus = bonus;
        this.rsu = rsu;
        this.relocateStipend = relocateStipend;
        this.holidays = holidays;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getCompany() {return company; }
    public String getLocation() {
        return location;
    }
    public int getCostIndex() {
        return costIndex;
    }
    public int getSalary() {
        return salary;
    }
    public int getBonus() {
        return bonus;
    }
    public int getRsu() {
        return rsu;
    }
    public int getRelocateStipend() {
        return relocateStipend;
    }
    public int getHolidays() {
        return holidays;
    }


    public void setAsCurrentJob() {
        this.isCurrentJob = true;
    }

    public double getScore() {
        double sum = Weight.getSalaryWeight() + Weight.getBonusWeight() + Weight.getRsuWeight() + Weight.getRelocationStipendWeight() + Weight.getHolidaysWeight();
        double score = Weight.getSalaryWeight() / sum * salary
                + Weight.getBonusWeight() / sum * bonus
                + Weight.getRsuWeight() / sum * rsu / 4
                + Weight.getRelocationStipendWeight() / sum * relocateStipend
                + Weight.getHolidaysWeight() / sum * holidays * salary / 260;
        return score;
    }

    public String toString() {
        String str = String.format("%-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s", id, title, company, location, salary, bonus, rsu, relocateStipend, holidays);
        if (isCurrentJob) {
            str += "  <- Current Job";
        }
        return str;
    }

    public String toShort() {
        String str = String.format("%-8s %-8s %-8s", id, title, company);
        if (isCurrentJob) {
            str += "  <- Current Job";
        }
        return str;
    }


}
