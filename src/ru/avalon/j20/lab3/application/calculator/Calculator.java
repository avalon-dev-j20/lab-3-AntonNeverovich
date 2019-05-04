package ru.avalon.j20.lab3.application.calculator;

import ru.avalon.j20.lab3.application.core.*;

import javax.swing.*;
import java.awt.*;

public class Calculator {

    public static MainFrame mainFrame;
    private static JLabel resultLabel;
    private static String buffer = "0"; // переменная для записи введенных значений на калькуляторе
    private static int operand = 0;
    private static double var_1 = 0.0;

    private static Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 30);
    private static Font resultFont = new Font(Font.SANS_SERIF, Font.PLAIN, 60);

    // Кнопки
    private static JButton button_0 = createButton("0");
    private static JButton button_1 = createButton("1");
    private static JButton button_2 = createButton("2");
    private static JButton button_3 = createButton("3");
    private static JButton button_4 = createButton("4");
    private static JButton button_5 = createButton("5");
    private static JButton button_6 = createButton("6");
    private static JButton button_7 = createButton("7");
    private static JButton button_8 = createButton("8");
    private static JButton button_9 = createButton("9");
    private static JButton button_sum = createButton("+");
    private static JButton button_dif = createButton("-");
    private static JButton button_mult = createButton("*");
    private static JButton button_div = createButton("/");
    private static JButton button_ce = createButton("CE");
    private static JButton button_point = createButton(".");
    private static JButton resultButton = createButton("=");

    /**
     * Метод, запускающий приложение.
     */
    public static void run() {
        System.out.println( Calculator.class.getSimpleName() + " starts...");

        int x = 450, y = 600; // размеры окна
        mainFrame = new MainFrame("Calculator",
                new BorderLayout(),
                new Dimension(x, y));

        // максимальные и минимальные размеры окна
        mainFrame.setMinimumSize(new Dimension((int)(x / 1.5), (int)(y / 1.5)));
        mainFrame.setMaximumSize(new Dimension((int)(x * 1.5), (int)(y * 1.5)));
        mainFrame.setResizable(false);

        addInterface();
        mainFrame.pack();

    }

    /**
     * Метод добавляющий интерфейс.
     */
    private static void addInterface() {

        // создаем главную панель
        JPanel mainPanel = createMainPanel();

        // Создаем метку для вывода результата
        resultLabel = createResultView();
        mainPanel.add(resultLabel, BorderLayout.NORTH);

        // Создаем панель для кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        // первая строка элементов
        buttonPanel.add(button_7);
        buttonPanel.add(button_8);
        buttonPanel.add(button_9);
        buttonPanel.add(button_sum);

        // вторая строка элементов
        buttonPanel.add(button_4);
        buttonPanel.add(button_5);
        buttonPanel.add(button_6);
        buttonPanel.add(button_dif);

        // третья строка элементов
        buttonPanel.add(button_1);
        buttonPanel.add(button_2);
        buttonPanel.add(button_3);
        buttonPanel.add(button_mult);

        // четвертая строка элементов
        buttonPanel.add(button_ce);
        buttonPanel.add(button_0);
        buttonPanel.add(button_point);
        buttonPanel.add(button_div);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Создаем кнопку равно
        resultButton.setFont(resultFont);
        mainPanel.add(resultButton, BorderLayout.SOUTH);

        // Добавляем обработчики для кнопок ввода
        addListener();

        // Добавляем все элементы в приложение
        mainFrame.add(mainPanel);

    }

    /**
     * Метод, добавляющий логику работы приложения
     */
    private static void addListener() {
        button_1.addActionListener(e -> onClickEnterButton("1"));
        button_2.addActionListener(e -> onClickEnterButton("2"));
        button_3.addActionListener(e -> onClickEnterButton("3"));
        button_4.addActionListener(e -> onClickEnterButton("4"));
        button_5.addActionListener(e -> onClickEnterButton("5"));
        button_6.addActionListener(e -> onClickEnterButton("6"));
        button_7.addActionListener(e -> onClickEnterButton("7"));
        button_8.addActionListener(e -> onClickEnterButton("8"));
        button_9.addActionListener(e -> onClickEnterButton("9"));

        // Отдельный обработчик для нуля
        button_0.addActionListener(e -> {
            if (buffer.equals("0")) {
                resultLabel.setText(buffer);
            } else {
                buffer = buffer + "0";
                resultLabel.setText(buffer);
            }
        });

        // Отдельный обарботчик для точки
        button_point.addActionListener(e -> {
            buffer = buffer + ".";
            resultLabel.setText(buffer);
        });

        // Обработчик для кнопки обнуления
        button_ce.addActionListener(e -> {
            buffer = "0";
            resultLabel.setText(buffer);
        });

        // Обработчики для арифметических операций
        button_sum.addActionListener(e -> setOperand(1));
        button_dif.addActionListener(e -> setOperand(2));
        button_mult.addActionListener(e -> setOperand(3));
        button_div.addActionListener(e -> setOperand(4));

        // Обработчик кнопки равно для записи результата в буфер
        resultButton.addActionListener(e -> CopyBuffer.copyToClipboard(buffer));

        // Обработчик кнопки равно для вычислений
        resultButton.addActionListener(e -> getResult());
    }

    private static void getResult() {
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
    private static void setOperand(int i) {
        var_1 = Double.valueOf(buffer);
        operand = i;
        buffer = "0";
        resultLabel.setText(buffer);
    }

    /**
     * Метод описывающий логику обработки кнопок для ввода чисел
     * @param value значение, которое добавляет кнопка
     */
    private static void onClickEnterButton(String value) {
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
    private static JLabel createResultView() {
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
    private static JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        return mainPanel;
    }

    /**
     * Метод создет кнопку
     * @param name имя кнопки
     */
    private static JButton createButton(String name) {
        JButton button = new JButton(name);
        button.setFont(buttonFont);
        return button;
    }

}