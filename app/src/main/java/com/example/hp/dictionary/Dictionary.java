package com.example.hp.dictionary;

import java.io.Serializable;



/*public class Dictionary implements Serializable {


    String word, meaning;

    public Dictionary(String word, String meaning){

        this.word=word;

        this.meaning = meaning;

    }

    public  String getWord()
    {
        return word;
    }

   public  String getMeaning()
    {
        return meaning;
    }

}*/

public class Dictionary {
    private String Word;
    private String Wordtype;
    private String Defination;

    public Dictionary(String word, String wordtype, String defination) {
        Word = word;
        Wordtype = wordtype;
        Defination = defination;
    }


    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public String getWordtype() {
        return Wordtype;
    }

    public void setWordtype(String wordtype) {
        Wordtype = wordtype;
    }

    public String getDefination() {
        return Defination;
    }

    public void setDefination(String defination) {
        Defination = defination;
    }

}
