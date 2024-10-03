package Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
public class CommandRunnerConfig {
    private final CommandLineController commandLineController;

    @Autowired
    public CommandRunnerConfig(CommandLineController commandLineController) {
        this.commandLineController = commandLineController;
    }

    @Bean
    public CommandLineRunner scanCommands() {
        return args -> {
            try (Scanner scanner = new Scanner(System.in))
            {
                System.out.println("Введите команду. 'exit' для выхода.");
                while (true)
                {
                    System.out.print("> ");
                    String input = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(input.trim()))
                    {
                        System.out.println("Выход из программы...");
                        break;
                    }

                    commandLineController.processCommand(input);
                }
            }
        };
    }
}
