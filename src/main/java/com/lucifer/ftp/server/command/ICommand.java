package com.lucifer.ftp.server.command;

import com.lucifer.ftp.server.FtpUserStruct;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 15, 2019 9:52:17 AM
 * @since 1.0.0
 */
public interface ICommand {
	public void execute(FtpUserStruct userStatus, String command) throws Exception;
}
