package com.qxh.impl.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.qxh.constant.CodeConstant;
import com.qxh.exmodel.ListGoods;
import com.qxh.impl.common.CommonMapper;
import com.qxh.model.Goods;
import com.qxh.service.GoodsService;
import com.qxh.utils.ErrorCode;
import com.qxh.utils.IPageConstants;
import com.qxh.utils.PageUtil;
import com.qxh.utils.Result;

public class GoodsServiceImpl implements GoodsService {

	Logger log = Logger.getLogger(this.getClass());
	private GoodsMapper goodsMapper;
	private CommonMapper commonMapper;

	public void setGoodsMapper(GoodsMapper goodsMapper) {
		this.goodsMapper = goodsMapper;
	}

	public void setCommonMapper(CommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}

	/**
	 * 物料列表
	 */
	@Override
	public Result getGoodsList(String name, String code, String kindCode, String page) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		// ======去掉name中是否"(")"=======================
		StringBuilder builder = new StringBuilder();
		StringBuilder builderLast = new StringBuilder();
		if (StringUtils.isNotEmpty(name)) {
			String[] split = name.split("[(]");
			for (String string : split) {
				builder.append(string);
			}

			String[] lastArray = builder.toString().split("[)]");
			for (String string : lastArray) {
				builderLast.append(string);
			}
			name = builderLast.toString();
		}
		// ======去掉name中是否"(")"=======================
		Map<String, Object> param = new HashMap<>();
		param.put("name", name);
		param.put("code", code);
		param.put("kindCode", kindCode);
		param.put("page", (Integer.parseInt(page) - 1) * IPageConstants.PageSize);
		param.put("pageSize", IPageConstants.PageSize);
		try {
			List<ListGoods> goodsList = commonMapper.getGoodsList(param);
			int totalCount = 0, totalPage = 1;
			//设置循环序号
			if (goodsList != null && goodsList.size() > 0) {
				int l = goodsList.size();
				int orderIndex = (Integer.parseInt(page) - 1) * IPageConstants.PageSize + 1;
				for (int i = 0; i < l; i++) {
					goodsList.get(i).setOrderIndex(orderIndex);
					orderIndex++;
				}
				totalCount = commonMapper.getGoodsCount(param);
				totalPage = PageUtil.getTotalPage(totalCount, IPageConstants.PageSize);
			}
			Map<String, Object> data = new HashMap<>();
			data.put("goodsList", goodsList);
			data.put("totalPage", totalPage);
			data.put("totalCount", totalCount);
			result.setData(data);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 查询物料列表：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + "page:" + page
					+ ",kindCode:" + kindCode + ",code:" + code + ",name:" + name + "\r\n\r\n");
		}
		return result;
	}

	/**
	 * 添加/修改物料
	 */
	@Override
	public Result addGoods(String goodsId, String code, String goodsNm, String alias, String kindId, String baseUnit,
			String shortNm, String tradiNm, String illegal, String spec, String groupStandardCode, String assistAttr,
			String assistUnit, String sequenceUnit, String helpCode, String barCode, String approvalNumber,
			String picNumber, String weightUnit, String lengthUnit, String grossWeight, String netWeight, String length,
			String width, String height, String volumeUnit, String volume, String equipAttr, String tradeMark,
			String brand, String depict, String keyword, String remark, String summary, String firstLetter,
			String spell, String englishNm, String foreighNm, String groupId, String criteria, String minUnit,
			String minUnitNm) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Goods goods = new Goods();
		goods.setAtNo(Integer.parseInt(goodsId));
		goods.setCode(code);
		goods.setAlias(alias);
		goods.setApprovalNumber(approvalNumber);
		goods.setAssistAttr(assistAttr);
		goods.setAssistUnit(Integer.parseInt(assistUnit));
		goods.setBarCode(barCode);
		goods.setBaseUnit(Integer.parseInt(baseUnit));
		goods.setBrand(brand);
		goods.setCriteria(criteria);
		goods.setDepict(depict);
		goods.setEnglishNm(englishNm);
		goods.setEquipAttr(equipAttr);
		goods.setFirstLetter(firstLetter);
		goods.setFirstLetter(firstLetter);
		goods.setGoodsNm(goodsNm);
		goods.setGrossWeight(Double.parseDouble(grossWeight));
		goods.setGroupId(groupId);
		goods.setGroupStandardCode(groupStandardCode);
		goods.setHeight(Double.parseDouble(height));
		goods.setHelpCode(helpCode);
		goods.setIllegal(Integer.parseInt(illegal));
		goods.setKeyword(keyword);
		goods.setKindId(Integer.parseInt(kindId));
		goods.setLength(Double.parseDouble(length));
		goods.setLengthUnit(Integer.parseInt(lengthUnit));
		goods.setMinUnit(Double.parseDouble(minUnit));
		goods.setMinUnitNm(Integer.parseInt(minUnitNm));
		goods.setNetWeight(Double.parseDouble(netWeight));
		goods.setPicNumber(picNumber);
		goods.setRemark(remark);
		goods.setSequenceUnit(Integer.parseInt(sequenceUnit));
		goods.setShortNm(shortNm);
		goods.setSpec(spec);
		goods.setSpell(spell);
		goods.setSummary(summary);
		goods.setTradeMark(tradeMark);
		goods.setTradiNm(tradiNm);
		goods.setVolume(Double.parseDouble(volume));
		goods.setVolumeUnit(Integer.parseInt(volumeUnit));
		goods.setWeightUnit(Integer.parseInt(weightUnit));
		goods.setWidth(Double.parseDouble(width));
		try {
			// 单位名称
			String unitNm = goodsMapper.getUnitNm(baseUnit);
			goods.setUnitNm(unitNm);
			if (goodsId.equals("-1")) {// 新增物料
				// 物料编码生成
				String newCode = createGoodsCode(goods);
				if (StringUtils.isEmpty(newCode)) {
					result.setCode(CodeConstant.CODE200);
					result.setMsg("该分类已满，无法再添加商品");
					return result;
				}
				goods.setCode(newCode);
				goodsMapper.addGoods(goods);
				goodsMapper.addGoodsDetail(goods);
			} else {// 修改物料
					// 判断是否改变了物料分类
				int oldKindId = goodsMapper.getKindIdByGoodsId(goods);
				if (oldKindId != goods.getKindId()) {
					// 物料分类改变，需要改变物料编码
					String newCode = createGoodsCode(goods);
					if (StringUtils.isEmpty(newCode)) {
						result.setCode(CodeConstant.CODE200);
						result.setMsg("该分类已满，无法再添加商品");
						return result;
					}
					goods.setCode(newCode);
				}
				goodsMapper.editGoods(goods);
				goodsMapper.editGoodsDetail(goods);
			}
		} catch (Exception e) {
			log.error("\r\n 添加/修改物料：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + "goodsId:"
					+ goodsId + ",goodsNm:" + goodsNm + ",alias:" + alias + ",kindId:" + kindId + ",baseUnit:"
					+ baseUnit + ",shortNm:" + shortNm + ",tradiNm:" + tradiNm + ",illegal:" + illegal + ",spec:" + spec
					+ ",groupStandardCode:" + groupStandardCode + ",assistAttr:" + assistAttr + ",assistUnit:"
					+ assistUnit + ",sequenceUnit:" + sequenceUnit + ",helpCode:" + helpCode + ",barCode:" + barCode
					+ ",approvalNumber:" + approvalNumber + ",picNumber:" + picNumber + ",weightUnit:" + weightUnit
					+ ",lengthUnit:" + lengthUnit + ",grossWeight:" + grossWeight + ",netWeight:" + netWeight
					+ ",length:" + length + ",width:" + width + ",height:" + height + ",volumeUnit:" + volumeUnit
					+ ",volume:" + volume + ",equipAttr:" + equipAttr + ",tradeMark:" + tradeMark + ",brand:" + brand
					+ ",depict:" + depict + ",keyword:" + keyword + ",remark:" + remark + ",summary:" + summary
					+ ",firstLetter:" + firstLetter + ",spell:" + spell + ",englishNm:" + englishNm + ",foreighNm:"
					+ foreighNm + ",groupId:" + groupId + ",criteria:" + criteria + ",minUnit:" + minUnit
					+ ",minUnitNm:" + minUnitNm + "\r\n\r\n");
			throw new RuntimeException();
		}
		return result;
	}

	private String createGoodsCode(Goods goods) {
		String maxGoodsCode = goodsMapper.getMaxGoodsCode(goods);
		if (StringUtils.isEmpty(maxGoodsCode))
			maxGoodsCode = "000";
		else
			maxGoodsCode = maxGoodsCode.substring(maxGoodsCode.length() - 3);
		int codeInt = Integer.parseInt(maxGoodsCode) + 1;
		if (codeInt > 1000) {
			return "";
		}
		String newCode = String.valueOf(codeInt);
		if (newCode.length() == 1)
			newCode = "00" + codeInt;
		else if (newCode.length() == 2)
			newCode = "0" + codeInt;
		String kindCode = goodsMapper.getGoodsKindCode(goods);
		newCode = kindCode + newCode;
		return newCode;
	}

	/**
	 * 根据id查询物料
	 */
	@Override
	public Result getGoodsById(String goodsId) {
		Result result = new Result();
		result.setCode(CodeConstant.CODE1000);
		result.setMsg("成功");
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		try {
			Goods goods = goodsMapper.getGoodsById(param);
			result.setData(goods);
		} catch (Exception e) {
			result.setCode(CodeConstant.CODE200);
			result.setMsg("获取数据失败");
			log.error("\r\n 根据id查询物料：  errorcode=" + ErrorCode.geterrocode(this) + "  \r\n" + e + "\r\n" + "goodsId:"
					+ goodsId + "\r\n\r\n");
			e.printStackTrace();
		}
		return result;
	}

}
