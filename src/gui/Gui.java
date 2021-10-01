package gui;

import model.Item;
import model.ItemList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Gui extends JFrame implements ActionListener {
    public JFrame frame;
    public JPanel panel;
    public JLabel menuLabel;
    public JButton addButton;
    public JButton getButton;
    public JButton deleteButton;
    public JButton backButton;
    public JButton enterButton;
    public JTextField addTextField;
    public JTextField deleteTextField;

    public JButton deleteEnterButton;
    public JButton deleteBackButton;

    public ItemList itemList;


    // MODIFIES: this
    // EFFECTS: create a new graphical interface
    public Gui() {
        init();
        makeFrame();
        makePanel();
        makeMenuButtons();
    }

    // MODIFIES: this
    // EFFECTS: initiate a list of items
    public void init() {
        itemList = new ItemList();
    }

    // MODIFIES: this
    // EFFECTS: create the Jframe
    public void makeFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(900, 700);
        frame.setLayout(null);
        frame.setVisible(true);

        menuLabel = new JLabel("Random Choice Generator");
        menuLabel.setBounds(505, 70, 400, 50);
        menuLabel.setFont(new Font("Helvetica", Font.PLAIN, 30));
        frame.add(menuLabel);

    }

    // MODIFIES: this
    // EFFECTS: create item display panel
    public void makePanel() {
        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBounds(20, 50, 400, 600);
        frame.add(panel);
        panel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: update item display panel
    public void refreshPanel() {
        panel.removeAll();
            for (Map.Entry<String, Item> entry : itemList.getList().entrySet()) {
                JLabel label = new JLabel(" " + entry.getKey() + "." + " " + entry.getValue().name);
                label.setForeground(Color.CYAN);
                panel.add(label);
            }
    }

    // MODIFIES: this
    // EFFECTS: create menu buttons
    public void makeMenuButtons() {
        addButton = new JButton();
        getButton = new JButton();
        deleteButton = new JButton();

        addButton.setBounds(650, 300, 70, 30);
        getButton.setBounds(650, 200, 70, 30);
        deleteButton.setBounds(650, 400, 70, 30);

        createButton(addButton, "Add");
        createButton(getButton, "Get");
        createButton(deleteButton, "Delete");

        addButton.addActionListener(this);
        getButton.addActionListener(this);
        deleteButton.addActionListener(this);

        backButton = new JButton();
        createButton(backButton, "Back");
        backButton.setBounds(415, 400, 70, 30);
        backButton.addActionListener(this);
        backButton.setVisible(false);

        enterButton = new JButton();
        createButton(enterButton, "Enter");
        enterButton.setBounds(415, 300, 70, 30);
        enterButton.addActionListener(this);
        enterButton.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: modify buttons
    public void createButton(JButton button, String s) {
        button.setForeground(Color.red);
        button.setText(s);
        frame.add(button);
        button.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: process user input
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addResult();
        }
        if (e.getSource() == getButton) {
            getResult();
        }
        if (e.getSource() == deleteButton) {
            deleteResult();
        }
        if (e.getSource() == backButton) {
            returnToMenu();
        }
        if (e.getSource() == enterButton) {
            String s = addTextField.getText();
            itemList.addItem(s);
            returnToMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: refreshes menu page
    public void returnToMenu() {
        frame.getContentPane().removeAll();
        revalidate();
        frame.setLayout(null);
        frame.add(addButton);
        frame.add(getButton);
        frame.add(deleteButton);
        frame.add(menuLabel);
        frame.add(backButton);
        frame.add(enterButton);
        refreshPanel();
        frame.add(panel);
        panel.setVisible(true);
        setButtonsVisible();
        backButton.setVisible(false);
        enterButton.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: delete user input item
    public void deleteResult() {
        setButtonsInvisible();
        backButton.setVisible(false);

        deleteTextField = new JTextField();
        deleteTextField.setBounds(600 , 200, 200, 50);
        frame.add(deleteTextField);

        JLabel label = new JLabel("Please Enter the Item Index");
        label.setBounds(600, 150, 500, 50);
        frame.add(label);

        deleteBackButton = new JButton();
        deleteEnterButton = new JButton();

        deleteEnterButton.setBounds(650, 300, 70, 30);
        deleteBackButton.setBounds(650, 400, 70, 30);

        createButton(deleteBackButton, "Back");
        createButton(deleteEnterButton, "Enter");

        deleteBackButton.addActionListener(this::deleteButtonAction);
        deleteEnterButton.addActionListener(this::deleteButtonAction);

    }

    // MODIFIES: this
    // EFFECTS: process user input
    public void deleteButtonAction(ActionEvent e) {
        if (e.getSource() == deleteEnterButton) {
            try {
                String s = deleteTextField.getText();
                itemList.deleteItem(s);
                panel.setVisible(false);
                panel.setVisible(true);
                returnToMenu();
            } catch (NumberFormatException exception) {
                panel.setVisible(false);
                returnToMenu();
            }
        }
        if (e.getSource() == deleteBackButton) {
            returnToMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: generate random result
    public void getResult() {
        panel.setVisible(false);
        setButtonsInvisible();
        backButton.setVisible(true);

        frame.setLayout(new BorderLayout());

        if (itemList.getList().size() != 0) {
            int i = getRandomIndex();
            String s = itemList.list.get(String.valueOf(i)).name;
            JLabel label = new JLabel(s, JLabel.CENTER);
            label.setAlignmentX(0);
            label.setAlignmentY(0);
            frame.add(label);
        } else {
            JLabel label = new JLabel("List is empty!");
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
           // label.setBounds(450, 200, 1000, 100);
            frame.add(label);
        }



    }


    // EFFECTS: generate a random index for the existing list of items
    public int getRandomIndex() {
        int max = itemList.list.size();
        int min = 0;

        return (int) ((Math.random() * (max - min)) + min);
    }

    // MODIFIES: this
    // EFFECTS: add an item
    public void addResult() {
        panel.setVisible(false);
        setButtonsInvisible();
        backButton.setVisible(true);
        enterButton.setVisible(true);

        addTextField = new JTextField();
        addTextField.setBounds(350 , 200, 200, 50);
        frame.add(addTextField);
    }

    // MODIFIES: this
    // EFFECTS: sets all menu buttons invisible
    public void setButtonsInvisible() {
        addButton.setVisible(false);
        getButton.setVisible(false);
        deleteButton.setVisible(false);
        menuLabel.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: set all menu buttons visible
    public void setButtonsVisible() {
        addButton.setVisible(true);
        getButton.setVisible(true);
        deleteButton.setVisible(true);
        menuLabel.setVisible(true);
    }
}

