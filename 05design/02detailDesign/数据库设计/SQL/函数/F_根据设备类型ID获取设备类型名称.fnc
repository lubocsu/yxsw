create or replace function F_GET_SB_TYPE_NAME(sbtypeid in varchar2) return varchar2 is
  Result varchar2(2000);
	/**
	*  �����豸����ID��ȡ�豸��������
	**/
begin
  select t.name into Result from biz_t_sb_types t where t.sb_type_id = sbtypeid;
  return(Result);
end F_GET_SB_TYPE_NAME;
/
