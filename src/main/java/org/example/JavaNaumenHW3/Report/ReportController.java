package org.example.JavaNaumenHW3.Report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public Long createReport() {
        Long reportId = reportService.createReport();
        reportService.generateReportAsync(reportId);
        return reportId;
    }

    @GetMapping("/{id}")
    public String getReportContent(@PathVariable Long id) {
        return reportService.getReportContent(id);
    }
}

