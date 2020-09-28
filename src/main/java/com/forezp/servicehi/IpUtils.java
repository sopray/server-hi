package com.forezp.servicehi;



import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IpUtils {

    private static final String[] HEADERS = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR",
            "X-Real-IP"
    };


    public static String getHostIp() {
        String ip = null;
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
                            && inetAddress.isSiteLocalAddress()) {
                        ip = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            //log.error("Fail to get IP address.", e);
        }
        return ip;
    }

    public static String getHostName() {
        String hostName = null;
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
                            && inetAddress.isSiteLocalAddress()) {
                        hostName = inetAddress.getHostName();
                    }
                }
            }
        } catch (SocketException e) {
            //log.error("Fail to get host name.", e);
        }
        return hostName;
    }

    /***
     * 获取客户端ip地址(可以穿透代理)
     * @param request HttpServletRequest
     * @return
     */
//    public static String getIpAddress(HttpServletRequest request) {
//        String ip = "";
//        for (String header : HEADERS) {
//            ip = request.getHeader(header);
//            if(isNotEmptyIp(ip)) {
//                break;
//            }
//        }
//        if(isEmptyIp(ip)){
//            ip = request.getRemoteAddr();
//        }
//        if(isNotEmptyIp(ip) && ip.contains(",")){
//            ip = ip.split(",")[0];
//        }
//        if("0:0:0:0:0:0:0:1".equals(ip) || StringUtils.isBlank(ip)){
//            ip = "127.0.0.1";
//        }
//        return ip;
//    }

    /**
     * 判断ip是否不为空，不为空返回true
     * @param ip
     * @return
     */
    public static boolean isNotEmptyIp(final String ip){
        return !isEmptyIp(ip);
    }
    /**
     * 判断ip是否为空，空返回true
     * @param ip
     * @return
     */
    public static boolean isEmptyIp(final String ip){
        return (ip == null || ip.length() == 0 || ip.trim().equals("") || "unknown".equalsIgnoreCase(ip));
    }



    public static void main(String[] str){
        String ars= "[https://res.jaadee.net/merchantApi/other/2020-08-17/1597656653837-8a6984efc594559f72273155f551ee04.jpeg]";
        String[] bok = ars.split(",");
        System.out.println(bok[0].replace("[","").replace("]",""));
    }
}
