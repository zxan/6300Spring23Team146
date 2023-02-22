package edu.gatech.cs6300.entity;

public class Weight {
    private static int salaryWeight;
    private static int bonusWeight;
    private static int rsuWeight;
    private static int relocationStipendWeight;
    private static int holidaysWeight;

    public Weight() {
        salaryWeight = 1;
        bonusWeight = 1;
        rsuWeight = 1;
        relocationStipendWeight = 1;
        holidaysWeight = 1;
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
