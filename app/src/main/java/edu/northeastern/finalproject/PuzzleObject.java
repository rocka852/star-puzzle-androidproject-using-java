package edu.northeastern.finalproject;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
public class PuzzleObject {
    public int [][] arr;
    public int row;
    public int col;
    public PuzzleObject(int [][] arr) {
        this.arr = arr;
        this.row = -1;
        this.col = -1;
    }


    public boolean switchTo(int a) {
        if(nextTo(a)) {
            System.out.println("Switch to: " + a);
            int [] index_0 = index(0);
            int [] index_a = index(a);

            this.arr[index_0[0]][index_0[1]] = a;
            this.arr[index_a[0]][index_a[1]] = 0;
            return true;
        }
        else return false;
    }
    public boolean nextTo(int a) {
        int row0 = -1;
        int col0 = -1;
        for(int i = 0; i < this.arr.length; i++)
            for(int j = 0; j < this.arr[i].length; j++) {
                if (arr[i][j] == a) {
                    this.row = i;
                    this.col = j;
                }
                if (arr[i][j] == 0) {
                    row0 = i;
                    col0 = j;
                }
            }
        return Math.abs(this.row - row0) + Math.abs(this.col - col0) == 1;
    }

    public int [] index (int a) {
        int [] temp = new int[2];
        for (int i = 0; i < this.arr.length; i++)
            for(int j = 0; j < this.arr[i].length; j++) {
                if (this.arr[i][j] == a) {
                    temp[0] = i;
                    temp[1] = j;
                }
            }
        return temp;
    }

    public int [][] getArray() {
        return this.arr;
    }

    public String toString() {
        String text = "";
        for(int i = 0; i < this.arr.length; i++) {
            text += Arrays.toString(this.arr[i]);
            text += "\n";
        }
        return text;
    }

    public boolean equals(int [][] others) {
        return Arrays.deepEquals(this.arr, others);
    }

    public boolean shuffle() {
        ArrayList <Integer> temp = new ArrayList<>();

        for (int i = 0; i < 9; i++)
            temp.add(i);
        Collections.shuffle(temp);

        for (int i = 0; i < this.arr.length; i ++) {
            for (int j = 0; j < this.arr[i].length; j++){
                this.arr[i][j] = temp.get(i * 3 + j);
            }

        }
        return true;
    }

    public boolean cheat() {
        for(int i = 0; i < this.arr.length; i ++)
            for(int j = 0; j < this.arr[i].length; j++) {
                this.arr[i][j] = i * 3 + j;
            }
        this.arr[0][0] = 1;
        this.arr[0][1] = 0;
        return true;
    }

}
