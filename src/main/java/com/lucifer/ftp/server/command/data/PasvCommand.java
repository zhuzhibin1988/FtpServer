package com.lucifer.ftp.server.command.data;

import com.lucifer.ftp.server.FtpUserStruct;
import com.lucifer.ftp.server.command.AbstractCommand;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 18, 2019 2:06:56 PM
 * @since 1.0.0
 */

public class PasvCommand extends AbstractCommand {
	@Override
	public void execute(FtpUserStruct ftpUserStruct, String command) {
		ftpUserStruct.setMode(FtpUserStruct.PASV_MODE);
		openDataSocket(ftpUserStruct);
	}
}
