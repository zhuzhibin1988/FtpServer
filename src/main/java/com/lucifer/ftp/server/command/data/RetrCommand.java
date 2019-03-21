package com.lucifer.ftp.server.command.data;

import java.io.FileInputStream;
import java.net.Socket;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.lucifer.ftp.server.FtpUserStruct;
import com.lucifer.ftp.server.command.AbstractCommand;
import com.lucifer.ftp.server.enums.FtpReply;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 19, 2019 5:12:35 PM
 * @since 1.0.0
 */
public class RetrCommand extends AbstractCommand {

	@Override
	public void execute(FtpUserStruct ftpUserStruct, String command) throws Exception {
		Socket interactiveSocket = ftpUserStruct.getInteractiveSocket();
		Socket passiveDataSocket = ftpUserStruct.getPassiveDataSocket();

		String currentPath = ftpUserStruct.getCurrentPath();
		String filename = parseCommand(command);
		filename = getFilePath(currentPath, filename);
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(filename);
			this.write(passiveDataSocket, fileInputStream);
		} finally {
			IOUtils.closeQuietly(fileInputStream);
			closeDataSocket(ftpUserStruct);
		}
		this.write(interactiveSocket, FtpReply.DATA_CONNECTION_CLOSE.message());
	}

	public String parseCommand(String command) {
		String[] array = StringUtils.split(command, " ");
		String path = null;
		if (array.length == 2) {
			path = array[1];
		} else {
			throw new IllegalArgumentException();
		}
		return path.trim();
	}

	public String getFilePath(String currentPath, String filename) {
		String parentPath = currentPath;
		if (filename.startsWith("/")) {
			return filename;
		} else {
			if (parentPath.equals("/")) {
				parentPath += filename;
			} else {
				parentPath += "/" + filename;
			}
			return parentPath;
		}
	}
}
