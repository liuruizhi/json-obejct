package com.tools.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//import org.codehaus.jackson.JsonEncoding;
//import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;


/**
 * 
 * 用来处理前端传过来的json
 * 
 * @author DMD
 * 
 * @param <T>
 */
public class JsonOperation<T> {
	private static ObjectMapper objectMapper = null;
	static{
		try{
			objectMapper = new ObjectMapper();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//将获取的json转换成相应的对象
	public static <T> T jsonToBean(String json, Class<T> cl) {
		T result = null;
		try {
			result = objectMapper.readValue(json, cl);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	//将获取的list类型的json还原成list，注：可能存在问题，因为这种list的格式还没定下来
	public static <T> List<T> jsonToList(String json, Class<T> cl){
		List<T> result = null;
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, cl);
			result = objectMapper.readValue(json, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	//将前台需要的对象转换成json格式
	public static <T> String BeanToJson(T t){
		String result = null;
		try {
			result = objectMapper.writeValueAsString(t);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	//将后台list数据转成json格式
	public static <T> String ListToJson(List<T> list){
		String result = null;
		try {
			result = objectMapper.writeValueAsString(list);
//			json = json.substring(1, json.length() - 1);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
//	public static void main(String[] args) {
//		Customer customer = new Customer();
//		customer.setId(45);
//		customer.setEmail("ruizhigege@126.com");
//		customer.setName("name");
//		customer.setPhone("123456789");
//		Customer customers = new Customer();
//		customers.setId(45);
//		customers.setEmail("ruizhigege@126.com");
//		customers.setName("name");
//		customers.setPhone("123456789");
//		List<Customer> list = new ArrayList<Customer>();
//		list.add(customer);
//		list.add(customers);
////		System.out.println(BeanToJson(customer));
//		System.out.println(ListToJson(list));
//		System.out.println(jsonToList(ListToJson(list), Customer.class).get(0).getEmail());
//	}
}
