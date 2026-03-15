# Movie Hub

**Movie Hub** istifadəçilərə film məlumatlarını idarə etmək, aktyor və rəy əlavə etmək, filmləri axtarmaq və reaktiv şəkildə təqdim etmək imkanı verir. Layihə modular arxitekturaya malikdir və müxtəlif servislər arasında mesajlaşma üçün Kafka istifadə olunur.  

Movie Hub layihəsi praktika və öyrənmək məqsədli yazılıb. Layihə reactive programming prinsiplərinə əsaslanır və microservice-ready dizaynı ilə gələcəkdə digər servislərlə inteqrasiyaya hazırdır.  

---

## Layihənin Məqsədi
- Film məlumatlarının idarə olunmasını avtomatlaşdırmaq  
- Reactive programming prinsiplərini Spring WebFlux üzərində öyrənmək və praktika etmək  
- Kafka ilə event-driven arxitektura təcrübəsi qazanmaq  
- Elasticsearch və Redis ilə sürətli axtarış və cache implementasiyasını görmək  
- Unit və integration testlər vasitəsilə kod keyfiyyətini təmin etmək  
- Modular arxitektura və asinxron microservice dizaynını öyrənmək  

---

## İstifadə Olunan Texnologiyalar

| Texnologiya | Təyinatı |
|------------|-----------|
| Java, Spring Boot, Spring WebFlux | Backend və reactive REST API implementasiyası |
| MongoDB (Reactive) | Əsas database, asinxron CRUD əməliyyatlar üçün |
| Elasticsearch | Film axtarışı və filtrasiya üçün indeksləmə |
| Apache Kafka | Event-driven arxitektura üçün mesajlaşma |
| Redis | Tez-tez istifadə olunan sorğular üçün caching (planlaşdırılır) |
| MapStruct | DTO ↔ Entity mapping üçün |
| Bean Validation | Request validation |
| GlobalExceptionHandler | Xəta idarəsi və standart API cavabları |
| JUnit5, Mockito | Unit və integration testlər (planlaşdırılır) |
| Docker | Layihənin container üzərində çalışdırılması |

---

## Layihə Modulları

1. **movie-core**  
   - Domain modellər, DTO-lar, service və repository-lərin əsas implementasiyası  
   - MongoDB ilə reaktiv CRUD əməliyyatlar  

2. **movie-kafka**  
   - Kafka producer-lər və shared Kafka konfiqurasiyası  
   - Event publishing üçün mərkəzi modul  

3. **movie-elastic**  
   - Elasticsearch ilə film axtarışı və indeksləmə servisləri  
   - Kafka consumer-lər burada yerləşir, çünki bu modul hadisələrə reaksiya verərək indeksləri yeniləyir  

4. **movie-cache** (planlaşdırılır)  
   - Redis caching implementasiyası  
   - Tez-tez soruşulan sorğuların cavablarını saxlamaq və performansı artırmaq  

5. **movie-api**  
   - Controller-lər və API layer  
   - Reactive endpoints istifadəçilər, filmlər, aktyorlar və rəylər üçün  

6. **application** modulu Movie Hub-un əsas giriş nöqtəsidir. Burada `@SpringBootApplication` annotasiyası var ki:  

   - Spring konteksini işə salır və bütün modulları avtomatik konfiqurasiya edir  
   - Kontrollerləri, servisləri və repository-ləri skan edir  
   - Reactive REST API üçün web server-i işə salır  
   - Kafka, MongoDB, Elasticsearch və Redis konfiqurasiyalarını birləşdirir
---

## Gələcək Planlar

- Redis caching tam implementasiya olunacaq  
- Movie-elastic modulundakı buglar həll ediləcək  
