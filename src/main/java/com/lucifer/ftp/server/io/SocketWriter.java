package com.lucifer.ftp.server.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import com.lucifer.ftp.server.Constant;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 20, 2019 5:07:03 PM
 * @since 1.0.0
 */
public class SocketWriter {
	private Socket socket = null;

	public SocketWriter(Socket socket) {
		this.socket = socket;
	}

	public int write(byte[] content) throws IOException {
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(content);
		outputStream.flush();
		return content.length;
	}

	public int write(String content) throws IOException {
		return write(content.getBytes());
	}

	public int writeWithEor(byte[] content) throws IOException {
		byte[] byteContent = Arrays.copyOf(content, content.length + Constant.EOR.length);
		System.arraycopy(Constant.EOR, 0, byteContent, byteContent.length - Constant.EOR.length,
		    Constant.EOR.length);
		write(byteContent);
		return byteContent.length;
	}

	public int writeWithEor(String content) throws IOException {
		return writeWithEor(content.getBytes());
	}
}
