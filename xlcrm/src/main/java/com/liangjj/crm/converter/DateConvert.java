package com.liangjj.crm.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * @author 小亮
 *springmvc转换器，将页面传递到后台的字符串数据转换成日期格式
 */
public class DateConvert implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date=dateFormat.parse(source);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
