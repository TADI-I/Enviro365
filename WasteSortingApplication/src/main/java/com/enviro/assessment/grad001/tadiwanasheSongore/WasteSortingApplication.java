/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.enviro.assessment.grad001.tadiwanasheSongore;

/**
 *
 * @author tadiw
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.enviro.assessment.grad001.tadiwanasheSongore.repository")
public class WasteSortingApplication {
    public static void main(String[] args) {
        SpringApplication.run(WasteSortingApplication.class, args);
    }
}
