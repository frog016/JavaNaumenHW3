package org.example.JavaNaumenHW3.Controllers;

import org.example.JavaNaumenHW3.Cinema.Movie;
import org.example.JavaNaumenHW3.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CommandLineController {
    private final MovieService movieService;

    @Autowired
    public CommandLineController(MovieService movieService) {
        this.movieService = movieService;
    }

    public void processCommand(String input) {
        String[] parsedInput = input.split(" ");
        String command = parsedInput[0];
        String[] arguments = Arrays.copyOfRange(parsedInput, 1, parsedInput.length);

        switch (command) {
            case "create" -> applyCreateCommand(arguments);
            case "find" -> applyFindCommand(arguments);
            case "delete" -> applyDeleteCommand(arguments);
            case "update" -> applyUpdateCommand(arguments);
            default -> System.out.println("Неизветсная команда!");
        }
    }

    private void applyCreateCommand(String[] commandArguments) {
        Long id = Long.parseLong(commandArguments[0]);
        int genre = Integer.parseInt(commandArguments[2]);
        float duration = Float.parseFloat(commandArguments[1]);
        String description = commandArguments[3];

        Movie movie = movieService.createMovie(id, genre, duration, description);
        System.out.println("Добавлен фильм: " + movie);
    }

    private void applyFindCommand(String[] commandArguments) {
        Long id = Long.parseLong(commandArguments[0]);
        Movie movie = movieService.findById(id);

        System.out.println("Найден фильм: " + movie);
    }

    private void applyDeleteCommand(String[] commandArguments) {
        Long id = Long.parseLong(commandArguments[0]);
        movieService.deleteById(id);

        System.out.println("Удален фильм с id: " + id);
    }

    private void applyUpdateCommand(String[] commandArguments) {
        Long id = Long.parseLong(commandArguments[0]);
        String description = commandArguments[1];

        movieService.updateDescription(id, description);
        System.out.println("Обновлено описание фильма с id: " + id + " на " + description);
    }
}
