package com.blog.util;

import java.lang.reflect.Field;

import com.blog.annotation.Validation;
import com.blog.exception.DataException;

/**
 * 
 * @author niuzhiwei
 *         <p>
 *         类的参数控制验证调用
 *         <p>
 *         注意调用此方法，验证类的属性参数是否为空是 需要类的私有属性中加入
 *         <p>
 *         此@DataValidation注解 即可进行类属性 值的空值验证验证
 *         <p>
 *         Integer 数字0不作为空值判断的标准
 */
public class DataValUtil {
	/**
	 * 
	 * @param Class
	 *            <p>
	 *            将类的模板传入,传入方式为(类.getClass())
	 * @param obj
	 *            <p>
	 *            将类的对象传入,传入方式为(类)
	 * @throws DataException
	 */
	public static void dataValidation(Class<?> Class, Object obj) throws DataException {
		if (obj == null || obj.equals("null"))
			throw new DataException("[err]空对象");
		Field[] fields = Class.getDeclaredFields();
		for (Field f : fields) {
			// 获取字段中包含fieldMeta的注解
			Validation meta = f.getAnnotation(Validation.class);
			if (meta == null)
				continue;
			// 对类的参数进行验证
			f.setAccessible(true);// 设置属性是可以访问的
			try {
				Object val = f.get(obj);// 得到属性的值
				String type = f.getType().toString();// 得到此属性的类型
				if (type.endsWith("String")) {
					if (String.valueOf(val) == null || String.valueOf(val).equals("null"))
						throw new DataException("[err]对象" + Class.getName() + "的属性：" + f.getName() + "是空字符串");

				} else if (type.endsWith("Date")) {
					if (String.valueOf(val) == null || String.valueOf(val) == "null")
						throw new DataException("[err]对象" + Class.getName() + "的属性：" + f.getName() + "(不能空)");

				} else if (type.endsWith("Integer") || type.endsWith("int")) {
					if (String.valueOf(val) == null || String.valueOf(val) == "null"
							|| Integer.valueOf(String.valueOf(val)) == 0)
						throw new DataException("[err]对象" + Class.getName() + "的属性：" + f.getName() + "(不能空" + "或不能是0)");
				} else if (type.endsWith("Float") || type.endsWith("float")) {
					if (String.valueOf(val) == null || String.valueOf(val) == "null"
							|| Float.valueOf(String.valueOf(val)) == 0.0)
						throw new DataException("[err]对象" + Class.getName() + "的属性：" + f.getName() + "(不能空"
								+ "或不能是0.0)");
				} else if (type.endsWith("Double") || type.endsWith("double")) {
					if (String.valueOf(val) == null || String.valueOf(val) == "null"
							|| Double.valueOf(String.valueOf(val)) == 0.0)
						throw new DataException("[err][err]对象" + Class.getName() + "的属性：" + f.getName() + "(不能空"
								+ "或不能是0.0)");
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
