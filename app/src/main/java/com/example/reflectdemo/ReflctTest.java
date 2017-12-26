package com.example.reflectdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hasee on 2017/12/26.
 */

public class ReflctTest {
    public static void main(String[] arggs) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        //方式一
        Class<Person> personClass = Person.class;
        //方式二
        Class<Person> aClass = (Class<Person>) Class.forName("com.example.reflectdemo.Person");
        //方式三
        Person person = new Person("ddd",2);
        Class<Person> aClass2 = (Class<Person>) person.getClass();


        //在class中拿field,所有public属性
        Field[] fields = personClass.getFields();
        //在class中拿field,所有属性
        Field[] declaredFields = personClass.getDeclaredFields();
        System.out.println("======================"+ (fields.length>=declaredFields.length));


        Field field = personClass.getDeclaredField("name");
        //设置可访问、可以拿到私有的属性
        field.setAccessible(true);
        field.get(person);
        System.out.println("======================"+  field.get(person));


        Method getName = personClass.getDeclaredMethod("getName");
        getName.setAccessible(true);
        //方法对象调用方式       method.invoke(他所属的对象，方法的形参)
        getName.invoke(person);
        System.out.println("======================"+  getName.invoke(person));


        //通过反射拿到构造器，去构造一个对象
        Constructor<Person> constructor = personClass.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        Person person1 = constructor.newInstance("1510B", 3);
        System.out.println("======================"+  person1.getAge());


        //有默认的构造器才可以用这个方法创建对象
//        Person person2 = personClass.newInstance();
//        System.out.println("======================"+  person2.getAge());



    }
}
