package com.lucifer.ftp.server.enums;

/**
* @desc
* @author zhuzhibin@kuaizi.co
* @date Mar 14, 2019 3:06:18 PM
* @since 1.0.0
*/

/**
 * https://tools.ietf.org/html/rfc959
 * 
 * @author zhuzhibin
 */
public enum FtpReply {
	/**
	 * code:110
	 * 新文件指示器上的重启标记
	 * Restart marker reply.
	 * In this case, the text is exact and not left to the
	 * particular implementation; it must read:
	 * MARK yyyy = mmmm
	 * Where yyyy is User-process data stream marker, and mmmm
	 * server's equivalent marker (note the spaces between markers
	 * and "=")
	 */
	RESTART_MARKER(110, "新文件指示器上的重启标记"),
	/**
	 * code:120
	 * 服务器准备就绪的时间（分钟数）
	 * Service ready in n minutes.
	 */
	SERVICE_READY_LATER(120, "服务器准备就绪的时间(分钟数)"),
	/**
	 * code:125
	 * 打开数据连接，开始传输
	 * Data connection already open; transfer starting.
	 */
	DATA_CONNECTION_OPEN_AND_TRANSFER(125, "打开数据连接，开始传输"),
	/**
	 * code:150
	 * 打开连接
	 * File status okay; about to open data connection.
	 */
	DATA_CONNECTION_OPEN_AND_FILE_OK(150, "打开连接"),
	/**
	 * code:200
	 * 成功
	 * Command okay.
	 */
	CMD_OK(200, "成功"),
	/**
	 * code:202
	 * 命令没有执行
	 * Command not implemented, superfluous at this site.
	 */
	CMD_SUPERFLUOUS(202, "命令没有执行"),
	/**
	 * code:211
	 * 系统状态回复
	 * System status, or system help reply.
	 */
	SYS_STATUS(211, "系统状态回复"),
	/**
	 * code:212
	 * 目录状态回复
	 * Directory status
	 */
	DIRECTORY_STATUS(212, "目录状态回复"),
	/**
	 * code:213
	 * 文件状态回复
	 * File status
	 */
	FILE_STATUS(213, "文件状态回复"),
	/**
	 * code:214
	 * 帮助信息回复
	 * Help message.
	 * On how to use the server or the meaning of a particular
	 * non-standard command. This reply is useful only to the
	 * human user.
	 */
	HELP_MESSAGE(214, "帮助信息回复"),
	/**
	 * code:215
	 * 系统类型回复
	 * NAME system type.
	 * Where NAME is an official system name from the list in the
	 * Assigned Numbers document.
	 */
	SYS_REPLY(215, "系统类型回复"),
	/**
	 * code:220
	 * 服务就绪
	 * Service ready for new user.
	 */
	SERVICE_READY(220, "服务就绪"),
	/**
	 * code:221
	 * 退出网络
	 * Service closing control connection.
	 * Logged out if appropriate.
	 */
	SERVICE_QUIT(221, "退出网络"),
	/**
	 * code:225
	 * 打开数据连接
	 * Data connection open; no transfer in progress.
	 */
	DATA_CONNECTION_OPEN(225, "打开数据连接"),
	/**
	 * code:226
	 * 结束数据连接
	 * Closing data connection.
	 * Requested file action successful (for example, file
	 * transfer or file abort).
	 */
	DATA_CONNECTION_CLOSE(226, "结束数据连接"),
	/**
	 * code:227
	 * 进入被动模式（IP 地址、ID 端口）
	 * Entering Passive Mode (h1,h2,h3,h4,p1,p2).
	 */
	ENTER_PASV_MODE(227, "进入被动模式（IP 地址、ID 端口）"),
	/**
	 * code:230
	 * 用户登录
	 * User logged in, proceed.
	 */
	LOG_IN(230, "用户登录"),
	/**
	 * code:250
	 * 文件行为完成
	 * Requested file action okay, completed.
	 */
	FILE_ACTION_COMPLETED(250, "文件行为完成"),
	/**
	 * code:257
	 * 路径名建立
	 * "PATHNAME" created.
	 */
	PATH_CREATED(257, "路径名建立"),
	/**
	 * code:331
	 * 要求密码
	 * User name okay, need password.
	 */
	NEED_PWD(331, "要求密码"),
	/**
	 * code:332
	 * 要求帐号
	 * Need account for login.
	 */
	NEED_ACCOUNT(332, "要求帐号"),
	/**
	 * code:350
	 * 文件行为暂停
	 * Requested file action pending further information.
	 */
	FILE_ACTION_PEND(350, "文件行为暂停"),
	/**
	 * code:421
	 * 服务关闭
	 * Service not available, closing control connection.
	 * This may be a reply to any command if the service knows it
	 * must shut down.
	 */
	SERVICE_CLOSE(421, "服务关闭"),
	/**
	 * code:425
	 * 无法打开数据连接
	 * Can't open data connection.
	 */
	DATA_CONNECTION_OPEN_FAIL(425, "无法打开数据连接"),
	/**
	 * code:426
	 * 结束连接
	 * Connection closed; transfer aborted.
	 */
	CONNECTION_CLOSE(426, "结束连接"),
	/**
	 * code:450
	 * 文件不可用
	 * Requested file action not taken.
	 * File unavailable (e.g., file busy).
	 */
	FILE_BUSY(450, "文件不可用"),
	/**
	 * code:451
	 * 遇到本地错误
	 * Requested action aborted. Local error in processing.
	 */
	LOCAL_ERROR(451, "遇到本地错误"),
	/**
	 * code:452
	 * 磁盘空间不足
	 * Requested action not taken.
	 * Insufficient storage space in system.
	 */
	INSUFFICIENT_STORAGE(452, "磁盘空间不足"),
	/**
	 * code:500
	 * 无效命令
	 * Syntax error, command unrecognized.
	 * This may include errors such as command line too long.
	 */
	CMD_UNRECOGNIZED(500, "无效命令"),
	/**
	 * code:501
	 * 错误参数
	 * Syntax error in parameters or arguments.
	 */
	CMD_ARGUMENT_ERROR(501, "错误参数"),
	/**
	 * code:502
	 * 命令没有执行
	 * Command not implemented.
	 */
	CMD_NOT_IMPLEMENT(502, "命令没有执行"),
	/**
	 * code:503
	 * 错误指令序列
	 * Bad sequence of commands.
	 */
	CMD_BAD_SEQUENCE(503, "错误指令序列"),
	/**
	 * code:504
	 * 无效命令参数
	 * Command not implemented for that parameter.
	 */
	CMD_UNVAILABLE(504, "无效命令参数"),
	/**
	 * code:530
	 * 未登录网络
	 * Not logged in.
	 */
	NOT_LOG_IN(530, "未登录网络"),
	/**
	 * code:532
	 * 存储文件需要帐号
	 * Need account for storing files.
	 */
	NEED_ACCOUNT_FOR_STORE(532, "存储文件需要帐号"),
	/**
	 * code:550
	 * 文件不可用
	 * Requested action not taken.
	 * File unavailable (e.g., file not found, no access).
	 */
	FILE_NOT_FOUND(550, "文件不可用"),
	/**
	 * code:551
	 * 不知道的页类型
	 * Requested action aborted. Page type unknown.
	 */
	PAGE_TYPE_UNKNOWN(551, "不知道的页类型"),
	/**
	 * code:552
	 * 超过存储分配
	 * Requested file action aborted.
	 * Exceeded storage allocation (for current directory or
	 * dataset).
	 */
	EXCEED_STORAGE(552, "超过存储分配"),
	/**
	 * code:553
	 * 文件名不允许
	 * Requested action not taken.
	 * File name not allowed.
	 */
	FILE_NAME_NOT_ALLOW(553, "文件名不允许");

	private int replyCode;

	private String replyMessage;

	FtpReply(int replyCode, String replyMessage) {
		this.replyCode = replyCode;
		this.replyMessage = replyMessage;
	}

	public int code() {
		return this.replyCode;
	}

	public String message() {
		return this.replyCode + " " + this.replyMessage;
	}
}
