package ru.avalon.j20.lab3.application.core;

import javax.swing.*;
import java.awt.*;

public class MainFrameFactory {

    /**
     * Класс - конструктор главного окна приложения.
     * @param title название приложения, заголовок окна
     * @param layoutManager менеджер компоновки
     * @param dimension размеры
     */
    public static MainFrame buildAppFrame(String title, LayoutManager layoutManager, Dimension dimension) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setTitle(title);
        mainFrame.setPreferredSize(dimension);
        mainFrame.setLayout(layoutManager);
        mainFrame.setVisible(true);
        return mainFrame;
    }
}
