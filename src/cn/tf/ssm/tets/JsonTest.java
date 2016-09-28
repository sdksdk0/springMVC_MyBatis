package cn.tf.ssm.tets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tf.ssm.po.Items;
@Controller
public class JsonTest {
	
	@RequestMapping("/requestJson")
	public @ResponseBody Items requestJson(@RequestBody Items items)throws Exception{
		
		return items;
		
	}
	
	//请求key/value、响应json
	//使用responseBody将java对象响应为json串
	@RequestMapping("/responseJson")
	public  @ResponseBody Items responseJson(Items items)throws Exception{
		
		
		return items;
		
	}
	
	

}
