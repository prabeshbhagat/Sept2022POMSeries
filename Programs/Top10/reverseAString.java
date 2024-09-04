package Top10;

public class reverseAString {

	public static void main(String[] args) {

		String name = "Abcde";
		String temp = "";
		
		for (int i = name.length() - 1; i >= 0; i--) {
			temp = temp + name.charAt(i);
		}
		System.out.println(temp);

	}

}
