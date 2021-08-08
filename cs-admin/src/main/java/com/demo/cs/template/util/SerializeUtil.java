package com.demo.cs.template.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * 序列化工具类
 */
public class SerializeUtil {
    
    /**
     * 字节数组转换为字符串
     */
    public static String bytesToString(byte[] bytes) {
        //// 字符串、字节数组转换方式很多，可以根据自己的要求，自定义转换方式
        //转换成hex
        //return org.apache.commons.codec.binary.Hex.encodeHexString(bytes);
        //转换成base64
        return org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
    }
    
    /**
     * 字符串转换为字节数组
     * @param str
     * @return
     */
    public static byte[] stringToByte(String str) throws DecoderException {
        //// 字符串、字节数组转换方式很多，可以根据自己的要求，自定义转换方式
        //转换成hex
        //return org.apache.commons.codec.binary.Hex.decodeHex(str);
        //转换成base64
        return org.apache.commons.codec.binary.Base64.decodeBase64(str);
    }
    
    /**
     * 序列化对象
     * @param obj 序列化对象
     * @return  对象序列化之后的字符串
     */
    public static String serialize(Object obj) throws Exception{
        if(obj!=null) {
            ByteArrayOutputStream bos=null;
            ObjectOutputStream oos=null;
            try {
                bos=new ByteArrayOutputStream();
                oos=new ObjectOutputStream(bos);
                oos.writeObject(obj);
                byte[] bytes = bos.toByteArray();
                return bytesToString(bytes);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("序列化对象失败："+String.valueOf(obj));
            }finally {
                try {
                    if(bos!=null){
                        bos.close();
                    }
                    if(oos!=null){
                        oos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    /**
     * 反序列化对象
     * @param str   反序列化字符串
     * @return      反序列化之后的对象
     */
    public static <T extends Serializable> T deserialize(String str) throws Exception{
        if(StringUtils.isNotEmpty(str)){
            ByteArrayInputStream bai=null;
            ObjectInputStream ois=null;
            try{
                bai=new ByteArrayInputStream(stringToByte(str));
                ois=new ObjectInputStream(bai);
                return (T)ois.readObject();
            }catch (Exception e){
                e.printStackTrace();
                throw new Exception("字符串反序列化对象失败："+String.valueOf(str));
            }finally {
                try {
                    if(ois!=null){
                        ois.close();
                    }
                    if(bai!=null){
                        bai.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    /**
     * 序列化对象（依赖commons-lang3包）
     * @param obj 序列化对象
     * @return  对象序列化之后的字符串
     */
    public static String serializeCommon(Serializable obj) throws Exception{
        if(obj!=null) {
            byte[] bytes = SerializationUtils.serialize(obj);
            return bytesToString(bytes);
        }
        return null;
    }
    
    /**
     * 反序列化对象（依赖commons-lang3包）
     * @param str   反序列化字符串
     * @return      反序列化之后的对象
     */
    public static <T extends Serializable> T deserializeCommon(String str) throws Exception{
        if(StringUtils.isNotEmpty(str)){
            return SerializationUtils.deserialize(stringToByte(str));
        }
        return null;
    }

}
