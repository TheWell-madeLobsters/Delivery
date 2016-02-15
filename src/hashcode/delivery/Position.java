/*
 * The MIT License
 *
 * Copyright 2016 The Well-made Lobsters.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package hashcode.delivery;

/**
 * Positions class.
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

    public Position(int coordRow, int coordColumn) {
        this.row = coordRow;
        this.column = coordColumn;
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

    /**
     * Computes the Euclidean distance between the specified positions.
     *
     * @param a first position
     * @param b second position
     * @return the Euclidean distance between positions a and b
     */
    public static int getDistance(Position a, Position b) {
        double result = Math.sqrt(Math.pow((a.getRow() - b.getRow()), 2) + Math.pow((a.getColumn()
                - b.getColumn()), 2));
        return (int) Math.ceil(result);
    }

}
