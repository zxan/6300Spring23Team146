package edu.gatech.seclass.jobcompare6300.model;

public class Weight {
    private static int weightentryID;
    private static int salaryWeight;
    private static int bonusWeight;
    private static int rsuWeight;
    private static int relocationStipendWeight;
    private static int holidaysWeight;

    public Weight(int entryID, int salary, int bonus, int rsu, int relocationstipend, int holiday) {
        weightentryID = entryID;
        salaryWeight = salary;
        bonusWeight = bonus;
        rsuWeight = rsu;
        relocationStipendWeight = relocationstipend;
        holidaysWeight = holiday;
    }

    public static int getSalaryWeight() {
        return salaryWeight;
    }

    public static void setSalaryWeight(int salaryWeight) {
        Weight.salaryWeight = salaryWeight;
    }

    public static int getBonusWeight() {
        return bonusWeight;
    }

    public static void setBonusWeight(int bonusWeight) {
        Weight.bonusWeight = bonusWeight;
    }

    public static int getRsuWeight() {
        return rsuWeight;
    }

    public static void setRsuWeight(int rsuWeight) {
        Weight.rsuWeight = rsuWeight;
    }

    public static int getRelocationStipendWeight() {
        return relocationStipendWeight;
    }

    public static void setRelocationStipendWeight(int relocationStipendWeight) {
        Weight.relocationStipendWeight = relocationStipendWeight;
    }

    public static int getHolidaysWeight() {
        return holidaysWeight;
    }

    public static void setHolidaysWeight(int holidaysWeight) {
        Weight.holidaysWeight = holidaysWeight;
    }
}
