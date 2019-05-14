package ru.avalon.j20.lab3.application.colorpicker;

import ru.avalon.j20.lab3.application.core.*;

import javax.swing.*;
import java.awt.*;
import java.util.Formatter;

public class ColorPicker extends MainFrame {

    private MainFrame mainFrame;
    private GraphicPanel rectangle;
    private static ColorCoordinate colorCoordinate = new ColorCoordinate(125, 125, 125);

    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;

    private String hexColor = colorCoordinate.getHexColor();


    public ColorPicker() {
    }

    /**
     * Метод, запускающий приложение.
     */
    public void run() {
        System.out.println(ColorPicker.class.getSimpleName() + " starts...");

        mainFrame = MainFrameFactory.buildAppFrame(
                                                "Color Picker",
                                                    new BorderLayout(),
                                                    new Dimension(600, 300));

        mainFrame.setBounds(455, 0, getWidth(), getHeight());

        addInterface();

        mainFrame.pack();

    }

    /**
     * Метод добавляющий интерфейс.
     */
    private void addInterface() {

        // создаем главную панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        // Создаем графический компонент на главной панели
        rectangle = new GraphicPanel(); // создаем прямоугольник
        rectangle.setToolTipText(colorCoordinate.getHexColor());
        mainPanel.add(rectangle);

        // Создаем панель со слайдерами
        JPanel sliderPanel = addSliderPanel();

        // Добавляем панель со слайдерами
        mainPanel.add(sliderPanel);

        // Добавляем галвную панель
        mainFrame.add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Метод создающий интерфес панели со слайдерами
     * @return возвращает компонент JPanel
     */
    private JPanel addSliderPanel() {
        // Объект на главной панели
        JPanel sliderPanel = new JPanel(); // создаем панель с ползунками
        sliderPanel.setLayout(new GridLayout(3, 1));
        sliderPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        // Объекты на панели с ползунками
        JLabel redLabel = new JLabel(redColorLabelName());
        redLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JLabel greenLabel = new JLabel(greenColorLabelName());
        greenLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JLabel blueLabel = new JLabel(blueColorLabelName());
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
            try {
                CopyBuffer.copyToClipboard(hexColor);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            redLabel.setText(redColorLabelName());
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
            try {
                CopyBuffer.copyToClipboard(hexColor);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            greenLabel.setText(greenColorLabelName());
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
            try {
                CopyBuffer.copyToClipboard(hexColor);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            blueLabel.setText(blueColorLabelName());
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
    private JPanel oneColorPanel(JLabel label, JSlider slider) {
        JPanel panel = new JPanel();
        //panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel.setLayout(new GridBagLayout());
        GridBagManager manager = new GridBagManager(2, 1);

        // задаем расположение для ярлыка
        manager.setGridWidth(1)
                .setWeights(0.15f, 0f)
                .alignLeft()
                .spaceRight(6);
        panel.add(label, manager.getConstraints());

        // задаем арсположение для слайдера
        manager.setGridWidth(6)
                .setWeights(0.85f, 0f)
                .alignLeft()
                .fillHorizontally()
                .span();
        panel.add(slider, manager.getConstraints());

        return panel;
    }

    /**
     * Метод, описывающий однотипный код для создания слайдера.
     * @param slider Абстрактный слайдер
     */
    private void sliderBuilder(JSlider slider) {
        slider.setMajorTickSpacing(slider.getMaximum());
        slider.setMinorTickSpacing(((slider.getMaximum() + 1) / 8));
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    // Следующие два метода необходимы для инкапсуляции и обращения к переменным
    public MainFrame getMainFrame() { return mainFrame; }
    public static ColorCoordinate getColorCoordinate() { return colorCoordinate; }

    private String redColorLabelName() {
        StringBuilder redColorValue = new StringBuilder();
        Formatter formatter = new Formatter(redColorValue);
        formatter.format("%03d", colorCoordinate.getR());
        String formattedRedColorValue = redColorValue.toString();
        return "Red[" + formattedRedColorValue + "]:\t\t";
    }

    private String greenColorLabelName() {
        StringBuilder greenColorValue = new StringBuilder();
        Formatter formatter = new Formatter(greenColorValue);
        formatter.format("%03d", colorCoordinate.getG());
        String formattedGreenColorValue = greenColorValue.toString();
        return "Green[" + formattedGreenColorValue + "]:";
    }

    private String blueColorLabelName() {
        StringBuilder blueColorValue = new StringBuilder();
        Formatter formatter = new Formatter(blueColorValue);
        formatter.format("%03d", colorCoordinate.getB());
        String formattedBlueColorValue = blueColorValue.toString();
        return "Blue[" + formattedBlueColorValue + "]:\t\t";
    }
}
