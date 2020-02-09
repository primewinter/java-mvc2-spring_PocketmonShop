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
				
				if(j == cityArray[i].getCityDuration()-1 ) {	//�ش� ������ ������ ü�������� ��� (ù��° ���� ����)
					if(cityArray[i].getCityDuration() == 1) {	//== if(j==0) //�ش絵���� ������ ü������ && ü���ϼ�=1  =>>  ������ ���(ù��°ü������=������ü������)
						
						if( i==0 ) {	//ù��° ������ ���
							day.setCityNames(cityArray[i].getCityName());
						}else {			//ù��°�� �ƴ� ������ ù��° ü������ ��� = �տ� ���� �ϳ� �� ����!
							day.setCityNames(day.getCityNames()+", "+cityArray[i].getCityName());
						}
					}else {		//�ش絵���� ������ ü������ && ü���ϼ�!=1  =>  ������ �ƴ� ���
						
						if(i+1 < cityArray.length) {	//�ڿ� ���ð� ���� ���
							day.setCityNames(cityArray[i].getCityName());
							
						}else if( i+1 == cityArray.length){	//���� ���� ���
							//day.setCityNameList(cityNames);
							day.setDate((Timestamp)todayStamp.clone());
							dayNoo++;
							todayStamp.setTime(todayStamp.getTime() + (1000*60*60*24));
							day.setDayNo(dayNoo);
							dayList.add(day);
							day = new Day();
						}
					}
					
				}else if( j < cityArray[i].getCityDuration()-1 && j!=0 ) {	//�ش� ������ ������ ü���� ��.....�̸鼭 ù��° ü������ �ƴ� ���
					//cityNames = cityArray[i].getCityName();
					day.setCityNames(cityArray[i].getCityName());
					day.setDate((Timestamp)todayStamp.clone());
					dayNoo++;
					todayStamp.setTime(todayStamp.getTime() + (1000*60*60*24));
					day.setDayNo(dayNoo);
					dayList.add(day);
					day = new Day();
					day.setCityNames(cityArray[i].getCityName());	//���� ���ÿ��� ���� �����̸� �Ѱ��ֱ� ����....����
				
					
				}else if( j==0 && cityArray[i].getCityDuration() != 1 ) {	//�ش� ������ ù��° ü�������̸鼭 ü���ϼ��� 1���� ū ���=������ �ƴ� ���
					if( i==0 ) {	//ù��° ������ ��� 
						day.setCityNames(cityArray[i].getCityName());

					}else {		//ù��° ���ð� �ƴ� ���
						day.setCityNames(day.getCityNames()+", "+cityArray[i].getCityName());	
						
					}
					day.setDate((Timestamp)todayStamp.clone());
					dayNoo++;
					todayStamp.setTime(todayStamp.getTime() + (1000*60*60*24));
					day.setDayNo(dayNoo);
					dayList.add(day);
					day = new Day();
					day.setCityNames(cityArray[i].getCityName());	//���� ���ÿ��� ���� �����̸� �Ѱ��ֱ� ����....����
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
		
		startDate.setTime(startDate.getTime() + (1000*60*60*24*(planTotalDays-1)) );	//���� �������� = �����������  + (�ѿ����ϼ�-1)
		
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
	
	
	/*		���ڸ� ������ �տ� 0�� �ٿ� ���ڸ� ���ڷ� ����� ���� �����... (��,�� �ش�)
	 * 		Timestamp�� toString�ϸ� yyyy-MM-dd ���·� ��µż� substring���� �ϴ� �ذ���
	 * 		if(date != null) {
				Date dateForDay = new Date(date.getTime());		//������ ���� dateForDay
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