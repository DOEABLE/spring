package com.hana4.demo1.controller;

import com.hana4.demo1.dto.UserDTO;
import com.hana4.demo1.service.ApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final ApiService service;

    public ApiController(ApiService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers(){
        return service.getUsers();
    }

    @GetMapping("/users/{id}")
    @Tag(name = "고객 정보", description = "한 명의 고객에 대한 정보")
    @Operation(summary = "URL 링크 설명", description = "펼쳤을 때 설명 부분...")
    @Parameters({
            @Parameter(name = "id", description = "유저 아이디", example = "1"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청에 성공하였습니다.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "해당 유저가 없습니다.")
    })
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id){
        System.out.println("id ="+id);
        UserDTO user = service.getUser(id);
        if (Objects.isNull(user) || user.getId() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    @Tag(name = "고객 등록", description = "이름과 나이로 고객 등록")
    @Operation(summary = "Post로 고객의 이름과 나이 출력", description = "펼쳤을 때 설명 부분...")
    @Parameters({
            @Parameter(name = "name", description = "유저 이름", example = "홍길동"),
            @Parameter(name = "age", description = "유저 나이", example = "33"),
    })
    public UserDTO addUser(@RequestBody UserDTO user){
        return service.addUser(user.getName(),user.getAge());
    }



    @PatchMapping("/users/{id}")
    public ResponseEntity<?> modifyUser(@PathVariable("id") Long id, @RequestBody UserDTO user){
        try{
            user.setId(id);
            return ResponseEntity.ok(service.modifyUser(user));
        }catch(Exception e){
            return ResponseEntity.status(500).body(e);
        }

    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> removeUser(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(service.removeUser(id));
        }catch(Exception e){
            return ResponseEntity.status(500).body(e);
        }

    }


}
