create or replace function F_GET_CX_TASK_PERSONS(taskid in varchar2) return varchar2 is
/**
  获取任务参与人员的名称
*/
  Result varchar2(100);
begin
  

	SELECT wm_concat( P.PERSON_NAME) into Result
		FROM BIZ_T_XJ_CX_TASK T, BIZ_T_XJ_CX_TASK_PERSONS P
	 WHERE T.CX_TASK_ID = P.CX_TASK_ID
		 AND T.CX_TASK_ID = taskid
		 ;
  return(Result);
end F_GET_CX_TASK_PERSONS;
/
