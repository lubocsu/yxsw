package com.upsoft.system.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：JdbcUtil.java<br>
* 摘要：jdbc工具类<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：Awesan<br>
* 完成日期：2015年2月6日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：Awesan<br>
* 完成日期：2015年2月6日<br>
*/
public class JdbcUtil {
	private static String driver = null;
	private static String url = null; 
    private static String username = null; 
    private static String password = null; 
    private static Properties props = new Properties(); 
    
    static { 
        try { 
        	props.load(JdbcUtil.class.getClassLoader().getResourceAsStream("application.properties")); 
        } catch (IOException e) { 
        	e.printStackTrace();
        } 
        driver = (props.getProperty("jdbc.driver"));
        url = (props.getProperty("jdbc.url")); 
        username = (props.getProperty("jdbc.username")); 
        password = (props.getProperty("jdbc.password")); 
        //注册驱动类
        //
        try { 
        	Class.forName(driver); 
        } catch (ClassNotFoundException e) { 
        	e.printStackTrace();
        } 
    } 
    
    /**
     * 获得连接
     * @date 2015年2月6日 上午9:38:42
     * @author Awesan
     * @return 
     */
    public static Connection getConnection() { 
        Connection conn = null; 
        //创建数据库连接 
        try { 
        	conn = DriverManager.getConnection(url, username, password); 
        } catch (SQLException e) { 
        	e.printStackTrace();
        } 
        return conn; 
    }
    
    /**
     * 关闭资源
     * @date 2015年2月6日 上午9:42:09
     * @author Awesan
     * @param rs
     * @param stmt
     * @param con 
     */
    public static void close(ResultSet rs, Statement stmt, Connection con){
 	   try{
 	      if(rs!=null) rs.close();
 	    }catch(Exception ex){
 		  ex.printStackTrace();
 		}
 		try{
 	      if(stmt!=null) stmt.close();
 	    }catch(Exception ex){
 		  ex.printStackTrace();
 		}
 		try{
 	      if(con!=null) con.close();
 	    }catch(Exception ex){
 		  ex.printStackTrace();
 		}
 	}
}
