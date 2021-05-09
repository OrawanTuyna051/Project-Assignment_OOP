import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gui  {
    private String currentOperand = "";
    private boolean isTemp2 = false;
    private double temp1 = 0;
    private double temp2 = 0;
    private JTextField input = new JTextField("", 30);
    private JButton[] funcButton = new JButton[10];
    private JButton[] numericButtons = new JButton[10];
    private JButton[] operationButtons = new JButton[7];
    private JPanel numInputPanel = new JPanel(new GridLayout(4, 3));
    private JPanel subPanel = new JPanel(new GridBagLayout());

    void clear() {
        try {
            input.setText("");
            input.repaint();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    void createInputPanel() {
        JButton []constButton =  new JButton[3];
        constButton[0] = new JButton(".");

        constButton[1] = new JButton("pi");
        constButton[2] = new JButton("e");
        constButton[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = input.getText() + ".";
                System.out.println(number);
                if (!isTemp2) {
                    temp1 = Double.parseDouble(number);
                    input.setText(String.valueOf(number));
                } else {
                    temp1 = Double.parseDouble(number);
                    input.setText(String.valueOf(number));
                }
            }
        });
        // add shortcut button
        constButton[0].addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        constButton[1].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String number = input.getText() + String.valueOf(Math.PI);
                if (!isTemp2) {
                    temp1 = Double.parseDouble(number);
                    input.setText(String.valueOf(number));
                } else {
                    temp1 = Double.parseDouble(number);
                    input.setText(String.valueOf(number));
                }
            }
        });
        constButton[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = input.getText() + String.valueOf(Math.E);
                if (!isTemp2) {
                    temp1 = Double.parseDouble(number);
                    input.setText(String.valueOf(number));
                } else {
                    temp1 = Double.parseDouble(number);
                    input.setText(String.valueOf(number));
                }
            }
        });

        for (int i = 9; i >= 0; i--) {
            final int int_i = i;
            numericButtons[i] = new JButton("" + i);
            numericButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == numericButtons[int_i]) {
                        System.out.println("Button " + int_i + " was press");
                        String number = input.getText() + String.valueOf(int_i);
                        if (!isTemp2) {
                            temp1 = Double.parseDouble(number);
                            input.setText(String.valueOf(number));
                        } else {
                            temp1 = Double.parseDouble(number);
                            input.setText(String.valueOf(number));
                        }
                    }
                }
            });
        }
        for (int i = 0; i < 10; i++) {
            numInputPanel.add(numericButtons[i]);
        }
        numInputPanel.add(constButton[0]);
        numInputPanel.add(constButton[1]);
        numInputPanel.add(constButton[2]);

    }

    private JPanel funcPanel = new JPanel(new GridLayout(2, 5));

    void createFunctionalPanel() {
        String[] funcName = {"Sin", "Cos", "Tan", "Log", "Ln", "x^2", "x^y", "e^x", "1/x", "Sqrt"};
        for (int i = 0; i < funcName.length; i++) {
            final int idx = i;
            funcButton[i] = new JButton(funcName[i]);
            funcButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentOperand = funcName[idx];
                    isTemp2 = true;
                    temp2 = Double.parseDouble(input.getText());
                    clear();
                    System.out.println(currentOperand);
                }
            });
            funcPanel.add(funcButton[i]);
        }
    }
    void removeLast(){
        String temp = input.getText();
        input.setText(temp.substring(0,temp.length() - 1));
    }
    void validateOperand(String operand) {
        switch (operand) {
            case "+":
                input.setText(String.valueOf(temp1 + temp2));
                break;
            case "-":
                input.setText(String.valueOf(temp2 - temp1));
                break;
            case "*":
                input.setText(String.valueOf(temp2 * temp1));

                break;
            case "Sin":
                input.setText(String.valueOf(Math.sin(Math.toRadians(temp1))));
                break;
            case "Cos":
                input.setText(String.valueOf(Math.cos(Math.toRadians(temp1))));
                break;
            case "Tan":
                input.setText(String.valueOf(Math.tan(Math.toRadians(temp1))));
                break;
            case "e^x":
                input.setText(String.valueOf(Math.pow(Math.E, temp1)));
                break;
            case "x^2":
                input.setText(String.valueOf(Math.pow(temp1,2)));
                break;
            case "x^y":
                System.out.println("value temp1 = " + temp1);
                System.out.println("value temp2 = " + temp2);
                input.setText(String.valueOf(Math.pow(temp2, temp1)));
                break;
            case "Log":
                input.setText(String.valueOf(Math.log10(temp1)));
                break;
            case "Ln":
                input.setText(String.valueOf(Math.log(temp1)));
                break;
            case "1/x":
                input.setText(String.valueOf(1/temp1));
                break;
            case "Sqrt":
                input.setText(String.valueOf(Math.sqrt(temp1)));
                break;

            case "Del" : removeLast();
            break;

        }
    }

    private JPanel operatorPanel = new JPanel();

    void createOperatorPanel() {

        String[] funcName = {"Del", "C", "+", "-", "*", "/"};
        operatorPanel.setLayout(new GridLayout(4, 1));
        for (int i = 0; i < funcName.length; i++) {
            final int idx = i;
            operationButtons[i] = new JButton(funcName[i]);
            operationButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    currentOperand = funcName[idx];
                    if(!funcName[idx].equals("Del")){
                        isTemp2 = true;
                        temp2 = Double.parseDouble(input.getText());
                        clear();
                        System.out.println(currentOperand);
                    } else {
                        validateOperand(currentOperand);
                    }
                }
            });
            operatorPanel.add(operationButtons[i]);

        }
        JButton execute = new JButton("=");
        execute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateOperand(currentOperand);
            }
        });
        operatorPanel.add(execute);

    }
    void createSubPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        subPanel.add(numInputPanel,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        subPanel.add(operatorPanel,gbc);
    }
    public Gui(String title) {
        setTitle(title);
        createInputPanel();
        createFunctionalPanel();
        createOperatorPanel();
        createSubPanel();
        setLayout(new GridBagLayout());
        setSize(450, 300);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(input,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(funcPanel,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(subPanel,gbc);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public static void main(String[] args){
        new Gui("Calculator").setVisible(true);
    }

}
