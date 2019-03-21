package com.lucifer.ftp.server.command.interactive;

import java.io.IOException;
import java.net.Socket;

import com.lucifer.ftp.server.FtpUserStruct;
import com.lucifer.ftp.server.command.AbstractCommand;
import com.lucifer.ftp.server.enums.FtpReply;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 15, 2019 2:45:00 PM
 * @since 1.0.0
 */
public class ConnectCommand extends AbstractCommand {

	@Override
	public void execute(FtpUserStruct ftpUserStruct, String command) throws IOException {
		Socket interactiveSocket = ftpUserStruct.getInteractiveSocket();
		if (ftpUserStruct.getLogStatus() == FtpUserStruct.WAIT_USER) {
			ftpUserStruct.setLogStatus(FtpUserStruct.USER_CONNECT);
			this.write(interactiveSocket, FtpReply.SERVICE_READY.message());
		}
	}
}
