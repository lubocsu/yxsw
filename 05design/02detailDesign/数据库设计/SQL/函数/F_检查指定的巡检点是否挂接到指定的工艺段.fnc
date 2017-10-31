create or replace function F_XJD_HAVE_ATTACH_TECHNICS(xjditemid in varchar2, technicsid in varchar2) return varchar2 is
  Result varchar2(1);
  /**
��ѯָ����Ѳ����Ƿ��Ѿ���������ָ���Ĺ�����
����  xjditemid Ѳ���ID
����  technicsid ���նε�ID
����ֵ 1 ��������,0����δ������
*/
	tmp integer :=0;
begin
   IF technicsid is NULL THEN
         SELECT
          COUNT(1) INTO TMP
        FROM biz_t_xj_technics_scope_manage I,
             biz_t_xj_technics_scope_att_xj D
          WHERE I.TECHNICS_ID = D.TECHNICS_ID
            AND I.DEL_FLAG ='0' --δɾ��
            AND D.XJD_ITEM_ID = xjditemid
            ;
  ELSE
  SELECT COUNT(*) INTO TMP FROM BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ T WHERE T.XJD_ITEM_ID = xjditemid AND T.TECHNICS_ID = technicsid;
	  END IF;
   IF TMP >0 THEN
    Result := '1';
  ELSE
    Result :='0';
  END IF;
  return(Result);
end F_XJD_HAVE_ATTACH_TECHNICS;
/
