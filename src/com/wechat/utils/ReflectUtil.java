package com.wechat.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectUtil {
	//获取的一个泛型类类型
	public static Class getSuperClassGenericType(Class clazz,int index) {
		Type genType=clazz.getGenericSuperclass();
		//具有<>符号的变量是参数化类型-ParameterizedType
		if(!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		//返回参数类型
		Type[] params=((ParameterizedType)genType).getActualTypeArguments();
		
		//
		if(index>params.length || index<0) {
			return Object.class;
		}
		
		if(!(params[index] instanceof Class)) {
			return Object.class;
		}
		
		return (Class) params[index];
	}
	
	//获取basedao类似结构的子类传递上来的类型
	//返回T类型的字节码
	//泛型方法，返回的泛型类型等于定义的泛型类�??
	public static <T> Class<T> getSupserGenericType(Class clazz){
		return getSuperClassGenericType(clazz,0);
	}
	
	
	
	
	
	
	
	
	
	
}
