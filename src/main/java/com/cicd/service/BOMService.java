package com.cicd.service;

import com.cicd.model.BOM;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BOMService {
    public String convertToJSON(BOM bom) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(bom);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BOM parseToBOM(String text) {
        BOM bom = new BOM();

        // Example regex pattern to match part number, description, and quantity
        Pattern pattern = Pattern.compile("(\\S+)\\s+(\\S+.*)\\s+(\\d+)");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String partNumber = matcher.group(1);
            String description = matcher.group(2);
            int quantity = Integer.parseInt(matcher.group(3));
            BOM.BOMItem item = new BOM.BOMItem(partNumber, description, quantity);
            bom.addItem(item);
        }
        return bom;
    }
}
