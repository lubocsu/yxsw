
begin
	for i in 0..1000 loop
		insert into reallist
      (orgcode, tagindex, tagname, tagdec, t, val, important, type, createtime, yxbz, revoketime, site, tltype, unit, lev, alarmmax, alarmmin, py, px)
    values
      (i, i, 'test', 'test', sysdate, 0, 0, 'test', sysdate, 0, sysdate, 'test', 0, 'test', 0, 0, 0, 'test', 'test');
	end loop;
	commit;
end;
/