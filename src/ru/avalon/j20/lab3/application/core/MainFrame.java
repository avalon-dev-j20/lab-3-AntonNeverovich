package ru.avalon.j20.lab3.application.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainFrame extends JFrame {

    public MainFrame() {}

    /**
     * Метод размещает приложение рядом с другим приложением
     */
    public void positionNextToApplication(JFrame frame) {
        int x = frame.getWidth() + 5;
        int y = 0;
        setBounds(x, y, getWidth(), getHeight());
    }

}
