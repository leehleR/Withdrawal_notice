package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.service.extra;

import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.WithdrawalNotice;
import com.opencsv.CSVWriter;

import java.io.StringWriter;
import java.util.List;

public class CsvUtil {

    public static String convertNoticeToCsv(List<WithdrawalNotice> notices){
        StringWriter writer = new StringWriter();

        try(CSVWriter csvWriter = new CSVWriter(writer)) {

            String[] header = {
                    "ID",
                    "Date",
                    "Withdrawal Amount",
                    "Product ID",
                    "Product Name",
                    "Product Type",
                    "Investor Name"
            };
            csvWriter.writeNext(header);

            for(WithdrawalNotice notice : notices){
                System.out.println("Id: "+ notice.getCreatedAt());
                String[] data = {

                        String.valueOf(notice.getId()),
                        String.valueOf(notice.getCreatedAt().toLocalDate()),
                        String.valueOf(notice.getWithdrawalAmount()),
                        String.valueOf(notice.getProducts().getProductId()),
                        notice.getProducts().getProductName(),
                        notice.getProducts().getProductType(),
                        notice.getProducts().getInvestor().getFullName()
                };

                csvWriter.writeNext(data);
            }



            return writer.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error converting notices to CSV");
        }
    }
}
