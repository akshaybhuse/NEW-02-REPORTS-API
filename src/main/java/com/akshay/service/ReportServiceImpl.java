package com.akshay.service;

import com.akshay.dto.SearchRequestDto;
import com.akshay.dto.SearchResponseDto;
import com.akshay.entity.PersonEligibilityDetailsEntity;
import com.akshay.repo.PersonEligibilityDetailsRepo;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private PersonEligibilityDetailsRepo repo;

    @Override
    public List<String> getUniquePlanNames() {
        return repo.findUniquePlanNames();
       /* return repo.findAll()
                .stream()
                .map(PersonEligibilityDetailsEntity::getPlanName)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .toList();*/
    }

    @Override
    public List<String> getUniquePlanStatuses() {
        return repo.findUniquePlanStatuses();
        /*return repo.findAll()
                .stream()
                .map(PersonEligibilityDetailsEntity::getPlanStatus)
                .distinct()
                .filter(Objects::nonNull)
                .sorted()
                .toList();*/
    }

    @Override
    public List<SearchResponseDto> search(SearchRequestDto searchRequestDto) {
        PersonEligibilityDetailsEntity queryBuilder = new PersonEligibilityDetailsEntity();

        String planName = searchRequestDto.getPlanName();
        if (planName != null && !planName.isEmpty()) {
            queryBuilder.setPlanName(planName);
        }

        String planStatus = searchRequestDto.getPlanStatus();
        if (planStatus != null && !planStatus.isEmpty()) {
            queryBuilder.setPlanStatus(planStatus);
        }

        LocalDate planStartDate = searchRequestDto.getPlanStartDate();
        if (planStartDate != null) {
            queryBuilder.setPlanStartDate(planStartDate);
        }

        LocalDate planEndDate = searchRequestDto.getPlanEndDate();
        if (planEndDate != null) {
            queryBuilder.setPlanEndDate(planEndDate);
        }
        Example<PersonEligibilityDetailsEntity> entityExample = Example.of(queryBuilder);

        List<PersonEligibilityDetailsEntity> list = repo.findAll(entityExample);

        List<SearchResponseDto> searchResponseDtos = list.stream()
                .map(response -> {
                            SearchResponseDto searchResponseDto = new SearchResponseDto();
                            BeanUtils.copyProperties(response, searchResponseDto);
                            return searchResponseDto;
                        }
                ).toList();
        return searchResponseDtos;
    }

    @Override
    public void generateExcel(HttpServletResponse httpServletResponse) throws IOException {

        List<PersonEligibilityDetailsEntity> entityList = repo.findAll();

        try (HSSFWorkbook workbook = new HSSFWorkbook()) {

            HSSFSheet sheet = workbook.createSheet("Report");
            HSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Name");
            headerRow.createCell(1).setCellValue("Email");
            headerRow.createCell(2).setCellValue("Mobile Number");
            headerRow.createCell(3).setCellValue("Gender");
            headerRow.createCell(4).setCellValue("SSN");

            AtomicInteger rowNumber = new AtomicInteger(1);
            entityList.forEach(entities -> {
                //int i = 1;
                HSSFRow dataRow = sheet.createRow(rowNumber.getAndIncrement());
                dataRow.createCell(0).setCellValue(entities.getPersonName());
                dataRow.createCell(1).setCellValue(entities.getPersonEmail());
                dataRow.createCell(2).setCellValue(String.valueOf(entities.getPersonMobileNo()));
                dataRow.createCell(3).setCellValue(String.valueOf(entities.getPersonGender()));
                dataRow.createCell(4).setCellValue(String.valueOf(entities.getPersonSsn()));
                // i++;
            });
            workbook.write(httpServletResponse.getOutputStream());  // send file to client
            httpServletResponse.flushBuffer();
        }
    }

    @Override
    public void generatePdf(HttpServletResponse httpServletResponse) throws IOException {

        List<PersonEligibilityDetailsEntity> entityList = repo.findAll();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, httpServletResponse.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Color.DARK_GRAY);
        Paragraph p = new Paragraph("Search Report", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 1.5f, 3.0f});
        table.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        //font = FontFactory.getFont(FontFactory.HELVETICA);
        font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Name", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Mobile Number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Gender", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SSN", font));
        table.addCell(cell);

        entityList.forEach(entity -> {
            table.addCell(entity.getPersonName());
            table.addCell(entity.getPersonEmail());
            table.addCell(String.valueOf((entity.getPersonMobileNo())));
            table.addCell(String.valueOf(entity.getPersonGender()));
            table.addCell(String.valueOf(entity.getPersonSsn()));
        });
        document.add(table);
        document.close();
    }
}
