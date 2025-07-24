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



## API Endpoint Veri Beklentileri ve Açıklamaları

### NotificationController

- **POST /notification/send**
  - Body: NotificationRequestDTO (JSON, aşağıda örnek)
  - Açıklama: Tek cihaza bildirim gönderir.
- **POST /notification/send/batch**
  - Body: NotificationRequestDTO (JSON)
  - Açıklama: Birden fazla cihaza toplu bildirim gönderir.
- **POST /notification/send/all**
  - Body: NotificationRequestDTO (JSON)
  - Açıklama: Tüm cihazlara bildirim gönderir.

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
- `channel` ve `templateName` zorunlu, diğer alanlar isteğe bağlıdır.

---

### UserDeviceController

- **GET /device**
  - Açıklama: Tüm kullanıcı cihazlarını listeler.
- **POST /device/register**
  - Body: UserDeviceRegisterRequestDTO (JSON)
  - Açıklama: Yeni bir kullanıcı cihazı kaydeder.
- **GET /device/{id}**
  - Path Variable: id (string)
  - Açıklama: Belirli bir cihazı id ile getirir.
- **DELETE /device/{id}**
  - Path Variable: id (string)
  - Açıklama: Belirli bir cihazı id ile siler.

Kayıt için beklenen JSON:
```json
{
  "ownerName": "string",
  "platform": "PLATFORM_ENUM_DEGERI",
  "fireBaseToken": "string"
}
```

---

### EmailAdressController

- **GET /email**
  - Açıklama: Tüm e-posta adreslerini listeler.
- **POST /email/register**
  - Body: EmailAddressRegisterRequestDTO (JSON)
  - Açıklama: Yeni bir e-posta adresi kaydeder.
- **GET /email/{id}**
  - Path Variable: id (string)
  - Açıklama: Belirli bir e-posta adresini id ile getirir.
- **DELETE /email/{id}**
  - Path Variable: id (string)
  - Açıklama: Belirli bir e-posta adresini id ile siler.

Kayıt için beklenen JSON:
```json
{
  "emailAddress": "string"
}
```

---

### MessageTemplateController

- **GET /templates**
  - Açıklama: Tüm mesaj şablonlarını listeler.
- **POST /templates**
  - Body: MessageTemplateRegisterRequestDTO (JSON)
  - Açıklama: Yeni bir mesaj şablonu kaydeder.
- **GET /templates/{name}**
  - Path Variable: name (string)
  - Açıklama: Belirli bir şablonu isme göre getirir.
- **DELETE /templates/{name}**
  - Path Variable: name (string)
  - Açıklama: Belirli bir şablonu isme göre siler.

Kayıt için beklenen JSON:
```json
{
  "name": "string",
  "title_template": "string",
  "body_template": "string"
}
```

---

### UserTelegramAccountController

- **GET /telegram**
  - Açıklama: Tüm Telegram hesaplarını listeler.
- **POST /telegram/register**
  - Body: UserTelegramAccount (JSON)
  - Açıklama: Yeni bir Telegram hesabı kaydeder.
- **GET /telegram/{id}**
  - Path Variable: id (string)
  - Açıklama: Belirli bir Telegram hesabını id ile getirir.
- **DELETE /telegram/{id}**
  - Path Variable: id (string)
  - Açıklama: Belirli bir Telegram hesabını id ile siler.

Kayıt için beklenen JSON:
```json
{
  "id": "string (opsiyonel, genellikle null)",
  "name": "string",
  "telegramId": 123456789
}
```

---

### LogController

- **GET /logs/{count}**
  - Path Variable: count (int)
  - Açıklama: Son N log kaydını getirir.
- **GET /logs/all**
  - Açıklama: Tüm log kayıtlarını getirir.
- **DELETE /logs/all**
  - Açıklama: Tüm log kayıtlarını siler.

---

> Tüm endpointler için detaylı örnekler ve enum değerleri için ilgili Java dosyalarına bakınız. 
