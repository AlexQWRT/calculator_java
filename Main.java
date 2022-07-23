import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import java.util.Vector;

public class Main {

    private JFrame window;
    private JTextField textField;

    private JButton buttonDot;
    private JButton buttonEquals;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton buttonPlus;

    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton buttonMinus;

    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton buttonMult;

    private JButton buttonClearAll;
    private JButton button0;
    private JButton buttonBackspace;
    private JButton buttonDevide;

    private void setOperationsEnabled() {
        buttonPlus.setEnabled(true);
        buttonMinus.setEnabled(true);
        buttonMult.setEnabled(true);
        buttonDevide.setEnabled(true);
        buttonEquals.setEnabled(true);
        buttonDot.setEnabled(true);
    }

    private void setOperationsDisabled() {
        buttonPlus.setEnabled(false);
        buttonMinus.setEnabled(false);
        buttonMult.setEnabled(false);
        buttonDevide.setEnabled(false);
        buttonEquals.setEnabled(false);
        buttonDot.setEnabled(false);
    }

    private void setAllEnabled() {
        buttonDot.setEnabled(true);
        buttonEquals.setEnabled(true);

        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        buttonPlus.setEnabled(true);

        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        buttonMinus.setEnabled(true);

        button7.setEnabled(true);
        button8.setEnabled(true);
        button9.setEnabled(true);
        buttonMult.setEnabled(true);

        button0.setEnabled(true);
        buttonBackspace.setEnabled(true);
        buttonDevide.setEnabled(true);
    }

    private void setAllDisabled() {
        buttonDot.setEnabled(false);
        buttonEquals.setEnabled(false);

        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        buttonPlus.setEnabled(false);

        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);
        buttonMinus.setEnabled(false);

        button7.setEnabled(false);
        button8.setEnabled(false);
        button9.setEnabled(false);
        buttonMult.setEnabled(false);

        button0.setEnabled(false);
        buttonBackspace.setEnabled(false);
        buttonDevide.setEnabled(false);
    }

    public Main() {
        Font font = new Font("Cascadia Code", 0, 30);

        window = new JFrame("Calculator");
        window.setBounds(300, 100, 500, 500);
        window.setMinimumSize(new Dimension(400, 400));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridBagLayout());

        GridBagConstraints position = new GridBagConstraints();
        position.fill = GridBagConstraints.BOTH;
        position.weightx = 1;
        position.weighty = 1;
        int paddings = 10;
        position.insets = new Insets(paddings, paddings, paddings, paddings);

        //-------------------------------------------------------------------------------------------

        textField = new JTextField();
        textField.setFont(font);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        position.gridx = 0;
        position.gridy = 0;
        position.gridwidth = 4;
        window.add(textField, position);

        //-------------------------------------------------------------------------------------------

        buttonDot = new JButton(".");
        buttonDot.setFont(font);
        buttonDot.setFocusable(false);
        buttonDot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + ".");
                setOperationsDisabled();
            }
        });
        position.gridwidth = 2;
        position.gridx = 0;
        position.gridy = 1;
        window.add(buttonDot, position);

        buttonEquals = new JButton("=");
        buttonEquals.setFont(font);
        buttonEquals.setFocusable(false);
        buttonEquals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsEnabled();
                String expression = textField.getText();
                Vector<Double> numbers = new Vector<Double>();
                Pattern pattern = Pattern.compile("\\-?[0-9]{1,}(\\.[0-9]{1,})?");
                Matcher matcher = pattern.matcher(expression);
                try {
                    while (matcher.find()) {
                        numbers.add(Double.parseDouble(matcher.group()));
                    }
                } catch (NumberFormatException ex) {
                    setAllDisabled();
                    textField.setText("Error.");
                }
                Vector<String> symbols = new Vector<String>();
                pattern = Pattern.compile("[\\+\\-\\*\\/]");
                matcher = pattern.matcher(expression);
                while (matcher.find()) {
                    symbols.add(matcher.group());
                }
                if (expression.charAt(0) == '-') {
                    symbols.remove(0);
                }
                for (int i = 1; i < numbers.size(); i++) {
                    double buffer = 0;
                    switch(symbols.elementAt(i - 1)) {
                        case "*":
                            buffer = numbers.elementAt(i - 1) * numbers.elementAt(i);
                            numbers.insertElementAt(buffer, i - 1);
                            numbers.removeElementAt(i);
                            numbers.removeElementAt(i);
                            symbols.removeElementAt(i - 1);
                            i--;
                            break;
                        case "/":
                            if (numbers.elementAt(i) == 0) {
                                setAllDisabled();
                                textField.setText("Dividing by zero.");
                                return;
                            }
                            buffer = numbers.elementAt(i - 1) / numbers.elementAt(i);
                            numbers.insertElementAt(buffer, i - 1);
                            numbers.removeElementAt(i);
                            numbers.removeElementAt(i);
                            symbols.removeElementAt(i - 1);
                            i--;
                            break;
                    }
                }
                double result = numbers.elementAt(0);
                for (int i = 1; i < numbers.size(); i++) {
                    switch(symbols.elementAt(i - 1)) {
                        case "+":
                        case "-":
                            result += numbers.elementAt(i);
                            break;
                    }
                }
                textField.setText(result + "");
            }
        });
        position.gridx = 2;
        position.gridy = 1;
        window.add(buttonEquals, position);

        //-------------------------------------------------------------------------------------------

        button1 = new JButton("1");
        button1.setFont(font);
        button1.setFocusable(false);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsEnabled();
                textField.setText(textField.getText() + "1");
            }
        });
        position.gridwidth = 1;
        position.gridx = 0;
        position.gridy = 2;
        window.add(button1, position);

        button2 = new JButton("2");
        button2.setFont(font);
        button2.setFocusable(false);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsEnabled();
                textField.setText(textField.getText() + "2");
            }
        });
        position.gridx = 1;
        position.gridy = 2;
        window.add(button2, position);

        button3 = new JButton("3");
        button3.setFont(font);
        button3.setFocusable(false);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsEnabled();
                textField.setText(textField.getText() + "3");
            }
        });
        position.gridx = 2;
        position.gridy = 2;
        window.add(button3, position);

        buttonPlus = new JButton("+");
        buttonPlus.setFont(font);
        buttonPlus.setFocusable(false);
        buttonPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsDisabled();
                textField.setText(textField.getText() + "+");
            }
        });
        position.gridx = 3;
        position.gridy = 2;
        window.add(buttonPlus, position);

        //-------------------------------------------------------------------------------------------

        button4 = new JButton("4");
        button4.setFont(font);
        button4.setFocusable(false);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsEnabled();
                textField.setText(textField.getText() + "4");
            }
        });
        position.gridx = 0;
        position.gridy = 3;
        window.add(button4, position);

        button5 = new JButton("5");
        button5.setFont(font);
        button5.setFocusable(false);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsEnabled();
                textField.setText(textField.getText() + "5");
            }
        });
        position.gridx = 1;
        position.gridy = 3;
        window.add(button5, position);

        button6 = new JButton("6");
        button6.setFont(font);
        button6.setFocusable(false);
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsEnabled();
                textField.setText(textField.getText() + "6");
            }
        });
        position.gridx = 2;
        position.gridy = 3;
        window.add(button6, position);

        buttonMinus = new JButton("-");
        buttonMinus.setFont(font);
        buttonMinus.setFocusable(false);
        buttonMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsDisabled();
                textField.setText(textField.getText() + "-");
            }
        });
        position.gridx = 3;
        position.gridy = 3;
        window.add(buttonMinus, position);

        //-------------------------------------------------------------------------------------------

        button7 = new JButton("7");
        button7.setFont(font);
        button7.setFocusable(false);
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsEnabled();
                textField.setText(textField.getText() + "7");
            }
        });
        position.gridx = 0;
        position.gridy = 4;
        window.add(button7, position);

        button8 = new JButton("8");
        button8.setFont(font);
        button8.setFocusable(false);
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsEnabled();
                textField.setText(textField.getText() + "8");
            }
        });
        position.gridx = 1;
        position.gridy = 4;
        window.add(button8, position);

        button9 = new JButton("9");
        button9.setFont(font);
        button9.setFocusable(false);
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsEnabled();
                textField.setText(textField.getText() + "9");
            }
        });
        position.gridx = 2;
        position.gridy = 4;
        window.add(button9, position);

        buttonMult = new JButton("*");
        buttonMult.setFont(font);
        buttonMult.setFocusable(false);
        buttonMult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsDisabled();
                textField.setText(textField.getText() + "*");
            }
        });
        position.gridx = 3;
        position.gridy = 4;
        window.add(buttonMult, position);

        //-------------------------------------------------------------------------------------------

        buttonClearAll = new JButton("C");
        buttonClearAll.setFont(font);
        buttonClearAll.setFocusable(false);
        buttonClearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllEnabled();
                setOperationsDisabled();
                buttonMinus.setEnabled(true);
                textField.setText("");
            }
        });
        position.gridx = 0;
        position.gridy = 5;
        window.add(buttonClearAll, position);

        button0 = new JButton("0");
        button0.setFont(font);
        button0.setFocusable(false);
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsEnabled();
                textField.setText(textField.getText() + "0");
            }
        });
        position.gridx = 1;
        position.gridy = 5;
        window.add(button0, position);

        buttonBackspace = new JButton("<");
        buttonBackspace.setFont(font);
        buttonBackspace.setFocusable(false);
        buttonBackspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isEmpty()) {
                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                }
                if (textField.getText().length() > 1) {
                    
                    char sym = textField.getText().charAt(textField.getText().length() - 1);
                    if (sym == '+' || sym == '-' || sym == '*' || sym == '/' || sym == '.') {
                        setOperationsDisabled();
                    } else {
                        setOperationsEnabled();
                    }
                } else {
                    setOperationsDisabled();
                    buttonMinus.setEnabled(true);
                }
            }
        });
        position.gridx = 2;
        position.gridy = 5;
        window.add(buttonBackspace, position);

        buttonDevide = new JButton("/");
        buttonDevide.setFont(font);
        buttonDevide.setFocusable(false);
        buttonDevide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOperationsDisabled();
                textField.setText(textField.getText() + "/");
            }
        });
        position.gridx = 3;
        position.gridy = 5;
        window.add(buttonDevide, position);

        setOperationsDisabled();
        buttonMinus.setEnabled(true);
        window.setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }
}
