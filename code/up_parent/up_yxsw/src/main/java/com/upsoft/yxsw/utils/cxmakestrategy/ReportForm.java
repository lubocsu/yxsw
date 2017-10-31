package com.upsoft.yxsw.utils.cxmakestrategy;

import org.apache.commons.lang3.StringUtils;

import com.upsoft.system.util.DateUtil;

public class ReportForm {
	private static final int N_DATE_LASTEST_ONE_MONTH=1;
	private static final int N_DATE_LASTEST_THREE_MONTH=2;
	private static final int N_DATE_LASTEST_HALF_YEAR=3;
	private static final int N_DATE_LASTEST_YEAR=4;
	private static final int N_DATE_SELFS=5;
	private static final int N_DATE_WEEK=0;
	private static final int N_DATE_MONTH_FIRST_DAY=6;
	private static final int N_DATE_FIRST_WEEK=7;
	private static final int N_DATE_FIRST_YEAR=8;
	/**
	 * 0：本周；   1:近一个月；2：近三个月；3：近半年；4：近一年；5：自定义查询 6:获取本月的第一天
	 */
	private int dateType = 7;
	
	private String startDate = null;
	
	private String endDate = null;

	public int getDateType() {
		return dateType;
	}

	public void setDateType(int dateType) {
		this.dateType = dateType;
	}

	/**
	 * 根据查询中是近一月、三月、半年、一年不同类型，返回开始日期
	 * @return
	 */
	public String getStartDate() {
		switch (dateType) {
		case N_DATE_LASTEST_ONE_MONTH:
			startDate = DateUtil.getLastestOneMonth(DateUtil.DATE_FORMAT_yyyyMMdd);
			break;
		case N_DATE_LASTEST_THREE_MONTH:
			startDate = DateUtil.getLastestThreeMonth(DateUtil.DATE_FORMAT_yyyyMMdd);
			break;
		case N_DATE_LASTEST_HALF_YEAR:
			startDate = DateUtil.getLastestSixMonth(DateUtil.DATE_FORMAT_yyyyMMdd);
			break;
		case N_DATE_LASTEST_YEAR:
			startDate = DateUtil.getLastestYear(DateUtil.DATE_FORMAT_yyyyMMdd);
			break;
		case N_DATE_SELFS:
			startDate = DateUtil.stringToString(startDate, DateUtil.DATE_FORMAT_WITHOUT_TIME, DateUtil.DATE_FORMAT_yyyyMMdd);
			break;
		case N_DATE_MONTH_FIRST_DAY:
			startDate=DateUtil.getNowMonthFirstDay(DateUtil.DATE_FORMAT_yyyyMMdd);
			break;
		case N_DATE_WEEK:
		   startDate=DateUtil.getLastWeek(DateUtil.DATE_FORMAT_yyyyMMdd);
		   break;
		case N_DATE_FIRST_WEEK:
			startDate=DateUtil.getFirstWeek(DateUtil.DATE_FORMAT_yyyyMMdd);
			break;
	    case N_DATE_FIRST_YEAR:
	    	startDate=DateUtil.getFirstYear(DateUtil.DATE_FORMAT_yyyyMMdd);
	    	break;
		default:
			break;
		}
		//默认开始日期为近三个月日期
		if(StringUtils.isEmpty(startDate))
			startDate = DateUtil.getFirstWeek(DateUtil.DATE_FORMAT_yyyyMMdd);
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 根据查询中是近一月、三月、半年、一年不同类型，返回结束日期
	 * @return
	 */
	public String getEndDate() {
		switch (dateType) {
		case N_DATE_LASTEST_ONE_MONTH:
			endDate = DateUtil.getCurrentDay(DateUtil.DATE_FORMAT_yyyyMMdd);
			break;
		case N_DATE_LASTEST_THREE_MONTH:
			endDate = DateUtil.getCurrentDay(DateUtil.DATE_FORMAT_yyyyMMdd);
			break;
		case N_DATE_LASTEST_HALF_YEAR:
			endDate = DateUtil.getCurrentDay(DateUtil.DATE_FORMAT_yyyyMMdd);
			break;
		case N_DATE_LASTEST_YEAR:
			endDate = DateUtil.getCurrentDay(DateUtil.DATE_FORMAT_yyyyMMdd);
			break;
		case N_DATE_SELFS:
			endDate = DateUtil.stringToString(endDate, DateUtil.DATE_FORMAT_WITHOUT_TIME, DateUtil.DATE_FORMAT_yyyyMMdd);
			break;
		case N_DATE_MONTH_FIRST_DAY:
			endDate=DateUtil.getCurrentDay(DateUtil.DATE_FORMAT_yyyyMMdd);
			break;
        case N_DATE_WEEK:
        	endDate=DateUtil.getCurrentDay(DateUtil.DATE_FORMAT_yyyyMMdd);
        	break;
        case N_DATE_FIRST_WEEK:
        	endDate=DateUtil.getCurrentDay(DateUtil.DATE_FORMAT_yyyyMMdd);
        	break;
        case N_DATE_FIRST_YEAR:
        	endDate=DateUtil.getCurrentDay(DateUtil.DATE_FORMAT_yyyyMMdd);
		default:
			break;
		}
		//默认结束日期为当前日期
		if(StringUtils.isEmpty(endDate))
			endDate = DateUtil.getCurrentDay(DateUtil.DATE_FORMAT_yyyyMMdd);
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}