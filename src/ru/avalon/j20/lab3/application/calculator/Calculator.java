package ru.avalon.j20.lab3.application.calculator;

import ru.avalon.j20.lab3.application.core.*;

import javax.swing.*;
import java.awt.*;

public class Calculator extends MainFrame {

    private MainFrame mainFrame;
    private JLabel resultLabel;
    private String buffer = "0"; // переменная для записи введенных значений на калькуляторе
    private int operand = 0;
    private double var_1 = 0.0;

    private Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 30);
    private Font resultFont = new Font(Font.SANS_SERIF, Font.PLAIN, 60);

    private int x = 450, y = 600; // размеры окна
    private final Dimension MINIMAL_SIZE = new Dimension((int)(x / 1.5), (int)(y / 1.5));
    private final Dimension MAXIMUM_SIZE = new Dimension((int)(x * 1.5), (int)(y * 1.5));


    // Кнопки
    private final JButton BUTTON_0 = createButton("0");
    private final JButton BUTTON_1 = createButton("1");
    private final JButton BUTTON_2 = createButton("2");
    private final JButton BUTTON_3 = createButton("3");
    private final JButton BUTTON_5 = createButton("5");
    private final JButton BUTTON_4 = createButton("4");
    private final JButton BUTTON_6 = createButton("6");
    private final JButton BUTTON_7 = createButton("7");
    private final JButton BUTTON_8 = createButton("8");
    private final JButton BUTTON_9 = createButton("9");
    private final JButton BUTTON_SUM = createButton("+");
    private final JButton BUTTON_DIF = createButton("-");
    private final JButton BUTTON_MULT = createButton("*");
    private final JButton BUTTON_DIV = createButton("/");
    private final JButton BUTTON_CE = createButton("CE");
    private final JButton BUTTON_point = createButton(".");
    private final JButton BUTTON_RESULT = createButton("=");

    public Calculator() {
    }

    /**
     * Метод, запускающий приложение.
     */
    public void run() {
        System.out.println( Calculator.class.getSimpleName() + " starts...");

        mainFrame = MainFrameFactory.buildAppFrame(
                                                "Calculator",
                                                    new BorderLayout(),
                                                    new Dimension(x, y));

        // максимальные и минимальные размеры окна

        mainFrame.setMinimumSize(MINIMAL_SIZE);
        mainFrame.setMaximumSize(MAXIMUM_SIZE);

        mainFrame.addComponentListener(new DisableToResize(mainFrame, MAXIMUM_SIZE));

        //mainFrame.setResizable(false);


        addInterface();
        mainFrame.pack();

    }

    /**
     * Метод добавляющий интерфейс.
     */
    private void addInterface() {

        // создаем главную панель
        JPanel mainPanel = mainPanelFactory();

        // Создаем метку для вывода результата
        resultLabel = createResultView();
        mainPanel.add(resultLabel, BorderLayout.NORTH);

        // Создаем панель для кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        // первая строка элементов
        buttonPanel.add(BUTTON_7);
        buttonPanel.add(BUTTON_8);
        buttonPanel.add(BUTTON_9);
        buttonPanel.add(BUTTON_SUM);

        // вторая строка элементов
        buttonPanel.add(BUTTON_4);
        buttonPanel.add(BUTTON_5);
        buttonPanel.add(BUTTON_6);
        buttonPanel.add(BUTTON_DIF);

        // третья строка элементов
        buttonPanel.add(BUTTON_1);
        buttonPanel.add(BUTTON_2);
        buttonPanel.add(BUTTON_3);
        buttonPanel.add(BUTTON_MULT);

        // четвертая строка элементов
        buttonPanel.add(BUTTON_CE);
        buttonPanel.add(BUTTON_0);
        buttonPanel.add(BUTTON_point);
        buttonPanel.add(BUTTON_DIV);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Создаем кнопку равно
        BUTTON_RESULT.setFont(resultFont);
        mainPanel.add(BUTTON_RESULT, BorderLayout.SOUTH);

        // Добавляем обработчики для кнопок ввода
        addListener();

        // Добавляем все элементы в приложение
        mainFrame.add(mainPanel);

    }

    /**
     * Метод, добавляющий логику работы приложения
     */
    private void addListener() {
        BUTTON_1.addActionListener(e -> onClickEnterButton("1"));
        BUTTON_2.addActionListener(e -> onClickEnterButton("2"));
        BUTTON_3.addActionListener(e -> onClickEnterButton("3"));
        BUTTON_4.addActionListener(e -> onClickEnterButton("4"));
        BUTTON_5.addActionListener(e -> onClickEnterButton("5"));
        BUTTON_6.addActionListener(e -> onClickEnterButton("6"));
        BUTTON_7.addActionListener(e -> onClickEnterButton("7"));
        BUTTON_8.addActionListener(e -> onClickEnterButton("8"));
        BUTTON_9.addActionListener(e -> onClickEnterButton("9"));

        // Отдельный обработчик для нуля
        BUTTON_0.addActionListener(e -> {
            if (buffer.equals("0")) {
                resultLabel.setText(buffer);
            } else {
                buffer = buffer + "0";
                resultLabel.setText(buffer);
            }
        });

        // Отдельный обарботчик для точки
        BUTTON_point.addActionListener(e -> {
            buffer = buffer + ".";
            resultLabel.setText(buffer);
        });

        // Обработчик для кнопки обнуления
        BUTTON_CE.addActionListener(e -> {
            buffer = "0";
            resultLabel.setText(buffer);
        });

        // Обработчики для арифметических операций
        BUTTON_SUM.addActionListener(e -> setOperand(1));
        BUTTON_DIF.addActionListener(e -> setOperand(2));
        BUTTON_MULT.addActionListener(e -> setOperand(3));
        BUTTON_DIV.addActionListener(e -> setOperand(4));

        // Обработчик кнопки равно для записи результата в буфер
        BUTTON_RESULT.addActionListener(e -> {
            try {
                CopyBuffer.copyToClipboard(buffer);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        // Обработчик кнопки равно для вычислений
        BUTTON_RESULT.addActionListener(e -> getResult());
    }

    private void getResult() {
        double result;
        if (operand == 1) { // сложение
            result = var_1 + Double.valueOf(buffer);
            buffer = String.valueOf(result);
            resultLabel.setText(buffer);
        } else if (operand == 2) { // вычитание
            result = var_1 - Double.valueOf(buffer);
            buffer = String.valueOf(result);
            resultLabel.setText(buffer);
        } else if (operand == 3) { // умножение
            result = var_1 * Double.valueOf(buffer);
            buffer = String.valueOf(result);
            resultLabel.setText(buffer);
        } else if (operand == 4) { // деление
            result = var_1 / Double.valueOf(buffer);
            buffer = String.valueOf(result);
            resultLabel.setText(buffer);
        }
    }

    /**
     * Метод указывающий на то, кнопка какой арифметической операции была нажата.
     * @param i индекс операнда
     */
    private void setOperand(int i) {
        var_1 = Double.valueOf(buffer);
        operand = i;
        buffer = "0";
        resultLabel.setText(buffer);
    }

    /**
     * Метод описывающий логику обработки кнопок для ввода чисел
     * @param value значение, которое добавляет кнопка
     */
    private void onClickEnterButton(String value) {
        if (buffer.equals("0")) {
            resultLabel.setText(value);
            buffer = resultLabel.getText();
        } else {
            buffer = buffer + value;
            resultLabel.setText(buffer);
        }
    }

    /**
     * Метод создает компонент, отвечающий за вывод результатов вычислений
     * @return возвращает компонент JLabel
     */
    private JLabel createResultView() {
        JLabel label = new JLabel("", SwingConstants.RIGHT);
        label.setText(buffer);
        label.setFont(resultFont);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
        return label;
    }

    /**
     * Метод создает главную панель, на которой будет создаваться весь интерфейс
     * @return возвращает главную панель
     */
    private JPanel mainPanelFactory() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        return mainPanel;
    }

    /**
     * Метод создет кнопку
     * @param name имя кнопки
     */
    private JButton createButton(String name) {
        JButton button = new JButton(name);
        button.setFont(buttonFont);
        return button;
    }

    public MainFrame getMainFrame() { return mainFrame; }

}