package com.qxh.utils;

public interface IPageConstants {
	public final static int PageSize = 20;

	/**
	 * @Fields MESSAGE_TYPE_SYSTEM : 系统消息
	 */
	public final static int MESSAGE_TYPE_MESSAGEINFO = 0;

	/**
	 * @Fields MESSAGE_TYPE_OPERATE : 系统提醒信息
	 */
	public final static int MESSAGE_TYPE_ATMESSAGE = 1;
	/**
	 * @Fields MESSAGE_TYPE_OPERATE : 系统提醒信息
	 */
	public final static String MESSAGE_TYPE_OPERATE = "O";
	/**
	 * @Fields DEVICE_ANDROID : 1为 android 2为 iOS
	 */
	public final static String DEVICE_ANDROID = "1";
	/**
	 * @Fields DEVICE_IOS :1为 android 2为 iOS
	 */
	public final static String DEVICE_IOS = "2";
	/**
	 * @Fields PUSH_INDEX :推送别名前缀
	 */
	public final static String PUSH_INDEX = "sdb_";

	public static final int TickectPageSize = 8;
}
