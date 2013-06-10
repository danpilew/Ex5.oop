package clids.ex5.crosswords;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is a basic implementation of CrosswordShape. 
 * The shape is stored as list of strings.
 * 
 * @author CLIDS
 */
public class MyCrosswordStructure implements CrosswordStructure {
	protected List<String> data = new ArrayList<String>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see CrosswordShape#getHeight()
	 */
	public Integer getHeight() {
		return this.data.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CrosswordShape#getWidth()
	 */
	public Integer getWidth() {
		return (getHeight() != 0) ?  this.data.get(0).length() : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CrosswordShape#getSlotType(CrosswordPosition)
	 */
	public SlotType getSlotType(CrosswordPosition pos) {
		if (pos.getX() >= getWidth() || pos.getX() < 0 || 
				pos.getY() >= getHeight() || pos.getY() < 0)
			return SlotType.FRAME_SLOT;
		
		switch (this.data.get(pos.getY()).charAt(pos.getX())) {
		case '_':
			return SlotType.UNUSED_SLOT;
		default:
			return SlotType.FRAME_SLOT;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CrosswordShape#Load
	 */
	public void load(String textFileName) throws IOException {
		Scanner sc=null;
		try {
			this.data = new ArrayList<String>();
			sc = new Scanner(new FileReader(textFileName));
			while (sc.hasNextLine()) {
				this.data.add(sc.nextLine());
			}
		} finally {
			if (sc!=null) sc.close();
		}
	}

}
