import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Balázs on 2017-04-29.
 */
public class Window extends JFrame implements IClickEvent {

    JPanel gamePanel;
    JButton startButton;
    ArrayList<IDrawable> drawableBoardItems;
    ArrayList<IDrawable> drawableTrainItems;


    public Window(String title){
        super(title);
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

        }

    }

}
