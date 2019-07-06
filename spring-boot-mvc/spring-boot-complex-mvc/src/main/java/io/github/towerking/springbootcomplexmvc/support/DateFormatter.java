package io.github.towerking.springbootcomplexmvc.support;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * just for demo
 * */
@Component
public class DateFormatter implements Formatter<Date> {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        if (s == null || s.length() == 0) {
            throw new ParseException("日期字符串不能为空", 0);
        }
        try {
            return simpleDateFormat.parse(s);
        } catch (Exception e) {
            throw new ParseException(s, 0);
        }
    }

    @Override
    public String print(Date date, Locale locale) {
        if (date == null) {
            return null;
        }
        // 暂时还没有看到这个东西是怎么弄的，做过好几次测试，代码都未进入
        return simpleDateFormat.format(date);
    }
}
