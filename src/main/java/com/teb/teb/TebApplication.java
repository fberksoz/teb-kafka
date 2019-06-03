package com.teb.teb;

import com.teb.teb.Utils.Utils;
import com.teb.teb.kafka.Consumer;
import com.teb.teb.objects.LiveData;
import com.teb.teb.repositories.ILogRepository;
import com.teb.teb.repositories.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = {"com.teb.teb.repositories"})
@ComponentScan({"com.teb.teb.repositories"})
@RestController
public class TebApplication {

    private static Repo repo;

    @Autowired
    private Repo repoo;

    @PostConstruct
    private void initStaticDao() {
        repo = this.repoo;
    }

    public static void main(String[] args) {
        SpringApplication.run(TebApplication.class, args);
        Utils.generateContinuouslyLogToFile();
        Utils.continuouslyReadFile();
        Consumer.startConsuming(repo);
    }

    @CrossOrigin
    @RequestMapping("/liveData")
    public String getData() {
        return LiveData.getData();
    }

    @CrossOrigin
    @RequestMapping("/")
    public String home() {
        return "Hello from Docker";
    }
}
