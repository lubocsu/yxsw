create or replace function F_GENERATE_TASK_CODE(taskdate in varchar2, wscid in varchar2) return varchar2 is
  /**
生成厂巡任务编号 每天从orgcode+"_"+yyyymmdd001开始，计算方式为最大值+1
参数  taskdate 任务的时间
参数  wscid 污水厂ID

返回值 计算出的任务编号
*/
  Result varchar2(36);
	V_COUNT INTEGER :=0;
	V_MAXCODE varchar2(36) :='';
	V_ORG_CODE varchar2(36);
	v_num varchar2(32);
begin
	SELECT ORGCODE INTO V_ORG_CODE FROM SYS_ORG WHERE ORGID=wscid;
  SELECT COUNT(1) INTO V_COUNT FROM BIZ_T_XJ_CX_TASK T WHERE T.BELONG_WSC_ID =wscid AND T.CX_TASK_DATE = taskdate;
	IF V_COUNT <=0 THEN
		Result := V_ORG_CODE||'_'||taskdate||'001';
	ELSE
		SELECT MAX(T.CX_TASK_CODE) INTO V_MAXCODE FROM BIZ_T_XJ_CX_TASK T WHERE T.BELONG_WSC_ID =wscid AND T.CX_TASK_DATE = taskdate;
		v_num :=substr(V_MAXCODE,instr(V_MAXCODE,'_',-1)+1);--截取SWJT_YXSW_20170921002后面的数字
		Result :=   V_ORG_CODE||'_'||(to_number(v_num)+1);
	END IF;
  return(Result);
end F_GENERATE_TASK_CODE;
/
