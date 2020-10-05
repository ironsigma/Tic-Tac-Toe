import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class BoardPanel extends JPanel implements MouseListener {
    private static final int[] HORIZONTAL_POSITIONS = { 44, 223, 405 };
    private static final int[] VERTICAL_POSITION = { 40, 224, 403 };
    private static final int FIGURE_WIDTH = 110;
    private static final int FIGURE_HEIGHT = 110;

    private Image boardImage;
    private Image[] boardFigures = new Image[9];
    private Image[] catImages = new Image[9];
    private Image[] dogImages = new Image[9];
    private TIC_TAC_TOE game;

    public BoardPanel() {
        initGame();
        initComponents();
    }

    private void initGame() {
        game = new TIC_TAC_TOE();
        game.resetBoard();
    }

    private void initComponents() {
        // load images
        boardImage = getImage("board.png");
        loadPieceImages("cat", catImages);
        loadPieceImages("dog", dogImages);

        // Listen to the mouse
        addMouseListener(this);

        // set panel size
        setPreferredSize(new Dimension(boardImage.getWidth(null), boardImage.getHeight(null)));
    }

    private void loadPieceImages(String name, Image[] images) {
        for (int i = 0; i < 9; ++i) {
            images[i] = getImage(String.format("%s-%02d.png", name, i + 1));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw board
        g.drawImage(boardImage, 0, 0, this);

        paintFigure(g, 0, 0);
        paintFigure(g, 1, 0);
        paintFigure(g, 2, 0);

        paintFigure(g, 0, 1);
        paintFigure(g, 1, 1);
        paintFigure(g, 2, 1);

        paintFigure(g, 0, 2);
        paintFigure(g, 1, 2);
        paintFigure(g, 2, 2);

        // make sure we update the graphics
        Toolkit.getDefaultToolkit().sync();
    }

    void paintFigure(Graphics g, int x, int y) {
        char figure = game.ch[y][x];
        if (figure != 'X' && figure != '0') {
            return;
        }
        g.drawImage(boardFigures[y * 3 + x], HORIZONTAL_POSITIONS[x], VERTICAL_POSITION[y], this);
    }

    private Point clickToSquare(int x, int y) {
        Point square = new Point(-1, -1);
        if (x >= HORIZONTAL_POSITIONS[0] && x <= HORIZONTAL_POSITIONS[0] + FIGURE_WIDTH) {
            square.x = 0;
        } else if (x >= HORIZONTAL_POSITIONS[1] && x <= HORIZONTAL_POSITIONS[1] + FIGURE_WIDTH) {
            square.x = 1;
        } else if (x >= HORIZONTAL_POSITIONS[2] && x <= HORIZONTAL_POSITIONS[2] + FIGURE_WIDTH) {
            square.x = 2;
        }

        if (y >= VERTICAL_POSITION[0] && y <= VERTICAL_POSITION[0] + FIGURE_HEIGHT) {
            square.y = 0;
        } else if (y >= VERTICAL_POSITION[1] && y <= VERTICAL_POSITION[1] + FIGURE_HEIGHT) {
            square.y = 1;
        } else if (y >= VERTICAL_POSITION[2] && y <= VERTICAL_POSITION[2] + FIGURE_HEIGHT) {
            square.y = 2;
        }
        return square;
    }

    private char nextFigure = 'X';

    private boolean takeSquare(Point square) {
        // already something there skip
        if (game.ch[square.y][square.x] == 'X' || game.ch[square.y][square.x] == '0') {
            System.out.printf("Square (%d, %d) already taken!%n",
                    square.x, square.y);
            return false;
        }

        // update the game
        game.ch[square.y][square.x] = nextFigure;

        // pick a random index for an image
        Image symbolImage;
        int randomImageIndex =  (int) (Math.random() * 9);

        // Assign images and swap the figure for next turn
        if (nextFigure == 'X') {
            symbolImage = catImages[randomImageIndex];
            nextFigure = '0';

        } else {
            symbolImage = dogImages[randomImageIndex];
            nextFigure = 'X';
        }

        boardFigures[square.y * 3 + square.x] = symbolImage;

        repaint();
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point square = clickToSquare(mouseEvent.getX(), mouseEvent.getY());
        if (square.x != -1 && square.y != -1 && takeSquare(square)) {
            int check = game.check();
            if (check == 1) {
                JOptionPane.showMessageDialog(this, "Player wins!",
                        "Game Over", JOptionPane.PLAIN_MESSAGE);

            } else if (check == 2) {
                JOptionPane.showMessageDialog(this, "No more moves!",
                        "Game Over", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        /* NO need to handle mouse presses */
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        /* No need to handle button release */
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        /* No need to test for enter */
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        /* No need to test for exit */
    }

    public static Image getImage(String filename) {
        return new ImageIcon("images/" + filename).getImage();
    }
}
