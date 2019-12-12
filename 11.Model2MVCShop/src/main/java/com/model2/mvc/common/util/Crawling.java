package com.model2.mvc.common.util;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {

	public static void main(String[] args) {
		// Jsoup�� �̿��ؼ� http://www.cgv.co.kr/movies/ ũ�Ѹ�
				String searchKeyword = "�ĵ�";
				String url = "https://namu.wiki/w/"+searchKeyword; //ũ�Ѹ��� url����
				Document doc = null;        //Document���� �������� ��ü �ҽ��� ����ȴ�

				try {
					doc = Jsoup.connect(url).get();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//select�� �̿��Ͽ� ���ϴ� �±׸� �����Ѵ�. select�� ���ϴ� ���� �������� ���� �߿��� ����̴�.
				Elements element = doc.select("div.wiki-heading-content");    
				System.out.println("��� "+element);
				System.out.println("============================================================");

				//Iterator�� ����Ͽ� �ϳ��� �� ��������
				Iterator<Element> ie1 = element.select("div.wiki-paragraph").iterator();
//				Iterator<Element> ie2 = element.select("strong.title").iterator();
				        
				while (ie1.hasNext()) {
//					System.out.println(ie1.next().text()+"\t"+ie2.next().text());
					System.out.println(ie1.next().text());
				}
				
				System.out.println("============================================================");
			}
		}