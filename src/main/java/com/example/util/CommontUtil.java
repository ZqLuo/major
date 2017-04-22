package com.example.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 拆退表处理常用方法
 * @author lzq
 * @date 2016-4-13 上午11:57:00
 */
public class CommontUtil {
	public final static String YES = "1";
	public final static String NO = "0";
	public final static String TICK = "√";
	public final static String STICK = "×";
	
	public static String nvl(Object obj){
		return obj == null ? "":obj+"";
	}
	

	public static Double parseDouble(String vlaue){
		if(StringUtils.isEmpty(vlaue)){
			return null;
		}else{
			return Double.parseDouble(vlaue);
		}
	}
	
	public static Long parseLong(Object value){
		if(value == null || value.equals("")){
			return 0L;
		}
		return Long.parseLong(value+"");
	}
	
	/**
	 * 格式化double类型数值，防止以科学计数法显示
	 * @return
	 */
	public static String formartDoublt(Double value){
		if(value == null){
			return "";
		}
		BigDecimal bigDecimal = new BigDecimal(value + "");
		return bigDecimal.toPlainString();
	}

	public static String dateToStr(Date date,String pattern){
		if(date == null){
			return "";
		}
		SimpleDateFormat spf = new SimpleDateFormat(pattern);
		return spf.format(date);
	}

	public static Date strToDate(String dateStr,String pattern) {
		if(StringUtil.isEmpty(dateStr)){
			return null;
		}
		SimpleDateFormat spf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = spf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * double类型相减，防止精度丢失
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static String subtractDouble(Double d1,Double d2){
		if(d1 == null || d2 == null){
			return "";
		}
		if(d1 != null && d2 != null){
			BigDecimal d1Big = new BigDecimal(Double.toString(d1));
			BigDecimal d2Big = new BigDecimal(Double.toString(d2));
			return Double.parseDouble(d1Big.subtract(d2Big)+"") + "";
		}
		return "";
	}
	
	/**
	 * 生成json字符串（解决fastjson默认生成的字符串不显示空值的问题）
	 * @param object
	 * @return
	 */
	public static String getJsonString(Object object){
		return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
	}
	
	/**
	 * 判断对象中的所有属性值是否都为空
	 * @param object
	 * @param unIncludeAttr（不进行判断的字段名称）
	 * @return
	 */
	public static boolean objectValueIsNull(Object object,List<String> unIncludeAttr){
		if(object == null){
			return true;
		}
		Field[] fields = object.getClass().getDeclaredFields();
		boolean listIsNull = StringUtil.listIsEmpty(unIncludeAttr);
		try {
			for(Field field : fields){
				if(listIsNull || !unIncludeAttr.contains(field.getName())){
					Method method = object.getClass().getMethod(getGetterName(field.getName()));
					Object value = method.invoke(object);
					if(StringUtil.isNotEmpty(value)){
						return false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static String getGetterName(String fieldname) {
		if(fieldname.length() == 1){
			return "get" + fieldname.toUpperCase();
		}
		fieldname = new StringBuffer("get")
				.append(fieldname.substring(0, 1).toUpperCase())
				.append(fieldname.substring(1)).toString();

		return fieldname.toString();
	}
	
	/**
	 * base64字符串转对象
	 * @param base64Str
	 * @return
	 * @throws CtbServiceExciption 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object base64ToObject(String base64Str) {
		try {
        	ByteArrayInputStream bIn = new ByteArrayInputStream(Base64.decodeBase64(base64Str));  
        	ObjectInputStream objIn = new ObjectInputStream(bIn); 
			return objIn.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 对象转base64字符串
	 * @param base64Str
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static String objectToBase64(Object object) throws IOException, ClassNotFoundException{
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		ObjectOutputStream objOut = new ObjectOutputStream(bOut);  
        objOut.writeObject(object); 
        return Base64.encodeBase64String(bOut.toByteArray());
	}
}	
