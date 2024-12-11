## Тесты для API веб-приложения “Еда” (REST-assured & Jackson)
Этот репозиторий содержит набор тестов для автоматизированного тестирования API веб-приложения “Еда”, написанных с использованием REST-assured и Jackson. Тесты проверяют функциональность добавления новых продуктов и корректность возвращаемых данных.

## Функциональность, покрытая тестами:
* Добавление овощей (с примерами: Картофель и Melotria).
* Добавление фруктов (с примерами: Клубника и Mangosteen).
* Проверка корректности возвращаемых данных (название, тип, признак “экзотичности”) при добавлении продуктов.

## Предварительные условия:
* Установлен JDK 17 или выше.
* Установлен Maven (или Gradle).
* Запущен локальный сервер веб-приложения “Еда”, предоставляющий REST API. Адрес API должен быть указан в конфигурационных файлах или коде.
* Установлены зависимости REST-assured и Jackson. См. файл pom.xml (или build.gradle).
  
## Запуск тестов:
* Клонируйте репозиторий.
* Запустите тесты с помощью Maven: mvn test (или соответствующую команду для Gradle).
  
## Структура проекта:
* src/test/java/: Java-код, содержащий тесты, использующие REST-assured и Jackson для сериализации/десериализации JSON.
* src/main/java/pojos/: Java-классы, представляющие структуру данных, возвращаемых API (например, класс Product для представления информации о продукте).
