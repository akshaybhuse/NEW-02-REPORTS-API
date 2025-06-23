package com.akshay.service;

import com.akshay.dto.SearchRequestDto;
import com.akshay.dto.SearchResponseDto;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface IReportService {

    List<String> getUniquePlanNames();

    List<String> getUniquePlanStatuses();

    List<SearchResponseDto> search(SearchRequestDto searchRequestDto);

    void generateExcel(HttpServletResponse httpServletResponse) throws IOException;

    void generatePdf(HttpServletResponse httpServletResponse) throws IOException;

}
