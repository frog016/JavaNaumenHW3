package org.example.JavaNaumenHW3.Report;

import org.example.JavaNaumenHW3.Entity.Movie;
import org.example.JavaNaumenHW3.Repository.MovieRepository;
import org.example.JavaNaumenHW3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportService {
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(UserRepository userRepository, MovieRepository movieRepository, ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.reportRepository = reportRepository;
    }

    public Long createReport() {
        Report report = new Report();
        reportRepository.save(report);
        return report.getId();
    }

    public String getReportContent(Long reportId) {
        Report report = reportRepository.findById(reportId).orElse(null);
        if (report == null) {
            return "Отчет не найден.";
        }
        if (report.getStatus() == ReportStatus.CREATED) {
            return "Отчет еще не сформирован.";
        }
        if (report.getStatus() == ReportStatus.ERROR) {
            return "Ошибка при формировании отчета: " + report.getContent();
        }
        return report.getContent();
    }

    @Async
    public CompletableFuture<Void> generateReportAsync(Long reportId) {
        Report report = reportRepository.findById(reportId).orElse(null);
        if (report == null) {
            return CompletableFuture.completedFuture(null);
        }

        try {
            Long startTime = System.currentTimeMillis();

            CompletableFuture<Long> userCountFuture = CompletableFuture.supplyAsync(userRepository::count);
            CompletableFuture<List<Movie>> movieListFeature = CompletableFuture.supplyAsync(() -> (List<Movie>) movieRepository.findAll());

            Long userCountStartTime = System.currentTimeMillis();
            Long userCount = userCountFuture.join();
            Long userCountEndTime = System.currentTimeMillis();
            Long userCountElapsedTime = userCountEndTime - userCountStartTime;

            Long movieListStartTime = System.currentTimeMillis();
            List<Movie> movies = movieListFeature.join();
            Long movieListEndTime = System.currentTimeMillis();
            Long movieListElapsedTime = movieListEndTime - movieListStartTime;

            StringBuilder contentBuilder = new StringBuilder();
            contentBuilder.append("<h1>Отчет о статистике приложения</h1>");
            contentBuilder.append("<p>Количество зарегистрированных пользователей: ").append(userCount).append("</p>");
            contentBuilder.append("<p>Время на получение количества пользователей: ").append(userCountElapsedTime).append(" мс</p>");
            contentBuilder.append("<h2>Список объектов:</h2><ul>");

            for (Movie movie : movies) {
                contentBuilder.append("<li>").append(movie.toString()).append("</li>");
            }
            contentBuilder.append("</ul>");
            contentBuilder.append("<p>Время на получение списка объектов: ").append(movieListElapsedTime).append(" мс</p>");

            Long endTime = System.currentTimeMillis();
            Long totalTime = endTime - startTime;
            contentBuilder.append("<p>Общее время формирования отчета: ").append(totalTime).append(" мс</p>");

            report.setContent(contentBuilder.toString());
            report.setStatus(ReportStatus.COMPLETED);
        } catch (Exception e) {
            report.setStatus(ReportStatus.ERROR);
            report.setContent("Ошибка при формировании отчета: " + e.getMessage());
        } finally {
            reportRepository.save(report);
        }

        return CompletableFuture.completedFuture(null);
    }
}

