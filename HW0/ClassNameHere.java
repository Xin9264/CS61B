public class ClassNameHere {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int n = m.length;
        int max = m[0];
        for(int i=1; i<n; i++){
            if(max<m[i]){
                max = m[i];
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};      
        int max_elm;
        max_elm = max(numbers);
        System.out.println(max_elm);
    }
}