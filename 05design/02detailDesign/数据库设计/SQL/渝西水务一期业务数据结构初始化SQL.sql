/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2017/10/17 10:49:44                          */
/*==============================================================*/


alter table BIZ_T_BASE_FACTORY_INFO
   drop primary key cascade;

drop table BIZ_T_BASE_FACTORY_INFO cascade constraints;

alter table BIZ_T_GG_CHECK_ITEM
   drop primary key cascade;

drop table BIZ_T_GG_CHECK_ITEM cascade constraints;

alter table BIZ_T_GG_CHECK_ITEMF_KXX
   drop primary key cascade;

drop table BIZ_T_GG_CHECK_ITEMF_KXX cascade constraints;

drop table BIZ_T_GG_CHECK_ITEMF_KXX_SEL cascade constraints;

alter table BIZ_T_GG_CHECK_ITEM_DETAIL
   drop primary key cascade;

drop table BIZ_T_GG_CHECK_ITEM_DETAIL cascade constraints;

drop index IDX_T_GG_MSG_MANAGE_CREATIME;

drop index IDX_T_GG_MSG_MANAGE_IMPLEVEL;

drop index IDX_T_GG_MSG_MANAGE_CREATOR;

drop index IDX_T_GG_MSG_MANAGE_WSC;

alter table BIZ_T_GG_MSG_MANAGE
   drop primary key cascade;

drop table BIZ_T_GG_MSG_MANAGE cascade constraints;

drop index IDX_T_GG_NOTICE_MANAGE_BEL_WSC;

drop index IDX_T_GG_NOTICE_MANAGE_CT;

drop index IDX_T_GG_NOTICE_MANAGE_LIM;

drop index IDX_T_GG_NOTICE_MANAGE_ALIVE;

drop index IDX_T_GG_NOTICE_MANAGE_TYPE;

alter table BIZ_T_GG_NOTICE_MANAGE
   drop primary key cascade;

drop table BIZ_T_GG_NOTICE_MANAGE cascade constraints;

drop index IDX_T_GG_SBSS_ATTACH_NOT_REAPT;

drop index IDX_T_GG_SBSS_ATTACH_BASE_WSC;

drop index IDX_T_GG_SBSS_ATTACH_BASE_TYPE;

drop index IDX_T_GG_SBSS_ATTACH_BASE_CODE;

alter table BIZ_T_GG_SBSS_ATTACH_BASE
   drop primary key cascade;

drop table BIZ_T_GG_SBSS_ATTACH_BASE cascade constraints;

alter table BIZ_T_GG_WARNING_MANAGE
   drop primary key cascade;

drop table BIZ_T_GG_WARNING_MANAGE cascade constraints;

alter table BIZ_T_SBSS_RELATION
   drop primary key cascade;

drop table BIZ_T_SBSS_RELATION cascade constraints;

drop index IDX_T_SB_BASEINFO_SB_BEL_WSC;

drop index IDX_T_SB_BASEINFO_SB_SORT;

drop index IDX_T_SB_BASEINFO_SB_TYPE;

drop index IDX_T_SB_BASEINFO_SB_MANUF;

drop index IDX_T_SB_BASEINFO_SB_GCJK;

drop index IDX_T_SB_BASEINFO_CODE;

alter table BIZ_T_SB_BASEINFO
   drop primary key cascade;

drop table BIZ_T_SB_BASEINFO cascade constraints;

alter table BIZ_T_SB_BASEINFO_HISTORY
   drop primary key cascade;

drop table BIZ_T_SB_BASEINFO_HISTORY cascade constraints;

alter table BIZ_T_SB_TYPES
   drop primary key cascade;

drop table BIZ_T_SB_TYPES cascade constraints;

drop index IDX_T_SS_BASEINFO_LAYER;

drop index IDX_T_SS_BASEINFO_PID;

drop index IDX_T_SS_BASEINFO_CODE;

drop index IDX_T_SS_BASEINFO_BEL_WSC;

alter table BIZ_T_SS_BASEINFO
   drop primary key cascade;

drop table BIZ_T_SS_BASEINFO cascade constraints;

alter table BIZ_T_XJD_ITEM
   drop primary key cascade;

drop table BIZ_T_XJD_ITEM cascade constraints;

drop index IDX_XJD_ITEM_DETAIL_SBSSTYPE;

drop index IDX_XJD_ITEM_DETAIL_SBSSCODE;

drop index IDX_XJD_ITEM_DETAIL_ITEMIDFK;

alter table BIZ_T_XJD_ITEM_DETAIL
   drop primary key cascade;

drop table BIZ_T_XJD_ITEM_DETAIL cascade constraints;

drop index IDX_T_XJ_CX_TASK_WSC;

drop index IDX_T_XJ_CX_TASK_KZJ;

drop index IDX_T_XJ_CX_TASK_STATUS;

drop index IDX_T_XJ_CX_TASK_TYPE;

drop index IDX_T_XJ_CX_TASK_CODE;

drop index IDX_T_XJ_CX_TASK_DATE;

alter table BIZ_T_XJ_CX_TASK
   drop primary key cascade;

drop table BIZ_T_XJ_CX_TASK cascade constraints;

drop index IDX_T_XJ_CX_TASK_ITEM_RFIDCOND;

drop index IDX_T_XJ_CX_TASK_ITEM_RFIDCONT;

drop index IDX_T_XJ_CX_TASK_ITEM_IDFK;

drop index IDX_T_XJ_CX_TASK_ITEM_FK;

alter table BIZ_T_XJ_CX_TASK_ITEM
   drop primary key cascade;

drop table BIZ_T_XJ_CX_TASK_ITEM cascade constraints;

alter table BIZ_T_XJ_CX_TASK_ITEM_FAULTLC
   drop primary key cascade;

drop table BIZ_T_XJ_CX_TASK_ITEM_FAULTLC cascade constraints;

alter table BIZ_T_XJ_CX_TASK_ITEM_RST_DET
   drop primary key cascade;

drop table BIZ_T_XJ_CX_TASK_ITEM_RST_DET cascade constraints;

drop index IDX_T_XJ_CX_TASK_ITEM_SBSSID;

drop index IDX_XJ_CX_TASK_ITEM_SBSS_CONFT;

drop index IDX_XJ_CX_TASK_ITEM_SBSS_TYPE;

drop index IDX_XJ_CX_TASK_ITEM_SBSS_XJD;

alter table BIZ_T_XJ_CX_TASK_ITEM_SBSS
   drop primary key cascade;

drop table BIZ_T_XJ_CX_TASK_ITEM_SBSS cascade constraints;

drop index IDX_CX_TASK_ITEM_SBSS_RSTICODE;

drop index IDX_CX_TASK_ITEM_SBSS_RST_FK;

alter table BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST
   drop primary key cascade;

drop table BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST cascade constraints;

drop index IDX_T_XJ_CX_TASK_PERSONS_INCHA;

drop index IDX_T_XJ_CX_TASK_PERSONS_PID;

drop index IDX_T_XJ_CX_TASK_PERSONS_TFK;

alter table BIZ_T_XJ_CX_TASK_PERSONS
   drop primary key cascade;

drop table BIZ_T_XJ_CX_TASK_PERSONS cascade constraints;

alter table BIZ_T_XJ_FREQ_SEGMENT
   drop primary key cascade;

drop table BIZ_T_XJ_FREQ_SEGMENT cascade constraints;

drop index IDX_XJ_FREQ_SEGMENT_SBSSRPT;

alter table BIZ_T_XJ_FREQ_SEGMENT_SBSS
   drop primary key cascade;

drop table BIZ_T_XJ_FREQ_SEGMENT_SBSS cascade constraints;

alter table BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ
   drop primary key cascade;

drop table BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ cascade constraints;

alter table BIZ_T_XJ_TECHNICS_SCOPE_MANAGE
   drop primary key cascade;

drop table BIZ_T_XJ_TECHNICS_SCOPE_MANAGE cascade constraints;

alter table BIZ_T_XJ_WORK_GROUP
   drop primary key cascade;

drop table BIZ_T_XJ_WORK_GROUP cascade constraints;

alter table BIZ_T_XJ_WORK_GROUP_DETIAL
   drop primary key cascade;

drop table BIZ_T_XJ_WORK_GROUP_DETIAL cascade constraints;

drop index IDX_T_XJ_ZB_ITEM_CODE;

alter table BIZ_T_XJ_ZB_ITEM
   drop primary key cascade;

drop table BIZ_T_XJ_ZB_ITEM cascade constraints;

drop index IDX_T_XJ_ZB_PLAN_DATE_UNIQ;

alter table BIZ_T_XJ_ZB_PLAN
   drop primary key cascade;

drop table BIZ_T_XJ_ZB_PLAN cascade constraints;

alter table BIZ_T_XJ_ZB_PLAN_DETAIL
   drop primary key cascade;

drop table BIZ_T_XJ_ZB_PLAN_DETAIL cascade constraints;

alter table BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON
   drop primary key cascade;

drop table BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON cascade constraints;

drop index IDX_T_XJ_ZYP_CX_MAKE_WARN;

drop index IDX_T_XJ_ZYP_CX_MAKE_DATE;

drop index IDX_T_XJ_ZYP_CX_MAKE_BEL_WSC;

alter table BIZ_T_XJ_ZYP_CX_MAKE
   drop primary key cascade;

drop table BIZ_T_XJ_ZYP_CX_MAKE cascade constraints;

alter table BIZ_T_XJ_ZYP_CX_MAKE_HIS
   drop primary key cascade;

drop table BIZ_T_XJ_ZYP_CX_MAKE_HIS cascade constraints;

drop index IDX_XJ_ZYP_CX_MAKE_PERSON_KHFK;

alter table BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH
   drop primary key cascade;

drop table BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH cascade constraints;

drop index IDX_T_XJ_ZYP_CX_MAKE_ID_FK;

alter table BIZ_T_XJ_ZYP_CX_MAKE_TMP
   drop primary key cascade;

drop table BIZ_T_XJ_ZYP_CX_MAKE_TMP cascade constraints;

drop index IDX_XJ_ZYP_CX_MAKE_TMP_ID;

alter table BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM
   drop primary key cascade;

drop table BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM cascade constraints;

drop index IDX_T_XJ_ZYP_TEMPLATE_WSC;

alter table BIZ_T_XJ_ZYP_TEMPLATE
   drop primary key cascade;

drop table BIZ_T_XJ_ZYP_TEMPLATE cascade constraints;

alter table BIZ_T_XJ_ZYP_TEMPLATE_ITM
   drop primary key cascade;

drop table BIZ_T_XJ_ZYP_TEMPLATE_ITM cascade constraints;

drop index IDX_XJ_ZYP_TEMPLA_ITM_DETNOREP;

alter table BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET
   drop primary key cascade;

drop table BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET cascade constraints;

drop index INDEX_T_ZB_CJ_SB_UNIQUE;

alter table BIZ_T_ZB_CJ
   drop primary key cascade;

drop table BIZ_T_ZB_CJ cascade constraints;

drop table DES_BASE_TABLE cascade constraints;

alter table SYS_VERSION_MANAGE
   drop primary key cascade;

drop table SYS_VERSION_MANAGE cascade constraints;

/*==============================================================*/
/* Table: BIZ_T_BASE_FACTORY_INFO                               */
/*==============================================================*/
create table BIZ_T_BASE_FACTORY_INFO 
(
   ID                   VARCHAR2(32)         not null,
   NAME                 VARCHAR2(128)        not null,
   TYPE                 VARCHAR2(8),
   ZJXZ                 VARCHAR2(8),
   ADDRESS              VARCHAR2(256),
   RESPONSOBILITY       VARCHAR2(32),
   FACTORY_TEL          VARCHAR2(16),
   CONTACT_PEOPLE       VARCHAR2(32),
   CONTACT_PHONE        VARCHAR2(11),
   CONTACT_OTHERWAY     VARCHAR2(1024),
   CONTACT_MAIL         VARCHAR2(256),
   BUSINESS_SCOPE       VARCHAR2(2048),
   GSDJH                VARCHAR2(16),
   NRSSBH               VARCHAR2(16),
   SHXYDM               VARCHAR2(16),
   OFFICIAL_WEBSITE     VARCHAR2(256),
   LONGITUDE            NUMBER(12,9),
   LATITUDE             NUMBER(12,9),
   REAMRK1              VARCHAR2(4000),
   OUT_SERVICE          integer              default 0,
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table BIZ_T_BASE_FACTORY_INFO is
'管理与渝西生产过程有关的设备供应商或施工厂商';

comment on column BIZ_T_BASE_FACTORY_INFO.ID is
'主键ID';

comment on column BIZ_T_BASE_FACTORY_INFO.NAME is
'厂商名称';

comment on column BIZ_T_BASE_FACTORY_INFO.TYPE is
'厂商类型：施工或设备商
01	设备供应商
02	施工商
';

comment on column BIZ_T_BASE_FACTORY_INFO.ZJXZ is
'厂商性质：厂商的资金性质
01	国企
02	民营
03	中外合资
04	外资';

comment on column BIZ_T_BASE_FACTORY_INFO.ADDRESS is
'厂商地址';

comment on column BIZ_T_BASE_FACTORY_INFO.RESPONSOBILITY is
'厂商法人代表';

comment on column BIZ_T_BASE_FACTORY_INFO.FACTORY_TEL is
'企业联系电话';

comment on column BIZ_T_BASE_FACTORY_INFO.CONTACT_PEOPLE is
'主要联系人';

comment on column BIZ_T_BASE_FACTORY_INFO.CONTACT_PHONE is
'主要联系人手机';

comment on column BIZ_T_BASE_FACTORY_INFO.CONTACT_OTHERWAY is
'其他联系方式';

comment on column BIZ_T_BASE_FACTORY_INFO.CONTACT_MAIL is
'联系人邮箱';

comment on column BIZ_T_BASE_FACTORY_INFO.BUSINESS_SCOPE is
'主要经营范围';

comment on column BIZ_T_BASE_FACTORY_INFO.GSDJH is
'工商登记号码';

comment on column BIZ_T_BASE_FACTORY_INFO.NRSSBH is
'纳税人识别号';

comment on column BIZ_T_BASE_FACTORY_INFO.SHXYDM is
'社会信用代码';

comment on column BIZ_T_BASE_FACTORY_INFO.OFFICIAL_WEBSITE is
'企业官网';

comment on column BIZ_T_BASE_FACTORY_INFO.LONGITUDE is
'X坐标';

comment on column BIZ_T_BASE_FACTORY_INFO.LATITUDE is
'Y坐标';

comment on column BIZ_T_BASE_FACTORY_INFO.REAMRK1 is
'备注说明';

comment on column BIZ_T_BASE_FACTORY_INFO.OUT_SERVICE is
'设置是否停用该供应商，已经停用的供应商不能再作为未来采购的来源：1是 0否';

comment on column BIZ_T_BASE_FACTORY_INFO.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_BASE_FACTORY_INFO.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_BASE_FACTORY_INFO.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_BASE_FACTORY_INFO.UPDATOR_ACCOUNT is
'修改人ID或账号';

comment on column BIZ_T_BASE_FACTORY_INFO.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_BASE_FACTORY_INFO.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_BASE_FACTORY_INFO
   add constraint PK_BIZ_T_BASE_FACTORY_INFO primary key (ID);

/*==============================================================*/
/* Table: BIZ_T_GG_CHECK_ITEM                                   */
/*==============================================================*/
create table BIZ_T_GG_CHECK_ITEM 
(
   CHECK_ITEM_ID        VARCHAR2(32)         not null,
   CHECK_ITEM_CODE      VARCHAR2(32)         not null,
   CHECK_ITEM_NAME      VARCHAR2(128)        not null,
   CHECK_ITEM_TYPE      VARCHAR2(8)          not null,
   INPUT_TYPE           VARCHAR2(8)          not null,
   CHECK_ITEM_DESC      VARCHAR2(1024),
   BYZD                 VARCHAR2(1024),
   DEL_FLAG             integer              default 0 not null,
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table BIZ_T_GG_CHECK_ITEM is
'定义检查项内容，定义的检查项可以通过相关功能与设备与设施进行绑定，这样在巡检设备与设施时就可以获取检查项进行巡检工作执行。
检查项定义的内容所有单位都可以使用';

comment on column BIZ_T_GG_CHECK_ITEM.CHECK_ITEM_ID is
'主键ID';

comment on column BIZ_T_GG_CHECK_ITEM.CHECK_ITEM_CODE is
'检查项CODE';

comment on column BIZ_T_GG_CHECK_ITEM.CHECK_ITEM_NAME is
'检查项名称';

comment on column BIZ_T_GG_CHECK_ITEM.CHECK_ITEM_TYPE is
'检查项类型:
1	设备检查项
2	设施检查项
';

comment on column BIZ_T_GG_CHECK_ITEM.INPUT_TYPE is
'输入类型：文本、数字、单选
0	单选
1	文本
2	数字
';

comment on column BIZ_T_GG_CHECK_ITEM.CHECK_ITEM_DESC is
'检查项说明';

comment on column BIZ_T_GG_CHECK_ITEM.BYZD is
'备用字段';

comment on column BIZ_T_GG_CHECK_ITEM.DEL_FLAG is
'是否删除，1是 0否';

comment on column BIZ_T_GG_CHECK_ITEM.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_GG_CHECK_ITEM.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_GG_CHECK_ITEM.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_GG_CHECK_ITEM.UPDATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_GG_CHECK_ITEM.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_GG_CHECK_ITEM.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_GG_CHECK_ITEM
   add constraint PK_BIZ_T_GG_CHECK_ITEM primary key (CHECK_ITEM_ID);

/*==============================================================*/
/* Table: BIZ_T_GG_CHECK_ITEMF_KXX                              */
/*==============================================================*/
create table BIZ_T_GG_CHECK_ITEMF_KXX 
(
   ITEMF_KXX_ID         VARCHAR2(32)         not null,
   KXX_VALUE            VARCHAR2(128),
   IS_VALID             integer              default 1,
   KXX_DESC             VARCHAR2(2048)
);

comment on table BIZ_T_GG_CHECK_ITEMF_KXX is
'创建检查项时，可从此表及从表查询选项数据，帮助用户快速输入';

comment on column BIZ_T_GG_CHECK_ITEMF_KXX.ITEMF_KXX_ID is
'ID';

comment on column BIZ_T_GG_CHECK_ITEMF_KXX.KXX_VALUE is
'名称';

comment on column BIZ_T_GG_CHECK_ITEMF_KXX.IS_VALID is
'是否有效';

comment on column BIZ_T_GG_CHECK_ITEMF_KXX.KXX_DESC is
'说明';

alter table BIZ_T_GG_CHECK_ITEMF_KXX
   add constraint PK_BIZ_T_GG_CHECK_ITEMF_KXX primary key (ITEMF_KXX_ID);

/*==============================================================*/
/* Table: BIZ_T_GG_CHECK_ITEMF_KXX_SEL                          */
/*==============================================================*/
create table BIZ_T_GG_CHECK_ITEMF_KXX_SEL 
(
   ID                   VARCHAR2(32)         not null,
   ITEMF_KXX_ID         VARCHAR2(32)         not null,
   SEL_VALUE            VARCHAR2(32),
   SEL_NAME             VARCHAR2(128),
   SEL_VALUE2           VARCHAR2(32),
   SEL_VALUE3           VARCHAR2(32),
   SEL_SORT             INTEGER
);

comment on table BIZ_T_GG_CHECK_ITEMF_KXX_SEL is
'存储预定义的检查项的可选项范围';

comment on column BIZ_T_GG_CHECK_ITEMF_KXX_SEL.ID is
'ID';

comment on column BIZ_T_GG_CHECK_ITEMF_KXX_SEL.ITEMF_KXX_ID is
'主表主键';

comment on column BIZ_T_GG_CHECK_ITEMF_KXX_SEL.SEL_VALUE is
'值';

comment on column BIZ_T_GG_CHECK_ITEMF_KXX_SEL.SEL_NAME is
'名称';

comment on column BIZ_T_GG_CHECK_ITEMF_KXX_SEL.SEL_VALUE2 is
'值2';

comment on column BIZ_T_GG_CHECK_ITEMF_KXX_SEL.SEL_VALUE3 is
'值3';

comment on column BIZ_T_GG_CHECK_ITEMF_KXX_SEL.SEL_SORT is
'排序号';

/*==============================================================*/
/* Table: BIZ_T_GG_CHECK_ITEM_DETAIL                            */
/*==============================================================*/
create table BIZ_T_GG_CHECK_ITEM_DETAIL 
(
   DETAIL_ID            VARCHAR2(32)         not null,
   CHECK_ITEM_ID        VARCHAR2(32)         not null,
   SEL_VALUE            VARCHAR2(32),
   SEL_NAME             VARCHAR2(128),
   SEL_SORT             INTEGER,
   SFZC                 VARCHAR2(8),
   SFMR                 integer              default 0
);

comment on table BIZ_T_GG_CHECK_ITEM_DETAIL is
'检查项类型为单选或多选时，此处存储检查项的可选项范围';

comment on column BIZ_T_GG_CHECK_ITEM_DETAIL.DETAIL_ID is
'主键';

comment on column BIZ_T_GG_CHECK_ITEM_DETAIL.CHECK_ITEM_ID is
'主表BIZ_T_GG_CHECK_ITEMID';

comment on column BIZ_T_GG_CHECK_ITEM_DETAIL.SEL_VALUE is
'选项值';

comment on column BIZ_T_GG_CHECK_ITEM_DETAIL.SEL_NAME is
'选项名称';

comment on column BIZ_T_GG_CHECK_ITEM_DETAIL.SEL_SORT is
'排序号';

comment on column BIZ_T_GG_CHECK_ITEM_DETAIL.SFZC is
'该选项被选择时代表是否有问题：1正常，2异常';

comment on column BIZ_T_GG_CHECK_ITEM_DETAIL.SFMR is
'是否默认 1是，0否';

alter table BIZ_T_GG_CHECK_ITEM_DETAIL
   add constraint PK_BIZ_T_GG_CHECK_ITEM_DETAIL primary key (DETAIL_ID);

/*==============================================================*/
/* Table: BIZ_T_GG_MSG_MANAGE                                   */
/*==============================================================*/
create table BIZ_T_GG_MSG_MANAGE 
(
   MSG_ID               VARCHAR2(32)         not null,
   TITLE                VARCHAR2(128),
   CONTENT              VARCHAR2(4000),
   IMPORTANT_LEVEL      VARCHAR2(8),
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   BELONG_WSC_ID        VARCHAR2(32),
   BELONG_WSCN_AME      VARCHAR2(128),
   BELONG_DEPT          VARCHAR2(32),
   RECIVER_ID           VARCHAR2(32)         not null,
   RECIVER_NAME         VARCHAR2(32)         not null,
   IS_HAVE_READ         INTEGER              default 0
);

comment on table BIZ_T_GG_MSG_MANAGE is
'系统内消息发送与接收管理';

comment on column BIZ_T_GG_MSG_MANAGE.MSG_ID is
'主键ID';

comment on column BIZ_T_GG_MSG_MANAGE.TITLE is
'标题';

comment on column BIZ_T_GG_MSG_MANAGE.CONTENT is
'内容';

comment on column BIZ_T_GG_MSG_MANAGE.IMPORTANT_LEVEL is
'备用，重要程度：
1	一般
2	重要';

comment on column BIZ_T_GG_MSG_MANAGE.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_GG_MSG_MANAGE.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_GG_MSG_MANAGE.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_GG_MSG_MANAGE.BELONG_WSC_ID is
'所属厂站ID，非业务数据时非必填';

comment on column BIZ_T_GG_MSG_MANAGE.BELONG_WSCN_AME is
'所属厂站名称，非业务数据时非必填';

comment on column BIZ_T_GG_MSG_MANAGE.BELONG_DEPT is
'所属部门';

comment on column BIZ_T_GG_MSG_MANAGE.RECIVER_ID is
'消息接收人ID';

comment on column BIZ_T_GG_MSG_MANAGE.RECIVER_NAME is
'消息接收人名称';

comment on column BIZ_T_GG_MSG_MANAGE.IS_HAVE_READ is
'是否已读 1是 0否';

alter table BIZ_T_GG_MSG_MANAGE
   add constraint PK_BIZ_T_GG_MSG_MANAGE primary key (MSG_ID);

/*==============================================================*/
/* Index: IDX_T_GG_MSG_MANAGE_WSC                               */
/*==============================================================*/
create index IDX_T_GG_MSG_MANAGE_WSC on BIZ_T_GG_MSG_MANAGE (
   BELONG_WSC_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_GG_MSG_MANAGE_CREATOR                           */
/*==============================================================*/
create index IDX_T_GG_MSG_MANAGE_CREATOR on BIZ_T_GG_MSG_MANAGE (
   CREATOR_ACCOUNT ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_GG_MSG_MANAGE_IMPLEVEL                          */
/*==============================================================*/
create index IDX_T_GG_MSG_MANAGE_IMPLEVEL on BIZ_T_GG_MSG_MANAGE (
   IMPORTANT_LEVEL ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_GG_MSG_MANAGE_CREATIME                          */
/*==============================================================*/
create index IDX_T_GG_MSG_MANAGE_CREATIME on BIZ_T_GG_MSG_MANAGE (
   CREATE_TIMESTEMP ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_GG_NOTICE_MANAGE                                */
/*==============================================================*/
create table BIZ_T_GG_NOTICE_MANAGE 
(
   NOTICE_ID            VARCHAR2(32)         not null,
   TITLE                VARCHAR2(128)        not null,
   CONTENT              CLOB,
   IMPORTANT_LEVEL      VARCHAR2(8),
   GG_TYPE              VARCHAR2(8),
   LIMIT_DATE           VARCHAR2(16)         not null,
   IS_ALIVE             integer              default 1 not null,
   TITLE_ICO            VARCHAR2(32),
   NOTICE_DESC          VARCHAR2(2048),
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   BELONG_DEPT          VARCHAR2(32),
   BELONG_WSC_ID        VARCHAR2(32),
   BELONG_WSC_NAME      VARCHAR2(128),
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table BIZ_T_GG_NOTICE_MANAGE is
'公告发送管理
此处发送的安全公告一般因含有全体人员的含义（虽然可以设置接收人，是为了后期个性化需求），与安全提醒及消息发送有一定差别';

comment on column BIZ_T_GG_NOTICE_MANAGE.NOTICE_ID is
'主键ID';

comment on column BIZ_T_GG_NOTICE_MANAGE.TITLE is
'标题';

comment on column BIZ_T_GG_NOTICE_MANAGE.CONTENT is
'内容';

comment on column BIZ_T_GG_NOTICE_MANAGE.IMPORTANT_LEVEL is
'重要程度：
1	一般
2	重要
';

comment on column BIZ_T_GG_NOTICE_MANAGE.GG_TYPE is
'公告类型：
1	政策宣传
2	教育学习
3	安全提醒
4	其他
';

comment on column BIZ_T_GG_NOTICE_MANAGE.LIMIT_DATE is
'有效期';

comment on column BIZ_T_GG_NOTICE_MANAGE.IS_ALIVE is
'是否有效 1是 0否';

comment on column BIZ_T_GG_NOTICE_MANAGE.TITLE_ICO is
'备用：标题图标，附件ID';

comment on column BIZ_T_GG_NOTICE_MANAGE.NOTICE_DESC is
'备注';

comment on column BIZ_T_GG_NOTICE_MANAGE.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_GG_NOTICE_MANAGE.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_GG_NOTICE_MANAGE.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_GG_NOTICE_MANAGE.BELONG_DEPT is
'所属部门';

comment on column BIZ_T_GG_NOTICE_MANAGE.BELONG_WSC_ID is
'所属单位ID，非业务数据时非必填';

comment on column BIZ_T_GG_NOTICE_MANAGE.BELONG_WSC_NAME is
'所属单位名称，非业务数据时非必填';

comment on column BIZ_T_GG_NOTICE_MANAGE.UPDATOR_ACCOUNT is
'修改人ID或账号';

comment on column BIZ_T_GG_NOTICE_MANAGE.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_GG_NOTICE_MANAGE.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_GG_NOTICE_MANAGE
   add constraint PK_BIZ_T_GG_NOTICE_MANAGE primary key (NOTICE_ID);

/*==============================================================*/
/* Index: IDX_T_GG_NOTICE_MANAGE_TYPE                           */
/*==============================================================*/
create bitmap index IDX_T_GG_NOTICE_MANAGE_TYPE on BIZ_T_GG_NOTICE_MANAGE (
   GG_TYPE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_GG_NOTICE_MANAGE_ALIVE                          */
/*==============================================================*/
create bitmap index IDX_T_GG_NOTICE_MANAGE_ALIVE on BIZ_T_GG_NOTICE_MANAGE (
   IS_ALIVE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_GG_NOTICE_MANAGE_LIM                            */
/*==============================================================*/
create index IDX_T_GG_NOTICE_MANAGE_LIM on BIZ_T_GG_NOTICE_MANAGE (
   LIMIT_DATE DESC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_GG_NOTICE_MANAGE_CT                             */
/*==============================================================*/
create index IDX_T_GG_NOTICE_MANAGE_CT on BIZ_T_GG_NOTICE_MANAGE (
   CREATE_TIMESTEMP DESC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_GG_NOTICE_MANAGE_BEL_WSC                        */
/*==============================================================*/
create bitmap index IDX_T_GG_NOTICE_MANAGE_BEL_WSC on BIZ_T_GG_NOTICE_MANAGE (
   BELONG_WSC_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_GG_SBSS_ATTACH_BASE                             */
/*==============================================================*/
create table BIZ_T_GG_SBSS_ATTACH_BASE 
(
   ATTACH_BASE_ID       VARCHAR2(32)         not null,
   CODE                 VARCHAR2(32)         not null,
   CONF_DESC            VARCHAR2(128),
   BYZD                 VARCHAR2(2048),
   SB_OR_SS             VARCHAR2(8)          not null,
   CONF_TYPE            VARCHAR2(8)          not null,
   DETAIL_ID            VARCHAR2(32)         not null,
   NAME                 VARCHAR2(128),
   SORT_NUM             INTEGER,
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   BELONG_WSC_ID        VARCHAR2(32),
   BELONG_WSC_NAME      VARCHAR2(128),
   BELONG_DEPT          VARCHAR2(32),
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.ATTACH_BASE_ID is
'主键ID';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.CODE is
'设备或设施ID,根据SB_OR_SS字段判断';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.CONF_DESC is
'配置说明';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.BYZD is
'备用';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.SB_OR_SS is
'代表此项是为设备或设施做配置：1设备，2设施，3巡检点';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.CONF_TYPE is
'配置类型：1检查项配置，2安全定义配置';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.DETAIL_ID is
'配置项ID,根据CONF_TYPE字段确定
检查项定义表BIZ_T_GG_CHECK_ITEM的CODE
安全提醒定义表BIZ_T_GG_WARNING_MANAGE的ID';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.NAME is
'冗余字段，配置项名称或标题';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.SORT_NUM is
'排序号';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.BELONG_WSC_ID is
'所属厂站ID，非业务数据时非必填';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.BELONG_WSC_NAME is
'所属厂站名称，非业务数据时非必填';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.BELONG_DEPT is
'所属部门';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.UPDATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_GG_SBSS_ATTACH_BASE.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_GG_SBSS_ATTACH_BASE
   add constraint PK_BIZ_T_GG_SBSS_ATTACH_BASE primary key (ATTACH_BASE_ID);

/*==============================================================*/
/* Index: IDX_T_GG_SBSS_ATTACH_BASE_CODE                        */
/*==============================================================*/
create index IDX_T_GG_SBSS_ATTACH_BASE_CODE on BIZ_T_GG_SBSS_ATTACH_BASE (
   CODE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_GG_SBSS_ATTACH_BASE_TYPE                        */
/*==============================================================*/
create bitmap index IDX_T_GG_SBSS_ATTACH_BASE_TYPE on BIZ_T_GG_SBSS_ATTACH_BASE (
   SB_OR_SS ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_GG_SBSS_ATTACH_BASE_WSC                         */
/*==============================================================*/
create bitmap index IDX_T_GG_SBSS_ATTACH_BASE_WSC on BIZ_T_GG_SBSS_ATTACH_BASE (
   BELONG_WSC_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_GG_SBSS_ATTACH_NOT_REAPT                        */
/*==============================================================*/
create unique index IDX_T_GG_SBSS_ATTACH_NOT_REAPT on BIZ_T_GG_SBSS_ATTACH_BASE (
   CODE ASC,
   DETAIL_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_GG_WARNING_MANAGE                               */
/*==============================================================*/
create table BIZ_T_GG_WARNING_MANAGE 
(
   WARNING_ID           VARCHAR2(32)         not null,
   TITLE                VARCHAR2(128)        not null,
   CONTENT              CLOB                 not null,
   TITLE_ICO            VARCHAR2(32),
   TX_TYPE              VARCHAR2(8),
   IS_HAVE_READ         integer              default 0,
   IS_IMPORTANT         integer              default 0,
   DEL_FLAG             integer              default 0 not null,
   BYZD                 VARCHAR2(1024),
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   BELONG_WSC_ID        VARCHAR2(32),
   BELONG_WSC_NAME      VARCHAR2(128),
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table BIZ_T_GG_WARNING_MANAGE is
'每个厂站维护自己的安全提醒信息';

comment on column BIZ_T_GG_WARNING_MANAGE.WARNING_ID is
'主键ID';

comment on column BIZ_T_GG_WARNING_MANAGE.TITLE is
'标题';

comment on column BIZ_T_GG_WARNING_MANAGE.CONTENT is
'内容';

comment on column BIZ_T_GG_WARNING_MANAGE.TITLE_ICO is
'备用：标题图标，附件ID，只能有一个';

comment on column BIZ_T_GG_WARNING_MANAGE.TX_TYPE is
'提醒类型:
1	设备安全
2	设施安全
3              巡检点安全
';

comment on column BIZ_T_GG_WARNING_MANAGE.IS_HAVE_READ is
'备用：是否要求必读，1是 0否';

comment on column BIZ_T_GG_WARNING_MANAGE.IS_IMPORTANT is
'是否重要提醒，[重要提醒可标星]，1是 0否';

comment on column BIZ_T_GG_WARNING_MANAGE.DEL_FLAG is
'是否删除，1是 0否';

comment on column BIZ_T_GG_WARNING_MANAGE.BYZD is
'备用字段';

comment on column BIZ_T_GG_WARNING_MANAGE.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_GG_WARNING_MANAGE.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_GG_WARNING_MANAGE.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_GG_WARNING_MANAGE.BELONG_WSC_ID is
'所属厂站ID，非业务数据时非必填';

comment on column BIZ_T_GG_WARNING_MANAGE.BELONG_WSC_NAME is
'所属厂站名称，非业务数据时非必填';

comment on column BIZ_T_GG_WARNING_MANAGE.UPDATOR_ACCOUNT is
'修改人ID或账号';

comment on column BIZ_T_GG_WARNING_MANAGE.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_GG_WARNING_MANAGE.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_GG_WARNING_MANAGE
   add constraint PK_BIZ_T_GG_WARNING_MANAGE primary key (WARNING_ID);

/*==============================================================*/
/* Table: BIZ_T_SBSS_RELATION                                   */
/*==============================================================*/
create table BIZ_T_SBSS_RELATION 
(
   SS_SB_ID             VARCHAR2(32)         not null,
   SS_ID                VARCHAR2(128)        not null,
   SB_ID                VARCHAR2(128)        not null
);

comment on table BIZ_T_SBSS_RELATION is
'设施下可以挂接设施，设施下可以挂接设备
每个厂站有单独的数据';

comment on column BIZ_T_SBSS_RELATION.SS_SB_ID is
'主键ID';

comment on column BIZ_T_SBSS_RELATION.SS_ID is
'设施ID';

comment on column BIZ_T_SBSS_RELATION.SB_ID is
'设备ID';

alter table BIZ_T_SBSS_RELATION
   add constraint PK_BIZ_T_SBSS_RELATION primary key (SS_SB_ID);

/*==============================================================*/
/* Table: BIZ_T_SB_BASEINFO                                     */
/*==============================================================*/
create table BIZ_T_SB_BASEINFO 
(
   SB_ID                VARCHAR2(32)         not null,
   SB_CODE              VARCHAR2(32)         not null,
   SB_FN_CODE           VARCHAR2(32),
   SB_NAME              VARCHAR2(128)        not null,
   SREMARK              VARCHAR2(128),
   SB_SORT              VARCHAR2(8),
   SB_TYPE_ID           VARCHAR2(32),
   GCJK                 VARCHAR2(8),
   SBXH                 VARCHAR2(128),
   XNCS                 VARCHAR2(128),
   JGYL                 VARCHAR2(128),
   MANUFACTURE_ID       VARCHAR2(128),
   SUPPLY_ID            VARCHAR2(128),
   IS_ZXCJY             VARCHAR2(8),
   ZY_STATUS            VARCHAR2(8),
   START_VALUE          NUMBER(8,2),
   ZJPG_SJ              VARCHAR2(14),
   ZJPG_VALUE           NUMBER(8,2),
   REMARK               VARCHAR2(2048),
   CAN_USE_YEAR         INTEGER,
   HAVE_LOST_YEAR       INTEGER,
   LONGITUDE            NUMBER(12,9),
   LATITUDE             NUMBER(12,9),
   BUY_DATE             VARCHAR2(16),
   START_USE_DATE       VARCHAR2(16),
   SET_ADDRESS          VARCHAR2(256),
   BYZD                 VARCHAR2(1024),
   DEL_FLAG             VARCHAR2(8),
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   BELONG_WSC_ID        VARCHAR2(32),
   BELONG_WSC_NAME      VARCHAR2(128),
   BELONG_DEPT          VARCHAR2(32),
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table BIZ_T_SB_BASEINFO is
'设备资产信息管理';

comment on column BIZ_T_SB_BASEINFO.SB_CODE is
'资产编号';

comment on column BIZ_T_SB_BASEINFO.SB_FN_CODE is
'资产财务编号';

comment on column BIZ_T_SB_BASEINFO.SB_NAME is
'设备名称';

comment on column BIZ_T_SB_BASEINFO.SREMARK is
'简称：备用';

comment on column BIZ_T_SB_BASEINFO.SB_SORT is
'ABCDEF类
A	A
B	B
C	C
D	D
E	E
F	F';

comment on column BIZ_T_SB_BASEINFO.SB_TYPE_ID is
'设备类型分类 见设备累类型表';

comment on column BIZ_T_SB_BASEINFO.GCJK is
'GC	国产
HZ	合资
JK	进口
YZ	外资
QT	其他';

comment on column BIZ_T_SB_BASEINFO.SBXH is
'设备型号';

comment on column BIZ_T_SB_BASEINFO.XNCS is
'性能参数';

comment on column BIZ_T_SB_BASEINFO.JGYL is
'结构原理';

comment on column BIZ_T_SB_BASEINFO.MANUFACTURE_ID is
'设备制造商 BIZ_T_BASE_FACTORY_INFO表';

comment on column BIZ_T_SB_BASEINFO.SUPPLY_ID is
'备用：设备供应商 BIZ_T_BASE_FACTORY_INFO表';

comment on column BIZ_T_SB_BASEINFO.IS_ZXCJY is
'是、否
0	否
1	是';

comment on column BIZ_T_SB_BASEINFO.ZY_STATUS is
'新购在库、在用、更换闲置在库、报废在库、报废已处理
01	新购
02	在用
03	更换闲置
04	报废
';

comment on column BIZ_T_SB_BASEINFO.START_VALUE is
'设备原价值';

comment on column BIZ_T_SB_BASEINFO.ZJPG_SJ is
'最近评估时间';

comment on column BIZ_T_SB_BASEINFO.ZJPG_VALUE is
'最近评估现值 元';

comment on column BIZ_T_SB_BASEINFO.REMARK is
'备注';

comment on column BIZ_T_SB_BASEINFO.CAN_USE_YEAR is
'最低使用年限';

comment on column BIZ_T_SB_BASEINFO.HAVE_LOST_YEAR is
'已折旧年限';

comment on column BIZ_T_SB_BASEINFO.LONGITUDE is
'X_坐标';

comment on column BIZ_T_SB_BASEINFO.LATITUDE is
'Y_坐标';

comment on column BIZ_T_SB_BASEINFO.BUY_DATE is
'购入日期';

comment on column BIZ_T_SB_BASEINFO.START_USE_DATE is
'开始使用日期';

comment on column BIZ_T_SB_BASEINFO.SET_ADDRESS is
'安装位置';

comment on column BIZ_T_SB_BASEINFO.BYZD is
'是否必须扫描二维码 1是 0否';

comment on column BIZ_T_SB_BASEINFO.DEL_FLAG is
'是否删除，1是 0否';

comment on column BIZ_T_SB_BASEINFO.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_SB_BASEINFO.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_SB_BASEINFO.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_SB_BASEINFO.BELONG_WSC_ID is
'所属水厂ID，非业务数据时非必填';

comment on column BIZ_T_SB_BASEINFO.BELONG_WSC_NAME is
'所属水厂名称，非业务数据时非必填';

comment on column BIZ_T_SB_BASEINFO.BELONG_DEPT is
'所属部门';

comment on column BIZ_T_SB_BASEINFO.UPDATOR_ACCOUNT is
'修改人ID或账号';

comment on column BIZ_T_SB_BASEINFO.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_SB_BASEINFO.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_SB_BASEINFO
   add constraint PK_BIZ_T_SB_BASEINFO primary key (SB_ID);

/*==============================================================*/
/* Index: IDX_T_SB_BASEINFO_CODE                                */
/*==============================================================*/
create unique index IDX_T_SB_BASEINFO_CODE on BIZ_T_SB_BASEINFO (
   SB_CODE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_SB_BASEINFO_SB_GCJK                             */
/*==============================================================*/
create bitmap index IDX_T_SB_BASEINFO_SB_GCJK on BIZ_T_SB_BASEINFO (
   GCJK ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_SB_BASEINFO_SB_MANUF                            */
/*==============================================================*/
create bitmap index IDX_T_SB_BASEINFO_SB_MANUF on BIZ_T_SB_BASEINFO (
   MANUFACTURE_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_SB_BASEINFO_SB_TYPE                             */
/*==============================================================*/
create bitmap index IDX_T_SB_BASEINFO_SB_TYPE on BIZ_T_SB_BASEINFO (
   SB_TYPE_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_SB_BASEINFO_SB_SORT                             */
/*==============================================================*/
create bitmap index IDX_T_SB_BASEINFO_SB_SORT on BIZ_T_SB_BASEINFO (
   SB_SORT ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_SB_BASEINFO_SB_BEL_WSC                          */
/*==============================================================*/
create bitmap index IDX_T_SB_BASEINFO_SB_BEL_WSC on BIZ_T_SB_BASEINFO (
   BELONG_WSC_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_SB_BASEINFO_HISTORY                             */
/*==============================================================*/
create table BIZ_T_SB_BASEINFO_HISTORY 
(
   ID                   VARCHAR2(32)         not null,
   SB_CODE              VARCHAR2(32)         not null,
   SB_FN_CODE           VARCHAR2(32),
   SB_NAME              VARCHAR2(128)        not null,
   SREMARK              VARCHAR2(128),
   SB_SORT              VARCHAR2(8),
   SB_TYPE              VARCHAR2(32),
   GCJK                 VARCHAR2(8),
   SBXH                 VARCHAR2(128),
   XNCS                 VARCHAR2(128),
   JGYL                 VARCHAR2(128),
   MANUFACTURE_ID       VARCHAR2(128),
   SUPPLY_ID            VARCHAR2(128),
   IS_ZXCJY             VARCHAR2(8),
   ZY_STATUS            VARCHAR2(8),
   START_VALUE          NUMBER(8,2),
   ZJPG_SJ              VARCHAR2(14),
   ZJPG_VALUE           NUMBER(8,2),
   REMARK               VARCHAR2(2048),
   CAN_USE_YEAR         INTEGER,
   HAVE_LOST_YEAR       INTEGER,
   LONGITUDE            NUMBER(12,9),
   LATITUDE             NUMBER(12,9),
   BUY_DATE             VARCHAR2(16),
   START_USE_DATE       VARCHAR2(16),
   SET_ADDRESS          VARCHAR2(256),
   BYZD                 VARCHAR2(1024),
   DEL_FLAG             VARCHAR2(8),
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   BELONG_WSC_ID        VARCHAR2(32),
   BELONG_WSC_NAME      VARCHAR2(128),
   BELONG_DEPT          VARCHAR2(32),
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table BIZ_T_SB_BASEINFO_HISTORY is
'设备资产信息管理';

comment on column BIZ_T_SB_BASEINFO_HISTORY.SB_CODE is
'资产编号';

comment on column BIZ_T_SB_BASEINFO_HISTORY.SB_FN_CODE is
'资产财务编号';

comment on column BIZ_T_SB_BASEINFO_HISTORY.SB_NAME is
'设备名称';

comment on column BIZ_T_SB_BASEINFO_HISTORY.SREMARK is
'简注：备用';

comment on column BIZ_T_SB_BASEINFO_HISTORY.SB_SORT is
'ABCDEF类
A	A
B	B
C	C
D	D
E	E
F	F';

comment on column BIZ_T_SB_BASEINFO_HISTORY.SB_TYPE is
'设备类型分类 见设备累类型表';

comment on column BIZ_T_SB_BASEINFO_HISTORY.GCJK is
'GC	国产
HZ	合资
JK	进口
YZ	外资
QT	其他';

comment on column BIZ_T_SB_BASEINFO_HISTORY.SBXH is
'设备型号';

comment on column BIZ_T_SB_BASEINFO_HISTORY.XNCS is
'性能参数';

comment on column BIZ_T_SB_BASEINFO_HISTORY.JGYL is
'结构原理';

comment on column BIZ_T_SB_BASEINFO_HISTORY.MANUFACTURE_ID is
'设备制造商 BIZ_T_BASE_FACTORY_INFO表';

comment on column BIZ_T_SB_BASEINFO_HISTORY.SUPPLY_ID is
'设备供应商 BIZ_T_BASE_FACTORY_INFO表';

comment on column BIZ_T_SB_BASEINFO_HISTORY.IS_ZXCJY is
'是、否
0	否
1	是';

comment on column BIZ_T_SB_BASEINFO_HISTORY.ZY_STATUS is
'新购在库、在用、更换闲置在库、报废在库、报废已处理
01	新购
02	在用
03	更换闲置
04	报废
';

comment on column BIZ_T_SB_BASEINFO_HISTORY.START_VALUE is
'设备原价值';

comment on column BIZ_T_SB_BASEINFO_HISTORY.ZJPG_SJ is
'最近评估时间';

comment on column BIZ_T_SB_BASEINFO_HISTORY.ZJPG_VALUE is
'最近评估现值 元';

comment on column BIZ_T_SB_BASEINFO_HISTORY.REMARK is
'备注';

comment on column BIZ_T_SB_BASEINFO_HISTORY.CAN_USE_YEAR is
'最低使用年限';

comment on column BIZ_T_SB_BASEINFO_HISTORY.HAVE_LOST_YEAR is
'已折旧年限';

comment on column BIZ_T_SB_BASEINFO_HISTORY.LONGITUDE is
'X_坐标';

comment on column BIZ_T_SB_BASEINFO_HISTORY.LATITUDE is
'Y_坐标';

comment on column BIZ_T_SB_BASEINFO_HISTORY.BUY_DATE is
'购入日期';

comment on column BIZ_T_SB_BASEINFO_HISTORY.START_USE_DATE is
'开始使用日期';

comment on column BIZ_T_SB_BASEINFO_HISTORY.SET_ADDRESS is
'安装位置';

comment on column BIZ_T_SB_BASEINFO_HISTORY.BYZD is
'是否必须扫描二维码 1是 0否';

comment on column BIZ_T_SB_BASEINFO_HISTORY.DEL_FLAG is
'是否删除，1是 0否';

comment on column BIZ_T_SB_BASEINFO_HISTORY.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_SB_BASEINFO_HISTORY.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_SB_BASEINFO_HISTORY.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_SB_BASEINFO_HISTORY.BELONG_WSC_ID is
'所属水厂ID，非业务数据时非必填';

comment on column BIZ_T_SB_BASEINFO_HISTORY.BELONG_WSC_NAME is
'所属水厂名称，非业务数据时非必填';

comment on column BIZ_T_SB_BASEINFO_HISTORY.BELONG_DEPT is
'所属部门';

comment on column BIZ_T_SB_BASEINFO_HISTORY.UPDATOR_ACCOUNT is
'修改人ID或账号';

comment on column BIZ_T_SB_BASEINFO_HISTORY.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_SB_BASEINFO_HISTORY.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_SB_BASEINFO_HISTORY
   add constraint PK_BIZ_T_SB_BASEINFO_HISTORY primary key (ID);

/*==============================================================*/
/* Table: BIZ_T_SB_TYPES                                        */
/*==============================================================*/
create table BIZ_T_SB_TYPES 
(
   SB_TYPE_ID           VARCHAR2(32)         not null,
   CODE                 VARCHAR2(32)         not null,
   PARENT_TYPE_ID       VARCHAR2(32),
   NAME                 VARCHAR2(128)        not null,
   ORDERS               INTEGER,
   LAYER                VARCHAR2(256)        not null,
   UNIT                 VARCHAR2(8),
   REMARK               VARCHAR2(1024),
   OUT_SERVICE          integer              default 0,
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table BIZ_T_SB_TYPES is
'管理设备类型信息及层级信息';

comment on column BIZ_T_SB_TYPES.SB_TYPE_ID is
'主键ID';

comment on column BIZ_T_SB_TYPES.CODE is
'类型编码';

comment on column BIZ_T_SB_TYPES.PARENT_TYPE_ID is
'父类型ID，如果是顶级为NULL';

comment on column BIZ_T_SB_TYPES.NAME is
'类型名称';

comment on column BIZ_T_SB_TYPES.ORDERS is
'排序号';

comment on column BIZ_T_SB_TYPES.LAYER is
'层级关系';

comment on column BIZ_T_SB_TYPES.UNIT is
'计量单位';

comment on column BIZ_T_SB_TYPES.REMARK is
'类型说明';

comment on column BIZ_T_SB_TYPES.OUT_SERVICE is
'是否停用该类型，停用后不可再被设备选择该类型，1是 0否';

comment on column BIZ_T_SB_TYPES.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_SB_TYPES.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_SB_TYPES.UPDATOR_ACCOUNT is
'修改人ID或账号';

comment on column BIZ_T_SB_TYPES.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_SB_TYPES.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_SB_TYPES
   add constraint PK_BIZ_T_SB_TYPES primary key (SB_TYPE_ID);

/*==============================================================*/
/* Table: BIZ_T_SS_BASEINFO                                     */
/*==============================================================*/
create table BIZ_T_SS_BASEINFO 
(
   SS_ID                VARCHAR2(32)         not null,
   CODE                 VARCHAR2(32)         not null,
   NAME                 VARCHAR2(128)        not null,
   FUNCTION             VARCHAR2(2048),
   PARENT_ID            VARCHAR2(32),
   LAYER                VARCHAR2(256),
   SORT                 INTEGER,
   BYZD                 VARCHAR2(1024),
   REMARK               VARCHAR2(2048),
   LONGITUDE            NUMBER(12,9),
   LATITUDE             NUMBER(12,9),
   DEL_FLAG             integer              default 0,
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   BELONG_WSC_ID        VARCHAR2(32),
   BELONG_WSC_NAME      VARCHAR2(128),
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table BIZ_T_SS_BASEINFO is
'管理厂内设施信息
每个厂站有单独的数据';

comment on column BIZ_T_SS_BASEINFO.SS_ID is
'主键ID';

comment on column BIZ_T_SS_BASEINFO.CODE is
'设施编号';

comment on column BIZ_T_SS_BASEINFO.NAME is
'设施名称';

comment on column BIZ_T_SS_BASEINFO.FUNCTION is
'设施功能说明';

comment on column BIZ_T_SS_BASEINFO.PARENT_ID is
'上级设施ID';

comment on column BIZ_T_SS_BASEINFO.LAYER is
'层级关系';

comment on column BIZ_T_SS_BASEINFO.SORT is
'排序号';

comment on column BIZ_T_SS_BASEINFO.BYZD is
'是否必须扫描二维码 1是 0否';

comment on column BIZ_T_SS_BASEINFO.REMARK is
'备注、预留字段';

comment on column BIZ_T_SS_BASEINFO.LONGITUDE is
'X_坐标';

comment on column BIZ_T_SS_BASEINFO.LATITUDE is
'Y_坐标';

comment on column BIZ_T_SS_BASEINFO.DEL_FLAG is
'是否删除，1是 0否,下属有设施或设备时不可被删除';

comment on column BIZ_T_SS_BASEINFO.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_SS_BASEINFO.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_SS_BASEINFO.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_SS_BASEINFO.BELONG_WSC_ID is
'所属厂站ID，非业务数据时非必填';

comment on column BIZ_T_SS_BASEINFO.BELONG_WSC_NAME is
'所属厂站名称，非业务数据时非必填';

comment on column BIZ_T_SS_BASEINFO.UPDATOR_ACCOUNT is
'修改人ID或账号';

comment on column BIZ_T_SS_BASEINFO.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_SS_BASEINFO.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_SS_BASEINFO
   add constraint PK_BIZ_T_SS_BASEINFO primary key (SS_ID);

/*==============================================================*/
/* Index: IDX_T_SS_BASEINFO_BEL_WSC                             */
/*==============================================================*/
create bitmap index IDX_T_SS_BASEINFO_BEL_WSC on BIZ_T_SS_BASEINFO (
   BELONG_WSC_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_SS_BASEINFO_CODE                                */
/*==============================================================*/
create unique index IDX_T_SS_BASEINFO_CODE on BIZ_T_SS_BASEINFO (
   CODE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_SS_BASEINFO_PID                                 */
/*==============================================================*/
create bitmap index IDX_T_SS_BASEINFO_PID on BIZ_T_SS_BASEINFO (
   PARENT_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_SS_BASEINFO_LAYER                               */
/*==============================================================*/
create index IDX_T_SS_BASEINFO_LAYER on BIZ_T_SS_BASEINFO (
   LAYER ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJD_ITEM                                        */
/*==============================================================*/
create table BIZ_T_XJD_ITEM 
(
   XJD_ITEM_ID          VARCHAR2(32)         not null,
   XJD_ITEM_NAME        VARCHAR2(128),
   XJD_ITEM_ADDRESS     VARCHAR2(256),
   XJD_ITEM_DESC        VARCHAR2(128),
   BYZD                 VARCHAR2(1024),
   RFID_CODE            VARCHAR2(32),
   DEL_FLAG             integer              default 0,
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   BELONG_WSC_ID        VARCHAR2(32),
   BELONG_WSC_NAME      VARCHAR2(128),
   BELONG_DEPT          VARCHAR2(32),
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table BIZ_T_XJD_ITEM is
'巡检点定义管理表';

comment on column BIZ_T_XJD_ITEM.XJD_ITEM_ID is
'主键';

comment on column BIZ_T_XJD_ITEM.XJD_ITEM_NAME is
'巡检点名称';

comment on column BIZ_T_XJD_ITEM.XJD_ITEM_ADDRESS is
'巡检点位置';

comment on column BIZ_T_XJD_ITEM.XJD_ITEM_DESC is
'巡检点说明';

comment on column BIZ_T_XJD_ITEM.BYZD is
'备用字段';

comment on column BIZ_T_XJD_ITEM.RFID_CODE is
'RFID编号';

comment on column BIZ_T_XJD_ITEM.DEL_FLAG is
'是否删除，1是 0否';

comment on column BIZ_T_XJD_ITEM.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_XJD_ITEM.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJD_ITEM.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_XJD_ITEM.BELONG_WSC_ID is
'所属厂站ID，非业务数据时非必填';

comment on column BIZ_T_XJD_ITEM.BELONG_WSC_NAME is
'所属厂站名称，非业务数据时非必填';

comment on column BIZ_T_XJD_ITEM.BELONG_DEPT is
'所属部门';

comment on column BIZ_T_XJD_ITEM.UPDATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJD_ITEM.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_XJD_ITEM.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_XJD_ITEM
   add constraint PK_BIZ_T_XJD_ITEM primary key (XJD_ITEM_ID);

/*==============================================================*/
/* Table: BIZ_T_XJD_ITEM_DETAIL                                 */
/*==============================================================*/
create table BIZ_T_XJD_ITEM_DETAIL 
(
   DETAIL_ID            VARCHAR2(32)         not null,
   XJD_ITEM_ID          VARCHAR2(32)         not null,
   SBSS_ID              VARCHAR2(32)         not null,
   NAME                 VARCHAR2(128),
   DETAIL_TYPE          VARCHAR2(8)          not null,
   BYZD                 VARCHAR2(1024)
);

comment on table BIZ_T_XJD_ITEM_DETAIL is
'考虑：设备或设施删除时，及设备为非‘在用’状态时，本表也应该删除';

comment on column BIZ_T_XJD_ITEM_DETAIL.DETAIL_ID is
'主键';

comment on column BIZ_T_XJD_ITEM_DETAIL.XJD_ITEM_ID is
'巡检点主键';

comment on column BIZ_T_XJD_ITEM_DETAIL.SBSS_ID is
'设备或设施ID';

comment on column BIZ_T_XJD_ITEM_DETAIL.NAME is
'设备或设施名称';

comment on column BIZ_T_XJD_ITEM_DETAIL.DETAIL_TYPE is
'类型 1设备，2设施';

comment on column BIZ_T_XJD_ITEM_DETAIL.BYZD is
'备用字段';

alter table BIZ_T_XJD_ITEM_DETAIL
   add constraint PK_BIZ_T_XJD_ITEM_DETAIL primary key (DETAIL_ID);

/*==============================================================*/
/* Index: IDX_XJD_ITEM_DETAIL_ITEMIDFK                          */
/*==============================================================*/
create index IDX_XJD_ITEM_DETAIL_ITEMIDFK on BIZ_T_XJD_ITEM_DETAIL (
   XJD_ITEM_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_XJD_ITEM_DETAIL_SBSSCODE                          */
/*==============================================================*/
create index IDX_XJD_ITEM_DETAIL_SBSSCODE on BIZ_T_XJD_ITEM_DETAIL (
   SBSS_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_XJD_ITEM_DETAIL_SBSSTYPE                          */
/*==============================================================*/
create bitmap index IDX_XJD_ITEM_DETAIL_SBSSTYPE on BIZ_T_XJD_ITEM_DETAIL (
   DETAIL_TYPE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJ_CX_TASK                                      */
/*==============================================================*/
create table BIZ_T_XJ_CX_TASK 
(
   CX_TASK_ID           VARCHAR2(32)         not null,
   CX_TASK_DATE         VARCHAR2(14),
   CX_TASK_CODE         VARCHAR2(32),
   CX_TASK_NAME         VARCHAR2(128),
   CX_TASK_DESC         VARCHAR2(4000),
   CX_TASK_PSTART_TIME  VARCHAR2(14),
   CX_TASK_PEND_TIME    VARCHAR2(14),
   CX_TASK_ASTART_TIME  VARCHAR2(14),
   CX_TASK_AEND_TIME    VARCHAR2(14),
   CX_TASK_TYPE         VARCHAR2(8),
   CX_TASK_STATUS       VARCHAR2(8),
   IS_OVER_TIME         integer              default 0,
   CX_TASK_CAN_DELIV    integer              default 0,
   CX_TASK_HAVE_DELIV   integer              default 0,
   CX_TASK_GEN_TIME     VARCHAR2(14),
   BELONG_WSC_ID        VARCHAR2(32)         not null,
   BELONG_WSC_NAME      VARCHAR2(128),
   BYZD                 VARCHAR2(1024),
   SF_ZC_OVER_TIME      VARCHAR2(8),
   OVER_TIME_DESC       VARCHAR2(2048)
);

comment on table BIZ_T_XJ_CX_TASK is
'系统自动生成巡检任务的主体信息';

comment on column BIZ_T_XJ_CX_TASK.CX_TASK_ID is
'主键ID';

comment on column BIZ_T_XJ_CX_TASK.CX_TASK_DATE is
'任务所属日期,该日期是标明属于哪一天的厂巡范围 比如9号的作业票到10号8点都会有厂巡，任务都算是9号的 yyyyMMdd格式';

comment on column BIZ_T_XJ_CX_TASK.CX_TASK_CODE is
'任务编号';

comment on column BIZ_T_XJ_CX_TASK.CX_TASK_NAME is
'任务名称';

comment on column BIZ_T_XJ_CX_TASK.CX_TASK_DESC is
'任务概述';

comment on column BIZ_T_XJ_CX_TASK.CX_TASK_PSTART_TIME is
'计划开始时间';

comment on column BIZ_T_XJ_CX_TASK.CX_TASK_PEND_TIME is
'计划结束时间';

comment on column BIZ_T_XJ_CX_TASK.CX_TASK_ASTART_TIME is
'实际开始时间';

comment on column BIZ_T_XJ_CX_TASK.CX_TASK_AEND_TIME is
'实际完成时间';

comment on column BIZ_T_XJ_CX_TASK.CX_TASK_TYPE is
'任务方式:1系统下发2主动处理';

comment on column BIZ_T_XJ_CX_TASK.CX_TASK_STATUS is
'任务状态：
0 待下发
1 已下发
2 在执行
3 已完成
';

comment on column BIZ_T_XJ_CX_TASK.IS_OVER_TIME is
'是否超期,任务超时完成时修改该字段 1是0否';

comment on column BIZ_T_XJ_CX_TASK.CX_TASK_CAN_DELIV is
'是否可以被转交 1 是 0否
当任务的责任人多于1个时，任务将不可被转交';

comment on column BIZ_T_XJ_CX_TASK.CX_TASK_HAVE_DELIV is
'是否被转交 1 是 0否 ';

comment on column BIZ_T_XJ_CX_TASK.CX_TASK_GEN_TIME is
'任务生成时间';

comment on column BIZ_T_XJ_CX_TASK.BELONG_WSC_ID is
'所属厂站ID，非业务数据时非必填';

comment on column BIZ_T_XJ_CX_TASK.BELONG_WSC_NAME is
'所属厂站名称，非业务数据时非必填';

comment on column BIZ_T_XJ_CX_TASK.BYZD is
'备用字段';

comment on column BIZ_T_XJ_CX_TASK.SF_ZC_OVER_TIME is
'是否正常超期 1 是 0否 ';

comment on column BIZ_T_XJ_CX_TASK.OVER_TIME_DESC is
'超期说明';

alter table BIZ_T_XJ_CX_TASK
   add constraint PK_BIZ_T_XJ_CX_TASK primary key (CX_TASK_ID);

/*==============================================================*/
/* Index: IDX_T_XJ_CX_TASK_DATE                                 */
/*==============================================================*/
create index IDX_T_XJ_CX_TASK_DATE on BIZ_T_XJ_CX_TASK (
   CX_TASK_DATE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_XJ_CX_TASK_CODE                                 */
/*==============================================================*/
create unique index IDX_T_XJ_CX_TASK_CODE on BIZ_T_XJ_CX_TASK (
   CX_TASK_CODE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_XJ_CX_TASK_TYPE                                 */
/*==============================================================*/
create bitmap index IDX_T_XJ_CX_TASK_TYPE on BIZ_T_XJ_CX_TASK (
   CX_TASK_TYPE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_XJ_CX_TASK_STATUS                               */
/*==============================================================*/
create bitmap index IDX_T_XJ_CX_TASK_STATUS on BIZ_T_XJ_CX_TASK (
   CX_TASK_STATUS ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_XJ_CX_TASK_KZJ                                  */
/*==============================================================*/
create bitmap index IDX_T_XJ_CX_TASK_KZJ on BIZ_T_XJ_CX_TASK (
   CX_TASK_CAN_DELIV ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_XJ_CX_TASK_WSC                                  */
/*==============================================================*/
create bitmap index IDX_T_XJ_CX_TASK_WSC on BIZ_T_XJ_CX_TASK (
   BELONG_WSC_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJ_CX_TASK_ITEM                                 */
/*==============================================================*/
create table BIZ_T_XJ_CX_TASK_ITEM 
(
   TASK_ITEM_ID         VARCHAR2(32)         not null,
   TASK_ID              VARCHAR2(32),
   XJD_ITEM_ID          VARCHAR2(32),
   XJD_ITEM_NAME        VARCHAR2(128),
   XJD_ITEM_ADDRESS     VARCHAR2(256),
   XJD_ITEM_DESC        VARCHAR2(128),
   RFID_CONFIRMED_TYPE  VARCHAR2(8),
   OPT_TIME             VARCHAR2(14),
   HAVE_COMPLETE        integer              default 0,
   RFID_CODE            VARCHAR2(32),
   BYZD                 VARCHAR2(1024)
);

comment on column BIZ_T_XJ_CX_TASK_ITEM.TASK_ITEM_ID is
'主键ID';

comment on column BIZ_T_XJ_CX_TASK_ITEM.TASK_ID is
'任务ID';

comment on column BIZ_T_XJ_CX_TASK_ITEM.XJD_ITEM_ID is
'巡检点ID';

comment on column BIZ_T_XJ_CX_TASK_ITEM.XJD_ITEM_NAME is
'巡检点名称';

comment on column BIZ_T_XJ_CX_TASK_ITEM.XJD_ITEM_ADDRESS is
'巡检点位置';

comment on column BIZ_T_XJ_CX_TASK_ITEM.XJD_ITEM_DESC is
'巡检点说明';

comment on column BIZ_T_XJ_CX_TASK_ITEM.RFID_CONFIRMED_TYPE is
'RFID确定方式：
1  扫描
2  无法处理';

comment on column BIZ_T_XJ_CX_TASK_ITEM.OPT_TIME is
'操作时间 精确到秒';

comment on column BIZ_T_XJ_CX_TASK_ITEM.HAVE_COMPLETE is
'是否已完成:1是 0否';

comment on column BIZ_T_XJ_CX_TASK_ITEM.RFID_CODE is
'巡检点的RFID编号';

comment on column BIZ_T_XJ_CX_TASK_ITEM.BYZD is
'备用字段';

alter table BIZ_T_XJ_CX_TASK_ITEM
   add constraint PK_BIZ_T_XJ_CX_TASK_ITEM primary key (TASK_ITEM_ID);

/*==============================================================*/
/* Index: IDX_T_XJ_CX_TASK_ITEM_FK                              */
/*==============================================================*/
create index IDX_T_XJ_CX_TASK_ITEM_FK on BIZ_T_XJ_CX_TASK_ITEM (
   TASK_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_XJ_CX_TASK_ITEM_IDFK                            */
/*==============================================================*/
create index IDX_T_XJ_CX_TASK_ITEM_IDFK on BIZ_T_XJ_CX_TASK_ITEM (
   XJD_ITEM_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_XJ_CX_TASK_ITEM_RFIDCONT                        */
/*==============================================================*/
create bitmap index IDX_T_XJ_CX_TASK_ITEM_RFIDCONT on BIZ_T_XJ_CX_TASK_ITEM (
   RFID_CONFIRMED_TYPE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_XJ_CX_TASK_ITEM_RFIDCOND                        */
/*==============================================================*/
create bitmap index IDX_T_XJ_CX_TASK_ITEM_RFIDCOND on BIZ_T_XJ_CX_TASK_ITEM (
   RFID_CODE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJ_CX_TASK_ITEM_FAULTLC                         */
/*==============================================================*/
create table BIZ_T_XJ_CX_TASK_ITEM_FAULTLC 
(
   ID                   VARCHAR2(32)         not null,
   TTASK_ITEM_SBSS_ID   VARCHAR2(128),
   OPT_TIME             VARCHAR2(14),
   OPT_ID               VARCHAR2(32),
   OPT_NAME             VARCHAR2(128),
   OPT_CONTENT          VARCHAR2(4000)
);

comment on table BIZ_T_XJ_CX_TASK_ITEM_FAULTLC is
'巡检发现问题处理流程';

comment on column BIZ_T_XJ_CX_TASK_ITEM_FAULTLC.ID is
'ID';

comment on column BIZ_T_XJ_CX_TASK_ITEM_FAULTLC.TTASK_ITEM_SBSS_ID is
'巡检任务巡检点设备与设施表主键';

comment on column BIZ_T_XJ_CX_TASK_ITEM_FAULTLC.OPT_TIME is
'操作时间';

comment on column BIZ_T_XJ_CX_TASK_ITEM_FAULTLC.OPT_ID is
'操作人员ID';

comment on column BIZ_T_XJ_CX_TASK_ITEM_FAULTLC.OPT_NAME is
'操作人员名称';

comment on column BIZ_T_XJ_CX_TASK_ITEM_FAULTLC.OPT_CONTENT is
'操作内容';

alter table BIZ_T_XJ_CX_TASK_ITEM_FAULTLC
   add constraint PK_BIZ_T_XJ_CX_TASK_ITEM_FAULT primary key (ID);

/*==============================================================*/
/* Table: BIZ_T_XJ_CX_TASK_ITEM_RST_DET                         */
/*==============================================================*/
create table BIZ_T_XJ_CX_TASK_ITEM_RST_DET 
(
   DETAIL_ID            VARCHAR2(32)         not null,
   SBSS_RST_ID          VARCHAR2(32)         not null,
   SEL_VALUE            VARCHAR2(32),
   SEL_NAME             VARCHAR2(128),
   SEL_SORT             INTEGER,
   SFZC                 VARCHAR2(8),
   SFMR                 integer              default 0
);

comment on table BIZ_T_XJ_CX_TASK_ITEM_RST_DET is
'结果检查项复制表，来源：BIZ_T_GG_CHECK_ITEM_DETAIL
为了避免检查项发生了修改后影响历史数据';

comment on column BIZ_T_XJ_CX_TASK_ITEM_RST_DET.DETAIL_ID is
'主键';

comment on column BIZ_T_XJ_CX_TASK_ITEM_RST_DET.SBSS_RST_ID is
'主表BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST';

comment on column BIZ_T_XJ_CX_TASK_ITEM_RST_DET.SEL_VALUE is
'选项值';

comment on column BIZ_T_XJ_CX_TASK_ITEM_RST_DET.SEL_NAME is
'选项名称';

comment on column BIZ_T_XJ_CX_TASK_ITEM_RST_DET.SEL_SORT is
'排序号';

comment on column BIZ_T_XJ_CX_TASK_ITEM_RST_DET.SFZC is
'该选项被选择时代表是否有问题：1正常，2异常';

comment on column BIZ_T_XJ_CX_TASK_ITEM_RST_DET.SFMR is
'是否默认 1是，0否';

alter table BIZ_T_XJ_CX_TASK_ITEM_RST_DET
   add constraint PK_BIZ_T_XJ_CX_TASK_ITEM_RST_D primary key (DETAIL_ID);

/*==============================================================*/
/* Table: BIZ_T_XJ_CX_TASK_ITEM_SBSS                            */
/*==============================================================*/
create table BIZ_T_XJ_CX_TASK_ITEM_SBSS 
(
   TTASK_ITEM_SBSS_ID   VARCHAR2(32)         not null,
   TASK_ITEM_ID         VARCHAR2(32)         not null,
   DETAIL_TYPE          VARCHAR2(8)          not null,
   SBSS_ID              VARCHAR2(32),
   NAME                 VARCHAR2(128),
   MUST_SCAN            VARCHAR2(8)          default '0' not null,
   EWM_CONFIRMED_TYPE   VARCHAR2(8),
   HAVE_COMPLETE        integer              default 0,
   OPT_TIME             VARCHAR2(14),
   BYZD                 VARCHAR2(1024),
   XJ_DESC              VARCHAR2(4000),
   SF_FAULT             integer              default 0,
   BZZ_SF_FAULT         VARCHAR2(8),
   SJK_SF_FAULT         VARCHAR2(8)
);

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS.TTASK_ITEM_SBSS_ID is
'ID';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS.TASK_ITEM_ID is
'任务巡检点表ID';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS.DETAIL_TYPE is
'类型 1设备，2设施';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS.SBSS_ID is
'设备或设施ID';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS.NAME is
'设备或设施名称';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS.MUST_SCAN is
'是否必扫标签:1是 0否';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS.EWM_CONFIRMED_TYPE is
'确认方式：
1 正常扫描
2无法扫描';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS.HAVE_COMPLETE is
'是否已完成:1是 0否';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS.OPT_TIME is
'操作时间 精确到秒';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS.BYZD is
'备用字段';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS.XJ_DESC is
'巡检说明';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS.SF_FAULT is
'巡检是否有问题  1是 0否';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS.BZZ_SF_FAULT is
'班组长判断是否有问题1是 0否
';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS.SJK_SF_FAULT is
'生技科判断是否有问题1是 0否
';

alter table BIZ_T_XJ_CX_TASK_ITEM_SBSS
   add constraint PK_BIZ_T_XJ_CX_TASK_ITEM_SBSS primary key (TTASK_ITEM_SBSS_ID);

/*==============================================================*/
/* Index: IDX_XJ_CX_TASK_ITEM_SBSS_XJD                          */
/*==============================================================*/
create index IDX_XJ_CX_TASK_ITEM_SBSS_XJD on BIZ_T_XJ_CX_TASK_ITEM_SBSS (
   TASK_ITEM_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_XJ_CX_TASK_ITEM_SBSS_TYPE                         */
/*==============================================================*/
create bitmap index IDX_XJ_CX_TASK_ITEM_SBSS_TYPE on BIZ_T_XJ_CX_TASK_ITEM_SBSS (
   DETAIL_TYPE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_XJ_CX_TASK_ITEM_SBSS_CONFT                        */
/*==============================================================*/
create bitmap index IDX_XJ_CX_TASK_ITEM_SBSS_CONFT on BIZ_T_XJ_CX_TASK_ITEM_SBSS (
   EWM_CONFIRMED_TYPE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_XJ_CX_TASK_ITEM_SBSSID                          */
/*==============================================================*/
create bitmap index IDX_T_XJ_CX_TASK_ITEM_SBSSID on BIZ_T_XJ_CX_TASK_ITEM_SBSS (
   SBSS_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST                        */
/*==============================================================*/
create table BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST 
(
   SBSS_RST_ID          VARCHAR2(32)         not null,
   TASK_ITEM_SBSS_ID    VARCHAR2(32)         not null,
   CHECK_ITEM_CODE      VARCHAR2(32)         not null,
   CHECK_ITEM_NAME      VARCHAR2(128)        not null,
   INPUT_TYPE           VARCHAR2(8)          not null,
   CHECK_VALUE          VARCHAR2(4000),
   BYZD                 VARCHAR2(1024)
);

comment on table BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST is
'保存巡检检查项记录的结果
如果检查有照片，可根据ID到附件表去获取';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST.SBSS_RST_ID is
'ID';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST.TASK_ITEM_SBSS_ID is
'任务巡检点设备设施表BIZ_T_XJ_CX_TASK_ITEM_SBSS的ID';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST.CHECK_ITEM_CODE is
'检查项CODE';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST.CHECK_ITEM_NAME is
'检查项名称';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST.INPUT_TYPE is
'冗余，输入类型：文本、数字、单选
0	单选
1	文本
2	数字
';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST.CHECK_VALUE is
'检查值，即巡检填报的值';

comment on column BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST.BYZD is
'备用字段';

alter table BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST
   add constraint PK_BIZ_T_XJ_CX_TASK_ITEM_SBSS_ primary key (SBSS_RST_ID);

/*==============================================================*/
/* Index: IDX_CX_TASK_ITEM_SBSS_RST_FK                          */
/*==============================================================*/
create index IDX_CX_TASK_ITEM_SBSS_RST_FK on BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST (
   TASK_ITEM_SBSS_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_CX_TASK_ITEM_SBSS_RSTICODE                        */
/*==============================================================*/
create index IDX_CX_TASK_ITEM_SBSS_RSTICODE on BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST (
   CHECK_ITEM_CODE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJ_CX_TASK_PERSONS                              */
/*==============================================================*/
create table BIZ_T_XJ_CX_TASK_PERSONS 
(
   ID                   VARCHAR2(32)         not null,
   CX_TASK_ID           VARCHAR2(32)         not null,
   PERSON_ID            VARCHAR2(32)         not null,
   PERSON_NAME          VARCHAR2(128),
   IS_VALID             integer              default 1,
   IN_CHARGE            integer              default 0,
   ZR_ID                VARCHAR2(64),
   ZR_NAME              VARCHAR2(64),
   BYZD                 VARCHAR2(1024)
);

comment on table BIZ_T_XJ_CX_TASK_PERSONS is
'生成巡检任务时的任务人员
将来任务的人员都可以看到生成的任务
但第一个人启动任务后就成为了任务的负责人，其他人就成为配合人员，任务被启动后其他人就不能再次启动，也看不到任务列表，不能进行执行。
转交只能在任务启动前转交';

comment on column BIZ_T_XJ_CX_TASK_PERSONS.ID is
'ID';

comment on column BIZ_T_XJ_CX_TASK_PERSONS.CX_TASK_ID is
'任务ID';

comment on column BIZ_T_XJ_CX_TASK_PERSONS.PERSON_ID is
'参与人ID';

comment on column BIZ_T_XJ_CX_TASK_PERSONS.PERSON_NAME is
'参与人名称';

comment on column BIZ_T_XJ_CX_TASK_PERSONS.IS_VALID is
'是否有效 1 有效，0无效';

comment on column BIZ_T_XJ_CX_TASK_PERSONS.IN_CHARGE is
'是否负责人 1是 0否 
任务有多个参与人员时，第一个启动任务的为任务的负责人';

comment on column BIZ_T_XJ_CX_TASK_PERSONS.ZR_ID is
'备用，转入人员ID';

comment on column BIZ_T_XJ_CX_TASK_PERSONS.ZR_NAME is
'备用，转入人员名称';

comment on column BIZ_T_XJ_CX_TASK_PERSONS.BYZD is
'备用字段';

alter table BIZ_T_XJ_CX_TASK_PERSONS
   add constraint PK_BIZ_T_XJ_CX_TASK_PERSONS primary key (ID);

/*==============================================================*/
/* Index: IDX_T_XJ_CX_TASK_PERSONS_TFK                          */
/*==============================================================*/
create index IDX_T_XJ_CX_TASK_PERSONS_TFK on BIZ_T_XJ_CX_TASK_PERSONS (
   CX_TASK_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_XJ_CX_TASK_PERSONS_PID                          */
/*==============================================================*/
create index IDX_T_XJ_CX_TASK_PERSONS_PID on BIZ_T_XJ_CX_TASK_PERSONS (
   PERSON_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_XJ_CX_TASK_PERSONS_INCHA                        */
/*==============================================================*/
create bitmap index IDX_T_XJ_CX_TASK_PERSONS_INCHA on BIZ_T_XJ_CX_TASK_PERSONS (
   IN_CHARGE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJ_FREQ_SEGMENT                                 */
/*==============================================================*/
create table BIZ_T_XJ_FREQ_SEGMENT 
(
   FREQ_SEGMENT_ID      VARCHAR2(32)         not null,
   FREQ_SEGMENT_NAME    VARCHAR2(128),
   DETAIL_ID            VARCHAR2(32)         not null,
   DETAIL_NAME          VARCHAR2(128)        not null,
   START_TIME           VARCHAR2(8)          not null,
   END_TIME             VARCHAR2(8)          not null,
   BYZD                 VARCHAR2(1024),
   DEL_FLAG             integer              default 0 not null,
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   BELONG_WSC_ID        VARCHAR2(32)         not null,
   BELONG_WSC_NAME      VARCHAR2(128),
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table BIZ_T_XJ_FREQ_SEGMENT is
'设置设备或设施的巡检频率需求，设置了频率后，系统可判定下次的巡检时间是何时
本表设置指定班次中巡检间隔划分情况
比如白班是08：00-18：00
那么本表中设置这个时间范围内划分几个段，比如08：00-10：00是一个段
一个班次下面的所有分段时间不可用重叠，且班次下的所有分段连续起来应与班次时间段一致
然后在分段关联设备表中关联设备即可。
';

comment on column BIZ_T_XJ_FREQ_SEGMENT.FREQ_SEGMENT_ID is
'主键ID';

comment on column BIZ_T_XJ_FREQ_SEGMENT.FREQ_SEGMENT_NAME is
'频率名称';

comment on column BIZ_T_XJ_FREQ_SEGMENT.DETAIL_ID is
'班次ID，表BIZ_T_XJ_WORK_GROUP_DETIAL的ID';

comment on column BIZ_T_XJ_FREQ_SEGMENT.DETAIL_NAME is
'班次名称';

comment on column BIZ_T_XJ_FREQ_SEGMENT.START_TIME is
'起始时间:格式如：08:30一个班次下面的所有分段时间不可用重叠，且班次下的所有分段连续起来应与班次时间段一致';

comment on column BIZ_T_XJ_FREQ_SEGMENT.END_TIME is
'结束时间:格式如：08:30一个班次下面的所有分段时间不可用重叠，且班次下的所有分段连续起来应与班次时间段一致';

comment on column BIZ_T_XJ_FREQ_SEGMENT.BYZD is
'备用字段';

comment on column BIZ_T_XJ_FREQ_SEGMENT.DEL_FLAG is
'是否删除，1是 0否';

comment on column BIZ_T_XJ_FREQ_SEGMENT.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_XJ_FREQ_SEGMENT.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJ_FREQ_SEGMENT.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_XJ_FREQ_SEGMENT.BELONG_WSC_ID is
'所属厂站ID，非业务数据时非必填';

comment on column BIZ_T_XJ_FREQ_SEGMENT.BELONG_WSC_NAME is
'所属厂站名称，非业务数据时非必填';

comment on column BIZ_T_XJ_FREQ_SEGMENT.UPDATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJ_FREQ_SEGMENT.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_XJ_FREQ_SEGMENT.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_XJ_FREQ_SEGMENT
   add constraint PK_BIZ_T_XJ_FREQ_SEGMENT primary key (FREQ_SEGMENT_ID);

/*==============================================================*/
/* Table: BIZ_T_XJ_FREQ_SEGMENT_SBSS                            */
/*==============================================================*/
create table BIZ_T_XJ_FREQ_SEGMENT_SBSS 
(
   FREQ_SEGMENT_SBSS_ID VARCHAR2(32)         not null,
   FREQ_SEGMENT_ID      VARCHAR2(32)         not null,
   SBSS_TYPE            VARCHAR2(8),
   SBSS_ID              VARCHAR2(128),
   SBSS_NAME            VARCHAR2(128),
   XJD_ITEM_ID          VARCHAR2(32)         not null,
   XJD_ITEM_NAME        VARCHAR2(128),
   TECHNICS_ID          VARCHAR2(128),
   TECHNICS_NAME        VARCHAR2(128),
   BYZD                 VARCHAR2(1024)
);

comment on table BIZ_T_XJ_FREQ_SEGMENT_SBSS is
'保存班次分段中设置关联的设备设施信息
关联的设备与设施就是在一次巡检时（该时间段范围内巡检时）需要进行巡检的设备与设施';

comment on column BIZ_T_XJ_FREQ_SEGMENT_SBSS.FREQ_SEGMENT_SBSS_ID is
'主键ID';

comment on column BIZ_T_XJ_FREQ_SEGMENT_SBSS.FREQ_SEGMENT_ID is
'分段ID';

comment on column BIZ_T_XJ_FREQ_SEGMENT_SBSS.SBSS_TYPE is
'设施与设备类型：1.设备  2设施';

comment on column BIZ_T_XJ_FREQ_SEGMENT_SBSS.SBSS_ID is
'设别或设施的ID';

comment on column BIZ_T_XJ_FREQ_SEGMENT_SBSS.SBSS_NAME is
'设备或设施的名称';

comment on column BIZ_T_XJ_FREQ_SEGMENT_SBSS.XJD_ITEM_ID is
'冗余字段：巡检点ID';

comment on column BIZ_T_XJ_FREQ_SEGMENT_SBSS.XJD_ITEM_NAME is
'冗余字段：巡检点名称';

comment on column BIZ_T_XJ_FREQ_SEGMENT_SBSS.TECHNICS_ID is
'工艺段ID';

comment on column BIZ_T_XJ_FREQ_SEGMENT_SBSS.TECHNICS_NAME is
'工艺名称';

comment on column BIZ_T_XJ_FREQ_SEGMENT_SBSS.BYZD is
'备用字段';

alter table BIZ_T_XJ_FREQ_SEGMENT_SBSS
   add constraint PK_BIZ_T_XJ_FREQ_SEGMENT_SBSS primary key (FREQ_SEGMENT_SBSS_ID);

/*==============================================================*/
/* Index: IDX_XJ_FREQ_SEGMENT_SBSSRPT                           */
/*==============================================================*/
create unique index IDX_XJ_FREQ_SEGMENT_SBSSRPT on BIZ_T_XJ_FREQ_SEGMENT_SBSS (
   FREQ_SEGMENT_ID ASC,
   SBSS_TYPE ASC,
   SBSS_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ                        */
/*==============================================================*/
create table BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ 
(
   ATT_ID               VARCHAR2(32)         not null,
   TECHNICS_ID          VARCHAR2(32)         not null,
   XJD_ITEM_ID          VARCHAR2(32)         not null,
   XJD_ITEM_NAME        VARCHAR2(128),
   YBZD                 VARCHAR2(1024)
);

comment on table BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ is
'保存工艺段关联的巡检点，用于支撑值班排班巡检工作';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ.ATT_ID is
'主键ID';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ.TECHNICS_ID is
'工艺段ID';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ.XJD_ITEM_ID is
'巡检点ID';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ.XJD_ITEM_NAME is
'巡检点名称';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ.YBZD is
'备用字段';

alter table BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ
   add constraint PK_BIZ_T_XJ_TECHNICS_SCOPE_ATT primary key (ATT_ID);

/*==============================================================*/
/* Table: BIZ_T_XJ_TECHNICS_SCOPE_MANAGE                        */
/*==============================================================*/
create table BIZ_T_XJ_TECHNICS_SCOPE_MANAGE 
(
   TECHNICS_ID          VARCHAR2(32)         not null,
   TECHNICS_NAME        VARCHAR2(128)        not null,
   TECHNICS_DESC        VARCHAR2(1024),
   YBZD                 VARCHAR2(1024),
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   BELONG_WSC_ID        VARCHAR2(32),
   BELONG_WSCN_AME      VARCHAR2(128),
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL,
   DEL_FLAG             integer              default 0 not null
);

comment on table BIZ_T_XJ_TECHNICS_SCOPE_MANAGE is
'工艺段配置主要是为了让各个厂所配置工艺段信息，工艺段下面可挂接巡检点。
在进行厂巡排版时，负责该工艺段的用户就要负责该工艺段下的所有巡检点的巡检工作';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_MANAGE.TECHNICS_ID is
'主键ID';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_MANAGE.TECHNICS_NAME is
'工艺段名称';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_MANAGE.TECHNICS_DESC is
'工艺段说明';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_MANAGE.YBZD is
'备用字段';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_MANAGE.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_MANAGE.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_MANAGE.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_MANAGE.BELONG_WSC_ID is
'所属厂站ID，非业务数据时非必填';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_MANAGE.BELONG_WSCN_AME is
'所属厂站名称，非业务数据时非必填';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_MANAGE.UPDATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_MANAGE.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_MANAGE.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

comment on column BIZ_T_XJ_TECHNICS_SCOPE_MANAGE.DEL_FLAG is
'是否删除，1是 0否';

alter table BIZ_T_XJ_TECHNICS_SCOPE_MANAGE
   add constraint PK_BIZ_T_XJ_TECHNICS_SCOPE_MAN primary key (TECHNICS_ID);

/*==============================================================*/
/* Table: BIZ_T_XJ_WORK_GROUP                                   */
/*==============================================================*/
create table BIZ_T_XJ_WORK_GROUP 
(
   WORK_GROUP_ID        VARCHAR2(32)         not null,
   WORK_GROUP_NAME      VARCHAR2(128),
   WORK_GROUP_DESC      VARCHAR2(1024),
   START_TIME           VARCHAR2(16)         not null,
   END_TIME             VARCHAR2(16)         not null,
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   BELONG_WSC_ID        VARCHAR2(32),
   BELONG_WSCN_AME      VARCHAR2(128),
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table BIZ_T_XJ_WORK_GROUP is
'设置每个厂站的班次主表
一个厂站都有一个或多个班次（一个厂站本表只会有一条数据），但要满足班次的时间和为24小时，且班次之间不能有空档时间';

comment on column BIZ_T_XJ_WORK_GROUP.WORK_GROUP_ID is
'主键ID';

comment on column BIZ_T_XJ_WORK_GROUP.WORK_GROUP_NAME is
'名称';

comment on column BIZ_T_XJ_WORK_GROUP.WORK_GROUP_DESC is
'设置说明';

comment on column BIZ_T_XJ_WORK_GROUP.START_TIME is
'开始时间，格式如：08:30，';

comment on column BIZ_T_XJ_WORK_GROUP.END_TIME is
'截止时间，格式如：08:30，';

comment on column BIZ_T_XJ_WORK_GROUP.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_XJ_WORK_GROUP.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJ_WORK_GROUP.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_XJ_WORK_GROUP.BELONG_WSC_ID is
'所属厂站ID，非业务数据时非必填';

comment on column BIZ_T_XJ_WORK_GROUP.BELONG_WSCN_AME is
'所属厂站名称，非业务数据时非必填';

comment on column BIZ_T_XJ_WORK_GROUP.UPDATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJ_WORK_GROUP.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_XJ_WORK_GROUP.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_XJ_WORK_GROUP
   add constraint PK_BIZ_T_XJ_WORK_GROUP primary key (WORK_GROUP_ID);

/*==============================================================*/
/* Table: BIZ_T_XJ_WORK_GROUP_DETIAL                            */
/*==============================================================*/
create table BIZ_T_XJ_WORK_GROUP_DETIAL 
(
   DETAIL_ID            VARCHAR2(32)         not null,
   GROUP_ID             VARCHAR2(32)         not null,
   DETAIL_NAME          VARCHAR2(128)        not null,
   DETAIL_DESC          VARCHAR2(1024),
   START_TIME           VARCHAR2(16)         not null,
   END_TIME             VARCHAR2(16)         not null,
   SORT_NUM             INTEGER,
   BYZD                 VARCHAR2(1024),
   DEL_FLAG             integer              default 0 not null
);

comment on table BIZ_T_XJ_WORK_GROUP_DETIAL is
'用于设置每一个具体班次的信息，一个厂站会有多个班次';

comment on column BIZ_T_XJ_WORK_GROUP_DETIAL.DETAIL_ID is
'主键ID';

comment on column BIZ_T_XJ_WORK_GROUP_DETIAL.GROUP_ID is
'主表BIZ_T_XJ_WORK_GROUP的ID';

comment on column BIZ_T_XJ_WORK_GROUP_DETIAL.DETAIL_NAME is
'班次名称,如白班，中班，晚班等';

comment on column BIZ_T_XJ_WORK_GROUP_DETIAL.DETAIL_DESC is
'设置说明';

comment on column BIZ_T_XJ_WORK_GROUP_DETIAL.START_TIME is
'开始时间，格式如：08:30';

comment on column BIZ_T_XJ_WORK_GROUP_DETIAL.END_TIME is
'截止时间，格式如：08:30';

comment on column BIZ_T_XJ_WORK_GROUP_DETIAL.SORT_NUM is
'排序号';

comment on column BIZ_T_XJ_WORK_GROUP_DETIAL.BYZD is
'备用字段';

comment on column BIZ_T_XJ_WORK_GROUP_DETIAL.DEL_FLAG is
'是否删除，1是 0否';

alter table BIZ_T_XJ_WORK_GROUP_DETIAL
   add constraint PK_BIZ_T_XJ_WORK_GROUP_DETIAL primary key (DETAIL_ID);

/*==============================================================*/
/* Table: BIZ_T_XJ_ZB_ITEM                                      */
/*==============================================================*/
create table BIZ_T_XJ_ZB_ITEM 
(
   CXZB_ID              VARCHAR2(32)         not null,
   CXZB_CODE            VARCHAR2(32)         not null,
   CXZB_NAME            VARCHAR2(128)        not null,
   CXZB_JC              VARCHAR2(128),
   CXZB_UNIT            VARCHAR2(8),
   CXZB_DESC            VARCHAR2(1024),
   CXZB_TBLX            VARCHAR2(8)          default '1',
   BYZD                 VARCHAR2(1024),
   DEL_FLAG             integer              default 0,
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table BIZ_T_XJ_ZB_ITEM is
'存储设定的巡检指标基础数据，如COD/BOD等
整个公司的巡检指标在此处统一定义与保存，各厂站在制定工作票时使用即可
在删除该表数据时，首先要检查作业票模板中是否进行了关联，如果有关联则不能删除';

comment on column BIZ_T_XJ_ZB_ITEM.CXZB_CODE is
'指标CODE';

comment on column BIZ_T_XJ_ZB_ITEM.CXZB_NAME is
'指标名称';

comment on column BIZ_T_XJ_ZB_ITEM.CXZB_JC is
'简称';

comment on column BIZ_T_XJ_ZB_ITEM.CXZB_UNIT is
'计量单位';

comment on column BIZ_T_XJ_ZB_ITEM.CXZB_DESC is
'指标说明';

comment on column BIZ_T_XJ_ZB_ITEM.CXZB_TBLX is
'巡检指标填报类型，字典项【作业票指标输入类型】
2	数值
1	普通文本

';

comment on column BIZ_T_XJ_ZB_ITEM.BYZD is
'备用字段';

comment on column BIZ_T_XJ_ZB_ITEM.DEL_FLAG is
'是否删除，1是 0否';

comment on column BIZ_T_XJ_ZB_ITEM.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_XJ_ZB_ITEM.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJ_ZB_ITEM.UPDATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJ_ZB_ITEM.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_XJ_ZB_ITEM.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_XJ_ZB_ITEM
   add constraint PK_BIZ_T_XJ_ZB_ITEM primary key (CXZB_ID);

/*==============================================================*/
/* Index: IDX_T_XJ_ZB_ITEM_CODE                                 */
/*==============================================================*/
create unique index IDX_T_XJ_ZB_ITEM_CODE on BIZ_T_XJ_ZB_ITEM (
   CXZB_CODE ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJ_ZB_PLAN                                      */
/*==============================================================*/
create table BIZ_T_XJ_ZB_PLAN 
(
   ZB_PLAN_ID           VARCHAR2(32)         not null,
   ZB_DATE              VARCHAR2(16)         not null,
   ZB_FZR_ID            VARCHAR2(32),
   ZB_FZR_MC            VARCHAR2(128),
   START_TIME           VARCHAR2(16)         not null,
   END_TIME             VARCHAR2(16)         not null,
   BYZD                 VARCHAR2(1024),
   VALID_FLAG           integer              default 0 not null,
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   BELONG_WSC_ID        VARCHAR2(32)         not null,
   BELONG_WSC_NAME      VARCHAR2(128),
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table BIZ_T_XJ_ZB_PLAN is
'每个厂站管理自己的厂巡排班计划,管理计划主信息
每个工作日只能有一个计划';

comment on column BIZ_T_XJ_ZB_PLAN.ZB_PLAN_ID is
'主键ID';

comment on column BIZ_T_XJ_ZB_PLAN.ZB_DATE is
'值班日期 yyyyMMdd';

comment on column BIZ_T_XJ_ZB_PLAN.ZB_FZR_ID is
'值班负责人ID';

comment on column BIZ_T_XJ_ZB_PLAN.ZB_FZR_MC is
'值班负责人名称';

comment on column BIZ_T_XJ_ZB_PLAN.START_TIME is
'开始时间，格式如：yyyyMMddHHmm,不需要秒，需要根据该厂站的班次周期计算出在本天值班的开始时间
开始日期是值班日期 yyyyMMdd+本次值班开始时间
';

comment on column BIZ_T_XJ_ZB_PLAN.END_TIME is
'截止时间，格式如：yyyyMMddHHmm,不需要秒，需要根据该厂站的班次周期计算出在本天值班的截止时间
开始日期是值班日期 yyyyMMdd+本次值班结束时间';

comment on column BIZ_T_XJ_ZB_PLAN.BYZD is
'备用';

comment on column BIZ_T_XJ_ZB_PLAN.VALID_FLAG is
'是否已经生效，1是 0否 当天的值班任务被生成后即变为生效状态
生效后不能删除';

comment on column BIZ_T_XJ_ZB_PLAN.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_XJ_ZB_PLAN.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJ_ZB_PLAN.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_XJ_ZB_PLAN.BELONG_WSC_ID is
'所属厂站ID，非业务数据时非必填';

comment on column BIZ_T_XJ_ZB_PLAN.BELONG_WSC_NAME is
'所属厂站名称，非业务数据时非必填';

comment on column BIZ_T_XJ_ZB_PLAN.UPDATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJ_ZB_PLAN.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_XJ_ZB_PLAN.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_XJ_ZB_PLAN
   add constraint PK_BIZ_T_XJ_ZB_PLAN primary key (ZB_PLAN_ID);

/*==============================================================*/
/* Index: IDX_T_XJ_ZB_PLAN_DATE_UNIQ                            */
/*==============================================================*/
create unique index IDX_T_XJ_ZB_PLAN_DATE_UNIQ on BIZ_T_XJ_ZB_PLAN (
   ZB_DATE ASC,
   BELONG_WSC_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJ_ZB_PLAN_DETAIL                               */
/*==============================================================*/
create table BIZ_T_XJ_ZB_PLAN_DETAIL 
(
   PLAN_DETAIL_ID       VARCHAR2(32)         not null,
   ZB_PLAN_ID           VARCHAR2(32)         not null,
   DETAIL_ID            VARCHAR2(32)         not null,
   DETAIL_NAME          VARCHAR2(128),
   TECHNICS_ID          VARCHAR2(32)         not null,
   TECHNICS_NAME        VARCHAR2(128),
   BYZD                 VARCHAR2(1024)
);

comment on table BIZ_T_XJ_ZB_PLAN_DETAIL is
'管理每个班次的值班人员';

comment on column BIZ_T_XJ_ZB_PLAN_DETAIL.PLAN_DETAIL_ID is
'主键ID';

comment on column BIZ_T_XJ_ZB_PLAN_DETAIL.ZB_PLAN_ID is
'值班计划ID';

comment on column BIZ_T_XJ_ZB_PLAN_DETAIL.DETAIL_ID is
'班次ID 表BIZ_T_XJ_WORK_GROUP_DETIAL的ID';

comment on column BIZ_T_XJ_ZB_PLAN_DETAIL.DETAIL_NAME is
'班次名称';

comment on column BIZ_T_XJ_ZB_PLAN_DETAIL.TECHNICS_ID is
'工段ID';

comment on column BIZ_T_XJ_ZB_PLAN_DETAIL.TECHNICS_NAME is
'工段名称';

comment on column BIZ_T_XJ_ZB_PLAN_DETAIL.BYZD is
'备用字段';

alter table BIZ_T_XJ_ZB_PLAN_DETAIL
   add constraint PK_BIZ_T_XJ_ZB_PLAN_DETAIL primary key (PLAN_DETAIL_ID);

/*==============================================================*/
/* Table: BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON                        */
/*==============================================================*/
create table BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON 
(
   PLAN_DETAIL_PERSON_ID VARCHAR2(32)         not null,
   PLAN_DETAIL_ID       VARCHAR2(32)         not null,
   PERSON_ID            VARCHAR2(32)         not null,
   PERSON_NAME          VARCHAR2(128),
   BYZD                 VARCHAR2(1024)
);

comment on table BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON is
'厂巡排班管理值班人员
一个班次在一个工段上可能有一个或多个人员';

comment on column BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON.PLAN_DETAIL_PERSON_ID is
'主键ID';

comment on column BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON.PLAN_DETAIL_ID is
'排班从表ID';

comment on column BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON.PERSON_ID is
'值班人员ID';

comment on column BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON.PERSON_NAME is
'值班人员名称';

comment on column BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON.BYZD is
'备用字段';

alter table BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON
   add constraint PK_BIZ_T_XJ_ZB_PLAN_DETAIL_PER primary key (PLAN_DETAIL_PERSON_ID);

/*==============================================================*/
/* Table: BIZ_T_XJ_ZYP_CX_MAKE                                  */
/*==============================================================*/
create table BIZ_T_XJ_ZYP_CX_MAKE 
(
   CX_MAKE_ID           VARCHAR2(32)         not null,
   ZYP_DATE             VARCHAR2(16)         not null,
   ZYP_CODE             VARCHAR2(32),
   WARNING              VARCHAR2(8),
   BYZD                 VARCHAR2(1024),
   TEMP_VERSION         INTEGER,
   CXZYP_STATUS         VARCHAR2(8),
   FZR_ID               VARCHAR2(128)        not null,
   FZR_NAME             VARCHAR2(128)        not null,
   ZYP_DESC             VARCHAR2(4000),
   TBRY_ID              VARCHAR2(32)         not null,
   TBRY_NAME            VARCHAR2(32)         not null,
   CREATOR_TIMESTEMP    VARCHAR2(14)         default NULL not null,
   BELONG_WSC_ID        VARCHAR2(32)         not null,
   BELONG_WSC_NAME      VARCHAR2(128)        not null,
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL,
   WEATHER              VARCHAR2(16)
);

comment on column BIZ_T_XJ_ZYP_CX_MAKE.CX_MAKE_ID is
'拟定表主键ID';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.ZYP_DATE is
'作业票日期';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.ZYP_CODE is
'作业票编号';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.WARNING is
'状态：red	红色
orange	橙色
green	绿色
';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.BYZD is
'备用字段';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.TEMP_VERSION is
'模板版本号,不需要人工维护，从模板主表获取';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.CXZYP_STATUS is
'作业票状态:
01 拟定中
02  审批中
03  已审批
04  在填报
05  待验收
06  已验收';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.FZR_ID is
'班组负责人ID，在拟定时选择';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.FZR_NAME is
'班组负责人名称';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.ZYP_DESC is
'工作票说明';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.TBRY_ID is
'当前操作人，填报人员ID，即作业票拟定人';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.TBRY_NAME is
'填报人员名称';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.CREATOR_TIMESTEMP is
'创建时间';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.BELONG_WSC_ID is
'所属厂站ID，非业务数据时非必填';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.BELONG_WSC_NAME is
'所属厂站名称，非业务数据时非必填';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.UPDATOR_ACCOUNT is
'修改人ID或账号';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

comment on column BIZ_T_XJ_ZYP_CX_MAKE.WEATHER is
'天气：
suny	晴
overcast	阴
smallrain	小雨
normalrain	中雨
bigrain	大雨
';

alter table BIZ_T_XJ_ZYP_CX_MAKE
   add constraint PK_BIZ_T_XJ_ZYP_CX_MAKE primary key (CX_MAKE_ID);

/*==============================================================*/
/* Index: IDX_T_XJ_ZYP_CX_MAKE_BEL_WSC                          */
/*==============================================================*/
create bitmap index IDX_T_XJ_ZYP_CX_MAKE_BEL_WSC on BIZ_T_XJ_ZYP_CX_MAKE (
   BELONG_WSC_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_XJ_ZYP_CX_MAKE_DATE                             */
/*==============================================================*/
create index IDX_T_XJ_ZYP_CX_MAKE_DATE on BIZ_T_XJ_ZYP_CX_MAKE (
   ZYP_DATE ASC,
   BELONG_WSC_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Index: IDX_T_XJ_ZYP_CX_MAKE_WARN                             */
/*==============================================================*/
create bitmap index IDX_T_XJ_ZYP_CX_MAKE_WARN on BIZ_T_XJ_ZYP_CX_MAKE (
   WARNING ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJ_ZYP_CX_MAKE_HIS                              */
/*==============================================================*/
create table BIZ_T_XJ_ZYP_CX_MAKE_HIS 
(
   HIS_ID               VARCHAR2(32)         not null,
   CX_MAKE_ID           VARCHAR2(128),
   OPT_ID               VARCHAR2(32)         not null,
   OPT_NAME             VARCHAR2(128),
   OPT_TYPE             VARCHAR2(8),
   OPT_CONTENT          VARCHAR2(1024),
   OPT_DESC             VARCHAR2(2048),
   OPT_TIME             VARCHAR2(14)
);

comment on column BIZ_T_XJ_ZYP_CX_MAKE_HIS.HIS_ID is
'主键ID';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_HIS.CX_MAKE_ID is
'作业票拟定主键ID';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_HIS.OPT_ID is
'操作人ID';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_HIS.OPT_NAME is
'操作人名称';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_HIS.OPT_TYPE is
'操作类型 1?拟定??2??审核??3?中止?4?填报5?验收';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_HIS.OPT_CONTENT is
'操作内容，主要填写流程中的结果，比如通过、不通过';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_HIS.OPT_DESC is
'操作说明，填写审批时的意见';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_HIS.OPT_TIME is
'操作时间，yyyyMMddHHmmss ';

alter table BIZ_T_XJ_ZYP_CX_MAKE_HIS
   add constraint PK_BIZ_T_XJ_ZYP_CX_MAKE_HIS primary key (HIS_ID);

/*==============================================================*/
/* Table: BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH                        */
/*==============================================================*/
create table BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH 
(
   PERSON_KH_ID         VARCHAR2(32)         not null,
   CX_MAKE_ID           VARCHAR2(32)         not null,
   BKH_ID               VARCHAR2(32)         not null,
   BKH_NAME             VARCHAR2(128),
   KH_DESC              VARCHAR2(1024),
   KH_SCORE             NUMBER(6,2),
   HAVE_CONFIRM         integer              default 0,
   CONFIRM_TIME         DATE,
   BYZD                 VARCHAR2(1024)
);

comment on table BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH is
'记录人员考核数据
考核数据目前只是一个记录，暂无其他用处';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH.PERSON_KH_ID is
'人员考核主键';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH.CX_MAKE_ID is
'拟定表BIZ_T_XJ_ZYP_CX_MAKE主键ID';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH.BKH_ID is
'被考核人员ID';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH.BKH_NAME is
'被考核人姓名';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH.KH_DESC is
'考核情况说明';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH.KH_SCORE is
'所扣分分值';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH.HAVE_CONFIRM is
'是否已经确认 1是 0否';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH.CONFIRM_TIME is
'确认时间';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH.BYZD is
'备用字段';

alter table BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH
   add constraint PK_BIZ_T_XJ_ZYP_CX_MAKE_PERSON primary key (PERSON_KH_ID);

/*==============================================================*/
/* Index: IDX_XJ_ZYP_CX_MAKE_PERSON_KHFK                        */
/*==============================================================*/
create index IDX_XJ_ZYP_CX_MAKE_PERSON_KHFK on BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH (
   CX_MAKE_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJ_ZYP_CX_MAKE_TMP                              */
/*==============================================================*/
create table BIZ_T_XJ_ZYP_CX_MAKE_TMP 
(
   MAKE_TMP_ID          VARCHAR2(32)         not null,
   CX_MAKE_ID           VARCHAR2(32)         not null,
   ZXP_TEMP_ITM_ID      VARCHAR2(32),
   ZXP_TEMP_ITM_NAME    VARCHAR2(128)        not null,
   JLXDZ                integer              default 1,
   JLSBZ                integer              default 1,
   JLQCL                integer              default 0,
   BYZD                 VARCHAR2(1024),
   ZXP_TEMP_SORT        INTEGER
);

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP.MAKE_TMP_ID is
'主键ID';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP.CX_MAKE_ID is
'拟定表BIZ_T_XJ_ZYP_CX_MAKE ID';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP.ZXP_TEMP_ITM_ID is
'模板主键ID,拟定工作票时关联的模板表BIZ_T_XJ_ZYP_TEMPLATE_ITM 的主键ID';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP.ZXP_TEMP_ITM_NAME is
'内容名称,定义的工作票上的分块内容，比如：进水水质';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP.JLXDZ is
'记录下达值，1是 0否';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP.JLSBZ is
'记录完成值，1是 0否';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP.JLQCL is
'记录去除率，1是 0否';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP.BYZD is
'备用字段';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP.ZXP_TEMP_SORT is
'排序号';

alter table BIZ_T_XJ_ZYP_CX_MAKE_TMP
   add constraint PK_BIZ_T_XJ_ZYP_CX_MAKE_TMP primary key (MAKE_TMP_ID);

/*==============================================================*/
/* Index: IDX_T_XJ_ZYP_CX_MAKE_ID_FK                            */
/*==============================================================*/
create index IDX_T_XJ_ZYP_CX_MAKE_ID_FK on BIZ_T_XJ_ZYP_CX_MAKE_TMP (
   CX_MAKE_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM                         */
/*==============================================================*/
create table BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM 
(
   MAKE_TMP_ITEM_ID     VARCHAR2(32)         not null,
   MAKE_TMP_ID          VARCHAR2(32)         not null,
   CXZB_ID              VARCHAR2(32),
   CXZB_NAME            VARCHAR2(128),
   CXZB_JC              VARCHAR2(128),
   JLXDZ                VARCHAR2(4000),
   JLXDZFD              NUMBER(8,2),
   JLSBZ                VARCHAR2(4000),
   JLQCL                NUMBER(6,2),
   BYZD                 VARCHAR2(1024)
);

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM.MAKE_TMP_ITEM_ID is
'数据项主键ID';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM.MAKE_TMP_ID is
'模板下达BIZ_T_XJ_ZYP_CX_MAKE_TMP表的主键';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM.CXZB_ID is
'巡检指标项ID';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM.CXZB_NAME is
'冗余：指标项名称';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM.CXZB_JC is
'冗余：简称';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM.JLXDZ is
'下达值';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM.JLXDZFD is
'浮动值';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM.JLSBZ is
'完成值';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM.JLQCL is
'出去率';

comment on column BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM.BYZD is
'备用字段';

alter table BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM
   add constraint PK_BIZ_T_XJ_ZYP_CX_MAKE_TMP_IT primary key (MAKE_TMP_ITEM_ID);

/*==============================================================*/
/* Index: IDX_XJ_ZYP_CX_MAKE_TMP_ID                             */
/*==============================================================*/
create index IDX_XJ_ZYP_CX_MAKE_TMP_ID on BIZ_T_XJ_ZYP_CX_MAKE_TMP_ITEM (
   MAKE_TMP_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJ_ZYP_TEMPLATE                                 */
/*==============================================================*/
create table BIZ_T_XJ_ZYP_TEMPLATE 
(
   TEMP_ID              VARCHAR2(32)         not null,
   TEMP_NAME            VARCHAR2(128),
   TEMP_VERSION         INTEGER              default 0,
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   BELONG_WSC_ID        VARCHAR2(32),
   BELONG_WSC_NAME      VARCHAR2(128),
   BELONG_DEPT          VARCHAR2(32),
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table BIZ_T_XJ_ZYP_TEMPLATE is
'每个厂站只可以定义一个厂巡检模板，该模版不能被删除，只能维护其里面的模板项定义
该数据不可被删除';

comment on column BIZ_T_XJ_ZYP_TEMPLATE.TEMP_ID is
'主键';

comment on column BIZ_T_XJ_ZYP_TEMPLATE.TEMP_NAME is
'名称';

comment on column BIZ_T_XJ_ZYP_TEMPLATE.TEMP_VERSION is
'版本号，模板下的模板项被修改时增加此编号';

comment on column BIZ_T_XJ_ZYP_TEMPLATE.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_XJ_ZYP_TEMPLATE.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJ_ZYP_TEMPLATE.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_XJ_ZYP_TEMPLATE.BELONG_WSC_ID is
'所属厂站ID，非业务数据时非必填';

comment on column BIZ_T_XJ_ZYP_TEMPLATE.BELONG_WSC_NAME is
'所属厂站名称，非业务数据时非必填';

comment on column BIZ_T_XJ_ZYP_TEMPLATE.BELONG_DEPT is
'所属部门';

comment on column BIZ_T_XJ_ZYP_TEMPLATE.UPDATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJ_ZYP_TEMPLATE.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_XJ_ZYP_TEMPLATE.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_XJ_ZYP_TEMPLATE
   add constraint PK_BIZ_T_XJ_ZYP_TEMPLATE primary key (TEMP_ID);

/*==============================================================*/
/* Index: IDX_T_XJ_ZYP_TEMPLATE_WSC                             */
/*==============================================================*/
create unique index IDX_T_XJ_ZYP_TEMPLATE_WSC on BIZ_T_XJ_ZYP_TEMPLATE (
   BELONG_WSC_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_XJ_ZYP_TEMPLATE_ITM                             */
/*==============================================================*/
create table BIZ_T_XJ_ZYP_TEMPLATE_ITM 
(
   ZXP_TEMP_ITM_ID      VARCHAR2(32)         not null,
   ZXP_TEMP_ITM_NAME    VARCHAR2(128)        not null,
   TEMP_ID              VARCHAR2(32)         not null,
   JLXDZ                integer              default 1,
   JLSBZ                integer              default 1,
   JLQCL                integer              default 0,
   CONF_DESC            VARCHAR2(1024),
   ZXP_TEMP_SORT        INTEGER,
   BYZD                 VARCHAR2(1024),
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.ZXP_TEMP_ITM_ID is
'模板项主键ID';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.ZXP_TEMP_ITM_NAME is
'内容名称,定义的工作票上的分块内容，比如：进水水质';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.TEMP_ID is
'模板主键';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.JLXDZ is
'记录下达值，1是 0否';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.JLSBZ is
'记录完成值，1是 0否';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.JLQCL is
'记录去除率，1是 0否';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.CONF_DESC is
'配置说明';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.ZXP_TEMP_SORT is
'排序号';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.BYZD is
'备用字段';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.CREATOR_NAME is
'创建人名称';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.UPDATOR_ACCOUNT is
'创建人ID或账号';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.UPDATOR_NAME is
'修改人姓名';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

alter table BIZ_T_XJ_ZYP_TEMPLATE_ITM
   add constraint PK_BIZ_T_XJ_ZYP_TEMPLATE_ITM primary key (ZXP_TEMP_ITM_ID);

/*==============================================================*/
/* Table: BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET                         */
/*==============================================================*/
create table BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET 
(
   DETAIL_ID            VARCHAR2(32)         not null,
   ZXP_TEMP_ITM_ID      VARCHAR2(32)         not null,
   CXZB_ID              VARCHAR2(32),
   CXZB_NAME            VARCHAR2(128),
   CXZB_JC              VARCHAR2(128),
   DETAIL_SORT          INTEGER,
   BYZD                 VARCHAR2(1024)
);

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET.DETAIL_ID is
'主键ID';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET.ZXP_TEMP_ITM_ID is
'模板项ID';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET.CXZB_ID is
'巡检指标项ID';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET.CXZB_NAME is
'冗余：指标项名称';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET.CXZB_JC is
'简称';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET.DETAIL_SORT is
'排序号';

comment on column BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET.BYZD is
'备用字段';

alter table BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET
   add constraint PK_BIZ_T_XJ_ZYP_TEMPLATE_ITM_D primary key (DETAIL_ID);

/*==============================================================*/
/* Index: IDX_XJ_ZYP_TEMPLA_ITM_DETNOREP                        */
/*==============================================================*/
create unique index IDX_XJ_ZYP_TEMPLA_ITM_DETNOREP on BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET (
   ZXP_TEMP_ITM_ID ASC,
   CXZB_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: BIZ_T_ZB_CJ                                           */
/*==============================================================*/
create table BIZ_T_ZB_CJ 
(
   ID                   VARCHAR2(32)         not null,
   WSC_CODE             VARCHAR2(128),
   JCX_ID               VARCHAR2(32),
   TAGINDEX             VARCHAR2(32),
   SB_ID                VARCHAR2(128),
   TECHNICS_ID          VARCHAR2(128),
   作业票指标组ID             VARCHAR2(32),
   ZBX_ID               VARCHAR2(32),
   REMARK               VARCHAR2(2048)
);

comment on table BIZ_T_ZB_CJ is
'检查项通过在线采集关系表';

comment on column BIZ_T_ZB_CJ.WSC_CODE is
'所属厂站机构编号';

comment on column BIZ_T_ZB_CJ.JCX_ID is
'检查项的ID';

comment on column BIZ_T_ZB_CJ.TAGINDEX is
'RealList中的TagIndex';

comment on column BIZ_T_ZB_CJ.SB_ID is
'设备ID';

comment on column BIZ_T_ZB_CJ.TECHNICS_ID is
'工艺段ID  备用';

comment on column BIZ_T_ZB_CJ.作业票指标组ID is
'作业票指标组ID  BIZ_T_XJ_ZYP_TEMPLATE_ITM表中ID';

comment on column BIZ_T_ZB_CJ.ZBX_ID is
'厂巡系统的指标项的编码 备用';

comment on column BIZ_T_ZB_CJ.REMARK is
'说明,可在该字段中说明这个设备是哪个工艺段的等信息';

alter table BIZ_T_ZB_CJ
   add constraint PK_BIZ_T_ZB_CJ primary key (ID);

/*==============================================================*/
/* Index: INDEX_T_ZB_CJ_SB_UNIQUE                               */
/*==============================================================*/
create index INDEX_T_ZB_CJ_SB_UNIQUE on BIZ_T_ZB_CJ (
   SB_ID ASC
)
tablespace SWJT_YXSW_IDX;

/*==============================================================*/
/* Table: DES_BASE_TABLE                                        */
/*==============================================================*/
create table DES_BASE_TABLE 
(
   DEL_FLAG             integer              default 0,
   CREATE_TIMESTEMP     VARCHAR2(14)         default NULL not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null,
   BELONG_WSC_ID        VARCHAR2(32),
   BELONG_WSC_NAME      VARCHAR2(128),
   BELONG_DEPT          VARCHAR2(32),
   UPDATOR_ACCOUNT      VARCHAR2(32),
   UPDATOR_NAME         VARCHAR2(32),
   UPDATE_TIMESTEMP     VARCHAR2(14)         default NULL
);

comment on table DES_BASE_TABLE is
'用于复制黏贴用';

comment on column DES_BASE_TABLE.DEL_FLAG is
'是否删除，1是 0否';

comment on column DES_BASE_TABLE.CREATE_TIMESTEMP is
'创建时间，一般不作为业务字段使用';

comment on column DES_BASE_TABLE.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column DES_BASE_TABLE.CREATOR_NAME is
'创建人名称';

comment on column DES_BASE_TABLE.BELONG_WSC_ID is
'所属厂站ID，非业务数据时非必填';

comment on column DES_BASE_TABLE.BELONG_WSC_NAME is
'所属厂站名称，非业务数据时非必填';

comment on column DES_BASE_TABLE.BELONG_DEPT is
'所属部门';

comment on column DES_BASE_TABLE.UPDATOR_ACCOUNT is
'创建人ID或账号';

comment on column DES_BASE_TABLE.UPDATOR_NAME is
'修改人姓名';

comment on column DES_BASE_TABLE.UPDATE_TIMESTEMP is
'修改时间，一般不作为业务字段使用';

/*==============================================================*/
/* Table: SYS_VERSION_MANAGE                                    */
/*==============================================================*/
create table SYS_VERSION_MANAGE 
(
   ID                   VARCHAR2(32)         not null,
   VERSION_TYPE         VARCHAR2(8)          not null,
   CURRENT_VERSION      VARCHAR2(32)         not null,
   CHANGE_TIME          VARCHAR2(16)         not null,
   CHANGE_DESC          CLOB                 not null,
   CREATE_TIME          DATE                 not null,
   CREATOR_ACCOUNT      VARCHAR2(32)         not null,
   CREATOR_NAME         VARCHAR2(32)         not null
);

comment on column SYS_VERSION_MANAGE.ID is
'主键ID';

comment on column SYS_VERSION_MANAGE.VERSION_TYPE is
'版本类型：
1   APP更新
2   PC主程序更新';

comment on column SYS_VERSION_MANAGE.CURRENT_VERSION is
'当前版本号';

comment on column SYS_VERSION_MANAGE.CHANGE_TIME is
'更新时间';

comment on column SYS_VERSION_MANAGE.CHANGE_DESC is
'更新说明';

comment on column SYS_VERSION_MANAGE.CREATE_TIME is
'创建时间';

comment on column SYS_VERSION_MANAGE.CREATOR_ACCOUNT is
'创建人ID或账号';

comment on column SYS_VERSION_MANAGE.CREATOR_NAME is
'创建人名称';

alter table SYS_VERSION_MANAGE
   add constraint PK_SYS_VERSION_MANAGE primary key (ID);

