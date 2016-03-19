
public class WordListNode
{
	private Word m_word;
	private WordListNode m_nextNode;
	
	public WordListNode(Word word)
	{
		m_word = word;
		m_nextNode = null;
	}
	
	public Word getWord()
	{
		return m_word;
	}
	
	public void setNextNode(WordListNode nextNode)
	{
		m_nextNode = nextNode;
	}
	
	public WordListNode getNextNode()
	{
		return m_nextNode;
	}
}
