//Описание: Представляет абитуриента с полями для имени,
// баллов и типа обучения.
package org.Excel;

public class Abiturient {
    String name;
    int balls;
    educationType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public educationType getType(){
        return type;
    }
    public String getOuputType() {
        if(type == educationType.TARGET){
            String outputType = "целевик";
            return outputType;
        } else if (type == educationType.FREEYER) {
            String outputType = "бюджетник";
            return outputType;
        }
        else{
            String outputType = "платник";
            return outputType;
        }

    }

    public void setType(educationType type) {
        this.type = type;
    }

    public Abiturient(String name, int balls, educationType type) {
        this.name = name;
        this.balls = balls;
        this.type = type;
    }

}

