create or replace function F_FORMAT_TASK_TIME(orgid in varchar2,taskdate in varchar2, starttime in varchar2,endtime in varchar2,type in varchar2 :=1) return varchar2 is
  /**
��ʽ����������Ŀ�ʼ��������ڣ���ͨ���������������ڣ��������Ŀ�ʼʱ��(hhMM)������ʱ��(hhMM)����������ʼ����ʱ�䣨yyyymmddHH24mi��
��Ҫ�Ǵ����������
����  orgid ��վ��ID
����  taskdate �����ʱ��
����  starttime ��ʼʱ�� Ӧ������Դ�ڹ���Ƶ�μ����ݱ��е�ʱ��
����  endtime ����ʱ�� Ӧ������Դ�ڹ���Ƶ�μ����ݱ��е�ʱ��
����  type 1 ���㿪ʼʱ�� 2 �������ʱ��
����ֵ ������Ŀ�ʼʱ������ʱ��
*/
  Result varchar2(200);
  v_taskdate varchar2(32) :='';
	v_plantime    varchar2(100) :='';
begin
	select start_time into v_plantime from BIZ_T_XJ_WORK_GROUP g where g.belong_wsc_id=orgid;
	IF type =1 THEN --˵�����㿪ʼʱ��
		IF starttime ='0000' THEN --˵���ǵڶ�����賿��ʼ�����㿪ʼʱ��Ϊ��ǰ���ڼ�һ��
			SELECT TO_CHAR(TO_DATE(taskdate,'yyyymmdd')+1,'yyyymmdd') INTO v_taskdate FROM DUAL;
      Result :=v_taskdate||starttime||'00';
		ELSIF (starttime <=v_plantime AND endtime <=v_plantime) THEN --��ʼ�����ʱ�䶼С�ڹ������ڵĿ�ʼʱ�䣬˵����ʼ��������ڶ��ǵ��죬���㿪ʼʱ��Ϊ��ǰ���ڼ�һ��
			SELECT TO_CHAR(TO_DATE(taskdate,'yyyymmdd')+1,'yyyymmdd') INTO v_taskdate FROM DUAL;
      Result :=v_taskdate||starttime||'00';
		ELSIF (starttime >=v_plantime AND endtime <=v_plantime) THEN --˵����ʼʱ�仹��ͷһ�죬������ʱ���ǿ�����
			Result :=taskdate||starttime||'00';  --��ʼʱ��
		ELSE
			Result :=taskdate||starttime||'00';  --��ʼʱ��
		END IF;
	ELSE  --�������ʱ��
		IF starttime ='0000' THEN --˵���ǵڶ�����賿��ʼ����ô����ʱ��϶�Ҳ�ǵڶ��죬�������ʱ��Ϊ��ǰ���ڼ�һ��
			SELECT TO_CHAR(TO_DATE(taskdate,'yyyymmdd')+1,'yyyymmdd') INTO v_taskdate FROM DUAL;
      Result :=v_taskdate||endtime||'00';
		ELSIF (starttime <=v_plantime AND endtime <=v_plantime) THEN --��ʼ�����ʱ�䶼С�ڹ������ڵĿ�ʼʱ�䣬˵����ʼ��������ڶ��ǵ��죬�������ʱ��Ϊ��ǰ���ڼ�һ��
			SELECT TO_CHAR(TO_DATE(taskdate,'yyyymmdd')+1,'yyyymmdd') INTO v_taskdate FROM DUAL;
      Result :=v_taskdate||endtime||'00';
		ELSIF (starttime >=v_plantime AND endtime <=v_plantime) THEN --˵����ʼʱ�仹��ͷһ�죬������ʱ���ǿ����ˣ��������ʱ��Ϊ��ǰ���ڼ�һ��
			SELECT TO_CHAR(TO_DATE(taskdate,'yyyymmdd')+1,'yyyymmdd') INTO v_taskdate FROM DUAL;
      Result :=v_taskdate||endtime||'00';
		ELSE
			Result :=taskdate||endtime||'00';  --��ʼʱ��
		END IF;
	END IF;
	
  return(Result);
end F_FORMAT_TASK_TIME;
/
