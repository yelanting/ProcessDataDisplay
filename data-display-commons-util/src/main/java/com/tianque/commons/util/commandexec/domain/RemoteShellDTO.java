/**
 * @author : 孙留平
 * @since : 2019年5月10日 上午9:45:18
 * @see:
 */
package com.tianque.commons.util.commandexec.domain;

/**
 * @author : Administrator
 * @since : 2019年5月10日 上午9:45:18
 * @see :
 */
public class RemoteShellDTO {
	/**
	 * 远程ip
	 */
	private String remoteIp;

	/**
	 * 远程端口
	 */
	private Integer remotePort;

	/**
	 * 远程脚本
	 */
	private String remoteShell;

	/**
	 * 登陆用户
	 */
	private String loginUsername;

	/**
	 * 登陆密码
	 */
	private String loginPassword;

	/**
	 * 登陆之后的判断字符 # $ 或者>
	 */
	private Character prompt;

	/**
	 * @return the remoteIp
	 */
	public String getRemoteIp() {
		return remoteIp;
	}

	/**
	 * @param remoteIp
	 *            the remoteIp to set
	 */
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	/**
	 * @return the remotePort
	 */
	public Integer getRemotePort() {
		return remotePort;
	}

	/**
	 * @param remotePort
	 *            the remotePort to set
	 */
	public void setRemotePort(Integer remotePort) {
		this.remotePort = remotePort;
	}

	/**
	 * @return the remoteShell
	 */
	public String getRemoteShell() {
		return remoteShell;
	}

	/**
	 * @param remoteShell
	 *            the remoteShell to set
	 */
	public void setRemoteShell(String remoteShell) {
		this.remoteShell = remoteShell;
	}

	/**
	 * @return the loginUsername
	 */
	public String getLoginUsername() {
		return loginUsername;
	}

	/**
	 * @param loginUsername
	 *            the loginUsername to set
	 */
	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	/**
	 * @return the loginPassword
	 */
	public String getLoginPassword() {
		return loginPassword;
	}

	/**
	 * @param loginPassword
	 *            the loginPassword to set
	 */
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	/**
	 * @return the prompt
	 */
	public Character getPrompt() {
		return prompt;
	}

	/**
	 * @param prompt
	 *            the prompt to set
	 */
	public void setPrompt(Character prompt) {
		this.prompt = prompt;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RemoteShellDTO [remoteIp=" + remoteIp + ", remotePort="
				+ remotePort + ", remoteShell=" + remoteShell
				+ ", loginUsername=" + loginUsername + ", loginPassword="
				+ loginPassword + ", prompt=" + prompt + "]";
	}

}
