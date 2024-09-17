package models.human;

import interfaces.BodyPart;

public class Head extends BodyParts implements BodyPart {
    public Head(String name) {
        super(name);
    }

    public class Eyes{

        private final String name;
        Eyes(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }
        public boolean isEyeDamaged = false;
    }

    public Eyes eyes = new Eyes("глаз");

}
