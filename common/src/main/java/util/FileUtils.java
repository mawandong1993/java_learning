package util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mawandong
 * @date 2018/3/29 0:44
 */
public class FileUtils {
  
  /**
     * 判断文件是否存在，只能传入文件路径
     *
     * @param filePath
     * @return
     */
    public static boolean fileExists(String filePath){
        if(StringUtils.isEmpty(filePath)){
            return false;
        }
        File file=new File(filePath);
        if(file.exists()){
            if(file.isFile()){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 判断文件夹是否存在，只能传入文件夹路径
     *
     * @param dirPath
     * @return
     */
    public static boolean dirExists(String dirPath){
        if(StringUtils.isEmpty(dirPath)){
            return false;
        }
        File dir=new File(dirPath);
        if(dir.exists()){
            if(dir.isDirectory()){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 创建目录
     *
     * @param path
     */
    public static void pathMkdir(String path) {
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
    }

    /**
     * 创建文件
     *
     * @param path
     */
    public static void createNewFil(String path) throws IOException {
        File file = new File(path);
        if(file.exists()){
            return;
        }
        File fileParent = file.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        file.createNewFile();
    }



    /**
     * 删除文件
     *
     * @param filePath 文件路径
     */
    public static void deleteByPath(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 删除文件批量
     *
     * @param filePathList 文件路径
     */
    public static void deleteByPathBath(List<String> filePathList) {
        for (String path : filePathList) {
            deleteByPath(path);
        }
    }

    /**
     * 递归删除目录下的所有文件，文件夹
     *
     * @param dir 将要删除的文件目录
     */
    public static boolean doDeleteDir(String dir) {
        File file = new File(dir);
        return deleteDir(file);
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 创建zip压缩文件
     *
     * @param filePathList
     * @return
     * @throws ZipException
     */
    public static String createZip(List<String> filePathList, String zipPath) throws ZipException, IOException {
        createNewFil(zipPath);
        ZipFile zip = new ZipFile(zipPath);
        //要紧跟设置编码
        zip.setFileNameCharset("GBK");
        ArrayList<File> list = new ArrayList<>();
        for (String filePath : filePathList) {
            list.add(new File(filePath));
        }
        ZipParameters para = new ZipParameters();
        para.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        para.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        zip.addFiles(list, para);
        return zipPath;
    }

    /**
     * 根据byte数据，生成文件
     * @param data byte数据
     * @param filePath 文件绝对路径
     */
    public static void byte2File(byte[] data, String filePath){
        BufferedOutputStream bos=null;
        FileOutputStream fos=null;
        File file=null;
        try{
            file=new File(filePath);
            File dir=new File(file.getParent());
            //判断文件目录是否存在
            if(!dir.exists() && !dir.isDirectory()){
                dir.mkdirs();
            }

            fos=new FileOutputStream(file);
            bos=new BufferedOutputStream(fos);
            bos.write(data);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if(bos != null){
                    bos.close();
                }
                if(fos != null){
                    fos.close();
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得指定文件的byte数据
     * @param filePath 文件绝对路径
     * @return
     */
    public static byte[] file2Byte(String filePath){
        ByteArrayOutputStream bos=null;
        BufferedInputStream in=null;
        try{
            File file=new File(filePath);
            if(!file.exists()){
                throw new FileNotFoundException("file not exists");
            }
            bos=new ByteArrayOutputStream((int)file.length());
            in=new BufferedInputStream(new FileInputStream(file));
            int bufSize=1024;
            byte[] buffer=new byte[bufSize];
            int len=0;
            while(-1 != (len=in.read(buffer,0,bufSize))){
                bos.write(buffer,0,len);
            }
            return bos.toByteArray();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally{
            try{
                if(in!=null){
                    in.close();
                }
                if(bos!=null){
                    bos.close();
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取文件中的字符串 UTF-8
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) {
        StringBuilder str=new StringBuilder();
        String encoding = "UTF-8";
        try {
            File file = new File(filePath);
            //判断文件是否存在
            if (file.isFile() && file.exists()) {
                //考虑到编码格式
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    str.append(lineTxt);
                }
                read.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    /**
     * 将字符串写入文件 UTF-8
     * @param str
     * @param filePath
     */
    public static void writerFile(String str, String filePath){
        String encoding = "UTF-8";
        try {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath), encoding));
            pw.print(str);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
}
