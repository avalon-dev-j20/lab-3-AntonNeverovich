package ru.avalon.j20.lab3.application.colorpicker;

public class ColorCoordinate {

    private int r;
    private int g;
    private int b;

    public ColorCoordinate(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    // getters & setters
    public int getR() { return r; }
    public int getG() { return g; }
    public int getB() { return b; }

    public void setR(int r) { this.r = r; }
    public void setG(int g) { this.g = g; }
    public void setB(int b) { this.b = b; }

    @Override
    public String toString() { return r + ", " + g + ", " + b ; }

    /**
     * Метод реализующий алгоритм преобразования цветовых координат RGB в шеснадцатиричный формат.
     * @return значение цвета в шеснадцатиричной форме
     */
    public String getHexColor() {
        return "#" + Integer.toHexString(0x100 | r).substring(1).toUpperCase()
                + Integer.toHexString(0x100 | g).substring(1).toUpperCase()
                + Integer.toHexString(0x100 | b).substring(1).toUpperCase();
    }
}
