/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode.delivery;

/**
 *
 * @author Marco Terrinoni
 */
public class Position {

    private int row;
    private int column;

    public Position() {
        row = 0;
        column = 0;
    }

    public Position(int coordX, int coordY) {
        this.row = coordX;
        this.column = coordY;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean equals(Position position) {
        return this.row == position.row && this.column == position.column;
    }

    public int getDistance(Position position) {
        return getDistance(this, position);
    }

    public static int getDistance(Position a, Position b) {
        double result = Math.sqrt(Math.pow((a.getRow() - b.getRow()), 2) + Math.pow((a.getColumn()
                - b.getColumn()), 2));
        return (int) Math.ceil(result);
    }

}
