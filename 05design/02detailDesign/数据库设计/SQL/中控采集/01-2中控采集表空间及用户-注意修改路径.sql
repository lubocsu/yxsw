--常规表空间
create tablespace swjt_yxsw_zk
logging
datafile 'F:\oradata\swjt_yxsw_zk\swjt_yxsw_zk.dbf'
size 64m
autoextend on
next 16m 
extent management local;

--临时表空间
create temporary tablespace SWJT_YXSW_ZK_TEMP  

tempfile 'F:\oradata\swjt_yxsw_zk\SWJT_YXSW_ZK_TEMP.dbf'
size 64m
autoextend on
next 16m 
extent management local;


--索引表空间注意修改路径
create tablespace swjt_yxsw_zk_IDX
logging
datafile 'F:\oradata\swjt_yxsw_zk\swjt_yxsw_zk_idx.dbf'
size 64m
autoextend on
next 16m 
extent management local;


--创建用户
create user swjt_yxsw_zk identified by swjt_yxsw_zk
  default tablespace swjt_yxsw_zk
  temporary tablespace SWJT_YXSW_ZK_TEMP
  profile DEFAULT
  quota unlimited on swjt_yxsw_zk;
-- Grant/Revoke role privileges 
grant connect to swjt_yxsw_zk;
grant resource to swjt_yxsw_zk;
-- Grant/Revoke system privileges 
grant unlimited tablespace to swjt_yxsw_zk;
