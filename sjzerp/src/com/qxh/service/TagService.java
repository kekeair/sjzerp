package com.qxh.service;

import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : TagService
 * @todo : [标签管理接口]
 * 
 * 创建时间 ： 2016年11月18日上午11:10:42
 */
public interface TagService {


	/**
	 * @description : [添加标签]
	 * @param tagNm
	 * @param creatorId
	 * @时间:2016年11月18日下午1:45:18
	 */
	void addTag(String tagNm, String creatorId,int structId);

	/**
	 * @description : [修改标签]
	 * @param atNo
	 * @param tagNm
	 * @param creatorId
	 * @时间:2016年11月18日下午1:45:34
	 */
	void editTag(String atNo, String tagNm);

	/**
	 * @description : [获取标签列表]
	 * @param page
	 * @param structId
	 * @return
	 * @时间:2016年11月18日下午2:12:39
	 */
	Result getTagList(String page, int structId);

	/**
	 * @description : [通过id获取标签对象]
	 * @param atNo
	 * @return
	 * @时间:2016年11月18日下午2:42:16
	 */
	Result getTagById(String atNo);

	/**
	 * @description : [删除标签]
	 * @param atNo
	 * @时间:2016年11月18日下午3:00:25
	 */
	void delTag(Integer atNo);

	/**
	 * @description : [获取tag集合]
	 * @return
	 * @时间:2016年11月20日下午10:58:08
	 */
	Result getTagListForCustomer(int structId);
	/**
	 * 查询标签物料
	 * @param name
	 * @param code
	 * @param kindCode
	 * @param page
	 * @return
	 */
	Result getTagGoods(String name, String code, String kindCode, 
			String page,String tagId);
	

}
