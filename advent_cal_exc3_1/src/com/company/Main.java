package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> wireOne = new ArrayList<>();
        ArrayList<String> wireTwo = new ArrayList<>();
        ArrayList<String> listOfCoordinatesOne = new ArrayList<>();
        ArrayList<String> listOfCoordinatesTwo = new ArrayList<>();
        int xAxis = 0;
        int yAxis = 0;

        try {

            Scanner sc = new Scanner(new File("src/com/company/test.txt"));
            sc.useDelimiter(",");

            while (sc.hasNext()) {
                String current_value = sc.next();
                wireOne.add(current_value);
            }

            sc = new  Scanner(new File("src/com/company/test2.txt"));
            sc.useDelimiter(",");

            while (sc.hasNext()) {
                String current_value = sc.next();
                wireTwo.add(current_value);
            }

        } catch (Exception ignored) {
            System.out.println(ignored);
        }

        System.out.println("Array creator executed");

        for(int i = 0; i < wireOne.size(); i++) {

            char direction = wireOne.get(i).charAt(0);
            int spacesToMove = Integer.parseInt(wireOne.get(i).substring(1, wireOne.get(i).length()));

            boolean directionIsPositive = false;

            if(direction == 'U' || direction == 'R') {
                directionIsPositive = true;
            }

            if(direction == 'L' || direction == 'R') {
                for(int j = 1; j <= spacesToMove; j++) {
                    listOfCoordinatesOne.add("X" + (xAxis + (j * negativeOrPositiveMultiplier(directionIsPositive))) + " " + "Y" + yAxis);
                }

                xAxis = xAxis + (spacesToMove * negativeOrPositiveMultiplier(directionIsPositive));
            }

            if(direction == 'U' || direction == 'D') {
                for(int j = 1; j <= spacesToMove; j++) {
                    listOfCoordinatesOne.add("X" + xAxis + " " + "Y" + (yAxis + (j * negativeOrPositiveMultiplier(directionIsPositive))));
                }

                yAxis = yAxis + (spacesToMove * negativeOrPositiveMultiplier(directionIsPositive));
            }

        }

        System.out.println("Forloop 1 executed");

        System.out.println("Wire one xAxis: " + xAxis);
        System.out.println("Wire one yAxis: " + yAxis);

        yAxis = 0;
        xAxis = 0;

        for(int i = 0; i < wireTwo.size(); i++) {

            char direction = wireTwo.get(i).charAt(0);
            int spacesToMove = Integer.parseInt(wireTwo.get(i).substring(1, wireTwo.get(i).length()));

            boolean directionIsPositive = false;

            if(direction == 'U' || direction == 'R') {
                directionIsPositive = true;
            }

            if(direction == 'L' || direction == 'R') {

                for(int j = 1; j <= spacesToMove; j++) {
                    listOfCoordinatesTwo.add("X" + (xAxis + (j * negativeOrPositiveMultiplier(directionIsPositive))) + " " + "Y" + yAxis);
                }

                xAxis = xAxis + (spacesToMove * negativeOrPositiveMultiplier(directionIsPositive));

            }

            if(direction == 'U' || direction == 'D') {

                for(int j = 1; j <= spacesToMove; j++) {
                    listOfCoordinatesTwo.add("X" + xAxis + " " + "Y" + (yAxis + (j * negativeOrPositiveMultiplier(directionIsPositive))));
                }

                yAxis = yAxis + (spacesToMove * negativeOrPositiveMultiplier(directionIsPositive));

            }

        }

        System.out.println("Forloop 2 executed");

        System.out.println("Wire two xAxis: " + xAxis);
        System.out.println("Wire two yAxis: " + yAxis);

        ArrayList<String> touchingPoints = new ArrayList<>();

        for(int w = 0; w < listOfCoordinatesOne.size(); w++) {
            if(listOfCoordinatesTwo.contains(listOfCoordinatesOne.get(w))) {
                touchingPoints.add(listOfCoordinatesOne.get(w));
            }
            System.out.println("Third loop's loop nr " + (w+1));
        }

        int distanceOfClosestTouchingPoint = Integer.MAX_VALUE;

        for(int y = 0; y < touchingPoints.size(); y++) {
            System.out.println("Touching point " + (y+1) + " is " + touchingPoints.get(y));

            Pattern MY_PATTERN = Pattern.compile("X(-?)(\\d+)\\sY(-?)(\\d+)");

            Matcher m = MY_PATTERN.matcher(touchingPoints.get(y));
            while (m.find()) {
                int xValue = Integer.parseInt(m.group(2));
                int yValue = Integer.parseInt(m.group(4));

                int calculation = xValue + yValue;

                if(calculation < distanceOfClosestTouchingPoint) {
                    distanceOfClosestTouchingPoint = calculation;
                }
            }
        }

        System.out.println(distanceOfClosestTouchingPoint);

    }

    public static int negativeOrPositiveMultiplier(boolean state) {
        int result = 0;

        if(state) {
            result = 1;
        } else {
            result = -1;
        }

        return result;
    }
}
