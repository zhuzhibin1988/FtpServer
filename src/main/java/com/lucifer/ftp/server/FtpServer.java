package com.lucifer.ftp.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import com.lucifer.ftp.server.common.SocketUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 8, 2019 11:46:03 AM
 * @since 1.0.0
 */
@Slf4j
public class FtpServer {
	private final static int port = 8888;

	boolean running = true;

	public void start() {
		ServerSocket ftpServer = null;
		try {
			SocketAddress address = new InetSocketAddress(Constant.HOST, port);
			ftpServer = new ServerSocket();
			ftpServer.bind(address);
			log.info("start ftp server at {}:{}", Constant.HOST, port);
			while (this.running) {
				Socket interactiveSocket = ftpServer.accept();
				log.info("ftp client {} connect", SocketUtils.socketAddressInfo(interactiveSocket));

				FtpUserStruct ftpUserStruct = new FtpUserStruct();
				ftpUserStruct.setInteractiveSocket(interactiveSocket);
				new InteractiveHandlerThread(ftpUserStruct).handler();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			stop();
		} finally {
			if (ftpServer != null) {
				try {
					ftpServer.close();
					log.info("stop ftp server at {}:{}", Constant.HOST, port);
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
	}

	public void stop() {
		this.running = false;
	}

	public static void main(String[] args) {
		FtpServer server = new FtpServer();
		server.start();
	}
}
