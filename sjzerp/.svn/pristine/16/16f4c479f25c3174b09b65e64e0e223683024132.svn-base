package com.qxh.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qxh.service.CommonService;

/**
 * @ClassName: SpringMVCInterceptor
 * @Description: 操作日志处理
 * @author chenyang
 * @date 2015年10月23日 上午11:25:37
 * 
 */
public class SpringMVCInterceptor extends HandlerInterceptorAdapter {

	private CommonService service;

	public void setService(CommonService service) {
		this.service = service;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String url = request.getRequestURI();
//		SysUser mUser = (SysUser) SessionUtil.getSession().getAttribute(
//				"sysUser");
//		String epToken = (String) SessionUtil.getSession().getAttribute(
//				"epToken");
//
//		// 记录登录日志
//		if (mUser != null && StringUtils.isNotEmpty(url)) {
////			SysUser sysUser = service.getSysUserById(mUser.getId());
////			if (sysUser != null && !StringUtils.isEmpty(sysUser.getEpToken())
////					&& !StringUtils.isEmpty(epToken)
////					&& !epToken.equals(sysUser.getEpToken())) {
////				SessionUtil.getSession().removeAttribute("userAccount");
////				SessionUtil.getSession().removeAttribute("userPwd");
////				SessionUtil.getSession().removeAttribute("epToken");
////				modelAndView.addObject("epLoginToken", 1);
////			}
//			extractedLog(request, url, mUser);
//		}

	}

//	private void extractedLog(HttpServletRequest request, String url,
//			SysUser mUser) {
//		EnterpriseInfo enterpriseInfo = service.getEnterPriseByEpId(mUser
//				.getEpId() + "");
//		String content = "";
//		String way = "";
//		if (url.contains("updateEpInfo.do")) {
//			content = mUser.getUserName() + "更新店铺资料";
//			way = "配置中心/店铺资料";
//		} else if (url.contains("updateEpHead.do")) {
//			content = mUser.getUserName() + "更新【"
//					+ enterpriseInfo.getStoreName() + "】商家头像";
//			way = "配置中心/店铺资料";
//		} else if (url.contains("updateEpScenePic.do")) {
//			content = mUser.getUserName() + "更新【"
//					+ enterpriseInfo.getStoreName() + "】商家场景图片";
//			way = "配置中心/店铺资料";
//		} else if (url.contains("customVipPropertySave.do")) {
//			content = mUser.getUserName() + "更新【"
//					+ enterpriseInfo.getStoreName() + "】商家自定义属性";
//			way = "常规/熟客管理/熟客列表";
//		} else if (url.contains("updateBranch.do")) {
//			content = mUser.getUserName() + "更新【"
//					+ enterpriseInfo.getStoreName() + "】门店信息";
//			way = "配置中心/店铺资料";
//		} else if (url.contains("operRechargePackage.do")) {
//			content = mUser.getUserName() + "修改【"
//					+ enterpriseInfo.getStoreName() + "】商家充值套餐";
//			way = "配置中心/充值套餐设置";
//		} else if (url.contains("delRechargePackage.do")) {
//			content = mUser.getUserName() + "删除【"
//					+ enterpriseInfo.getStoreName() + "】商家充值套餐";
//			way = "配置中心/充值套餐设置";
//		} else if (url.contains("operClerk.do")) {
//			content = mUser.getUserName() + "新增或者修改【"
//					+ enterpriseInfo.getStoreName() + "】商家店员";
//			way = "配置中心/店员管理";
//		} else if (url.contains("delClerk.do")) {
//			content = mUser.getUserName() + "删除【"
//					+ enterpriseInfo.getStoreName() + "】商家店员";
//			way = "配置中心/店员管理";
//		} else if (url.contains("editJoinGift.do")) {
//			content = mUser.getUserName() + "编辑【"
//					+ enterpriseInfo.getStoreName() + "】普通熟客特权";
//			way = "配置中心/会员等级";
//		} else if (url.contains("operVipPri.do")) {
//			content = mUser.getUserName() + "新增或编辑【"
//					+ enterpriseInfo.getStoreName() + "】会员等级";
//			way = "配置中心/会员等级";
//		} else if (url.contains("delVipPri.do")) {
//			content = mUser.getUserName() + "删除【"
//					+ enterpriseInfo.getStoreName() + "】会员等级";
//			way = "配置中心/会员等级";
//		}
//		/*
//		 * else if (url.contains("contributeRecovery.do")) { content =
//		 * mUser.getUserName() + "恢复【" + enterpriseInfo.getStoreName() +
//		 * "】商家默认设置"; way = "设置/贡献值设置"; } else if
//		 * (url.contains("editContribute.do")) { content = mUser.getUserName() +
//		 * "修改【" + enterpriseInfo.getStoreName() + "】商家贡献设置"; way = "设置/贡献值设置";
//		 * }
//		 */
//		/*
//		 * else if (url.contains("pointRecovery.do")) { content =
//		 * mUser.getUserName() + "恢复【" + enterpriseInfo.getStoreName() +
//		 * "】商家默认设置"; way = "积分设置"; } else if (url.contains("editPoint.do")) {
//		 * content = mUser.getUserName() + "编辑【" + enterpriseInfo.getStoreName()
//		 * + "】商家默认设置"; way = "积分设置"; } else if
//		 * (url.contains("vipLevelRecovery.do")) { content = mUser.getUserName()
//		 * + "恢复【" + enterpriseInfo.getStoreName() + "】商家VIP等级默认设置"; way =
//		 * "设置/VIP等级设置"; } else if (url.contains("editVipLevel.do")) { content =
//		 * mUser.getUserName() + "编辑【" + enterpriseInfo.getStoreName() +
//		 * "】商家VIP等级设置"; way = "设置/VIP等级设置"; }
//		 */
//		else if (url.contains("editFansInfo.do")) {
//			content = mUser.getUserName() + "修改【"
//					+ enterpriseInfo.getStoreName() + "】商家熟客信息";
//			way = "常规/熟客管理/熟客列表";
//		} else if (url.contains("atFans.do")) {
//			content = mUser.getUserName() + "@【"
//					+ enterpriseInfo.getStoreName() + "】商家熟客";
//			way = "常规/熟客管理/熟客列表";
//		} else if (url.contains("operExactGroup.do")) {
//			content = mUser.getUserName() + "创建或修改【"
//					+ enterpriseInfo.getStoreName() + "】商家条件组";
//			way = "精准营销/精准规则/规则列表";
//		} else if (url.contains("delExactGroup.do")) {
//			content = mUser.getUserName() + "删除【"
//					+ enterpriseInfo.getStoreName() + "】商家条件组";
//			way = "精准营销/精准规则/规则列表";
//		} else if (url.contains("sendGift.do")) {
//			content = mUser.getUserName() + "发送【"
//					+ enterpriseInfo.getStoreName() + "】商家赠品";
//			way = "精准营销/精准规则/规则列表";
//		} else if (url.contains("updateTopicState.do")) {
//			content = mUser.getUserName() + "更新【"
//					+ enterpriseInfo.getStoreName() + "】商家话题状态";
//			way = "精准营销/话题/话题列表";
//		} else if (url.contains("delTopic.do")) {
//			content = mUser.getUserName() + "删除【"
//					+ enterpriseInfo.getStoreName() + "】商家话题";
//			way = "精准营销/话题/话题列表";
//		} else if (url.contains("operTopic.do")) {
//			content = mUser.getUserName() + "添加或修改【"
//					+ enterpriseInfo.getStoreName() + "】商家话题";
//			way = "精准营销/话题/话题列表";
//		}
//
//		/*
//		 * else if (url.contains("cancelEnroll.do")) { content =
//		 * mUser.getUserName() + "取消【" + enterpriseInfo.getStoreName() +
//		 * "】商家入围"; way = "入围纪录"; } else if (url.contains("rightUpDown.do")) {
//		 * content = mUser.getUserName() + "降权或恢复【" +
//		 * enterpriseInfo.getStoreName() + "】商家"; way = "评论页面"; } else if
//		 * (url.contains("updateQuestionState.do")) { content =
//		 * mUser.getUserName() + "更改【" + enterpriseInfo.getStoreName() +
//		 * "】商家问卷状态"; way = "问卷列表"; } else if (url.contains("delQuestion.do")) {
//		 * content = mUser.getUserName() + "删除【" + enterpriseInfo.getStoreName()
//		 * + "】商家问卷"; way = "问卷列表"; } else if (url.contains("operQuestion.do"))
//		 * { content = mUser.getUserName() + "添加或修改【" +
//		 * enterpriseInfo.getStoreName() + "】商家问卷"; way = "问卷列表"; } else if
//		 * (url.contains("updateRedState.do")) { content = mUser.getUserName() +
//		 * "更改【" + enterpriseInfo.getStoreName() + "】商家红包状态"; way = "红包列表"; }
//		 * else if (url.contains("delRed.do")) { content = mUser.getUserName() +
//		 * "删除【" + enterpriseInfo.getStoreName() + "】商家红包"; way = "红包列表"; } else
//		 * if (url.contains("operRed.do")) { content = mUser.getUserName() +
//		 * "添加或修改【" + enterpriseInfo.getStoreName() + "】商家红包"; way = "红包列表"; }
//		 */
//
//		else if (url.contains("addPic.do")) {
//			content = mUser.getUserName() + "添加【"
//					+ enterpriseInfo.getStoreName() + "】商家图片";
//			way = "图片选择";
//		} else if (url.contains("delGroup.do")) {
//			content = mUser.getUserName() + "删除【"
//					+ enterpriseInfo.getStoreName() + "】商家图片分组";
//			way = "图片选择";
//		} else if (url.contains("delPic.do")) {
//			content = mUser.getUserName() + "删除【"
//					+ enterpriseInfo.getStoreName() + "】商家图片";
//			way = "图片选择";
//		} else if (url.contains("movePic.do")) {
//			content = mUser.getUserName() + "移动【"
//					+ enterpriseInfo.getStoreName() + "】商家图片";
//			way = "图片选择";
//		} else if (url.contains("operGroup.do")) {
//			content = mUser.getUserName() + "新建或编辑【"
//					+ enterpriseInfo.getStoreName() + "】商家图片分组";
//			way = "图片选择";
//		}
//		// else if (url.contains("rename.do")) {
//		// content = mUser.getUserName() + "重命名【"
//		// + enterpriseInfo.getStoreName() + "】商家声音";
//		// way = "素材/声音";
//		// } else if (url.contains("delSound.do")) {
//		// content = mUser.getUserName() + "删除【"
//		// + enterpriseInfo.getStoreName() + "】商家声音";
//		// way = "素材/声音";
//		// }
//		else if (url.contains("goodsAdd.do")) {
//			content = mUser.getUserName() + "新建或修改【"
//					+ enterpriseInfo.getStoreName() + "】商家产品";
//			way = "常规/产品管理/产品列表";
//		} else if (url.contains("goodsOpt.do")) {
//			content = mUser.getUserName() + "编辑【"
//					+ enterpriseInfo.getStoreName() + "】商家产品";
//			way = "常规/产品管理/产品列表";
//		} else if (url.contains("fillGoods.do")) {
//			content = mUser.getUserName() + "破损或补货【"
//					+ enterpriseInfo.getStoreName() + "】商家产品";
//			way = "常规/产品管理/产品列表";
//		} else if (url.contains("comTicketOpt.do")) {
//			content = mUser.getUserName() + "添加【"
//					+ enterpriseInfo.getStoreName() + "】商家优惠券";
//			way = "优惠券";
//		} else if (url.contains("comTicketEdit.do")) {
//			content = mUser.getUserName() + "编辑【"
//					+ enterpriseInfo.getStoreName() + "】商家优惠券";
//			way = "优惠券";
//		} else if (url.contains("uploadPic.do")) {
//			content = mUser.getUserName() + "上传【"
//					+ enterpriseInfo.getStoreName() + "】商家图片";
//			way = "图片选择";
//		} else if (url.contains("giveGift.do")) {
//			content = mUser.getUserName() + "赠送【"
//					+ enterpriseInfo.getStoreName() + "】商家礼品";
//			way = "常规/产品管理/产品列表";
//		} else if (url.contains("editFullGive.do")) {
//			content = mUser.getUserName() + "编辑【"
//					+ enterpriseInfo.getStoreName() + "】商家满就送";
//			way = "配置中心/送好礼";
//		} else if (url.contains("addFullGive.do")) {
//			content = mUser.getUserName() + "添加【"
//					+ enterpriseInfo.getStoreName() + "】商家满就送";
//			way = "配置中心/送好礼";
//		} else if (url.contains("updateIntroNewState.do")) {
//			content = mUser.getUserName() + "更新【"
//					+ enterpriseInfo.getStoreName() + "】商家转介绍新人礼包状态";
//			way = "转介绍/转介绍规则/转介绍新人礼包";
//		} else if (url.contains("saveShareLimit.do")) {
//			content = mUser.getUserName() + "保存【"
//					+ enterpriseInfo.getStoreName() + "】商家分享上限";
//			way = "转介绍/转介绍规则/精彩活动";
//		} else if (url.contains("unfreezeRecommend.do")) {
//			content = mUser.getUserName() + "解冻或冻结【"
//					+ enterpriseInfo.getStoreName() + "】商家介绍人状态";
//			way = "转介绍/历史记录";
//		} else if (url.contains("operateIntroduceNew.do")) {
//			content = mUser.getUserName() + "编辑/新增【"
//					+ enterpriseInfo.getStoreName() + "】商家新人礼包";
//			way = "转介绍/转介绍规则/转介绍新人礼包";
//		} else if (url.contains("deleteIntroducePri.do")) {
//			content = mUser.getUserName() + "删除【"
//					+ enterpriseInfo.getStoreName() + "】商家转介绍规则";
//			way = "转介绍/转介绍规则/精彩活动";
//		} else if (url.contains("addIntroducePri.do")) {
//			content = mUser.getUserName() + "新增【"
//					+ enterpriseInfo.getStoreName() + "】商家转介绍规则";
//			way = "转介绍/转介绍规则/精彩活动";
//		} else if (url.contains("editIntroducePriInit.do")) {
//			content = mUser.getUserName() + "编辑【"
//					+ enterpriseInfo.getStoreName() + "】商家转介绍特权";
//			way = "转介绍/转介绍规则/精彩活动";
//		} else if (url.contains("updateIntroduceRule.do")) {
//			content = mUser.getUserName() + "更新【"
//					+ enterpriseInfo.getStoreName() + "】商家转介绍特权";
//			way = "转介绍/转介绍规则/精彩活动";
//		}else if(url.contains("vipLblAdd.do")){
//			content = mUser.getUserName() + "添加【"
//					+ enterpriseInfo.getStoreName() + "】熟客标签";
//			way = "精准营销/精准规则/熟客标签";
//		}else if(url.contains("epSendMsgPbl.do")){
//			content = mUser.getUserName() + "发送【"
//					+ enterpriseInfo.getStoreName() + "】商家消息";
//			way = "精准营销/精准规则/熟客标签";
//		}else if(url.contains("exportFansLblNum.do")){
//			content = mUser.getUserName() + "导出【"
//					+ enterpriseInfo.getStoreName() + "】熟客标签";
//			way = "精准营销/精准规则/熟客标签";
//		}else if(url.contains("editFansLblNum.do")){
//			content = mUser.getUserName() + "编辑【"
//					+ enterpriseInfo.getStoreName() + "】熟客标签";
//			way = "精准营销/精准规则/熟客标签";
//		}else if(url.contains("delFansLbl.do")){
//			content = mUser.getUserName() + "删除【"
//					+ enterpriseInfo.getStoreName() + "】熟客标签";
//			way = "精准营销/精准规则/熟客标签";
//		}else if(url.contains("pointRoleEdit.do")){
//			content = mUser.getUserName() + "编辑【"
//					+ enterpriseInfo.getStoreName() + "】积分规则";
//			way = "积分/积分规则";
//		}
//		if (StringUtils.isNotEmpty(content) && StringUtils.isNotEmpty(way))
//			service.insertOperateLog(mUser.getId(), enterpriseInfo.getAtNo()
//					+ "", content, way, SessionUtil.getIpAddr(request));
//	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
