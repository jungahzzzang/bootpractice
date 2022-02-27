package com.book.springboot.service.posts;

import com.book.springboot.domain.posts.Posts;
import com.book.springboot.domain.posts.PostsRepository;
import com.book.springboot.web.dto.PostsListResponseDto;
import com.book.springboot.web.dto.PostsResponseDto;
import com.book.springboot.web.dto.PostsSaveRequestDto;
import com.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//기존에는 @Autowired로 의존성을 주입받았지만,
//@RequiredArgsConstructor를 통해 생성자로 주입받을 수 있다.
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return  new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) //트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선됨.
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
        /*
        map(PostsListResponseDto::new) = map(posts -> new PostslistResponseDto(posts))
         */
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }
}
