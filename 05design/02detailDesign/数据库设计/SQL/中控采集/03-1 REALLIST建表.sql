-- Create table
create table REALLIST
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
tablespace SWJT_YXSW_ZK
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table REALLIST
  is '实时数据列表';
-- Add comments to the columns 
comment on column REALLIST.orgcode
  is '机构编码';
comment on column REALLIST.tagindex
  is '变量的索引';
comment on column REALLIST.tagname
  is '变量名';
comment on column REALLIST.tagdec
  is '描述';
comment on column REALLIST.t
  is '时间';
comment on column REALLIST.val
  is '值';
comment on column REALLIST.important
  is '是否重要参数 0：不是1：是(主要正对在线监测重要参数页面)';
comment on column REALLIST.type
  is '参数类型，关联表parametertype';
comment on column REALLIST.createtime
  is '创建时间';
comment on column REALLIST.yxbz
  is '有效标志  0:有效    1:无效';
comment on column REALLIST.revoketime
  is '撤消时间 ,决定不再用这个参数的时间';
comment on column REALLIST.site
  is '站点名称';
comment on column REALLIST.tltype
  is '种类：1：生产工艺数据  2：管网遥测数据';
comment on column REALLIST.unit
  is '单位';
comment on column REALLIST.lev
  is '标高';
comment on column REALLIST.alarmmax
  is '报警高值';
comment on column REALLIST.alarmmin
  is '报警低值';
comment on column REALLIST.py
  is '坐标Y';
comment on column REALLIST.px
  is '坐标X';
