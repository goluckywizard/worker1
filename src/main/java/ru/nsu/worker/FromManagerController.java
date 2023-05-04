package ru.nsu.worker;

import com.roytuts.jaxb.WorkerRequest;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableRabbit
public class FromManagerController {
    @Value("${queue.name}")
    String queueName;
    WorkerService workerService;

    public FromManagerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @RabbitListener(queues = ("${queue.name}"))
    public void rabbitListener(String request) {
        System.out.println(request);
        //workerService.calculateHash(request);
    }
}
