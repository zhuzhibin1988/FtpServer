package com.lucifer.ftp.server;

import java.net.ServerSocket;
import java.net.Socket;

import com.lucifer.ftp.server.enums.FtpCommand;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 15, 2019 11:05:43 AM
 * @since 1.0.0
 */

@Setter
@Getter
public class FtpUserStruct {
	public final static int WAIT_USER = 0x0;
	public final static int USER_CONNECT = 0x1;
	public final static int USER_AUTH = 0x2;
	public final static int USER_LOGIN = 0x3;

	public final static int PASV_MODE = 0x0;
	public final static int POST_MODE = 0x1;

	public final static int BIT_TYPE = 0x0;
	public final static int ASCII_TYPE = 0x1;
	public final static int EBCDIC_TYPE = 0x2;

	private int logStatus = WAIT_USER;
	private int mode = PASV_MODE;
	private int transferType = BIT_TYPE;

	private String user = null;

	private String currentPath = null;

	private Socket passiveDataSocket = null;

	private ServerSocket passiveDataServerSocket = null;

	private Socket activeDataSocket = null;

	private Socket interactiveSocket = null;

	private FtpCommand currentCmd = null;

	public void reset() {
		this.logStatus = WAIT_USER;
		this.mode = PASV_MODE;
		this.transferType = BIT_TYPE;
		this.user = null;
		this.currentCmd = null;
		this.currentPath = null;
		this.passiveDataSocket = null;
		this.passiveDataServerSocket = null;
		this.activeDataSocket = null;
	}
}
