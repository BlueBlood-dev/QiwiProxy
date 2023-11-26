# Информация о команде:
## Monkeys
### 1. Артамонов Сергей
### 2. Вартанов Сурен
### 3. Терентьев Александр
### 4. Хащук Денис

# Ссылка на гугл диск с видео, демонстрирующим работоспособность прототипа:
https://drive.google.com/file/d/1pQPuRQjeHYzz8ku6Yvm4ETdfryAZqB8Q/view?usp=sharing

# Описание структуры проекта:

- `qiwi.hackaton` - главная папка модуля, которая содержит:
  - `controllers` - папка с контроллерами, включающая:
    - `RequestController` - класс контроллера запросов.
    - `repositories` - папка с репозиториями, содержит:
      - `CacheRepository` - интерфейс репозитория кеша.
      - `GuavaCacheRepository` - класс реализации репозитория кеша.
  - `models` - папка с моделями, включает:
    - Классы: `ParametrsDTO`, `Request`, `RequestDTO`, `Response`, `Result`, `URLParam`.
    - `mappers` - папка с классом `RequestMapper`.
  - `services` - папка с сервисами, содержит:
    - Интерфейсы: `CachingService`, `RequestRedirectingService`.
    - Классы: `CachingServiceImpl`, `RequestRedirectingServiceImpl`.
  - `Main` - главный класс приложения.