package algoExpert;

import java.util.ArrayList;
import java.util.List;

public class CalendarMatching {
    public static void main(String[] args) {

        List<List<Double>> firstSchedule = new ArrayList<>();
        List<Double> inner = new ArrayList<>();
        inner.add(0,9.0);
        inner.add(1,10.5);
        firstSchedule.add(inner);
        inner = new ArrayList<>();
        inner.add(0,12.0);
        inner.add(1,13.0);
        firstSchedule.add(inner);
        inner = new ArrayList<>();
        inner.add(0,16.0);
        inner.add(1,18.0);
        firstSchedule.add(inner);

        List<List<Double>> secondSchedule = new ArrayList<>();
        inner = new ArrayList<>();
        inner.add(0,10.0);
        inner.add(1,11.5);
        secondSchedule.add(inner);
        inner = new ArrayList<>();
        inner.add(0,12.5);
        inner.add(1,14.5);
        secondSchedule.add(inner);
        inner = new ArrayList<>();
        inner.add(0,14.5);
        inner.add(1,15.0);
        secondSchedule.add(inner);
        inner = new ArrayList<>();
        inner.add(0,16.0);
        inner.add(1,17.0);
        secondSchedule.add(inner);

        List<Double> hours1 = new ArrayList<>();
        hours1.add(0,9.0);
        hours1.add(1,18.5);

        List<Double> hours2 = new ArrayList<>();
        hours2.add(0,10.0);
        hours2.add(1,18.5);

        double minTime = 0.5;
        printListOfDoubles(getMatchingCalendars(firstSchedule,hours1,secondSchedule,hours2,minTime));
    }

    public static List<List<Double>> getMatchingCalendars(List<List<Double>> firstSchedule,List<Double> hours1,
                                                          List<List<Double>> secondSchedule,List<Double> hours2,
                                                          double minTime){

        List<Double> possibleSchedule = getPossibleSchedule(hours1,hours2);

        List<List<Double>> possibleIntervalsFirst = getIntervalsWhereFree(firstSchedule,possibleSchedule);
        List<List<Double>> possibleIntervalsSecond = getIntervalsWhereFree(secondSchedule,possibleSchedule);

        List<List<Double>> possibleIntervalsIntersected = getPossibleIntervalsIntersected(possibleIntervalsFirst,
                possibleIntervalsSecond);

        return getFinalCalendar(possibleIntervalsIntersected,possibleSchedule,minTime);
    }

    private static List<List<Double>> getFinalCalendar(List<List<Double>> possibleIntervalsIntersected,
                                                       List<Double> possibleSchedule,double minTime){

        for(int i = 0; i < possibleIntervalsIntersected.size(); i++){

            List<Double> currentInterval = possibleIntervalsIntersected.get(i);
            double start = currentInterval.get(0);
            double finish = currentInterval.get(1);

            double difference = finish - start;

            if(difference < minTime){
                possibleIntervalsIntersected.remove(currentInterval);
            }
        }

        return possibleIntervalsIntersected;
    }

    private static List<List<Double>> getPossibleIntervalsIntersected(List<List<Double>> possible1,List<List<Double>> possible2){

        List<List<Double>> possibleInterval;

        int s1 = possible1.size();
        int s2 = possible2.size();

        int minSize = Math.min(s1,s2);

        if(minSize == s1){
            possibleInterval = getPossibleIntersection(possible1,possible2);
        } else {
            possibleInterval = getPossibleIntersection(possible2,possible1);
        }

        return possibleInterval;
    }

    private static List<List<Double>> getPossibleIntersection(List<List<Double>> possible1,List<List<Double>> possible2){

        List<List<Double>> possibleInterval = new ArrayList<>();

        for(int i = 0; i < possible1.size(); i++){
            List<Double> inner = new ArrayList<>();

            List<Double> c1 = possible1.get(i);
            List<Double> c2 = possible2.get(i);

            double st1 = c1.get(0);
            double fin1 = c1.get(1);

            double st2 = c2.get(0);
            double fin2 = c2.get(1);

            if(st1 <= st2){
                if(fin1 > st2){
                    inner.add(st2);
                    inner.add(fin1);

                    possibleInterval.add(inner);
                }
            } else if(st2 < st1){
                if(fin2 > st1){
                    inner.add(st1);
                    inner.add(fin2);

                    possibleInterval.add(inner);
                }
            }
        }


        for(int i = possible1.size(); i < possible2.size(); i++){

            List<Double> inner = new ArrayList<>();
            List<Double> current = possible2.get(i);

            inner.add(current.get(0));
            inner.add(current.get(1));

            possibleInterval.add(inner);
        }

        return possibleInterval;
    }

    private static List<List<Double>> getIntervalsWhereFree(List<List<Double>> schedule, List<Double> hours){

        List<List<Double>> possibleInterval = new ArrayList<>();

        double actualFinish = hours.get(1);

        for(int i = 0; i < schedule.size() -1; i++){

            List<Double> inner = new ArrayList<>();

            List<Double> currentInterval = schedule.get(i);
            List<Double> nextInterval = schedule.get(i +1);

            double finish1 = currentInterval.get(1);
            double start2 = nextInterval.get(0);

            if(finish1 < start2){
                inner.add(finish1);
                inner.add(start2);

                possibleInterval.add(inner);
                inner = new ArrayList<>();
            }

            if(i + 1 == schedule.size() -1){
                double finish2 = nextInterval.get(1);

                if(finish2 < actualFinish){
                    inner.add(finish2);
                    inner.add(actualFinish);

                    possibleInterval.add(inner);
                }
            }

        }

        return possibleInterval;
    }

    private static List<Double> getPossibleSchedule(List<Double> hours1,List<Double> hours2){

        List<Double> possibleSchedule = new ArrayList<>(2);
        double startHour = Math.max(hours1.get(0),hours2.get(0));
        double finishHour = Math.min(hours1.get(1),hours2.get(1));

        possibleSchedule.add(startHour);
        possibleSchedule.add(finishHour);

        return possibleSchedule;
    }

    private static void printListOfDoubles(List<List<Double>> list){

        for(List<Double> l : list){
            printList(l);
        }
    }

    private static void printList(List<Double> list) {
        int count = 0;
        for (double i : list) {
            if(count % 2 != 0){
                System.out.println(i + " ");
            } else {
                System.out.print(i + "-");
            }
            count++;
        }
    }
}
