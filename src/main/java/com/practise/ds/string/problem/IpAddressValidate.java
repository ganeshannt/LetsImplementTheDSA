package com.practise.ds.string.problem;

public class IpAddressValidate {


    private static String validIPAddress(String ip) {
        if (isValidIpv4(ip)) {
            return ("IPv4");
        } else if (isValidIpv6(ip)) {
            return ("IPv6");
        }
        return ("Neither");
    }

    private static boolean isValidIpv4(String ip) {
        if (ip.endsWith(".")) {
            return false;
        }
        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        for (String part : parts) {
            if (!part.matches("\\d{1,3}") || Integer.parseInt(part) > 255 || (part.startsWith("0") && (part.length() > 1))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidIpv6(String ip) {

        if (ip.endsWith(":")) {
            return false;
        }

        String[] parts = ip.split(":");

        if (parts.length != 8) {
            return false;
        }
        for (String part : parts) {
            if (!part.matches("[0-9a-fA-F]{1,4}")) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(validIPAddress("12..33.4"));
    }
}
