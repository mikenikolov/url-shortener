# 🔗URL Shortener

Shortener API service which gives the possibility to shorten the links or just mask them.
Service also supports accounts, pagination & profile statistic.

## 🎯 Features
- Quick short URL (random chars)
- Custom short URL (custom link)
- Profile statistic & list of links (with pagination)

## 🔨 Technologies used in the project
- Java 11
- Spring Boot | MVC | Security | Data
- Maven 3.8.4
- PostgreSQL
- Hibernate
- Lombok
- JSON Web Token (JWT)

## 🌐 Endpoints

**(❗) need to be authenticated**

| Method   | URL                              | Request Body              | Response Body                                                             | Description                                              |
|----------|----------------------------------|---------------------------|---------------------------------------------------------------------------|----------------------------------------------------------|
| `GET`    | `/{chars}`                       | `-`                       | `-`                                                                       | `Shortened link`                                         |
| `POST`   | `/v1/register`                   | `username, password`      | `id, username`                                                            | `Register a user`                                        |
| `POST`   | `/v1/login`                      | `username, password`      | `owner, issued_at, expired_at, jwt_token`                                 | `Get JWT token (aka Log in)`                             |
| ❗`POST`  | `/v1/short`                      | `original_url`            | `original_url, short_url, timestamp`                                      | `Short link with random chars`                           |
| ❗`!POST` | `/v1/short/custom`               | `original_url, short_url` | `original_url, short_url, timestamp`                                      | `Short link with custom chars`                           |
| ❗`!GET`  | `/v1/profile`                    | `-`                       | `id, username, number_of_urls`                                            | `Profile statistic`                                      |
| ❗`!GET`  | `/v1/profile/urls?page={number}` | `-`                       | `urls, total_pages, current_page, next_page, previous_page, is_last_page` | `List of links shortened by user. First page by default` |

## ❓ How to set up

1. Install JDK 11
2. Clone the newest version of the project
3. Open `/src/main/resources/application.properties` and change environment variables such as
`PGSQL_HOST, PGSQL_PORT, PGSQL_DB` for PostgreSQL database, `DOMAIN_NAME` for redirecting links. 
4. To run the application, navigate to the root directory of the project and run it using the console with the following command

For Windows: `mvnw.cmd spring-boot:run`

For Linux: `./mvnw spring-boot:run`

If you have pre-installed right version of Maven: `mvn spring-boot:run`

## ⁉ How to use (Postman example)

1️⃣: **Register**

❗ Username must be unique, 2-16 chars

❗ Password must be 6-32 chars

![image](https://user-images.githubusercontent.com/101512791/233787031-3c36b43e-6c04-4041-9227-e2498b305c3c.png)

2️⃣: **Authenticate (get JWT token)**

You will get JWT token, so now you have permission to your account and the whole service

❗ Token is available for 60 minutes by default but can be configured in `/src/main/resources/application.properties` > `validity-time` in ms.

![image](https://user-images.githubusercontent.com/101512791/233787055-1fd06847-8555-41b0-9c95-17f924cce02b.png)

3️⃣: **Short URL**

- **Random chars**

Generating short URL with random characters

  ![image](https://user-images.githubusercontent.com/101512791/233785608-b54bf30e-3de1-4ad4-ac85-7562689773d5.png)

- **Custom chars**

Generating short URL with custom characters

❗ Some custom chars are disallowed to avoid confusing the user

  ![image](https://user-images.githubusercontent.com/101512791/233785663-0cdd623c-4dc8-4f55-b730-fef71ab93fc3.png)

4️⃣: **Profile statistic**

Here you can see your ID, username and number of shortened links.

![image](https://user-images.githubusercontent.com/101512791/233785772-0242ce12-b556-4630-affb-4c6754434ac5.png)

5️⃣: **User's URLs**

All your links sorted by timestamp.

![image](https://user-images.githubusercontent.com/101512791/233785790-b7c54bc2-569a-4a36-8a04-2a19a8b12ff5.png)

6️⃣: **Next page with URLs**

By adding `?page={number}` you can switch to another page. As you can see, page supports **pagination,** so 
you have all metadata about a page and redirect links between pages.

![image](https://user-images.githubusercontent.com/101512791/233785810-689cae51-380d-4d3d-b85a-575ad7731f48.png)

7️⃣: **Redirect**

Result of this application, you can use your new link to open your original one.

![image](https://user-images.githubusercontent.com/101512791/233786335-10cc1d00-1865-4813-8ca7-759e139b1cae.png)