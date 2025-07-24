Notification Application
## Proje Amacı:
mesaj taslakları ve parametreler ile farklı kanallardan otomatikleştirilmiş bildirim gönerimi sağlamak.

## Özellikler
- MongoDB ile veri yönetimi
- SMTP üzerinden e-posta gönderme
- Telegram Bot API entegrasyonu
- Firebase push notification desteği

Veritabanına mobil uygulamadan aldığımız firebase tokenleri ile cihaz kaydı yapabiliriz.
Telegram üzerinden MessagingBot isimli bota mesaj atıldığında backend düzenli olarak telegram GetUpdates endpointten gelen mesajları çeker ve bu mesajlarda yeni bir kullancı varsa kullanıcın chat idsini database'e kaydeder.
Emails endpointi üzerinden databasede kayıtlı emailleri düzenleyebiliriz.
Uygulama "send", "send/all", "send/batch" üzerinde gönderdiğimiz NotificationRequestDTO ile yukarıdaki kanallar vasıtası ile bildirim gönderir.



## Konfigurasyon
Proje klasörüne .env dosyası oluşturup bu template' i doldurabilirsiniz.

#Format: mongodb+srv://<kullanıcı_adı>:<şifre>@<cluster_adı>/<veritabanı_adı>?options
MONGODB_URI=mongodb+srv://<username>:<password>@<cluster-address>/<database-name>?retryWrites=true&w=majority&appName=<app-name>

#E-posta kullanıcı adı
#Projede e-posta gönderimi için kullanılacak e-posta adresi
MAIL_USERNAME=your-email@example.com

#E-posta uygulama şifresi (App Password)
#Google hesabınızda 2FA aktif ise, uygulama şifresi kullanmalısınız
MAIL_PASSWORD=your-app-password

#Telegram Bot API URL'si
#Telegram bot token'ınızı içeren API endpoint URL'si
TELEGRAM_BOT_API_URL=https://api.telegram.org/bot<your-bot-token>

#Firebase servis hesabı kimlik dosyasının yolu
#Firebase Admin SDK kimlik doğrulaması için JSON dosyasının projenizdeki konumu
FIREBASE_CREDENTIALS_PATH=path/to/firebase-adminsdk.json

## API Endpoint Veri Beklentileri

### NotificationController

- **POST /notification/send**
- **POST /notification/send/batch**
- **POST /notification/send/all**

Beklenen JSON:
```json
{
  "deviceTokens": ["string"],
  "deviceIds": ["string"],
  "emails": ["string"],
  "emailIds": ["string"],
  "telegramChatIds": [123456789],
  "channel": "CHANNEL_ENUM_DEGERI",
  "templateName": "string",
  "parameters": { "key": "value" }
}
```
Açıklama:
- `channel` ve `templateName` zorunlu.
- Diğer alanlar isteğe bağlı.
- Her istek kendi channelına göre olan alanı okur diğerleri boş kalabilir.
- send/all rotasında sadece "channel", "templateName", "parameters" okunur

### UserDeviceController

- **POST /device/register**

Beklenen JSON:
```json
{
  "ownerName": "string",
  "platform": "PLATFORM_ENUM_DEGERI",
  "fireBaseToken": "string"
}
```
Açıklama:
- Tüm alanlar zorunlu.

### EmailAdressController

- **POST /email/register**

Beklenen JSON:
```json
{
  "emailAddress": "string"
}
```
Açıklama:
- `emailAddress` zorunlu.


### MessageTemplateController

- **POST /templates**

Beklenen JSON:
```json
{
  "name": "string",
  "title_template": "string",
  "body_template": "string"
}
```
Açıklama:
- Tüm alanlar zorunlu.


### UserTelegramAccountController

- **POST /telegram/register**

Beklenen JSON:
```json
{
  "id": "string (opsiyonel, genellikle null)",
  "name": "string",
  "telegramId": 123456789
}
```
Açıklama:
- `name` ve `telegramId` zorunlu.
- `id` genellikle null gönderilir.


### LogController

- Sadece Notification Loglarını tutar.
- GET ve DELETE endpointleri parametre veya body beklemez.
- Sadece path variable olarak `/logs/{count}` endpointinde `count` (int) beklenir.
