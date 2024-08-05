package com.nikiarsov.helpers;

/**
 * *
 * @author Nikolai Arsov
 * @version 1.0
 */
public class ConsolePrint implements Printable {

    /**
     * @param color
     * @param arg
     */
    @Override
    public void execPrint(String color, String arg) {
        arg = this.getColor(color) + arg;
        System.out.println(arg);
    }

    /**
     * @param color
     */
    private String getColor(String color) {
        String hashColor = "";
        if (color.equals("blue")) {
            hashColor = "\u001B[34m";
        } else if (color.equals("yellow")) {
            hashColor = "\u001B[33m";
        } else if (color.equals("white")) {
            hashColor = "\u001B[37m";
        } else if (color.equals("reset")) {
            hashColor = "\u001B[0m";
        } else {
            hashColor = "\u001B[0m";
        }

        return hashColor;
    }
}
