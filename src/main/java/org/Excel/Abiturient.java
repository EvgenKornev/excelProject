//Описание: Представляет абитуриента с полями для имени,
// баллов и типа обучения.
package org.Excel;

/**
 * Класс, представляющий абитуриента с полями для имени, баллов и типа обучения.
 *@author Kornev E.A.
 *@version 1.0.0
 */
public class Abiturient {
    private String name;
    private int balls;
    private educationType type;

    /**
     * Метод для получения имени абитуриента.
     *
     * @return Имя абитуриента
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для установки имени абитуриента.
     *
     * @param name Имя абитуриента
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод для получения баллов абитуриента.
     *
     * @return Баллы абитуриента
     */
    public int getBalls() {
        return balls;
    }

    /**
     * Метод для установки баллов абитуриента.
     *
     * @param balls Баллы абитуриента
     */
    public void setBalls(int balls) {
        this.balls = balls;
    }

    /**
     * Метод для получения типа обучения абитуриента.
     *
     * @return Тип обучения абитуриента
     */
    public educationType getType() {
        return type;
    }

    /**
     * Метод для получения типа обучения абитуриента в виде строки (целевик, бюджетник или платник).
     *
     * @return Тип обучения в виде строки
     */
    public String getOuputType() {
        if (type == educationType.TARGET) {
            return "целевик";
        } else if (type == educationType.FREEYER) {
            return "бюджетник";
        } else {
            return "платник";
        }
    }

    /**
     * Метод для установки типа обучения абитуриента.
     *
     * @param type Тип обучения абитуриента
     */
    public void setType(educationType type) {
        this.type = type;
    }

    /**
     * Конструктор класса Abiturient.
     *
     * @param name Имя абитуриента
     * @param balls Баллы абитуриента
     * @param type Тип обучения абитуриента
     */
    public Abiturient(String name, int balls, educationType type) {
        this.name = name;
        this.balls = balls;
        this.type = type;
    }
}


