package com.example.dotestonline.controller;

import com.example.dotestonline.dto.AnswerDTO;
import com.example.dotestonline.dto.QuestionDTO;
import com.example.dotestonline.service.QuestionService;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("admin")
public class AdminController {
    private QuestionService questionService;

    @Autowired
    public AdminController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("")
    public String admin() {
        return "AdminHome";
    }

    @PostMapping("/upload/file")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        //check file
        try {
            List<QuestionDTO> listAdded = processExcelFile(multipartFile);
            return ResponseEntity.ok("You have added " + listAdded.size() + " questions");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error upload question");
        }
    }
    @GetMapping(value="/download/template", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public byte[] downloadSampleTemplate() throws IOException {
        Path path= Paths.get("src/main/resources/templates/file/template.xlsx");
        byte[] template= Files.readAllBytes(path);
        return template;
    }

    public List<QuestionDTO> processExcelFile(MultipartFile multipartFile) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(multipartFile.getInputStream());
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        //create list of questions
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        Iterator<Row> rowIterator = xssfSheet.iterator();
        //skip the first row
        rowIterator.next();
        while (rowIterator.hasNext()) {
            XSSFRow xssfRow = (XSSFRow) rowIterator.next();
            XSSFCell cell0 = xssfRow.getCell(0);
            XSSFCell cell1 = xssfRow.getCell(1);
            XSSFCell cell2 = xssfRow.getCell(2);
            if (cell0 == null || cell1 == null || cell2 == null) {
                throw new RuntimeException("Cell is null at row" + xssfRow.getRowNum());
            }
            //create a question
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestionText(xssfRow.getCell(0).getStringCellValue().replaceAll("^\\s+|\\s+$", ""));
            questionDTO.setQuestionType(xssfRow.getCell(2).getStringCellValue().toLowerCase().replaceAll("^\\s+|\\s+$", ""));
            String correctAnswerTemp = xssfRow.getCell(1).getStringCellValue().replaceAll("^\\s+|\\s+$", "");
            //create a list of answers
            List<AnswerDTO> answerOptions = new ArrayList<>();
            for (int i = 3; i < xssfRow.getLastCellNum(); i++) {
                XSSFCell cell = xssfRow.getCell(i);
                if (cell != null) {
                    if (cell.getCellType() == CellType.STRING) {
                        String option = (char) ('A' + i - 3) + ". " + xssfRow.getCell(i).getStringCellValue().replaceAll("^\\s+|\\s+$", "");
                        boolean isCorrectAnswer = correctAnswerTemp.equals(option.substring(0, option.indexOf(".")));
                        answerOptions.add(new AnswerDTO(option, isCorrectAnswer, questionDTO));
                    } else {
                        String option = (char) ('A' + i - 3) + ". " + xssfRow.getCell(i).getNumericCellValue();
                        boolean isCorrectAnswer = correctAnswerTemp.equals(option.substring(0, option.indexOf(".")));
                        answerOptions.add(new AnswerDTO(option, isCorrectAnswer, questionDTO));
                    }
                } else {
                    throw new RuntimeException("Cell is null at row " + xssfRow.getRowNum() + ", column " + i);
                }
            }
            questionDTO.setAnswers(answerOptions);
            questionDTOS.add(questionDTO);
        }
        questionService.createMultipleQuestion(questionDTOS);
        return questionDTOS;
    }
}
