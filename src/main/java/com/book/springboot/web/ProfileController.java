package com.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final Environment env;

    @GetMapping("/profile")
    public String profile(){                //현재 실행 중인 ActiceProfile을 모두 가져옴.
                                            //real,real1,real2 중 하나라도 있으면 그 값을 반환하도록 함.
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        List<String> realProfiles = Arrays.asList("real","real1","real2");
        String defaultProfiles = profiles.isEmpty()? "default":profiles.get(0);

        return profiles.stream().filter(realProfiles::contains).findAny().orElse(defaultProfiles);
    }
}
