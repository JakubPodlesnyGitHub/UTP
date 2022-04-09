package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainWindow extends JFrame implements ActionListener {
    private JButton stopButton, createNewJButton;
    private JPanel jPanelNorth, jPanelCentre, jPanelSouth;
    private JTextArea jTextAreaCentre;
    private int counter = 1;
    private List<NewTaskButton> list;
    private ExecutorService executorService;
    private Font font;
    private Color color;

    public MainWindow() {
        createObjectsSWING();
        settingsWindow();
        createPanelNorth();
        createPanelCentre();
        createMainWindow();
        addActionListeners();
    }

    private void createObjectsSWING() {
        font = new Font(Font.SERIF, Font.ITALIC, 20);
        color = new Color(0xFFB500);
        stopButton = new JButton("STOP");
        stopButton.setFont(font);
        stopButton.setForeground(color);
        stopButton.setBackground(new Color(0x8203FF));
        createNewJButton = new JButton("CREATE NEW");
        createNewJButton.setFont(font);
        createNewJButton.setForeground(color);
        createNewJButton.setBackground(new Color(0x8203FF));
        jPanelNorth = new JPanel(new BorderLayout());
        jPanelSouth = new JPanel(new GridLayout(3, 0));
        jPanelCentre = new JPanel(new BorderLayout());
        jTextAreaCentre = new JTextArea(100, 70);
        jTextAreaCentre.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        jTextAreaCentre.setEditable(false);
        list = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(3);
    }

    private void settingsWindow() {
        this.setIconImage(new ImageIcon("images.png").getImage());
        this.pack();
        this.validate();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("GLOWNE OKNO DO TWORZENIA THREDOW");
        this.setVisible(true);
    }

    private void createPanelNorth() {
        jPanelNorth.add(createNewJButton, BorderLayout.SOUTH);
        jPanelNorth.add(stopButton, BorderLayout.NORTH);
    }

    private void createPanelCentre() {
        jPanelCentre.add(new JScrollPane(jTextAreaCentre));
    }

    private void createMainWindow() {
        this.add(jPanelNorth, BorderLayout.NORTH);
        this.add(jPanelCentre, BorderLayout.CENTER);
        this.add(jPanelSouth, BorderLayout.SOUTH);
    }

    private void addActionListeners() {
        createNewJButton.addActionListener(this);
        stopButton.addActionListener(this);
    }

    //////////////////////////////////////////////////////////////
    private void stopMethod() {
        executorService.shutdownNow();
        list.forEach(button -> {
            button.setText(button.getMyName() + ": DONE!");
            button.setEnabled(false);
            createNewJButton.setEnabled(false);
            stopButton.setEnabled(false);
        });
    }

    private void createNewMethod(NewTaskButton threadButton) {
        jPanelSouth.add(threadButton);
        list.add(threadButton);
        this.validate();
        counter++;
    }
    /////////////////////////////////////////////////////////////

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ////////////////////////////////////DANE////////////////////////////////////
        String mainChoice = actionEvent.getActionCommand();
        String name;
        NewTaskButton newTASKButton;
        ///////////////////
        if (mainChoice.equals("STOP")) {
            stopMethod();
        } else if (mainChoice.equals("CREATE NEW")) {
            name = "Thread " + counter;
            newTASKButton = new NewTaskButton(name, executorService, jTextAreaCentre, jPanelSouth, list);
            createNewMethod(newTASKButton);
        }
    }
}
