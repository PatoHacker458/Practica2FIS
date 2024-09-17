package org.example.practica2fis.Models;

import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public class Compound
{
    private List<List<Seat>> seatsMatrix = new ArrayList<>();
    private int nrows, ncols;

    public Compound(int nrows, int ncols) { //6 rows and 4 cols in this case
        this.nrows = nrows;
        this.ncols = ncols;

        for (int i = 0; i < this.nrows; i++) {
            List<Seat> list = new ArrayList<>();
            for (int j = 0; j < this.ncols; j++) {
                Seat seat = new Seat(i < 2 ? 'A' : i < 4 ? 'B' : 'C', i * 4 + j + 1);
                list.add(seat);
            }
            this.seatsMatrix.add(list);
        }
    }

    public boolean checkAvailability(int index)
    {
        int[] indexes = convertIndex(index);
        return this.seatsMatrix.get(indexes[0]).get(indexes[1]).isAvailable();
    }

    public String seatInfo(int index)
    {
        int[] indexes = convertIndex(index);
        return this.seatsMatrix.get(indexes[0]).get(indexes[1]).toString();
    }

    public void selectSeat(int index){
        int[] indexes = convertIndex(index);
        this.seatsMatrix.get(indexes[0]).get(indexes[1]).getCircle().setStroke(Paint.valueOf("YELLOW"));
    }

    public void buySeat(int index)
    {
        int[] indexes = convertIndex(index);
        this.seatsMatrix.get(indexes[0]).get(indexes[1]).setAvailable(false);
        this.seatsMatrix.get(indexes[0]).get(indexes[1]).getCircle().setStroke(Paint.valueOf("GRAY"));
    }

    private int[] convertIndex(int index){
        index--;
        return new int[]{index / this.ncols, index % this.ncols};
    }

}
