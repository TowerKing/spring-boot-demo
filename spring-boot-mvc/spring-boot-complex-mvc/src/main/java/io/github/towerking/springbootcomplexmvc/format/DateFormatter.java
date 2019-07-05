package io.github.towerking.springbootcomplexmvc.format;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;


/**
 * just for demo
 * */
@Component
public class DateFormatter implements Formatter<Date> {


    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        if (s == null || s.length() == 0) {
            throw new ParseException("日期字符串不能为空", 0);
        }
        try {
            return new Date(s);
        } catch (Exception e) {
            throw new ParseException(s, 0);
        }
    }

    @Override
    public String print(Date date, Locale locale) {
        if (date == null) {
            return null;
        }

        return date.toString() + "--customer";
    }
}
