package com.qxh.impl.tag;

import java.util.List;
import java.util.Map;

import com.qxh.model.Stock;
import com.qxh.model.Tag;

public interface TagMapper {

	/**
	 * @description : [查询标签列表]
	 * @param param
	 * @return
	 * @时间:2016年11月18日上午11:25:06
	 */
	List<Tag> getTagList(Map<String, Object> param);

	/**
	 * @description : [添加标签]
	 * @param param
	 * @时间:2016年11月18日下午1:50:52
	 */
	void addTag(Map<String, Object> param);

	/**
	 * @description : [通过id获取标签对象]
	 * @param param
	 * @return
	 * @时间:2016年11月18日下午2:44:43
	 */
	Tag getTagById(Map<String, Object> param);

	/**
	 * @description : [修改标签]
	 * @param param
	 * @时间:2016年11月18日下午2:54:26
	 */
	void editTag(Map<String, Object> param);

	/**
	 * @description : [删除标签]
	 * @param param
	 * @时间:2016年11月18日下午3:01:50
	 */
	void delTag(Map<String, Object> param);

	/**
	 * @param param 
	 * @description : [获取tag集合]
	 * @return
	 * @时间:2016年11月20日下午11:00:20
	 */
	List<Tag> getTagListForCustomer(Map<String, Object> param);
	/**
	 * 查询标签物料
	 * @param param
	 * @return
	 */
	List<Stock> getTagGoods(Map<String, Object> param);
	/**
	 * 查询标签物料数量
	 * @param param
	 * @return
	 */
	int getTagGoodsNum(Map<String, Object> param);

	
}
