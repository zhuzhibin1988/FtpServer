package com.lucifer.ftp.server.command.data;

import java.io.InputStream;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

import com.lucifer.ftp.server.FtpUserStruct;
import com.lucifer.ftp.server.command.AbstractCommand;
import com.lucifer.ftp.server.enums.FtpReply;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 18, 2019 10:24:27 AM
 * @since 1.0.0
 */
public class ListCommand extends AbstractCommand {

	@Override
	public void execute(FtpUserStruct ftpUserStruct, String command) throws Exception {
		Socket interactiveSocket = ftpUserStruct.getInteractiveSocket();
		Socket passiveDataSocket = ftpUserStruct.getPassiveDataSocket();
		String currentPath = ftpUserStruct.getCurrentPath();
		InputStream inputStream = null;
		try {
			Process process = Runtime.getRuntime().exec("ls -l " + currentPath);
			inputStream = process.getInputStream();
			this.write(passiveDataSocket, inputStream);
			process.waitFor();
		} finally {
			IOUtils.closeQuietly(inputStream);
			closeDataSocket(ftpUserStruct);
		}
		this.write(interactiveSocket, FtpReply.DATA_CONNECTION_CLOSE.message());
	}
}
