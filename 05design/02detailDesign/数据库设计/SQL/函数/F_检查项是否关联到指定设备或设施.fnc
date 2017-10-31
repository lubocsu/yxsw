create or replace function F_GET_CHECK_ITEM_ATTACHED_SBSS(itemid in varchar2,sbss in varchar2,buid in varchar2) return varchar2 is
  Result varchar2(1);
/**
查询指定的检查项是否已经被关联到指定的设备或设施
参数  itemid 检查项ID
参数  sbss 设备还是设施 1 设备  2 设施
参数  buid 要检查的业务ID 即设备的ID  或设施的ID
返回值 1 代表被关联,0代表未被关联
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
