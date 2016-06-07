import java.util.Scanner;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class Com {
    private static int number = 0;  //表示答案数量
    int count = 0;   //下文的数组下标
    static String[] str ;  //保存正确答案的字符串数组，为了去除重复

    public static void main(String[] args) {
        Com c = new Com();
        System.out.print("请输入皇后数字n：");
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        int[][] reserve = new int[n][n]; //储存皇后的状态
        str = new String[n*100];
        int sign = 1;
        c.startRun(reserve, n ,sign);
        System.out.println(number);
    }

    public void startRun(int[][] reserve , int n ,int sign){
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                if(reserve[i][j] == 0)
                    reserve[i][j] = 1;  //该点为一个皇后
                else{
                    continue;
                }
                if(sign == n){
                    if(checkAllQuean(reserve,n)){  //对n皇后进行位置判断
                        output(reserve,n);  //一个输出函数，输出n皇后的点
                        System.out.println();
                        number++;
                    }
                }else if(sign < n){
                    startRun(reserve , n ,sign + 1); //进行遍历操作
                }
                reserve[i][j] = 0;
            }
        }
    }

    /*
     * 将确定的解答数组，保存在一个String[]里面，用来避免重复
     * 若重复则返回false
     * 不重复则返回true
     */
    public boolean checkReNumber(int[] x,int [] y , int n){
        String test = null ;
        for(int j = 0; j < n;j++){
            test += x[j]+""+y[j]+"";
        }
        for(String st : str){
            if(st == null)
                continue;
            if(st.equals(test)){
                return false;
            }
        }
        str[count++] = test;
        return true;
    }

    /*
     * 检查两个皇后是否在同一行，同一列，或者同一斜线上
     * 存在返回false
     * 不存在返回true
     */
    public boolean checkTwoQuean(int i , int j , int m ,int n){
        if(i == m)
            return false;
        else if(j == n)
            return false;
        else if(Math.abs((m - i)) == Math.abs((n - j)))
            return false;
        else{
            return true;
        }
    }

    public void output(int[][] reserve , int n){
        for(int k = 0; k < n;k++){
            for(int h = 0;h< n;h++){
                if(reserve[k][h] == 0)
                    continue;
                System.out.print(k+","+h+" ");
            }
        }
    }

    public boolean checkAllQuean(int [][] reserve, int n) {
        int[] x = new int[n];
        int x1 = 0;
        int[] y = new int[n];
        int y1 = 0;
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                if(reserve[i][j] == 1){
                    x[x1++] = i;
                    y[y1++] = j;
                }
            }
        }// 获得所有皇后的点坐标
        for(x1 = 0;x1 < n;x1++){
            for(y1 = 0;y1 < n;y1++){
                if(x1 == y1)
                    continue;
                if(!checkTwoQuean(x[x1],y[x1],x[y1],y[y1])){  //比较每一次n皇后的点点点点坐标
                    return false;
                }
            }
        }
        if(!checkReNumber(x,y,n)){
            return false;
        }
        return true;
    }
}


