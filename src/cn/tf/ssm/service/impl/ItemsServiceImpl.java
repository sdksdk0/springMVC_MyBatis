package cn.tf.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.tf.ssm.mapper.ItemsMapper;
import cn.tf.ssm.mapper.ItemsMapperCustom;
import cn.tf.ssm.po.Items;
import cn.tf.ssm.po.ItemsCustom;
import cn.tf.ssm.po.QueryItemVo;
import cn.tf.ssm.service.ItemsService;

public class ItemsServiceImpl implements ItemsService{

	@Autowired
	private ItemsMapperCustom itemsMapperCustom;
	@Autowired
	private ItemsMapper  itemsMapper;
	
	@Override
	public List<ItemsCustom> findItemsList(QueryItemVo queryItemsVo)
			throws Exception {
		return itemsMapperCustom.findItemsList(queryItemsVo);
	}


	@Override
	public Items findItemById(Integer id) throws Exception {
		return itemsMapper.selectByPrimaryKey(id);
	}


	@Override
	public void updateItems(Integer id, Items items) throws Exception {
		
		
		
		
		
		items.setId(id);
		itemsMapper.updateByPrimaryKey(items);
		
	}

}
