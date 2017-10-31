create or replace function F_GET_SB_IS_ATTACH_XJD(sbid in varchar2,xjd_id in varchar2 ) return varchar2 is
  Result varchar2(1);
/**
��ѯָ�����豸�Ƿ񱻹�����Ѳ���
����  sbid �豸ID
����  xjd_id Ѳ���  �ò�������Ϊ�գ���Ϊ�յĺ����Ǽ��ָ�����豸�Ƿ��������ָ����Ѳ�����

����ֵ 1 ����������Ѳ���,0����δ������
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
      AND I.DEL_FLAG ='0' --δɾ��
      AND D.SBSS_ID = sbid
      AND D.DETAIL_TYPE ='1'
      ;
  ELSE 
      SELECT
    COUNT(1) INTO TMP
  FROM BIZ_T_XJD_ITEM I,
       BIZ_T_XJD_ITEM_DETAIL D
    WHERE I.XJD_ITEM_ID = D.XJD_ITEM_ID
      AND I.DEL_FLAG ='0' --δɾ��
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
