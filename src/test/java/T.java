/*
    2022/2/19 12:18
    @author 张渭
    Project Name:ssm_crud
     
    theme:
*/
public class T {
    public static void main(String[] args) {
        Integer i1=11;
        Integer i2=11;
        System.out.println(i1==i2);//true
        Integer i3=127;
        Integer i4=127;
        System.out.println(i3==i4);//true
        Integer i5=128;
        Integer i6=128;
        System.out.println(i5==i6);//false

        System.out.println(new Integer(10)==new Integer(10));
    }
}
