package com.epam.camunda.delegate;

import com.epam.camunda.App;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Component
public class TimeCheckerDelegate implements JavaDelegate {

    private static long previousTime = 0L;
    private static int iterations = 0;
    private static List<Double> resultSet = new ArrayList<>();

    public void execute(DelegateExecution delegateExecution) {
        long time = System.nanoTime();

        if (iterations == 100000) {
            resultSet.set(0, resultSet.get(1));
            DoubleSummaryStatistics statistics = resultSet.stream().mapToDouble(d -> d).summaryStatistics();

//            resultSet.forEach(aDouble -> {
//                System.out.println(aDouble);
//                for (int i = 0; i < aDouble.intValue(); i++) {
//                    System.out.println("------------");
//                }
//            });
            System.out.println("#####################################################################################");
            //Collections.sort(resultSet);
            //resultSet.forEach(System.out::println);
            System.out.println("#####################################################################################");
            System.out.println(statistics);
            System.out.println("#####################################################################################");
            System.exit(1);
        }


        resultSet.add((time - previousTime) / 1e+6);


        iterations++;
        previousTime = System.nanoTime();
    }
}
