package org.example;

import java.util.Scanner;

public class Main {

    public static String[][] genCinema(int rows, int seats) {
        String[][] cinema = new String[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = "S";
            }
        }
        return cinema;
    }

    public static void showSeats(int rows, int seats, String[][] cinema) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i =0; i < seats; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print (i + 1);
            for (int j = 0; j < seats; j++) {
                System.out.print(" " + cinema[i][j]);
            }
            System.out.println();
        }
    }



    public static void buyTicket(int ticketRow, int ticketNumber, int rows, int seats, String[][] cinema, int[][] income) {


        while (true) {
            if (ticketRow > rows || ticketNumber > seats ) {
                System.out.println("Wrong input!");
                System.out.println();
                ticketRow = readInt("Enter a row number:");
                ticketNumber = readInt("Enter a seat number in that row:");

            } else if (cinema[ticketRow-1][ticketNumber-1].equals("B")) {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
                ticketRow = readInt("Enter a row number:");
                ticketNumber = readInt("Enter a seat number in that row:");

            } else {
                cinema[ticketRow-1][ticketNumber-1] = "B";
                System.out.print("Ticket price:");
                System.out.println("$" + income[ticketRow-1][ticketNumber-1]);
                break;
            }
        }

    }

    public static int[][] getIncome(int rows, int seats) {
        int[][] income = new int[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {

                if (rows * seats <= 60) {
                    income[i][j] = 10;
                } else {
                    if (i >= rows/2) {
                        income[i][j] = 8;
                    } else {
                        income[i][j] = 10;
                    }
                }
            }
        }
        return income;
    }


    public static void doStatistics(int rows, int seats, String[][] cinema, int[][] income) {
        /*Number of purchased tickets: 0
        Percentage: 0.00%
        Current income: $0
        Total income: $360*/
        int counter = 0;
        int totalIncome = 0;
        int currentIncome = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {


                if (cinema[i][j].equals("B")) {
                    counter += 1;
                    currentIncome += income[i][j];

                }
                totalIncome += income[i][j];
            }
        }
        System.out.println("Number of purchased tickets: " + counter);
        System.out.printf("Percentage: %.2f", (counter/0.01/(rows * seats)));
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static int readInt(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        int rows = readInt("Enter the number of rows:");
        int seats = readInt("Enter the number of seats in each row:");
        String[][] cinema = genCinema(rows, seats);
        int[][] income = getIncome(rows, seats);
        System.out.println();

        while (true) {
            int n = readInt("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit""");
            System.out.println();
            if (n == 1) {
                showSeats(rows, seats, cinema);
            } else if (n == 2) {
                int ticketRow = readInt("Enter a row number:");
                int ticketNumber = readInt("Enter a seat number in that row:");
                buyTicket(ticketRow, ticketNumber, rows, seats, cinema, income);
            } else if (n == 3) {
                doStatistics(rows, seats, cinema, income);
            } else if (n == 0) {
                System.out.println(0);
                break;

            }
            System.out.println();
        }
    }
}