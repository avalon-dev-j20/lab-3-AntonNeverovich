package ru.avalon.j20.lab3.application.core;

import java.awt.*;

/**
 * Вспомогательный класс для упрощения написания кода
 * для расположения компонентов для менеджера GridBagLayout
 */
public class GridBagManager {

    // Координаты текущей ячейки
    private int gridx, gridy;

    // Настраиваемый объект GridBagConstraints
    private GridBagConstraints constraints;

    /**
     * Конструктор класса
     * @param gridx количество ячеек по горизонтали
     * @param gridy количество ячеек по вертикали
     */
    public GridBagManager(int gridx, int gridy) {
        this.gridx = gridx;
        this.gridy = gridy;
        this.constraints = new GridBagConstraints();
    }

    /**
     * Метод возвращает объект GridBagConstraints
     * @return объект GridBagConstraints
     */
    public GridBagConstraints getConstraints() {
        return constraints;
    }

    /**
     * Метод перемещает на следующую ячейку
     */
    public GridBagManager nextCell() {
        constraints = new GridBagConstraints();
        constraints.gridx = gridx++;
        constraints.gridy = gridy;
        // здесь и далее для удобства вызова методов
        return this;
    }

    /**
     * Метод раздвигает ячейку до конца строки
     */
    public GridBagManager span() {
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        return this;
    }

    /**
     * Метод заполняет ячейку по-горизонтали
     */
    public GridBagManager fillHorizontally() {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        return this;
    }

    /**
     * Метод создающий отступ справа
     * @param size размер пустого пространства в пикселях
     */
    public GridBagManager spaceRight(int size) {
        constraints.insets.right = size;
        return this;
    }

    /**
     * Метод задает веса для ячеек
     * @param horizontal вес по-горизонтали
     * @param vertical вес по-вертикали
     */
    public GridBagManager setWeights(float horizontal, float vertical) {
        constraints.weightx = horizontal;
        constraints.weighty = vertical;
        return this;
    }

    /**
     * Метод выравнивает содержимое ячейки по левому краю
     */
    public GridBagManager alignLeft() {
        constraints.anchor = GridBagConstraints.LINE_START;
        return this;
    }

    /**
     * Метод задает длину компонента в ячейках
     * @param width количество ячеек
     */
    public GridBagManager setGridWidth(int width) {
        constraints.gridwidth = width;
        return this;
    }

    // setters
    public void setGridx(int gridx) { this.gridx = gridx; }
    public void setGridy(int gridy) { this.gridy = gridy; }
}
