package com.upsoft.system.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：DictSequenceGernerator.java<br>
* 摘要：字典序列生成服务<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：Awesan<br>
* 完成日期：2015年2月4日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：Awesan<br>
* 完成日期：2015年2月4日<br>
*/
public class SequenceGernerator {
	public static long maxSysemNum = 0;
	public static long maxUserNum = 0;
	public static long maxDefinedNum = 0;
	
	static {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT (SELECT MAX(TO_NUMBER(SUBSTR(T.TREEID,2))) FROM SYS_DICT_TREE T WHERE T.TREEID LIKE 'D%') AS MAXDEFINED,(SELECT MAX(TO_NUMBER(SUBSTR(T.TREEID,2)))  FROM SYS_DICT_TREE T WHERE T.TREEID LIKE 'S%') AS MAXSYSTEM,(SELECT MAX(TO_NUMBER(SUBSTR(T.TREEID,2))) FROM SYS_DICT_TREE T WHERE T.TREEID LIKE 'U%')  AS MAXUSER FROM DUAL";
			conn = JdbcUtil.getConnection();
			stmt = conn.createStatement(); 
			rs = stmt.executeQuery(sql); 
			while (rs.next()) {
				maxSysemNum = rs.getLong("maxsystem");
				maxUserNum = rs.getLong("maxuser");
				maxDefinedNum = rs.getLong("maxdefined");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, stmt, conn);
		}
	}
	
	/**
	 * 返回指定类型序列号
	 * @date 2015年2月4日 上午11:53:39
	 * @author Awesan
	 * @param type
	 * @return 
	 */
	public static String getSequenceByType(int type){
		String startChar="";
		long num = 0;
		switch (type) {
		case 0:
			startChar = "S";
			num = ++maxSysemNum;
			break;
		case 1:
			startChar = "U";
			num = ++maxUserNum;
			break;
		case 2:
			startChar = "D";
			num = ++maxDefinedNum;
			break;
		}
		DecimalFormat df = new DecimalFormat("000000");
		return startChar + df.format(num);
	}
	 
	/**
	 * 根据类型
	 * @date 2015年2月4日 下午1:02:03
	 * @author Awesan
	 * @param type 
	 */
	public static void rollbackNum(int type){
		switch (type) {
		case 0:
			maxSysemNum--;
			break;
		case 1:
			maxUserNum--;
			break;
		case 2:
			maxDefinedNum--;
			break;
		}
	}
}
