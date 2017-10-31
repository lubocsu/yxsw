create or replace trigger trigger_REALLIST
  after insert OR UPDATE  on REALLIST 
  for each row
/******************************************************************
功能说明:中控采集实时表数据转历史数据
创建人:hy
创建日期:2017-10-16

<功能修订日志n>：
  修订日期：
  修订人：
修订原因：
  修改内容：
*******************************************************************/
begin
  insert into REALLIST_HIS
  values
    (:new.orgcode,
     :new.tagindex,
     :new.tagname,
     :new.tagdec,
     :new.t,
     :new.val,
     :new.important,
     :new.type,
     :new.createtime,
     :new.yxbz,
     :new.revoketime,
     :new.site,
     :new.tltype,
     :new.unit,
     :new.lev,
     :new.alarmmax,
     :new.alarmmin,
     :new.py,
     :new.px);
end;
