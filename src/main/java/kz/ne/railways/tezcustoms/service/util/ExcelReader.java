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

            Workbook baeuldungWorkBook = new XSSFWorkbook(file);
            DataFormatter formatter = new DataFormatter();
            Sheet sheet = baeuldungWorkBook.getSheetAt(0);
            invoiceData.setInvoiceNumber(sheet.getRow(0).getCell(3).getStringCellValue());
            invoiceData.setInvoiceDate(sheet.getRow(1).getCell(3).getDateCellValue());
            invoiceData.setShipper(sheet.getRow(2).getCell(3).getStringCellValue());
            invoiceData.setConsignee(sheet.getRow(3).getCell(3).getStringCellValue());

            for (Row row : sheet) {
                if (row.getRowNum() < 7)
                    continue;
                if (row.getCell(0) == null || formatter.formatCellValue(row.getCell(0)).equals("")) {
                    invoiceData.setTotal(formatter.formatCellValue(row.getCell(7)));
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

                invoiceData.addInvoiceItems(invoiceRow);
            }

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
}
