
public class Word 
{
	private String m_word = null;
	
	public Word(String word)
	{
		m_word = word ;
	}
	
	public int getSyllableCount()
	{
		int syllableCount = countVowels();

		syllableCount -= countConsecutiveVowels();

		if (hasSilentE())
			syllableCount -= 1;

		return syllableCount;
	}
	
	public String getString()
	{
		return m_word;
	}
	
	private int countVowels()
	{
		int vowelCount = 0;
		
		for(int i = 0; i < m_word.length(); i++)
		{
			char ch = m_word.charAt(i);
			if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U')
			{
				vowelCount++;
			}
		}
		return vowelCount;
	}
	
	private int countConsecutiveVowels()
	{
		boolean wasVowel = false;
		int vowelCount = 0;
		
		for(int i = 0; i <m_word.length(); i++)
		{
			char ch = m_word.charAt(i);
			if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U')
			{
				if(wasVowel)
				{
					vowelCount++;
				}
				
				wasVowel = true;
			}
			else
				wasVowel = false;
		}
		
		return vowelCount;
	}
	
	private boolean hasSilentE()
	{
		char lastChar = m_word.charAt(m_word.length() - 1);
		
		if (lastChar == 'e' || lastChar == 'E')
			return true;
		else
			return false;
	}
}
