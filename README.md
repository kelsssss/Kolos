# Kolos
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-blue.svg)](https://kotlinlang.org/)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-1.10.1-brightgreen)](https://developer.android.com/jetpack/compose)

Это мой главный пет-проект, в котором я демонстрирую свои способности в программировании. Это приложение по трекингу криптовалют, оно получает с помощью api данные, показывает в удобном и интуитивно понятном интерфейсе их польpователю, также есть при нажатии на монету экран деталей. 
Все монеты, добавленные в избранное добавляются в базу данных(с помощью Room), из которой позже отображаются(для быстрого трекинга нужных монет).
Реализованы регистрация/авторизация с помощью Firebase, а также монеты из бд синхронизируются в firestore, который выдает данные по uid пользователя, тем самым у каждого пользователя свой "личный кабинет" с синхронизацией монет, которые лично он добавил в избранное.

## Основные возможности:
- 📈 Отображение актуальных курсов криптовалют (через API)
- ⭐ Система избранного с сохранением в Room Database
- 🔄 Синхронизация избранного между устройствами через Firestore
- 🔐 Авторизация/регистрация через Firebase Authentication
- 📱 Интуитивно понятный интерфейс с Jetpack Compose 

## 🛠 Технологии
- Jetpack Compose
- ViewModel
- Firebase
- Coroutines
- Retrofit
- Room
- Coil
- Dagger hilt
- MpAndroidChart

## 📸 Скриншоты 

| Авторизация | Регистрация | Главный экран |
|-------------|-------------|---------------|
| <img src="https://github.com/user-attachments/assets/a59ef4c8-8b70-43b3-8375-deca5558f4a7" width="200"> | <img src="https://github.com/user-attachments/assets/b1a8d3d2-821f-4a47-bf87-bbf14f52e89b" width="200"> | <img src="https://github.com/user-attachments/assets/bcedf2c4-c375-4da1-8d76-84b274b137e1" width="200"> |

| Поиск | Детали монеты | Избранное |
|-------|---------------|-----------|
| <img src="https://github.com/user-attachments/assets/77217301-fcef-4560-b7af-8d5a596c80c0" width="200"> | <img src="https://github.com/user-attachments/assets/74d1ad91-3c4e-415e-9d77-5cd0e9b26887" width="200"> | <img src="https://github.com/user-attachments/assets/fee51f65-aec8-4629-8ce2-7115bbc888a3" width="200"> |

| Управление аккаунтом | Смена пароля | Удаление аккаунта |
|----------------------|--------------|-------------------|
| <img src="https://github.com/user-attachments/assets/c0364ab9-f724-4ab9-9c38-cbd267c938eb" width="200"> | <img src="https://github.com/user-attachments/assets/82aa7e3a-ed53-40a0-871b-55305feac149" width="200"> | <img src="https://github.com/user-attachments/assets/c304dffe-e779-4875-b3fa-d9da0fe5d5de" width="200"> |

