import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyFileReader 
{
	BufferedReader m_bufferedReader;
	WordList m_wordList;
	
	public MyFileReader ()
	{
		m_wordList = new WordList();
	}
	
	public void open(String fileName)
	{
		try
		{
			FileReader fr = new FileReader(fileName);
			m_bufferedReader = new BufferedReader(fr);
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Could not find the file: " + e.toString());
			e.printStackTrace();
			m_bufferedReader = null;
		}
	}
	
	// When readLine returns null we have reached the end of the file.
	public String readLine() 
	{
		String returnValue = null;
		
		try
		{
			if (m_bufferedReader != null)	
				returnValue = m_bufferedReader.readLine();
		}
		catch (IOException e)
		{
			System.err.println("Could not read from text file: " + e.toString());
			e.printStackTrace();
		}

		return returnValue;
	}

	public void close()
	{
		try
		{
			if (m_bufferedReader != null)
			{
				m_bufferedReader.close();
				m_bufferedReader = null;
			}
		}
		catch (IOException e)
		{
			System.err.println("Could not read from text file: " + e.toString());
			e.printStackTrace();
		}
	}
	
	// Return null if we are done.
	// Return empty string if next line does not match.
	public String getHaikuLine(int syllableCount)
	{
		String currentLine = "";
		int currentLineSyllableCount = 0;

		while (true)
		{
			Word currentWord = getNextWord();

			if (currentWord == null)
			{
				currentLine = null;
				break;
			}

			int currentWordSyllableCount = currentWord.getSyllableCount();

			// This word is too big to be the first line of a Haiku.
			if (currentWordSyllableCount > syllableCount)
			{
				currentLine = "";
				break;
			}
			// This is not a word so skip it.
			else if (currentWordSyllableCount <= 0)
			{
				continue;
			}
			else
			{
				currentLineSyllableCount += currentWordSyllableCount;

				if (currentLineSyllableCount > syllableCount)
				{
					currentLine = "";
					break;
				}
				else
				{
					if (currentLine == "")
						currentLine = currentWord.getString();
					else
						currentLine += " " + currentWord.getString();

					if (currentLineSyllableCount == syllableCount)
						break;

					// Otherwise currentLineSyllableCount is less than
					// syllableCount and we can just drop through
					// to continue (repeat the loop).
				}
			}
		}

		return currentLine;
	}
	
	// This method returns null if there are no more words.
	// Strips off leading and trailing spaces; tabs; newlines; etc.
	public Word getNextWord()
	{
		// If the list is out of words nextWord will be null
		Word nextWord = m_wordList.remove();

		if (nextWord == null)
		{
			String inputLine;
			
			try 
			{
				inputLine = m_bufferedReader.readLine();
			} 
			catch (IOException e) 
			{
				System.err.println("IO error: " + e.toString());
				e.printStackTrace();
				inputLine = null;
			}
			
			if (inputLine != null)
				processInputLine(inputLine);
			
			// If the list is out of words next word will be null.
			nextWord = m_wordList.remove();
		}
		
		return nextWord;
	}
	
	private void processInputLine(String inputLine)
	{
		String[] words = inputLine.split(" ");
		
		for (String word : words)
		{
			int firstCharIndex = -1;
			int lastCharIndex = -1;
			char[] wordArray = word.toCharArray();
			
			for(int i = 0; i < wordArray.length; i++)
			{
				if (Character.isLetterOrDigit(wordArray[i]))
				{
					firstCharIndex = i;
					break;
				}
			}
			
			if (firstCharIndex == -1)
				continue;
			
			for (int i = wordArray.length - 1; i > firstCharIndex; i--)
			{
				if (Character.isLetterOrDigit(wordArray[i]))
				{
					lastCharIndex = i;
					break;
				}
			}
			
			if(lastCharIndex == -1)
				lastCharIndex = firstCharIndex;
			
			int count = lastCharIndex - firstCharIndex + 1;
			String realWord = new String(wordArray, firstCharIndex, count);
			
			Word currWord = new Word(realWord);
			
			m_wordList.insert(currWord);
		}
	}
}
