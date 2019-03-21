package com.lucifer.ftp.server.command.interactive;

import java.io.IOException;

import com.lucifer.ftp.server.FtpUserStruct;
import com.lucifer.ftp.server.command.AbstractCommand;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 15, 2019 5:09:24 PM
 * @since 1.0.0
 */
public class SystCommand extends AbstractCommand {

	@Override
	public void execute(FtpUserStruct ftpUserStruct, String command) throws IOException {
		String os = "max os";
		this.write(ftpUserStruct.getInteractiveSocket(), os);
	}
}
