create or replace function F_XJ_FREQ_SEGMENT_SBSS_ISRPT(freqsegmentid in varchar2, sbssid in varchar2) return varchar2 is
  Result varchar2(1);
  /**
查询指定的班次频率ID下，是否已经有指定的设备或设施id
参数  freqsegmentid 班次频率ID
参数  sbssid 设备或设施ID
返回值 1 代表被关联,0代表未被关联
*/
	tmp integer :=0;
begin
  SELECT COUNT(*) INTO TMP FROM BIZ_T_XJ_FREQ_SEGMENT_SBSS T WHERE T.FREQ_SEGMENT_ID = freqsegmentid AND T.SBSS_ID = sbssid;
	 IF TMP >0 THEN
    Result := '1';
  ELSE
    Result :='0';
  END IF;
  return(Result);
end F_XJ_FREQ_SEGMENT_SBSS_ISRPT;
/
