package ru.avalon.j20.lab3.application.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class DisableToResize implements ComponentListener {

    private JFrame frame;
    private Dimension dimension;

    public DisableToResize(JFrame frame, Dimension dimension) {
        this.frame = frame;
        this.dimension = dimension;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        if (isFrameDimensionsMoreThenMaximum(e))
            frame.setSize(dimension);
    }

    private boolean isFrameDimensionsMoreThenMaximum(ComponentEvent e) {
        return e.getComponent().getWidth() > dimension.getWidth()
               || e.getComponent().getHeight() > dimension.getHeight();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
