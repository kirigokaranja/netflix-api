package com.aws.netflix;

import com.aws.netflix.models.Category;
import com.aws.netflix.models.User;
import com.aws.netflix.models.UserType;
import com.aws.netflix.repositories.CategoryRepository;
import com.aws.netflix.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateDummyData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public CreateDummyData(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Category thriller = new Category("Thriller");
        categoryRepository.save(thriller);

        Category comedy = new Category("Comedy");
        categoryRepository.save(comedy);

        User admin = new User("Admin", UserType.admin, 1);
        userRepository.save(admin);
    }
}
