/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.enviro.assessment.grad001.tadiwanasheSongore;

/**
 *
 * @author tadiw
 */
import com.enviro.assessment.grad001.tadiwanasheSongore.model.WasteCategory;
import com.enviro.assessment.grad001.tadiwanasheSongore.repository.WasteCategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.enviro.assessment.grad001.tadiwanasheSongore.repository")
public class WasteSortingApplication {

    private final WasteCategoryRepository wasteCategoryRepository;

    public WasteSortingApplication(WasteCategoryRepository wasteCategoryRepository) {
        this.wasteCategoryRepository = wasteCategoryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(WasteSortingApplication.class, args);
    }

    @PostConstruct
    public void initData() {
        if (wasteCategoryRepository.count() == 0) {
            wasteCategoryRepository.save(new WasteCategory("Plastic"));
            wasteCategoryRepository.save(new WasteCategory("Metal"));
            wasteCategoryRepository.save(new WasteCategory("Glass"));
            System.out.println("Initialized default waste categories.");
        }
    }
}
