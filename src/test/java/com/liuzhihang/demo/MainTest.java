package com.liuzhihang.demo;

/**
 * @author liuzhihang
 * @date 2020/10/15 17:11
 */
public class MainTest {


    public static void main(String[] args) {

        // int hc1 = hashCodeOf("Authorization");
        // int hc2 = hashCodeOf("Authorization");
        //
        // System.out.println("hc1 = " + hc1);
        // System.out.println("hc2 = " + hc2);

        int higher1 = higher((byte) 'a');
        int higher2 = higher((byte) 'A');

        System.out.println("higher1 = " + higher1);
        System.out.println("higher2 = " + higher2);

    }


    static int hashCodeOf(String headerName) {
        int hc = 17;

        for (int i = 0; i < headerName.length(); ++i) {
            hc = (hc << 4) + hc + higher((byte) headerName.charAt(i));
        }
        return hc;
    }

    private static int higher(byte b) {
        return b & (b >= 'a' && b <= 'z' ? 0xDF : 0xFF);
    }

}
