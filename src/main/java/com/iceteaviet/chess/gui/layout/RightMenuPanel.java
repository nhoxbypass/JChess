package main.java.com.iceteaviet.chess.gui.layout;

import main.java.com.iceteaviet.chess.gui.UIConstants;
import main.java.com.iceteaviet.chess.gui.UIUtils;
import main.java.com.iceteaviet.chess.gui.view.Chronometer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Genius Doan on 6/14/2017.
 */
public class RightMenuPanel extends JPanel {
    Chronometer chronometerA = new Chronometer("Player A");
    Chronometer chronometerB = new Chronometer("Player B");

    GameHistoryPanel gameHistoryPanel;
    ChatPanel chatPanel;
    JButton btnPause;

    public RightMenuPanel() {
        super();
        setLayout(new GridLayout(3, 0));

        setSize(UIConstants.RIGHT_GAME_PANEL_DIMENSION);
        this.setBorder(new EmptyBorder(8, 8, 8, 8));

        JPanel timerPanel = new JPanel();
        timerPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 4));
        timerPanel.setBorder(new EmptyBorder(8, 0, 8, 0));
        chronometerA.setBorder(new EmptyBorder(8, 8, 8, 4));
        chronometerB.setBorder(new EmptyBorder(8, 0, 8, 8));
        timerPanel.add(chronometerA);
        timerPanel.add(chronometerB);

        btnPause = new JButton("Pause Game");
        btnPause.setHorizontalAlignment(SwingConstants.CENTER);
        btnPause.setBackground(UIConstants.PRIMARY_BG_COLOR);
        btnPause.setForeground(Color.WHITE);
        UIUtils.setEmptyBorder(btnPause, 4, 20, 4, 20);
        try {
            ImageIcon icon = UIUtils.getScaledIconFromResources("pause.png", 24, 24);
            btnPause.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        timerPanel.add(btnPause);

        this.gameHistoryPanel = new GameHistoryPanel();
        gameHistoryPanel.setBorder(new EmptyBorder(8, 0, 8, 0));


        chatPanel = new ChatPanel();

        add(timerPanel);
        add(gameHistoryPanel);
        add(chatPanel);
    }

    public GameHistoryPanel getGameHistoryPanel() {
        return gameHistoryPanel;
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }
}