package clids.ex5.crosswords;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Basic implementation of CrosswordDictionary interface. Based on HashMap
 * @author CLIDS
 */
public class MyCrosswordDictionary implements CrosswordDictionary {

	// Holds dictionary data, maps each term to its matching definition.
	protected Map<String, String> data = new HashMap<String, String>();

	/*
	 * (non-Javadoc)
	 * @see clids.ex5.crosswords.CrosswordDictionary#getTermDefinition(java.lang.String)
	 */
	public String getTermDefinition(String term) {
		return this.data.get(term);
	}

	/*
	 * (non-Javadoc)
	 * @see clids.ex5.crosswords.CrosswordDictionary#getTerms()
	 */
	public Set<String> getTerms() {
		return data.keySet();
	}

	/*
	 * (non-Javadoc)
	 * @see clids.ex5.crosswords.CrosswordDictionary#load(java.lang.String)
	 */
	public void load(String dictionaryFileName) throws IOException {
		HashSet<String> glosCheck = new HashSet<String>();
		int counter = 1;
		String word, glos;
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(dictionaryFileName));
			while (sc.hasNextLine()) {
				String entryLine = sc.nextLine();
				if (entryLine.indexOf(':') != -1) {
					// fetch word of current line
					word = entryLine.substring(0, entryLine.indexOf(':'));

					// Skip untrue words
					if (word.length() < 2)
						continue;

					// fetch definition of current line
					glos = entryLine.substring(entryLine.indexOf(':') + 1);

					// Adding stars to repetitive glosses for convenience
					// If you implement your dictionary you don't have to do it
					while (glosCheck.contains(glos)) glos+="*";

					glosCheck.add(glos);
					data.put(word.toLowerCase(), glos);
	                                counter++;

				} else {
					// Handling gloss-less files. Again, you don't have 
					// to do it as you can assume valid input file.
					// If there is no ":" all glosses represented as numbers
					//word = entryLine;
					//glos = "Dummy" + counter;
				}
				//Ignoring repetitive terms
				//data.put(word.toLowerCase(), glos);
				//counter++;
			}
		} finally {
			if (sc != null)
				sc.close();
		}
	}

}
