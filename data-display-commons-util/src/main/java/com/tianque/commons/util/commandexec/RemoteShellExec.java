/**
 * @author : 孙留平
 * @since : 2019年5月10日 上午9:44:40
 * @see:
 */
package com.tianque.commons.util.commandexec;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.net.telnet.TelnetClient;

import com.tianque.commons.util.commandexec.domain.RemoteShellDTO;

/**
 * @author : Administrator
 * @since : 2019年5月10日 上午9:44:40
 * @see :
 */
public class RemoteShellExec {
    private RemoteShellDTO remoteShellDTO;
    private InputStream inputStream;
    private PrintStream out;
    private TelnetClient telnetClient = new TelnetClient("VT220");

    public RemoteShellExec() {
    }

    public RemoteShellExec(RemoteShellDTO remoteShellDTO) {
        this.remoteShellDTO = remoteShellDTO;
    }

    /**
     * @return the remoteShellDTO
     */
    public RemoteShellDTO getRemoteShellDTO() {
        return remoteShellDTO;
    }

    /**
     * @param remoteShellDTO
     *            the remoteShellDTO to set
     */
    public void setRemoteShellDTO(RemoteShellDTO remoteShellDTO) {
        this.remoteShellDTO = remoteShellDTO;
    }

    public void getConnection() {
        try {
            this.telnetClient.connect(this.remoteShellDTO.getRemoteIp(),
                    this.remoteShellDTO.getRemotePort());

            this.inputStream = this.telnetClient.getInputStream();
            this.out = new PrintStream(this.telnetClient.getOutputStream());

            System.out.println("连接成功");
            this.remoteShellDTO.setPrompt(
                    "root".equals(this.remoteShellDTO.getLoginUsername()) ? '#'
                            : '>');
            login();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute() {
        getConnection();
        try {
            String result = sendCommand(this.remoteShellDTO.getRemoteShell());
            System.out
                    .println(new String(result.getBytes("ISO-8859-1"), "gbk"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public String sendCommand(String command) {
        write(command);
        return readUntil(this.remoteShellDTO.getPrompt() + "");
    }

    public String readUntil(String pattern) {
        char lastChar = pattern.charAt(pattern.length() - 1);
        StringBuffer sBuffer = new StringBuffer();
        try {
            char ch = (char) this.inputStream.read();
            while (true) {
                sBuffer.append(ch);
                if (ch == lastChar) {
                    if (sBuffer.toString().endsWith(pattern)) {
                        return sBuffer.toString();
                    }
                }
                ch = (char) this.inputStream.read();
                System.out.println(ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void write(String value) {
        out.println(value);
        out.flush();
    }

    public void login() {

        readUntil("login:");
        write(this.remoteShellDTO.getLoginUsername());

        readUntil("password:");

        write(this.remoteShellDTO.getLoginPassword());

        readUntil(this.remoteShellDTO.getPrompt() + "");
    }

    public void disconnect() {
        try {
            this.telnetClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        RemoteShellDTO remoteShellDTO = new RemoteShellDTO();

        // remoteShellDTO.setRemoteIp("192.168.110.15");
        // remoteShellDTO.setRemotePort(23);
        // remoteShellDTO.setLoginUsername("admin");
        // remoteShellDTO.setLoginPassword("Admin@123");
        // remoteShellDTO.setRemoteShell("ls");

        remoteShellDTO.setRemoteIp("192.168.110.57");
        remoteShellDTO.setRemotePort(23);
        remoteShellDTO.setLoginUsername("administrator");
        remoteShellDTO.setLoginPassword("AutoTest123");
        remoteShellDTO.setRemoteShell("tasklist");
        RemoteShellExec remoteShellExec = new RemoteShellExec();
        remoteShellExec.setRemoteShellDTO(remoteShellDTO);
        remoteShellExec.execute();
    }
}
