import java.util.ListIterator;

public class LLListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LLList list = new LLList();
		list.addItem(1, 0);
		list.addItem(2, 1);
		list.addItem(3, 2);
		list.addItem(4, 3);
		list.addItem(5, 4);
		System.out.println(list);
		
		LLList rev = reverse(list);
		System.out.println(rev);
	}
	
	public static LLList reverse(LLList list)
	{
		LLList rev = new LLList();

		ListIterator iter = list.iterator();
		while (iter.hasNext())
		{
			rev.addItem(iter.next(),0);	
		}

		return rev;
	}

}
