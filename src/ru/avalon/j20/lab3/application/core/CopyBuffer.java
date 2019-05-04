package ru.avalon.j20.lab3.application.core;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * Класс реализующий функцию копирования в буфер обмена.
 */
public class CopyBuffer {

    /**
     * Метод копирующий строку в буфер обмена.
     * @param text строка, которую нужно записать в буфер обмена
     */
    public static void copyToClipboard(String text) {
        Clipboard clipboard = Toolkit
                .getDefaultToolkit()
                .getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
    }

}
