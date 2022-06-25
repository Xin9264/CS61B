public class ConditionalsWithBlocks{
	public static void main(String[] args){
		int x = 5;
		if(x<10){
			System.out.println("Ishall increment x by 10.");
			x = x+10;
		}
		if(x<10){
			System.out.println("Ishall increment x by 10.");
			x = x+10;
		}
		System.out.println(x);

	}
}