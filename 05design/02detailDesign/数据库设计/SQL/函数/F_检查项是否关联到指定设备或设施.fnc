create or replace function F_GET_CHECK_ITEM_ATTACHED_SBSS(itemid in varchar2,sbss in varchar2,buid in varchar2) return varchar2 is
  Result varchar2(1);
/**
��ѯָ���ļ�����Ƿ��Ѿ���������ָ�����豸����ʩ
����  itemid �����ID
����  sbss �豸������ʩ 1 �豸  2 ��ʩ
����  buid Ҫ����ҵ��ID ���豸��ID  ����ʩ��ID
����ֵ 1 ��������,0����δ������
*/
tmp integer :=0;
begin
/*  IF sbid = NULL THEN
    sbid = 'AXT';
  END IF;*/
    SELECT COUNT(1) INTO TMP FROM BIZ_T_GG_SBSS_ATTACH_BASE B WHERE B.DETAIL_ID = itemid AND B.CODE = buid AND B.SB_OR_SS = sbss;

  IF TMP >0 THEN
    Result := '1';
  ELSE
    Result :='0';
  END IF;

  return(Result);
end F_GET_CHECK_ITEM_ATTACHED_SBSS;
/
