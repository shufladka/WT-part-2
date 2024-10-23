package by.bsuir.service;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Интерфейс "Сервис меню"
 */
public interface MenuService {

    /**
     * Метод для отображения главного меню в консоль
     * @param in Буфер введенного текста с устройства ввода
     * @param out Печать форматированного представления объектов в поток текстового вывода
     */
    void showMainMenu(BufferedReader in, PrintWriter out);
}
