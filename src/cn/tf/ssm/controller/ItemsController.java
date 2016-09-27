package cn.tf.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.tf.ssm.po.Items;
import cn.tf.ssm.po.ItemsCustom;
import cn.tf.ssm.service.ItemsService;

@Controller
@RequestMapping("/items")
public class ItemsController {
	
	
	@Autowired
	private ItemsService itemsService;
	
	@RequestMapping("/queryItems")
	public ModelAndView  queryItems() throws Exception {
		
		List<ItemsCustom>  itemsList=itemsService.findItemsList(null);
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
	
	
	
/*	@RequestMapping("/editItems")
	public String  editItems(Model model,HttpServletRequest request,@RequestParam(value="id")Integer id) throws Exception {
		
		//String id=request.getParameter("id");
		Items items=itemsService.findItemById(id);
		model.addAttribute("items",items);
		
		return "editItems";
	}*/
	
	//保存修改
	@RequestMapping("/saveItems")
	public String  editItems(Integer id,Items items) throws Exception {
		itemsService.updateItems(id,items);
		
		//重定向到商品的查询
		//return "redirect:queryItems.action";
		
		//转发
		return "forward:queryItems.action";
		
		//return "success";
	}
	
	

}
