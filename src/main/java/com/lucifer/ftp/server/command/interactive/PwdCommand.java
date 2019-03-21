package com.lucifer.ftp.server.command.interactive;

import java.io.IOException;
import java.net.Socket;

import com.lucifer.ftp.server.FtpUserStruct;
import com.lucifer.ftp.server.command.AbstractCommand;
import com.lucifer.ftp.server.enums.FtpReply;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 15, 2019 5:30:34 PM
 * @since 1.0.0
 */
public class PwdCommand extends AbstractCommand {

	@Override
	public void execute(FtpUserStruct ftpUserStruct, String command) throws IOException {
		Socket interactiveSocket = ftpUserStruct.getInteractiveSocket();
		if (ftpUserStruct.getLogStatus() == FtpUserStruct.USER_LOGIN) {
			this.write(interactiveSocket,
			    FtpReply.PATH_CREATED.code() + " \"" + ftpUserStruct.getCurrentPath() + "\"");
		} else {
			this.write(interactiveSocket, FtpReply.SERVICE_CLOSE.message());
		}
	}
}
