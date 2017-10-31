package com.upsoft.system.util;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanUtil {

	/**
	 * @Description <p>获取到对象中属性为null的属性名  </P>
	 * @date 2017年3月21日 下午1:11:08
	 * @author Administrator
	 * @param source
	 * @return 
	 */
	public static String[] getNullPropertyNames(Object source) {
        
		final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
 
        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
           
        	Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

	/**
	 * @Description <p> 拷贝对象非空属性值 </P>
	 * @date 2017年3月21日 下午1:39:21
	 * @author Administrator
	 * @param source
	 * @param target 
	 */
	public static void copyPropertiesIgnoreNull(Object source, Object target) {
     
		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
	
	
	
	/**
	 *  @Description <p> 根据指定的属性值获得对象拷贝是忽略的属性值 </P>
	 * @date 2017年3月21日 下午4:26:56
	 * @author Administrator
	 * @param source
	 * @param neededProperty
	 * @return 
	 */
	public static String[] getIgnorePropertyNames(Object source, String[] neededProperty){
		
		final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        
        List<String> ignoreList = new ArrayList<String>();
        List<String> neededList = (neededProperty != null ? Arrays.asList(neededProperty) : null);
        for (PropertyDescriptor propertyDescriptor : pds) {
        	
        	if(!neededList.contains(propertyDescriptor.getName())){
        	
        		ignoreList.add(propertyDescriptor.getName());
        	}
		}
        String[] result = new String[ignoreList.size()];
		return ignoreList.toArray(result);
	}
	
	/**
	 * @Description <p> 拷贝对象指定的属性值 </P>
	 * @date 2017年3月21日 下午4:26:03
	 * @author Administrator
	 * @param source
	 * @param target
	 * @param neededProperty 
	 */
	public static void copyPropertiesAssigned(Object source, Object target, String[] neededProperty){
		
		BeanUtils.copyProperties(source, target, getIgnorePropertyNames(source,neededProperty));
	}
}
