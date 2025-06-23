package com.akshay.rest;

import com.akshay.dto.SearchRequestDto;
import com.akshay.dto.SearchResponseDto;
import com.akshay.service.IReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reports/api")
public class ReportRestController {

    @Autowired
    private IReportService service;

    @GetMapping("/plan-names")
    public ResponseEntity<List<String>> getUniquePlanNames() {
        List<String> uniquePlanNames = service.getUniquePlanNames();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(uniquePlanNames);
    }

    @GetMapping("/plan-status")
    public ResponseEntity<List<String>> getUniquePlanStatuses() {
        List<String> uniquePlanStatuses = service.getUniquePlanStatuses();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(uniquePlanStatuses);
    }

    @PostMapping("/search")
    public ResponseEntity<List<SearchResponseDto>> search(@RequestBody SearchRequestDto searchRequestDto) {
        List<SearchResponseDto> search = service.search(searchRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(search);
    }

    //download report as excel
    @GetMapping("/excel")
    public void downloadExcel(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("application/vnd.malformations-office document.spreadsheet.sheet");
        String fileName = "Reports_" + LocalDate.now() + ".xls";
        servletResponse.setHeader("Content-Disposition",
                "attachment;filename=" + fileName);
        service.generateExcel(servletResponse);
    }

    //download report as PDF
    @GetMapping("/pdf")
    public void downloadPdf(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("application/pdf");
        String fileName = "Reports_" + LocalDate.now() + ".pdf";
        servletResponse.setHeader("Content-Disposition",
                "attachment;filename=" + fileName);
        service.generatePdf(servletResponse);
    }
}
