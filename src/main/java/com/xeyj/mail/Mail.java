package com.xeyj.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.xeyj.javaBean.AdditionalRegisterBean;
import com.xeyj.javaBean.ConsultBean;
import com.xeyj.javaBean.DoctorBean;
import com.xeyj.javaBean.GreenRodeBean;
import com.xeyj.javaBean.PatientBean;
import com.xeyj.util.DateUtil;
import com.xeyj.util.IniReader;

public class Mail {

//	private static final String SENDER_EMAIL = "lq@mmedi.cn";
	private static final String INI_FILENAME = "mail.ini";
//	
//	
//	private static final String[] RECEIVER_LIST = new String[]{
//		"zhhm@mmedi.cn",
//		"gzhb@mmedi.cn",
//		"shwh@mmedi.cn",
//		"lkch@mmedi.cn",
//		"wzhq@mmedi.cn",
//		"lq@mmedi.cn",
//		"zhangruyi@mmedi.cn"
//		};
	
	private static String senderAccount,senderPassword;
	private static List<String> receivers;
	
    public static void main(String[] args) throws MessagingException {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        /*
         * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
         * mail.user / mail.from
         */
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.126.com");
        // 发件人的账号
        
        try {
			parseIni();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
        props.put("mail.user", senderAccount);
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", senderPassword);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人(s)
        InternetAddress[] toList = new InternetAddress[receivers.size()];
        
        for(int i=0;i<receivers.size();i++){
        	InternetAddress internetAddress = new InternetAddress();
        	internetAddress.setAddress(receivers.get(i));
        	toList[i]=internetAddress;
        }
        
        message.setRecipients(RecipientType.TO, toList);

////        // 设置抄送
//        InternetAddress cc = new InternetAddress(CC_EMAIL);
//        message.setRecipient(RecipientType.CC, cc);
//
//        // 设置密送，其他的收件人不能看到密送的邮件地址
//        InternetAddress bcc = new InternetAddress("aaaaa@163.com");
//        message.setRecipient(RecipientType.CC, bcc);

        // 设置邮件标题
        message.setSubject("健康雨系统运行数据");

        String content = "";
        try {
        	content = repairData();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
        // 设置邮件的内容体
        
        message.setContent(content, "text/html;charset=UTF-8");

        // 发送邮件
        try {
        	Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    private static void parseIni() throws IOException {
    	IniReader iniReader = new IniReader(INI_FILENAME);
    	senderAccount = iniReader.getValue("sender", "account");
    	senderPassword = iniReader.getValue("sender", "password");
    	receivers = new ArrayList<String>();
    	
    	String receiverStr = iniReader.getValue("receivers", "receivers");
    	String cur = receiverStr;
		while(cur.length()>0){
			if(cur.contains(",")){
				receivers.add(cur.substring(0, cur.indexOf(",")));
				cur=cur.substring(cur.indexOf(",")+1,cur.length());
			}else{
				receivers.add(cur);
				break;
			}
		}
	}
	private static String repairData() throws Exception{
//    	1，医生注册。   
//    	2，患者注册。
//    	3，加号申请。
//    	4，快速通道申请。
//    	5，在线咨询次数。
//    	6，义诊。
    	
    	String doctorData = repairDoctorData();
    	String patientData = repairPatientData();
    	String additionalRegisterData = repairAdditionalRegisterData();
    	String dailyGreenRodeData = repairGreenRodeData(0);
    	String allGreenRodeData = repairGreenRodeData(1);
    	String consultData = repairConsultData();
    	
    	String yesterday = DateUtil.getYesterdayString();
    	String title = "统计时段："+yesterday+" 00:00:00"+"至 "+yesterday+" 23:59:59";
    	
    	return title
    			+doctorData
    			+patientData
    			+additionalRegisterData
    			+dailyGreenRodeData
    			+allGreenRodeData
    			+consultData;
    }
    private static String repairConsultData() throws Exception {
    	ConsultBean bean = new ConsultBean();
    	int dailyCount,total;
		
    	dailyCount = bean.getDailyCount();
    	total = bean.getCount();
    	
    	ArrayList<String> titleList = new ArrayList<String>();
    	titleList.add("日新增");
    	titleList.add("总数");
    	
    	ArrayList<String> contentList = new ArrayList<String>();
    	contentList.add(dailyCount+"");
    	contentList.add(total+"");
    	
    	return constructData("在线咨询数据:",titleList,contentList);
	}
    
    private static String repairGreenRodeData(int queryType) throws Exception {
    	GreenRodeBean bean = new GreenRodeBean();
    	StringBuffer sbZhuanjiahao = new StringBuffer();
    	StringBuffer sbDuoxueke = new StringBuffer();
    	StringBuffer sbChuancihuojian = new StringBuffer();
    	StringBuffer sbAnpaizhuyuan = new StringBuffer();
    	StringBuffer sbAnpaishousu = new StringBuffer();
    	StringBuffer sbOther = new StringBuffer();
    	
    	StringBuffer sbYizhen = new StringBuffer();
    	StringBuffer sbBaichengxing = new StringBuffer();
    	
    	StringBuffer sbTotal = new StringBuffer();

    	bean.getManyCountData(
    			
    			sbZhuanjiahao,sbDuoxueke,
    			sbChuancihuojian,sbAnpaizhuyuan,
    			sbAnpaishousu,sbOther,
    			
    			sbYizhen,sbBaichengxing,
    			sbTotal,queryType
    			);
    	
    	ArrayList<String> titleList = new ArrayList<String>();
    	titleList.add("挂专家号");
    	titleList.add("多学科会诊");
    	titleList.add("穿刺活检");
    	titleList.add("安排住院");
    	titleList.add("安排手术");
    	titleList.add("其他服务");
    	
    	titleList.add("义诊");
    	titleList.add("百城行");
    	 
    	titleList.add("合计");
    	
    	ArrayList<String> contentList = new ArrayList<String>();
    	contentList.add(sbZhuanjiahao.toString());
    	contentList.add(sbDuoxueke.toString());
    	contentList.add(sbChuancihuojian.toString());
    	contentList.add(sbAnpaizhuyuan.toString());
    	contentList.add(sbAnpaishousu.toString());
    	contentList.add(sbOther.toString());
    	
    	contentList.add(sbYizhen.toString());
    	contentList.add(sbBaichengxing.toString());
    	
    	contentList.add(sbTotal.toString());
    	if(0==queryType){
    		return constructData("服务申请数据(日新增):",titleList,contentList);
    	}else{
    		return constructData("服务申请数据(总数):",titleList,contentList);
    	}
	}
    
	private static String repairAdditionalRegisterData() throws Exception {
		AdditionalRegisterBean bean = new AdditionalRegisterBean();
		int dailyCount,total;
		dailyCount = bean.getDailyCount();
    	total = bean.getCount();
    	
    	ArrayList<String> titleList = new ArrayList<String>();
    	titleList.add("日新增");
    	titleList.add("总数");
    	
    	ArrayList<String> contentList = new ArrayList<String>();
    	contentList.add(dailyCount+"");
    	contentList.add(total+"");
    	
    	return constructData("加号数据:",titleList,contentList);
	}
	
	private static String repairDoctorData() throws Exception {
		DoctorBean bean = new DoctorBean();
		int dailyCount,dailyValidCount,total,validTotal;
		
    	dailyCount = bean.getDailyCount();
    	dailyValidCount = dailyCount - bean.getDailyInvalidCount();
    	total = bean.getCount();
    	validTotal = total - bean.getInvalidCount();
    	
    	ArrayList<String> titleList = new ArrayList<String>();
    	titleList.add("认证日新增");
    	titleList.add("日新增");
    	titleList.add("认证总数");
    	titleList.add("总数");
    	
    	ArrayList<String> contentList = new ArrayList<String>();
    	contentList.add(dailyValidCount+"");
    	contentList.add(dailyCount+"");
    	contentList.add(validTotal+"");
    	contentList.add(total+"");
    	
    	return constructData("医生数据:",titleList,contentList);
	} 
	
	private static String repairPatientData() throws Exception {
		PatientBean bean = new PatientBean();
    	int dailyCount,dailyValidCount,total,validTotal;
    	dailyCount = bean.getDailyCount();
    	dailyValidCount = dailyCount - bean.getDailyInvalidCount();
    	total = bean.getCount();
    	validTotal = total - bean.getInvalidCount();
    	
    	ArrayList<String> titleList = new ArrayList<String>();
    	titleList.add("实名日新增");
    	titleList.add("日新增");
    	titleList.add("实名总数");
    	titleList.add("总数");
    	
    	ArrayList<String> contentList = new ArrayList<String>();
    	contentList.add(dailyValidCount+"");
    	contentList.add(dailyCount+"");
    	contentList.add(validTotal+"");
    	contentList.add(total+"");
    	
    	return constructData("患者数据:",titleList,contentList);
	}
	
	private static String constructData(String topic,List<String> titleList,List<String> contentList){
		StringBuilder sb = new StringBuilder();
		
		sb.append("<style type=\"text/css\">.onecentertext-align:center;width:200px;height:50px;}#sebackground-color:#006699 ;padding:20px;color:#FFF;}</style>");
		sb.append("<table border='1'cellspacing=\"0\" cellpadding=\"20\" > ");	

		// 标题
		sb.append("<br><br>"+topic);
		sb.append("<tr id='se'>");
		for (String title : titleList) {
			sb.append("<td class='onecenter'>" + title + "</td>");
		}
		sb.append("</tr>");
		
		// 内容
		sb.append("<tr>");
		for (String content : contentList) {
			sb.append("<td class='onecenter'>" + content + "</td>");
		}
		sb.append("</tr>");

		//结束
		sb.append("</table>");	
		return sb.toString();
	}
}
