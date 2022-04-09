package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class NewTaskButton extends JButton implements ActionListener {
    private String name;
    private Task task;
    private boolean tmp = false;
    private ExecutorService executorService;

    //GUI
    private JTextArea jTextArea;
    private JPanel jPanel;
    private List<NewTaskButton> list;
    private Font font = new Font(Font.SERIF, Font.ITALIC, 20);

    public NewTaskButton(String name, ExecutorService executorService, JTextArea jTextArea, JPanel jPanel, List<NewTaskButton> list) {
        super(name);
        this.name = name;
        this.executorService = executorService;
        this.jTextArea = jTextArea;
        this.jPanel = jPanel;
        this.list = list;
        task = new Task(name, 1000, this);
        this.addActionListener(this);
        buttonSettings();
    }

    public void buttonSettings() {
        this.setFont(font);
    }

    public void keySettings(JButton jButton) {
        jButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == 'c') {
                    tmp = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                tmp = false;
            }
        });
        if (tmp) {
            this.setText(name + ": Cancelled!");
            task.taskCancel();
            this.setEnabled(false);
        }

    }

    public void threadStatusMethod(String action) {
        if (action.equals(name)) {
            task.taskStart();
            this.setText("Suspend: " + name);
        } else if (action.equals("Suspend: " + name)) {
            task.taskStop();
            this.setText("Continue: " + name);
        } else if (action.equals("Continue: " + name)) {
            task.taskResume();
            this.setText("Suspend: " + name);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String threadChoice = actionEvent.getActionCommand();
        threadStatusMethod(threadChoice);
        keySettings(this);
    }

    //GETERY

    public String getMyName() {
        return name;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public JTextArea getTextArea() {
        return jTextArea;
    }

    public JPanel getPanel() {
        return jPanel;
    }

    public List<NewTaskButton> getList() {
        return list;
    }
}
