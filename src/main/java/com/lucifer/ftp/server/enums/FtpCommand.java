package com.lucifer.ftp.server.enums;

import com.lucifer.ftp.server.command.ICommand;
import com.lucifer.ftp.server.command.data.ListCommand;
import com.lucifer.ftp.server.command.data.NlstCommand;
import com.lucifer.ftp.server.command.data.PasvCommand;
import com.lucifer.ftp.server.command.data.PortCommand;
import com.lucifer.ftp.server.command.data.RetrCommand;
import com.lucifer.ftp.server.command.interactive.CwdCommand;
import com.lucifer.ftp.server.command.interactive.FeatCommand;
import com.lucifer.ftp.server.command.interactive.PassCommand;
import com.lucifer.ftp.server.command.interactive.PwdCommand;
import com.lucifer.ftp.server.command.interactive.QuitCommand;
import com.lucifer.ftp.server.command.interactive.SystCommand;
import com.lucifer.ftp.server.command.interactive.UserCommand;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 14, 2019 2:45:14 PM
 * @since 1.0.0
 */
public enum FtpCommand {
    // 中断数据连接程序
    // ABOR,
    // 系统特权帐号
    // ACCT,
    // 为服务器上的文件存储器分配字节
    // ALLO,
    // 添加文件到服务器同名文件
    // APPE,
    // 改变服务器上的父目录
    // CDUP,
    // 改变服务器上的工作目录
	CWD(new CwdCommand()),
    // 删除服务器上的指定文件
    // DELE,
    // 返回指定命令信息
    // HELP,
    // 如果是文件名列出文件信息，如果是目录则列出文件列表
	LIST(new ListCommand()),
    // 传输模式（S=流模式，B=块模式，C=压缩模式）
    // MODE,
    // 在服务器上建立指定目录
    // MKD,
    // 列出指定目录内容
	NLST(new NlstCommand()),
    // 无动作，除了来自服务器上的承认
    // NOOP,
    // 系统登录密码
	PASS(new PassCommand()),
    // 请求服务器等待数据连接
	PASV(new PasvCommand()),
    // IP 地址和两字节的端口 ID
	PORT(new PortCommand()),
    // 显示当前工作目录
	PWD(new PwdCommand()),
    // 从 FTP 服务器上退出登录
	QUIT(new QuitCommand()),
    // 重新初始化登录状态连接
    // REIN,
    // 由特定偏移量重启文件传递
    // REST,
    // 从服务器上找回（复制）文件
	RETR(new RetrCommand()),
    // 在服务器上删除指定目录
    // RMD,
    // 对旧路径重命名
    // RNFR,
    // 对新路径重命名
    // RNTO,
    // 由服务器提供的站点特殊参数
    // SITE,
    // 挂载指定文件结构
    // SMNT,
    // 在当前程序或目录上返回信息
    // STAT,
    // 储存（复制）文件到服务器上
    // STOR,
    // 储存文件到服务器名称上
    // STOU,
    // 数据结构（F=文件，R=记录，P=页面）
    // STRU,
    // 返回服务器使用的操作系统
	SYST(new SystCommand()),
    // 数据类型（A=ASCII，E=EBCDIC，I=binary）
    // TYPE,
    // 系统登录的用户名
	USER(new UserCommand()),

	FEAT(new FeatCommand());

	private ICommand command = null;

	FtpCommand(ICommand command) {
		this.command = command;
	}

	public ICommand command() {
		return this.command;
	}
}
