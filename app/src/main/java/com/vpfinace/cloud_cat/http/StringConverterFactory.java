package com.vpfinace.cloud_cat.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class StringConverterFactory extends Converter.Factory {
    private final Gson gson;

    public static StringConverterFactory create() {
        return create(new Gson());
    }

    public static StringConverterFactory create(Gson gson) {
        return new StringConverterFactory(gson);
    }

    private StringConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        //判断响应实体类型是否是我们需要特殊处理的特殊类型(此处以String类型)
        if (type == String.class) {
            //创建xxConverter来 进行特殊转换
            return new MyResponseConverter<String>();
        } else {
            //其它类型我们不处理，返回null就行 会交给后面的解析器来解析
            return null;
        }
    }

    private class MyResponseConverter<T> implements Converter<ResponseBody, T> {
        @Override
        public T convert(ResponseBody value) throws IOException {
            //返回源数据
            String response = value.string();
            return (T)response;
        }
    }
}