package com.example.reflectlibrary;

import android.app.Activity;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hasee on 2017/12/26.
 */

public class ButtknifeUtils {
    public static void injectView(final Activity activity) throws IllegalAccessException {

        //找到被InjectView注解的控件 2、通过反射拿到注解中的资源 3、通过findViewById把xml中的对象绑定给 被注解修饰的控件
        Class<Activity> aClass = (Class<Activity>) activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();


        //帮助我们findViewById
        for(Field field:declaredFields){

            //该属性是否被InjectView注解修饰
            if(field.isAnnotationPresent(InjectView.class)){
                InjectView annotation = field.getAnnotation(InjectView.class);
                int id = annotation.value();
                View view = activity.findViewById(id);
                //可访问
                field.setAccessible(true);
                //给控件赋值
                field.set(activity,view);
                if(field.isAnnotationPresent(ImitationDeclared.class)){
                    if(view instanceof TextView){
                        ((TextView) view).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                }
            }

        }
        //帮助我们找点击事件的方法
       Method[] declaredMethods = aClass.getDeclaredMethods();
        for(final Method method:declaredMethods){

            //该属性是否被InjectView注解修饰
            if(method.isAnnotationPresent(BindOnclick.class)){

                //获取注解对象，为了拿注解中的属性 id
                BindOnclick annotation = method.getAnnotation(BindOnclick.class);
                int[] ids = annotation.value();
            for(int id :ids){
                View view = activity.findViewById(id);
                //可访问
                method.setAccessible(true);
                //给控件赋值
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {

                            //调用一下被BindOnclick注解修饰的方法
                            method.invoke(activity,v);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            }
        }
    }
}
