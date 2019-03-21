package com.lucifer.ftp.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import com.lucifer.ftp.server.common.SocketUtils;
import com.lucifer.ftp.server.enums.FtpReply;
import com.lucifer.ftp.server.io.SocketWriter;

import lombok.extern.slf4j.Slf4j;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 18, 2019 2:49:03 PM
 * @since 1.0.0
 */

@Slf4j
public class PasvDataServerThread extends Thread {

	private FtpUserStruct ftpUserStruct = null;

	public PasvDataServerThread(FtpUserStruct ftpUserStruct) {
		this.ftpUserStruct = ftpUserStruct;
	}

	public int getPasvPort(ServerSocket server) {
		return server.getLocalPort();
	}

	@Override
	public void run() {
		try {
			SocketAddress address = new InetSocketAddress(Constant.HOST, 0);
			ServerSocket passiveDataServerSocket = new ServerSocket();
			passiveDataServerSocket.bind(address);
			int pasvPort = getPasvPort(passiveDataServerSocket);
			int high8bit = pasvPort >> 8;
			int low8bit = pasvPort & 0x000000FF;
			String pasvContent = FtpReply.ENTER_PASV_MODE.code() + " " + "Entering Passive Mode (127,0,0,1," + high8bit
			    + "," + low8bit + ")";

			Socket interactiveSocket = this.ftpUserStruct.getInteractiveSocket();
			SocketWriter writer = new SocketWriter(interactiveSocket);
			writer.writeWithEor(pasvContent);
			writer.writeWithEor(FtpReply.DATA_CONNECTION_OPEN_AND_FILE_OK.message());
			Socket passiveDataSocket = passiveDataServerSocket.accept();
			this.ftpUserStruct.setPassiveDataSocket(passiveDataSocket);
			this.ftpUserStruct.setPassiveDataServerSocket(passiveDataServerSocket);
			log.info("data transfer client {} connnect", SocketUtils.socketAddressInfo(passiveDataSocket));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void handler() {
		this.start();
	}
}
