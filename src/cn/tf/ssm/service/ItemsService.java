package cn.tf.ssm.service;

import java.util.List;

import cn.tf.ssm.po.Items;
import cn.tf.ssm.po.ItemsCustom;
import cn.tf.ssm.po.QueryItemVo;

public interface ItemsService {
	
	// 商品查询
	public List<ItemsCustom> findItemsList(QueryItemVo queryItemsVo)
			throws Exception;
	
	public Items findItemById(Integer id) throws Exception;
	
	public void updateItems(Integer id,Items items) throws Exception;

}
