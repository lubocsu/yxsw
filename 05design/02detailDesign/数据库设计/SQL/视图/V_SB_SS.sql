CREATE OR REPLACE VIEW V_SB_SS AS
SELECT B.SB_ID           AS ID,
         B.SB_CODE         AS CODE,
         B.SB_NAME         AS NAME,
         B.BYZD,                      --ÊÇ·ñ±ØÉ¨¶þÎ¬Âë
         B.BELONG_WSC_ID,
         B.BELONG_WSC_NAME,
				 B.DEL_FLAG,
				 '1'               AS SB_OR_SS
    FROM BIZ_T_SB_BASEINFO B
  UNION
  SELECT S.SS_ID AS ID,
         S.CODE,
         S.NAME,
         S.BYZD,
         S.BELONG_WSC_ID,
         S.BELONG_WSC_NAME,
				 TO_CHAR(S.DEL_FLAG) AS DEL_FLAG,
				 '2'               AS SB_OR_SS
    FROM BIZ_T_SS_BASEINFO S
;
