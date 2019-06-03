# animalback
Тестовое задание для ГСК Угория. Серверная часть

Тестовое задание на знание RESTful.

Сделать небольшую базу данных (MS SQL/Postgresql):
- Таблица Животные;
- Таблица Цвет шкуры;
- Таблица Вид животного;
- Таблица Местоположение;
- Таблица Регион;

Между таблицами настроить связи и зависимости.

Для сущности животное реализовать API (RESTful) для выполнения CRUD-операций. Будет плюсом, если реализовать UI, любой на ваш выбор.
Кроме этого необходимо организовать поиск животного по региону (возможен множественный выбор), цвету и виду.

Результат разместить на GitHub используя концепцию GitFlow.


<b>Результат разработки бэкэнд-серверной части:</b>
Rest API реализован следующий:
- HTTP GET запрос к /animals возвращает весь список жывотных
- HTTP GET запрос к /animals/1 возвращает жывотного с id 1
- HTTP GET запрос к /search?region=Новосибирск&color=Чёрный&type=Корова возвращает список животных соответствующих критериям поиска
- HTTP POST запрос к /animals с объектом животного в виде JSON создаёт нового животного
- HTTP PUT запрос к /animals с объектом животного в виде JSON обновляет информацию о животном с id взятым из JSONа
- HTTP DELETE запрос к /animals/1 удаляет животного с id 1
