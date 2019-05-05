package ru.avalon.j20.lab3.application.colorpicker;

import javax.swing.*;
import java.awt.*;

/**
 * Класс создающий графическую панель,
 * на которой будут отображаться графические компоненты:
 * в данном случае - прямоугольник
 */
public class GraphicPanel extends JPanel {

    /**
     * Конструктуор класса.
     * В конструктуоре создается объект клсса RectangleArea,
     * а также задаются параметры самой панели такие как:
     * размер и видимость
     */
    public GraphicPanel() {
        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        setVisible(true);
    }

    /**
     *  Метод отвечающий за отрисовку компонентов на данной панели.
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawRectangle(graphics);
    }

    /**
     * Метод описывающий логику отрисовки прямоугольника
     */
    private void drawRectangle(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        int r = ColorPicker.getColorCoordinate().getR(),
                g = ColorPicker.getColorCoordinate().getG(),
                b = ColorPicker.getColorCoordinate().getB();
        g2.setColor(new Color (r, g, b));
        // Размеры прямоугольника првязаны к размерам GraphicPanel
        int w = getWidth();
        int h = getHeight();
        g2.drawRect(0, 0, w, h);
        g2.fillRect(0, 0, w, h);
    }
}
