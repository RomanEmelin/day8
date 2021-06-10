package edu.school21;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {

    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void print(String text) {
        renderer.printText(LocalDateTime.now() + " " + text);
    }
}
