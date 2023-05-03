import core.FileSystem;
import context.ExecutorContext;
import handler.EventSelector;
import io.StandardInput;
import io.StandardOutput;

import java.util.Scanner;

public class Shell {
    public static void main(String[] args) {
        FileSystem fs = new FileSystem("root");
        ExecutorContext context = fs.context;
        EventSelector selector = new EventSelector();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(generatePrompt(context));
            final String command = scanner.nextLine();
            if ("exit".equals(command)) {
                break;
            } else {
                StandardInput input = new StandardInput(command);
                final StandardOutput output = selector.select(input, fs.context);
                if (!output.toString().isBlank()) {
                    System.out.println(output.toString());
                }
            }
        }
    }

    public static String generatePrompt(ExecutorContext context) {
        return "[" + context.user + " " + context.path + "]" + "$ ";
    }
}
