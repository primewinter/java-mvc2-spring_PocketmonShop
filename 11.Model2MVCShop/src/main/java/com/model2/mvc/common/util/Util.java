package com.model2.mvc.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.model2.mvc.service.domain.City;
import com.model2.mvc.service.domain.Day;


public class Util {

	///Field
	
	///Constructor
		
	///Method
	public static List<Day> cityListToDayList(List<City> cityList) {
		
		List<Day> dayList = new ArrayList<Day>();
		
		City[] cityArray = cityList.toArray(new City[cityList.size()]);
		
		Date today = new Date();
		Timestamp todayStamp = new Timestamp(today.getTime());
		
		int dayNoo = 0;
		Day day = new Day();
		
		for(int i=0; i<cityArray.length; i++) {
			for(int j=0; j<cityArray[i].getCityDuration(); j++) {
				
				if(j == cityArray[i].getCityDuration()-1 ) {	//해당 도시의 마지막 체류일자인 경우 (첫번째 도시 포함)
					if(cityArray[i].getCityDuration() == 1) {	//== if(j==0) //해당도시의 마지막 체류일자 && 체류일수=1  =>>  무박인 경우(첫번째체류일자=마지막체류일자)
						
						if( i==0 ) {	//첫번째 도시인 경우
							day.setCityNames(cityArray[i].getCityName());
						}else {			//첫번째가 아닌 도시의 첫번째 체류일인 경우 = 앞에 도시 하나 더 있음!
							day.setCityNames(day.getCityNames()+", "+cityArray[i].getCityName());
						}
					}else {		//해당도시의 마지막 체류일자 && 체류일수!=1  =>  무박이 아닌 경우
						
						if(i+1 < cityArray.length) {	//뒤에 도시가 남은 경우
							day.setCityNames(cityArray[i].getCityName());
							
						}else if( i+1 == cityArray.length){	//도시 끝인 경우
							//day.setCityNameList(cityNames);
							day.setDate((Timestamp)todayStamp.clone());
							dayNoo++;
							todayStamp.setTime(todayStamp.getTime() + (1000*60*60*24));
							day.setDayNo(dayNoo);
							dayList.add(day);
							day = new Day();
						}
					}
					
				}else if( j < cityArray[i].getCityDuration()-1 && j!=0 ) {	//해당 도시의 마지막 체류일 전.....이면서 첫번째 체류일이 아닌 경우
					//cityNames = cityArray[i].getCityName();
					day.setCityNames(cityArray[i].getCityName());
					day.setDate((Timestamp)todayStamp.clone());
					dayNoo++;
					todayStamp.setTime(todayStamp.getTime() + (1000*60*60*24));
					day.setDayNo(dayNoo);
					dayList.add(day);
					day = new Day();
					day.setCityNames(cityArray[i].getCityName());	//다음 도시에게 현재 도시이름 넘겨주기 위해....세팅
				
					
				}else if( j==0 && cityArray[i].getCityDuration() != 1 ) {	//해당 도시의 첫번째 체류일자이면서 체류일수가 1보다 큰 경우=무박이 아닌 경우
					if( i==0 ) {	//첫번째 도시인 경우 
						day.setCityNames(cityArray[i].getCityName());

					}else {		//첫번째 도시가 아닌 경우
						day.setCityNames(day.getCityNames()+", "+cityArray[i].getCityName());	
						
					}
					day.setDate((Timestamp)todayStamp.clone());
					dayNoo++;
					todayStamp.setTime(todayStamp.getTime() + (1000*60*60*24));
					day.setDayNo(dayNoo);
					dayList.add(day);
					day = new Day();
					day.setCityNames(cityArray[i].getCityName());	//다음 도시에게 현재 도시이름 넘겨주기 위해....세팅
				}
			}
		}

		return dayList;
	}
	
	public static int getDday(Timestamp startDate) {
		
		Date today = new Date();
		
		long diff = startDate.getTime() - today.getTime();		
		int dday = (int)( diff / (1000*60*60*24) + 1 );
		
		return dday;
	}
	
	public static String getEndDate(Timestamp startDate, int planTotalDays) {
		
		startDate.setTime(startDate.getTime() + (1000*60*60*24*(planTotalDays-1)) );	//여행 종료일자 = 여행시작일자  + (총여행일수-1)
		
		return toDateStr(startDate);
	}
	
	

	
	
	public static String toDateStr(Timestamp date) {
		if (date == null)
			return "";
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(new Date(date.getTime()));
		}
	}
	
	

	public static Object getBean(String beanName) {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        return context.getBean(beanName);
    }
	
//	Calendar calendar = Calendar.getInstance();
//	int year = calendar.get(calendar.YEAR);
//	int month = calendar.get(calendar.MONTH);
//	int day = calendar.get(calendar.DAY_OF_MONTH);
	
	
	/*		한자리 숫자의 앞에 0을 붙여 두자리 숫자로 만들기 위한 노오력... (월,일 해당)
	 * 		Timestamp는 toString하면 yyyy-MM-dd 형태로 출력돼서 substring으로 일단 해결함
	 * 		if(date != null) {
				Date dateForDay = new Date(date.getTime());		//요일을 위한 dateForDay
				this.dateString = date.toString().substring(0,10) + " : "+dateForDay.toString().substring(0,3);
			}
	if(date != null) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		String month = ""+(cal.get(cal.MONTH)+1);
		if( cal.get(cal.MONTH) < 9) {
			month = "0"+month;
		}
		
		String day = ""+cal.get(cal.DAY_OF_MONTH);
		if( cal.get(cal.DAY_OF_MONTH) <10 ) {
			day = "0"+day;
		}
		
		this.dateString = cal.get(cal.YEAR)+"-"+month+"-"+day;
	}
	*/
	
	
}