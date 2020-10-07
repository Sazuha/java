import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Second {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        CaesarEncryption caesar= new CaesarEncryption();
        Md5Encryption md5 = new Md5Encryption();


//凯撒加解密
        System.out.println("加密方法：A.凯撒 B.MD5");
        String firstOption = in.nextLine();
        if("A".equals(firstOption)){
            System.out.println("A.加密 B.解密");
            String secondOption = in.nextLine();
            if("A".equals(secondOption)){
                caesar.encryption();
            }
            if("B".equals(secondOption)){
                caesar.decryption();
            }
        }


//MD5加密
        if("B".equals(firstOption)){
            System.out.println("A.加密 B.解密");
            String secondOption = in.nextLine();
            if("A".equals(secondOption)){
                md5.encryption();
            }
            try {
                if ("B".equals(secondOption)) {
                    md5.decryption();
                }
            }
            catch(UnsupportedOperationException e){
                System.out.println(e.getMessage());
            }//异常处理
        }
    }
}


//接口
interface Encryption{
    abstract void encryption();
    abstract void decryption();
}


//凯撒部分
class CaesarEncryption implements Encryption {
    public int n, i;
    public String str;
    public char word;

    @Override
    public void encryption() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入偏移位数n：");
        n = in.nextInt();
        System.out.println("请输入句子：");
        in.nextLine();
        str = in.nextLine();

        for (i = 0; i < str.length(); ++i) {
            word = str.charAt(i);
            if ('a'<=word&&'z'>=word) {
                word += n % 26;
                if(word>'z'){
                    word -= 26;
                }
            }
            else if('A'<=word&&'Z'>=word){
                word += n % 26;
                if(word>'Z') {
                    word -= 26;
                }
            }
            else if('0'<=word&&'9'>=word){
                word += n % 10;
                if(word>'9'){
                    word -= 10;
                }
            }
            System.out.print(word);
        }
    }

    @Override
    public void decryption() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入偏移位数n：");
        n = in.nextInt();
        System.out.println("请输入句子：");
        in.nextLine();
        str = in.nextLine();

        for (i = 0; i < str.length(); ++i) {
            word = str.charAt(i);
            if ('a'<=word&&'z'>=word) {
                word -= n % 26;
                if(word<'a'){
                    word += 26;
                }
            }
            else if('A'<=word&&'Z'>=word){
                word -= n % 26;
                if(word<'A') {
                    word += 26;
                }
            }
            else if('0'<=word&&'9'>=word){
                word -= n % 10;
                if(word<'0'){
                    word += 10;
                }
            }
            System.out.print(word);
        }
    }
}


//MD5部分
class Md5Encryption implements Encryption{
    public String str;


    @Override
    public void encryption() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入句子：");
        str = in.nextLine();
        try {
            MessageDigest md5= MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            String str = new BigInteger(1, md5.digest()).toString(16);
            System.out.println("加密后：" + str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void decryption() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("MD5 算法无法解密");
    }
}