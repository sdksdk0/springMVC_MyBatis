package cn.tf.ssm.po;

import java.util.List;

public class QueryItemVo {
	
	//接收页面的批量提交
	private List<Items> itemsList;
	
	
	//商品信息
	private Items items;
	
	//商品扩展
	private ItemsCustom itemsCustom;

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}

	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}

	public List<Items> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<Items> itemsList) {
		this.itemsList = itemsList;
	}
	
	
	

}
