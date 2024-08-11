Задание: Java Collections☕
Разработка Сервиса Управления Библиотекой
Цель: Создать сервис LibraryService в рамках приложения для управления библиотекой, используя Java
коллекции.
Основные компоненты:
 Классы Данных:
Book : с полями String title , String author , int year , Long bookId .
User : с полями String name , int age , Long userId .
 Сервис LibraryService:
На вход в конструкторе принимает: List<User> - список всех пользователей, List<Book> - список всех
книг.
Метод getAllBooks() : возвращает список всех книг.
Метод getAllAvailableBooks() : возвращает список доступных книг (не на руках у пользователей).
Метод getUserBooks(Long userId) : возвращает список книг, взятых пользователем.
Метод takeBook(Long userId, Long bookId) : пользователь берет книгу. Нужно проверить, доступна ли
книга.
Метод returnBook(Long userId, Long bookId) : пользователь возвращает книгу.
 Хранение данных:
Используйте подходящие коллекции для хранения информации о книгах и пользователях.
 Тестирование:
Напишите тесты на LibraryService с использованием Junit . Проверьте основные методы и сценарии
сервиса.
