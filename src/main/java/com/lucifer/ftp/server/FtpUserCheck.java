package com.lucifer.ftp.server;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc
 * @author zhuzhibin@kuaizi.co
 * @date Mar 15, 2019 10:08:57 AM
 * @since 1.0.0
 */
public class FtpUserCheck {

	private final static Map<String, UserInfo> userMap = new HashMap<String, UserInfo>();

	static {
		userMap.put("ftp", new UserInfo("ftp", "1", "/"));
	}

	public static boolean checkUser(String user) {
		return userMap.containsKey(user);

	}

	public static boolean checkUserPassword(String user, String password) {
		if (checkUser(user)) {
			return password.equals(userMap.get(user).getPassword());
		} else {
			return false;
		}
	}

	public static boolean checkUserPath(String user, String path) {
		String rootPath = userMap.get(user).getRootPath();
		return rootPath.startsWith(rootPath);
	}

	public static String getRootPath(String user) {
		return userMap.get(user).getRootPath();
	}

	@Setter
	@Getter
	static class UserInfo {
		private String user;
		private String password;
		private String rootPath;

		public UserInfo(String user, String password, String rootPath) {
			this.user = user;
			this.password = password;
			this.rootPath = rootPath;
		}
	}
}
