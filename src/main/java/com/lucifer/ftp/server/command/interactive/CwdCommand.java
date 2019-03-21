package com.lucifer.ftp.server.command.interactive;

import java.io.IOException;
import java.net.Socket;

import org.apache.commons.lang3.StringUtils;

import com.lucifer.ftp.server.FtpUserStruct;
import com.lucifer.ftp.server.command.AbstractCommand;
import com.lucifer.ftp.server.enums.FtpReply;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 18, 2019 10:34:13 AM
 * @since 1.0.0
 */
public class CwdCommand extends AbstractCommand {

	@Override
	public void execute(FtpUserStruct ftpUserStruct, String command) throws IOException {
		Socket interactiveSocket = ftpUserStruct.getInteractiveSocket();
		String newPath = parseCommand(command);
		String currentPath = ftpUserStruct.getCurrentPath();
		currentPath = changePath(currentPath, newPath);
		ftpUserStruct.setCurrentPath(currentPath);
		this.write(interactiveSocket,
		    FtpReply.PATH_CREATED.code() + " \"" + ftpUserStruct.getCurrentPath() + "\"");
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

	public String changePath(String currentPath, String newPath) {
		String tmpPath = currentPath;
		if (newPath.startsWith("/")) {
			return newPath;
		} else if (newPath.startsWith("..")) {
			if (tmpPath.lastIndexOf("/") == 0) {
				tmpPath = "/";
			} else {
				tmpPath = tmpPath.substring(0, tmpPath.lastIndexOf("/"));
			}
			return tmpPath;
		} else {
			if (tmpPath.equals("/")) {
				tmpPath += newPath;
			} else {
				tmpPath += "/" + newPath;
			}
			return tmpPath;
		}
	}

}
