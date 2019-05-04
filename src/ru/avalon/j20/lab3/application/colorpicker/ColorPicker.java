package ru.avalon.j20.lab3.application.colorpicker;

import ru.avalon.j20.lab3.application.core.ColorCoordinate;
import ru.avalon.j20.lab3.application.core.CopyBuffer;
import ru.avalon.j20.lab3.application.core.GridBagManager;
import ru.avalon.j20.lab3.application.core.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ColorPicker {

    private static MainFrame mainFrame;
    private static GraphicPanel rectangle;
    private static ColorCoordinate colorCoordinate = new ColorCoordinate(125, 125, 125);

    private static JSlider redSlider;
    private static JSlider greenSlider;
    private static JSlider blueSlider;

    private static String hexColor = colorCoordinate.getHexColor();

    /**
     * Метод, запускающий приложение.
     */
    public static void run() {
        System.out.println(ColorPicker.class.getSimpleName() + " starts...");

        mainFrame = new MainFrame("Color Picker",
                new BorderLayout(),
                new Dimension(600, 300));

        // Расположим приложение рядом с другим
        mainFrame.setToNext();

        addInterface();

        mainFrame.pack();

    }

    /**
     * Метод добавляющий интерфейс.
     */
    private static void addInterface() {

        // создаем главную панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        // Создаем графический компонент на главной панели
        rectangle = new GraphicPanel(); // создаем прямоугольник
        rectangle.setToolTipText(colorCoordinate.getHexColor());
        mainPanel.add(rectangle);

        // Проверка работы слайдеров
        //JLabel labelColorCoordinate = new JLabel(colorCoordinate.toString());

        // Создаем панель со слайдерами
        JPanel sliderPanel = addSliderPanel();

        // Проверка работы ползунков
        //mainPanel.add(labelColorCoordinate);

        // Добавляем панель со слайдерами
        mainPanel.add(sliderPanel);

        // Добавляем галвную панель
        mainFrame.add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Метод создающий интерфес панели со слайдерами
     * @return возвращает компонент JPanel
     */
    private static JPanel addSliderPanel() {
        // Объект на главной панели
        JPanel sliderPanel = new JPanel(); // создаем панель с ползунками
        sliderPanel.setLayout(new GridLayout(3, 1));
        sliderPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        // Объекты на панели с ползунками
        JLabel redLabel = new JLabel("Red[" + colorCoordinate.getR() + "]:\t");
        redLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JLabel greenLabel = new JLabel("Green[" + colorCoordinate.getG() + "]:\t");
        greenLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JLabel blueLabel = new JLabel("Blue[" + colorCoordinate.getB() + "]:\t");
        blueLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        // Обработчик для слайдеров (для всех идентичный),
        // разница в том, какую цветовую координату он перехватывает
        redSlider = new JSlider(0, 255, 125);
        sliderBuilder(redSlider);
        redSlider.addChangeListener(e -> {
            // Перехватывает кординату Red
            int value = redSlider.getValue();
            colorCoordinate.setR(value);
            hexColor = colorCoordinate.getHexColor();
            rectangle.setToolTipText(hexColor);
            CopyBuffer.copyToClipboard(hexColor);
            redLabel.setText("Red[" + colorCoordinate.getR() + "]:\t" );
            //labelColorCoordinate.setText(colorCoordinate.toString());
            rectangle.repaint();
        });

        greenSlider = new JSlider(0, 255, 125);
        sliderBuilder(greenSlider);
        greenSlider.addChangeListener(e -> {
            // Перехватывает кординату Green
            int value = greenSlider.getValue();
            colorCoordinate.setG(value);
            hexColor = colorCoordinate.getHexColor();
            rectangle.setToolTipText(hexColor);
            CopyBuffer.copyToClipboard(hexColor);
            greenLabel.setText("Green[" + colorCoordinate.getG() + "]:\t");
            //labelColorCoordinate.setText(colorCoordinate.toString());
            rectangle.repaint();
        });

        blueSlider = new JSlider(0, 255, 125);
        sliderBuilder(blueSlider);
        blueSlider.addChangeListener(e -> {
            // Перехватывает кординату Blue
            int value = blueSlider.getValue();
            colorCoordinate.setB(value);
            hexColor = colorCoordinate.getHexColor();
            rectangle.setToolTipText(hexColor);
            CopyBuffer.copyToClipboard(hexColor);
            blueLabel.setText("Blue[" + colorCoordinate.getB() + "]:\t");
            //labelColorCoordinate.setText(colorCoordinate.toString());
            rectangle.repaint();
        });

        // Создаем панель для JLabel и JSlider для одного цвета
        JPanel redColor = oneColorPanel(redLabel, redSlider);
        JPanel greenColor = oneColorPanel(greenLabel, greenSlider);
        JPanel blueColor = oneColorPanel(blueLabel, blueSlider);


        // Добавляем компоненты на sliderPanel
        sliderPanel.add(redColor);
        sliderPanel.add(greenColor);
        sliderPanel.add(blueColor);

        return sliderPanel;
    }

    /**
     * Метод, создающий панель для работы с один цветом
     * @param label Ярлык
     * @param slider Слайдер
     * @return возвращает созданую панель
     */
    private static JPanel oneColorPanel(JLabel label, JSlider slider) {
        JPanel panel = new JPanel();
        //panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel.setLayout(new GridBagLayout());
        GridBagManager manager = new GridBagManager(7, 1);

        // задаем расположение для ярлыка
        manager.setGridWidth(1)
                .setWeights(1f, 0f)
                .alignLeft();
                //.spaceRight(6);
        panel.add(label, manager.getConstraints());

        // задаем арсположение для слайдера
        manager.setGridWidth(6)
                .setWeights(1f, 0f)
                .alignLeft()
                //.fillHorizontally()
                .span();
        panel.add(slider, manager.getConstraints());

        return panel;
    }

    /**
     * Метод, описывающий однотипный код для создания слайдера.
     * @param slider Абстрактный слайдер
     */
    private static void sliderBuilder(JSlider slider) {
        slider.setMajorTickSpacing(slider.getMaximum());
        slider.setMinorTickSpacing(((slider.getMaximum() + 1) / 8));
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    // Следующие два методв необходимы для инкапсуляции и обращения к переменным
    public static MainFrame getMainFrame() { return mainFrame; }
    public static ColorCoordinate getColorCoordinate() { return colorCoordinate; }
}
