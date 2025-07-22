# finance-product-test
Rest Api for test task Finance Product >>>
REST API сервис для управления пользователями и переводами денежных средств между ними.

Поддерживает:
- Регистрацию и авторизацию пользователей с JWT-аутентификацией.
- Переводы между пользователями с валидацией баланса.
- Хранение истории транзакций.
- Получение транзакций по пользователю и по интервалу дат.
- Обработку ошибок и кастомные ответы API.
- Liquibase для миграции БД.
- Автоматическая инициализация 3 пользователей через DataLoader.

Технологии
- Java 21
- Spring Boot 3
- Spring Security + JWT
- PostgreSQL 15
- Liquibase
- Docker & Docker Compose
- Lombok
- Hibernate / Spring Data JPA
- Custom Exception Handling + API Responses

Как запустить:
Предварительные требования
- Docker
- Docker Compose
- Git

Клонирование и запуск
bash
git clone https://github.com/Bexaruz2004/finance-product-test.git
cd finance-product-test
docker-compose up --build

API будет доступен по адресу:  
http://localhost:8080

Тестовые пользователи

Создаются автоматически через DataLoader.java

| Email            | Пароль |
|------------------|--------|
| user-1@gmail.com | 1111   |
| user-2@gmail.com | 2222   |
| user-3@gmail.com | 3333   |

Эндпоинты

AuthController
| Метод | URL               | Описание                      |
|-------|-------------------|-------------------------------|
| POST  | /api/auth/register | Регистрация пользователя     |
| POST  | /api/auth/login    | Авторизация, получение JWT   |

UserController
| Метод | URL               | Описание                      |
|-------|-------------------|-------------------------------|
| GET   | /api/users/me     | Получить текущего пользователя (по JWT) |

TransactionController
| Метод | URL                        | Описание                         |
|-------|----------------------------|----------------------------------|
| POST  | /api/transactions/transfer | Перевод между пользователями    |
| GET   | /api/transactions/user/{id} | Транзакции конкретного пользователя |
| GET   | /api/transactions/dates?from=2024-01-01T00:00:00&to=2024-12-31T23:59:59 ( Транзакции по интервалу дат )

Безопасность
Используется JWT-аутентификация.  
Добавляй Authorization: Bearer <accessToken> в заголовки защищённых запросов.

Обработка ошибок
Общий GlobalExceptionHandler покрывает:
- RestException, EntityNotFoundException
- Валидационные ошибки (@Valid)
- IllegalArgumentException


Docker:

Dockerfile
FROM openjdk:21-jdk-slim
WORKDIR /app
RUN apt-get update && apt-get install -y maven
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package -DskipTests
CMD ["java", "-jar", "target/finance-product-0.0.1-SNAPSHOT.jar"]

docker-compose.yml
version: '3.8'

services:
  postgres:
    image: postgres:15
    container_name: finance_postgres
    environment:
      POSTGRES_DB: finance_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 123
    ports:
      - "5433:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data

  app:
    build: .
    container_name: finance_app
    depends_on:
      - postgres
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/finance_db
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: 123
      SPRING_JPA_HIBERNATE_DDL_AUTO: none
      SPRING_LIQUIBASE_ENABLED: true
      SPRING_LIQUIBASE_CHANGE_LOG: classpath:db.changelog/main-changelog.yml
      JWT_ACCESS_SECRET: g72OlrxnZCg0Xgp18z+W5lR5566GnPrHpCNoVNkvhKk=
      JWT_ACCESS_EXPIRATION: 3600000

volumes:
  pgdata:

Автор
Bexruz Nasrullayev  
Тестовое задание по Spring Boot, Backend Developer  
2025
