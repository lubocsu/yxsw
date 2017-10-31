/*
 * EncodingUtil.java
 * Created on 2015年1月28日 下午6:06:51
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.system.util;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import sun.misc.BASE64Decoder;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：EncodingUtil.java<br>
 * 摘要：编码转换工具类<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月28日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月28日<br>
 */
public class EncodingUtil {
	
	public static Boolean isChinese(char c){
		String reg = "[\u4e00-\u9fa5]";
		return Pattern.matches(reg, String.valueOf(c));
	}
	
	public static Boolean isChinese(String str){
		String reg = "[\u4e00-\u9fa5]+";
		return Pattern.matches(reg, str);
	}
	
	public static Boolean isContainChinese(String str){
		for (char c : str.toCharArray()) {
			if (isChinese(c))	return true;
		}
		return false;
	}
	
	public static String encode(String source, String code) throws UnsupportedEncodingException{
		return URLEncoder.encode(source, code);  
	}
	
	/**
	 * Base64字符串码转字节数组
	 * 
	 * @date 2015年3月9日 上午11:53:53
	 * @author TW
	 * @param imgStr
	 *            Base64字符串码
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static byte[] ImageStr2Bytes(String imgStr) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(imgStr);
			return b;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 图片旋转90度输出
	 * 
	 * @date 2015年3月9日 上午11:53:23
	 * @author TW
	 * @return
	 */
	public static BufferedImage rotate90SX(BufferedImage bi) {
		int width = bi.getWidth();
		int height = bi.getHeight();

		BufferedImage biFlip = new BufferedImage(height, width, bi.getType());

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				biFlip.setRGB(j, i, bi.getRGB(width - i - 1, j));

		return biFlip;
	}
	
}
