package cn.tf.ssm.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

//自定义参数转换器
//S源类型，T目标类型
public class CustomDateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

}
