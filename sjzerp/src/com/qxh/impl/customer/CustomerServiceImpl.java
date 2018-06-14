package com.qxh.impl.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.model.Customer;
import com.qxh.model.User;
import com.qxh.service.CustomerService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;
/**
 * 
 * @author Mr chen
 * @name : CustomerServiceImpl
 * @todo : [客户的service实现类]
 * 
 * 创建时间 ： 2016年11月16日下午2:35:57
 */
public class CustomerServiceImpl implements CustomerService {
	
	Logger log = Logger.getLogger(this.getClass());
	
	private CustomerMapper customerMapper;
	public void setCustomerMapper(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}
	
	/**
	 * 客户列表
	 */
	@Override
	public Result getCustomerList(int structId,String customerNm,String page) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("structId", structId);
		param.put("customerNm", customerNm);
		param.put("page", (Integer.parseInt(page)-1)*IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		
		try {
			List<Customer> customerList=customerMapper.getCustomerList(param);
			int totalCount=0,totalPage=1;
			if(customerList!=null&&customerList.size()>0){
				int l=customerList.size();
				int orderIndex=(Integer.parseInt(page)-1)*IPageConstants.PageSize+1;
				//每一页显示的编号
				for (int i = 0; i < l; i++) {
					Customer customer = customerList.get(i);
					customer.setOrderIndex(orderIndex);
					orderIndex++;
				}
			
				totalCount=customerMapper.getCustomerCount(param);
				totalPage=PageUtil.getTotalPage(totalCount, IPageConstants.PageSize);
			}
			Map<String, Object> data = new HashMap<>();
			data.put("customerList", customerList);
			data.put("totalPage", totalPage);
			data.put("totalCount", totalCount);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询供应商列表：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" + "structId:" + structId  + "\r\n\r\n");
			e.printStackTrace();
		}
		return result;
	}




	/* 
	 * Todo : [添加客户]
	 * @时间:2016年11月17日上午8:07:26
	 */
	@Override
	public void addCustomer(String customNm, String phone, String provinceId, String cityId, String countyId,
			String address,int structId,String countermanId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("customNm", customNm);
		param.put("phone", phone);
		param.put("provinceId", provinceId);
		param.put("cityId", cityId);
		param.put("countyId", countyId);
		param.put("address", address);
		param.put("structId", structId);
		param.put("countermanId", countermanId);
		try {
			customerMapper.addCustomer(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		
	}




	/* 
	 * Todo : [删除客户]
	 * @时间:2016年11月17日上午9:28:07
	 */
	@Override
	public void delCustomer(Integer atNo) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		
		try {
			customerMapper.delCustomer(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
	}




	/* 
	 * Todo : [通过id查询客户对象]
	 * @时间:2016年11月17日上午10:50:19
	 */
	@Override
	public Result getCustomerById(String atNo) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		try {
			Customer customer = customerMapper.getCustomerById(param);
			result.setData(customer);
			result.setCode(CodeConstant.CODE1000);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}




	/* 
	 * Todo : [通过id修改对象]
	 * @时间:2016年11月17日下午12:52:12
	 */
	@Override
	public void editCustomer(String atNo, String customNm, String phone, String provinceId, String cityId,
			String countyId, String address,String countermanId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		param.put("customNm", customNm);
		param.put("phone", phone);
		param.put("provinceId", provinceId);
		param.put("cityId", cityId);
		param.put("countyId", countyId);
		param.put("address", address);
		param.put("countermanId", countermanId);

		try {
			customerMapper.editCustomer(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		
	}




	/* 
	 * Todo : [修改标签]
	 * @时间:2016年11月21日上午10:34:21
	 */
	@Override
	public void editCustomTag(String tagArray,String atNo) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		param.put("tagId", tagArray);
		try {
			customerMapper.editCustomTag(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		
	}

	/* 
	 * Todo : [通过strutsId获取客户名称]
	 * @时间:2016年12月8日下午5:59:47
	 */
	@Override
	public Result getCustomByStrustId(int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("structId", structId);
		try {
			List<Customer> list=customerMapper.getCustomByStrustId(param);
			Map<String, Object> data = new HashMap<>();
			data.put("list", list);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询省列表：  errorcode=" + ErrorCode.geterrocode(this)
			+ "  \r\n" + e + "\r\n" );
		}
		return result;
	}

	/* 
	 * Todo : [获取业务员列表]
	 * @时间:2016年12月21日上午11:07:01
	 */
	@Override
	public Result getCountermanList(int structId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("structId", structId);
		try {
			List<User> list=customerMapper.getCountermanList(param);
			Map<String, Object> data = new HashMap<>();
			data.put("list", list);
			result.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
