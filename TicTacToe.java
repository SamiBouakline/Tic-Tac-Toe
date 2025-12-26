import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class tic_tac_toe implements ActionListener {
    JFrame frame;
    boolean xTurn=true;
    JButton[] buttons = new JButton[9];
    JLabel msg = new JLabel("");
    boolean win = false;
    
    public TicTacToe() {
        //Title Bar
        JLabel title = new JLabel("Tic-Tac-Toe!");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel instructions = new JLabel ("Click on a Square to move");
        instructions.setFont(new Font("Arial", Font.PLAIN, 10));
        instructions.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel titleBar = new JPanel();
        titleBar.setLayout (new BorderLayout());
        titleBar.add(title, BorderLayout.NORTH);
        titleBar.add(instructions, BorderLayout.CENTER);
        titleBar.setBackground(Color.red);
        
        
        //Button Board
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(3,3));
        board.setBackground(Color.red);
        for (int i = 0; i< buttons.length; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 30));
            buttons[i].addActionListener(this);
            board.add(buttons[i]);
        }
        board.setBorder(new EmptyBorder(10,10,10,10));
        
        //waste of space - no longer needed due to empty boarder libary
        /*
        JPanel westWaste = new JPanel();
        westWaste.setSize(50, 400);
        westWaste.setBackground(Color.orange);
        
        JPanel eastWaste = new JPanel();
        eastWaste.setSize(50, 400);
        eastWaste.setBackground(Color.orange);
        */  
        //application frame
        JFrame.setDefaultLookAndFeelDecorated (true);
        frame = new JFrame();
        frame.setSize(350, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.add(titleBar, BorderLayout.NORTH);
        frame.add(board, BorderLayout.CENTER);
        frame.add(msg, BorderLayout.SOUTH);
        //frame.add(westWaste, BorderLayout.WEST);
        //frame.add(eastWaste, BorderLayout.EAST);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
    {
        JButton button = (JButton) e.getSource();
        if (xTurn) {
            button.setText("X");
        } 
        else {
            button.setText("O");
        }
        button.setEnabled(false);

        checkForWinner();
        xTurn = !xTurn;
        
        if (xTurn)
        {
            msg.setText("X's Turn");
        }
        else
        {
            msg.setText("O's Turn");
        }
        
        //check for tie
        int count = 0;
        for (int i=0;i<9;i++)
        {
            if (!buttons[i].isEnabled())
            {
                count ++;
            }
        }
        
        if ((count == 9) && (win == false))
        {
            JOptionPane.showMessageDialog(frame, "It's a tie!");
            resetGame();
        }
        
    }

    public void checkForWinner() {
        
        //check all three rows for a winner
        for (int i=0;i<9;i=i+3)
        {  
            if (!buttons[i].getText().equals("") && buttons[i].getText().equals(buttons[i+1].getText()) && (buttons[i].getText().equals(buttons[i+2].getText())))
            {
                win = true;
                JOptionPane.showMessageDialog(frame, buttons[i].getText() + " WINS!!!!");
                resetGame();
            }
        }//end check rows
        
        //check cols
        for (int i=0;i<3;i++)
        {  
            if (!buttons[i].getText().equals("") && buttons[i].getText().equals(buttons[i+3].getText()) && (buttons[i].getText().equals(buttons[i+6].getText())))
            {
                win = true;
                JOptionPane.showMessageDialog(frame, buttons[i].getText() + " WINS!!!!");
                resetGame();
            }
        }//end check cols
        
        //check diagonal left to right
        if (!buttons[0].getText().equals("") && buttons[0].getText().equals(buttons[0+4].getText()) && (buttons[0].getText().equals(buttons[0+8].getText())))
        {
            win = true;
            JOptionPane.showMessageDialog(frame, buttons[i].getText() + " WINS!!!!");
            resetGame();
        }//end check diagonal left to right
        
        //check diagonal right to left
        if (!buttons[2].getText().equals("") && buttons[2].getText().equals(buttons[2+2].getText()) && (buttons[2].getText().equals(buttons[2+4].getText())))
        {
            win = true;
            JOptionPane.showMessageDialog(frame, buttons[i].getText() + " WINS!!!!");
            resetGame();
        }//end check diagonal right to left
        
    }

    public void resetGame() {
      win = false;
        for (int i=0;i<buttons.length;i++){
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}