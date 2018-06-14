/**
 * 
 */
package com.qxh.impl.customerteam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.impl.demand.DemandMapper;
import com.qxh.impl.purchase.PurchaseMapper;
import com.qxh.model.CustomTeam;
import com.qxh.model.DemandListD;
import com.qxh.model.PurchaseListD;
import com.qxh.service.CustomerteamService;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;

/**
 * @author Mr chen
 * @name : CustomerteamServiceImpl
 * @todo : [工作组]
 * 
 *       创建时间 ： 2016年11月17日下午2:45:41
 */
public class CustomerteamServiceImpl implements CustomerteamService {

	Logger log = Logger.getLogger(this.getClass());

	private CustomerteamMapper customerteamMapper;
	public void setCustomerteamMapper(CustomerteamMapper customerteamMapper) {
		this.customerteamMapper = customerteamMapper;
	}
	
	private DemandMapper demandMapper;
	public void setDemandMapper(DemandMapper demandMapper) {
		this.demandMapper = demandMapper;
	}
	
	private PurchaseMapper purchaseMapper;
	public void setPurchaseMapper(PurchaseMapper purchaseMapper) {
		this.purchaseMapper = purchaseMapper;
	}

	/*
	 * Todo : [通过客户id查询工作组列表]
	 * 
	 * @时间:2016年11月17日下午2:46:14
	 */
	@Override
	public Result getCustomerList(String headAtNo, String teamNm, String page) {

		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();

		param.put("headAtNo", headAtNo);
		param.put("teamNm", teamNm);
		param.put("page", (Integer.parseInt(page) - 1) * IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);

		try {
			List<CustomTeam> customerteamList = customerteamMapper.getCustomerteamList(param);
			int totalCount = 0, totalPage = 1;
			if (customerteamList != null && customerteamList.size() > 0) {
				int l = customerteamList.size();
				int orderIndex = (Integer.parseInt(page) - 1) * IPageConstants.PageSize + 1;
				// 每一页显示的编号
				for (int i = 0; i < l; i++) {
					CustomTeam customTeam = customerteamList.get(i);
					customTeam.setOrderIndex(orderIndex);
					orderIndex++;
				}

				totalCount = customerteamMapper.getCustomerteamCount(param);
				totalPage = PageUtil.getTotalPage(totalCount, IPageConstants.PageSize);
			}
			Map<String, Object> data = new HashMap<>();
			data.put("customerteamList", customerteamList);
			data.put("totalPage", totalPage);
			data.put("totalCount", totalCount);
			data.put("headAtNo", headAtNo);
			result.setData(data);
			result.setCode(CodeConstant.CODE1000);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}

	/* 
	 * Todo : [添加工作组]
	 * @时间:2016年11月17日下午5:27:03
	 */
	@Override
	public void addCustomerteam(String teamNm, String headAtNo) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("teamNm", teamNm);
		param.put("headAtNo", headAtNo);
		try {
			customerteamMapper.addCustomerteam(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		
	}

	/* 
	 * Todo : [通过id获取工作组]
	 * @时间:2016年11月17日下午6:20:47
	 */
	@Override
	public Result getCustomerteamById(String atNo) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		try {
			CustomTeam customTeam = customerteamMapper.getCustomerteamById(param);
			result.setData(customTeam);
			result.setCode(CodeConstant.CODE1000);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}

	/* 
	 * Todo : [修改工作组名称]
	 * @时间:2016年11月17日下午6:31:55
	 */
	@Override
	public void editCustomerteam(String atNo, String teamNm) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		
		param.put("atNo", atNo);
		param.put("teamNm", teamNm);

		try {
			customerteamMapper.editCustomerteam(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
	}

	/* 
	 * Todo : [删除工作组]
	 * @时间:2016年11月17日下午7:16:34
	 */
	@Override
	public void delCustomerteam(Integer atNo) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("atNo", atNo);
		
		try {
			customerteamMapper.delCustomerteam(param);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		
	}

	/* 
	 * Todo : [通过账单id查询工作组]
	 * @时间:2016年11月23日下午4:30:12
	 */
	@Override
	public Result getCustomTeamNameList(String teamBillId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("teamBillId", teamBillId);
		try {
			List<DemandListD> teamIdList = demandMapper.getTeamIdListByBillId(param);
			StringBuffer buffer = new StringBuffer();
			for (DemandListD demandListD : teamIdList) {
				buffer.append(demandListD.getTeamId()).append(",");
			}
			String strIds = buffer.toString();
			if(StringUtils.isEmpty(strIds)){
				result.setMsg("账单不存在明细表");
				return result;
			}
			String teamIds = strIds.substring(0, strIds.length()-1);
			param.put("teamIds", teamIds);
			List<CustomTeam> customTeamNameList = customerteamMapper.getCustomTeamNameListByBillId(param);
			Map<String, Object> data = new HashMap<>();
			data.put("customTeamNameList", customTeamNameList);
			result.setData(data);
			result.setCode(CodeConstant.CODE1000);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}

	/* 
	 * Todo : [通过采购单id查询工作组]
	 * @时间:2016年11月25日上午9:03:07
	 */
	@Override
	public Result getCustomTeamNameListByPurchase(String teamBillId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("teamBillId", teamBillId);
		try {
			List<PurchaseListD> teamIdList = purchaseMapper.getTeamIdListByBillId(param);
			StringBuffer buffer = new StringBuffer();
			for (PurchaseListD purchaseListD : teamIdList) {
				buffer.append(purchaseListD.getTeamId()).append(",");
			}
			String strIds = buffer.toString();
			if(StringUtils.isEmpty(strIds)){
				result.setCode(CodeConstant.CODE200);
				result.setMsg("账单不存在明细表");
				return result;
			}
			String teamIds = strIds.substring(0, strIds.length()-1);
			param.put("teamIds", teamIds);
			List<CustomTeam> customTeamNameList = customerteamMapper.getCustomTeamNameListByBillId(param);
			if(teamIds.contains("-2")){
				CustomTeam team=new CustomTeam();
				team.setAtNo(-2);
				team.setTeamNm("配送中心");
				customTeamNameList.add(team);
			}
			Map<String, Object> data = new HashMap<>();
			data.put("customTeamNameList", customTeamNameList);
			result.setData(data);
			result.setCode(CodeConstant.CODE1000);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			e.printStackTrace();
		}
		return result;
	}

}
