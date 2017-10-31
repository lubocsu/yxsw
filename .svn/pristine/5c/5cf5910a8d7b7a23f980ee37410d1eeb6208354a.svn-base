create or replace procedure P_PRO_EXE_LOG(param        in varchar2,
                                          pcard        in varchar2,
                                          address      in varchar2,
                                          execontent   in varchar2,
                                          exesql       in varchar2,
                                          haserror     in varchar2,
                                          errorcontent in varchar2,
                                          remark       in varchar2) is PRAGMA AUTONOMOUS_TRANSACTION;
p_id      VARCHAR2(36) :='';
begin
   SELECT sys_guid() INTO p_id FROM DUAL;
   INSERT INTO BIZ_T_PRO_EXE_LOG(
           id,
             exe_card,
             address,
             exe_content  ,
             exe_sql   ,
             remark   ,
             error_content,
             has_error   ,
             exec_param  ,
             record_time
   )values(
             p_id,
             pcard,
             address,
             execontent,
             exesql,
             remark,
             errorcontent,
             haserror,
             param,
             sysdate
   );
   commit;
    EXCEPTION WHEN OTHERS THEN
      null;

end P_PRO_EXE_LOG;
/
