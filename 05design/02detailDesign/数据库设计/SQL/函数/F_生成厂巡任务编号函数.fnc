create or replace function F_GENERATE_TASK_CODE(taskdate in varchar2, wscid in varchar2) return varchar2 is
  /**
���ɳ�Ѳ������ ÿ���orgcode+"_"+yyyymmdd001��ʼ�����㷽ʽΪ���ֵ+1
����  taskdate �����ʱ��
����  wscid ��ˮ��ID

����ֵ �������������
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
		v_num :=substr(V_MAXCODE,instr(V_MAXCODE,'_',-1)+1);--��ȡSWJT_YXSW_20170921002���������
		Result :=   V_ORG_CODE||'_'||(to_number(v_num)+1);
	END IF;
  return(Result);
end F_GENERATE_TASK_CODE;
/
