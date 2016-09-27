package cn.tf.ssm.mapper;

import cn.tf.ssm.po.Items;
import cn.tf.ssm.po.ItemsCustom;
import cn.tf.ssm.po.ItemsExample;
import cn.tf.ssm.po.QueryItemVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemsMapperCustom {
	// 商品查询
		public List<ItemsCustom> findItemsList(QueryItemVo queryItemsVo)
				throws Exception;
}