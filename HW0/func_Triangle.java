public class func_Triangle {
    public static void drawTriangle(int n){
        int i = 0;
        int j = 0;
        for(i=0;i<5;i++)
        {
            for(j=0;j<i+1;j++)
            {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void main(String[] args)
    {
        int N = 5;
        drawTriangle(N);
    }
}
