package com.JavaProblems;

import java.util.Scanner;

public class maximumArea {
//    You are given N points on a 2D plane having an x-coordinate and y-coordinate of the type (x,y).
//    You need to find 4 points from the given N points such that these four points make a square.
//    Also, the sides should be parallel to x-axis and y-axis.
//    If there are multiple such points, the selected square should be the one with greater length.
//    If there are still multiple choices of such points, choose those points in which the bottom left point has a lower y coordinate
//
//    Input Format
//
//    The first line contains a single integer N, denoting the number of points on the plane.
//
//    Each of the next lines contain two space separated integers x and y, these denote the x and y coordinates of the point.
//
//    Constraints
//
//    1 <= n <= 1000
//
//    1 <= x,y <= 109
//
//    Output Format
//
//    Print two space separated integers, the coordinates of the bottom left point of the square found. If there are no squares present, print -1.

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] points = new int[n][2];
        for(int i = 0; i<n; i++){
            points[i][0] = sc.nextInt();
            points[i][1] = sc.nextInt();
        }

        int[] result = findSquare(points);
        if(result[0] == -1 && result[1]  == -1){
            System.out.println(-1);
        }
        else{
            System.out.println(result[0] + " " + result[1]);
        }
    }

//    I still had to adjust some things in the main method to take inputs correctly, but this is still is awesome.
//    A bit scary too😗. I'll first do the questions myself, and then again with copilot and compare😁.

    public static int[] findSquare(int[][] points){
        int[] result = new int[2];
        int max = 0;
        for(int i = 0; i < points.length; i++){
            for(int j = i + 1; j < points.length; j++){
                for(int k = j + 1; k < points.length; k++){
                    for(int l = k + 1; l < points.length; l++){
                        if(isSquare(points[i], points[j], points[k], points[l])){
                            int area = findArea(points[i], points[j], points[k]);
                            if(area > max){
                                max = area;
                                result[0] = points[i][0];
                                result[1] = points[i][1];
                            }
                        }
                    }
                }
            }
        }
        if(max == 0){
            result[0] = -1;
            result[1] = -1;
        }
        return result;
    }

    private static int findArea(int[] point, int[] point1, int[] point2) {
        int x1 = point[0];
        int y1 = point[1];
        int x2 = point1[0];
        int y2 = point1[1];
        int x3 = point2[0];
        int y3 = point2[1];
        int side1 = (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        int side2 = (int) Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        return side1 * side2;
    }

    public static boolean isSquare(int[] p1, int[] p2, int[] p3, int[] p4){
        int d1 = findDistance(p1, p2);
        int d2 = findDistance(p1, p3);
        int d3 = findDistance(p1, p4);
        if(d1 == d2 && 2 * d1 == d3){
            int d = findDistance(p2, p4);
            return (d == findDistance(p3, p4) && d == d1);
        }
        if(d2 == d3 && 2 * d2 == d1){
            int d = findDistance(p2, p3);
            return (d == findDistance(p2, p4) && d == d2);
        }
        if(d3 == d1 && 2 * d3 == d2){
            int d = findDistance(p3, p4);
            return (d == findDistance(p2, p3) && d == d3);
        }
        return false;
    }

    public static int findDistance(int[] p1, int[] p2){
        return (int) (Math.pow((p1[0] - p2[0]), 2) + Math.pow((p1[1] - p2[1]), 2));
    }
}
