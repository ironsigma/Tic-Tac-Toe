import javax.swing.*;
import java.awt.*;

public class TicTacToeGui extends JFrame {
    public TicTacToeGui() {
        initUI();
    }

    private void initUI() {
        BoardPanel boardPanel = new BoardPanel();

        add(boardPanel);

        pack();
        setResizable(false);

        setTitle("Tic-Tac-Toe");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TicTacToeGui app = new TicTacToeGui();
            app.setVisible(true);
        });
    }
}
