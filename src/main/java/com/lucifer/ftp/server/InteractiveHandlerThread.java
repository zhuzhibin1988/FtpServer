package com.lucifer.ftp.server;

import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.lucifer.ftp.server.command.ICommand;
import com.lucifer.ftp.server.command.interactive.ConnectCommand;
import com.lucifer.ftp.server.command.interactive.InvalidCommand;
import com.lucifer.ftp.server.command.interactive.QuitCommand;
import com.lucifer.ftp.server.common.SocketUtils;
import com.lucifer.ftp.server.enums.FtpCommand;

import lombok.extern.slf4j.Slf4j;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 18, 2019 2:49:03 PM
 * @since 1.0.0
 */

@Slf4j
public class InteractiveHandlerThread extends Thread {

	private FtpUserStruct ftpUserStruct = null;

	public InteractiveHandlerThread(FtpUserStruct ftpUserStruct) {
		this.ftpUserStruct = ftpUserStruct;
	}

	@Override
	public void run() {
		Socket interactiveSocket = this.ftpUserStruct.getInteractiveSocket();
		ConnectCommand connect = new ConnectCommand();
		try {
			connect.execute(this.ftpUserStruct, "");
			InputStream socketInputStream = interactiveSocket.getInputStream();
			String interactiveSocketInfo = SocketUtils.socketAddressInfo(interactiveSocket);
			byte[] buffer = new byte[Constant.BUFFER_SIZE];
			int length = -1;
			byte[] commandCache = new byte[0];
			while ((length = socketInputStream.read(buffer)) != -1) {
				commandCache = Arrays.copyOf(commandCache, commandCache.length + length);
				System.arraycopy(buffer, 0, commandCache, commandCache.length - length, length);
				if (isEndOfRecord(buffer, length)) {
					commandCache = Arrays.copyOf(commandCache, commandCache.length - Constant.EOR.length);
					String command = new String(commandCache);
					log.info("{}>>server: {}", interactiveSocketInfo, command);
					ICommand cmd = parseCommand(command);
					try {
						cmd.execute(this.ftpUserStruct, command);
						commandCache = new byte[0];
						if (cmd instanceof QuitCommand) {
							break;
						}
					} catch (Exception e) {
						log.error("ftp command execute error", e);
					}
				}
			}
			log.info("client {} disconnnect", interactiveSocketInfo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public boolean isEndOfRecord(byte[] buffer, int length) {
		boolean isEnd = true;
		byte[] eor = Arrays.copyOfRange(buffer, length - Constant.EOR.length, length);
		for (int i = 0; i < Constant.EOR.length; i++) {
			isEnd = isEnd && (eor[i] == Constant.EOR[i]);
		}
		return isEnd;
	}

	public ICommand parseCommand(String command) {
		String cmd = StringUtils.split(command, " ")[0];
		try {
			FtpCommand ftpCommand = FtpCommand.valueOf(cmd.toUpperCase());
			this.ftpUserStruct.setCurrentCmd(ftpCommand);
			return ftpCommand.command();
		} catch (Throwable t) {
			return new InvalidCommand();
		}
	}

	public void handler() {
		this.start();
	}

}
