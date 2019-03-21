package com.lucifer.ftp.server.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import com.lucifer.ftp.server.Constant;
import com.lucifer.ftp.server.FtpUserStruct;
import com.lucifer.ftp.server.PasvDataServerThread;
import com.lucifer.ftp.server.common.SocketUtils;
import com.lucifer.ftp.server.io.SocketWriter;

import lombok.extern.slf4j.Slf4j;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 15, 2019 10:24:08 AM
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractCommand implements ICommand {

	public void write(Socket socket, String content) throws IOException {
		SocketWriter writer = new SocketWriter(socket);
		writer.writeWithEor(content);
	}

	public void write(Socket socket, InputStream inputStream) throws IOException {
		byte[] buffer = new byte[Constant.BUFFER_SIZE];
		OutputStream outputStream = null;
		outputStream = socket.getOutputStream();
		int length = -1;
		while ((length = inputStream.read(buffer)) != -1) {
			buffer = Arrays.copyOf(buffer, length);
			outputStream.write(buffer);
		}
		outputStream.flush();
	}

	public void openDataSocket(FtpUserStruct ftpUserStruct) {
		if (ftpUserStruct.getMode() == FtpUserStruct.PASV_MODE) {
			new PasvDataServerThread(ftpUserStruct).handler();
		}
	}

	public void closeDataSocket(FtpUserStruct ftpUserStruct) throws IOException {
		if (ftpUserStruct.getMode() == FtpUserStruct.PASV_MODE) {
			Socket passiveDataSocket = ftpUserStruct.getPassiveDataSocket();
			ServerSocket passiveDataServerSocket = ftpUserStruct.getPassiveDataServerSocket();
			passiveDataSocket.close();
			passiveDataServerSocket.close();
			log.info("data transfer client {} disconnect", SocketUtils.socketAddressInfo(passiveDataSocket));
		}
	}
}
