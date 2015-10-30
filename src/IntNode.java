
public class IntNode {
	
	private int val;
	private IntNode next;
	
	public IntNode(IntNode next, int val)
	{
		this.next = next;
		this.val = val;
	}

	public static void main(String[] args) {
		IntNode one = new IntNode(null, 15);
		IntNode two = new IntNode(one, 11);
		IntNode three = new IntNode(two, 3);
		IntNode four = new IntNode(three, 9);
		IntNode five = new IntNode(four, 11);
		IntNode six = new IntNode(five, 5);
		
		IntNode first = removeOdds(six);
		System.out.println(first);
		
		while (first != null)
		{
			System.out.println(first.val);
			first = first.next;
		}

	}
	
	public static IntNode removeOdds(IntNode first)
	{
		if (first == null) return null;

		IntNode trav = first;
		IntNode trail = null;
		while (trav != null)
		{
			if (trav.val % 2 == 0)
			{
				trail = trav;
				trav = trav.next;
			}
			else
			{
				if (trail == null)
				{
					first = trav.next;
				}
				else
				{
					trail.next = trav.next;
				}
				trav = trav.next;
			}
		}
		return first;
	}

}
