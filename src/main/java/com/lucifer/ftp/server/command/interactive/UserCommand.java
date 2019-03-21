package com.lucifer.ftp.server.command.interactive;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.lucifer.ftp.server.FtpUserStruct;
import com.lucifer.ftp.server.command.AbstractCommand;
import com.lucifer.ftp.server.enums.FtpReply;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 15, 2019 9:53:17 AM
 * @since 1.0.0
 */

public class UserCommand extends AbstractCommand {

	@Override
	public void execute(FtpUserStruct ftpUserStruct, String command) throws IOException {
		try {
			String user = parseCommand(command);
			ftpUserStruct.setUser(user);
			ftpUserStruct.setLogStatus(FtpUserStruct.USER_AUTH);
			this.write(ftpUserStruct.getInteractiveSocket(), FtpReply.NEED_PWD.message());
		} catch (IllegalArgumentException e) {
			this.write(ftpUserStruct.getInteractiveSocket(), FtpReply.CMD_ARGUMENT_ERROR.message());
		}
	}

	/**
	 * cmd: user zhuzhibin
	 * 
	 * @param parameter
	 * @return
	 */
	public String parseCommand(String command) {
		String[] array = StringUtils.split(command, " ");
		String user = null;
		if (array.length == 2) {
			user = array[1];
		} else {
			throw new IllegalArgumentException();
		}
		return user.trim();
	}

}
