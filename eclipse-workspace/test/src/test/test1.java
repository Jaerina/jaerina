package test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test1 {
	public static void main(String[] args) {
		try {
			//ダウンロードする対象のURLと文字コード
			//String url = "http://www.example.com";
			Date today = new Date();
			int toDay = today.getDay();
			System.out.println(toDay);
			String url = new String();
			int cnt = 0;
			
			toDay = 5;
			
			switch(toDay) {
			case 1:
			case 4:
				url = "https://www.hpfree.com/takarakuji/";
				cnt = 6;
				break;
			case 2:
				url = "https://www.hpfree.com/nori/";
				cnt = 5;
				break;
			case 5:
				url = "https://www.hpfree.com/takarakuji/loto7.html";
				cnt = 7;
				break;
			default:
				break;
			}
			String charset = "UTF-8";

			if(cnt != 0) {
				//HTMLを取得
				test1 downloader = new test1();
				List<String> contents = downloader.read(url, charset);
				String num = "";
	
				int count = 0;
				//取得したHTMLを出力
				for(String str : contents) {
					if(count < cnt && str.contains("<p style=\"text-align: center\"><span style=\"font-size:18px;\">")) {
						num += str.substring(str.length() - 13,str.length() - 11) + ", ";
						count++;
					}
				}
				num = num.substring(0, num.length() - 2);
				System.out.println(num);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> read(String url,String charset) throws Exception {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			URLConnection conn = new URL(url).openConnection();
			is = conn.getInputStream();
			isr = new InputStreamReader(is,charset);
			br = new BufferedReader(isr);

			ArrayList<String> lineList = new ArrayList<String>();
			String line = null;
			while((line = br.readLine()) != null) {
				lineList.add(line);
			}
			return lineList;
		}finally {
			try {
				br.close();
			}catch(Exception e) {
			}
			try {
				isr.close();
			}catch(Exception e) {
			}
			try {
				is.close();
			}catch(Exception e) {
			}
		}
	}
}