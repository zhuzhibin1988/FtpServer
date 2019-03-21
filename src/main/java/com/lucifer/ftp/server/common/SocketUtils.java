package com.lucifer.ftp.server.common;

import java.net.Socket;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 20, 2019 3:59:32 PM
 * @since 1.0.0
 */
public class SocketUtils {
	public static String socketAddressInfo(Socket socket) {
		String ip = socket.getInetAddress().getHostAddress();
		int port = socket.getPort();
		String socketInfo = ip + ":" + port;
		return socketInfo;
	}
}
