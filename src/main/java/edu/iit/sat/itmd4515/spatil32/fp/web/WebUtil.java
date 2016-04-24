/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.web;

/**
 * @author Dell
 * This class contains static methods to check input parameters.
 */
public class WebUtil {

    /**
     * Checks if input parameter is empty
     * @param param parameter
     * @return true or false
     */
    public static boolean isEmpty(String param) {
        if ((param == null) || (param.trim().equals(""))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Trims input parameter
     * @param param parameter
     * @return string
     */
    public static String trimParam(String param) {
        if (isEmpty(param)) {
            return null;
        } else {
            return param.trim();
        }
    }
}