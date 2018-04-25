package com.ceiec.webguide.formal.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * CreateDate: 2018/3/12 <br/>
 * Author: WangHao <br/>
 * Description:
 **/

public class IpAddressUtil {

    /**
     * 获取本机正确的IP地址
     *
     * @return String xxx.xxx.xxx.xxx形式的String
     */
    public static String getLocalIp() {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface net = en.nextElement();
                String name = net.getName();
                if (!name.contains("docker") && !name.contains("lo") && !name.contains("virbr0")) {
                    for (Enumeration<InetAddress> enumIpAddress = net.getInetAddresses(); enumIpAddress.hasMoreElements();) {
                        InetAddress inetAddress = enumIpAddress.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipAddress = inetAddress.getHostAddress().toString();
                            if (!ipAddress.contains("::") && !ipAddress.contains("0:0:") && !ipAddress.contains("fe80")) {
                                return ipAddress;
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return ip;
    }
}
