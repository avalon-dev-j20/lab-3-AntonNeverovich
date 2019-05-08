//import ru.avalon.j20.lab3.application.calculator.Calculator;
import ru.avalon.j20.lab3.application.calculator.Calculator;
import ru.avalon.j20.lab3.application.colorpicker.ColorPicker;

public class Application {

    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        calculator.run();

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.run();
    }
}
