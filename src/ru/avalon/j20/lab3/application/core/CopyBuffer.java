package ru.avalon.j20.lab3.application.core;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.lang.Thread;

/**
 * Класс реализующий функцию копирования в буфер обмена.
 */
public class CopyBuffer {

    private static Clipboard clipboard = Toolkit
                                        .getDefaultToolkit()
                                        .getSystemClipboard();
    private static StringSelection selection;

    /**
     * Метод копирующий строку в буфер обмена.
     * @param text строка, которую нужно записать в буфер обмена
     */
    public static void copyToClipboard(String text) throws IllegalStateException, InterruptedException {
        Thread.sleep(20);
        selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
    }

}
