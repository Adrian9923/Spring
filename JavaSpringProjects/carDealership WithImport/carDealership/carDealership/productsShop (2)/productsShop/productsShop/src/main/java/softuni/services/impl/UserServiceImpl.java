package softuni.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.dto.UserDto;
import softuni.models.User;
import softuni.repositories.UserRepository;
import softuni.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final String USERS_FILE_PATH = "C:\\Users\\Legion\\Desktop\\productsShop\\productsShop\\src\\main\\resources\\static\\files\\json\\users.json";
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(Gson gson, ModelMapper modelMapper, UserRepository userRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    private String readInformationFromFile() throws IOException {
        return Files.readString(Path.of(USERS_FILE_PATH));
    }

    @Override
    public void registerUsers() throws IOException {
        UserDto[] users = this.gson
                .fromJson(this.readInformationFromFile(), UserDto[].class);

        List<User> allUsers = userRepository.findAll();
        Random random = new Random();

        for (UserDto userDto : users) {
            User user = this.modelMapper.map(userDto, User.class);

            int numOfFriends = Math.min(random.nextInt(5), allUsers.size() - 1);
            Set<User> userFriends = new HashSet<>();
            for (int i = 0; i < numOfFriends; i++) {
                int randomIndex = random.nextInt(allUsers.size());
                User randomFriend = allUsers.get(randomIndex);
                if (!randomFriend.equals(user)) {
                    userFriends.add(randomFriend);
                }
            }
            user.setUsers(userFriends);
            this.userRepository.saveAndFlush(user);
        }

    }
}
