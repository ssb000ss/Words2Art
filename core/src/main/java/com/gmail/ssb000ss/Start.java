package com.gmail.ssb000ss;

import com.gmail.ssb000ss.objects.WordList;
import com.gmail.ssb000ss.exceptions.WordException;
import com.gmail.ssb000ss.objects.Question;
import com.gmail.ssb000ss.objects.Word;
import com.gmail.ssb000ss.utils.QuestionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) throws WordException {
        WordList wordList=new WordList(TestWordList());
        QuestionManager questionManager=new QuestionManager(wordList);
        List<Question>questions=questionManager.getQuestions();

        while (true){
            Scanner scanner=new Scanner(System.in);
            for (Question s:questions) {
                System.out.println("Question number :"+s.getIdQuestion());
                System.out.println("How to translate this word? : "+s.getCorrectWord().getWord()+"?");
                System.out.println("__________________________________________________________");
                int i=1;
                List<Word> tempList=new ArrayList<>();
                for (Word w :s.getItems()) {
                    tempList.add(w);
                    System.out.println(i+") "+w.getTranslation());
                    i++;
                }
                System.out.println("Please,enter the correct answer:");
                int a=scanner.nextInt();
                Word answer=new Word();
                switch (a){
                    case 1:
                        answer=tempList.get(0);
                        break;
                    case 2:
                        answer=tempList.get(1);
                        break;
                    case 3:
                        answer=tempList.get(2);
                        break;
                    case 4:
                        answer=tempList.get(3);
                        break;
                    default:
                        System.out.println("Error");
                }
                if(s.getAnswer(answer).isCorrect()){
                    System.out.println("Correctly!!!\n\n\n");
                }else {
                    System.out.println("Wrong!!!\n\n\n");
                }
            }
            break;
        }


        //игра опросов в консоли,
        // для проверки работоспособности ядра без бд


        }

    private static List<Word> TestWordList() {
        List<Word> words=new ArrayList<>();
        words.add(new Word(1,"mama","Mother"));
        words.add(new Word(2,"papa","Father"));
        words.add(new Word(3,"sestra","Sister"));
        words.add(new Word(4,"brat","Brother"));
        words.add(new Word(5,"mashina","Car"));
        words.add(new Word(6,"solnce","Sun"));
        words.add(new Word(7,"voda","Water"));
        words.add(new Word(8,"stol","Table"));
        words.add(new Word(9,"mywka","Mouse"));
        words.add(new Word(10,"lisa","Fox"));
        words.add(new Word(11,"kowka","Cat"));
        words.add(new Word(12,"sobaka","Dog"));
        return words;

    }
}

