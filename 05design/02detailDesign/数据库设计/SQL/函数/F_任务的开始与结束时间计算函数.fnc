create or replace function F_FORMAT_TASK_TIME(orgid in varchar2,taskdate in varchar2, starttime in varchar2,endtime in varchar2,type in varchar2 :=1) return varchar2 is
  /**
格式化计算任务的开始与结束日期，即通过给定的任务日期，与期望的开始时间(hhMM)，结束时间(hhMM)，计算任务开始结束时间（yyyymmddHH24mi）
主要是处理跨天的情况
参数  orgid 厂站的ID
参数  taskdate 任务的时间
参数  starttime 开始时间 应该是来源于工作频次及内容表中的时间
参数  endtime 结束时间 应该是来源于工作频次及内容表中的时间
参数  type 1 计算开始时间 2 计算结束时间
返回值 计算出的开始时间或结束时间
*/
  Result varchar2(200);
  v_taskdate varchar2(32) :='';
	v_plantime    varchar2(100) :='';
begin
	select start_time into v_plantime from BIZ_T_XJ_WORK_GROUP g where g.belong_wsc_id=orgid;
	IF type =1 THEN --说明计算开始时间
		IF starttime ='0000' THEN --说明是第二天的凌晨开始，计算开始时间为当前日期加一天
			SELECT TO_CHAR(TO_DATE(taskdate,'yyyymmdd')+1,'yyyymmdd') INTO v_taskdate FROM DUAL;
      Result :=v_taskdate||starttime||'00';
		ELSIF (starttime <=v_plantime AND endtime <=v_plantime) THEN --开始与结束时间都小于工作周期的开始时间，说明开始与结束日期都是第天，计算开始时间为当前日期加一天
			SELECT TO_CHAR(TO_DATE(taskdate,'yyyymmdd')+1,'yyyymmdd') INTO v_taskdate FROM DUAL;
      Result :=v_taskdate||starttime||'00';
		ELSIF (starttime >=v_plantime AND endtime <=v_plantime) THEN --说明开始时间还是头一天，而结束时间是跨天了
			Result :=taskdate||starttime||'00';  --开始时间
		ELSE
			Result :=taskdate||starttime||'00';  --开始时间
		END IF;
	ELSE  --计算结束时间
		IF starttime ='0000' THEN --说明是第二天的凌晨开始，那么结束时间肯定也是第二天，计算结束时间为当前日期加一天
			SELECT TO_CHAR(TO_DATE(taskdate,'yyyymmdd')+1,'yyyymmdd') INTO v_taskdate FROM DUAL;
      Result :=v_taskdate||endtime||'00';
		ELSIF (starttime <=v_plantime AND endtime <=v_plantime) THEN --开始与结束时间都小于工作周期的开始时间，说明开始与结束日期都是第天，计算结束时间为当前日期加一天
			SELECT TO_CHAR(TO_DATE(taskdate,'yyyymmdd')+1,'yyyymmdd') INTO v_taskdate FROM DUAL;
      Result :=v_taskdate||endtime||'00';
		ELSIF (starttime >=v_plantime AND endtime <=v_plantime) THEN --说明开始时间还是头一天，而结束时间是跨天了，计算结束时间为当前日期加一天
			SELECT TO_CHAR(TO_DATE(taskdate,'yyyymmdd')+1,'yyyymmdd') INTO v_taskdate FROM DUAL;
      Result :=v_taskdate||endtime||'00';
		ELSE
			Result :=taskdate||endtime||'00';  --开始时间
		END IF;
	END IF;
	
  return(Result);
end F_FORMAT_TASK_TIME;
/
