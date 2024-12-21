package tetris;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GeneratePiece {
    static Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("generatePiece called successfully.");
    }

    public int generatePiece() {
        return rand.nextInt(7) + 1;
    }

    public void drawOPiece(Graphics g, int paddingLeft, int paddingTop, int squareSize, int pieceX, int pieceY) {
        g.setColor(Color.YELLOW);
        g.fillRect(paddingLeft + pieceX * squareSize, paddingTop + pieceY * squareSize, squareSize, squareSize);
        g.fillRect(paddingLeft + (pieceX + 1) * squareSize, paddingTop + pieceY * squareSize, squareSize, squareSize);
        g.fillRect(paddingLeft + pieceX * squareSize, paddingTop + (pieceY + 1) * squareSize, squareSize, squareSize);
        g.fillRect(paddingLeft + (pieceX + 1) * squareSize, paddingTop + (pieceY + 1) * squareSize, squareSize, squareSize);
    }

    public void drawIPiece(Graphics g, int paddingLeft, int paddingTop, int squareSize, int pieceX, int pieceY) {
        g.setColor(Color.CYAN);
        for (int i = 0; i < 4; i++) {
            g.fillRect(paddingLeft + pieceX * squareSize, paddingTop + (pieceY + i) * squareSize, squareSize, squareSize);
        }
    }

    public void drawSPiece(Graphics g, int paddingLeft, int paddingTop, int squareSize, int pieceX, int pieceY) {
        g.setColor(Color.GREEN);
        g.fillRect(paddingLeft + pieceX * squareSize, paddingTop + pieceY * squareSize, squareSize, squareSize);
        g.fillRect(paddingLeft + (pieceX + 1) * squareSize, paddingTop + pieceY * squareSize, squareSize, squareSize);
        g.fillRect(paddingLeft + pieceX * squareSize, paddingTop + (pieceY + 1) * squareSize, squareSize, squareSize);
        g.fillRect(paddingLeft + (pieceX - 1) * squareSize, paddingTop + (pieceY + 1) * squareSize, squareSize, squareSize);
    }
    
    public void drawZPiece(Graphics g, int paddingLeft, int paddingTop, int squareSize, int pieceX, int pieceY) {
        g.setColor(Color.RED);
        g.fillRect(paddingLeft + pieceX * squareSize, paddingTop + pieceY * squareSize, squareSize, squareSize);
        g.fillRect(paddingLeft + (pieceX + 1) * squareSize, paddingTop + pieceY * squareSize, squareSize, squareSize);
        g.fillRect(paddingLeft + (pieceX + 1) * squareSize, paddingTop + (pieceY + 1) * squareSize, squareSize, squareSize);
        g.fillRect(paddingLeft + (pieceX + 2) * squareSize, paddingTop + (pieceY + 1) * squareSize, squareSize, squareSize);
    }
    

    public void drawLPiece(Graphics g, int paddingLeft, int paddingTop, int squareSize, int pieceX, int pieceY) {
        g.setColor(Color.YELLOW);
        for (int i = 0; i < 3; i++) {
            g.fillRect(paddingLeft + (pieceX) * squareSize, paddingTop + (pieceY + i) * squareSize, squareSize, squareSize);
        }
        g.fillRect(paddingLeft + (pieceX + 1) * squareSize, paddingTop + (pieceY + 2) * squareSize, squareSize, squareSize);
    }

    public void drawJPiece(Graphics g, int paddingLeft, int paddingTop, int squareSize, int pieceX, int pieceY) {
        g.setColor(Color.BLUE);
        for (int i = 0; i < 3; i++) {
            g.fillRect(paddingLeft + (pieceX) * squareSize, paddingTop + (pieceY + i) * squareSize, squareSize, squareSize);
        }
        g.fillRect(paddingLeft + (pieceX - 1) * squareSize, paddingTop + (pieceY + 2) * squareSize, squareSize, squareSize);
    }

    public void drawTPiece(Graphics g, int paddingLeft, int paddingTop, int squareSize, int pieceX, int pieceY) {
        g.setColor(Color.MAGENTA);
        g.fillRect(paddingLeft + (pieceX) * squareSize, paddingTop + (pieceY) * squareSize, squareSize, squareSize);
        g.fillRect(paddingLeft + (pieceX - 1) * squareSize, paddingTop + (pieceY + 1) * squareSize, squareSize, squareSize);
        g.fillRect(paddingLeft + (pieceX) * squareSize, paddingTop + (pieceY + 1) * squareSize, squareSize, squareSize);
        g.fillRect(paddingLeft + (pieceX + 1) * squareSize, paddingTop + (pieceY + 1) * squareSize, squareSize, squareSize);
    }




    public static int[][] getOPieceCoordinates(int pieceX, int pieceY){
        return new int[][]{
            {pieceX,pieceY}, {pieceX + 1,pieceY},
            {pieceX,pieceY + 1}, {pieceX + 1,pieceY + 1}
        };
    }

    public static int[][] getIPieceCoordinates(int pieceX, int pieceY){
        return new int[][]{
            {pieceX,pieceY},{pieceX,pieceY+1},{pieceX,pieceY + 2},{pieceX,pieceY + 3}
        };
    }

    public static int[][] getJPieceCoordinates(int pieceX, int pieceY) {
        return new int[][]{
            {pieceX, pieceY}, {pieceX, pieceY+1}, {pieceX, pieceY + 2},
                            {pieceX - 1, pieceY + 2}
        };
    }
    
    public static int[][] getLPieceCoordinates(int pieceX, int pieceY) {
        return new int[][]{
            {pieceX, pieceY},
            {pieceX, pieceY+1},
            {pieceX, pieceY+2},
            {pieceX + 1, pieceY + 2}
        };
    }
    
    public static int[][] getSPieceCoordinates(int pieceX, int pieceY) {
        return new int[][]{
            {pieceX, pieceY}, {pieceX + 1, pieceY},
            {pieceX, pieceY + 1}, {pieceX - 1, pieceY + 1}
        };
    }
    
    public static int[][] getZPieceCoordinates(int pieceX, int pieceY) {
        return new int[][]{
            {pieceX, pieceY}, {pieceX + 1, pieceY},
            {pieceX + 1, pieceY + 1}, {pieceX + 2, pieceY + 1}
        };
    }
    
    public static int[][] getTPieceCoordinates(int pieceX, int pieceY) {
        return new int[][] {
            {pieceX, pieceY},          
            {pieceX - 1, pieceY + 1}, 
            {pieceX, pieceY + 1},      
            {pieceX + 1, pieceY + 1}   
        };
    }

    public static int[][] rotate(int piece, int pieceX, int pieceY) {
        int[][] coords = new int[4][2];
        switch (piece) {
            case 1: 
                coords = getOPieceCoordinates(pieceX, pieceY);
                break;
            case 2: 
                coords = getIPieceCoordinates(pieceX, pieceY);
                for (int i = 0; i < coords.length; i++) {
                    int temp = coords[i][0];
                    coords[i][0] = -coords[i][1] + pieceX + 1;
                    coords[i][1] = temp - pieceY + 1;
                }
                break;
            case 3:
                coords = getSPieceCoordinates(pieceX, pieceY);
                // Implement rotation logic for the S piece
                break;
            case 4: 
                coords = getZPieceCoordinates(pieceX, pieceY);
                // Implement rotation logic for the Z piece
                break;
            case 5: 
                coords = getLPieceCoordinates(pieceX, pieceY);
                // Implement rotation logic for the L piece
                break;
            case 6: 
                coords = getJPieceCoordinates(pieceX, pieceY);
                // Implement rotation logic for the J piece
                break;
            case 7: 
                coords = getTPieceCoordinates(pieceX, pieceY);
                // Implement rotation logic for the T piece
                break;
        }
        return coords;
    }
    
    


}
