# daddy-s-coffee-spring-api

**About api Authentication**

we used _authentication_ for JWT

### **For the First user you can you this dummy data**

-inclued these Deatails manually to database

```
username : firstUsername,
password : $2a$10$PoqDOiCkwqGDeeYqbuUWvOFyvmWrFUc/zKINfBeblTB9HQX.M2Zs2
permissionLevel : admin
imageUrl : ""
```

```
    {
        "username":"firstUsername",
        "password": "123"
    }
```

_-then you can get JWT then you can make a new user_ 


## Note
```
 - Permission level is not used in this api right now. the reason why i inclued to this for future development

 - also i include the permission level and username to jwt token for development purpose

```

>**http Requests user**

1. Log in
![Authentiocation](https://res.cloudinary.com/dj8a0phpt/image/upload/v1608363782/hSenid/ztrymxcjbojgahbtxbvu.png)

2. create user
 ![register User](https://res.cloudinary.com/dj8a0phpt/image/upload/v1608364258/hSenid/zxswq41hnbgyx4mhxj1f.png)
 `http request must attached JWT`

>**http Requests Item**

1. create item
 ![create Item](https://res.cloudinary.com/dj8a0phpt/image/upload/v1608364509/hSenid/ctewr8odb2x4e7z46pjo.png)
 `http request must attached JWT`

2. list all items
  ![list all items](https://res.cloudinary.com/dj8a0phpt/image/upload/v1608365245/hSenid/dgprw80uxqze2jswcj9k.png)

3. get item by id
 ![get item by id](https://res.cloudinary.com/dj8a0phpt/image/upload/v1608365374/hSenid/gb48dr5jbkbjnodi7hjg.png)

4. Delete Item
 ![get item by id](https://res.cloudinary.com/dj8a0phpt/image/upload/v1608365487/hSenid/ev4vwqlvf65vxp44og1g.png)
 `http request must attached JWT`

5. Update Item
 ![Update Item](https://res.cloudinary.com/dj8a0phpt/image/upload/v1608366076/hSenid/b9tjfiwat4jfunhtbsna.png)
 `http request must attached JWT`

>**http Requests Order**

1. create order
 ![Update Item](https://res.cloudinary.com/dj8a0phpt/image/upload/v1608366918/hSenid/xpozp2htnh0kx4sn6vtb.png)

2. view all orders
 ![Update Item](https://res.cloudinary.com/dj8a0phpt/image/upload/v1608367062/hSenid/ooi6e6r8xlrgjqxyxont.png)
 `http request must attached JWT`

3. view order by id
 ![Update Item](https://res.cloudinary.com/dj8a0phpt/image/upload/v1608367217/hSenid/ekl47xjkism9ykuxhhjl.png)
 `http request must attached JWT`

4. order make as complete
 ![Update Item](https://res.cloudinary.com/dj8a0phpt/image/upload/v1608367395/hSenid/rw9orbsiatdtxoicilms.png)
 `http request must attached JWT`



