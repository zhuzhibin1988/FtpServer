package com.lucifer.ftp.server.command.data;

import org.apache.commons.lang3.StringUtils;

import com.lucifer.ftp.server.FtpUserStruct;
import com.lucifer.ftp.server.command.AbstractCommand;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 18, 2019 12:08:52 PM
 * @since 1.0.0
 */
public class PortCommand extends AbstractCommand {
	public void execute(FtpUserStruct ftpUserStruct, String command) {

	}

	/**
	 * PORT 127,0,0,1,194,46
	 * 
	 * @param command
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
