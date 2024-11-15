package by.bsuir;

import by.bsuir.service.MenuService;
import by.bsuir.service.impl.MenuServiceImpl;

/**
* Задание: Реализовать диспетчерскую систему, следящую за кораблями в порту.
* Общие требования к заданию:
* · У каждого порта есть склад и причалы.
* · Корабли швартуются к причалам, после чего могут загрузиться-разгрузиться со склада порта.
* · У каждого причала может швартоваться только один корабль, остальные корабли, желающие
* зайти в порт, должны ждать в очереди.
* · Все свои действия корабли логгируют на консоль.
* · Раз в пять секунд диспетчерская система выводит в файл (лог-файл) текущее состояние порта
* (информацию о количестве товаров на складе, корабля, пришвартованных к причалам,
* кораблях, стоящих в очереди на швартовку).
* · * При швартовке корабль указывает порту ее длительность. Диспетчерская система следит за
* длительностью швартовки корабля, и, если та превышает указанное время, логгирует эту
* информацию в отдельный журнал.
* · **При выделении причала кораблю порт учитывает приоритет корабля, важность и срочность
* груза, а также нарушал ли корабль правила стоянки в прошлом.
*
* Решение задачи должно быть представлено в двух вариантах:
* **Первый вариант должен использовать возможности ключевого слова synchronized;
* **Второй вариант – возможности библиотеки java.ul.concurrent
* */
public class Main {

    /**
     * Главный метод программы
     * @param args Входные параметры
     * @throws InterruptedException Обработка ошибок
     */
    public static void main(String[] args) throws InterruptedException {
        MenuService menuService = new MenuServiceImpl();
        menuService.showMainMenu();
    }
}