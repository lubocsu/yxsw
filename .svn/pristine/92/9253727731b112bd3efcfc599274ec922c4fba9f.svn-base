
-- Create table
create table REALLIST_HIS
(
  orgcode    VARCHAR2(20) not null,
  tagindex   NUMBER(5) not null,
  tagname    VARCHAR2(50) not null,
  tagdec     VARCHAR2(50),
  t          DATE,
  val        NUMBER(12,2),
  important  VARCHAR2(2) default 0,
  type       VARCHAR2(5),
  createtime DATE,
  yxbz       VARCHAR2(2) default 0,
  revoketime DATE,
  site       VARCHAR2(20),
  tltype     VARCHAR2(2),
  unit       VARCHAR2(10),
  lev        NUMBER(5,2),
  alarmmax   NUMBER(10,2),
  alarmmin   NUMBER(10,2),
  py         VARCHAR2(20),
  px         VARCHAR2(20)
)
partition by range(t) interval(numtoyMinterval(1,'MONTH'))
 (
 partition p_2014_01_01 values less  than(to_date('2014-01-01','yyyy-mm-dd'))
 );
-- Add comments to the table 
comment on table REALLIST_HIS
  is '实时数据列表';
-- Add comments to the columns 
comment on column REALLIST_HIS.orgcode
  is '机构编码';
comment on column REALLIST_HIS.tagindex
  is '变量的索引';
comment on column REALLIST_HIS.tagname
  is '变量名';
comment on column REALLIST_HIS.tagdec
  is '描述';
comment on column REALLIST_HIS.t
  is '时间';
comment on column REALLIST_HIS.val
  is '值';
comment on column REALLIST_HIS.important
  is '是否重要参数 0：不是1：是(主要正对在线监测重要参数页面)';
comment on column REALLIST_HIS.type
  is '参数类型，关联表parametertype';
comment on column REALLIST_HIS.createtime
  is '创建时间';
comment on column REALLIST_HIS.yxbz
  is '有效标志  0:有效    1:无效';
comment on column REALLIST_HIS.revoketime
  is '撤消时间 ,决定不再用这个参数的时间';
comment on column REALLIST_HIS.site
  is '站点名称';
comment on column REALLIST_HIS.tltype
  is '种类：1：生产工艺数据  2：管网遥测数据';
comment on column REALLIST_HIS.unit
  is '单位';
comment on column REALLIST_HIS.lev
  is '标高';
comment on column REALLIST_HIS.alarmmax
  is '报警高值';
comment on column REALLIST_HIS.alarmmin
  is '报警低值';
comment on column REALLIST_HIS.py
  is '坐标Y';
comment on column REALLIST_HIS.px
  is '坐标X';
-- Create/Recreate indexes 
create index ZK_CREATE_INDEX on REALLIST_HIS (T)
  tablespace SWJT_YXSW_ZK_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create bitmap index ZK_ORGCODE_INDEX on REALLIST_HIS (ORGCODE)
  local tablespace SWJT_YXSW_ZK_IDX;
create bitmap index ZK_TAGINDEX_INDEX on REALLIST_HIS (TAGINDEX)
  local tablespace SWJT_YXSW_ZK_IDX;
