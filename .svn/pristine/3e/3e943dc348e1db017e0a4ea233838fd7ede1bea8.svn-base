create or replace procedure P_GENERATE_CX_TASK(taskdate in varchar2, wscid in varchar2,rst out varchar2) is
/**
功能说明：对指定单位（污水厂）在指定日期下的厂巡任务进行生成
参数：taskdate 需要生成的任务日期，格式如yyyymmdd，注意：针对一个厂的巡检是跨天的，但对于任务是同一天的。
               比如永川的巡检周期是第一天的早上8：00至第二天的8：00，
               那么针对2017-09-18的任务，在2017-09-18 08：00 至 2017-09-19 08：00 都属于2017-09-18号的任务
参数：wscid 污水长的机构ID
返回值：格式：数字#内容描述；  其中数字为0/1的选项，0代表生成失败，1代表成功，内容描述为结果描述
*/
v_technic_name varchar2(8000) :='';
v_ErrorCode NUMBER;           -- Variable to hold the error message code
v_ErrorText VARCHAR2(3000);    -- Variable to hold the error message text
p_params    VARCHAR2(1000) :=taskdate||'-'||wscid;
p_address   VARCHAR2(36);
p_card      VARCHAR2(36) :='';
v_raise EXCEPTION;
v_hasError varchar2(1) :='0';
v_count INTEGER := 0;
v_wsc_name varchar2(64) :='';
v_dm_sql varchar2(1000) :='';
v_dm_sql2 varchar2(1000) :='';
v_dm_sql3 varchar2(1000) :='';
v_dm_sql4 varchar2(1000) :='';
v_taskuuid    varchar2(36) :='';
v_taskxjuuid  varchar2(36) :='';
v_uuid  varchar2(36) :='';

v_technics varchar2(200) :='';
v_xjd_id varchar2(200) :='';
v_xjd_name varchar2(200) :='';
v_xjd_address varchar2(200) :='';
v_xjd_desc varchar2(200) :='';
v_xjd_rfidcode varchar2(200) :='';

v_taskName varchar2(200) :='';
v_taskDesc varchar2(200) :='';
v_taskCode varchar2(200) :='';
v_taskstarttime varchar2(14) :='';
v_taskendtime varchar2(14) :='';

TYPE I_CURSOR_TYPE IS REF CURSOR;
MY_CURSOR I_CURSOR_TYPE;
MY_CURSOR2 I_CURSOR_TYPE;
MY_CURSOR3 I_CURSOR_TYPE;
MY_CURSOR_GYD I_CURSOR_TYPE;
MY_CURSOR_PRS I_CURSOR_TYPE;
MY_CURSOR_SBSS I_CURSOR_TYPE;


v_task_person BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON%ROWTYPE;
v_segment_sbss BIZ_T_XJ_FREQ_SEGMENT_SBSS%ROWTYPE;
v_sb_baseinfo BIZ_T_SB_BASEINFO%ROWTYPE;
v_ss_baseinfo BIZ_T_SS_BASEINFO%ROWTYPE;

--查询指定单位的巡检频次内容
CURSOR C_XJPL_SQL IS
      SELECT
         S.FREQ_SEGMENT_ID,
         S.FREQ_SEGMENT_NAME,
         S.DETAIL_ID,
         S.DETAIL_NAME,
         S.START_TIME,
         S.END_TIME
         FROM BIZ_T_XJ_FREQ_SEGMENT S
         WHERE
           S.DEL_FLAG ='0'
          AND S.BELONG_WSC_ID = wscid;


begin
   SELECT sys_guid() INTO p_card FROM DUAL;
   p_pro_exe_log(p_params,p_card,'11','开始检查日期格式','','0','','' );

  rst := '0'||'#begin 厂站['||wscid||']在['||taskdate||']的任务生成失败';
  IF taskdate IS NULL OR LENGTH(taskdate)!=8 THEN
    rst := '0'||'#任务日期不能为空或不符合yyyyMMdd格式';
    v_hasError :='1';
    p_pro_exe_log(p_params,p_card,'1101','开始检查日期格式','','1','日期格式有问题','' );
    RETURN;
  END IF;

  p_pro_exe_log(p_params,p_card,'12','开始检查厂站ID','','0','','' );
  IF v_hasError ='0' THEN
    IF wscid IS NULL OR wscid ='' THEN
      rst := '0'||'#所属厂站ID不能为空';
      v_hasError :='1';
      p_pro_exe_log(p_params,p_card,'1201','开始检查厂站ID','','1','厂站ID为空','' );
      RETURN;
    END IF;
  END IF;

  IF v_hasError ='0' THEN
    SELECT COUNT(1) INTO v_count FROM SYS_ORG O WHERE O.ORGID = wscid AND O.ORGTYPEID=3;
    IF V_COUNT =0 THEN
      rst := '0'||'#参数厂站ID['||wscid||']有错误,没有在组织机构中查询到该厂站';
      v_hasError :='1';
      p_pro_exe_log(p_params,p_card,'1202','开始检查厂站ID','','1','机构中没有该厂站ID','' );
      RETURN;
    END IF;
  END IF;

  p_pro_exe_log(p_params,p_card,'13','开始检查是否在BIZ_T_XJ_FREQ_SEGMENT_SBSS设置了巡检内容','','0','','' );
  IF v_hasError ='0' THEN
    SELECT COUNT(1)
      INTO v_count
      FROM BIZ_T_XJ_FREQ_SEGMENT S, BIZ_T_XJ_FREQ_SEGMENT_SBSS SS
     WHERE SS.FREQ_SEGMENT_ID = S.FREQ_SEGMENT_ID
       AND S.DEL_FLAG = '0'
       AND S.BELONG_WSC_ID = wscid;

    IF V_COUNT =0 THEN
      rst := '0'||'#厂站['||wscid||']没有发现设置的巡检频次及巡检内容';
      v_hasError :='1';
      p_pro_exe_log(p_params,p_card,'1301','开始检查是否在BIZ_T_XJ_FREQ_SEGMENT_SBSS设置了巡检内容','','1','没有发现设置巡检内容','' );
      RETURN;
    END IF;
  END IF;

  --检查指定日期的排班是否已经制定
  p_pro_exe_log(p_params,p_card,'14','开始检查是否为当天设置了巡检值班计划','','0','','' );
  IF v_hasError ='0' THEN
    SELECT COUNT(1) into v_count
   FROM BIZ_T_XJ_ZB_PLAN P,
      BIZ_T_XJ_ZB_PLAN_DETAIL D
    WHERE D.ZB_PLAN_ID = P.ZB_PLAN_ID
     AND P.BELONG_WSC_ID = wscid
     AND P.ZB_DATE = taskdate
      ;
    IF V_COUNT =0 THEN
      rst := '0'||'#厂站['||wscid||']在['||taskdate||']的值班计划没有制定，请及时制定';
      v_hasError :='1';
      p_pro_exe_log(p_params,p_card,'1401','开始检查是否为当天设置了巡检值班计划','','1','没有发现设置的巡检值班计划','' );
      RETURN;
    END IF;
  END IF;

  --开始处理自动生成厂巡任务
  p_address :='15';
  select o.ORGNAME into v_wsc_name from SYS_ORG o where o.ORGID=wscid;
  --开始检查指定厂站是否已经生成了当天的任务
  p_pro_exe_log(p_params,p_card,'15','开始检查当天任务是否已经重复生成','','0','','' );
  IF v_hasError ='0' THEN
    SELECT COUNT(1) INTO v_count FROM BIZ_T_XJ_CX_TASK C WHERE C.CX_TASK_DATE = taskdate AND C.BELONG_WSC_ID =wscid ;
    IF V_COUNT >0 THEN
      rst := '1'||'#厂站['||wscid||']在['||taskdate||']的任务已经生成,请勿重复生成';
      v_hasError :='1';
      p_pro_exe_log(p_params,p_card,'1501','开始检查当天任务是否已经重复生成','','1','当天任务已经存在','' );
      RETURN;
    END IF;
  END IF;

  p_address :='16';
  p_pro_exe_log(p_params,p_card,p_address,'开始处理任务生成','','0','','' );
  IF v_hasError ='0' THEN
     --检索厂站的巡检频次
     FOR C_XJPL IN C_XJPL_SQL LOOP
       --查询频次下的工艺段，后面可以根据工艺段查找巡检点进行任务生成 因为每个工艺段有不同的负责人所以不工艺段应该是不同任务
       v_dm_sql3 :=' SELECT SB.TECHNICS_ID FROM BIZ_T_XJ_FREQ_SEGMENT_SBSS SB WHERE SB.FREQ_SEGMENT_ID='''||C_XJPL.FREQ_SEGMENT_ID||''' GROUP BY SB.TECHNICS_ID ';
       p_address :='1601';
       p_pro_exe_log(p_params,p_card,p_address,'开始查询工艺段',v_dm_sql3,'0','','' );
        OPEN MY_CURSOR_GYD FOR v_dm_sql3;
        LOOP
          FETCH MY_CURSOR_GYD INTO v_technics;
          EXIT WHEN MY_CURSOR_GYD%NOTFOUND;

          SELECT sys_guid() INTO v_taskuuid FROM DUAL;
          SELECT M.TECHNICS_NAME INTO v_technic_name FROM BIZ_T_XJ_TECHNICS_SCOPE_MANAGE M WHERE M.TECHNICS_ID=v_technics;
          v_taskName :=taskdate||v_technic_name||'('||C_XJPL.FREQ_SEGMENT_NAME||')厂巡值班任务';
          v_taskDesc :='该任务为'||'['||v_technic_name||']'||C_XJPL.DETAIL_NAME||'('||C_XJPL.FREQ_SEGMENT_NAME||')'||'任务';
          SELECT F_FORMAT_TASK_TIME(wscid,taskdate,C_XJPL.START_TIME,C_XJPL.END_TIME) INTO v_taskstarttime FROM DUAL;
          SELECT F_FORMAT_TASK_TIME(wscid,taskdate,C_XJPL.START_TIME,C_XJPL.END_TIME,'2') INTO v_taskendtime FROM DUAL;
          SELECT F_GENERATE_TASK_CODE(taskdate,wscid) INTO v_taskCode FROM DUAL;

           INSERT INTO BIZ_T_XJ_CX_TASK
         (CX_TASK_ID,
          CX_TASK_DATE,
          CX_TASK_CODE,
          CX_TASK_NAME,
          CX_TASK_DESC,
          CX_TASK_PSTART_TIME,
          CX_TASK_PEND_TIME,
          CX_TASK_TYPE,
          CX_TASK_STATUS,
          IS_OVER_TIME,
          CX_TASK_CAN_DELIV,
          CX_TASK_HAVE_DELIV,
          CX_TASK_GEN_TIME,
          BELONG_WSC_ID,
          BELONG_WSC_NAME)
          VALUES(
              v_taskuuid,
              taskdate,
              v_taskCode,
              v_taskName,
              v_taskDesc,
              v_taskstarttime,
              v_taskendtime,
              '1',
              '0',
              '0',
              '0',
              '0',
              to_char(sysdate,'yyyymmddHH24miss'),
              wscid,
              v_wsc_name
          );
            --根据工艺段查询巡检点
          v_dm_sql2 :='SELECT SB.XJD_ITEM_ID FROM BIZ_T_XJ_FREQ_SEGMENT_SBSS SB WHERE SB.TECHNICS_ID='''||v_technics||''' AND SB.FREQ_SEGMENT_ID='''||C_XJPL.FREQ_SEGMENT_ID||''' GROUP BY SB.XJD_ITEM_ID';
          p_address :='1602';
          p_pro_exe_log(p_params,p_card,p_address,'开始查询巡检点',v_dm_sql2,'0','','' );
          OPEN MY_CURSOR2 FOR v_dm_sql2;
          LOOP
             FETCH MY_CURSOR2 INTO v_xjd_id;
             EXIT WHEN MY_CURSOR2%NOTFOUND;
             --查询巡检点的基本信息
             SELECT I.XJD_ITEM_NAME, I.XJD_ITEM_ADDRESS, I.XJD_ITEM_DESC, I.RFID_CODE
                 INTO v_xjd_name,v_xjd_address,v_xjd_desc,v_xjd_rfidcode
                    FROM BIZ_T_XJD_ITEM I
                         WHERE I.XJD_ITEM_ID = v_xjd_id;

             SELECT sys_guid() INTO v_taskxjuuid FROM DUAL;
             --插入巡检点
             INSERT INTO BIZ_T_XJ_CX_TASK_ITEM (
                    TASK_ITEM_ID,
                    TASK_ID,
                    XJD_ITEM_ID,
                    XJD_ITEM_NAME,
                    XJD_ITEM_ADDRESS,
                    XJD_ITEM_DESC,
                    RFID_CODE,
                    HAVE_COMPLETE
                    )
                VALUES(
                    v_taskxjuuid,
                    v_taskuuid,
                    v_xjd_id,
                    v_xjd_name,
                    v_xjd_address,
                    v_xjd_desc,
                    v_xjd_rfidcode,
                    '0'
                );

             --开始计算并插入巡检点需要关联的设备与设施
             v_dm_sql4 :='SELECT *
                            FROM BIZ_T_XJ_FREQ_SEGMENT_SBSS SB
                           WHERE SB.FREQ_SEGMENT_ID ='''||C_XJPL.FREQ_SEGMENT_ID||'''
                             AND SB.XJD_ITEM_ID ='''||v_xjd_id||'''
                             AND SB.TECHNICS_ID ='''||v_technics||'''';
              p_address :='1603';
              p_pro_exe_log(p_params,p_card,p_address,'开始查询设备设施',v_dm_sql4,'0','','' );
              OPEN MY_CURSOR_SBSS FOR v_dm_sql4;
              LOOP
                FETCH MY_CURSOR_SBSS INTO v_segment_sbss;
                EXIT WHEN MY_CURSOR_SBSS%NOTFOUND;
                IF v_segment_sbss.sbss_type ='1' THEN --代表是设备
                  --从设备表获取数据
                  SELECT count(1) into v_count FROM BIZ_T_SB_BASEINFO T WHERE T.SB_ID = v_segment_sbss.sbss_id AND T.ZY_STATUS='02' AND T.DEL_FLAG='0';
                  IF v_count =1 THEN
                    SELECT * INTO v_sb_baseinfo FROM BIZ_T_SB_BASEINFO T WHERE T.SB_ID = v_segment_sbss.sbss_id AND T.DEL_FLAG='0';
                    SELECT sys_guid() INTO v_uuid FROM DUAL;
                    INSERT INTO BIZ_T_XJ_CX_TASK_ITEM_SBSS(
                           TTASK_ITEM_SBSS_ID,
                           TASK_ITEM_ID,
                           DETAIL_TYPE,
                           SBSS_ID,
                           NAME,
                           MUST_SCAN,
                           HAVE_COMPLETE,
                           OPT_TIME,
                           SF_FAULT

                    )VALUES(
                           v_uuid,v_taskxjuuid,'1',v_sb_baseinfo.sb_id,v_sb_baseinfo.sb_name,NVL(v_sb_baseinfo.byzd,0),'0',
                                to_char(sysdate,'yyyymmddHH24miss'),'0'
                    );
                  END IF;
                ELSE
                  --处理设施
                  SELECT count(1) into v_count FROM BIZ_T_SS_BASEINFO T WHERE T.SS_ID = v_segment_sbss.sbss_id AND T.DEL_FLAG='0';
                  IF v_count =1 THEN
                    SELECT * INTO v_ss_baseinfo FROM BIZ_T_SS_BASEINFO T WHERE T.SS_ID = v_segment_sbss.sbss_id AND T.DEL_FLAG='0';
                    SELECT sys_guid() INTO v_uuid FROM DUAL;
                    INSERT INTO BIZ_T_XJ_CX_TASK_ITEM_SBSS(
                           TTASK_ITEM_SBSS_ID,
                           TASK_ITEM_ID,
                           DETAIL_TYPE,
                           SBSS_ID,
                           NAME,
                           MUST_SCAN,
                           HAVE_COMPLETE,
                           OPT_TIME,
                           SF_FAULT

                    )VALUES(
                           v_uuid,v_taskxjuuid,'2',v_ss_baseinfo.ss_id,v_ss_baseinfo.name,NVL(v_sb_baseinfo.byzd,0),'0',
                                to_char(sysdate,'yyyymmddHH24miss'),'0'
                    );
                  END IF;
                END IF;
              END LOOP;
          END LOOP;

          --插入任务的执行人员---------------------------------------------------
             v_dm_sql4 :='SELECT
                             R.PLAN_DETAIL_PERSON_ID,R.PLAN_DETAIL_ID,R.PERSON_ID,R.PERSON_NAME,R.BYZD
                         FROM BIZ_T_XJ_ZB_PLAN P,
                           BIZ_T_XJ_ZB_PLAN_DETAIL D,
                           BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON R
                         WHERE R.PLAN_DETAIL_ID = D.PLAN_DETAIL_ID
                            AND D.ZB_PLAN_ID = P.ZB_PLAN_ID
                            AND P.ZB_DATE ='''|| taskdate||'''
                            AND D.DETAIL_ID = '''||C_XJPL.DETAIL_ID||'''
                            AND D.TECHNICS_ID = '''||v_technics||''''
                            ;
            -- DBMS_OUTPUT.put_line(v_dm_sql4);
             p_address :='1604';
             p_pro_exe_log(p_params,p_card,p_address,'开始处理任务的人员',v_dm_sql4,'0','','' );
             OPEN MY_CURSOR_PRS FOR v_dm_sql4;
             LOOP
                FETCH MY_CURSOR_PRS INTO v_task_person;
                EXIT WHEN MY_CURSOR_PRS%NOTFOUND;
                --插入人员信息
                SELECT sys_guid() INTO v_uuid FROM DUAL;
                    --DBMS_OUTPUT.put_line(v_task_person.person_name);
                INSERT INTO BIZ_T_XJ_CX_TASK_PERSONS(
                       ID,CX_TASK_ID,PERSON_ID,PERSON_NAME,IS_VALID,IN_CHARGE
                )
                VALUES (
                       v_uuid,v_taskuuid,v_task_person.person_id,v_task_person.person_name,'1','0'
                );
             END LOOP;
             --插入任务的执行人员   结束---------------------------------------------------
        END LOOP;

     END LOOP;

     --生成任务后最后做一次检查，如果生成的任务只有和巡检点，没有相关的设备与设施，则删除该任务
     DELETE FROM BIZ_T_XJ_CX_TASK_ITEM I
     WHERE I.TASK_ID IN (SELECT T.CX_TASK_ID
                           FROM BIZ_T_XJ_CX_TASK T
                          WHERE T.CX_TASK_DATE = taskdate
                            AND T.BELONG_WSC_ID = wscid)
        AND NOT EXISTS(
            SELECT 1 FROM BIZ_T_XJ_CX_TASK_ITEM_SBSS S WHERE S.TASK_ITEM_ID = I.TASK_ITEM_ID
        );
      p_address :='1605';
      p_pro_exe_log(p_params,p_card,p_address,'开始删除没有设备设施的巡检点,影响'||SQL%ROWCOUNT,'','0','','' );
			
     --删除只有任务主信息，而没有巡检点的任务
     DELETE FROM BIZ_T_XJ_CX_TASK TS
      WHERE TS.CX_TASK_DATE = taskdate
        AND TS.BELONG_WSC_ID = wscid
        AND NOT EXISTS (SELECT 1
               FROM BIZ_T_XJ_CX_TASK_ITEM I
              WHERE TS.CX_TASK_ID = I.TASK_ID)
            ;
      p_address :='1606';
      p_pro_exe_log(p_params,p_card,p_address,'开始删除没有巡检点的任务,影响'||SQL%ROWCOUNT,'','0','','' );

      --删除没有任务的人员
      DELETE FROM BIZ_T_XJ_CX_TASK_PERSONS P
       WHERE NOT EXISTS (SELECT 1
                FROM BIZ_T_XJ_CX_TASK T
               WHERE T.CX_TASK_ID = P.CX_TASK_ID);
      p_address :='1607';
      p_pro_exe_log(p_params,p_card,p_address,'开始删除没有任务的人员,影响'||SQL%ROWCOUNT,'','0','','' );
  rst := '1'||'#厂站['||wscid||']在['||taskdate||']的任务生成成功';
  END IF;

 EXCEPTION WHEN OTHERS THEN
   v_ErrorCode := SQLCODE;
    v_ErrorText := SUBSTR(SQLERRM, 1, 1500);
   rst := '0'||'#ErrorCode:'||v_ErrorCode||',ErrorText:'||v_ErrorText;
   p_pro_exe_log(p_params,p_card,p_address,'任务生成过程出错','','1',v_ErrorText,'' );
end P_GENERATE_CX_TASK;
/
