package com.lucifer.ftp.server.command.interactive;

import java.io.IOException;
import java.net.Socket;

import org.apache.commons.lang3.StringUtils;

import com.lucifer.ftp.server.FtpUserCheck;
import com.lucifer.ftp.server.FtpUserStruct;
import com.lucifer.ftp.server.command.AbstractCommand;
import com.lucifer.ftp.server.enums.FtpReply;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 15, 2019 10:39:07 AM
 * @since 1.0.0
 */

public class PassCommand extends AbstractCommand {

	@Override
	public void execute(FtpUserStruct ftpUserStruct, String command) throws IOException {
		Socket interactiveSocket = ftpUserStruct.getInteractiveSocket();
		String user = ftpUserStruct.getUser();
		String password = parseCommand(command);
		try {
			boolean validPwd = FtpUserCheck.checkUserPassword(user, password);
			if (validPwd) {
				ftpUserStruct.setLogStatus(FtpUserStruct.USER_LOGIN);
				ftpUserStruct.setCurrentPath(FtpUserCheck.getRootPath(ftpUserStruct.getUser()));
				this.write(interactiveSocket, FtpReply.LOG_IN.message());
			} else {
				ftpUserStruct.reset();
				this.write(interactiveSocket, FtpReply.NOT_LOG_IN.message());
			}
		} catch (IllegalArgumentException e) {
			this.write(interactiveSocket, FtpReply.CMD_ARGUMENT_ERROR.message());
		}
	}

	/**
	 * cmd: pwd 11
	 * 
	 * @param parameter
	 * @return
	 */
	public String parseCommand(String command) {
		String[] array = StringUtils.split(command, " ");
		String pwd = null;
		if (array.length == 2) {
			pwd = array[1];
		} else {
			throw new IllegalArgumentException();
		}
		return pwd.trim();
	}
}
