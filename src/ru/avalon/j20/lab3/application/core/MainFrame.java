package ru.avalon.j20.lab3.application.core;

import ru.avalon.j20.lab3.application.calculator.Calculator;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private String title;
    private LayoutManager layoutManager;
    private Dimension dimension;

    /**
     * Класс - конструктор главного окна приложения.
     * @param title название приложения, заголовок окна
     * @param layoutManager менеджер компоновки
     * @param dimension размеры
     */
    public MainFrame(String title, LayoutManager layoutManager, Dimension dimension) {
        this.title = title;
        this.layoutManager = layoutManager;
        this.dimension = dimension;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(title);
        setPreferredSize(dimension);
        setLayout(layoutManager);
        setVisible(true);
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    /**
     * Метод размещает приложение по центру экрана
     */
    public void setToNext() {
        Dimension screenDimention = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = Calculator.mainFrame.getWidth() + 5;
        int y = 0;
        setBounds(x, y, getWidth(), getHeight());
    }
}
