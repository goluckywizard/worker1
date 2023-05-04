package ru.nsu.worker;

import com.roytuts.jaxb.WorkerRequest;
import com.roytuts.jaxb.WorkerResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.paukov.combinatorics3.*;

import java.util.ArrayList;

public class WorkerService {
    String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
    public void calculateHash(WorkerRequest request) {
        WorkerResponse result = new WorkerResponse();
        Generator.permutation(alphabet.split("")).withRepetitions(request.getMaxLength()).stream()
                .skip(request.getPartCount() * (request.getPartNumber() - 1))
                .forEach((strings -> {
                    String s = String.join("", strings);
                    if (DigestUtils.md5(s).equals(request.getHash())) {
                        result.getStringList().add(s);
                    }
                }));
        result.getStringList().forEach(System.out::println);
    }
}
