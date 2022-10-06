package com.dbproject.banksystem.service;

import com.dbproject.banksystem.domain.posts.Posts;
import com.dbproject.banksystem.domain.posts.PostsRepository;
import com.dbproject.banksystem.web.dto.PostsListResponseDto;
import com.dbproject.banksystem.web.dto.PostsResponseDto;
import com.dbproject.banksystem.web.dto.PostsSaveRequestDto;
import com.dbproject.banksystem.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // update 기능에 쿼리를 날리는 부분이 없음
    // 가능한 이유는 JPA 의 "영속성 컨텍스트" -> 엔티티를 영구 저장하는 환경(일종의 논리적 개념)
    // JPA 의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되느냐 아니냐로 갈림

    // JPA 엔티티 매니저가 활성화된 상태(기본 옵션)로 트랜잭션 안에서 DB 에서 Data 를 가져오면
    // 이 Data 는 영속성 컨텍스트가 유지된 상태
    // 이 상태에서 해당 Data 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영
    // 즉, Entity 객체 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없음
    // 이 개념을 "더티 체킹" 이라고 함
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        // postsRepository.delete(posts)
        // * JpaRepository 에서 이미 delete 메소드 지원
        // * 엔티티를 파라미터로 삭제 or deleteById 메소드 이용 시 id로 삭제 가능
        // * 존재하는 Posts 인지 확인을 위해 엔티티 조회 후 그대로 삭제

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
    // (readOnly = true)를 주면
    // 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도 개선
    // 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용하는 것을 추천
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
        // .map(posts -> new PostsListResponseDto(posts))
        // postsRepository 결과로 넘어온 Posts 의 Stream 을 map 을 통해 PostsListResponseDto 로 변환
        // -> List 로 반환하는 메소드
    }
}