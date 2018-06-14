package com.qxh.utils;

import java.lang.reflect.Field;

public class ErrorCode {

	/**
	 * project error
	 */
	public static String project = "7";

	/**
	 * package error
	 **/
	public static String util = "02";

	public static String rmi = "03";

	// public static String search = "08";
	// public static String search_FindPageAction = "01";
	// public static String search_FindPageAction_init = "01";
	// public static String search_FindPageAction_checkSellerPicShow = "02";
	// public static String search_FindPageAction_getBranchInfo = "03";
	// public static String search_FindPageAction_getIntroduction = "04";
	// public static String search_FindPageAction_getProductRecommendation =
	// "05";
	// public static String search_FindPageAction_getPointMallList = "06";
	// public static String search_FindPageAction_joinEp = "07";
	// public static String search_FindPageAction_initV1_7 = "08";
	// public static String search_FindPageAction_home = "09";
	// public static String search_FindPageAction_initV1_5 = "10";
	// public static String search_GetMyEpList = "02";
	// public static String search_GetMyEpList_init = "01";
	// public static String search_GetMyEpList_message = "02";
	// public static String search_GetMyEpList_messageDetail = "03";
	// public static String search_GetMyEpList_receiveGift = "04";
	// public static String search_GetMyEpList_pointDetail = "05";
	// public static String search_GetMyEpList_exchangePoint = "06";
	// public static String search_GetMyEpList_countShareV1_5 = "07";
	// public static String search_GetMyEpList_getNoticListV1_7 = "08";
	// public static String search_GetMyEpList_getBranchInfoV1_7 = "09";

	// public static String vipInfo = "09";
	// public static String vipInfo_MyAction = "01";
	// public static String vipInfo_MyAction_init = "01";
	// public static String vipInfo_MyAction_myConsume = "02";
	// public static String vipInfo_MyAction_evaluation = "03";
	// public static String vipInfo_MyAction_getEvaluateTerms = "04";
	// public static String vipInfo_MyAction_messageSettingInit = "05";
	// public static String vipInfo_MyAction_updateMessageSetting = "06";
	// public static String vipInfo_MyAction_myGiftList = "07";
	// public static String vipInfo_MyAction_giftDetail = "08";
	// public static String vipInfo_MyAction_backExchange = "09";
	// public static String vipInfo_MyAction_GetDeepObj = "10";

	/**
	 * package error
	 **/
	public static String common = "01";

	public static String utils = "02";
	public static String utils_MessagePush = "01";
	public static String utils_MessagePush_run = "01";

	public static String Configs = "11";
	public static String Configs_InfosServiceImpl = "01";
	/**
	 * @Fields V2_Configs_InfosServiceImpl_getConfigInfo :获取配置信息
	 */
	public static String Configs_InfosServiceImpl_getConfigInfo = "01";

	/**
	 * @Fields V2_Configs_InfosServiceImpl_getAllClassList : 获取分类
	 */
	public static String Configs_InfosServiceImpl_getAllClassList = "02";
	/**
	 * @Fields Configs_InfosServiceImpl_getAreaList :获取配置信息
	 */
	public static String Configs_InfosServiceImpl_getAreaList = "03";
	public static String Configs_InfosServiceImpl_getALLcityList = "04";
	public static String Configs_InfosServiceImpl_GetAreaList = "05";

	public static String Configs_InfosServiceImpl_getAreaListByCity = "06";
	public static String Configs_InfosServiceImpl_reportedDevice = "08";

	/**
	 * @Fields Configs_InfosServiceImpl_validVersion : 检查版本
	 */
	public static String Configs_InfosServiceImpl_validVersion = "07";

	// 商家信息
	public static String enterprise = "12";
	public static String enterprise_EnterpriseServiceImpl = "01";
	public static String enterprise_EnterpriseServiceImpl_getEnterpriseInit = "01";
	public static String enterprise_EnterpriseServiceImpl_getEnterpriseActivitys = "02";
	public static String enterprise_EnterpriseServiceImpl_getEnterpriseTickets = "03";
	public static String enterprise_EnterpriseServiceImpl_JoinEnterprise = "04";
	public static String enterprise_EnterpriseServiceImpl_SettingEnterprise = "05";
	public static String enterprise_EnterpriseServiceImpl_getStoreIntegral = "06";
	public static String enterprise_EnterpriseServiceImpl_getIntegralGoods = "07";
	public static String enterprise_EnterpriseServiceImpl_exchangeEnterpriseGoods = "08";
	public static String enterprise_EnterpriseServiceImpl_QuitEnterprise = "09";
	public static String enterprise_EnterpriseServiceImpl_searchFilterEnterprise = "10";
	public static String enterprise_EnterpriseServiceImpl_loadAllRecommendGoods = "11";
	public static String enterprise_EnterpriseServiceImpl_getBaseInfo = "12";
	public static String enterprise_EnterpriseServiceImpl_searchEnterprise = "13";

	// 首页
	public static String home = "13";
	public static String home_HomeServiceImpl = "01";
	public static String home_HomeServiceImpl_getHomeInit = "01";
	public static String home_HomeServiceImpl_getEnterpriseListByKindPage = "02";

	// 消息
	public static String message = "14";
	public static String message_IMessageServiceImpl = "01";
	public static String message_IMessageServiceImpl_getListByTypePage = "01";
	public static String message_IMessageServiceImpl_getMessageInfo = "02";
	public static String message_IMessageServiceImpl_getStoreMessageList = "03";
	public static String message_IMessageServiceImpl_getSystemMessageList = "04";
	public static String message_IMessageServiceImpl_getStoreMessageDetail = "05";
	public static String message_IMessageServiceImpl_getMessageById = "06";
	public static String message_IMessageServiceImpl_readMessageById = "07";
	// 二维码
	public static String qrcode = "15";
	public static String qrcode_IQrcodeServiceImpl = "01";
	public static String qrcode_IQrcodeServiceImpl_getPostTickets = "01";
	public static String qrcode_IQrcodeServiceImpl_getQrcode = "02";
	public static String qrcode_IQrcodeServiceImpl_checkIn = "03";

	// 会员包
	public static String user = "16";
	public static String user_UserServiceImpl = "01";
	public static String user_UserServiceImpl_getLoginByid = "01";
	public static String user_UserServiceImpl_getMyData = "02";
	public static String user_UserServiceImpl_getMyFootPrint = "03";
	public static String user_UserServiceImpl_getEnterpriseActivitysByCityPage = "04";
	public static String user_UserServiceImpl_getEnterpriseTicketsByPage = "05";
	public static String user_UserServiceImpl_drawTicket = "06";
	public static String user_UserServiceImpl_getEnterpriseListByCityPage = "07";
	public static String user_UserServiceImpl_getEnterprisePassTicketsByPage = "08";
	public static String user_UserServiceImpl_getRegisterCode = "09";
	public static String user_UserServiceImpl_registerNew = "10";
	public static String user_UserServiceImpl_getForgetCode = "11";
	public static String user_UserServiceImpl_resetPwd = "12";
	public static String user_UserServiceImpl_giveTicket = "13";
	public static String user_UserServiceImpl_editMyInfo = "14";
	public static String user_UserServiceImpl_editMemberHead = "15";
	public static String user_UserServiceImpl_deleteTicket = "16";
	public static String user_UserServiceImpl_signIn = "17";
	public static String user_UserServiceImpl_retreatTicket = "18";
	public static String user_UserServiceImpl_feedback = "19";
	public static String user_UserServiceImpl_getUnuseTickersWait = "20";
	public static String user_UserServiceImpl_getUsedTickers = "21";
	public static String user_UserServiceImpl_getStoreTickesList = "22";
	public static String user_UserServiceImpl_getPassTickers = "23";
	public static String user_UserServiceImpl_logout = "24";
	public static String user_UserServiceImpl_shareTicket = "25";
	public static String user_UserServiceImpl_reloadTickets = "26";

	// h5 errorcode
	public static String card = "17";

	public static String card_CardH5ServiceImpl_userCheck = "01";
	public static String card_CardH5ServiceImpl_resetCardPwd = "02";
	public static String card_CardH5ServiceImpl_getTicketList = "03";
	public static String card_CardH5ServiceImpl_info = "04";
	public static String card_CardH5ServiceImpl_binding = "05";
	public static String card_CardH5ServiceImpl_consumeHistory = "06";
	public static String card_CardH5ServiceImpl_consumeDetail = "07";
	public static String card_CardH5ServiceImpl_getStoreTicketList = "08";
	public static String card_CardH5ServiceImpl_integralHistory = "09";
	public static String card_CardH5ServiceImpl_getsetting = "10";
	public static String card_CardH5ServiceImpl_setting = "11";
	public static String card_CardH5ServiceImpl_cancelCard = "12";
	public static String card_CardH5ServiceImpl_exchange = "13";
	public static String card_CardH5ServiceImpl_drawTikect = "14";
	public static String card_CardH5ServiceImpl_deleteTikect = "15";
	public static String card_CardH5ServiceImpl_donateTikect = "16";
	public static String card_CardH5ServiceImpl_GetUsedAndPassedTickes = "17";
	public static String card_CardH5ServiceImpl_joinIn = "18";
	public static String card_CardH5ServiceImpl_checkCardNumber = "19";
	public static String config = "18";
	public static String config_ConfigServiceImpl_getStartUp = "01";
	public static String config_ConfigServiceImpl_reportDevice = "02";
	public static String config_ConfigServiceImpl_getMoreClass = "03";
	public static String config_ConfigServiceImpl_getAbout = "04";
	public static String config_ConfigServiceImpl_getAllClassList = "05";
	public static String config_ConfigServiceImpl_getcityList = "06";
	public static String config_ConfigServiceImpl_getALLcityList = "07";
	public static String config_ConfigServiceImpl_GetAreaList = "08";
	public static String config_ConfigServiceImpl_validVersion = "09";
	public static String config_ConfigServiceImpl_startup = "10";
	public static String config_ConfigServiceImpl_webCheck = "11";
	// public static String enterprise = "19";
	public static String enterprise_EnterpriseH5ServiceImpl_getIntegralGoods = "01";
	public static String enterprise_EnterpriseH5ServiceImpl_loadAllRecommendGoods = "02";
	public static String enterprise_EnterpriseH5ServiceImpl_branchList = "03";
	public static String enterprise_EnterpriseH5ServiceImpl_getStoreTicketDetail = "04";
	public static String enterprise_EnterpriseH5ServiceImpl_newActivity = "05";
	public static String enterprise_EnterpriseH5ServiceImpl_activityDetail = "06";
	public static String enterprise_EnterpriseH5ServiceImpl_info = "07";
	// public static String home = "20";
	 public static String home_HomeH5ServiceImpl_getInit = "01";
	 public static String home_HomeH5ServiceImpl_getSearch = "02";
	 public static String home_HomeH5ServiceImpl_getClassListByKindPage = "03";
	 public static String home_HomeH5ServiceImpl_getEnterpriseListByKindPage = "04";
	
	// public static String message = "21";
	 
	 
	// public static String user = "22";
	  public static String user_UserH5ServiceImpl_signIn = "01";
	  public static String user_UserH5ServiceImpl_login = "02";
	  public static String user_UserH5ServiceImpl_editUserPic = "03";
	  public static String user_UserH5ServiceImpl_getRegisterCode = "04";
	  public static String user_UserH5ServiceImpl_feedBack = "05";
	  public static String user_UserH5ServiceImpl_register = "06";
	  public static String user_UserH5ServiceImpl_checkValidate = "07";
	  public static String user_UserH5ServiceImpl_getForgetCode = "08";
	  public static String user_UserH5ServiceImpl_resetPwd = "09";
	  public static String user_UserH5ServiceImpl_beanRecord = "10";
	  public static String user_UserH5ServiceImpl_editUserinfo = "11";
	  public static String user_UserH5ServiceImpl_editUserRemind = "12";
	  public static String user_UserH5ServiceImpl_consumeHistory = "13";
	  public static String user_UserH5ServiceImpl_resetLoginPwd = "14";
	  public static String user_UserH5ServiceImpl_personData = "15";

	/**
	 * 获取异常编号 .
	 * 
	 * @param obj
	 *            调用该方法的类对象
	 * @return the errocode
	 */
	public static String geterrocode(Object obj) {
		String classname = obj.getClass().getName();

		String pnm = classname.substring(0, classname.lastIndexOf("."));
		pnm = pnm.substring(pnm.lastIndexOf(".") + 1);// 获取到包名

		String packages = GetValue(pnm, new ErrorCode());

		String cnm = classname.substring(classname.lastIndexOf(".") + 1);// 获取到类名

		String classnames = GetValue(pnm + "_" + cnm, new ErrorCode());

		String mnm = new Throwable().getStackTrace()[1].getMethodName();// 获取到方法名
		String method = GetValue(pnm + "_" + cnm + "_" + mnm, new ErrorCode());

		String result = project + packages + classnames + method;
		return result;
	}

	private static String GetValue(String pname, Object obj) {
		String value = "";
		// 获取f对象对应类中的所有属性域
		Field[] fields = obj.getClass().getDeclaredFields();// 获取异常类里所有的变量
		for (int i = 0, len = fields.length; i < len; i++) {
			// 对于每个属性，获取属性名
			String varName = fields[i].getName();
			try {
				// 获取原来的访问控制权限
				boolean accessFlag = fields[i].isAccessible();
				// 修改访问控制权限
				fields[i].setAccessible(true);
				// 获取在对象f中属性fields[i]对应的对象中的变量值
				Object o = fields[i].get(obj);
				if (varName.toUpperCase().trim()
						.equals(pname.toUpperCase().trim())) {
					value = o.toString();
				}
				// System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
				// 恢复访问控制权限
				fields[i].setAccessible(accessFlag);
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
			}
		}
		return value;
	}
}
