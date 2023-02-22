package edu.gatech.cs6300;

import edu.gatech.cs6300.entity.JobCompareService;

public class Main {
    public static void main(String[] args) {
        System.out.println("****************************************************");
        System.out.println("*        Welcome to the Job Compare Service!       *");
        System.out.println("****************************************************");

        JobCompareService service = new JobCompareService();
        service.commandLoop();
    }
}