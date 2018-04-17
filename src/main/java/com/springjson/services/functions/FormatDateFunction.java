package com.springjson.services.functions;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class FormatDateFunction {

    public String FormatDate(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }
}
