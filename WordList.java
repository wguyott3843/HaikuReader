// First in first out implementation
public class WordList 
{
	private WordListNode m_head;
	private WordListNode m_tail;
	
	public WordList()
	{
		m_head = null;
		m_tail = null;
	}
	
	// Places element into the linked list at the end
	public void insert(Word word)
	{
		WordListNode newNode = new WordListNode(word);
		
		if (m_tail == null)
		{
			m_tail = newNode;
			m_head = newNode;
		}
		else
		{
			m_tail.setNextNode(newNode);
			m_tail = newNode;
		}
	}
	
	// Removes and returns the element at the front of the linked list
	public Word remove()
	{
		Word returnValue = null;
		
		if (m_head != null)
		{
			WordListNode node = m_head;
		
			m_head = m_head.getNextNode();
			
			if (m_head == null)
				m_tail = null;
			
			returnValue = node.getWord();
		}
		
		return returnValue;
	}
}
