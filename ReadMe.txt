
ÖNEMLİ NOT:

* Uygulama Chrome Browser üzerinde çalışacak şekilde tasarlanmıştır.
* Chrome 79 versiyonu için "Drivers" klasörünün içine driver lar eklenmiştir.

Uygulama çalıştırılmadan önce koşum yapılacak makinede yüklü Chrome Browser ın versiyonu kontrol edilmeli,
farklı bir versiyon yüklü ise aşağıdaki link üzerinden uygun versiyona ait driver indirilerek
"Drivers" klasörüne eklenmelidir.
(Mac ve Windows OS için "chromedriver" isminde Linux OS için "chromedriver_linux" isminde driver eklenmelidir.)

Chrome Driver Link: ( https://chromedriver.chromium.org/downloads )


Proje Hakkında Bilgiler

* Proje Java dilinde Maven projesi olarak olusturulmustur.
* Selenium ve TestNG framework u kullanilmistir.
* Projenin temel ayarlarini "src/resources" directory nin altında "config.properties" dosyasi icerisinde yapilmaktadir.

config.properties dosyasindaki alanlar ve aciklamalari:

remote= Proje Remote da mı calisacak? (true,false),
browser= Hangi browser da calisacak? (chrome, ie, firefox),
baseUrl= Projenin URl i,
gridUrl= Remote URL,
reTry= Hata alan senaryo kac defa kossun,
headlessBrowser=HeadlessBrowser ile mi senaryolar kossun (true,false)

* Proje icerisindeli otomasyon senaryolarini calistirmak icin "testng.xml" dosyasina mouse ile sag click yapip " Run " dememiz yeterlidir.
* Kosum sonuclari "test-output" directory nin altinda "AutomationReport.html" ve "CustomizedReport.html" olacak sekilde iki farkli rapor olusmaktadir.



Projeyi Ayaga kaldirmak icin Gerekenler:

* JDK (Java SE Development Kit) ( link: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html),
* Intelli J Idea ( Link: https://www.jetbrains.com/idea/download/#section=mac),
* Maven (https://maven.apache.org/download.cgi),
* Git (https://git-scm.com/downloads)




