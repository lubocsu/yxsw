
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.upsoft.yxsw.service.BizTXjCxTaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestMain {

//	@Autowired
//	private BizTXjWorkGroupService bizTXjWorkGroupService; // 设置每个厂站的班次主表，

	@Autowired
	private BizTXjCxTaskService bizTXjCxTaskService;
	
	// @Test
	// public void createXJTask(){
	// List<BizTXjWorkGroup> workGroupList = bizTXjWorkGroupService.getList();
	// bizTXjWorkGroupService.saveCXTaskBySchedule(workGroupList);
	// }

	// 下发巡检任务
//	@Test
//	public void distributeXJTask() {
//		bizTXjCxTaskService.updateToDistributeXJTask();
//	}

	// 任务超期
//	@Test
//	public void overTimeXJTask() {
//		bizTXjCxTaskService.updateToOverTimeXJTask();
//	}
}
