package task.controller;

import task.service.Service;
import task.util.*;

public class Controller {

    Service service = new Service();

    Input input = new Input();
    OutPut outPut = new OutPut();
    Validator validator = new Validator();


    public void run() {
        while (true) {
            outPut.getStartInfo();
            try {
                outPut.outPutResult(service.generationNumberWord(service.parseToIntegerArray(input.getInt())));
            } catch (Exception e) {
                e.printStackTrace();
            }
            outPut.continuedRequest();
            if (!validator.continuedRequests(input.getString().toUpperCase())) {
                break;
            }
        }
    }
}
