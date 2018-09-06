package com.nsit.testengine.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public interface CommonUtils {

	public static String stackToString(Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}
