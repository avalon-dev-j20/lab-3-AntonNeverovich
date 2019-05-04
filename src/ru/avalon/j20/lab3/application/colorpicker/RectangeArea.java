package ru.avalon.j20.lab3.application.colorpicker;

import java.awt.geom.Rectangle2D;

/**
 * Класс, создающий объект приямоугольник
 * В настоящий момент не используется
 */
@Deprecated
public class RectangeArea extends Rectangle2D {

    private double x, y, w, h;

    public RectangeArea() { }

    /**
     * Конструктор класса
     * @param x абсцисса начальной точки, от которой идет построение прямоугольника
     * @param y ордината начальной точки
     * @param w ширина прямоугольника
     * @param h высота (длина) прямоугольника
     */
    public RectangeArea(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void setRect(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public int outcode(double x, double y) { return 0; }

    @Override
    public Rectangle2D createIntersection(Rectangle2D r) { return null; }

    @Override
    public Rectangle2D createUnion(Rectangle2D r) { return null; }

    // getters
    @Override
    public double getX() { return x; }

    @Override
    public double getY() { return y; }

    @Override
    public double getWidth() { return w; }

    @Override
    public double getHeight() { return h; }

    @Override
    public boolean isEmpty() { return w <= 0 || h <= 0; }

}
