public class HaikuReader 
{
	public static void main (String[] args)
	{
			String firstLine = "";
			String secondLine = "";
			String thirdLine = "";
			
			MyFileReader fileReader = new MyFileReader();
			fileReader.open(args[0]);
			
			while(true)
			{
				firstLine = fileReader.getHaikuLine(5);
				
				// If firstLine is null then we are
				// out of data in the input file and
				// we are done.
				if (firstLine == null)
					break;
				else if (firstLine.equals(""))
					continue;
				
				secondLine = fileReader.getHaikuLine(7);
				
				// If second Line is null then we are done.
				if (secondLine == null)
					break;
				// If secondLine is empty then the next set
				// of words was not seven syllables and this
				// is not a haiku and we can move on.
				else if (secondLine.equals(""))
					continue;
				
				thirdLine = fileReader.getHaikuLine(5);
				
				// If third Line is null then we are done.
				if (thirdLine == null)
					break;
				// If thirdLine is empty then the next set
				// of words was not five syllables and this
				// is not a haiku and we can move on.
				else if (thirdLine.equals(""))
					continue;
				
				System.out.println("Haiku found: ");
				System.out.println(firstLine);
				System.out.println(secondLine);
				System.out.println(thirdLine);
				System.out.println("");
			}
			
			fileReader.close();		
	}
}
