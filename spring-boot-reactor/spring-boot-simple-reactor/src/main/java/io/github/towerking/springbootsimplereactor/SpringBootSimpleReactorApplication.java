package io.github.towerking.springbootsimplereactor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@Slf4j
public class SpringBootSimpleReactorApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSimpleReactorApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Flux.range(1, 6)
                .publishOn(Schedulers.elastic())    // 6    see here: the order is very important, here request 4 number
                .doOnRequest(n -> log.info("Request {} number", n))
                .doOnComplete(() -> log.info("Publisher complete 1"))
//                .publishOn(Schedulers.elastic())    // 2  if here request 256 number
                .map(i -> {
                    log.info("Publish {}, {}", Thread.currentThread(), i);
                    return 10 / (i - 3); // 3 && 7
                    // return i;    // 1 && 6
                })
                .doOnComplete(() -> log.info("Publisher complete 2"))
                .onErrorResume( e -> {  // 4
                    log.error("Exception {}", e.toString());
                    return Mono.just(-1);
                })
//                .onErrorReturn(-1)  // 3
                .subscribeOn(Schedulers.single())   // 2
                .subscribe(i -> log.info("Subscribe {}: {}", Thread.currentThread(), i),
                        e -> log.error("error {}", e.toString()),
                        () -> log.info("Subscribe complete"),
                        s -> s.request(4)   // 5
                );

        Thread.sleep(2000);
    }
}
