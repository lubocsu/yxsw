package com.upsoft.system.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * 
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：FileUtil.java<br>
* 摘要：文件上传工具<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年3月30日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年3月30日<br>
 */
public class FileUtil {
	
	/**
	 * 
	 * @date 2017年3月30日 下午12:57:11
	 * @author 胡毅
	 * @param filePath 文件完整路径
	 * @param in 文件输入流
	 */
	public static void saveFile(String filePath,InputStream in){
		File file = new File(filePath);
		// 判断服务文件夹是否存在
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		FileOutputStream outFile;
		byte[] b = new byte[128];
		int len;
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			outFile = new FileOutputStream(file);
			while ((len = in.read(b)) > 0){
				outFile.write(b, 0, len);
			}
			in.close();
			if(null!=outFile){
				outFile.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteFile(String filePath){
		File file = new File(filePath);
		File dateFolder = file.getParentFile();
		if(file.exists()){
			file.delete();
			// 判断日期文件夹是否是空的
			if(dateFolder.listFiles().length==0){
				dateFolder.delete();
			}
		}
	}
}
