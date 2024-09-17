package ru.oleg.models;

import java.io.Serializable;

/**
 * Класс оружия
 */
public enum Weapon implements Serializable {
    HEAVY_BOLTGUN,
    GRAV_GUN,
    HEAVY_FLAMER;

    /**
     * @return перечисляет в строке все элементы Enum
     */

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var forms : values()) {
            nameList.append(forms.name()).append("\n");
        }
        return nameList.substring(0, nameList.length() - 1);
    }


}
