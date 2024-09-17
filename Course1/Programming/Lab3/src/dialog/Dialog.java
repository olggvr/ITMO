package dialog;

import interfaces.IEssence;
import properties.HumanState;

public class Dialog {

    public void SayPhraze(IEssence fromPerson, IEssence toPerson, String phraze, HumanState humanState) {
        if(humanState != null){
            System.out.println(fromPerson.getName() + " сказал с " + humanState + " " +
                    toPerson.getName() + ":");
        }else{
            System.out.println(fromPerson.getName() + " сказал " + toPerson.getName() + ":");
        }
        System.out.println("- " + phraze);
    }



}
