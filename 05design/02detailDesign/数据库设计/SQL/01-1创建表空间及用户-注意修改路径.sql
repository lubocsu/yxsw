﻿
--常规表空间
create tablespace SWJT_YXSW
logging
datafile 'E:\oracle\product\10.2.0\oradata\portalbiz\swjt_yxsw.dbf'
size 600m
autoextend on
next 128m 
extent management local;

--临时表空间
create temporary tablespace SWJT_YXSW_TEMP  
tempfile 'E:\oracle\product\10.2.0\oradata\portalbiz\SWJT_YXSW_TEMP.dbf' 
size 50m  
autoextend on  
next 50m maxsize 2048m  
extent management local;

--索引表空间
create tablespace SWJT_YXSW_IDX
logging
datafile 'E:\oracle\product\10.2.0\oradata\portalbiz\swjt_yxsw_idx.dbf'
size 64m
autoextend on
next 16m 
extent management local;


--创建用户
create user swjt_yxsw identified by swjt_yxsw
  default tablespace SWJT_YXSW
  temporary tablespace SWJT_YXSW_TEMP
  profile DEFAULT
  quota unlimited on SWJT_YXSW;
-- Grant/Revoke role privileges 
grant connect to swjt_yxsw;
grant resource to swjt_yxsw;
-- Grant/Revoke system privileges 
grant unlimited tablespace to swjt_yxsw;

