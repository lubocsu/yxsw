create or replace function F_GET_SB_IS_ATTACH_XJD(sbid in varchar2,xjd_id in varchar2 ) return varchar2 is
  Result varchar2(1);
/**
查询指定的设备是否被关联的巡检点
参数  sbid 设备ID
参数  xjd_id 巡检点  该参数可以为空，不为空的含义是检查指定的设备是否关联到了指定的巡检点下

返回值 1 代表被关联到巡检点,0代表未被关联
*/
tmp integer :=0;
begin
/*	IF sbid = NULL THEN
		sbid = 'AXT';
	END IF;*/
	
  IF xjd_id is NULL THEN
		SELECT
    COUNT(1) INTO TMP
  FROM BIZ_T_XJD_ITEM I,
       BIZ_T_XJD_ITEM_DETAIL D
    WHERE I.XJD_ITEM_ID = D.XJD_ITEM_ID
      AND I.DEL_FLAG ='0' --未删除
      AND D.SBSS_ID = sbid
      AND D.DETAIL_TYPE ='1'
      ;
  ELSE 
      SELECT
    COUNT(1) INTO TMP
  FROM BIZ_T_XJD_ITEM I,
       BIZ_T_XJD_ITEM_DETAIL D
    WHERE I.XJD_ITEM_ID = D.XJD_ITEM_ID
      AND I.DEL_FLAG ='0' --未删除
      AND D.SBSS_ID = sbid
      AND D.DETAIL_TYPE ='1'
      AND I.XJD_ITEM_ID = xjd_id
      ;
  END IF;
	IF TMP >0 THEN
		Result := '1';
	ELSE
		Result :='0';
	END IF;
	
  return(Result);
end F_GET_SB_IS_ATTACH_XJD;
/
