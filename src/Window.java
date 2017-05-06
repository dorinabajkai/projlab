import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by Balázs on 2017-04-29.
 */
public class Window extends JFrame implements IClickEvent {

    JPanel gamePanel;
    JButton startButton;
    ArrayList<IDrawable> drawableBoardItems;
    ArrayList<IDrawable> drawableTrainItems;
    Board board;


    public Window(String title, Board b){
        super(title);
        board = b;
        this.setSize(1000,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new FlowLayout());
        gamePanel = new JPanel();
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        startButton = new JButton("Start game");
        startButton.addActionListener( new StartNewGameActLis() );
        add(startButton);
        add(gamePanel);
        drawableBoardItems = new ArrayList<IDrawable>();
        drawableTrainItems = new ArrayList<IDrawable>();



    }

    public void drawAllItems(){
        gamePanel.removeAll();
        for(IDrawable iter : drawableBoardItems){
            iter.draw();
        }
        for(IDrawable iterator : drawableTrainItems){
            iterator.draw();
        }
        gamePanel.updateUI();
    }

    public void addDrawableBoardItem(IDrawable bi){
        drawableBoardItems.add(bi);
    }

    public void addDrawableTrainItem(IDrawable ti){
        drawableTrainItems.add(ti);
    }



    public void action(){

    }

    public class StartNewGameActLis implements ActionListener {

        public void actionPerformed(ActionEvent e){
            Controller controller = new Controller(board);
            controller.start();

        }

    }

    public class MouseClick implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
