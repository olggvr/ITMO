package models.human;

import interfaces.BodyPart;

public abstract class BodyParts implements BodyPart {

    private String name;
    public BodyParts(String name){
        this.name = name;
    }

    @Override
    public String getPartName(){return this.name;}

}
