package cn.tf.ssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.tf.ssm.group.VaildGroup1;
import cn.tf.ssm.po.Items;
import cn.tf.ssm.po.ItemsCustom;
import cn.tf.ssm.po.QueryItemVo;
import cn.tf.ssm.service.ItemsService;

@Controller
@RequestMapping("/items")
public class ItemsController {
	
	
	@Autowired
	private ItemsService itemsService;
	
	//商品查询
	@RequestMapping("/queryItems")
	public ModelAndView  queryItems(QueryItemVo queryItemsVo) throws Exception {
		
		List<ItemsCustom>  itemsList=itemsService.findItemsList(queryItemsVo);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("itemsList",itemsList);
		modelAndView.setViewName("itemsList");
		return modelAndView;
	}
	
	//修改商品回显
	//限制http请求方法
	@RequestMapping(value="/editItems",method=RequestMethod.GET)
	public String  editItems(Model model,Integer id) throws Exception {
		
		//String id=request.getParameter("id");
		Items items=itemsService.findItemById(id);
		model.addAttribute("items",items);
		
		//返回逻辑视图名
		return "editItems";
	}
	
	//获得商品分类
	@ModelAttribute("itemtypes")
	public Map<String, String> getItemTypes(){
		
		Map<String, String> itemTypes = new HashMap<String,String>();
		itemTypes.put("101", "电脑");
		itemTypes.put("102", "男装");
		
		return itemTypes;
	}
	
/*	@RequestMapping("/editItems")
	public String  editItems(Model model,HttpServletRequest request,@RequestParam(value="id")Integer id) throws Exception {
		
		//String id=request.getParameter("id");
		Items items=itemsService.findItemById(id);
		model.addAttribute("items",items);
		
		return "editItems";
	}*/
	
	//保存修改
	@RequestMapping("/saveItems")
	public String  editItems(Model model,Integer id,@Validated(value={VaildGroup1.class}) Items items,BindingResult bindingResult) throws Exception {
		
		if(bindingResult.hasErrors()){
			List<ObjectError>  allErrors=bindingResult.getAllErrors();
			for (ObjectError objectError : allErrors) {
				System.out.println(objectError.getDefaultMessage());
			}
			model.addAttribute("allErrors",allErrors);
			return "editItems";
		}
		
		
		itemsService.updateItems(id,items);
		
		//重定向到商品的查询
		//return "redirect:queryItems.action";
		
		//转发
		return "forward:queryItems.action";
		
		//return "success";
	}
	
	//批量删除
	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] id) throws Exception{
		
		return "success";
	}
	
	//批量更新
	@RequestMapping("/editAll")
	public String editAll(QueryItemVo queryItemVo){
		
		return "success";
	}
	

}
