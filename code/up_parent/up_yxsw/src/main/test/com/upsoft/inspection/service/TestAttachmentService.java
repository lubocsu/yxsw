package com.upsoft.inspection.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAttachmentService extends TestBase {
	
	@Autowired
	private AttchmentService attchmentService;
	
	@Test
	public void getAttachments (){
		List<Map<String, Object>> a = attchmentService.getAttchmentListByResultid("419B798F2D1F461197262044F8BD0B79");
		System.out.println(a);
	}
}
