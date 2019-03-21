package com.lucifer.ftp.server;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 20, 2019 3:20:53 PM
 * @since 1.0.0
 */
public class Constant {
	// 数据缓存
	public final static int BUFFER_SIZE = 64 * 1024;

	// telent 命令结束符
	public final static byte[] EOR = new byte[] { 13, 10 };

	public final static String HOST = "127.0.0.1";
}
