/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clids.ex5.crosswords;

import clids.ex5.search.BoardMove;

/**
 *
 * @author t7639496
 */
public class CrosswordEntryMove implements CrosswordEntry  {
private String word;
private String defenition;
private CrosswordPosition pos;
    public CrosswordEntryMove(String word, String defenition, CrosswordPosition pos){
        this.pos = pos;
        this.word  = word;
        this.defenition = defenition;
    }
    @Override
    public CrosswordPosition getPosition() {
        return pos;
    }

    @Override
    public String getDefinition() {
        return defenition;
    }

    @Override
    public String getTerm() {
        return word;
    }

    @Override
    public int getLength(){ 
        return word.length();
    }
    
}
