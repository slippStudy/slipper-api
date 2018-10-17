#Slipper-API

```
$ keytool -genkeypair -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048  -keystore keystore.p12 -validity 3650
```

생성된 keystore.p12 파일을 src/main/resources 에 위치시킨다.


```
$ docker-compose up -d
```