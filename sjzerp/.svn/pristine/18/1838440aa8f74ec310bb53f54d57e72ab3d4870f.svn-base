package com.qxh.impl.tag;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.model.Stock;
import com.qxh.model.Tag;
import com.qxh.service.TagService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : TagServiceImpl
 * @todo : [标签管理实现层]
 * 
 * 创建时间 ： 2016年11月18日上午11:11:33
 */
public class TagServiceImpl implements TagService {
	
	Logger log = Logger.getLogger(this.getClass());
	private TagMapper tagMapper;
	
	public void setTagMapper(TagMapper tagMapper) {
		this.tagMapper = tagMapper;
	}

	/* 
	 * Todo : [获取列表]
	 * @时间:2016年11月18日上午11:20:39
	 */
	@Override
	public Result getTagList(String page,int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		
		Map<String, Object> param = new HashMap<>();
		param.put("page", (Integer.parseInt(page)-1)*IPageConstants.PageSize);
		param.put("structId", structId);
		try {
			List<Tag> tagList= tagMapper.getTagList(param);
			if(tagList!=null&&tagList.size()>0){
				int l=tagList.size();
				int orderIndex=(Integer.parseInt(page)-1)*IPageConstants.PageSize+1;
				//每一页显示的编号
				for (int i = 0; i < l; i++) {
					Tag tag = tagList.get(i);
					tag.setOrderIndex(orderIndex);
					orderIndex++;
				}
			}
			Map<String, Object> data = new HashMap<>();
			data.put("tagList", tagList);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}

	/* 
	 * Todo : [添加标签]
	 * @时间:2016年11月18日下午1:47:00
	 */
	@Override
	public void addTag(String tagNm, String creator,int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("tagNm", tagNm);
		param.put("creator", creator);
		param.put("createTime",new Date());
		param.put("structId",structId);
		try {
			tagMapper.addTag(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}		
	}

	/* 
	 * Todo : [修改标签]
	 * @时间:2016年11月18日下午1:47:00
	 */
	@Override
	public void editTag(String atNo, String tagNm) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		
		param.put("atNo", atNo);
		param.put("tagNm", tagNm);
		param.put("updataTime", new Date());

		try {
			tagMapper.editTag(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
	}

	/* 
	 * Todo : [通过id获取标签对象]
	 * @时间:2016年11月18日下午2:42:40
	 */
	@Override
	public Result getTagById(String atNo) {
		
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		try {
			Tag tag = tagMapper.getTagById(param);
			result.setData(tag);
			result.setCode(CodeConstant.CODE1000);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}

	/* 
	 * Todo : [删除标签]
	 * @时间:2016年11月18日下午3:00:44
	 */
	@Override
	public void delTag(Integer atNo) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		
		try {
			tagMapper.delTag(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
	}

	/* 
	 * Todo : [获取tag集合]
	 * @时间:2016年11月20日下午10:58:23
	 */
	@Override
	public Result getTagListForCustomer(int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("structId", structId);
			List<Tag> tagListforCustomer= tagMapper.getTagListForCustomer(param);
			Map<String, Object> data = new HashMap<>();
			data.put("tagListforCustomer", tagListforCustomer);
			result.setData(data);
			
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询标签物料
	 */
	@Override
	public Result getTagGoods(String name, String code, String kindCode, 
			String page,String tagId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("name", name);
		param.put("goodsCode", code);
		param.put("kindCode", kindCode);
		param.put("page", (Integer.parseInt(page) - 1) * IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		param.put("tagId", tagId);
		try {
			List<Stock> list = tagMapper.getTagGoods(param);
			int count = tagMapper.getTagGoodsNum(param);
			Map<String, Object> data = new HashMap<>();
			data.put("list", list);
			data.put("totalCount", count);
			data.put("totalPage", PageUtil.getTotalPage(count, IPageConstants.PageSize));
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询标签物料：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + ",name:" + name
					+ ",name:" + name + ",code:" + code + ",page:" + page 
					+ ",kindCode:" + kindCode + "\r\n\r\n");
			e.printStackTrace();
		}
		return result;
	}
	
	
}
