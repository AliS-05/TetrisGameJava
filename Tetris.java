package tetris;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Tetris extends JPanel implements KeyListener {

    static int pieceX = 4;
    static int pieceY = 0;
    static int squareWidth, squareHeight;
    static Random rand = new Random();
    static GeneratePiece generatePiece = new GeneratePiece();
    static boolean isFalling = true;
    private Timer timer;
    static int[][] grid = new int[20][10];
    private int pieceType = -1;

    public Tetris() {
        this.setFocusable(true);
        this.addKeyListener(this);

        generateNewPiece();

        timer = new Timer(500, e -> {
            if (isFalling) {
                movePieceDown(); 
                repaint(); 
            }
        });
        timer.start();
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                g.setColor(Color.GRAY);
                g.drawRect(i * squareWidth, j * squareHeight, squareWidth, squareHeight);
                g.setColor(Color.BLACK);
                g.fillRect(i * squareWidth + 1, j * squareHeight + 1, squareWidth - 2, squareHeight - 2);
                //check if piece is present
                if (grid[j][i] != 0) {                    
                    g.setColor(getPieceColor(grid[j][i]));
                    g.fillRect(i * squareWidth + 1, j * squareHeight + 1, squareWidth - 2, squareHeight - 2);
                }
            }
        }
        checkFill(grid, g);
    }

    public static void checkFill(int[][] grid, Graphics g){
        for(int i = 0;i<grid.length;++i){
            boolean rowFilled = true;
            for(int j = 0;j<grid[i].length;++j){
                if(grid[i][j] == 0){
                    //check if row is filled
                    rowFilled = false;
                    break;
                }
            }
            if(rowFilled){
                for(int y = i;y>0;--y){
                    grid[y] = grid[y-1];
                }
                grid[0] = new int[grid[0].length];

                for(int x = 0;x<grid[i].length;++x){
                    g.setColor(Color.BLACK);
                    g.fillRect(x * squareWidth + 1, i * squareHeight + 1, squareWidth - 2, squareHeight - 2);
                }
            }
        }
    }

    private Color getPieceColor(int pieceType) {
        switch (pieceType) {
            case 1: return Color.YELLOW;  // O piece
            case 2: return Color.CYAN;    // I piece
            case 3: return Color.GREEN;   // S piece
            case 4: return Color.RED;     // Z piece
            case 5: return Color.ORANGE;  // L piece
            case 6: return Color.BLUE;    // J piece
            case 7: return Color.MAGENTA; // T piece
            default: return Color.BLACK;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int gridWidth = getWidth();
        int gridHeight = getHeight();
        squareWidth = gridWidth / 10;
        squareHeight = gridHeight / 20;

        drawGrid(g);

        if (pieceType != -1) {
            switch (pieceType) {
                case 1: generatePiece.drawOPiece(g, 0, 0, squareWidth, pieceX, pieceY); break;
                case 2: generatePiece.drawIPiece(g, 0, 0, squareWidth, pieceX, pieceY); break;
                case 3: generatePiece.drawSPiece(g, 0, 0, squareWidth, pieceX, pieceY); break;
                case 4: generatePiece.drawZPiece(g, 0, 0, squareWidth, pieceX, pieceY); break;
                case 5: generatePiece.drawLPiece(g, 0, 0, squareWidth, pieceX, pieceY); break;
                case 6: generatePiece.drawJPiece(g, 0, 0, squareWidth, pieceX, pieceY); break;
                case 7: generatePiece.drawTPiece(g, 0, 0, squareWidth, pieceX, pieceY); break;
            }
        }
    }

    public void generateNewPiece() {
        pieceType = rand.nextInt(7) + 1;  
        pieceX = 4;  
        pieceY = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int newX = pieceX;
        int newY = pieceY;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            int[][] rotatedCoords = GeneratePiece.rotate(pieceType,pieceX,pieceY);
            if (rotatedCoords != null) {
                pieceX = rotatedCoords[0][0];
                pieceY = rotatedCoords[0][1];
            }
            break;
            case KeyEvent.VK_LEFT:
                if (!isCollision(pieceX - 1, pieceY)) {
                    newX = pieceX - 1;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!isCollision(pieceX + 1, pieceY)) {
                    newX = pieceX + 1;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!isCollision(pieceX, pieceY + 1)) {
                    newY = pieceY + 1;
                }
                break;
            default:
                break;
        }

        pieceX = newX;
        pieceY = newY;
        repaint();
    }


    @Override
    public void keyReleased(KeyEvent e) {}

    public void movePieceDown() {
        if (pieceY < 19 && !isCollision(pieceX, pieceY + 1)) {
            pieceY++;
        } else {
            int[][] pieceCoordinates = getPieceCoordinates(pieceX, pieceY);
            for (int[] coord : pieceCoordinates) {
                grid[coord[1]][coord[0]] = pieceType;
            }
            generateNewPiece();
        }
    }

    public boolean isCollision(int newX, int newY) {
        int[][] pieceCoordinates = getPieceCoordinates(newX, newY);
        for (int[] coord : pieceCoordinates) {
            if (coord[0] < 0 || coord[0] >= 10 || coord[1] < 0 || coord[1] >= 20 || grid[coord[1]][coord[0]] != 0) {
                return true;
            }
        }
        return false;
    }

    public int[][] getPieceCoordinates(int pieceX, int pieceY) {
        switch (pieceType) {
            case 1: return GeneratePiece.getOPieceCoordinates(pieceX, pieceY);
            case 2: return GeneratePiece.getIPieceCoordinates(pieceX, pieceY);
            case 3: return GeneratePiece.getSPieceCoordinates(pieceX, pieceY);
            case 4: return GeneratePiece.getZPieceCoordinates(pieceX, pieceY);
            case 5: return GeneratePiece.getLPieceCoordinates(pieceX, pieceY);
            case 6: return GeneratePiece.getJPieceCoordinates(pieceX, pieceY);
            case 7: return GeneratePiece.getTPieceCoordinates(pieceX, pieceY);
            default: return new int[0][0];
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Tetris panel = new Tetris();
        frame.add(panel);
        frame.setSize(400, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
