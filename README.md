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
<p>
Rest API реализован следующий:
  
- HTTP GET запрос к /animals возвращает весь список животных;
- HTTP GET запрос к /animals/1 возвращает животного с id 1;
- HTTP GET запрос к /search?region=Новосибирск&color=Чёрный&type=Корова возвращает список животных соответствующих критериям поиска;
- HTTP POST запрос к /animals с объектом животного в виде JSON создаёт нового животного;
- HTTP PUT запрос к /animals с объектом животного в виде JSON обновляет информацию о животном с id взятым из JSONа;
- HTTP DELETE запрос к /animals/1 удаляет животного с id 1.
  
Архив с дистрибутивом находится по адресу https://github.com/SantAleks/animalback/blob/master/distr/animalback.7z
Для запуска необходимо:
1) распаковать архив;
2) внутри полученной папки в файле application.properties задать параметры подключения к БД Postgresql (url, username, password) и порт на котором должен принимать запросы сервис (server.port);
3) запустить сервис файлом animalback.bat.

<b>Пример работы REST-интерфейса:</b>
<p>
- Запрос всех записей:
<p>  
  <img src="https://github.com/SantAleks/animalback/blob/master/doc/get_all.JPG" alt="Запрос всех записей">
<p> 
- Запрос одной записи:
<p> 
  <img src="https://github.com/SantAleks/animalback/blob/master/doc/get_one.JPG" alt="Запрос одной записи">
<p> 
- Поиск по критериям:
<p> 
  <img src="https://github.com/SantAleks/animalback/blob/master/doc/get_search.JPG" alt="Поиск по критериям">
<p> 
- Создание новой записи:
<p> 
  действие
<p> 
  <img src="https://github.com/SantAleks/animalback/blob/master/doc/Post_action.JPG" alt="Создание новой записи. Действие">
<p> 
  результат
<p> 
  <img src="https://github.com/SantAleks/animalback/blob/master/doc/post_result.JPG" alt="Создание новой записи. Результат">
<p> 
- Обновление записи:
<p> 
  действие
<p> 
  <img src="https://github.com/SantAleks/animalback/blob/master/doc/Put_action.JPG" alt="Обновление записи. Действие">
<p> 
  результат
<p> 
  <img src="https://github.com/SantAleks/animalback/blob/master/doc/put_result.JPG" alt="Обновление записи. Результат">
<p> 
- Удаление записи:
<p> 
  действие
<p> 
  <img src="https://github.com/SantAleks/animalback/blob/master/doc/Delete_action.JPG" alt="Удаление записи. Действие">
<p> 
  результат
<p> 
  <img src="https://github.com/SantAleks/animalback/blob/master/doc/Delete_result.JPG" alt="Удаление записи. Результат">  
