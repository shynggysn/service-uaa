package kz.ne.railways.tezcustoms.service.util;

import kz.ne.railways.tezcustoms.service.model.InvoiceData;
import kz.ne.railways.tezcustoms.service.model.InvoiceRow;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ExcelReader {

    private final ResourceLoader resourceLoader;
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public InvoiceData getInvoiceFromFile(InputStream file) throws IOException {
        InvoiceData invoiceData = new InvoiceData();
//        File file = new File(String.valueOf(resourceLoader.getResource("classpath:templateForInvoice.xlsx").getFile()));
        try {
//            FileInputStream inputStream = new FileInputStream(file);
            File tnved = new File(String.valueOf(resourceLoader.getResource("classpath:TNVED.xlsx").getFile()));
            FileInputStream inputStream = new FileInputStream(tnved);
            Workbook tnvedWorkBook = new XSSFWorkbook(inputStream);
            Sheet tnvedSheet = tnvedWorkBook.getSheetAt(0);

            Workbook baeuldungWorkBook = new XSSFWorkbook(file);
            DataFormatter formatter = new DataFormatter();
            Sheet sheet = baeuldungWorkBook.getSheetAt(0);
            invoiceData.setInvoiceNumber(sheet.getRow(1).getCell(2).getStringCellValue());
            invoiceData.setInvoiceDate(sheet.getRow(2).getCell(2).getDateCellValue());
            invoiceData.setShipper(sheet.getRow(3).getCell(2).getStringCellValue());
            invoiceData.setConsignee(sheet.getRow(4).getCell(2).getStringCellValue());

            for (Row row : sheet) {
                if (row.getRowNum() < 7)
                    continue;
                if (row.getCell(2) == null || formatter.formatCellValue(row.getCell(2)).equals("")) {
                    invoiceData.setTotal(formatter.formatCellValue(row.getCell(8)));
                    break;
                }

                InvoiceRow invoiceRow = new InvoiceRow();
                invoiceRow.setName(formatter.formatCellValue(row.getCell(1)));
                invoiceRow.setCode(formatter.formatCellValue(row.getCell(2)));
                invoiceRow.setUnit(formatter.formatCellValue(row.getCell(3)));
                invoiceRow.setQuantity(formatter.formatCellValue(row.getCell(4)));
                invoiceRow.setNetto(formatter.formatCellValue(row.getCell(5)));
                invoiceRow.setBrutto(formatter.formatCellValue(row.getCell(6)));
                invoiceRow.setPrice(formatter.formatCellValue(row.getCell(7)));
                invoiceRow.setTotalPrice(formatter.formatCellValue(row.getCell(8)));
                invoiceRow.setDescription(getDescriptionTNVED(tnvedSheet, invoiceRow.getCode()));
                invoiceData.addInvoiceItems(invoiceRow);

                System.out.println(invoiceRow.getDescription());
            }
            inputStream.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return invoiceData;
    }

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    private String getDescriptionTNVED(Sheet sheet, String code) throws IOException {
        StringBuilder result = null;
        for (int i = 0; i < 21146; i++) {
            String cell = sheet.getRow(i).getCell(0).getStringCellValue();
            if (cell.equals(code)) {
                String value = sheet.getRow(i).getCell(1).getStringCellValue();
                int counter = StringUtils.countOccurrencesOf(value, "—");
                result = new StringBuilder(value.substring(counter * 2));
                while (counter > 0) {
                    for (int j = i; j > 0; j--) {
                        String parent = sheet.getRow(j).getCell(1).getStringCellValue();
                        int count = StringUtils.countOccurrencesOf(parent, "—");
                        if (count<counter) {
                            result.insert(0, parent.substring(count * 2) + " ");
                            counter --;
                        }
                    }
                }
                break;
            }
        }
        return result.toString();
    }
}
