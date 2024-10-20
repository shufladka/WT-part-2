package by.bsuir;

import by.bsuir.domain.*;
import by.bsuir.service.DispatcherService;
import by.bsuir.service.impl.DispatcherServiceImpl;

import java.util.List;

/*
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
    public static void main(String[] args) throws InterruptedException {
        Dock dock1 = new Dock(1, "first dock");
        Dock dock2 = new Dock(2, "second dock");

        Ship ship1 = new Ship(1, "first ship", 1800.0, Priority.LOW, 3000);
        Ship ship2 = new Ship(2, "second ship", 2400.0, Priority.MEDIUM, 4000);
        Ship ship3 = new Ship(3, "third ship", 4800.0, Priority.HIGH, 5000);

        Warehouse warehouse = new Warehouse(1, "first warehouse", 50000.0, 0.0);

        Port port = new Port(1, "first port", List.of(dock1, dock2), warehouse);


        DispatcherService dispatcherService = new DispatcherServiceImpl();

        // Создаем потоки для назначения причалов
        Thread thread1 = new Thread(() -> dispatcherService.assignDockConc(port, ship1));
        Thread thread2 = new Thread(() -> dispatcherService.assignDockConc(port, ship2));
        Thread thread3 = new Thread(() -> dispatcherService.assignDockConc(port, ship3));

        // Запускаем потоки
        thread1.start();
        thread2.start();
        thread3.start();

        // Ждем завершения всех потоков
        thread1.join();
        thread2.join();
        thread3.join();
    }
}