--更新用户表，去掉用户类型和工种，以及新增修改时，不选择工种和用户类型

--调整机构调整机构类型为 集团、公司、厂所、部门（字典数据，不可修改）
select * from sys_dict_tree_data t where t.parentnodeid='S000004';

delete sys_dict_tree_data t where t.parentnodeid='S000004';

commit;

insert into sys_dict_tree_data (NODEID, PARENTNODEID, CODE, DATA1, DATA2, DATA3, ORDERNO)
values (sys_guid(), 'S000004', '1', '集团', null, null, 1);

insert into sys_dict_tree_data (NODEID, PARENTNODEID, CODE, DATA1, DATA2, DATA3, ORDERNO)
values (sys_guid(), 'S000004', '2', '公司', null, null, 2);

insert into sys_dict_tree_data (NODEID, PARENTNODEID, CODE, DATA1, DATA2, DATA3, ORDERNO)
values (sys_guid(), 'S000004', '3', '厂站', null, null, 3);

insert into sys_dict_tree_data (NODEID, PARENTNODEID, CODE, DATA1, DATA2, DATA3, ORDERNO)
values (sys_guid(), 'S000004', '4', '部门', null, null, 4);

commit;
--修改机构负责人字长
alter table sys_org modify principal varchar2(36);

--新增用户岗位字典类型

--新增用户表岗位字段,字典字段
alter table sys_user add workPost varchar2(2);
comment on column SYS_USER.workPost is '工作岗位';

--新增用户是否时系统用户字段
alter table sys_user add isSysUser varchar2(2);
comment on column SYS_USER.isSysUser is '是否时系统用户 1：是、0：否';

--刷新用户表，默认就用户都是系统用户

update sys_user t set t.issysuser='1'; 